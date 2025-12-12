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
 * If not, see <http://www.gnu.org/licenses/>..
 */

package org.larssentech.xkomm.ui.gui.G.main;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

import org.larssentech.xkomm.ui.gui.G.constants.Constants4G;
import org.larssentech.xkomm.ui.shared.functions.Functions4Inactivity;
import org.larssentech.xkomm.ui.shared.functions.UisFunctions;
import org.larssentech.xkomm.ui.shared.main.BasicContactLizt;

class MainGFrame extends Frame {

	private final static Label status = new Label();
	private final static TextField meF = new TextField("", 27);
	private final static BasicContactLizt lizt = new BasicContactLizt(7, false);

	MainGFrame() {

		this.build(UisFunctions.requestMyEmail());
		MainGFrame.status.setText(Constants4G.FULL_VERSION_STRING);

		new MainGThread().start();
	}

	private void build(String login) {

		this.setTitle(Constants4G.FULL_VERSION_STRING);
		this.setLayout(new BorderLayout(0, 0));

		getMeF().setText(login);
		getMeF().setEditable(false);

		MainGFrame.lizt.addActionListener(new MainGListener(this));
		MainGFrame.lizt.addItemListener(new MainGListener(this));
		MainGFrame.lizt.addKeyListener(new MainGListener(this));
		MainGFrame.lizt.addMouseListener(new MainGListener(this));

		// getStatus().setForeground(Color.darkGray);

		this.add("North", getMeF());
		this.add("Center", getLizt());
		this.add("South", getStatus());

		this.setMenuBar(new MainGMenuBar());
		this.pack();
		this.setSize(200, 250);
		UisFunctions.requestLoadSavedMainFrame(this);

		this.addComponentListener(new MainGListener(this));
		this.addWindowListener(new MainGListener(this));

		// Start the inactivity tracker
		new Functions4Inactivity().start();

		this.setVisible(true);
	}

	static BasicContactLizt getLizt() { return lizt; }

	static TextField getMeF() { return meF; }

	static Label getStatus() { return status; }
}