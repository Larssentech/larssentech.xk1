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

package org.larssentech.xkomm.api.util;

import java.io.File;
import java.io.FileOutputStream;

import org.larssentech.xkomm.core.obj.constants.Constants4API;

public class Config implements Reg {

	public static void setConfiguration() {

		try {

			if (!new File(Constants4API.USER_HOME).exists()) new File(Constants4API.USER_HOME).mkdir();
			if (!new File(Constants4API.HISTORY_FOLDER_NU).exists()) new File(Constants4API.HISTORY_FOLDER_NU).mkdir();

			if (!new File(Reg.USER_FILE_PATH).exists()) {

				String data = "";
				FileOutputStream out = new FileOutputStream(new File(Reg.USER_FILE_PATH));
				out.write(data.getBytes());
				out.close();
			}

		}
		catch (Exception e) {

			e.printStackTrace();
		}
	}
}