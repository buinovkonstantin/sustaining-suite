package server.modules;

import java.util.Map;

import common.controller.Module;
import common.controller.ModuleException;

import server.controller.ServerController;

public class FactoriesModule implements Module {

	private ServerController serverController;
	private ServerLibraryModule serverLibraryModule;
	private FilesystemModule filesystemModule;
	private RequestProcessingModule requestProcessingModule;
	
	private Map<String, TaskFactory> factories;
	
	public FactoriesModule(ServerController serverController, ServerLibraryModule serverLibraryModule,
			FilesystemModule filesystemModule,
			RequestProcessingModule requestProcessingModule) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws ModuleException {
		initializeFactories();
		// TODO Auto-generated method stub
		
	}

	private void initializeFactories() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() throws ModuleException {
		// TODO Auto-generated method stub
		
	}

}
