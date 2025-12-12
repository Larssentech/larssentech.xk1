/*
 * Copyright 2014-2024 Larssentech Developers
 * 
 * This file is part of XKomm.
 * 
 * XKomm is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any Earlier
 * version.
 * 
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * XKomm. If not, see <http://www.gnu.org/licenses/>.
 */

package org.larssentech.xkomm.core.obj.objects;

import java.util.Date;

import org.larssentech.lib.CTK.objects.KeyPair;
import org.larssentech.lib.CTK.objects.PUK;

/**
 * 
 * Class to represent an XK2 client user (and as an exception, a server
 * account). Instances of User have an instance of Login and an instance of
 * KeyPair that holds the relevant internal values.
 * 
 * @author avanz.io
 *
 */
public class User {

	public static final String ENCRYPTED = "E";
	private long secondsInactive = 0;
	public static final long THRESHOLD = 120;

	private String id;
	private Login login;
	private Date lastSeen = new Date(0);
	private KeyPair keyPair;
	private String status = User.ENCRYPTED;

	private boolean good;
	private boolean loggedIn;
	private boolean appearOffline;

	public User(String id, String email) {

		this.id = id;
		this.login = new Login(email);
	}

	public User(final String id, final String email, final Date lastSeen) {

		this.status = ENCRYPTED;
		this.id = id;
		this.login = new Login(email);
		this.lastSeen = lastSeen;
		this.keyPair = new KeyPair();
	}

	public User(final String id, final Login login, final Date lastSeen, final KeyPair keyPair) {

		this.status = ENCRYPTED;
		this.id = id;
		this.login = login;
		this.lastSeen = lastSeen;
		this.keyPair = keyPair;
	}

	/**
	 * Empty user for the mere purpose of obtaining the server's PUK which does
	 * not really need a user of any value
	 */
	public User() {

		this.id = "-1";
		this.login = new Login("");
		this.keyPair = new KeyPair();
	}

	public User(final String email, final String encPass, final PUK puk) {

		this.id = "-1";
		this.login = new Login(email);
		this.login.setEncryptedPassword(encPass);
		this.keyPair = new KeyPair();
		this.keyPair.setPuk(puk);
	}

	public String getId() {

		return this.id;
	}

	public Login getLogin() {

		return this.login;
	}

	public boolean isOnline() {

		return this.isUserOnline();
	}

	public void setId(String id) {

		this.id = id;
	}

	public boolean isLoggedIn() {

		return !this.getId().equals("-1");
	}

	public Date getLastSeen() {

		return this.lastSeen;
	}

	public void setLastSeen(Date d) {

		this.lastSeen = d;
	}

	private boolean isUserOnline() {

		Date lastSeen = this.getLastSeen();
		Date now = new Date();

		long millisLastSeen = lastSeen.getTime();

		long millisnow = now.getTime();
		long seconds = (millisnow - millisLastSeen) / 1000;

		return (seconds < User.THRESHOLD);
	}

	public boolean isTheSameAs(User u) {

		return this.getLogin().getEmail().toLowerCase().equals(u.getLogin().getEmail().toLowerCase());
	}

	public boolean isInactive() {

		return this.secondsInactive > 120;
	}

	public KeyPair getKeyPair() {

		return this.keyPair;
	}

	public String getStatus() {

		return this.status;
	}

	public void setLoggedIn(boolean b) {

		this.loggedIn = b;
	}

	public boolean amLoggedIn() {

		return this.loggedIn;
	}

	public boolean amAppearOffline() {

		return this.appearOffline;
	}

	public void setAppearOffline(boolean b) {

		this.appearOffline = b;
	}

	public long getSecondsInactive() {

		return this.secondsInactive;
	}

	public void setSecondsInactive(long secondsInactive) {

		this.secondsInactive = secondsInactive;
	}

	public boolean isGood() {

		return this.good;
	}

	public void setGood(boolean isGood) {

		this.good = isGood;
	}
}