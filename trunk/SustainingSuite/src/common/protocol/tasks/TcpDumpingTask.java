package common.protocol.tasks;

import java.util.Date;
import java.util.Set;

import common.protocol.TaskImpl;

public class TcpDumpingTask extends TaskImpl {

	private Date startDate;
	private Set<String> nodes;
	private Nic nic;
	private String filter;
	
	public final static String TYPE = "TCP_DUMPING";
	
	public enum Nic { eth0, eth1, eth2 }

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

	public void setNic(Nic nic) {
		this.nic = nic;
	}

	public Nic getNic() {
		return nic;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilter() {
		return filter;
	};

}
