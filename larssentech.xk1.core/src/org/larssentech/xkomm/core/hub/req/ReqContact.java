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

import org.larssentech.xkomm.core.hub.shared.Communicator;
import org.larssentech.xkomm.core.obj.model.LoginHeaderModel;
import org.larssentech.xkomm.core.obj.model.ServerRequestModel;
import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.User;

class ReqContact {

	static boolean inviteUser(String contactString) throws SocketException, IOException {

		return new Communicator(LoginHeaderModel.createLoginHeader(ObjectStoreModel.getMe())).send(ServerRequestModel.inviteUser(contactString));
	}

	static String[] getContactKeys() {

		return ObjectStoreModel.getContactKeys();
	}

	static User findContact4(String contactString) {

		User thisUser = ObjectStoreModel.getContact4(contactString.toLowerCase());

		return (null == thisUser) ? new User() : thisUser;
	}

	static User getMe() {

		return ObjectStoreModel.getMe();
	}

	static void setMe(final User me) {

		ObjectStoreModel.setMe(me);
	}

	static boolean deleteContact(final User user) throws SocketException, IOException {

		return new Communicator(LoginHeaderModel.createLoginHeader(ObjectStoreModel.getMe())).send(ServerRequestModel.deleteContact(user));
	}
}