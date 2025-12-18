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
package org.larssentech.xkomm.core.hub.net;

import java.io.IOException;
import java.net.SocketException;

import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.hub.shared.Communicator;
import org.larssentech.xkomm.core.hub.shared.ObjectFactory;
import org.larssentech.xkomm.core.obj.constants.NetworkConstants;
import org.larssentech.xkomm.core.obj.model.LoginHeaderModel;
import org.larssentech.xkomm.core.obj.model.ServerRequestModel;
import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.Message;

class NetReceive {

	static void netReceiveMessagesv(int type) throws SocketException, IOException {

		Message[] messages = NetReceive.fetchMessages(type);

		for (int i = 0; i < messages.length; i++) NetReceive.put1MessageInStorev(messages[i]);
	}

	private static Message[] fetchMessages(int type) throws SocketException, IOException {

		String[][] rawMessages = new Communicator(LoginHeaderModel.createLoginHeader(ObjectStoreModel.getMe()))

				.receive(ServerRequestModel.retrieveMessages(NetworkConstants.SOME_MESSAGES, type));

		return ObjectFactory.importMessages(rawMessages, Hub.hubGetMe(), ObjectStoreModel.getContactValues());
	}

	private static void put1MessageInStorev(Message m) {

		if (!ObjectStoreModel.isMessageUidKnown(m.getUid())) {

			ObjectStoreModel.putInInboxv(m);

			ObjectStoreModel.recordMessageUid(m.getUid());
		}
	}
}