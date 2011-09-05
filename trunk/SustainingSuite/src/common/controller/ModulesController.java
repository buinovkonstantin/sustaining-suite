package common.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Fundamental controller managing lifetime of application.
 * Manages modules of application: their creation, initialization,
 * start and final stop.
 * 
 * An instance of this object must be created once at start of application
 * and should live as single instance.
 *
 * Subclass must use 'start' method as the entry point
 * to start application run, and 'stop' method to stop application run.
 * 
 * @author buinok
 */
public abstract class ModulesController {
	
	private List<Module> modules = new ArrayList<Module>();
	
	/**
	 * Synchronous application start.
	 * The method will work until the 'stop' method is called.
	 */
	protected void start() {

		try {
			init();
		} catch (ModuleException e1) {
			// can't initialize modules
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try {
			modules.addAll(getModules());
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

	/**
	 * Initializes controller instance
	 * @throws ModuleException
	 */
	protected abstract void init() throws ModuleException;

	/**
	 * Creates instances of all modules
	 * @return collection of modules to be activated for application run,
	 *         modules are iterated in collection in order of their start
	 * @throws ModuleException
	 */
	protected abstract Collection<? extends Module> getModules() throws ModuleException;

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

	/**
	 * Asynchronous stop of application run.
	 * Notifies controller to stop modules in reverse order than
	 * they were started.
	 */
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
