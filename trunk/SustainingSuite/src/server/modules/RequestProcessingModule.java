package server.modules;

import common.controller.Module;
import common.controller.ModuleException;

public class RequestProcessingModule implements Module {

	public RequestProcessingModule(FilesystemModule filesystemModule,
			JobProcessingModule jobProcessingModule) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws ModuleException {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws ModuleException {
		// TODO Auto-generated method stub

	}

	public void registerTaskFactory(String type, TaskFactory factory) {
		
	}

	public void unregisterTaskFactory(String type, TaskFactory factory) {
		
	}
}
