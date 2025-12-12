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
package org.larssentech.xkomm.core.hub.net;

import java.io.IOException;
import java.net.SocketException;

import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.hub.shared.Communicator;
import org.larssentech.xkomm.core.obj.constants.Constants4Core;
import org.larssentech.xkomm.core.obj.model.LoginHeaderModel;
import org.larssentech.xkomm.core.obj.model.ServerRequestModel;
import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.util.Logger;
import org.larssentech.xkomm.core.util.CoreUtil;

class NetSend {

	static boolean netSendMessagesv() throws SocketException, IOException {

		if (Hub.hubGetOutboxSize() == 0) return false;

		for (int i = 0; i < Hub.hubGetOutboxSize(); i++) netSendv(ObjectStoreModel.getOutboxvNext());

		return true;
	}

	private static boolean netSendv(final Message message) throws SocketException, IOException {

		if (null == message || !message.isGood() || message.isSent()) return false;

		CoreUtil.logSend("Send", message);

		final Communicator com = new Communicator(LoginHeaderModel.createLoginHeader(ObjectStoreModel.getMe()));

		if (message.getType() == Message.DATA) startNetTimer(com, message);

		// Long running, timer may force IOException
		message.setSent(com.send(ServerRequestModel.outgoingMessageXml(message)));

		processSendResultv(message);

		return message.isSent();

	}

	private static void startNetTimer(final Communicator com, final Message message) {

		Thread t = new Thread() {

			int counter = 0;

			public void run() {

				this.setName(Constants4Core.NET_SEND_TIMER_NAME);

				while (true) {

					this.counter++;

					Logger.p(".");

					if (message.isSent()) break;

					if (this.counter >= 150) {

						try {

							com.forceQuit();
						}

						catch (IOException e) {

							CoreUtil.logSend("Stop", message);
							e.printStackTrace();

							processSendResultv(message);
							break;
						}
					}

					try {
						Thread.sleep(100);
					}
					catch (InterruptedException ignored) {

					}
				}
			}
		};
		t.start();
	}

	private static void processSendResultv(Message message) {

		if (message.isSent()) {

			ObjectStoreModel.removeFromOutboxv(message);

			// To free the queue for large pay-loads (data blocks)
			if (message.getType() == Message.DATA) NetMemory.setLastDataID("");

			CoreUtil.logSend("Sent", message);
		}

		else {
			Logger.pl("\n---- Not sent");
			CoreUtil.logSend("Fail", message);
		}
	}
}