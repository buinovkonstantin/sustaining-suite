package common.protocol.tasks;

import java.util.Collection;
import java.util.Map;

import common.protocol.TaskImpl;

public class DownloadTask extends TaskImpl {

	private Map<String, Collection<String>> paths;
	
	public final static String TYPE = "DOWNLOAD";

	public void setPaths(Map<String, Collection<String>> paths) {
		this.paths = paths;
	}

	public Map<String, Collection<String>> getPaths() {
		return paths;
	}
	
}
