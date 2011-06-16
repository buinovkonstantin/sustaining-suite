package common.controller;

public interface Module {
	
	public abstract void start() throws ModuleException;
	
	public abstract void stop() throws ModuleException;

}
