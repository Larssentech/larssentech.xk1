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

package org.larssentech.xkomm.api.xapi;

import org.larssentech.xkomm.api.impl.Impl4Contacts;
import org.larssentech.xkomm.api.impl.Impl4History;
import org.larssentech.xkomm.api.impl.Impl4Init;
import org.larssentech.xkomm.api.impl.Impl4Message;
import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.core.obj.constants.Constants4Core;
import org.larssentech.xkomm.core.obj.constants.HistoryConstants;
import org.larssentech.xkomm.core.obj.model.XmlMessageModel;
import org.larssentech.xkomm.core.obj.objects.AccountPack;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.objects.User;
import org.larssentech.xkomm.core.obj.util.Logger;
import org.larssentech.xkomm.core.util.CoreUtil;

/**
 * Class to expose all the public methods available to the developer of any
 * application using XK2. This class is used mainly by GUI projects
 * 
 * @author jcer
 * 
 */
public abstract class Xkomm1Api implements HistoryConstants {

	public static void apiSetMyInactiveSeconds(long seconds) {

		Impl4Contacts.setMyInactiveSeconds(seconds);
	}

	public static boolean[] apiGetContactStatuses() {

		return Impl4Contacts.getStatuses();
	}

	public static boolean apiSendTyping(String contactString) {

		return Impl4Message.sendTyping(contactString);
	}

	public static boolean apiCheckStartupConditions(boolean quit) {

		// if (!Impl4Version.checkJavaVersion(quit)) return false;
		if (!Xkomm1Api.apiTestNetwork(true, false)) return false;

		return true;
	}

	/**
	 * Method to request the initial login by the user who wants to connect to
	 * the XK2 network. If the credentials are correct after passing throught
	 * the RSA encryption cycle, returns true, else false.
	 * 
	 * @param email
	 * @param plainPass
	 * @return true or false
	 */
	public static boolean apiInitialLogin(AccountPack pack) {

		return Impl4Init.initialLogin(pack);
	}

	/**
	 * Method to invoke the start of XK2 after the initial login has happened
	 * successfully.
	 * 
	 * @return true or false
	 */
	public static boolean apiStartXK2() {

		return Impl4Init.startXK2();
	}

	/**
	 * Method to request an invitation be sent to another user via message.
	 * 
	 * @param contactString
	 */
	public static void apiInviteUser(String contactString) {

		Hub.hubInviteUser(contactString);
	}

	public static void apiPl(String message) {

		Logger.pl(message);
	}

	/**
	 * Method to request the generation of an instance of a Message object based
	 * on the data sent by the user.
	 * 
	 * @param contactString
	 * @param type
	 * @param bodyBytes
	 * @return the message requested
	 */
	public static Message apiGenerateMessage(String contactString, int type, byte[] bodyBytes) {

		return Hub.hubGenerateMessage(contactString, type, bodyBytes);
	}

	/**
	 * Method to request XK2 exits after a number of seconds
	 * 
	 * @param seconds2Exit
	 */
	public static void apiExit(int seconds2Exit) {

		CoreUtil.doExit(seconds2Exit, "Exit requested...");
	}

	/**
	 * Method to check if XK2 was online last time a network connection took
	 * place. This is not a live check, but simply relies on a previous result.
	 * 
	 * @return
	 */
	public static boolean apiHaveNetwork() {

		return Hub.hubHaveNetwork();
	}

	/**
	 * Method to request the count of messages from a specific user in the
	 * inbox.
	 * 
	 * @param fromUser
	 * @return the number of messages
	 */
	public static int apiGetInboxCountFrom(String fromUser) {

		return Hub.hubGetInboxCountFrom(Hub.hubGetContact4(fromUser));
	}

	/**
	 * Method to request the next "sys" message in the inbox from a specific
	 * user contact.
	 * 
	 * @param contactString
	 * @return
	 */
	public static Message apiGetNextSysMessage(String contactString) {

		return Impl4Message.getNextSysMessage(contactString);
	}

	/**
	 * Method to request the sending of a message. All the details are within
	 * the instance of message. There is a parameter for save to history or not.
	 * 
	 * @param message
	 * @param history
	 * @return
	 */
	public static boolean apiSendMessage(Message message, boolean history) {

		return Impl4Message.sendMessage(message, history);
	}

	/**
	 * Retrieves the next text message from contactString
	 * 
	 * @param contactString
	 * @return
	 */
	public static Message apiGetNextTextMessage(String contactString, boolean history) {

		return Impl4Message.getNextTextMesage(contactString, history);
	}

	/**
	 * ANDROID --DO NOT REMOVE--
	 * 
	 * @param b
	 * 
	 * @return
	 */
	private static boolean apiTestNetwork(boolean print, boolean exit) {

		return Impl4Init.testNetwork(print, exit);
	}

	/**
	 * 
	 * @return
	 */
	public static String[] apiGetContactEmails() {

		return Hub.hubGetContactEmails();
	}

	/**
	 * 
	 * @param contactString
	 * @return
	 */
	public static boolean apiDeleteContact(String contactString) {

		return Hub.hubDeleteContact(Hub.hubGetContact4(contactString));
	}

	/**
	 * 
	 * @param contactString
	 * @return
	 */
	public static boolean apiIsContact(String contactString) {

		return Impl4Contacts.isContact(contactString);
	}

	/**
	 * Method to request the user object for the user of the app (aka 'me')
	 * 
	 * @return
	 */
	public static User apiGetMe() {

		return Hub.hubGetMe();
	}

	/**
	 * 
	 * @param contactString
	 * @return
	 */
	public static User apiGetContact4Email(String contactString) {

		return Hub.hubGetContact4(contactString);
	}

	public static XmlMessageModel[] apiGetHistory(String to, int lines) {

		return Impl4History.getHistory(Constants4API.HISTORY_FOLDER_NU, to, lines);
	}

	public static void apiDeleteHistory(String contactString) {

		Impl4History.deleteHistory(contactString);
	}

	public static String getServerUrl() {

		return Constants4Core.SERVER;
	}

	public static int getPort() {

		return Constants4Core.PORT;
	}

	public static boolean apiIsOnline(String contactString) {

		return Impl4Contacts.isOnline(contactString);
	}

	public static boolean apiGetContactsHaveChanged() {

		return Hub.hubDetectChanges();
	}

	public static boolean apiGetContactStatusHaveChanged() {

		return Hub.hubDetectStatusChanges();
	}

}