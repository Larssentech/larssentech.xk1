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

package org.larssentech.xkomm.core.obj.objectStore;

import java.util.Enumeration;

import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.objects.Session;
import org.larssentech.xkomm.core.obj.objects.User;

/**
 * XKomm2 is built around an elastic object store, capable of accommodating as
 * many users we want, messages received and messages going out as needed, with
 * the obvious limitation of the memory stack allocated to the virtual machine,
 * which can be quite large anyway.
 * 
 * XKomm2 has 2 separate faces, NETWORK facing; whatever is incoming or going
 * out will be taken care by the monitor, whose job is to keep the XKomm2 in
 * balance. The other face, user side, is on demand, meaning that some event
 * must trigger the retrieval of users or messages from this store.
 * 
 * There is 1 and only 1 Object Store, so everything is static.
 * 
 * All methods in this class return either single "base" objects (User, Message,
 * ...) or arrays of such objects, as well as booleans and integers. This is to
 * limit access to live collections to this class only.
 * 
 * Using JDK1.1, all casting is done in this class
 * 
 * @author jcer
 * 
 */
public class ObjectStoreModel {

	public static synchronized Session getSession() {

		return ObjectStore.getSession();
	}

	public static synchronized void setSession(Session s) {

		ObjectStore.setSession(s);
	}

	public static synchronized User getMe() { return ObjectStore.getMe(); }

	public static synchronized void setMe(User me) {

		ObjectStore.setMe(me);
	}

	public static synchronized boolean isMessageUidKnown(String uid) {

		return ObjectStore.getKnownIncomingUids().contains(uid);
	}

	public static synchronized boolean recordMessageUid(String uid) {

		ObjectStore.getKnownIncomingUids().addElement(uid);
		return true;
	}

	public static synchronized int getMessageCount4(User u) {

		int count = Integer.parseInt(ObjectStore.getMessagecount().get(u.getId()).toString());

		return count;
	}

	private static synchronized void increaseMessageCount4(User u) {

		int count = Integer.parseInt(ObjectStore.getMessagecount().get(u.getId()).toString());
		ObjectStore.getMessagecount().put(u.getId(), "" + (count + 1));
	}

	private static synchronized void decreaseMessageCount4(User u) {

		int count = Integer.parseInt(ObjectStore.getMessagecount().get(u.getId()).toString());
		ObjectStore.getMessagecount().put(u.getId(), "" + (count - 1));
	}

	public static synchronized void putInInboxv(Message m) {

		ObjectStore.getInboxv().addElement(m);
		increaseMessageCount4(m.getFrom());
	}

	public static synchronized void removeFromInboxv(Message message) {

		ObjectStore.getInboxv().removeElement(message);
		decreaseMessageCount4(message.getFrom());
	}

	public static synchronized void putContact(User thisNewUser) {

		ObjectStore.getContacts().put(thisNewUser.getLogin().getEmail(), thisNewUser);
		if (!ObjectStore.getMessagecount().containsKey(thisNewUser.getId())) ObjectStore.getMessagecount().put(thisNewUser.getId(), "" + 0);
	}

	public static synchronized boolean isContact(User user) {

		return ObjectStore.getContacts().containsKey(user.getLogin().getEmail());
	}

	public static synchronized User getContact4(String contactString) {

		return (User) ObjectStore.getContacts().get(contactString.toLowerCase());
	}

	public static synchronized String[] getContactKeys() {

		String[] keys = new String[ObjectStore.getContacts().size()];
		Enumeration enu = ObjectStore.getContacts().keys();

		int i = 0;
		while (enu.hasMoreElements()) {

			keys[i] = enu.nextElement().toString();
			i++;
		}

		return keys;
	}

	public static User[] getContactValues() {

		User[] users = new User[ObjectStore.getContacts().size()];
		Enumeration enu = ObjectStore.getContacts().keys();

		int i = 0;
		while (enu.hasMoreElements()) {

			User thisUser = (User) ObjectStore.getContacts().get(enu.nextElement());
			users[i] = thisUser;
			i++;
		}

		return users;
	}

	public static void removeContact4(String thisKey) {

		ObjectStore.getContacts().remove(thisKey);
	}

	public static int getOutboxvSize() {

		return ObjectStore.getOutboxv().size();
	}

	public static boolean putInOutboxv(Message message) {

		ObjectStore.getOutboxv().addElement(message);
		return true;
	}

	public static void removeFromOutboxv(Message message) {

		ObjectStore.getOutboxv().removeElement(message);

	}

	public static Message getOutboxvNext() {

		if (ObjectStore.getOutboxv().size() > 0) return (Message) ObjectStore.getOutboxv().elementAt(0);
		else return null;
	}

	public static synchronized Message getInboxvNext(final User u, int type) {

		if (ObjectStoreModel.getMessageCount4(u) == 0) return new Message();

		Message[] messages = new Message[ObjectStore.getInboxv().size()];

		ObjectStore.getInboxv().copyInto(messages);

		Message message = new Message();
		Message returnMessage = new Message();

		for (int i = 0; i < messages.length; i++) {

			message = messages[i];

			if (message.getFrom().getLogin().getEmail().equals(u.getLogin().getEmail()) && message.getType() == type) {

				message.setDelivered(true);
				message.setGood(true);

				returnMessage = message;
				break;
			}
		}

		if (message.isDelivered()) ObjectStoreModel.removeFromInboxv(message);

		return returnMessage;
	}
}