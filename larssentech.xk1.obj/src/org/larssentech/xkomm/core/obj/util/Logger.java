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

import org.larssentech.lib.log.Logg3r;
import org.larssentech.xkomm.core.obj.constants.Constants4API;

public class Logger extends Logg3r {

	public static final String MONITOR_LOG = "monitor.log";
	static String logPath = Constants4API.USER_HOME + Constants4API.SEP + "logs";

	public static void printServerPuk(String puk) {

		System.out.println("");
		log("=====================================================================");
		log("Anonymously Requesting Server PUK...");
		log("Server Sent: " + puk.substring(0, 45) + "...");
		log("=====================================================================");
		System.out.println("");
	}

	public static void logError(Exception e) {

		Logger.log(e.getMessage() + "; Details: \n" + "N/A" + "\n");
	}

	public static void setLogPath(String logPath) {
		Logger.logPath = logPath;
	}
}