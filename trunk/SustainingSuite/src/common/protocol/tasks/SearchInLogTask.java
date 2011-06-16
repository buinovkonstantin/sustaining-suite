package common.protocol.tasks;

import java.util.Date;
import java.util.Set;

import common.protocol.TaskImpl;

public class SearchInLogTask extends TaskImpl {

	private Date from;
	private Date to;
	private Set<String> nodes;
	private Set<String> logTypes; 
	private boolean isPatternRegExp;
	private String pattern;
	
	public final static String TYPE = "SEARCH_IN_LOG";

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getFrom() {
		return from;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getTo() {
		return to;
	}

	public void setNodes(Set<String> nodes) {
		this.nodes = nodes;
	}

	public Set<String> getNodes() {
		return nodes;
	}

	public void setLogTypes(Set<String> logTypes) {
		this.logTypes = logTypes;
	}

	public Set<String> getLogTypes() {
		return logTypes;
	}

	public void setPatternRegExp(boolean isPatternRegExp) {
		this.isPatternRegExp = isPatternRegExp;
	}

	public boolean isPatternRegExp() {
		return isPatternRegExp;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
	
}
