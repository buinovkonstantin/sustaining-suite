package client.model;

import java.util.Map;

import common.protocol.Task;

public class SessionContext {
	
	private Object sessionLock;
	
	private int sessionId;
	
	private int creationTimestamp;
	
	private int lastUpdateTimestamp;
	
	private long size;
	
	private Map<Integer, Task> tasks;

	public void setSessionLock(Object sessionLock) {
		this.sessionLock = sessionLock;
	}

	public Object getSessionLock() {
		return sessionLock;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setCreationTimestamp(int creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public int getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setLastUpdateTimestamp(int lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	public int getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getSize() {
		return size;
	}

	public void setTasks(Map<Integer, Task> tasks) {
		this.tasks = tasks;
	}

	public Map<Integer, Task> getTasks() {
		return tasks;
	}

}
