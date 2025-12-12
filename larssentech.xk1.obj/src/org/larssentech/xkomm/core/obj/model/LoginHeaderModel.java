package org.larssentech.xkomm.core.obj.model;

import org.larssentech.xkomm.core.obj.constants.LoginHeaderConstants;
import org.larssentech.xkomm.core.obj.constants.NetworkConstants;
import org.larssentech.xkomm.core.obj.objects.RequestBundle;
import org.larssentech.xkomm.core.obj.objects.User;

/**
 * 
 * Class to create and return an instance of the RequestBundle class.
 * loginHeader is a packaged object that will be sent to the server as a login
 * block with some "contract" attributes for the server to parse and, among
 * other things, ensure the encrypted and signed password matches the sender and
 * also decrypts to the corect value, for authentication.
 * 
 * @author avanz.io
 *
 */
public class LoginHeaderModel implements LoginHeaderConstants, NetworkConstants {

	public static RequestBundle createLoginHeader(User me) {

		RequestBundle requestBundle = new RequestBundle();

		String hSize = HEADER_SIZE_OPENER + HEADER_SIZE_VALUE + HEADER_SIZE_CLOSER;
		String hAppe = APPEAR_HOW_OPENER + (me.amAppearOffline() ? V_APPEAR_OFFLINE : V_APPEAR_ONLINE) + APPEAR_HOW_CLOSER;
		String hEmai = LOGIN_OPENER + me.getLogin().getEmail() + LOGIN_CLOSER;
		String hMode = MODE_OPENER + me.getStatus() + MODE_CLOSER;
		String hPass = ENC_PASS_OPENER + me.getLogin().getEncryptedPassword() + ENC_PASS_CLOSER;
		String hPukk = PUK_OPENER + me.getKeyPair().getPuk().getStringValue() + PUK_CLOSER;
		String hSecs = SECS_INACTIVE_OPENER + me.getSecondsInactive() + SECS_INACTIVE_CLOSER;

		// loginHeader is effectively a "Login Block"
		requestBundle.setXmlLoginHeaderSize(hSize);
		requestBundle.setXmlAppearHow(hAppe);
		requestBundle.setXmlEmail(hEmai);
		requestBundle.setXmlMode(hMode);
		requestBundle.setXmlEncryptedPassword(hPass);
		requestBundle.setXmlPuk(hPukk);
		requestBundle.setXmlInactive(hSecs);

		return populateHeader(requestBundle);
	}

	public static RequestBundle populateHeader(RequestBundle requestBundle) {

		requestBundle.getHeaderList().addElement(requestBundle.getXmlHeaderSize());
		requestBundle.getHeaderList().addElement(requestBundle.getXmlAppearHow());
		requestBundle.getHeaderList().addElement(requestBundle.getXmlInactive());
		requestBundle.getHeaderList().addElement(requestBundle.getXmlLogin());
		requestBundle.getHeaderList().addElement(requestBundle.getXmlMode());
		requestBundle.getHeaderList().addElement(requestBundle.getXmlEncryptedPassword());
		requestBundle.getHeaderList().addElement(requestBundle.getXmlPuk());

		return requestBundle;
	}

	public static void populate(RequestBundle requestBundle) {

		if (requestBundle.getHeaderAttributes().containsKey(HEADER_KEY_APPEAR_HOW)) requestBundle.setAppearHow(requestBundle.getHeaderAttributes().get(HEADER_KEY_APPEAR_HOW).toString());
		else requestBundle.setAppearHow(V_APPEAR_ONLINE);

		if (requestBundle.getHeaderAttributes().containsKey(HEADER_KEY_SECONDS_INACTIVE))
			requestBundle.setSecondsInactive(requestBundle.getHeaderAttributes().get(HEADER_KEY_SECONDS_INACTIVE).toString());
		else requestBundle.setSecondsInactive("0");

		requestBundle.setEmail(requestBundle.getHeaderAttributes().get(HEADER_KEY_LOGIN).toString());
		requestBundle.setEncPass(requestBundle.getHeaderAttributes().get(HEADER_KEY_ENC_PASS).toString());

		requestBundle.setPuk(requestBundle.getHeaderAttributes().get(HEADER_KEY_PUK).toString());
		requestBundle.setGood(true);

		requestBundle.setOK();
	}
}