package client.model;

import java.util.Arrays;
import java.util.Collection;

import client.library.Node;

public class ConnectionContext {
	
	public enum State {
		Disconnected(false),
		Connected(true),
		Aborted(false);
		
		private final boolean isConnected;
		
		private State(boolean isConnected) {
			this.isConnected = isConnected;
		}
		
		public boolean isConnected() {
			return isConnected;
		}
	};
	
	private ConnectionParams connectionParams;
	
	private char[] password = new char[0];
	
	private State state;
	
	private String centrastarVersion;
	
	private SessionContext sessionContext;
	
	private Collection<Node> nodes;

	public ConnectionContext() {
		state = State.Disconnected;
	}

	public void setConnectionParams(ConnectionParams connectionParams) {
		this.connectionParams = new ConnectionParams(connectionParams);
	}

	public ConnectionParams getConnectionParams() {
		return connectionParams;
	}

	public void setPassword(char[] password) {
		for(int i=0; i<this.password.length; i++)
			this.password[i] = 0;
		this.password = Arrays.copyOf(password, password.length);
	}

	public char[] getPassword() {
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
