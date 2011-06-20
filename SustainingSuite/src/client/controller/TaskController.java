package client.controller;

import client.model.ConnectionContext;
import common.controller.Module;
import common.controller.ModuleException;
import common.protocol.Task;

public class TaskController implements ConnectionUpdateHandler, Module {

	private CachedTaskRepository taskCache;
	private TaskMonitor taskMonitor;
	private TaskUpdater taskUpdater;
	private ConnectionController connectionController;
	
	public TaskController(ConnectionController connectionController) {
		this.connectionController = connectionController;
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

	@Override
	public void start() throws ModuleException {
		connectionController.registerHandler(this);
	}

	@Override
	public void stop() throws ModuleException {
		connectionController.unregisterHandler(this);
	}
	
}
