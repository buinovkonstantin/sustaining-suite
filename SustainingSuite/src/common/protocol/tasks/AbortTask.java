package common.protocol.tasks;

import common.protocol.TaskImpl;

public class AbortTask extends TaskImpl {

	private int abortedTaskId;
	
	public final static String TYPE = "ABORT";

	public void setAbortedTaskId(int abortedTaskId) {
		this.abortedTaskId = abortedTaskId;
	}

	public int getAbortedTaskId() {
		return abortedTaskId;
	}


}
