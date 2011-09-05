package server.controller;

import java.util.ArrayList;
import java.util.Collection;

import server.modules.FactoriesModule;
import server.modules.FilesystemModule;
import server.modules.JobProcessingModule;
import server.modules.RequestProcessingModule;
import server.modules.ServerLibraryModule;

import common.controller.Module;
import common.controller.ModuleException;
import common.controller.ModulesController;

public class ServerController extends ModulesController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ServerController controller = new ServerController();

		controller.start();
	}

	@Override
	protected void init() throws ModuleException {
		obtainServerLock();
	}
	
	@Override
	protected Collection<? extends Module> getModules() throws ModuleException {
		Collection<Module> modules = new ArrayList<Module>();
		
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
		
		return modules;
	}

	/*
	 * It's only allowed to run single instance of server component
	 * on a node. Second instance must not start.
	 */
	private void obtainServerLock() throws ModuleException {
		// the lock is obtained on platform level via
		// flock Linux command, so just skip this step
	}

}
