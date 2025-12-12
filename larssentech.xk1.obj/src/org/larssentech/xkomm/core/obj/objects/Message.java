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

import java.util.Date;

/**
 * 
 * Class to represent the generic messages sent and received by XK2. Instances
 * of Message have fields for sender, receiver (instances of User), sent date,
 * type, etc. and the actual payload as a byte array.
 * 
 * @author avanz.io
 *
 */
public class Message {

	public static final int TEXT = 0;
	public static final int DATA = 1;
	public static final int SYS = 2;
	public static final int DIRECT = 0;
	public static final int FWD = 1;
	public static final int RELAY = 2;

	private int way = 0;

	private int origin = 0;

	public static final boolean SAVE_HISTORY_YES = true;
	public static final boolean SAVE_HISTORY_NO = false;
	public static final int IN = 0;
	public static final int OUT = 1;

	private byte[] bodyBytes; // So we can load pure data for streaming
	private boolean delivered;
	private Date sentDate;
	private int type;
	private User from;
	private User to;
	private User forwardTo = new User();
	private User forwardFrom = new User();
	private String sid; // Server ID
	private String uid; // User ID

	public static final String XK2 = "XK2";
	public static final String IMSG = "iMessage";

	private String source = "XK2";
	private boolean good;
	private boolean locked;
	private boolean sent;

	// Constructor for empty Messages with empty Users to avoid "null"
	public Message() {

		this.setSid("0");
		this.setFrom(new User());
		this.setTo(new User());
		this.setForwardTo(new User());
		this.setBodyBytes(new byte[0]);
		this.setDelivered(false);
		this.setType(TEXT);
	}

	public Message(final String sid, final User from, final User to, final int type, byte[] bodyBytes) {

		this.setSid(sid);
		this.setFrom(from);
		this.setTo(to);
		this.setBodyBytes(bodyBytes);
		this.setDelivered(false);
		this.setType(type);
	}

	public boolean isFrom(User user) {

		return this.getFrom().isTheSameAs(user);
	}

	public String toString() {

		return this.getUid();
	}

	public String getBody() {

		return this.bodyBytes.length > 0 ? new String(this.bodyBytes) : "";
	}

	public User getFrom() {

		return this.from;
	}

	public Date getSentDate() {

		return this.sentDate;
	}

	public User getTo() {

		return this.to;
	}

	public String getSid() {

		return this.sid;
	}

	public boolean isDelivered() {

		return this.delivered;
	}

	public int getType() {

		return this.type;
	}

	public String getUid() {

		return this.uid;
	}

	public byte[] getBodyBytes() {

		return this.bodyBytes;
	}

	public void setSentDate(Date sent) {

		this.sentDate = sent;
	}

	public void setDelivered(boolean b) {

		this.delivered = b;
	}

	public void setBodyBytes(byte[] bodyBytes) {

		this.bodyBytes = bodyBytes;
	}

	public void setUid(String uid) {

		this.uid = uid;
	}

	public void setFrom(User from) {

		this.from = from;
	}

	public void setTo(User to) {

		this.to = to;
	}

	public void setType(int type) {

		this.type = type;
	}

	public void setSid(String sid) {

		this.sid = sid;
	}

	public void setGood(boolean b) {

		this.good = b;
	}

	public boolean isGood() {

		return this.good;
	}

	public void setLocked(boolean b) {

		this.locked = b;
	}

	public boolean isLocked() {

		return this.locked;
	}

	public int getWay() {

		return this.way;
	}

	public void setWay(int way) {

		this.way = way;
	}

	public String print() {

		return "From: " + this.getFrom().getLogin().getEmail()

				+ "\n" + "To: " + this.getTo().getLogin().getEmail()

				+ "\n" + "Original From: " + this.getForwardFrom().getLogin().getEmail()

				+ "\n" + "Original To: " + this.getForwardTo().getLogin().getEmail()

				+ "\n" + "Sent at: " + this.getSentDate()

				+ "\n" + "Body: " + this.getBody()

				+ "\n" + "Type: " + this.getType()

				+ "\n" + "Source: " + this.getSource()

				+ "\n" + "Origin: " + this.getOrigin()

				+ "\n" + "Sid: " + this.getSid()

				+ "\n";
	}

	public String getSource() {

		return this.source;
	}

	public void setSource(String source) {

		this.source = source;
	}

	public User getForwardTo() {

		return this.forwardTo;
	}

	public void setForwardTo(User forwardTo) {

		this.forwardTo = forwardTo;
	}

	public User getForwardFrom() {

		return this.forwardFrom;
	}

	public void setForwardFrom(User forwardFrom) {

		this.forwardFrom = forwardFrom;
	}

	public int getOrigin() {

		return this.origin;
	}

	public void setOrigin(int origin) {

		this.origin = origin;
	}

	public boolean isSent() { // TODO Auto-generated method stub

		return this.sent;
	}

	public void setSent(boolean send) {

		this.sent = send;

	}
}