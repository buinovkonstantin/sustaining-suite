package server.modules;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import common.controller.Module;
import common.controller.ModuleException;
import common.protocol.Task;

public class JobProcessingModule implements Module, Runnable {

	private FilesystemModule filesystemModule;
	private Thread jobsProcessor;
	private Queue<Task> jobs = new ConcurrentLinkedQueue<Task>();
	
	public JobProcessingModule(FilesystemModule filesystemModule) {
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
	
	public void createJob(Task request) {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
