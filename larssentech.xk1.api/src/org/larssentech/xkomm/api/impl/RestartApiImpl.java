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

import java.io.File;

import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.core.obj.util.Logger;

public class RestartApiImpl {

	public RestartApiImpl(boolean exit) {

		try {

			Logger.log(Constants4API.I_AM_THE_FIRESTARTER);
			Logger.log(Constants4API.TWISTED_FIRESTARTER);

			if (new File(Constants4API.XKOMM_JAR).exists()) Runtime.getRuntime().exec(new String[] { Constants4API.JAVA, Constants4API.JAR, Constants4API.XKOMM_JAR });

			if (new File(Constants4API.XKOMM_JM_JAR).exists()) Runtime.getRuntime().exec(new String[] { Constants4API.JAVA, Constants4API.JAR, Constants4API.XKOMM_JM_JAR });

			if (new File(Constants4API.XKOMM_K_JAR).exists()) Runtime.getRuntime().exec(new String[] { Constants4API.JAVA, Constants4API.JAR, Constants4API.XKOMM_K_JAR });

			if (exit) System.exit(0);

		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}

}
