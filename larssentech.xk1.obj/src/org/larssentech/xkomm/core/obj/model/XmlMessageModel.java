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
package org.larssentech.xkomm.core.obj.model;

import org.larssentech.lib.basiclib.io.parser.XMLParser;
import org.larssentech.lib.basiclib.toolkit.DateManipulationToolkit;
import org.larssentech.xkomm.core.obj.constants.HistoryConstants;
import org.larssentech.xkomm.core.obj.objects.Message;

public class XmlMessageModel {

	private final String xmlString;

	public XmlMessageModel(String xml) {

		this.xmlString = xml;
	}

	public String getFromEmail() {

		return XMLParser.parseValueForTag(getXmlString(), HistoryConstants.XML_FROM_EMAIL);
	}

	public String getXmlString() {

		return this.xmlString;
		
	}

	public String getToEmail() {

		return XMLParser.parseValueForTag(getXmlString(), HistoryConstants.XML_TO_EMAIL);
	}

	public String getSid() {

		return XMLParser.parseValueForTag(getXmlString(), HistoryConstants.XML_SID);
	}

	public int getWay() {

		String wayS = XMLParser.parseValueForTag(getXmlString(), HistoryConstants.XML_WAY);
		return Integer.parseInt(wayS);
	}

	public String getBody() {

		return XMLParser.parseValueForTag(getXmlString(), HistoryConstants.XML_BODY);
	}

	public String getSentDate() {

		return XMLParser.parseValueForTag(getXmlString(), HistoryConstants.XML_SENT);
	}

	public static XmlMessageModel message2Xml(Message message) {

		XmlMessageModel xmlMessage1 = new XmlMessageModel("");

		if (null != message) {

			String xmlSid = XMLParser.createXMLFor(HistoryConstants.SID, message.getSid());
			String xmlBody1 = XMLParser.createXMLFor(HistoryConstants.BODY, message.getBody());
			String xmlType = XMLParser.createXMLFor(HistoryConstants.TYPE, "" + message.getType());
			String xmlSent1 = XMLParser.createXMLFor(HistoryConstants.SENT, DateManipulationToolkit.formatDate(message.getSentDate(), HistoryConstants.XML_DATE_PATTERN));
			String xmlFrom1 = XMLParser.createXMLFor(HistoryConstants.FROM_EMAIL, message.getFrom().getLogin().getEmail());
			String xmlTo = XMLParser.createXMLFor(HistoryConstants.TO_EMAIL, message.getTo().getLogin().getEmail());
			String xmlWay = XMLParser.createXMLFor(HistoryConstants.WAY, "" + message.getWay());
			String fullXMLString = XMLParser.createXMLFor(HistoryConstants.MESSAGE, xmlSid + xmlWay + xmlBody1 + xmlSent1 + xmlFrom1 + xmlType + xmlTo);

			xmlMessage1 = new XmlMessageModel(fullXMLString);
		}
		return xmlMessage1;
	}

	public static String xmlMessage2Old(XmlMessageModel xmlM) {

		String oldM = "";

		String token = xmlM.getWay() == Message.IN ? HistoryConstants.MSG_OLD_IN_TOKEN : HistoryConstants.MSG_OLD_OUT_TOKEN;
		String sentDateS = xmlM.getSentDate();
		String body = xmlM.getBody();

		oldM = token + sentDateS + HistoryConstants.DOUBLE_SPACE + body;

		return oldM;
	}

}
