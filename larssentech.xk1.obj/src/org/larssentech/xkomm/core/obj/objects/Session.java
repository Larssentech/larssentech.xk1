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
 * Class to represent an individual users client session. This object is used to
 * ensure no single user is logged in more than once with the same account.
 * 
 * @author avanz.io
 *
 */
public class Session {

	private final String sessionValue;
	private boolean sessionChanges;
	public static final String SESSION_CHANGE = "1";

	public Session() {

		this.sessionValue = "";
	}

	public Session(String string) {

		this.sessionValue = string;
	}

	public String getSesionValue() {

		return this.sessionValue;
	}

	public boolean isGood() {

		return this.getSesionValue().length() > 0;
	}

	public boolean hasSessionChanged() {

		return this.sessionChanges;
	}

	public void setSessionChanges(boolean sessionChanges) {

		this.sessionChanges = sessionChanges;
	}
}