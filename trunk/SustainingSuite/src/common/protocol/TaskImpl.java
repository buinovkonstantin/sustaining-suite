package common.protocol;

import java.util.Collection;

import common.params.Params;

public class TaskImpl implements Task {

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExpirationPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Result> getResults() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Params getParams() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class StateImpl implements State {

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getTotalSteps() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getCompleteSteps() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getFailedSteps() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	public class ResultImpl implements Result {

		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getNode() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPath() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isSuccessful() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
