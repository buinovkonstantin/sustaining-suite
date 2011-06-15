package client.model;

import java.io.File;

import common.protocol.Task;

public class PersistedTaskRepository implements TaskRepository {

	public PersistedTaskRepository(File directory) {
		
	}

	@Override
	public int createTask(Task task) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Task getTask(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTask(int id) {
		// TODO Auto-generated method stub
		
	}
}
