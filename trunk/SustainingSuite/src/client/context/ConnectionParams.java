package client.context;

/**
 * Created by IntelliJ IDEA.
 * User: gregory
 * Date: 5/30/11
 * Time: 5:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionParams {
    private String connectionName;
    private String address;
    private String login;

    public ConnectionParams(String connectionName, String clusterAddress, String login) {
    	this.connectionName = connectionName;
    	this.address = clusterAddress;
    	this.login = login;
	}

	public String getConnectionName() {
        return connectionName;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("[name=");
    	builder.append(connectionName);
    	builder.append("; address=");
    	builder.append(address);
    	builder.append("; login=");
    	builder.append(login);
    	builder.append("]");
    	
    	return builder.toString();
    }
}
