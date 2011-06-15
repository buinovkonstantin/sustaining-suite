package client.model;

import java.util.Collection;

import client.library.Node;

public class ConnectionContext {

	public enum State { Disconnected, Connected, Aborted };
	
	private ConnectionParams connectionParams;
	
	private String password;
	
	private State state;
	
	private String centrastarVersion;
	
	private SessionContext sessionContext;
	
	private Collection<Node> nodes;
	
}
