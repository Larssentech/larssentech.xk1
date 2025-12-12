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
package org.larssentech.xkomm.core.obj.objects;

/**
 * 
 * Class to reflect the "Login" objhect for XK2. Each user instnace has a Login
 * instance to collect just that, the authentication credentials.
 * 
 * Note that passwords are stored in memory only in their RSA encruypted mode.
 * This is in order to provide end-to-end secure password transmission across
 * the network.
 * 
 * @author avanz.io
 *
 */
public class Login {

	private String email;
	private String encryptedPassword;

	public Login(String email) {

		this.email = email.toLowerCase();
	}

	public String getEmail() {

		return this.email.toLowerCase();
	}

	public void setEmail(String login) {

		this.email = login.toLowerCase();
	}

	public String getEncryptedPassword() {

		return this.encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {

		this.encryptedPassword = encryptedPassword;
	}

}