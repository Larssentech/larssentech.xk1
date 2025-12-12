/*
 * Copyright 2014-2024 Larssentech Developers
 * 
 * This file is part of XKomm.
 * 
 * XKomm is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General License for more details.
 * 
 * You should have received a copy of the GNU General License along with XKomm.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package org.larssentech.xkomm.core.hub.shared;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

import org.larssentech.lib.basiclib.toolkit.CollectionManipulationToolkit;
import org.larssentech.xkomm.core.hub.net.NetMemory;
import org.larssentech.xkomm.core.obj.constants.Constants4Core;
import org.larssentech.xkomm.core.obj.constants.NetworkConstants;
import org.larssentech.xkomm.core.obj.objects.RequestBundle;
import org.larssentech.xkomm.core.obj.util.Logger;

public class Communicator {

	private final Transmitter t;

	/**
	 * Transmitter class takes care of the next level down from the communicator
	 * where the methods were generic and, though low level, still at a functional
	 * stage.
	 * 
	 * Transmitter takes care of connecting, the actual sending and receiving to and
	 * from the server in a vanilla way, not function specific. Transmitter extends
	 * Socketeer for the lowest level of socket and stream creation. Socketeer is a
	 * private inner class of Transmitter, so nobody can touch sockets other than
	 * Transmitter
	 * 
	 * @author jcer
	 * 
	 */
	private class Transmitter {

		private final Socketeer s;

		private Transmitter(final RequestBundle requestBundle) throws SocketException, IOException {

			// this is the ONLY place instances of Socketeer (which
			// create sockets) can be requested as Socketeer is a
			// private inner class of transmitter (for optimal
			// encapsulation)

			this.s = new Socketeer();
			this.connect(requestBundle);
		}

		/**
		 * For anonymous requests to the server: the PUK
		 */
		private Transmitter() {

			this.s = new Socketeer();
		}

		/**
		 * Generic XK2 connect (with the login header details for authentication of the
		 * signed password) to the server method. This connect is common to all
		 * functional connections and so reusable so lives in the transmitter class
		 * 
		 * @param requestBundle
		 * @return
		 * @throws SocketException
		 * @throws IOException
		 * @throws XK2Exception
		 */
		private void connect(final RequestBundle requestBundle) throws SocketException, IOException {

			// The usual socket and stream setup
			this.getS().makeSocket(Constants4Core.SERVER, Constants4Core.PORT);

			// Server sends 2 lines which we discard
			this.fromServer();
			this.fromServer();

			// We send to the server as many lines as we have in the
			// header
			Vector headers = requestBundle.getHeaderList();

			for (int i = 0; i < headers.size(); i++)
				this.toServer(headers.elementAt(i).toString());
		}

		/**
		 * Generic receive 1 line from the server, line returned
		 * 
		 * @param request
		 * @throws XK2Exception
		 */
		private String fromServer() throws IOException {

			return this.getS().getBuf().readLine();
		}

		/**
		 * Generic send 1 line to the server, nothing returned
		 * 
		 * @param request
		 * @throws XK2Exception
		 */
		private void toServer(String request) {

			this.getS().getPr().println(request);
			this.getS().getPr().flush();
		}

		/**
		 * Getter
		 * 
		 * @return
		 */
		private Socketeer getS() {

			return this.s;
		}

		/**
		 * Class to take care of the socket and stream lowest level object management
		 * and connection as well as checking if the network is available or not.
		 * 
		 * @author jcer
		 * 
		 */
		private class Socketeer {

			private Socket s = null;
			private BufferedReader buf;
			private PrintWriter pr;

			private void makeSocket(String url, int port) throws IOException, SocketException {

				// Out.p("_Opening Socket_");
				NetMemory.SOCKETS++;
				this.s = new Socket(url, port);

				this.s.setSoTimeout(SocketParams.getSocketTimeout());
				this.buf = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
				this.pr = new PrintWriter(new OutputStreamWriter(this.s.getOutputStream()));
			}

			/**
			 * Simple method to close all streams and socket
			 * 
			 * @throws IOException
			 */
			private void disconnect() throws IOException {

				// Close all
				this.buf.close();
				this.pr.close();
				this.s.close();

				// Out.p("_Closing Socket_");
				NetMemory.SOCKETS--;

			}

			/**
			 * Getter
			 * 
			 * @return
			 */
			private BufferedReader getBuf() {
				return this.buf;
			}

			/**
			 * Getter
			 * 
			 * @return
			 */
			private PrintWriter getPr() {
				return this.pr;
			}
		} // End of Socketeer

	} // End of Transmitter

	/**
	 * Constructor for when XK2 must authenticate to request anything from the
	 * server. Authentication takes place by the server verifying that the password
	 * is signed by the correct user.
	 * 
	 * @param requestBundle
	 * @throws SocketException
	 * @throws IOException
	 * @throws XK2Exception
	 */
	public Communicator(final RequestBundle requestBundle) throws SocketException, IOException {

		this.t = new Transmitter(requestBundle);
	}

	/**
	 * Constructor for "anonymous" instances where there is no need to send anything
	 * to the server (e.g.: retrieving the server PUK)
	 */
	public Communicator() {

		this.t = new Transmitter();
	}

	/**
	 * "Specific" method that does only one thing, the log in to the server and
	 * return a String array with id and last active for the caller to send to the
	 * factory to convert to an instance of User.
	 * 
	 * @return String[] with id and last active
	 * @throws IOException
	 */
	public String[] login() throws IOException {

		String id = NetworkConstants.EMPTY_STRING;
		String la = NetworkConstants.EMPTY_STRING;

		// Send 1 line
		this.getT().toServer(NetworkConstants.SS_LOGIN);

		// Receive 1 lines
		id = this.getT().fromServer();

		// If ID received
		if (null == id) return new String[0];

		// Get last active
		la = this.getT().fromServer();

		// Close socket and unqueue
		this.getT().getS().disconnect();

		// So this id MUST be us
		return new String[] { id, la };
	}

	/**
	 * Generic method to send anything to the server and return the response which
	 * is boolean, success or not.
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public boolean send(String request) throws IOException {

		// Send 1 line to server
		this.getT().toServer(request);

		// Capture response from server
		String response = this.getT().fromServer();

		// Disconnect
		this.getT().getS().disconnect();

		// Return successful
		return NetworkConstants.OK.equals(response);
	}

	/**
	 * Generic method to receive anything requested from the server. The request is
	 * the parameter passed to the method, the response is always returned as a 2D
	 * String array so needs to be passed through a "factory" to create specific XK2
	 * objects (user, message, etc).
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public String[][] receive(String request) throws IOException {

		// Elastic container, TODO: move to ArrayList
		Vector records = new Vector();

		// Send 1 line to server with a command
		this.getT().toServer(request);

		// Read 1 line from server, which should be OK
		String response = this.getT().fromServer();

		if ((NetworkConstants.OK).equals(response)) {

			// Second elastic collection for each returned line TODO:
			// List
			Vector thisRecord = new Vector();

			// Here comes the engrudo, whatever that is...
			String thisLine = NetworkConstants.EMPTY_STRING;
			thisRecord = new Vector();

			// While not a end of response expect next line
			while (!(thisLine = this.getT().fromServer()).equals(NetworkConstants.RESPONSE_END)) {

				// If the line just received ends a record
				if (thisLine.equals(NetworkConstants.RECORD_END)) {

					// Add the record to 1st collection
					records.addElement(CollectionManipulationToolkit.vector2Array1D(thisRecord));

					// Reset 2nd record collection
					thisRecord.removeAllElements();
				}

				// If not the end of a record, add to the second
				// collection
				else
					thisRecord.addElement(thisLine);
			}

			// We tell the server we received OK (send a receipt)
			this.getT().toServer(NetworkConstants.OK);

			// The server has to acknowledge our receipt
			if (!this.getT().fromServer().equals(NetworkConstants.OK))
				Logger.pl(NetworkConstants.ERROR_IGNORED_RECEIPT);
		}

		// Close socket and unqueue
		this.getT().getS().disconnect();

		// Return 2D array of strings
		return CollectionManipulationToolkit.vector2Array2D(records);
	}

	/**
	 * Specific method just to retrieve (anonymously) the server PUK This method
	 * handles the initial connection to the server and has retry functionality. If
	 * the server PUK cannot be procured, then there is no point in XK2 continuing
	 * to run.
	 * 
	 * @return
	 * @throws IOException
	 * @throws SocketException
	 */
	public String getServerPukAnonymously() throws SocketException, IOException {

		// The usual socket and stream management
		Logger.pl("Requesting server PUK from: " + Constants4Core.SERVER + ":" + Constants4Core.PORT + "...");

		this.getT().getS().makeSocket(Constants4Core.SERVER, Constants4Core.PORT);

		// Server sends 2 lines
		Logger.pl(this.getT().fromServer());

		// Store the second line (contains the PUK)
		String puk = this.getT().fromServer();

		// Log
		Logger.printServerPuk(puk);

		// Send end of request once we have the PUK
		this.getT().toServer(NetworkConstants.ENDER);

		// Return to invoker
		return puk;
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	private Transmitter getT() {

		return this.t;
	}

	public void forceQuit() throws IOException {

		this.getT().getS().disconnect();
		throw new IOException();
	}
}