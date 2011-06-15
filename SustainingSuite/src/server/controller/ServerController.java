package server.controller;

import java.util.ArrayList;
import server.modules.FactoriesModule;
import server.modules.FilesystemModule;
import server.modules.JobProcessingModule;
import server.modules.RequestProcessingModule;
import server.modules.ServerLibraryModule;

public class ServerController {

	private ArrayList<Module> modules;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			obtainServerLock();
		} catch (ModuleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// server lock is already held - end execution
			return;
		}
		
		ServerController controller = new ServerController();
		
		controller.initModules();
		try {
			controller.startModules();
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		controller.waitForStop();
		
		try {
			controller.stopModules();
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void obtainServerLock() throws ModuleException {
		// TODO Auto-generated method stub
		// try to obtain server lock or throw exception
		// if it's already held by other server process
		
		throw new ModuleException();
	}

	private void initModules() {
		modules = new ArrayList<Module>();
		
		ServerLibraryModule serverLibraryModule = new ServerLibraryModule();
		modules.add(serverLibraryModule);
		
		FilesystemModule filesystemModule = new FilesystemModule();
		modules.add(filesystemModule);
		
		JobProcessingModule jobProcessingModule = new JobProcessingModule(filesystemModule);
		modules.add(jobProcessingModule);
		
		RequestProcessingModule requestProcessingModule = new RequestProcessingModule(filesystemModule, jobProcessingModule);
		modules.add(requestProcessingModule);
		
		FactoriesModule factoriesModule = new FactoriesModule(this, serverLibraryModule, filesystemModule, requestProcessingModule);
		modules.add(factoriesModule);
	}

	private void startModules() throws ModuleException {
		for(Module module : modules)
			module.start();
	}

	private void waitForStop() {
		synchronized(this) {
			// wait for stop event
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void stopModules() throws ModuleException {
		for(Module module : modules)
			module.stop();
	}

}
