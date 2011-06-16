package client.controller;

import client.model.ConnectionContext;
import common.protocol.Task;

public class TaskController implements ConnectionUpdateHandler {

	private CachedTaskRepository taskCache;
	private TaskMonitor taskMonitor;
	private TaskUpdater taskUpdater;
	
	public TaskController() {
		// TODO Auto-generated constructor stub
	}
	
	public TaskFactory getTaskFactory() {
		return null;
	}
	
	public Task getTask(int id) {
		return null;
	}
	
	public void registerTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		taskUpdater.registerTaskUpdateHandler(id, handler);
	}
 
	public void unregisterTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		taskUpdater.unregisterTaskUpdateHandler(id, handler);
	}

	@Override
	public void handle(ConnectionContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
