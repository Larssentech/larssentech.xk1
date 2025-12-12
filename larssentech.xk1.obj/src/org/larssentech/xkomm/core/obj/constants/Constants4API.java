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

public interface Constants4API extends Constants4Core {

	// RSA Constants4Core

	String HOME = System.getProperty("user.home");
	String SEP = System.getProperty("file.separator");
	String XKOMM_FOLDER = ".xkomm";
	String NEW_LINE = "\n";
	String OWN_PRI_K_NAME = "NX_RSA_PRI_KEY";
	String OWN_PUB_K_NAME = "NX_RSA_PUB_KEY";
	String OWN_RSA_DIR = ".nxrsa";
	String OWN_KEYPAIR_ABS_PATH = HOME + SEP + OWN_RSA_DIR + SEP;
	String OWN_PRI_K_ABS_PATH = HOME + SEP + OWN_RSA_DIR + SEP + OWN_PRI_K_NAME;
	String OWN_PUB_K_ABS_PATH = HOME + SEP + OWN_RSA_DIR + SEP + OWN_PUB_K_NAME;

	String USER_HOME = HOME + SEP + XKOMM_FOLDER;
	// History folder
	String HISTORY_FOLDER_NU = USER_HOME + SEP + "historyx" + SEP;

	int NO_NETWORK_EXIT = -1;
	int NO_VALID_JAVA_VERSION = -5;
	boolean JAVA_VERSION_OK = (!System.getProperty("java.version").startsWith("1.1") && !System.getProperty("java.version").startsWith("1.2") && !System.getProperty("java.version").startsWith("1.3")
			&& !System.getProperty("java.version").startsWith("1.4")) || System.getProperty("java.version").startsWith("0");
	String REMEMBER_ME_KEY = "XKomm.rememberMe";
	String EMAIL_KEY = "XKomm.login";
	String PASSWORD_KEY = "XKomm.pass";
	String SCREEN_LOCK_KEY = "XKomm.screenLockLimit";
	String DEFAULT_SCREEN_LOCK_MINS = "10";
	long HEADLESS_THREAD_SLEEP_MILLIS = 10000;
	String USER_FILE2 = "user.file";
	String JAVA = "java";
	String JAR = "-jar";
	String LOG_PATH = USER_HOME + SEP + "logs";
	String VERSION_FILE = USER_HOME + SEP + "version.data";
	String XKOMM_JAR = "XK2J.jar";

	String TAB = "\t";
	String DATE_PATTERN = "yyyy.MM.dd HH:mm";
	String MESSAGE_OUT_TOKEN = " <  ";
	String MESSAGE_IN_TOKEN = " >  ";
	String AERROR_CODE_001 = "[#A0791]";
	String ACCOUNT_FILE = USER_HOME + SEP + USER_FILE2;

	String STREAM_HEADER = "<file>";

	long STREAMX_2_SERVER = 1000;
	String FROM_QUEUE_STRING = " <-- ";
	String S_HISTORY_LINES = "XKomm.historyLines";
	String MAC_0S_SYS = ".DS_Store";
	String XKOMM_JM_JAR = "XK2Jm.jar";
	String XKOMM_K_JAR = "XK2K.jar";
	String TYPING = " Typing...";

	String I_AM_THE_FIRESTARTER = "I'm the Firestarter...";
	String TWISTED_FIRESTARTER = "...twisted Firestarter... (updating XK2, will restart app)";
}
