package common.protocol.tasks;

import java.util.Set;

import common.protocol.TaskImpl;

public class QueryDataTask extends TaskImpl {

	private Set<String> nodes;
	private Set<String> dataTypes; 
	private boolean queryForeignSessions;
	
	public final static String TYPE = "QUERY_DATA";

	public void setNodes(Set<String> nodes) {
		this.nodes = nodes;
	}

	public Set<String> getNodes() {
		return nodes;
	}

	public void setDataTypes(Set<String> dataTypes) {
		this.dataTypes = dataTypes;
	}

	public Set<String> getDataTypes() {
		return dataTypes;
	}

	public void setQueryForeignSessions(boolean queryForeignSessions) {
		this.queryForeignSessions = queryForeignSessions;
	}

	public boolean queryForeignSessions() {
		return queryForeignSessions;
	}

}
