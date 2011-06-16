package client.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import common.protocol.tasks.AbortTask;
import common.protocol.tasks.CustomLoggingTask;
import common.protocol.tasks.DownloadTask;
import common.protocol.tasks.Log;
import common.protocol.tasks.QueryDataTask;
import common.protocol.tasks.SearchInLogTask;
import common.protocol.tasks.SmartPacketDecodeTask;
import common.protocol.tasks.StopServerTask;
import common.protocol.tasks.TcpDumpingTask;
import common.protocol.tasks.TcpDumpingTask.Nic;

public interface TaskFactory {

	public SearchInLogTask createSearchInLogTask(
			Date from, Date to, Set<String> nodes, Set<String> logTypes, 
			boolean isPatternRegExp, String pattern);
	
	public CustomLoggingTask createCustomLoggingTask(
			Date startDate, Set<String> nodes, Log.Level level,
			Set<String> loggers, Collection<Log.Filter> filters);
	
	public TcpDumpingTask createTcpDumpingTask(
			Date startDate, Set<String> nodes, Nic nic, String filter);
	
	public AbortTask createAbortTask(int abortedTaskId);
	
	public QueryDataTask createQueryDataTask (
			Set<String> nodes, Set<String> dataTypes, 
			boolean queryForeignSessions);
	
	public DownloadTask createDownloadTask(
			Map<String, Collection<String>> paths);
	
	public SmartPacketDecodeTask createSmartPacketDecodeTask(
			String encodedPacketContent);
	
	public StopServerTask createStopServerTask();
	
}
