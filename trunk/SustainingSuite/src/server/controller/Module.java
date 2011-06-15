package server.controller;

public abstract class Module {
	
	public abstract void start() throws ModuleException;
	
	public abstract void stop() throws ModuleException;

}
