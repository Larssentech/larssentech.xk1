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
package org.larssentech.xkomm.core.hub.net;

import org.larssentech.xkomm.core.hub.shared.SocketParams;
import org.larssentech.xkomm.core.obj.constants.Constants4Core;
import org.larssentech.xkomm.core.obj.util.Logger;

public class NetMemory {

	private static boolean stopThread = false;
	private static long monitorSleep = Constants4Core.MONITOR_HEART_RATE;
	private static boolean started;
	private static boolean paused;
	private static boolean contactsHaveChanged = true;
	private static boolean contactStatusHaveChanged = true;
	public static int SOCKETS;

	public static boolean isPaused() {
		return paused;
	}

	/**
	 * Method to pause/resume all Core operations
	 * 
	 * @param b
	 */
	public static void pauseMonitor(boolean b) {

		paused = b;
		Logger.pl("Monitors paused: " + paused);
	}

	public static boolean isStarted() {
		return started;
	}

	public static boolean isStopThread() {
		return NetMemory.stopThread;
	}

	public static void setStopThread(boolean b) {
		NetMemory.stopThread = b;
	}

	public static void setStarted() {

		NetMemory.started = true;
	}

	public synchronized static void changeHeartbeat(int i) {

		if (NetMemory.monitorSleep + i >= 500 && NetMemory.monitorSleep + i <= 10000) {
			NetMemory.monitorSleep += i;
			String prefix = i < 0 ? "-" : "+";
			Logger.p(prefix + NetMemory.monitorSleep);
		} else
			Logger.p("=");
		if (SocketParams.getSocketTimeout() + i >= 500 && SocketParams.getSocketTimeout() + i <= 10000)
			SocketParams.setSocketTimeout(SocketParams.getSocketTimeout() + i);

	}

	public static long getMonitorSleep() {
		return NetMemory.monitorSleep;
	}

	public static void setLastDataID(String id) {
		SocketParams.lastDataSentId = id;
	}

	public static String getLastDataID() {
		return SocketParams.lastDataSentId;
	}

	public static boolean isContactsHaveChanged() {
		return contactsHaveChanged;
	}

	public static void setContactsHaveChanged(boolean b) {
		NetMemory.contactsHaveChanged = b;
	}

	public static void setContactStatusHaveChanged(boolean b) {
		NetMemory.contactStatusHaveChanged = b;
	}

	public static boolean isContactStatusHaveChanged() {
		return contactStatusHaveChanged;
	}

}