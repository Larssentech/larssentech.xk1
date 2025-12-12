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

package org.larssentech.xkomm.core.hub.req;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import org.larssentech.xkomm.core.hub.net.NetMonitor;
import org.larssentech.xkomm.core.hub.shared.Communicator;
import org.larssentech.xkomm.core.hub.shared.ObjectFactory;
import org.larssentech.xkomm.core.obj.constants.Constants4Core;
import org.larssentech.xkomm.core.obj.model.LoginHeaderModel;
import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.objects.User;
import org.larssentech.xkomm.core.obj.util.Logger;

class ReqSystem {

	static String getServerPuk() throws SocketException, IOException {

		return new Communicator().getServerPukAnonymously();
	}

	static boolean detectChanges() {

		boolean b = NetMonitor.haveContactsChanged();
		NetMonitor.setContactshaveChanged(false);
		return b;
	}

	static Message generateMessage(final User me, final User toUser, int type, byte[] bodyBytes) {

		return ObjectFactory.generateMessage(me, toUser, type, bodyBytes);
	}

	static boolean login() throws SocketException, IOException {

		String[] userStrings = new Communicator(LoginHeaderModel.createLoginHeader(ObjectStoreModel.getMe())).login();

		User me = ReqContact.getMe();

		if (userStrings.length == 0) return false;

		User u = ObjectFactory.generateUser(userStrings[0], userStrings[1]);

		me.setId(u.getId());
		me.setLastSeen(u.getLastSeen());
		me.setLoggedIn(true);

		return true;
	}

	public static boolean testNetwork(boolean print, boolean exit) {

		if (print) Logger.pl("Checking overall connectivity...");

		try {

			Socket s = new Socket(Constants4Core.V_TEST_URL, Constants4Core.V_TEST_PORT);
			s.setSoTimeout(2000);

			if (print)
				Logger.pl(Constants4Core.V_TEST_URL + " on port " + Constants4Core.V_TEST_PORT + ": " + true + "\n");

			s.close();

			// If we get to this point, we have successfully made a socket
			// connection
			return true;

		}

		catch (IOException e) {

			if (print) Logger.pl(Constants4Core.V_TEST_URL + " not reachable within time-out value");
			if (print) Logger.pl("Cannot continue, exiting...");

			if (exit) System.exit(-1);
		}

		return false;
	}

	public static boolean detectStatusChanges() {

		boolean b = NetMonitor.haveContactStatusChanged();
		NetMonitor.setContactStatushaveChanged(false);
		return b;
	}
}
