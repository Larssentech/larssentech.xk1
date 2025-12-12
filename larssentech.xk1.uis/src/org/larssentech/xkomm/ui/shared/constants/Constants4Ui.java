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
package org.larssentech.xkomm.ui.shared.constants;

import org.larssentech.xkomm.api.xapi.Xkomm1Api;
import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.core.obj.version.Version4Xk1;

public interface Constants4Ui extends Constants4API, Version4Xk1 {

	String SEP = Constants4API.SEP;
	String DISPLAY_FOLDER = Constants4API.USER_HOME + Constants4API.SEP + "display";

	String TITLE_J = "XK1J";
	String FIRESTARTER = "Firestarter...";

	String USER_FILE2 = "user_11.file";

	String ACCOUNT_FILE = USER_HOME + SEP + USER_FILE2;
	String SYSTEM_INFO = "System info:" + NEW_LINE + "Java Vendor:" + TAB + System.getProperty("java.vendor") + NEW_LINE + "Java Version:" + TAB + System.getProperty("java.version") + NEW_LINE
			+ "OS Arch.:" + TAB + System.getProperty("os.arch") + NEW_LINE + "OS Name:" + TAB + System.getProperty("os.name") + NEW_LINE + "OS Version:" + TAB + System.getProperty("os.version")
			+ NEW_LINE + "Server URL:" + TAB + Xkomm1Api.getServerUrl() + NEW_LINE + "Server Port:" + TAB + Xkomm1Api.getPort() + NEW_LINE;

	int LOGIN_COLS = 32;
	String ERROR_BAD_SYSTEM = "Something prevented XK1 from starting: you must be online and <" + Constants4API.SERVER + ":" + Constants4API.PORT + "> must be accessible)";
	String ERROR_S = "Error";
	String ADD_CONTACT_TITLE_FULL = "XK1" + " " + "New Contact";
	String DIALOG_INVITE_CONTACT = "Enter the email to invite";

	int DEFAULT_WIDTH = 300;
	int DEFAULT_HEIGHT = 500;
	int DEFAULT_X_LOC = 200;
	int DEFAULT_Y_LOC = 200;

	String JERROR_CODE_003 = "[#J003] "; // Login fail
	String JERROR_CODE_004 = "[#J004] "; // Server PUK fail
	String JERROR_CODE_005 = "[#J005] "; // XK1 did not start

	String DOT = ".";
	String SPACE = " ";

	String MAIN_FRAME_DISPLAY = Constants4API.USER_HOME + Constants4API.SEP + "display.data";

	String ALERT_ADD_CONT_1 = "Invite ";
	String ALERT_ADD_CONT_2 = " as a contact?";
	String ALERT_INVITE_SENT = "Invitation sent. Your new contact will appear when they accept the request.";

	int INACTIVITY_1_SECOND = 1000;
	long MAIN_THREAD_SLEEP = 500;
}