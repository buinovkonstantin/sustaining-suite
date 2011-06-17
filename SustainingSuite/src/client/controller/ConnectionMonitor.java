package client.controller;

import java.util.Collection;

public class ConnectionMonitor implements Runnable {

	private Collection<ConnectionUpdateHandler> handlers;
	
	public ConnectionMonitor() {
		
	}
	
	public void registerHandler(ConnectionUpdateHandler handler) {
		handlers.add(handler);
	}
	
	public void unregisterHandler(ConnectionUpdateHandler handler) {
		handlers.remove(handler);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
