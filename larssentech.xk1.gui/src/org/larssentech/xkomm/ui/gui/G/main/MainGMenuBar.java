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
 * XKomm. If not, see <http://www.gnu.org/licenses/>...
 */

package org.larssentech.xkomm.ui.gui.G.main;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

class MainGMenuBar extends MenuBar {

	/**
	 * Loads all Main Menu Bar: File, Edit, Contact, Account and Help menus,
	 * with all their commands
	 * 
	 * @param owner
	 */
	MainGMenuBar() {

		// FILE Menu ------------------------------------
		final Menu file = new Menu("File");
		final MenuItem exit = new MenuItem("Exit");
		file.add(exit);
		this.add(file);

		// Events

		exit.setActionCommand("EXIT");
		exit.addActionListener(new MainGListener(null));

		// CONTACT Menu ------------------------------------
		final Menu contact = new Menu("Contact");
		final MenuItem addContact = new MenuItem("Add Contact");
		final MenuItem deleteContact = new MenuItem("Delete Contact");

		// Events
		addContact.setActionCommand("ADD_CONTACT");
		addContact.addActionListener(new MainGListener(null));

		deleteContact.setActionCommand("DELETE_CONTACT");
		deleteContact.addActionListener(new MainGListener(null));

		contact.add(addContact);
		contact.add(deleteContact);

		this.add(contact);

		// ACCOUNT Menu ------------------------------------
		final Menu account = new Menu("Account");
		this.add(account);

		// HELP Menu ------------------------------------
		final Menu help = new Menu("Help");
		final MenuItem system = new MenuItem("System Info");
		final MenuItem about = new MenuItem("About XKomm");

		about.setActionCommand("ABOUT");
		about.addActionListener(new MainGListener(null));

		system.setActionCommand("SYS_INFO");
		system.addActionListener(new MainGListener(null));

		help.add(system);
		help.add(about);
		this.add(help);
	}
}