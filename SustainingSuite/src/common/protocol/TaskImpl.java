package common.protocol;

import java.util.Collection;
import java.util.Date;

import common.params.Params;

public class TaskImpl implements Task {

	private int id;
	private String type;
	private Date creationTime;
	private Date modificationTime;
	private int expirationPeriod;
	private State state;
	private Collection<Result> results;
	private Params params;
	
	public TaskImpl() {
		
	}
	
	public TaskImpl(Params params) {
		
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public Date getCreationTime() {
		return creationTime;
	}

	@Override
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	@Override
	public Date getModificationTime() {
		return modificationTime;
	}

	@Override
	public void setExpirationPeriod(int expirationPeriod) {
		this.expirationPeriod = expirationPeriod;
	}

	@Override
	public int getExpirationPeriod() {
		return expirationPeriod;
	}

	@Override
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setResults(Collection<Result> results) {
		this.results = results;
	}

	@Override
	public Collection<Result> getResults() {
		return results;
	}

	@Override
	public Params getParams() {
		return params;
	}
	
	public class StateImpl implements State {
		
		private Name name;
		private int totalSteps;
		private int completeSteps;
		private int failedSteps;

		@Override
		public void setName(Name name) {
			this.name = name;
		}

		@Override
		public void setTotalSteps(int totalSteps) {
			this.totalSteps = totalSteps;
		}

		@Override
		public void setCompleteSteps(int completeSteps) {
			this.completeSteps = completeSteps;
		}

		@Override
		public void setFailedSteps(int failedSteps) {
			this.failedSteps = failedSteps;
		}

		@Override
		public Name getName() {
			return name;
		}

		@Override
		public int getTotalSteps() {
			return totalSteps;
		}

		@Override
		public int getCompleteSteps() {
			return completeSteps;
		}

		@Override
		public int getFailedSteps() {
			return failedSteps;
		}
		
	}
	
	public class ResultImpl implements Result {
		
		private int id;
		private String node;
		private String path;
		private long size;
		private boolean isSuccessful;

		@Override
		public void setId(int id) {
			this.id = id;
		}

		@Override
		public void setNode(String node) {
			this.node = node;
		}

		@Override
		public void setPath(String path) {
			this.path = path;
		}

		@Override
		public void setSize(long size) {
			this.size = size;
		}

		@Override
		public void setSuccessful(boolean isSuccessful) {
			this.isSuccessful = isSuccessful;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public String getNode() {
			return node;
		}

		@Override
		public String getPath() {
			return path;
		}

		@Override
		public long getSize() {
			return size;
		}

		@Override
		public boolean isSuccessful() {
			return isSuccessful;
		}
		
	}

}
