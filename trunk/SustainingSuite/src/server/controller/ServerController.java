package server.controller;

import server.modules.FactoriesModule;
import server.modules.FilesystemModule;
import server.modules.JobProcessingModule;
import server.modules.RequestProcessingModule;
import server.modules.ServerLibraryModule;

import common.controller.ModuleException;
import common.controller.ModulesController;

public class ServerController extends ModulesController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ServerController controller = new ServerController();

		controller.execute();
	}

	@Override
	protected void initModules() throws ModuleException {
		obtainServerLock();
		
		ServerLibraryModule serverLibraryModule = new ServerLibraryModule();
		add(serverLibraryModule);
		
		FilesystemModule filesystemModule = new FilesystemModule();
		add(filesystemModule);
		
		JobProcessingModule jobProcessingModule = new JobProcessingModule(filesystemModule);
		add(jobProcessingModule);
		
		RequestProcessingModule requestProcessingModule = new RequestProcessingModule(filesystemModule, jobProcessingModule);
		add(requestProcessingModule);
		
		FactoriesModule factoriesModule = new FactoriesModule(this, serverLibraryModule, filesystemModule, requestProcessingModule);
		add(factoriesModule);
	}

	private void obtainServerLock() throws ModuleException {
		// TODO Auto-generated method stub
		// try to obtain server lock or throw exception
		// if it's already held by other server process
		
		throw new ModuleException();
	}

}
