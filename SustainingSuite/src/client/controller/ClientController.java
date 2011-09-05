package client.controller;

import java.util.ArrayList;
import java.util.Collection;

import client.view.MainFrame;
import common.controller.Module;
import common.controller.ModuleException;
import common.controller.ModulesController;

public class ClientController extends ModulesController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ClientController controller = new ClientController();

		controller.start();
	}

	@Override
	protected void init() throws ModuleException {
	}

	@Override
	protected Collection<? extends Module> getModules() throws ModuleException {
		Collection<Module> modules = new ArrayList<Module>();
		
		ClientLibraryModule clientLibraryModule = new ClientLibraryModule();
		modules.add(clientLibraryModule);
		ConnectionController connectionController = new ConnectionController(clientLibraryModule);
		modules.add(connectionController);
		TaskController taskController = new TaskController(connectionController);
		modules.add(taskController);
		MainFrame mainFrameModule = new MainFrame(this, connectionController);
		modules.add(mainFrameModule);
		
		return modules;
	}

}
