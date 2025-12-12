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
 * 
 * 
 * package org.larssentech.xkomm.core.hub.net;
 * 
 * import java.io.IOException; import java.net.SocketException; import
 * java.util.Vector;
 * 
 * import org.larssentech.xkomm.core.hub.req.Hub; import
 * org.larssentech.xkomm.core.hub.shared.SocketParams; import
 * org.larssentech.xkomm.core.obj.constants.Constants4Core; import
 * org.larssentech.xkomm.core.obj.constants.NetworkConstants; import
 * org.larssentech.xkomm.core.obj.objectStore.ObjectStoreModel; import
 * org.larssentech.xkomm.core.obj.objects.Message; import
 * org.larssentech.xkomm.core.obj.util.Logger;
 * 
 *//**
	 * 
	 * Class that performs the management of the monitor (inner class).
	 * 
	 * Controller: takes care of issues to do with reliability of network
	 * connections, so that the nomnitor can be restarted if needed.
	 * 
	 * Monitor: Takes care of the main thread of the XK2 engine, sending messages,
	 * receiving them, requesting the list of users, session, etc.
	 * 
	 * @author avanz.io
	 *
	 */
/*
 * class NetController extends Thread implements Constants4Core,
 * NetworkConstants {
 * 
 * private Monitor monitor;
 * 
 * public NetController() {
 * 
 * this.setName(Constants4Core.CONTROLLER_NAME); this.startMonitors(); }
 * 
 *//**
	 * ANDROID - DO NOT REMOVE
	 * 
	 * @return
	 */
/*
 * public Vector getControllerInfo() {
 * 
 * Vector info = new Vector();
 * 
 * info.addElement(new String[] { "getName", "" + this.getName() });
 * info.addElement(new String[] { "isAlive", "" + this.isAlive() });
 * info.addElement(new String[] { "toString", "" + this.toString() });
 * info.addElement(new String[] { "isInterrupted", "" + this.isInterrupted() });
 * info.addElement(new String[] { "isPaused", "" + NetMemory.isPaused() });
 * info.addElement(new String[] { "activeCount", "" + Thread.activeCount() });
 * info.addElement(new String[] { "getStackTrace", "" });
 * 
 * return info; }
 * 
 *//**
	 * 
	 * Fire and forget independently threaded loop based class that takes care of
	 * regularly checking with the FunctionStore's NetFunctions and do whatever is
	 * required from the network.
	 * 
	 * @author jcer
	 * 
	 */
/*
 * private static class Monitor extends Thread implements Constants4Core {
 * 
 * private Monitor() {
 * 
 * this.setName(Constants4Core.MONITOR_NAME); }
 * 
 *//**
	 * The run method is inherited from Thread and cannot have other than public
	 * visibility
	 */
/*
 * 
 * public void run() {
 * 
 * while (!NetMemory.isStopThread()) {
 * 
 * try {
 * 
 * while (NetMemory.isPaused()) Thread.sleep(Constants4Core.MONITOR_HEART_RATE);
 * 
 * NetMemory.increaseMonCount();
 * 
 * Thread.sleep(Constants4Core.MONITOR_HEART_RATE);
 * 
 * Monitor.monitor();
 * 
 * } catch (InterruptedException ignored) { } }
 * 
 * If we out of the loop, log (bad things happened) if (NetMemory.isStarted())
 * Logger.pl("Monitor not running...");
 * 
 * }
 * 
 *//**
	 * This SERIAL (do not change this, serial, single thread) sequence of
	 * functional code tries to handle all the activity XK2 needs done around the
	 * objects in the ObjectStoreModel. If at any point something fails, the rest of
	 * the functionality will stop as if one fails, the rest are likely to fail.
	 * This is because the most common issue is connectivity problems, so if we
	 * detect one, we skip everything else. Session, Contacts, Outbox, Inbox (text,
	 * sys and data).
	 * 
	 * It is important to handle contact updates before checking for messages
	 */
/*
 * private static void monitor() {
 * 
 * try {
 * 
 * For session if (!NetMemory.isStopThread()) NetSession.compareSessions();
 * 
 * Contacts too in the same thread if (!NetMemory.isStopThread())
 * NetContact.netUpdateContacts();
 * 
 * Outbox if (!NetMemory.isStopThread()) NetSend.netSendMessagesv();
 * 
 * 
 * Inbox: Only if the server reports changes we will try to receive stuff
 * otherwise it is a waste of resources
 * 
 * if (ObjectStoreModel.getSession().hasSessionChanged()) {
 * 
 * if (!NetMemory.isStopThread()) NetReceive.netReceiveMessagesv(Message.TEXT);
 * if (!NetMemory.isStopThread()) NetReceive.netReceiveMessagesv(Message.SYS);
 * if (!NetMemory.isStopThread()) NetReceive.netReceiveMessagesv(Message.DATA);
 * }
 * 
 * throttleUp(); }
 * 
 *//**
	 * This catch block is THE ONLY error handling that we have for automated
	 * operations. Therefore, here is where we decrease the number of open sockets
	 * that are not decreased on "disconnect" because of the exception preventing
	 * the call to disconnect
	 */
/*
 * catch (SocketException e) { throttleDown(e); }
 * 
 * catch (IOException e) { throttleDown(e); }
 * 
 * catch (Exception e) { throttleDown(e);
 * 
 * e.printStackTrace(); } }
 * 
 * private Monitor startThread() {
 * 
 * NetMemory.setStopThread(false); Logger.pl("Starting Monitor thread...");
 * this.start(); return this; }
 * 
 * private static void throttleDown(Exception e) {
 * 
 * { SocketParams.setNetwork(false); Logger.logError(e);
 * 
 * }
 * 
 * { NetMemory.changeHeartbeat(+500); } }
 * 
 * public static void throttleUp() {
 * 
 * SocketParams.setNetwork(true);
 * 
 * { NetMemory.changeHeartbeat(-500); } }
 * 
 * } End of Monitor
 * 
 * public void run() {
 * 
 * while (!NetMemory.isStopControllerThread()) { try {
 * 
 * while (NetMemory.isPaused()) Thread.sleep(NetMemory.getControllerSleep());
 * 
 * NetMemory.setStarted();
 * 
 * // doControl();
 * 
 * Thread.sleep(Constants4Core.CONTROLLER_HEART_RATE);
 * 
 * } catch (InterruptedException e) {
 * 
 * e.printStackTrace(); } }
 * 
 * Logger.pl("Controller not running. Goodbye."); }
 * 
 *//**
	 * This method runs in its own independent thread and keeps track of the number
	 * of "runs" for the separate, individually-threaded monitor (inbox, outbox,
	 * contacts, session). When the number of runs appears to have stalled (stuck)
	 * it will terminate the stuck thread and create a new one.
	 */
/*
 * private void doControl() {
 * 
 * If the monitor is not stuck, just keep track if (NetMemory.getMonCount() >
 * NetMemory.getMonPrevCount()) NetMemory.resetMonCounts();
 * 
 * If it is stuck, keep track of the stucks and log else {
 * 
 * Increase the metric NetMemory.incresaseMonStucks();
 * 
 * 
 * A send/receive should not take more than 5 seconds. But if no network, then
 * wait in a loop until we ascertain the network is up, then restart the monitor
 * 
 * if (NetMemory.getMonStucks() >= 5) {
 * 
 * 
 * Logger.processLog(
 * 
 * Logger.CONTROLLER_LOG,
 * 
 * " Mon: " + NetMemory.getMonCount() +
 * 
 * " Prev: " + NetMemory.getMonPrevCount() + " (" + (NetMemory.getMonCount() -
 * NetMemory.getMonPrevCount()) + ")" +
 * 
 * " Stuck: " + NetMemory.getMonStucks());
 * 
 * 
 * try {
 * 
 * 
 * This is the only place where we perform a live test of the network
 * availability after the first test right at the start. All other required
 * checks read a global variable.
 * 
 * while (!Hub.hubTestNetwork(true, false)) { SocketParams.setNetwork(false);
 * 
 * 
 * Try to stop the thread, but may not happen if it is stuck before a socket or
 * io exception, but try
 * 
 * NetMemory.setStopThread(true);
 * 
 * Log Logger.processLog(Logger.CONTROLLER_LOG, "No Network" +
 * SocketParams.getSocketTimeout());
 * 
 * And wait Thread.sleep(10000); }
 * 
 * Once NETWORK == true...
 * 
 * Logger.p("O"); this.startMonitors();
 * 
 * Reset stuck counter SocketParams.resetMonStucks(); }
 * 
 * catch (InterruptedException e) { } } } }
 * 
 *//**
	 * Method to stop the monitor (when it is stuck) and then create a new instance
	 * of the monitor.
	 * 
	 * @return
	 *//*
		 * private void startMonitors() {
		 * 
		 * // Kill the old Monitor if (this.monitor != null)
		 * NetMemory.setStopThread(true);
		 * 
		 * // Create a new Monitor this.monitor = new Monitor().startThread();
		 * 
		 * }
		 * 
		 * public static void doExit() {
		 * 
		 * NetMemory.setStopThread(true); NetMemory.setStopControllerThread(true); }
		 * 
		 * public static void pauseMonitor(boolean b) {
		 * 
		 * NetMemory.pauseMonitor(b); }
		 * 
		 * public static boolean isStarted() { return NetMemory.isStarted(); }
		 * 
		 * public static boolean haveContactsChanged() {
		 * 
		 * return NetMemory.isContactsHaveChanged(); }
		 * 
		 * public static void setContactshaveChanged(boolean b) {
		 * 
		 * NetMemory.setContactsHaveChanged(b); }
		 * 
		 * public static void setLastDataId(String uid) {
		 * 
		 * NetMemory.setLastDataID(uid); }
		 * 
		 * public static Object getLastDataId() { return NetMemory.getLastDataID(); }
		 * 
		 * public static boolean haveContactStatusChanged() {
		 * 
		 * return NetMemory.isContactStatusHaveChanged(); }
		 * 
		 * public static void setContactStatushaveChanged(boolean b) {
		 * 
		 * NetMemory.setContactStatusHaveChanged(b); } }
		 */