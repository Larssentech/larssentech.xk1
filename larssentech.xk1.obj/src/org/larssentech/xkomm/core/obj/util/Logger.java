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

package org.larssentech.xkomm.core.obj.util;

import java.io.File;
import java.util.Date;

import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.io.text.SaveToFile;
import org.larssentech.xkomm.core.obj.constants.Constants4API;

/**
 * 
 * Class for logging activity to file and, if verbose, print out to terminal.
 * This class supports XK2 and all other higher level parts of the XK2 app. The
 * path to the log files is sent from outside, in order to make sure all
 * filesystems can cope with logging (notably Android, which is very different)
 * 
 * @author avanz.io
 * 
 */
public class Logger {

	public static boolean SHUT_UP = false;

	public static final String RECEIVE = "receive.log";
	public static final String HUB = "hub.log";
	public static final String CONTROLLER_LOG = "controller.log";
	public static final String SEND_LOG = "send.log";
	public static final String ORCHESTRATOR_LOG = "orchestrator.log";
	public static final String SOCKET_LOG = "socket.log";
	public static final String RELAY_LOG = "relay.log";
	public static final String STREAM = "stream.log";
	private static final String ERROR_LOG = "error.log";

	public static final String MONITOR_LOG = "monitor.log";
	static String logPath = Constants4API.USER_HOME + Constants4API.SEP + "logs";

	public static void printServerPuk(String puk) {

		if (SHUT_UP) return;

		Logger.pl("=====================================================================");
		Logger.pl("Anonymously Requesting Server PUK...");
		Logger.pl("Server Sent: " + puk.substring(0, 45) + "...");
		Logger.pl("=====================================================================");
		Logger.pl("");
	}

	public static void logError(Exception e) {

		if (SHUT_UP) return;

		String stackTrace = "";

		for (int i = 0; i < e.getStackTrace().length; i++)
			stackTrace += e.getStackTrace()[i] + "\n";

		Logger.processLog(Logger.ERROR_LOG, e.getMessage() + "; Details: \n" + stackTrace + "\n");
	}

	public static void pl(String message) {

		if (SHUT_UP) return;

		Out.pl(new Date() + " >> " + message);

	}

	public static void processLog(String logName, String logLine) {

		if (SHUT_UP) return;

		String fileName = logPath + Constants4API.SEP + logName;
		new File(logPath).mkdir();
		if (new File(fileName).length() > 1024000) new File(fileName).delete();
		SaveToFile.saveToFile(fileName, new Date() + "\t" + logLine, true);
	}

	public static void setLogPath(String logPath) {
		Logger.logPath = logPath;
	}

	public static void p(String string) {

		if (SHUT_UP) return;

		Out.p(string);

	}

}