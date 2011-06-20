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

		ClientLibraryModule clientLibraryModule = new ClientLibraryModule();
		add(clientLibraryModule);
		ConnectionController connectionController = new ConnectionController(clientLibraryModule);
		add(connectionController);
		TaskController taskController = new TaskController(connectionController);
		add(taskController);
		MainFrame mainFrameModule = new MainFrame(this, connectionController);
		add(mainFrameModule);
	}

}
