package org.larssentech.xkomm.ui.gui.G.chat;

class ChatGThread extends Thread {

	private final ChatGFrame owner;

	ChatGThread(ChatGFrame chatGFrame) {

		this.owner = chatGFrame;
	}

	public void run() {

		this.setName("ChatGThread");

		while (this.owner.getExec().isOn()) {

			this.owner.getExec().refresh();

			this.owner.getExec().fetchNextText();

			this.owner.getTyping().fetchTypingMessage();

			this.owner.getExec().setWritingAvailability();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException ignored) {
			}

		}
	}
}