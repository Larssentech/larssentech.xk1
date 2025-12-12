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

import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.obj.objects.User;

public class Impl4Contacts {

	public static User[] getContacts() {

		return Hub.hubGetContacts();
	}

	public static boolean areAllOffline() {

		boolean[] b = getStatuses();

		for (int i = 0; i < b.length; i++)
			if (b[i]) return false;
		return true;
	}

	protected static boolean isNull(User user) {

		return null == user;
	}

	public static boolean isContact(String who) {

		if (isNull(Hub.hubGetContact4(who))) return false;

		return Hub.hubIsContact(Hub.hubGetContact4(who));
	}

	public static boolean[] getStatuses() {

		boolean[] statuses = new boolean[Hub.hubGetContacts().length];

		User[] localContacts = Hub.hubGetContacts();

		for (int i = 0; i < localContacts.length; i++) {
			statuses[i] = localContacts[i].isOnline();
		}

		return statuses;
	}

	public static void setMyInactiveSeconds(long seconds) {

		Hub.hubGetMe().setSecondsInactive(seconds);
	}

	public static boolean isOnline(String contactString) {

		return Hub.hubGetContact4(contactString).isOnline();
	}

}
