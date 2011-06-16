package client.controller;

import java.util.Map;

import common.protocol.Task;

public class TaskUpdater {

	private Map<Integer, TaskUpdateHandler> handlers;
	
	public void update(Task task) {
		// TODO Auto-generated method stub
		compareAndNotify(null, null);
	}
	
	private void compareAndNotify(Task previous, Task actual) {
		
	}

	public void registerTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		// TODO Auto-generated method stub
		handlers.put(Integer.valueOf(id), handler);
	}

	public void unregisterTaskUpdateHandler(int id, TaskUpdateHandler handler) {
		// TODO Auto-generated method stub
		
	}

}
