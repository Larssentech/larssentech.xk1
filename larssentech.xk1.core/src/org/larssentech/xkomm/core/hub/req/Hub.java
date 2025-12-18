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

package org.larssentech.xkomm.core.hub.req;

import java.io.IOException;
import java.net.SocketException;
import java.util.Vector;

import org.larssentech.lib.CTK.objects.PUK;
import org.larssentech.xkomm.core.hub.net.NetMonitor;
import org.larssentech.xkomm.core.hub.shared.SocketParams;
import org.larssentech.xkomm.core.obj.constants.Constants4Core;
import org.larssentech.xkomm.core.obj.constants.NetworkConstants;
import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.objects.Session;
import org.larssentech.xkomm.core.obj.objects.User;
import org.larssentech.xkomm.core.obj.util.Logger;

/**
 * 
 * The Hub is the entry point for requests from higher level parts of XK2. The
 * API (XAPI), is the only user of the Hub (for now). Hub is abstract to ensure
 * there is no attempt to instantiate it as there is one and only one Hub per
 * client runtime execution.
 * 
 * @author avanz.io
 *
 */
public abstract class Hub {

	private static NetMonitor controller;

	public static boolean hubDetectChanges() {

		return ReqSystem.detectChanges();
	}

	public static void hubSetLogPath(String logPath) {

		Logger.setLogPath(logPath);

	}

	public static boolean hubIsControllerAlive() {

		return null != controller;
	}

	public static Vector hubGetControllerInfo() {

		return controller.getControllerInfo();
	}

	public static void hubSetMe(User me) {

		ReqContact.setMe(me);
	}

	/**
	 * Method for the initial log-in for the user into the XK2 network
	 * 
	 * @return true or false, depending on login success
	 */
	public static boolean hubInitialLogin() {

		Logger.log(Hub.class.getName() + ": Server URL: <" + Constants4Core.SERVER + "> Port: <" + Constants4Core.PORT + ">");

		try {

			if (ReqSystem.login() && ReqContact.getMe().isLoggedIn()) {

				Logger.log(Hub.class.getName() + ":" + "initialLogin:" + " ...OK");

				ReqSession.setSession(ReqSession.initSession());

				return true;
			}

			Logger.log(Hub.class.getName() + ":" + "initialLogin:" + " ...FAIL");

		}

		catch (SocketException e) {

			Logger.log(Hub.class.getName() + ":initialLogin: Cannot login: " + e.toString());
		} catch (IOException e) {

			Logger.log(Hub.class.getName() + ":initialLogin: Cannot login: " + e.toString());
		}

		return false;
	}

	public static void hubPauseMonitor(boolean b) {

		NetMonitor.pauseMonitor(b);
	}

	public static boolean hubStartXkomm() {

		if (ReqContact.getMe().isLoggedIn()) {

			Logger.log(Hub.class.getName() + ":" + "starting...");

			if (!NetMonitor.isStarted()) {

				controller = new NetMonitor();
				controller.startMonitor();

				if (controller.getMonitor().isAlive()) Logger.log(Hub.class.getName() + ":" + "start XKomm:" + " ...OK");

				return true;
			}

			return false;

		}

		return false;
	}

	public static boolean hubRequestNetworkSlot() {

		while (!hubHaveNetwork()) {
			try {

				Logger.log(" W4N...");

				Thread.sleep(Constants4Core.REQUEST_NET_SLEEP);

			} catch (InterruptedException ignored) {
			}
		}
		return true;
	}

	public static boolean hubPut1InOutbox(Message message) {

		return ReqSend.put1InOutboxv(message);
	}

	public static boolean hubIsContact(User user) {

		return ObjectStoreModel.isContact(user);
	}

	public static String[] hubGetContactEmails() {

		return ReqContact.getContactKeys();
	}

	public static User hubGetMe() {

		return ReqContact.getMe();
	}

	public static Message hubGenerateMessage(String to, int type, byte[] bodyBytes) {

		return ReqSystem.generateMessage(Hub.hubGetMe(), Hub.hubGetContact4(to), type, bodyBytes);
	}

	public static Message hubGetNextTextFromInbox(User u) {

		return ReqReceive.getNextFromInboxv(u, Message.TEXT);
	}

	public static Message hubGetNextSysFromInbox(User u) {

		return ReqReceive.getNextFromInboxv(u, Message.SYS);
	}

	public static Message hubGetNextDataFromInbox(User u) {

		return ReqReceive.getNextFromInboxv(u, Message.DATA);
	}

	public static int hubGetInboxCountFrom(User user) {

		return ReqReceive.getInboxCountFrom(user);
	}

	public static User hubGetContact4(String contactString) {

		return ReqContact.findContact4(contactString);
	}

	public static PUK hubGetServerPUK() {

		try {

			return new PUK(ReqSystem.getServerPuk());
		}

		catch (Exception e) {

			Logger.log(NetworkConstants.GET_SERVER_PUK_ERROR + e.getClass().getName());
		}

		Logger.log("Failed to anonymously retrieve server PUK: Cannot Continue... Goodbye.");

		return new PUK();
	}

	public static boolean hubDeleteContact(User user) {

		try {
			return ReqContact.deleteContact(user);
		} catch (SocketException e) {

			Logger.log("Hub:deleteContact failed (" + e.getMessage() + ")");
		} catch (IOException e) {

			Logger.log("Hub:deleteContact failed (" + e.getMessage() + ")");
		}

		return false;
	}

	public static boolean hubHaveNetwork() {

		return SocketParams.haveNetwork();
	}

	public static User[] hubGetContacts() {

		return ObjectStoreModel.getContactValues();
	}

	public static int hubGetOutboxSize() {

		return ReqSend.getOutboxvSize();
	}

	public static boolean hubInviteUser(String otherUserEmail) {

		try {
			return ReqContact.inviteUser(otherUserEmail);
		} catch (SocketException e) {

			Logger.log("Hub:inviteUser failed (" + e.getMessage() + ")");
		} catch (IOException e) {

			Logger.log("Hub:inviteUser failed (" + e.getMessage() + ")");
		}

		return false;
	}

	public static void hubExit() {

		NetMonitor.doExit();
	}

	public static void hubSetSession(Session serverSession) {

		ReqSession.setSession(serverSession);

	}

	public static boolean hubTestNetwork(boolean print, boolean exit) {

		return ReqSystem.testNetwork(print, exit);
	}

	public static boolean hubDetectStatusChanges() {

		return ReqSystem.detectStatusChanges();
	}

}