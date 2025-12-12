/*
 * Copyright 1999-2023 Larssentech Developers
 *
 * This file is part of the Larssentech BasicLib2 project.
 *
 * The Larssentech BasicLib2 Library is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the source code. If not, see <http://www.gnu.org/licenses/>.
 */

package org.larssentech.xkomm.core.obj.objects;

import org.larssentech.lib.basiclib.settings.SettingsExtractor;
import org.larssentech.lib.basiclib.settings.SettingsUpdater;
import org.larssentech.lib.basiclib.util.LoginChecker;

public class AccountPack {

	protected String login = "Not Set";
	protected String plainPass = "Not Set";

	/**
	 * Constructor for cases where the user does not save his credentials on
	 * file
	 * 
	 * @param email
	 * @param plainPass
	 */
	public AccountPack(String email, String plainPass) {

		this.login = email;
		this.plainPass = plainPass;
	}

	/**
	 * Constructor for when load will be invoked after instantiation
	 * 
	 * @return
	 */
	public AccountPack() {

	}

	public AccountPack load(String accountFile) {

		this.login = SettingsExtractor.extractThis4(accountFile, "XKomm.login");
		this.plainPass = SettingsExtractor.extractThis4(accountFile, "XKomm.pass");

		return this;
	}

	public String getLogin() {

		return this.login;
	}

	public String getPlainPass() {

		return this.plainPass;
	}

	public void persist(String accountFile) {

		SettingsUpdater.updateLine(accountFile, "XKomm.login", this.login);
		SettingsUpdater.updateLine(accountFile, "XKomm.pass", this.plainPass);

	}

	public void update(String login, String pass, boolean rememberMe, boolean autoLogin) {

		this.login = login;
		this.plainPass = pass;
	}

	public boolean isGood() {

		boolean isGood = LoginChecker.validateEmail(this.login) && LoginChecker.validatePassword(this.plainPass);

		return isGood;
	}

	public boolean loginIsGood() {

		boolean isGood = LoginChecker.validateEmail(this.login);

		return isGood;
	}

	public boolean passIsGood() {

		return LoginChecker.validateHardPassword(this.plainPass);
	}

	public void setLogin(String string) {

		this.login = string;

	}

	public void setPlainPass(String plainPass) { this.plainPass = plainPass; }
}