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

package org.larssentech.xkomm.core.hub.shared;

import java.util.Date;

import org.larssentech.lib.CTK.objects.PUK;
import org.larssentech.lib.basiclib.console.Out;
import org.larssentech.lib.basiclib.toolkit.DTK2;
import org.larssentech.lib.basiclib.util.XKSequence;
import org.larssentech.xkomm.core.obj.constants.Constants4Core;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.objects.Session;
import org.larssentech.xkomm.core.obj.objects.User;

/**
 * 
 * Public class that only converts raw structures into "base" objects
 * 
 * @author jcer
 * 
 */
public class ObjectFactory {

	private static User importContact(String[] rawContacts) {

		String[] thisRecord = rawContacts;

		String id = thisRecord[0];
		String lastActive = thisRecord[2];

		String email = thisRecord[1];

		PUK puk = new PUK(thisRecord[3]);

		int secondsInactive = Integer.parseInt(thisRecord[6]);

		Date localDate = DTK2.getDateFromString(lastActive, Constants4Core.XK_DATE_FORMAT);

		User u = new User(id, email, localDate);

		u.setSecondsInactive(secondsInactive);
		u.getKeyPair().setPuk(puk);
		u.setLastSeen(localDate);

		u.setGood(true);

		return u;
	}

	public static Message[] importMessages(String[][] rawMessages, User me, final User[] contacts) {

		Message[] m = new Message[rawMessages.length];

		for (int i = 0; i < rawMessages.length; i++)
			m[i] = importMessage(rawMessages[i], me, contacts);

		return m;
	}

	private static Message importMessage(String[] thisRawMessage, User me, final User[] contacts) {

		String serverGeneratedID = thisRawMessage[0];

		String fromId = thisRawMessage[1];
		String msgBody = thisRawMessage[3];
		String sentDate = thisRawMessage[4]; // Arrives as MySQL TIMESTAMP

		String typeS = thisRawMessage[5];
		// String fromEmail = thisRawMessage[6];

		String originalSenderGeneratedID = thisRawMessage[7];

		int type = Integer.parseInt(typeS);

		User to = me;
		User from = null;

		for (int i = 0; i < contacts.length; i++)
			if (contacts[i].getId().equals(fromId)) {
				from = contacts[i];
				break;
			}

		Message m = new Message(serverGeneratedID, from, to, type, msgBody.getBytes());

		m.setUid(originalSenderGeneratedID);
		m.setWay(Message.IN);

		// This ensures the Date we get is for local time zone
		m.setSentDate(DTK2.getLocalDateFromGmtString(sentDate, Constants4Core.XK_DATE_FORMAT2));

		Out.pl("");
		Out.pl("INCOMING " + m.getType());
		Out.pl("Time zone Europe/London. Message timestamp: " + sentDate + " (UK server)");
		Out.pl("Time zone    Local     . Message timestamp: " + m.getSentDate().toString());
		Out.pl("");

		return m;
	}

	public static Message generateMessage(User me, User toUser, int type, byte[] bodyBytes) {

		Message m = new Message(XKSequence.next() + "", me, toUser, type, bodyBytes);
		m.setUid(generateID(me.getId(), toUser.getId()));
		m.setSentDate(new Date());
		m.setWay(Message.OUT);
		m.setGood(true);
		return m;
	}

	private static String generateID(String from, String to) {

		long rand = Math.round(Math.random() * 100);
		if (rand == 100) rand = 99;
		String randS = rand < 10 ? ("0" + rand) : ("" + rand);
		return from + ":" + to + ":" + new Date().getTime() + ":" + randS;
	}

	public static Session makeSession(String[][] strings) {

		if (null != strings && strings.length > 0 && strings[0].length > 0) {

			Session newSession = new Session(strings[0][0]);

			newSession.setSessionChanges(strings[0][1].equals(Session.SESSION_CHANGE) ? true : false);

			return newSession;
		}

		else
			return new Session();
	}

	public static User generateUser(String id, String lastActive) {

		Date lastSeen = DTK2.getDateFromString(lastActive, Constants4Core.XK_DATE_FORMAT);

		User user = new User(id, Constants4Core.EMPTY_STRING, lastSeen);// MySQL

		user.setGood(true);

		return user;
	}

	public static User[] generateContacts(String[][] rawContacts) {

		User[] contacts = new User[rawContacts.length];

		for (int i = 0; i < rawContacts.length; i++) {
			User thisUser = importContact(rawContacts[i]);
			contacts[i] = thisUser;
		}

		return contacts;
	}
}