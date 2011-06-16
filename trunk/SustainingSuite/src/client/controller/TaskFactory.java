package client.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import common.protocol.LogFilter;
import common.protocol.LogLevel;
import common.protocol.LogType;

import client.library.Node;
import client.model.tasks.AbortTask;
import client.model.tasks.CustomLoggingTask;
import client.model.tasks.DownloadTask;
import client.model.tasks.NodeList;
import client.model.tasks.QueryDataTask;
import client.model.tasks.SearchInLogTask;
import client.model.tasks.SmartPacketDecodeTask;
import client.model.tasks.StopServerTask;
import client.model.tasks.TcpDumpingTask;
import client.model.tasks.TcpDumpingTask.Nic;

public interface TaskFactory {

	public SearchInLogTask createSearchInLogTask(
			Date from, Date to, NodeList nodes, Set<LogType> logTypes, 
			boolean isPatternRegExp, String pattern);
	
	public CustomLoggingTask createCustomLoggingTask(
			Date startDate, NodeList nodes, LogLevel level,
			Set<String> loggers, Collection<LogFilter> filters);
	
	public TcpDumpingTask createTcpDumpingTask(
			Date startDate, NodeList nodes, Nic nic, String filter);
	
	public AbortTask createAbortTask(int abortedTaskId);
	
	public QueryDataTask createQueryDataTask (
			NodeList nodes, Set<String> dataTypes, 
			boolean queryForeignSessions);
	
	public DownloadTask createDownloadTask(
			Map<Node, Collection<String>> paths);
	
	public SmartPacketDecodeTask createSmartPacketDecodeTask(
			String encodedPacketContent);
	
	public StopServerTask createStopServerTask();
	
}
