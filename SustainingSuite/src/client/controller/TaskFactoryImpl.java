package client.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import common.protocol.tasks.AbortTask;
import common.protocol.tasks.CustomLoggingTask;
import common.protocol.tasks.DownloadTask;
import common.protocol.tasks.QueryDataTask;
import common.protocol.tasks.SearchInLogTask;
import common.protocol.tasks.SmartPacketDecodeTask;
import common.protocol.tasks.StopServerTask;
import common.protocol.tasks.TcpDumpingTask;
import common.protocol.tasks.Log.Filter;
import common.protocol.tasks.Log.Level;
import common.protocol.tasks.TcpDumpingTask.Nic;

public class TaskFactoryImpl implements TaskFactory {

	@Override
	public SearchInLogTask createSearchInLogTask(Date from, Date to,
			Set<String> nodes, Set<String> logTypes, boolean isPatternRegExp,
			String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomLoggingTask createCustomLoggingTask(Date startDate,
			Set<String> nodes, Level level, Set<String> loggers,
			Collection<Filter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TcpDumpingTask createTcpDumpingTask(Date startDate,
			Set<String> nodes, Nic nic, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbortTask createAbortTask(int abortedTaskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryDataTask createQueryDataTask(Set<String> nodes,
			Set<String> dataTypes, boolean queryForeignSessions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DownloadTask createDownloadTask(Map<String, Collection<String>> paths) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmartPacketDecodeTask createSmartPacketDecodeTask(
			String encodedPacketContent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopServerTask createStopServerTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
