/*
 * Copyright 2014-2024 Larssentech Developers
 * 
 * This file is part of XKomm.
 * 
 * XKomm is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * XKomm. If not, see <http://www.gnu.org/licenses/>.
 */
package org.larssentech.xkomm.core.hub.shared;

/**
 * 
 * Class to store the multiple parameters that the Network operations require,
 * both for client and server activity. This is not an interface as some of
 * these parameters can be changed by XK2 if/when/where needed, mainly for
 * tracking and performance monitoring purposes.
 * 
 * @author avanz.io
 *
 */
public class SocketParams {

	private static int socketTimeout = 5000;
	private static boolean network = true;

	public static String lastDataSentId = "";

	// Controller counter for whole monitor runs and stucks
	public static long monPrevCount;
	public static long monCount;
	public static long monStucks;

	public static void setNetwork(boolean b) { network = b; }

	public static void resetMonStucks() {

		monStucks = 0;
	}

	public static boolean haveNetwork() {

		return network;
	}

	public static int getSocketTimeout() { return socketTimeout; }

	public static void setSocketTimeout(int socketTimeout) { SocketParams.socketTimeout = socketTimeout; }

}
