package client.controller;

import common.controller.Module;
import common.controller.ModuleException;

import client.library.ClientLibraryException;
import client.library.ClientSession;
import client.library.ClientUserInterface;
import client.model.ConnectionContext;
import client.model.ConnectionContext.State;
import client.model.ConnectionParams;

public class ConnectionController implements Module {

	private ConnectionMonitor connectionMonitor;
	private ConnectionContext context;
	private ClientLibraryModule clientLibraryModule;
	
	public ConnectionController(ClientLibraryModule clientLibraryModule) {
		this.clientLibraryModule = clientLibraryModule;
		context = new ConnectionContext();
		connectionMonitor = new ConnectionMonitor();
	}
	
	public void registerHandler(ConnectionUpdateHandler handler) {
		connectionMonitor.registerHandler(handler);
	}
	
	public void unregisterHandler(ConnectionUpdateHandler handler) {
		connectionMonitor.unregisterHandler(handler);
	}
	
	public ConnectionContext getContext() {
		return context;
	}
	
	public void establishConnection(ConnectionParams connectionParams, char[] password, ClientUserInterface userInterface) {
		if(context.getState().isConnected())
			disconnect();
		
		context.setConnectionParams(connectionParams);
		context.setPassword(password);
		
		ClientSession clientSession = clientLibraryModule.getClientLibrary().createClientSession();
		try {
			clientLibraryModule.getClientLibrary().connect(
					clientSession, 
					context.getConnectionParams().getAddress(),
					context.getConnectionParams().getLogin(),
					context.getPassword(),
					userInterface);
		} catch (ClientLibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		context.setState(State.Disconnected);
	}

	@Override
	public void start() throws ModuleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() throws ModuleException {
		// TODO Auto-generated method stub
		
	}
	
}
