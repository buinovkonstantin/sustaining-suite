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

}
