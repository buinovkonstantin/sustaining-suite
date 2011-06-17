package common.protocol;

import java.util.Collection;
import java.util.Date;

import common.params.Params;

public interface Task {
	
	public void setId(int id);
	public int getId();
	
	public void setType(String type);
	public String getType();
	
	public void setCreationTime(Date creationDate);
	public Date getCreationTime();
	
	public void setModificationTime(Date modificationDate);
	public Date getModificationTime();
	
	public void setExpirationPeriod(int expirationPeriod);
	public int getExpirationPeriod();
	
	public void setState(State state);
	public State getState();

	public void setResults(Collection<Result> results);
	public Collection<Result> getResults();
	
	public Params getParams();
	
	public interface State {
		
		public enum Name { Pending, Initial, Running, Complete, Aborted };
		
		public void setName(Name name);
		public Name getName();
		
		public void setTotalSteps(int totalSteps);
		public int getTotalSteps();
		
		public void setCompleteSteps(int completeSteps);
		public int getCompleteSteps();
		
		public void setFailedSteps(int failedSteps);
		public int getFailedSteps();
		
	}
	
	public interface Result {
	
		public void setId(int id);
		public int getId();
		
		public void setNode(String node);
		public String getNode();
		
		public void setPath(String path);
		public String getPath();
		
		public void setSize(long size);
		public long getSize();
		
		public void setSuccessful(boolean isSuccessful);
		public boolean isSuccessful();
		
	}
}
