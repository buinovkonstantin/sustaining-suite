package server.library;

import java.util.Collection;
import java.util.Date;


import common.protocol.tasks.Log;

public interface NodeEnvironment {
	
	public boolean searchInLogs(Date from, Date to, Collection<String> logTypes, String pattern, boolean isRegExp);
	
	public boolean startCustomLog(String logName, Log.Level level, Collection<String> loggers, Collection<Log.Filter> filters);
	public boolean stopCustomLog(String logName);
	
	public boolean startTcpDumping(String nic, String filters);
	public boolean stopTcpDumping();
	
	public boolean queryFiles(Collection<DataType> dataTypes, boolean allSessions);
	public boolean downloadFiles(Collection<String> paths);

}
