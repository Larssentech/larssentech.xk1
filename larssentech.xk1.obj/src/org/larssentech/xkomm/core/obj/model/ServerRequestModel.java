package org.larssentech.xkomm.core.obj.model;

import org.larssentech.xkomm.core.obj.constants.NetworkConstants;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.core.obj.objects.User;
import org.larssentech.xkomm.core.obj.version.Version4Xk1;

public class ServerRequestModel implements NetworkConstants {

	public static String retrieveMessages(int howMany, int type) {

		return SS_RETRIEVE_MESSAGES_4_TYPE + " " + NetworkConstants.N_MSG_NUM + howMany + NetworkConstants.N_CLOSER + " " + NetworkConstants.N_MSG_TYPE + type + NetworkConstants.N_CLOSER;
	}

	public static String deleteContact(User contact) {

		return SS_DELETE_CONTACT + " " + N_CONTACT + contact.getId() + N_CLOSER;
	}

	public static String retrieveContacts() {

		return SS_RETRIEVE_CONTACTS;
	}

	public static String initSession(String id) {

		return SS_INIT_SESSION + " " + N_CONTACT + id + N_CLOSER + N_VERSION + Version4Xk1.BASE_VERSION_STRING + N_CLOSER;
	}

	public static String retrieveSession(String id) {

		return SS_RETR_SESSION + " " + N_CONTACT + id + N_CLOSER + N_VERSION + Version4Xk1.BASE_VERSION_STRING + N_CLOSER;
	}

	public static String outgoingMessageXml(Message message) {

		String xml =

				XML_REQUEST_NAME_O + SS_SEND_MESSAGE + XML_REQUEST_NAME_C

						+ XML_XUID_O + message.getUid() + XML_XUID_C

						+ XML_TO_O + message.getTo().getId() + XML_TO_C

						+ XML_MSG_TYPE_O + message.getType() + XML_MSG_TYPE_C

						+ XML_MSG_SRC_O + message.getSource() + XML_MSG_SRC_C

						+ XML_MSG_ORIGIN_O + message.getOrigin() + XML_MSG_ORIGIN_C

						+ XML_MSG_BODY_O + new String(message.getBodyBytes()) + XML_MSG_BODY_C;

		return xml;
	}

	public static String inviteUser(String otherUserEmail) {

		return SS_INVITE_USER + " " + N_RECEIVER + otherUserEmail + N_CLOSER;
	}
}