package org.larssentech.xkomm.ui.gui.G.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.larssentech.xkomm.ui.shared.functions.Functions4Inactivity;
import org.larssentech.xkomm.ui.shared.functions.UisFunctions;

class ChatGListener
		implements ComponentListener, WindowListener, ActionListener, ItemListener, KeyListener, MouseListener {

	private ChatGFrame owner;

	public ChatGListener(ChatGFrame chatGFrame) {

		this.owner = chatGFrame;
	}

	public void mouseClicked(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mousePressed(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mouseReleased(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mouseEntered(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mouseExited(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void keyTyped(KeyEvent e) {

		Functions4Inactivity.sh();
	}

	public void keyPressed(KeyEvent e) {

		Functions4Inactivity.sh();
	}

	public void keyReleased(KeyEvent e) {

		Functions4Inactivity.sh();

		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			this.owner.getExec().doSend();

		else
			this.owner.getTyping().sendTypingSignal();
	}

	public void itemStateChanged(ItemEvent e) {

		Functions4Inactivity.sh();
	}

	public void actionPerformed(ActionEvent e) {

		Functions4Inactivity.sh();

		if (e.getActionCommand().equals("SEND")) this.owner.getExec().doSend();

		if (e.getActionCommand().equals("CLOSE")) this.owner.getExec().recycle();

		if (e.getActionCommand().equals("CLEAR")) this.owner.getExec().doClear();

		if (e.getActionCommand().equals("SHOW_PUK")) this.owner.getExec().doShowPuk();

		if (e.getActionCommand().equals("SYS_INFO")) ChatGExec.doShowSysInfo();

	}

	public void windowOpened(WindowEvent e) {

		Functions4Inactivity.sh();
	}

	public void windowClosing(WindowEvent e) {

		Functions4Inactivity.sh();
		this.owner.getExec().recycle();

	}

	public void windowClosed(WindowEvent e) {

		Functions4Inactivity.sh();
	}

	public void windowIconified(WindowEvent e) {

		Functions4Inactivity.sh();
	}

	public void windowDeiconified(WindowEvent e) {

		Functions4Inactivity.sh();
	}

	public void windowActivated(WindowEvent e) {

		Functions4Inactivity.sh();
	}

	public void windowDeactivated(WindowEvent e) {

		Functions4Inactivity.sh();
	}

	public void componentResized(ComponentEvent e) {

		Functions4Inactivity.sh();
		UisFunctions.requestSaveChatFrame(this.owner, this.owner.getTo());
	}

	public void componentMoved(ComponentEvent e) {

		Functions4Inactivity.sh();
		UisFunctions.requestSaveChatFrame(this.owner, this.owner.getTo());

	}

	public void componentShown(ComponentEvent e) {

		Functions4Inactivity.sh();
	}

	public void componentHidden(ComponentEvent e) {

		Functions4Inactivity.sh();
	}
}