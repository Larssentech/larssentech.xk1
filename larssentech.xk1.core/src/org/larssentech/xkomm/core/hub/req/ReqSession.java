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
import org.larssentech.xkomm.core.hub.shared.ObjectFactory;
import org.larssentech.xkomm.core.obj.model.LoginHeaderModel;
import org.larssentech.xkomm.core.obj.model.ServerRequestModel;
import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.Session;

class ReqSession {

	static void setSession(Session initSession) {

		ObjectStoreModel.setSession(initSession);
	}

	static Session initSession() throws SocketException, IOException {

		return ObjectFactory.makeSession(new Communicator(LoginHeaderModel.createLoginHeader(ObjectStoreModel.getMe())).receive(ServerRequestModel.initSession(ObjectStoreModel.getMe().getId())));
	}

}
