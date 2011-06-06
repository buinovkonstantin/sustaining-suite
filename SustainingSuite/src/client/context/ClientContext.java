package client.context;

import java.util.Map;

import org.w3c.dom.Node;

import common.params.Params;
import common.params.ParamsImpl;


public class ClientContext {

	private static final String CONNECTION_TAG = "connection";

	public static Map<String, ConnectionParams> getConnectionsParams() {
		return null;
		
	}

	public static boolean updateConnectionsParams(ConnectionParams existingConnectionParams, 
			ConnectionParams newConnectionParams) throws ClientContextException {
		
		if(existingConnectionParams == null) {
			// add new connection params
			if(newConnectionParams == null || newConnectionParams.getConnectionName() == null)
				throw new ClientContextException("An attempt to add void connection occured");
			
			if(getConnectionsParams().containsKey(newConnectionParams.getConnectionName()))
				return false;
			
			getConnectionsParams().put(newConnectionParams.getConnectionName(), newConnectionParams);
			persistConnectionsParams();
		} else if (newConnectionParams == null) {
			// remove existing connection params
		} else {
			// update existing connection params
		}
		
		return false;
	}

	private static void persistConnectionsParams() {
		Params newRepositoryContent = new ParamsImpl();
		for(ConnectionParams params : getConnectionsParams().values()) {
			Params repositoryEntry = new ParamsImpl(CONNECTION_TAG);
		}
		// TODO Auto-generated method stub
		
	}
}
