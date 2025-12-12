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

package org.larssentech.xkomm.ui.gui.G.chat;

import java.awt.Color;

import org.larssentech.lib.awtlib.InfoPanel2;
import org.larssentech.lib.basiclib.toolkit.DateManipulationToolkit;
import org.larssentech.lib.basiclib.toolkit.StringManipulationToolkit;
import org.larssentech.xkomm.core.obj.model.XmlMessageModel;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.ui.gui.G.constants.Constants4G;
import org.larssentech.xkomm.ui.shared.chat.Xml2Message;
import org.larssentech.xkomm.ui.shared.functions.UisFunctions;

class ChatGExec {

	private ChatGFrame owner;
	private boolean on = true;

	private static String getPuk(String to) {

		return UisFunctions.requestPuk4(to).getStringValue();
	}

	private static void loadHistory(ChatGFrame owner, String from) {

		int historyLines = 200;

		XmlMessageModel[] xmlNuHistoryShort = UisFunctions.loadHistory(from, historyLines);

		for (int i = 0; i < xmlNuHistoryShort.length; i++) {

			Message m = Xml2Message.convertXml2Message(xmlNuHistoryShort[i]);

			owner.getChatArea().appendMessage(m);
		}
	}

	/**
	 * Deletes total history of a contact
	 *
	 * @param to
	 */
	private static void deleteHistory(String to) {

		UisFunctions.requestDeleteChat(to);

	}

	/**
	 * Constructor. Loads ChatGFrame object and also history old and new (XML). It
	 * also loads General Streaming object
	 *
	 * @param owner
	 */
	ChatGExec(ChatGFrame owner) {

		this.owner = owner;
		ChatGExec.loadHistory(owner, this.owner.getTo());
	}

	/**
	 * Tells us if Exec of ChatGFrame in On or isn't On. If Exec is loaded.
	 *
	 * @return
	 */
	boolean isOn() {

		return this.on;
	}

	/**
	 * Requests the "next" TEXT message
	 */
	void fetchNextText() {

		Message message = UisFunctions.requestReceiveTextMessage(this.owner.getTo());

		if (message.getBody().length() > 0) {

			DateManipulationToolkit.formatDate(message.getSentDate(), Constants4G.MSG_IN_DATE_FORMAT);

			this.owner.getChatArea().appendMessage(message);
		}
	}

	/**
	 * Sends the chat message just written by the XK2 user in the chat window
	 *
	 * @param what
	 */
	private void sendTextMessage(String what) {

		Message messagePlain = UisFunctions.requestSendTextMessage(this.owner.getTo(), what);

		this.owner.getChatArea().appendMessage(messagePlain);
		this.owner.getMsgF().setText("");
	}

	/**
	 * Saves chat window location, size, and closes it
	 */
	void recycle() {

		this.on = false;
		UisFunctions.requestRecycleFrame(this.owner.getTo(), this.owner);
	}

	/**
	 * Sets the user we are writing to at the up text field place, in green if
	 * Online, and in red if Offline. If is not a contact, it appears in grey
	 *
	 * @return
	 */
	private Color getStatusColour() {

		if (UisFunctions.requestIsContact(this.owner.getTo())) {

			// TODO: Change for constants in the future
			if (isToOnline())
				return new Color(0, 153, 0);
			else
				return Color.red;
		} else {

			return Color.gray;
		}
	}

	/**
	 * When there is not connection, set Send Button disabled
	 */
	void setWritingAvailability() {

		if (UisFunctions.requestIsContact(this.owner.getTo())) { // When
			// connection

			this.owner.getSendB().setEnabled(true);
		} else { // When without connection

			this.owner.getSendB().setEnabled(false);
		}
	}

	/**
	 * Returns the Online or Offline status of the contact we are writing to
	 *
	 * @return
	 */
	private boolean isToOnline() {

		return UisFunctions.requestOnline4(this.owner.getTo());
	}

	void refresh() {

		this.owner.getToF().setForeground(this.owner.getExec().getStatusColour());

	}

	public void doClear() {

		this.owner.getMsgF().setText("");
		this.owner.getChatArea().setText("");
		ChatGExec.deleteHistory(this.owner.getTo());

	}

	public void doShowPuk() {

		this.owner.getExec();
		String pukS = ChatGExec.getPuk(this.owner.getTo());

		String[] pukA = StringManipulationToolkit.chunkString(pukS, 76);
		String puk = "";
		for (int i = 0; i < pukA.length; i++)
			puk += pukA[i] + "\n";
		new InfoPanel2(15, 80).showInfo("Public Key for " + this.owner.getTo(), puk);

	}

	public static void doShowSysInfo() {

		InfoPanel2 ip2 = new InfoPanel2(12, 35);
		ip2.showInfo("XKomm - System Properties", Constants4G.SYSTEM_INFO);

	}

	public void doSend() {

		if (this.owner.getMsgF().getText().length() > 0 && !this.owner.getMsgF().getText().equals(".")
				&& !this.owner.getMsgF().getText().equals(" "))

			this.owner.getExec().sendTextMessage(this.owner.getMsgF().getText());
	}
}
// <-- Max is 200 lines!!!