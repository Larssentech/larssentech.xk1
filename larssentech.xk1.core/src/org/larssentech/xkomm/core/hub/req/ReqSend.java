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

import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.Message;

class ReqSend {

	/*
	 * static boolean put1InOutbox(final Message message) {
	 * 
	 * return ObjectStoreModel.putInOutbox(message); }
	 */

	static boolean put1InOutboxv(final Message message) {

		return ObjectStoreModel.putInOutboxv(message);
	}

	/*
	 * static int getOutboxSize() {
	 * 
	 * return ObjectStoreModel.getOutboxSize(); }
	 */

	public static int getOutboxvSize() {

		return ObjectStoreModel.getOutboxvSize();
	}

}
