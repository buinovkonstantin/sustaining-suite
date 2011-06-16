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

	public void setConnectionParams(ConnectionParams connectionParams) {
		this.connectionParams = connectionParams;
	}

	public ConnectionParams getConnectionParams() {
		return connectionParams;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setCentrastarVersion(String centrastarVersion) {
		this.centrastarVersion = centrastarVersion;
	}

	public String getCentrastarVersion() {
		return centrastarVersion;
	}

	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}

	public SessionContext getSessionContext() {
		return sessionContext;
	}

	public void setNodes(Collection<Node> nodes) {
		this.nodes = nodes;
	}

	public Collection<Node> getNodes() {
		return nodes;
	}
	
}
