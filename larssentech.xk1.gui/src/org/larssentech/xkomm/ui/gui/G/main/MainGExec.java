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

package org.larssentech.xkomm.ui.gui.G.main;

import java.awt.Color;

import org.larssentech.lib.awtlib.Alert;
import org.larssentech.lib.basiclib.toolkit.DateManipulationToolkit;
import org.larssentech.xkomm.ui.gui.G.chat.ChatGFrameLauncher;
import org.larssentech.xkomm.ui.gui.G.util.Alerter;
import org.larssentech.xkomm.ui.shared.chat.ChatRoomMan;
import org.larssentech.xkomm.ui.shared.functions.UisFunctions;

class MainGExec {

	/**
	 * Method to refresh the list of contacts only if needed. The criteria is
	 * simple: that the server array and the gui array do not contain the same
	 * entries, regardless of the order of appearance.
	 */

	static void doRefreshContactLizt() {

		boolean[] statuses = UisFunctions.requestGetContactStatuses();
		String[] serverContacts = UisFunctions.requestContactEmails();
		MainGFrame.getLizt().doRefreshLizt(statuses, serverContacts);

	}

	/*
	 * static void doRefreshContacts() {
	 * 
	 * String[] serverContacts = UisFunctions.requestContactEmails(); String[]
	 * guiContacts = MainGFrame.getLizt().getItems();
	 * 
	 * // If GUI is empty if (serverContacts.length > 0 && guiContacts.length ==
	 * 0) for (int i = 0; i < serverContacts.length; i++)
	 * MainGFrame.getLizt().add(serverContacts[i]);
	 * 
	 * // Only if contact arrays are different else if (serverContacts.length >
	 * 0 && CollectionManipulationToolkit.entriesDiffer4Arrays(guiContacts,
	 * serverContacts)) {
	 * 
	 * // Remove all MainGFrame.getLizt().removeAll();
	 * 
	 * // And add all for (int i = 0; i < serverContacts.length; i++)
	 * MainGFrame.getLizt().add(serverContacts[i]);
	 * 
	 * // Since the selected entry is lost, show version on status
	 * MainGFrame.getStatus().setText(Constants4G.FULL_VERSION_STRING); } }
	 */

	static void doDeleteContact() {

		String to = doGetSelectedContact();
		if (to.length() > 0) {
			if (UisFunctions.requestMyEmail().equals(to.toLowerCase())) new Alert().showMessage("You cannot delete yourself as a contact...");

			else {

				boolean b = Alerter.confirmDeleteContact();
				if (b) UisFunctions.requestDeleteContact(to);

				else new Alert().showMessage("Something went wrong and the contact was NOT deleted");
			}
		}

		else Alerter.messageNoContactSelected();
	}

	private synchronized static void doLaunch(String to) {

		if (!ChatRoomMan.isReg(to)) ChatRoomMan.reg(to, new ChatGFrameLauncher(to));
	}

	static void doRefreshStatus() {

		String to = doGetSelectedContact();

		if (to.length() > 0) {

			String s = (UisFunctions.requestOnline4(to) ? "Online ( " : "Offline ( ") + DateManipulationToolkit.formatDate(UisFunctions.requestLastSeen4Jre118(to), "yyyy.MM.dd HH:mm") + ")";

			MainGFrame.getStatus().setText(s);

			MainGFrame.getStatus().setForeground(doGetStatusColour(to));
		}
	}

	static void doExit() {

		UisFunctions.requestExit(3);
	}

	static Color doGetStatusColour(String contactString) {

		return UisFunctions.requestOnline4(contactString) ? Color.green : Color.red;
	}

	static void doCheckForMessages() {

		String[] emails = MainGFrame.getLizt().getItems();

		for (int i = 0; i < emails.length; i++) {

			if (UisFunctions.requestInboxCountFrom(emails[i]) > 0)

				doLaunch(emails[i]);
		}
	}

	public static void doLaunch() {

		String to = doGetSelectedContact();

		if (to.length() > 0) MainGExec.doLaunch(to);

	}

	private static String doGetSelectedContact() {

		return (null != MainGFrame.getLizt().getSelectedItem()) ? MainGFrame.getLizt().getSelectedItem() : "";
	}

}