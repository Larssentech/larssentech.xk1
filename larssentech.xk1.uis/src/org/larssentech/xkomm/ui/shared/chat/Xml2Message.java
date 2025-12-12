/*
 * Copyright 2014-2024 Larssentech Developers
 * 
 * This file is part of XKomm.
 * 
 * XKomm is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * XKomm is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General License for more details.
 * 
 * You should have received a copy of the GNU General License along with XKomm.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package org.larssentech.xkomm.ui.shared.chat;

import org.larssentech.lib.basiclib.toolkit.DTK2;
import org.larssentech.xkomm.api.xapi.Xkomm1Api;
import org.larssentech.xkomm.core.obj.constants.HistoryConstants;
import org.larssentech.xkomm.core.obj.model.XmlMessageModel;
import org.larssentech.xkomm.core.obj.objects.Message;

public class Xml2Message {

	public static Message convertXml2Message(XmlMessageModel xml) {

		Message m = new Message();

		if (null != xml) {

			String xmlSid = xml.getSid();
			String xmlBody1 = xml.getBody();
			String xmlType = "" + Message.TEXT;
			String xmlSent1 = xml.getSentDate();
			String xmlFrom1 = xml.getFromEmail();
			String xmlTo = xml.getToEmail();
			String xmlWay = "" + xml.getWay();

			m.setSid(xmlSid);
			m.setBodyBytes(xmlBody1.getBytes());
			m.setType(Integer.parseInt(xmlType));

			m.setSentDate(DTK2.getLocalDateFromLocalString(xmlSent1, HistoryConstants.XML_DATE_PATTERN));

			// If old style sent date in history (without seconds)
			if (m.getSentDate() == null)
				m.setSentDate(DTK2.getLocalDateFromLocalString(xmlSent1, HistoryConstants.XML_DATE_PATTERN_ALT));

			m.setFrom(Xkomm1Api.apiGetContact4Email(xmlFrom1));
			m.setTo(Xkomm1Api.apiGetContact4Email(xmlTo));
			m.setWay(Integer.parseInt(xmlWay));

			m.setGood(true);

		}
		return m;
	}

}
