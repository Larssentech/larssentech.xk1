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

package org.larssentech.xkomm.ui.shared.util;

import java.awt.Color;

import org.larssentech.lib.awtlib.Alert;
import org.larssentech.xkomm.ui.shared.constants.Constants4Ui;

public class Alerter extends Alert {

	public Alerter() {

		super();
	}

	public Alerter(Color background, Color foreground) {

		super(background, foreground);
	}

	public void badAccountPack(boolean exit) {

		this.showMessage(Constants4Ui.TITLE_J, Constants4Ui.JERROR_CODE_003 + "Your account details are required. Cannot continue. Exiting.");
		if (exit) System.exit(-1);
	}

	public void badLogin(boolean exit) {

		this.showMessage(Constants4Ui.TITLE_J, Constants4Ui.JERROR_CODE_004 + "Could not log in. Check credentials. Exiting.");
		if (exit) System.exit(-1);
	}

	public void badStart(boolean exit) {

		this.showMessage(Constants4Ui.TITLE_J, Constants4Ui.JERROR_CODE_005 + "Could not start XK2. Submit error code to: <bugs@larssentech.org>");
		if (exit) System.exit(-1);
	}

}