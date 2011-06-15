package server.library;

import java.util.Collection;
import java.util.Date;

import common.protocol.DataType;
import common.protocol.LogFilter;
import common.protocol.LogLevel;
import common.protocol.LogType;

public interface NodeEnvironment {
	
	public boolean searchInLogs(Date from, Date to, Collection<LogType> logTypes, String pattern, boolean isRegExp);
	
	public boolean startCustomLog(String logName, LogLevel level, Collection<String> loggers, Collection<LogFilter> filters);
	public boolean stopCustomLog(String logName);
	
	public boolean startTcpDumping(String nic, String filters);
	public boolean stopTcpDumping();
	
	public boolean queryFiles(Collection<DataType> dataTypes, boolean allSessions);
	public boolean downloadFiles(Collection<String> paths);

}
