package client.controller;

import common.protocol.Task;

public class TaskMonitor implements Runnable {

	private TaskUpdater taskUpdater;
	
	@Override
	public void run() {
		checkTasksStates();
	}
	
	private void checkTasksStates() {
		Task task = null;
		
		taskUpdater.update(task);
		
	}

	
}
