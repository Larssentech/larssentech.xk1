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

package org.larssentech.xkomm.core.obj.constants;

/**
 * 
 * Interface to provide constants for XK2. This interface is extended by higher
 * level interfaces, so Constants4Core only needs to hold the bare minimum for
 * the base SK2 engine.
 * 
 * @author avanz.io
 * 
 */
public interface Constants4Core {
	// Strings
	String XK_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.'0'";
	String EMPTY_STRING = "";
	String FROM_QUEUE_STRING = " <-- ";

	// Thread sleeps (2 threads: controller and monitor)
	long MONITOR_HEART_RATE = 2000;
	long CONTROLLER_HEART_RATE = MONITOR_HEART_RATE * 10;
	long REQUEST_NET_SLEEP = 2000;
	String OUT_MSG_STUCK = " MESSAGE STUCK IN OUTBOX ";
	String XK_DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS";
	String MONITOR_NAME = "xkomm.thread.monitor";
	String V_TEST_URL = "www.w3.org";
	int V_TEST_PORT = 80;
	String NET_SEND_TIMER_NAME = "Thread-Send-Counter";
	String MONITOR_EXCEPTION = "NetController>Monitor: caught exception; details:";
	String SERVER = "xkomm.larssentech.org";
	// public static final String SERVER = "localhost";
	int PORT = 60001;
}