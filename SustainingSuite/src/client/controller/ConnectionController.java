package client.controller;

import common.protocol.Task;

public class ConnectionController {

	private ConnectionMonitor connectionMonitor;
	
	public void registerHandler(ConnectionUpdateHandler handler) {
		connectionMonitor.registerHandler(handler);
	}
	
	public void unregisterHandler(ConnectionUpdateHandler handler) {
		connectionMonitor.unregisterHandler(handler);
	}
	
}
