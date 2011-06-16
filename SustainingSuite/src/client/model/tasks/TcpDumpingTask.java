package client.model.tasks;

import common.protocol.TaskImpl;

public class TcpDumpingTask extends TaskImpl {

	public final static String TYPE = "TCP_DUMPING";
	
	public enum Nic { eth0, eth1, eth2 };

}
