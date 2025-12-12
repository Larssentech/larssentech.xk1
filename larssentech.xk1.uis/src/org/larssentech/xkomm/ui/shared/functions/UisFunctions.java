package org.larssentech.xkomm.ui.shared.functions;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.larssentech.lib.CTK.objects.PUK;
import org.larssentech.lib.awtlib.Alert;
import org.larssentech.lib.awtlib.DisplayTool2;
import org.larssentech.lib.basiclib.util.LoginChecker;
import org.larssentech.xkomm.api.xapi.Xkomm1Api;
import org.larssentech.xkomm.core.obj.model.XmlMessageModel;
import org.larssentech.xkomm.core.obj.objects.AccountPack;
import org.larssentech.xkomm.core.obj.objects.Message;
import org.larssentech.xkomm.ui.shared.chat.ChatRoomMan;
import org.larssentech.xkomm.ui.shared.constants.Constants4Ui;
import org.larssentech.xkomm.ui.shared.contact.BasicGui4Contact;
import org.larssentech.xkomm.ui.shared.contact.BasicGui4Login;
import org.larssentech.xkomm.ui.shared.util.Alerter;
import org.larssentech.xkomm.ui.shared.util.UisUtil;

public class UisFunctions {

	public static int requestInboxCountFrom(String string) {

		return Xkomm1Api.apiGetInboxCountFrom(string);
	}

	public static XmlMessageModel[] loadHistory(String to, int lines) {

		return Xkomm1Api.apiGetHistory(to, lines);
	}

	public static void requestCreateContact() {

		AccountPack p = new AccountPack();

		new BasicGui4Contact(p, Constants4Ui.LOGIN_COLS).makePretty(300, 150);
		// Check login is correct...
		if (LoginChecker.isGood(p.getLogin())) if (new Alert().showConfirm(Constants4Ui.ALERT_ADD_CONT_1 + p.getLogin() + Constants4Ui.ALERT_ADD_CONT_2)) {

			UisFunctions.requestInviteUser(p.getLogin());
			new Alert().showMessage(Constants4Ui.TITLE_J, Constants4Ui.ALERT_INVITE_SENT);
		}
	}

	public static boolean requestIsContact(String to) {

		return Xkomm1Api.apiIsContact(to);
	}

	public static boolean requestSendTyping(String to) {

		return Xkomm1Api.apiSendTyping(to);
	}

	public static boolean requestDeleteContact(String contactString) {

		if (LoginChecker.isGood(contactString))

			return Xkomm1Api.apiDeleteContact(contactString);

		return false;
	}

	private static void requestInviteUser(String contactString) {

		Xkomm1Api.apiInviteUser(contactString);
	}

	public static Date requestLastSeen4(String contactString) {

		return Xkomm1Api.apiGetContact4Email(contactString).getLastSeen();
	}

	public static Date requestLastSeen4Jre118(String contactString) {

		Date lastSeen = Xkomm1Api.apiGetContact4Email(contactString).getLastSeen();

		Calendar cal = Calendar.getInstance();
		// remove next line if you're always using the current time.
		cal.setTime(lastSeen);
		// cal.add(Calendar.HOUR, +1);
		Date oneHourBack = cal.getTime();
		return oneHourBack;
	}

	public static String requestMyEmail() {

		return Xkomm1Api.apiGetMe().getLogin().getEmail();
	}

	public static PUK requestPuk4(String to) {

		return Xkomm1Api.apiGetContact4Email(to).getKeyPair().getPuk();
	}

	public static String[] requestContactEmails() {

		return Xkomm1Api.apiGetContactEmails();
	}

	public static boolean requestOnline4(String contactString) {

		return Xkomm1Api.apiIsOnline(contactString);
	}

	public static void requestLoadSavedChatFrame(String to, Frame frame) {

		frame.setLocation(UisFunctions.requestChatRoomLocation(to));
		frame.setSize(UisFunctions.requestChatRoomDimension(to));

	}

	public static void requestRecycleFrame(String key, Frame frame) {

		ChatRoomMan.recycle(frame, key);

	}

	private static Dimension requestChatRoomDimension(final String to) {

		if (!new File(Constants4Ui.DISPLAY_FOLDER).exists()) new File(Constants4Ui.DISPLAY_FOLDER).mkdir();

		Dimension size = DisplayTool2.readDimension(Constants4Ui.DISPLAY_FOLDER + Constants4Ui.SEP + to);
		if (null == size || size.width == 0) return new Dimension(Constants4Ui.DEFAULT_WIDTH, Constants4Ui.DEFAULT_HEIGHT);

		return size;
	}

	private static Point requestChatRoomLocation(String to) {

		if (!new File(Constants4Ui.DISPLAY_FOLDER).exists()) new File(Constants4Ui.DISPLAY_FOLDER).mkdir();

		Point location = DisplayTool2.readLocation(Constants4Ui.DISPLAY_FOLDER + Constants4Ui.SEP + to);
		return (null == location) ? new Point(Constants4Ui.DEFAULT_X_LOC, Constants4Ui.DEFAULT_Y_LOC) : location;
	}

	public static void requestSaveChatFrame(Frame owner, String key) {

		DisplayTool2.saveLocation(Constants4Ui.DISPLAY_FOLDER + Constants4Ui.SEP + key, owner.getLocation());
		DisplayTool2.saveDimension(Constants4Ui.DISPLAY_FOLDER + Constants4Ui.SEP + key, owner.getSize());
	}

	public static void requestLoadSavedMainFrame(Frame frame) {

		UisFunctions.requestLoadMainFrameLocation(frame);
		UisFunctions.requestSetMainFrameSize(frame);

	}

	private static void requestLoadMainFrameLocation(Frame frame) {

		Point location = DisplayTool2.readLocation(Constants4Ui.MAIN_FRAME_DISPLAY);
		frame.setLocation(location);

	}

	public static void requestSaveMainFrame(Frame frame) {

		DisplayTool2.saveDimension(Constants4Ui.MAIN_FRAME_DISPLAY, frame.getSize());
		DisplayTool2.saveLocation(Constants4Ui.MAIN_FRAME_DISPLAY, frame.getLocation());
	}

	private static void requestSetMainFrameSize(Frame frame) {

		Dimension dim = DisplayTool2.readDimension(Constants4Ui.MAIN_FRAME_DISPLAY);

		if (dim.width == 0 && dim.height == 0) frame.setSize(Constants4Ui.DEFAULT_WIDTH, Constants4Ui.DEFAULT_HEIGHT);

		else frame.setSize(dim);
	}

	/* Send */
	public static Message requestSendTextMessage(String email, String msg) {

		if (Xkomm1Api.apiHaveNetwork()) if (UisUtil.isMessage(msg)) {

			// The message to send, which will be updated downstream (encrypted)
			// so we need...
			Message message = Xkomm1Api.apiGenerateMessage(email, Message.TEXT, msg.getBytes());

			// So we need a backup one that is unencrypted and can display on
			// screen
			Message messagePlain = Xkomm1Api.apiGenerateMessage(email, Message.TEXT, msg.getBytes());

			if (Xkomm1Api.apiSendMessage(message, Message.SAVE_HISTORY_YES)) return messagePlain;
		}

		return new Message();
	}

	/* Receive */
	public static Message requestReceiveTextMessage(String to) {

		return Xkomm1Api.apiGetNextTextMessage(to, true);
	}

	public static Message requestdReceiveSys(String to) {

		return Xkomm1Api.apiGetNextSysMessage(to);
	}

	public static void requestDeleteChat(String contactString) {

		Xkomm1Api.apiDeleteHistory(contactString);
	}

	public static void requestExit(int i) {

		Xkomm1Api.apiExit(3);
	}

	public static void requestFirestarter() {

		new Alert().showMessage(Constants4Ui.FIRESTARTER, Constants4Ui.I_AM_THE_FIRESTARTER);
	}

	public static boolean requestLogin() {

		// Account pack for the login dialog
		AccountPack pack = new AccountPack();
		pack.load(Constants4Ui.ACCOUNT_FILE);

		// The login dialoog
		new BasicGui4Login(pack, Constants4Ui.LOGIN_COLS).makePretty(320, 200);

		// If details are not quite right will exit
		if (!pack.isGood()) new Alerter().badAccountPack(true);

		// Try logging in
		if (!Xkomm1Api.apiInitialLogin(pack)) new Alerter().badLogin(true);

		// Save credentials only after a good login
		pack.persist(Constants4Ui.ACCOUNT_FILE);

		// Start XK2
		if (!Xkomm1Api.apiStartXK2()) new Alerter().badStart(true);

		return true;
	}

	public static boolean[] requestGetContactStatuses() {

		return Xkomm1Api.apiGetContactStatuses();
		
	}

	public static boolean requestHaveContactsChanged() {

		return Xkomm1Api.apiGetContactsHaveChanged();
	}

	public static boolean requestHaveContactStatusChanged() {

		return Xkomm1Api.apiGetContactStatusHaveChanged();
	}

}
