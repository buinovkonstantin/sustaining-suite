package common.protocol.tasks;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import common.protocol.TaskImpl;

public class CustomLoggingTask extends TaskImpl {

	private Date startDate;
	private Set<String> nodes;
	private Log.Level level;
	private Set<String> loggers;
	private Collection<Log.Filter> filters;
	
	public final static String TYPE = "CUSTOM_LOGGING";

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setNodes(Set<String> nodes) {
		this.nodes = nodes;
	}

	public Set<String> getNodes() {
		return nodes;
	}

	public void setLevel(Log.Level level) {
		this.level = level;
	}

	public Log.Level getLevel() {
		return level;
	}

	public void setLoggers(Set<String> loggers) {
		this.loggers = loggers;
	}

	public Set<String> getLoggers() {
		return loggers;
	}

	public void setFilters(Collection<Log.Filter> filters) {
		this.filters = filters;
	}

	public Collection<Log.Filter> getFilters() {
		return filters;
	}

}
