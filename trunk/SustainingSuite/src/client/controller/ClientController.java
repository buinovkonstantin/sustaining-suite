package client.controller;

import client.view.MainFrame;
import common.controller.ModuleException;
import common.controller.ModulesController;

public class ClientController extends ModulesController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ClientController controller = new ClientController();

		controller.execute();
	}

	@Override
	protected void initModules() throws ModuleException {

		MainFrame mainFrameModule = new MainFrame(this);
		add(mainFrameModule);
	}

}
