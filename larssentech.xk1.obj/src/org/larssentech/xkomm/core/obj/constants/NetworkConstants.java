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

package org.larssentech.xkomm.core.obj.constants;

/**
 * 
 * Interface for shared values for client and server Network operations. These
 * constants are mainly the commands that the client will send to the server and
 * that the server must parse to provide a response back to the client.
 * 
 * @author avanz.io
 *
 */
public interface NetworkConstants {

	String V_APPEAR_ONLINE = "APPEAR_ONLINE";
	String V_APPEAR_OFFLINE = "APPEAR_OFFLINE";
	String N_CLOSER = "}}";
	String N_MESSAGE = "MSG={{";
	String N_RECEIVER = "TO={{";
	String N_MSG_TYPE = "TYPE={{";
	String N_MESSAGE_XUID = "XUID={{";
	String N_MSG_NUM = "NUM={{";
	String N_CONTACT = "WHO={{";
	String N_VERSION = "V={{";
	String SS_RETRIEVE_MESSAGES_4_TYPE = "XK_RETRIEVE_MESSAGES_4_TYPE";
	String SS_RETRIEVE_CONTACTS = "XK_RETRIEVE_CONTACTS2";

	// Message XML
	String SS_SEND_MESSAGE = "sendMessage";
	String XML_REQUEST_NAME_O = "<requestName>";
	String XML_REQUEST_NAME_C = "</requestName>";
	String XML_XUID_O = "<msgXuid>";
	String XML_XUID_C = "</msgXuid>";
	String XML_TO_O = "<msgTo>";
	String XML_TO_C = "</msgTo>";
	String XML_MSG_TYPE_O = "<msgType>";
	String XML_MSG_TYPE_C = "</msgType>";
	String XML_MSG_BODY_O = "<msgBody>";
	String XML_MSG_BODY_C = "</msgBody>";
	String XML_MSG_SRC_O = "<msgSrc>";
	String XML_MSG_SRC_C = "</msgSrc>";
	String XML_MSG_ORIGIN_O = "<msgOrigin>";
	String XML_MSG_ORIGIN_C = "</msgOrigin>";

	String SS_LOGIN = "XK_LOGIN2";
	String SS_DELETE_CONTACT = "XK_N_DELETE_CONTACT";
	String SS_INIT_SESSION = "XK_N_INIT_SESSION";
	String SS_RETR_SESSION = "XK_N_RETR_SESSION";
	String SS_INVITE_USER = "XK_INVITE_USER";
	String SS_SEP = "::";

	String SS_REJECT_DOWNLOAD = "XK_CANCEL_DOWNLOAD";
	String SS_CANCEL_UPLOAD = "XK_CANCEL_UPLOAD";
	String RESPONSE_END = "..";
	String RECORD_END = ".";
	String OK = "OK";
	String ENDER = "<end>";

	String BAD_ID = "-1";
	int SOME_MESSAGES = 10;

	String GET_SERVER_PUK_ERROR = "  Communicator.class -- getServerPuk() handled exception.\n";
	String EMPTY_STRING = "";
	String ERROR_IGNORED_RECEIPT = "------Server IGNORED our receipt!";
}