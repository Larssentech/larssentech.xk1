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

import org.larssentech.lib.awtlib.Alert;
import org.larssentech.xkomm.api.util.Config;
import org.larssentech.xkomm.api.xapi.Xkomm1Api;
import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.ui.shared.constants.Constants4Ui;

public class Starter implements Constants4Ui, Constants4API {

	public static void verify() {

		Config.setConfiguration();

		if (!Xkomm1Api.apiCheckStartupConditions(false)) Starter.errorQuit(ERROR_BAD_SYSTEM);

	}

	private static void errorQuit(String string) {

		new Alert().showMessage(ERROR_S, string);
		System.exit(0);
	}
}