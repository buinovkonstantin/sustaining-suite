package client.controller;

import java.util.Collection;
import java.util.TreeSet;

public class ConnectionMonitor implements Runnable {

	private Collection<ConnectionUpdateHandler> handlers;
	
	public ConnectionMonitor() {
		handlers = new TreeSet<ConnectionUpdateHandler>();
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
