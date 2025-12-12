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

import java.util.Hashtable;
import java.util.Vector;

/**
 * 
 * Class that holds the block or package of information the client sends the
 * server to perform authentication. These are in XML for transmission, but
 * there is also fields for storing the actual values by the server, once the
 * header is parsed.
 * 
 * @author avanz.io
 *
 */
public class RequestBundle {

	private final Hashtable headerAttributes = new Hashtable();

	private String appearHow = "";
	private String email = "";
	private String plainPass = "";
	private String mode = "";
	private String encPass = "";
	private String puk = "";
	private boolean isGood = false;
	private String secondsInactive;
	private String userId;
	private boolean ok = false;
	private String request = "";
	private String xmlHeaderSize;
	private String xmlLogin;
	private String xmlMode;
	private String xmlEncryptedPassword;
	private String xmlPuk;
	private String xmlInactive;
	private String xmlAppearHow;

	private Vector headerList = new Vector();

	private String dbPlainPass;

	private String dbEncPass;

	public void setXmlAppearHow(String string) {

		this.xmlAppearHow = string;
	}

	public void setXmlInactive(String string) {

		this.xmlInactive = string;
	}

	public void setXmlPuk(String string) {

		this.xmlPuk = string;
	}

	public void setXmlEncryptedPassword(String string) {

		this.xmlEncryptedPassword = string;
	}

	public void setXmlMode(String string) {

		this.xmlMode = string;
	}

	public void setXmlEmail(String string) {

		this.xmlLogin = string;
	}

	public void setXmlLoginHeaderSize(String string) {

		this.xmlHeaderSize = string;
	}

	public void setSecondsInactive(String readLine) {

		this.secondsInactive = readLine;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public void setAppearHow(String readLine) {

		this.appearHow = readLine;
	}

	public void setEmail(String readLine) {

		this.email = readLine;
	}

	public void setPlainPass(String readLine) {

		this.plainPass = readLine;
	}

	public void setMode(String readLine) {

		this.mode = readLine;
	}

	public void setEncPass(String readLine) {

		this.encPass = readLine;
	}

	public void setPuk(String puk) {

		this.puk = puk;
	}

	public void setGood(boolean b) {

		this.isGood = b;
	}

	public String getUserId() {

		return this.userId;
	}

	public String getAppearHow() {

		return this.appearHow;
	}

	public String getEmail() {

		return this.email;
	}

	public String getPlainPass() {

		return this.plainPass;
	}

	public String getMode() {

		return this.mode;
	}

	public String getEncPass() {

		return this.encPass;
	}

	public String getPuk() {

		return this.puk;
	}

	public boolean isGood() {

		return this.isGood;
	}

	public boolean hasPuk() {

		return this.puk.length() > 10;
	}

	public String secondsInactive() {

		return this.secondsInactive;
	}

	public void setOK() {

		this.ok = true;
	}

	public void setOK(boolean b) {

		this.ok = b;
	}

	public boolean isOK() {

		return this.ok;
	}

	public void setRequest(String request) {

		this.request = request;
	}

	public String getRequest() {

		return this.request;
	}

	public Hashtable getHeaderAttributes() {

		return this.headerAttributes;
	}

	public Vector getHeaderList() {

		return this.headerList;
	}

	public String getXmlHeaderSize() {

		return this.xmlHeaderSize;
	}

	public String getXmlLogin() {

		return this.xmlLogin;
	}

	public String getXmlMode() {

		return this.xmlMode;
	}

	public String getXmlEncryptedPassword() {

		return this.xmlEncryptedPassword;
	}

	public String getXmlPuk() {

		return this.xmlPuk;
	}

	public String getXmlInactive() {

		return this.xmlInactive;
	}

	public String getXmlAppearHow() {

		return this.xmlAppearHow;
	}

	public void setHeaderList(Vector headerList) {

		this.headerList = headerList;
	}

	public void setDBPlainPass(String string) {

		this.dbPlainPass = string;

	}

	public String getDbEncPass() {

		return this.dbEncPass;
	}

	public boolean isComplete() {

		return this.email.length() > 0 && this.encPass.length() > 0;
	}

	public Object getDbPlainPass() {

		return this.dbPlainPass;
	}

	public void setDBEncPassword(String pass) {

		this.dbEncPass = pass;

	}

}