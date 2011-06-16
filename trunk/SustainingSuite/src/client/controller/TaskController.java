package client.controller;

import common.protocol.Task;

public class TaskController {

	private CachedTaskRepository taskCache;
	private TaskMonitor taskMonitor;
	private TaskUpdater taskUpdater;
	
	public TaskFactory getTaskFactory() {
		return null;
	}
	
	public Task getTask(int id) {
		return null;
	}
	
	public void registerTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		
	}
 
	public void unregisterTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		
	}
	
}
