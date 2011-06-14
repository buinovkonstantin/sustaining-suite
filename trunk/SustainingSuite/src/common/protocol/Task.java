package common.protocol;

import java.util.Collection;

import common.params.Params;

public interface Task {
	
	public int getId();
	
	public String getType();
	
	public int getExpirationPeriod();
	
	public State getState();

	public Collection<Result> getResults();
	
	public Params getParams();
	
	public interface State {
		
		public final static String PENDING = "pending";
		public final static String INITIAL = "initial";
		public final static String RUNNING = "running";
		public final static String COMPLETE = "complete";
		public final static String ABORTED = "aborted";
		
		public String getId();
		
		public int getTotalSteps();
		
		public int getCompleteSteps();
		
		public int getFailedSteps();
		
	}
	
	public interface Result {
	
		public int getId();
		
		public String getNode();
		
		public String getPath();
		
		public int getSize();
		
		public boolean isSuccessful();
		
	}
}
