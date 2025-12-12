/*
 * Copyright 2014-2022 Larssentech Developers
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
package org.larssentech.xkomm.ui.shared.main;

import java.awt.List;

import org.larssentech.xkomm.ui.shared.functions.UisFunctions;

/**
 * Class to implement a refined version of java.awt.List The update of the
 * strings inside the List is taken care of in a smoother manner than just
 * clearing and reloading. This class is really meant to be reusable for any GUI
 * implementation where automatic update of a status is required, which makes
 * sense for a list of contacts that may be online or offline.
 * 
 * If anything other than string characters is required (e.g.: colour of an
 * individual string), then javax.swing.JList is a better choice. Swing provides
 * 'cute' results at the cost of complexity.
 * 
 * @author avanz.io
 *
 */
public class BasicContactLizt extends List {

	private static final long serialVersionUID = 1L;
	private static final int ON_AFTER = 1;
	private static final int ON_BEFORE = 0;

	private final int tokenPosition = ON_BEFORE;
	private final String onToken = "+";

	public BasicContactLizt(int i, boolean b) {

		super(i, b);
	}

	public String getSelectedItem() { // @Override

		int i = this.getSelectedIndex();

		if (i != -1) { String rawItem = this.getItems()[i]; return this.getCleanItem(rawItem); }
		return "";
	}

	/**
	 * Method to update the Contact list. This method will first store what
	 * (index for the) Contact is selected before the update. There is no
	 * reshuffling or clearing of the list, which would be confusing and
	 * unpleasant to the user. Therefore, the method updates contacts directly
	 * in the list and not using a copy of the array.
	 * 
	 * - First it will compare the local list to the server list and remove the
	 * local ones that no longer appear in the server list (because the contact
	 * has been deleted).
	 * 
	 * - Then it checks for any new Contacts availabe in the server but not yet
	 * found locally and add them.
	 * 
	 * - Then, it updates the 'mark' for the statuses
	 * 
	 * - Finally, it will select the same one that was selected before the
	 * updates when possible.
	 */
	public void doRefreshLizt(boolean[] statuses, String[] serverContactList) {

		if (UisFunctions.requestHaveContactStatusChanged()) {

			/* Remember who's selected to select him at the end */
			int currentSelection = this.getSelectedIndex();

			/* Remove contacts that no longer are found in server */
			for (int i = 0; i < this.getItems().length; i++) if (!findLiztItemClean(serverContactList, this.getItems()[i])) this.remove(this.getItems()[i]);

			/* Add contacts that are in server but not in list */
			for (int i = 0; i < serverContactList.length; i++) if (!findLiztItemClean(this.getItems(), serverContactList[i])) {

				super.add(mark(statuses[i], serverContactList[i]));

				try {
					Thread.sleep(500);
				}
				catch (InterruptedException ignored) {

				}
			}

			/* In any case, just re-check online/offline */
			// this.updateLiztItemsStatux(statuses);

			/* Set selected index again to provide stability to display */
			this.updateLiztItemSelect(currentSelection);
		}
	}

	/**
	 * Method to return whether a specific 'clean' Contact is found in the an
	 * array. This allows to compare each local contact against the full ser ver
	 * array or a server contact against the local array.
	 * 
	 * @param Contact array
	 * @param contact to find
	 * @return true/false that the array has a reference to the specific Contact
	 */
	private static boolean findLiztItemClean(String[] anyContactArray, String contact2Find) {

		for (int j = 0; j < anyContactArray.length; j++) if (anyContactArray[j].equals(contact2Find)) return true;

		return false;
	}

	public void add(String s) { // @Override

		String[] items = this.getItems(); // Clean

		boolean found = false;
		for (int i = 0; i < items.length; i++) if (items[i].equals(s)) { found = true; break; }

		if (!found) super.add(s);

	}

	/**
	 * Method to reselect the contact that was selected before all the update
	 * operations, so that the list does not end up selecting a different one
	 * than the user had in mind. This method takes care of the list having
	 * shrunk, so the one before the one that was deleted gets selected.
	 * 
	 * @param sel0
	 */
	private void updateLiztItemSelect(int sel0) {

		int sel = sel0;

		if (this.getItems().length <= sel) sel--;

		this.select(sel);
	}

	/**
	 * Method to retrieve the 'clean' Contact string from the list representing
	 * Contacts
	 * 
	 * @param email
	 * @return always the clean string for the Contact
	 */
	private String getCleanItem(String email) {

		String cleanEmail = email;

		if (BasicContactLizt.this.tokenPosition == ON_AFTER)
			cleanEmail = email.endsWith(BasicContactLizt.this.onToken) ? email.substring(0, email.length() - BasicContactLizt.this.onToken.length()) : email;
		if (BasicContactLizt.this.tokenPosition == ON_BEFORE)
			cleanEmail = email.startsWith(BasicContactLizt.this.onToken) ? email.substring(BasicContactLizt.this.onToken.length(), email.length()) : email;

		return cleanEmail;
	}

	/**
	 * Method to mark a list element based on whether the underlying Contact is
	 * online or offline
	 * 
	 * @param email
	 * @return the correctly marked string for the Contact
	 */
	private String mark(boolean online, String email) {

		String marked = online ? (BasicContactLizt.this.tokenPosition == ON_AFTER) ? email + BasicContactLizt.this.onToken : BasicContactLizt.this.onToken + email : email;

		return marked;
	}

	public String[] getItems() { // @Override

		String[] cleanItems = super.getItems();

		for (int i = 0; i < cleanItems.length; i++) cleanItems[i] = getCleanItem(cleanItems[i]);

		return cleanItems;
	}

	public void removeAll() {

	} // @Override
}

// <-- 100>