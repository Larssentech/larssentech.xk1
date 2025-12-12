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

import org.larssentech.lib.basiclib.io.text.ReadFromFile;
import org.larssentech.lib.basiclib.io.text.SaveToFile;
import org.larssentech.lib.basiclib.toolkit.StringManipulationToolkit;
import org.larssentech.xkomm.core.obj.constants.Constants4API;
import org.larssentech.xkomm.core.obj.model.XmlMessageModel;
import org.larssentech.xkomm.core.obj.objects.Message;

public class Impl4History {

	public static void saveReceived2History(Message message) {

		if (message.isDelivered()) Impl4History.saveToHistory(XmlMessageModel.message2Xml(message));
	}

	public static void saveSent2History(Message message) {

		Impl4History.saveToHistory(XmlMessageModel.message2Xml(message));
	}

	public static void deleteHistory(String to) {

		new File(Constants4API.HISTORY_FOLDER_NU + to).delete();
	}

	private static void saveToHistory(XmlMessageModel xmlM) {

		if (!new File(Constants4API.HISTORY_FOLDER_NU).exists()) new File(Constants4API.HISTORY_FOLDER_NU).mkdir();

		SaveToFile.saveToFile(
				Constants4API.HISTORY_FOLDER_NU + Constants4API.SEP
						+ (xmlM.getWay() == Message.IN ? xmlM.getFromEmail() : xmlM.getToEmail()),

				StringManipulationToolkit.replaceAll(xmlM.getXmlString(), "\n", "//n"), true);
	}

	public static XmlMessageModel[] getHistory(String path, String from, int lines) {

		String[] historyRaw = ReadFromFile.readFromFile2(path + Constants4API.SEP + from);
		String[] historyShort = historyRaw.length > lines ? new String[lines] : new String[historyRaw.length];

		XmlMessageModel[] xmlHistoryShort = new XmlMessageModel[historyShort.length];

		int gap = historyRaw.length - historyShort.length;

		for (int i = 0; i < historyShort.length; i++) {

			if (historyRaw[i + gap].startsWith("<message>") && historyRaw[i + gap].endsWith("</message>")) {
				historyShort[i] = StringManipulationToolkit.HTMLDecodeString(historyRaw[i + gap]);
				historyShort[i] = StringManipulationToolkit.replaceAll(historyShort[i], "//n", "\n");
				xmlHistoryShort[i] = new XmlMessageModel(historyShort[i]);
			}
		}

		return xmlHistoryShort;
	}
}
