package server.modules;

import server.library.ServerLibrary;
import common.controller.Module;
import common.controller.ModuleException;

public class ServerLibraryModule implements Module {
	
	private ServerLibrary library;

	@Override
	public void start() throws ModuleException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws ModuleException {
		// TODO Auto-generated method stub

	}
	
	public ServerLibrary getLibrary() {
		return library;
	}

}
