package client.controller;

import client.model.ConnectionContext;
import client.model.ConnectionParams;

public class ConnectionController {

	private ConnectionMonitor connectionMonitor;
	
	public void registerHandler(ConnectionUpdateHandler handler) {
		connectionMonitor.registerHandler(handler);
	}
	
	public void unregisterHandler(ConnectionUpdateHandler handler) {
		connectionMonitor.unregisterHandler(handler);
	}
	
	public ConnectionContext establishConnection(ConnectionParams connectionParams) {
		return null;
	}
	
	public void disconnect(ConnectionContext connection) {
		
	}
	
}
