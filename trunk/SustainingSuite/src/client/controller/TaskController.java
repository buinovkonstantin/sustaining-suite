package client.controller;

import common.protocol.Task;

public class TaskController {

	private CachedTaskRepository taskCache;
	private TaskMonitor taskMonitor;
	private TaskUpdater taskUpdater;
	
	public int createTask(Task task) {
		return 0;
	}
	
	public Task getTask(int id) {
		return null;
	}
	
	public void registerTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		
	}
 
	public void unregisterTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		
	}
 
}
