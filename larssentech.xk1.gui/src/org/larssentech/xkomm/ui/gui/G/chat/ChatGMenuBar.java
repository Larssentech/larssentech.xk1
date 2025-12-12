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

package org.larssentech.xkomm.ui.gui.G.chat;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

/**
 * Jeff
 *
 * @author Jeff Cerasuolo
 *
 */
class ChatGMenuBar extends MenuBar {

	/**
	 * Loads chat menu bar
	 *
	 * @param owner
	 */
	public ChatGMenuBar(final ChatGFrame owner) {

		// File Menu
		final Menu file = new Menu("File");
		final MenuItem close = new MenuItem("Close");

		close.setActionCommand("CLOSE");
		close.addActionListener(new ChatGListener(owner));

		file.add(close);
		this.add(file);

		// Chat menu
		final Menu chat = new Menu("Chat");

		final MenuItem clear, streamFile, puk;

		clear = new MenuItem("Clear Chat");
		streamFile = new MenuItem("Stream File");
		streamFile.setEnabled(false);
		puk = new MenuItem("View Public Key For Contact");

		streamFile.setActionCommand("STREAM");
		streamFile.addActionListener(new ChatGListener(owner));

		clear.setActionCommand("CLEAR");
		clear.addActionListener(new ChatGListener(owner));

		puk.setActionCommand("SHOW_PUK");
		puk.addActionListener(new ChatGListener(owner));

		chat.add(streamFile);
		chat.add(clear);
		chat.add(puk);
		this.add(chat);

		// Help Menu
		final Menu help = new Menu("Help");
		final MenuItem system = new MenuItem("System Info");

		system.setActionCommand("SYS_INFO");
		system.addActionListener(new ChatGListener(owner));

		help.add(system);
		this.add(help);
	}
}
