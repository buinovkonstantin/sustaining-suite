package client.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import client.view.menu.cluster.ExitDialog;

import common.controller.ModulesController;

public class MainFrameWindowListener implements WindowListener {

	private ModulesController controller;

	public MainFrameWindowListener(ModulesController controller) {
		this.controller = controller;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		new ExitDialog(controller);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
