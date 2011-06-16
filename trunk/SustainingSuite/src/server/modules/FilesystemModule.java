package server.modules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import common.controller.Module;
import common.controller.ModuleException;
import common.protocol.Task;

public class FilesystemModule implements Module {

	@Override
	public void start() throws ModuleException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws ModuleException {
		// TODO Auto-generated method stub

	}
	
	public Collection<String> getRequestsList() {
		return null;
	}
	
	public InputStream getRequestStream(String request) {
		return null;
	}
	
	public void removeRequest(String request) {
		
	}
	
	public void persistTask(Task task) throws IOException {
		
	}
	
	public void removeExpiredTasks(Collection<String> expiredTasks) throws IOException {
		
	}

}
