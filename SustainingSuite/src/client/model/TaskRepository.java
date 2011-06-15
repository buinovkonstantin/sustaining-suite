package client.model;

import common.protocol.Task;

public interface TaskRepository {

	public int createTask(Task task);
	
	public Task getTask(int id);
	
	public void removeTask(int id);
	
}
