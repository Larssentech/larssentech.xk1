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

import java.util.Hashtable;
import java.util.Vector;

import org.larssentech.xkomm.core.obj.objects.Session;
import org.larssentech.xkomm.core.obj.objects.User;

/**
 * JDK1.1
 */
class ObjectStore {

	private static User me;
	private static Session session;
	private static final Hashtable messageCount = new Hashtable();
	private static final Hashtable contacts = new Hashtable();
	private static final Vector inboxv = new Vector();
	private static final Vector outboxv = new Vector();

	private static final Vector knownIncomingUids = new Vector();

	static Hashtable getMessagecount() { return messageCount; }

	static Hashtable getContacts() { return contacts; }

	static Vector getKnownIncomingUids() { return knownIncomingUids; }

	static Session getSession() { return session; }

	static void setSession(Session session) { ObjectStore.session = session; }

	static User getMe() { return me; }

	static void setMe(User me) { ObjectStore.me = me; }

	public static Vector getInboxv() { return inboxv; }

	public static Vector getOutboxv() { return outboxv; }
}
