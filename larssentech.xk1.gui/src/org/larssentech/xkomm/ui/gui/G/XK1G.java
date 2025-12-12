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

package org.larssentech.xkomm.ui.gui.G;

import org.larssentech.lib.basiclib.toolkit.DTK2;
import org.larssentech.xkomm.core.obj.version.Version4Xk1;
import org.larssentech.xkomm.ui.gui.G.constants.Constants4G;
import org.larssentech.xkomm.ui.gui.G.main.MainGFrameLauncher;
import org.larssentech.xkomm.ui.shared.functions.UisFunctions;
import org.larssentech.xkomm.ui.shared.util.Starter;
import org.larssentech.xkomm.ui.shared.util.UisUtil;

public class XK1G implements Constants4G {

	/**
	 * Main entry point for the execution of XKomm2. This is the main class also in
	 * the manifest for the Jar package. No arguments are needed and nothing is
	 * passed on to the constructor.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		DTK2.OLD_JRE = true;

		// Print the copyright
		XK1G.print();

		// Start
		new XK1G();
	}

	public static void print() {

		UisUtil.pl("");
		UisUtil.pl("------------------------------------------");
		UisUtil.pl("Larssentech XKomm Version: " + Version4Xk1.BASE_VERSION_STRING);
		UisUtil.pl("XK1G GUI " + Version4Xk1.G_VERSION);
		UisUtil.pl("------------------------------------------");
		UisUtil.pl("");
	}

	XK1G() {

		// Checks for everything to be OK to start
		Starter.verify();

		if (UisFunctions.requestLogin()) new MainGFrameLauncher();
	}
}