/*
 * Copyright 2014-2022 Larssentech Developers
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
package org.larssentech.xkomm.ui.shared.chat;

import java.awt.Frame;
import java.util.Hashtable;

public class ChatRoomMan {

	public static boolean isReg(String to) {

		return FrameStore.isRegistered(to);
	}

	public static void reg(String frameKey, Frame frame) {

		FrameStore.registerFrame(frameKey, frame);
	}

	/**
	 * Just for closing the chat GUI
	 */
	public static void recycle(Frame frame, String key) {

		FrameStore.deRegisterFrame(key);
		frame.setVisible(false);
		frame.dispose();
	}

	protected static class FrameStore {
		protected static Hashtable store = new Hashtable();

		protected synchronized static void registerFrame(String email, Frame c) {

			store.put(email.toLowerCase(), c);
		}

		private synchronized static boolean isRegistered(String to) {

			return store.containsKey(to.toLowerCase());
		}

		private synchronized static boolean deRegisterFrame(String email) {

			store.remove(email.toLowerCase());
			return !store.contains(email.toLowerCase());
		}

		public static Hashtable getStore() { return store; }
	}
}