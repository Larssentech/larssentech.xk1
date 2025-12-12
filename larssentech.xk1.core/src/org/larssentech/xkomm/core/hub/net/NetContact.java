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
import java.util.Vector;

import org.larssentech.xkomm.core.hub.shared.Communicator;
import org.larssentech.xkomm.core.hub.shared.ObjectFactory;
import org.larssentech.xkomm.core.obj.model.LoginHeaderModel;
import org.larssentech.xkomm.core.obj.model.ServerRequestModel;
import org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel;
import org.larssentech.xkomm.core.obj.objects.User;

class NetContact {

	static void netUpdateContacts() throws SocketException, IOException {

		/* Fetch the contacts from the server */
		User[] serverContacts = ObjectFactory.generateContacts(

				new Communicator(LoginHeaderModel.createLoginHeader(ObjectStoreModel.getMe()))

						.receive(ServerRequestModel.retrieveContacts()));

		/* Contacts from the server that we do not presently have */
		updateContacts1(serverContacts);

		/* Contacts that are no longer arriving from the server */
		updateContacts2(serverContacts);

		/* Contacts common both to server and local: update attributes */
		updateContacts3(serverContacts);

	}

	// Server contacts we haven't got yet locally
	private static void updateContacts1(User[] serverContacts) {

		final Vector contacts2Add = new Vector();

		for (int i = 0; i < serverContacts.length; i++) {

			String thisKey = serverContacts[i].getLogin().getEmail();

			// If not in local contacts, add it
			if (null == ObjectStoreModel.getContact4(thisKey)) contacts2Add.addElement(serverContacts[i]);
		}

		for (int i = 0; i < contacts2Add.size(); i++) {

			User thisNewUser = (User) contacts2Add.elementAt(i);

			ObjectStoreModel.putContact(thisNewUser);

			NetMemory.setContactStatusHaveChanged(true);
			NetMemory.setContactsHaveChanged(true);
		}
	}

	// Local contacts no longer found on the server
	private static void updateContacts2(User[] serverContacts) {

		final Vector contacts2Del = new Vector();

		String[] localContactKeys = ObjectStoreModel.getContactKeys();

		// For each local contact
		for (int i = 0; i < localContactKeys.length; i++) {

			boolean found = false;
			String thisKey = localContactKeys[i];

			// See if we no longer find it in the server list
			for (int j = 0; j < serverContacts.length; j++)

				if (serverContacts[j].getLogin().getEmail().equals(thisKey)) { found = true; break; }

			// If the server list does not have our local contact, it means no
			// longer a contact, delete it from locals
			if (!found) contacts2Del.addElement(ObjectStoreModel.getContact4(thisKey));
		}

		// Delete the contacts we have locally but are not in the server anymore
		for (int i = 0; i < contacts2Del.size(); i++) {

			User thisDeadUser = (User) contacts2Del.elementAt(i);
			String thisKey = thisDeadUser.getLogin().getEmail();

			ObjectStoreModel.removeContact4(thisKey);
			NetMemory.setContactStatusHaveChanged(true);
			NetMemory.setContactsHaveChanged(true);
		}
	}

	// Update of contact details that have changed
	private static void updateContacts3(User[] serverContacts) {

		final Vector contacts2Upd = new Vector();

		// String[] localContacts = ObjectStoreModel.getContactKeys();

		for (int i = 0; i < serverContacts.length; i++) {
			String thisServerKey = serverContacts[i].getLogin().getEmail();

			User thisLocalUser = ObjectStoreModel.getContact4(thisServerKey);
			User thisServerUser = serverContacts[i];

			if (thisServerUser.isOnline() != thisLocalUser.isOnline()) { contacts2Upd.addElement(thisServerUser); NetMemory.setContactStatusHaveChanged(true); }

			if (thisServerUser.getSecondsInactive() != thisLocalUser.getSecondsInactive()) contacts2Upd.addElement(thisServerUser);

		}

		// Update

		for (int i = 0; i < contacts2Upd.size(); i++) {

			User thisServerUser = (User) contacts2Upd.elementAt(i);
			String thisKey = thisServerUser.getLogin().getEmail();
			User thisLocalUser = ObjectStoreModel.getContact4(thisKey);

			thisLocalUser.setLastSeen(thisServerUser.getLastSeen());

			thisLocalUser.getKeyPair().setPuk(thisServerUser.getKeyPair().getPuk());
			thisLocalUser.setSecondsInactive(thisServerUser.getSecondsInactive());
			NetMemory.setContactsHaveChanged(true);
		}
	}
}
