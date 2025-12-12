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
 * XKomm. If not, see <http://www.gnu.org/licenses/>..
 */

package org.larssentech.xkomm.ui.gui.G.util;

import org.larssentech.lib.awtlib.Alert;
import org.larssentech.lib.awtlib.InfoPanel2;
import org.larssentech.xkomm.ui.gui.G.constants.Constants4G;

public class Alerter {

	/**
	 * Alert window confirming file streaming
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean confirmStreamFile(String fileName) {

		return new Alert().showConfirm("XKomm File Streaming...", "You will be sending file: " + fileName + "; continue?");
	}

	/**
	 * Alert message for "About" copyright and credits information
	 */
	public static void messageAbout() {

		new Alert().showMessage("About XKomm", Constants4G.COPYRIGHT + " - " + "Larssentech.org");
	}

	/**
	 * Alert windows showing that a contact must be selected
	 */
	public static void messageNoContactSelected() {

		new Alert().showMessage("XKomm", "A contact must be selected to do this...");
	}

	/**
	 * Window panel showing system properties
	 */
	public static void messageShowSystemInfo() {

		new InfoPanel2(12, 35).showInfo("XKomm - System Properties", Constants4G.SYSTEM_INFO);
	}

	/**
	 * Alert window confirming contact delete
	 * 
	 * @return
	 */
	public static boolean confirmDeleteContact() {

		return new Alert().showConfirm("Are you sure you want to delete this user from your contacts?");
	}
}