package server.modules;

import java.util.HashMap;
import java.util.Map;

import common.controller.Module;
import common.controller.ModuleException;

public class RequestProcessingModule implements Module, Runnable {

	private Thread queueProcessingThread;
	private Map<String, TaskFactory> factories = new HashMap<String, TaskFactory>(); 
	
	public RequestProcessingModule(FilesystemModule filesystemModule,
			JobProcessingModule jobProcessingModule) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws ModuleException {
		queueProcessingThread = new Thread(this);
		queueProcessingThread.start();
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() throws ModuleException {
		// TODO Auto-generated method stub

	}

	public void registerTaskFactory(String type, TaskFactory factory) {
		factories.put(type, factory);
	}

	public void unregisterTaskFactory(String type, TaskFactory factory) {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
