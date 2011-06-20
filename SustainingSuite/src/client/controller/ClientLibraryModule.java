package client.controller;

import client.library.ClientLibrary;
import client.library.ClientLibraryImpl;
import common.controller.Module;
import common.controller.ModuleException;

public class ClientLibraryModule implements Module {

	ClientLibrary library;
	
	@Override
	public void start() throws ModuleException {
		library = new ClientLibraryImpl();
	}

	@Override
	public void stop() throws ModuleException {
	}
	
	public ClientLibrary getClientLibrary() {
		return library;
	}

}
