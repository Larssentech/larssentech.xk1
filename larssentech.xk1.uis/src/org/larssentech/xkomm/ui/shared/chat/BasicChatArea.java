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
package org.larssentech.xkomm.ui.shared.chat;

import java.awt.TextArea;

import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.ui.shared.constants.Constants4Ui;
import org.larssentech.xkomm.ui.shared.util.UisUtil;

public class BasicChatArea extends TextArea {

	public BasicChatArea(String string, int i, int j, int k) {

		super(string, i, j, k);
	}

	public void appendMessage(Message m) {

		String token = m.getWay() == Message.IN ? Constants4Ui.MESSAGE_IN_TOKEN : Constants4Ui.MESSAGE_OUT_TOKEN;
		this.append(token + UisUtil.requestFormatDate(m.getSentDate()) + " - " + m.getBody() + Constants4Ui.NEW_LINE);

		this.setCaretPosition(this.getText().length());

	}
}
