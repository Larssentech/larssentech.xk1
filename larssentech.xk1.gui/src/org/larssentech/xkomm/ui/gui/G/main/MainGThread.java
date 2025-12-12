package org.larssentech.xkomm.ui.gui.G.main;

import org.larssentech.xkomm.ui.shared.constants.Constants4Ui;

class MainGThread extends Thread {

	public void run() {

		this.setName("MainGThread");

		while (true) {

			// MainGExec.doRefreshContacts();
			MainGExec.doRefreshContactLizt();
			MainGExec.doCheckForMessages();
			MainGFrame.getMeF().setForeground(MainGExec.doGetStatusColour(MainGFrame.getMeF().getText()));

			try {

				Thread.sleep(Constants4Ui.MAIN_THREAD_SLEEP);
			}
			catch (InterruptedException ignored) {}
		}
	}
}