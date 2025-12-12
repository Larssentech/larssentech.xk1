package org.larssentech.xkomm.core.obj.constants;

/**
 * 
 * Just some constnats for the construction of the RequestBundle, XML and other
 * stuff.
 * 
 * @author avanz.io
 *
 */
public interface LoginHeaderConstants {

	// List of HEADER_KEY entries
	String HEADER_KEY_MODE = "mode";
	String HEADER_KEY_APPEAR_HOW = "appearHow";
	String HEADER_KEY_LOGIN = "login";
	String HEADER_KEY_ENC_PASS = "encPass";
	String HEADER_KEY_PUK = "puk";
	String HEADER_KEY_SECONDS_INACTIVE = "secondsInactive";
	String HEADER_SIZE_OPENER = "<headerSize>";
	String HEADER_SIZE_CLOSER = "</headerSize>";
	String HEADER_SIZE_VALUE = "6";
	String APPEAR_HOW_OPENER = "<appearHow>";
	String APPEAR_HOW_CLOSER = "</appearHow>";
	String LOGIN_OPENER = "<login>";
	String LOGIN_CLOSER = "</login>";
	String MODE_OPENER = "<mode>";
	String MODE_CLOSER = "</mode>";
	String ENC_PASS_OPENER = "<encPass>";
	String ENC_PASS_CLOSER = "</encPass>";
	String PUK_OPENER = "<puk>";
	String PUK_CLOSER = "</puk>";
	String SECS_INACTIVE_OPENER = "<secondsInactive>";
	String SECS_INACTIVE_CLOSER = "</secondsInactive>";
	String HEADER_SIZE = "<headerSize>";
}
