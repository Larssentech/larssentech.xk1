package org.larssentech.xkomm.ui.gui.G.main;

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

import org.larssentech.xkomm.ui.gui.G.util.Alerter;
import org.larssentech.xkomm.ui.shared.functions.Functions4Inactivity;
import org.larssentech.xkomm.ui.shared.functions.UisFunctions;

class MainGListener implements ComponentListener, WindowListener, ActionListener, ItemListener, KeyListener, MouseListener {

	private final MainGFrame mainGFrame;

	public MainGListener(final MainGFrame mainGFrame) {

		this.mainGFrame = mainGFrame;
	}

	public void componentResized(ComponentEvent e) {

		Functions4Inactivity.sh();
		UisFunctions.requestSaveMainFrame(this.mainGFrame);
	}

	public void mouseClicked(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mouseEntered(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mouseExited(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mousePressed(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void mouseReleased(MouseEvent e) {

		Functions4Inactivity.sh();
	}

	public void keyReleased(KeyEvent arg0) {

		Functions4Inactivity.sh();
	}

	public void keyTyped(KeyEvent arg0) {

		Functions4Inactivity.sh();
	}

	public void keyPressed(KeyEvent arg0) {

		Functions4Inactivity.sh();
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) MainGExec.doLaunch();
	}

	public void itemStateChanged(ItemEvent arg0) {

		Functions4Inactivity.sh();
		if (MainGFrame.getLizt().getSelectedIndex() != -1) MainGExec.doRefreshStatus();
	}

	public void actionPerformed(ActionEvent e) {

		Functions4Inactivity.sh();

		if (e.getActionCommand().equals("EXIT")) MainGExec.doExit();
		else if (e.getActionCommand().equals("ADD_CONTACT")) UisFunctions.requestCreateContact();
		else if (e.getActionCommand().equals("DELETE_CONTACT")) MainGExec.doDeleteContact();
		else if (e.getActionCommand().equals("ABOUT")) Alerter.messageAbout();
		else if (e.getActionCommand().equals("SYS_INFO")) Alerter.messageShowSystemInfo();

		else if (MainGFrame.getLizt().getSelectedIndex() != -1) {

			MainGExec.doLaunch();
		}
	}

	public void windowOpened(WindowEvent e) {

		Functions4Inactivity.sh();
	}

	public void windowClosing(WindowEvent e) {

		Functions4Inactivity.sh();
		MainGExec.doExit();
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

	public void componentMoved(ComponentEvent e) {

		Functions4Inactivity.sh();
		UisFunctions.requestSaveMainFrame(this.mainGFrame);

	}

	public void componentShown(ComponentEvent e) {

		Functions4Inactivity.sh();
	}

	public void componentHidden(ComponentEvent e) {

		Functions4Inactivity.sh();
	}
}