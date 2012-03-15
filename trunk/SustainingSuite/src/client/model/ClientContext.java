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

	private static final String GUI_SETTINGS_REPOSITORY = "gui.settings";
	
	private static final String SETTINGS_TAG = "gui_settings";
	private static final String GUI_NAME = "gui_name";
	private static final String GUI_SETTINGS = "gui_settings";
	
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

			RepositoryUtils.persistRepository(CONNECTIONS_REPOSITORY, newRepositoryContent);
		}
	}
	
	private static Map<String, Params> guiSettings = null;
	
	public static Map<String, Params> getGuiSettings() {
		synchronized (GUI_SETTINGS_REPOSITORY) {
			if(guiSettings == null) {
				guiSettings = new TreeMap<String, Params>();
				
				Params repositoryContent = RepositoryUtils.getRepository(GUI_SETTINGS_REPOSITORY);
				for(Params settings : repositoryContent.getAllParamsWithName(SETTINGS_TAG)) {
					try {
						String guiName = settings.getString(GUI_NAME);
						Params guiSettings = settings.getParams(GUI_SETTINGS);
						
						ClientContext.guiSettings.put(guiName, guiSettings);
					} catch (ParamsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			return guiSettings;
		}
	}

	public static boolean updateGuiSettings(Params existingGuiSettings, 
			Params newGuiSettings) throws ClientContextException {
		
		synchronized (GUI_SETTINGS_REPOSITORY) {
			try {
				if(existingGuiSettings == null) {
					// add new gui settings
					if(newGuiSettings == null || newGuiSettings.getString(GUI_NAME, null) == null)
						throw new ClientContextException("An attempt to add void connection occured");
						if(getGuiSettings().containsKey(newGuiSettings.getString(GUI_NAME)))
							return false;
		
					getGuiSettings().put(newGuiSettings.getString(GUI_NAME), newGuiSettings);
				} else if (newGuiSettings == null) {
					// remove existing gui settings
					if(existingGuiSettings == null || existingGuiSettings.getString(GUI_NAME, null) == null)
						throw new ClientContextException("An attempt to remove void connection occured");
					
					if(!getGuiSettings().containsKey(existingGuiSettings.getString(GUI_NAME)))
						return false;
					
					getGuiSettings().remove(existingGuiSettings.getString(GUI_NAME));
				} else {
					// update existing gui settings
					if(existingGuiSettings.getString(GUI_NAME, null) == null || newGuiSettings.getString(GUI_NAME, null) == null)
						throw new ClientContextException("An attempt to update (by)void connection occured");
	
					getGuiSettings().remove(existingGuiSettings.getString(GUI_NAME));
					getGuiSettings().put(newGuiSettings.getString(GUI_NAME), newGuiSettings);
				}
				persistGuiSettings();
			} catch (ParamsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}

	private static void persistGuiSettings() {
		Params newRepositoryContent = new ParamsImpl();
		
		synchronized (GUI_SETTINGS_REPOSITORY) {
			for(Params params : getGuiSettings().values())
				newRepositoryContent.putParams(SETTINGS_TAG, new ParamsImpl(params));
			
			RepositoryUtils.persistRepository(GUI_SETTINGS_REPOSITORY, newRepositoryContent);
		}
	}
}
