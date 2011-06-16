package common.protocol.tasks;

import common.protocol.TaskImpl;

public class SmartPacketDecodeTask extends TaskImpl {

	private String encodedPacketContent;
	
	public final static String TYPE = "SMART_PACKET_DECODE";

	public void setEncodedPacketContent(String encodedPacketContent) {
		this.encodedPacketContent = encodedPacketContent;
	}

	public String getEncodedPacketContent() {
		return encodedPacketContent;
	}
	
}
