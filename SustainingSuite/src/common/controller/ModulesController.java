package common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class ModulesController {
	private List<Module> modules = new ArrayList<Module>();
	
	/**
	 * @param args
	 */
	protected void execute() {

		try {
			initModules();
		} catch (ModuleException e1) {
			// can't initialize modules
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try {
			startModules();
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		waitForStop();
		
		try {
			stopModules();
		} catch (ModuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected abstract void initModules() throws ModuleException;
	
	protected boolean add(Module module) {
		return modules.add(module);
	}

	private void startModules() throws ModuleException {
		for(ListIterator<Module> iterator = modules.listIterator();
		iterator.hasNext(); ) {
			Module module = iterator.next();
			module.start();
		}
	}

	private void waitForStop() {
		synchronized(this) {
			// wait for stop event
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		synchronized(this) {
			this.notify();
		}
	}

	private void stopModules() throws ModuleException {
		for(ListIterator<Module> iterator = modules.listIterator(modules.size());
		iterator.hasPrevious(); ) {
			Module module = iterator.previous();
			module.stop();
		}
	}
}
