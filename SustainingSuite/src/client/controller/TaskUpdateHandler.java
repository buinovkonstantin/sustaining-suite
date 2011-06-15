package client.controller;

import common.protocol.Task;

public interface TaskUpdateHandler {

	public enum UpdateType { State, Result };
	
	public void handle(UpdateType type, Task task);
	
}
