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
package org.larssentech.xkomm.api.impl;

import org.larssentech.lib.CTK.objects.PUK;
import org.larssentech.lib.log.Logg3r;
import org.larssentech.xkomm.api.xapi.Xkomm1Api;
import org.larssentech.xkomm.core.hub.req.Hub;
import org.larssentech.xkomm.core.obj.objects.AccountPack;
import org.larssentech.xkomm.core.obj.objects.User;

public class Impl4Init {

	protected static boolean networkTestOK;
	protected static boolean loginOK;
	protected static boolean startXK2OK;

	public static boolean startXK2() {

		if (Impl4Init.loginOK) {

			Impl4Init.startXK2OK = Hub.hubStartXkomm();

			return Impl4Init.startXK2OK;
		}

		else {

			Xkomm1Api.apiPl("XAPI failed to start cannot continue.");
			return false;
		}
	}

	public static boolean testNetwork(boolean print, boolean exit) {

		Impl4Init.networkTestOK = Hub.hubTestNetwork(print, exit);

		return Impl4Init.networkTestOK;
	}

	public static boolean initialLogin(AccountPack pack) {

		// Read our PUK and encrypt our password and create the "me" user
		User me = new User(pack.getLogin(), pack.getPlainPass(), new PUK(""));

		// Set "me"
		Hub.hubSetMe(me);

		// and try to log in and start sh*t, and store result
		Impl4Init.loginOK = Hub.hubInitialLogin();

		// Return success or otherwise
		return loginOK;
	}

	public static void setPauseMonitors(boolean b) {

		Hub.hubPauseMonitor(b);
	}

	public static void setLoggingEnabled(boolean enabled) {

		Logg3r.VERBOSE = enabled;
	}
}
