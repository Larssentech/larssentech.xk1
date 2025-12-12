package org.larssentech.xkomm.ui.shared.util;

import java.util.Date;

import org.larssentech.xkomm.api.xapi.Xkomm1Api;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.ui.shared.constants.Constants4Ui;

public class UisUtil {

	public static boolean isMessage(String typedMsg) {

		return typedMsg.length() > 0 && !typedMsg.equals(Constants4Ui.DOT) && !typedMsg.equals(Constants4Ui.SPACE);
	}

	public static String requestFormatDate(Date date) {

		if (null == date) return "";
		return date.toString().substring(0, 19);
	}

	public static void pl(String message) {

		Xkomm1Api.apiPl(message);
	}

	public static Message requestAnonMessage(String s, int way) {

		Message m = new Message();

		m.setSentDate(new Date());
		m.setWay(way);
		m.setBodyBytes(s.getBytes());

		return m;
	}

}
