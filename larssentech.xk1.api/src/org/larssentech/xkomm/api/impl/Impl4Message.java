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
package org.larssentech.xkomm.api.impl;

import java.util.Date;

import org.larssentech.xkomm.api.xapi.Xkomm1Api;
import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.objects.User;

public class Impl4Message {

	public static Message getNextSysMessage(String fromEmail) {

		Message message = getNext4Type(fromEmail, Message.SYS);

		return message;
	}

	public static boolean sendMessage(Message message, boolean history) {

		if (history) Impl4History.saveSent2History(message);

		return putInOutboxAndTrack(message).length() > 0;
	}

	static Message getNext4Type(String from, int type) {

		User user = Hub.hubGetContact4(from);

		Message message = type == Message.SYS ? Hub.hubGetNextSysFromInbox(user) : Hub.hubGetNextTextFromInbox(user);

		if (message.isDelivered()) return message;
		return new Message();
	}

	public static Message getNextTextMesage(String from, boolean save2History) {

		Message message = getNext4Type(from, Message.TEXT);

		if (save2History) Impl4History.saveReceived2History(message);

		return message;
	}

	public static String putInOutboxAndTrack(Message m) {

		Hub.hubPut1InOutbox(m);
		return m.getSid();

	}

	public static boolean sendTyping(String contactString) {

		if (Hub.hubHaveNetwork()) {

			Message message = Hub.hubGenerateMessage(contactString, Message.SYS, Constants4API.TYPING.getBytes());

			message.setSentDate(new Date());

			Xkomm1Api.apiSendMessage(message, Message.SAVE_HISTORY_NO);

			return true;
		}

		return false;
	}
}