package org.larssentech.xkomm.ui.gui.G.util;

import java.awt.Label;

import org.larssentech.xkomm.ui.shared.functions.UisFunctions;

/**
 * 
 * @author Kenneth Alcala
 * 
 * Updated: Jeff Cerasuolo
 *
 */
public class Typing {

	private int sendCounter = 0; // Controls when sending a signal from Sender
									// to
	// Receiver
	private boolean b = false; // True when a Typing signal has been sent
	private boolean typingMessageReceived = false; // Shows process begins cause
													// we have
	// received a Typing message
	private boolean timeBegins = false; // Will control 1,2 seconds begins
	private int receiverCounter = 0; // Controls 1.200 milliseconds for Receiver
										// showing
	// Typing message part
	private boolean continueCounting = false; // Controls to add 100
												// milliseconds to
	// 'receiverCounter' when
	// it is needen (see below)

	private String to;
	private Label statusBar;

	public Typing(String to, Label statusBar) {

		this.to = to;
		this.statusBar = statusBar;
		type_algorithm();
	}

	/**
	 * This method is for Sender. Sends Typing message from Sender of message to
	 * Receiver of message
	 */
	public void sendTypingSignal() {

		// sendCounter begins at 0 and ends at 2000 milliseconds. 0 means, that
		// it is first key pressed, or that 2 seconds has passed by and begins
		// sending signal
		if (this.sendCounter == 0) {
			this.b = UisFunctions.requestSendTyping(this.to);

		}

	}

	/**
	 * This method is for Receiver. It's the algorithm that takes place when
	 * receiving Typing Signal
	 */
	private void type_algorithm() {

		Thread typingThread = new Thread() {

			public void run() {

				while (true) {

					// This part is for Sender
					if (Typing.this.b == true) { // We have just sent a Typing
													// message to
						// Receiver. sendCounter == 0

						Typing.this.b = false; // Stops new key pressed until 2
												// seconds are
						// elapsed
						Typing.this.timeBegins = true; // Begins 2 seconds
														// waiting (until
						// next key pressed)

					}
					else if (Typing.this.sendCounter == 2000) { // 2 seconds
																// finished.
																// Ready
						// for new key pressed
						// and begins again the 2
						// seconds

						Typing.this.sendCounter = 0;
						Typing.this.timeBegins = false;

					}
					else if (Typing.this.timeBegins == true) {

						Typing.this.sendCounter += 100;

					}

					// This part is for Receiver. It will be fetched at minimum
					// 1,6 seconds between Typing message
					if (Typing.this.typingMessageReceived == true) { // A key
																		// has
																		// been
						// pressed with
						// signal sent,
						// and we are at the
						// moment of
						// beginning
						// again the 1,6
						// seconds time

						Typing.this.typingMessageReceived = false; // Will wait
																	// until
																	// next
						// "Typing..." message
						Typing.this.statusBar.setText("Typing...");
						Typing.this.continueCounting = true; // Allow continue
																// counting
						// milliseconds

					}
					else if (Typing.this.receiverCounter == 1900) { // We finish
																	// showing
						// "Typing..." till next
						// Typing message
						// fetched

						Typing.this.receiverCounter = 0;
						Typing.this.statusBar.setText("");
						Typing.this.continueCounting = false; // Stops counting
																// milliseconds
						// until
						// next fetched Typing
						// message

					}
					else if (Typing.this.continueCounting == true) { // Continues
																		// showing
						// "Typing..."
						// until 1.600
						// milliseconds

						Typing.this.statusBar.setText("Typing...");
						Typing.this.receiverCounter += 100;

					}

					try {
						Thread.sleep(100);
					}
					catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}
		};
		typingThread.start();

	}

	/**
	 * This method is for Receiver. Detects Typing message sent from sender, and
	 * is the trigger to the up algorithm
	 */
	public void fetchTypingMessage() { // Will fetch Typing message, but with
										// 1,2 seconds of minimum delay
										// See above logic @ sendTypingSignal()

		if (UisFunctions.requestdReceiveSys(this.to).getBody().indexOf("Typing...") != -1) this.typingMessageReceived = true;

	}
}
