package client.model;

import java.util.Map;
import java.util.TreeMap;

import common.params.Params;
import common.params.ParamsException;
import common.params.ParamsImpl;

public class ClientContext {

	private static final String CONNECTIONS_REPOSITORY = "clusters.connections";
	
	private static final String CONNECTION_TAG = "connection";
	private static final String CONNECTION_NAME = "name";
	private static final String CONNECTION_ADDRESS = "address";
	private static final String CONNECTION_LOGIN = "login";
	
	//TODO remove this hardcode
	public final static String[] availableLoggers = {
        "ClusterManager",
        "ProfileManager",
        "PoolComponent",
        "AuthenticationComponent",
        "GarbageCollectionManager",
        "GCII",
        "GlobalIterationCompenent",
        "ReplicationComponent"
	};

    public enum logFilterType {
        message("com.emc.centera.library.logging.filters.MessageFilter"),
        category("com.emc.centera.library.logging.filters.CategoryFilter"),
        level("com.emc.centera.library.logging.filters.LevelFilter"),
        loggerName("com.emc.centera.library.logging.filters.LevelFilter"),
        transactionOrigin("com.emc.centera.library.logging.filters.QQ"),
        transactionId("com.emc.centera.library.logging.filters.QQ"),
        transactionType("com.emc.centera.library.logging.filters.QQ"),
        threadGroup("com.emc.centera.library.logging.filters.QQ"),
        threadName("com.emc.centera.library.logging.filters.QQ"),
        pid("com.emc.centera.library.logging.filters.QQ"),
        cycles("com.emc.centera.library.logging.filters.QQ"),
        timestamp("com.emc.centera.library.logging.filters.QQ");

        private final String className;

        logFilterType(String className) {
            this.className = className;
        }

        public String getClassName() {
            return className;
        }
    }
	
	private static Map<String, ConnectionParams> connectionsParams = null;

	public static Map<String, ConnectionParams> getConnectionsParams() {
		synchronized (CONNECTIONS_REPOSITORY) {
			if(connectionsParams == null) {
				connectionsParams = new TreeMap<String, ConnectionParams>();
				
				Params repositoryContent = RepositoryUtils.getRepository(CONNECTIONS_REPOSITORY);
				for(Params connection : repositoryContent.getAllParamsWithName(CONNECTION_TAG)) {
					try {
						String connectionName = connection.getString(CONNECTION_NAME);
						String clusterAddress = connection.getString(CONNECTION_ADDRESS);
						String login = connection.getString(CONNECTION_LOGIN);
						
						ConnectionParams connectionParams = new ConnectionParams(connectionName, clusterAddress, login);
						connectionsParams.put(connectionName, connectionParams);
					} catch (ParamsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return connectionsParams;
		}
	}

	public static boolean updateConnectionsParams(ConnectionParams existingConnectionParams, 
			ConnectionParams newConnectionParams) throws ClientContextException {
		
		synchronized (CONNECTIONS_REPOSITORY) {
			if(existingConnectionParams == null) {
				// add new connection params
				if(newConnectionParams == null || newConnectionParams.getConnectionName() == null)
					throw new ClientContextException("An attempt to add void connection occured");
				if(getConnectionsParams().containsKey(newConnectionParams.getConnectionName()))
					return false;
				
				getConnectionsParams().put(newConnectionParams.getConnectionName(), newConnectionParams);
			} else if (newConnectionParams == null) {
				if(existingConnectionParams == null || existingConnectionParams.getConnectionName() == null)
					throw new ClientContextException("An attempt to remove void connection occured");
				
				if(!getConnectionsParams().containsKey(existingConnectionParams.getConnectionName()))
					return false;
				
				getConnectionsParams().remove(existingConnectionParams.getConnectionName());
			} else {
				if(existingConnectionParams.getConnectionName() == null || newConnectionParams.getConnectionName() == null)
					throw new ClientContextException("An attempt to update (by)void connection occured");

				if(getConnectionsParams().containsKey(existingConnectionParams.getConnectionName()))
					return false;
		
				getConnectionsParams().remove(existingConnectionParams.getConnectionName());
				getConnectionsParams().put(newConnectionParams.getConnectionName(), newConnectionParams);
			}
			persistConnectionsParams();
		}
		
		return true;
	}

	private static void persistConnectionsParams() {
		Params newRepositoryContent = new ParamsImpl();
		
		synchronized (CONNECTIONS_REPOSITORY) {
			for(ConnectionParams params : getConnectionsParams().values()) {
				Params newRepositoryContentEntry = new ParamsImpl();
				newRepositoryContentEntry.putString(CONNECTION_NAME, params.getConnectionName());
				newRepositoryContentEntry.putString(CONNECTION_ADDRESS, params.getAddress());
				newRepositoryContentEntry.putString(CONNECTION_LOGIN, params.getLogin());
				
				newRepositoryContent.putParams(CONNECTION_TAG, newRepositoryContentEntry);
			}
		}

		RepositoryUtils.persistRepository(CONNECTIONS_REPOSITORY, newRepositoryContent);
	}
	
    public static void main(String[] args) {
    	System.out.println(getConnectionsParams());
    }
	
}
