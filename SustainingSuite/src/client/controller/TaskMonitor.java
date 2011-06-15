package client.controller;

import common.protocol.Task;

public class TaskMonitor {

	private TaskUpdater taskUpdater;
	
	private void checkTasksStates() {
		Task task = null;
		
		taskUpdater.update(task);
		
	}
}
