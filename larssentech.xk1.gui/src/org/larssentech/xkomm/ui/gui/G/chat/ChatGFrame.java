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

package org.larssentech.xkomm.ui.gui.G.chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import org.larssentech.xkomm.ui.gui.G.constants.Constants4G;
import org.larssentech.xkomm.ui.gui.G.util.Typing;
import org.larssentech.xkomm.ui.shared.chat.BasicChatArea;
import org.larssentech.xkomm.ui.shared.functions.UisFunctions;

class ChatGFrame extends Frame {

	private final Button sendB = new Button(Constants4G.SEND);

	private final Label mainStatusBar = new Label("");
	private final TextField toF = new TextField(25);
	private final TextField msgF = new TextField(25);

	private final BasicChatArea msgA = new BasicChatArea("", 18, 25, BasicChatArea.SCROLLBARS_VERTICAL_ONLY);
	private final ChatGExec exec;
	private final Typing typing;

	/**
	 * Shows chat window of selected contact, with all it's stuff
	 *
	 * @param tracker
	 * @param to
	 */
	ChatGFrame(final String to) {

		this.build(to);

		this.exec = new ChatGExec(this);
		this.typing = new Typing(to, this.mainStatusBar);

		new ChatGThread(this).start();

		this.setVisible(true);

		// This forces the caret to evaluate where it is and do a full scroll
		this.msgA.setCaretPosition(0);
		this.msgA.setCaretPosition(this.msgA.getText().length());
	}

	private void build(final String to) {

		this.setTitle(to);
		this.setLayout(new BorderLayout(0, 0));

		this.addComponentListener(new ChatGListener(this));
		this.addWindowListener(new ChatGListener(this));

		this.sendB.setActionCommand("SEND");
		this.sendB.addKeyListener(new ChatGListener(this));

		this.toF.setEditable(false);
		this.toF.setText(to);

		this.msgA.addMouseListener(new ChatGListener(this));
		this.msgF.addMouseListener(new ChatGListener(this));
		this.msgF.addKeyListener(new ChatGListener(this));

		this.sendB.addActionListener(new ChatGListener(this));

		Panel lowerP = new Panel();
		lowerP.setLayout(new BorderLayout(0, 2));

		lowerP.add("North", this.msgF);
		lowerP.add("Center", this.sendB);
		lowerP.add("South", this.mainStatusBar);

		this.add("North", this.toF);
		this.add("Center", this.msgA);
		this.add("South", lowerP);

		this.setMenuBar(new ChatGMenuBar(this));

		// Pack, resize then add the resize listener--in that order
		this.pack();
		this.setSize(200, 250);

		UisFunctions.requestLoadSavedChatFrame(to, this);
	}

	TextField getToF() { return this.toF; }

	String getTo() { return this.toF.getText().toLowerCase(); }

	ChatGExec getExec() { return this.exec; }

	BasicChatArea getChatArea() { return this.msgA; }

	TextField getMsgF() { return this.msgF; }

	Typing getTyping() { return this.typing; }

	public Component getSendB() { return this.sendB; }
}