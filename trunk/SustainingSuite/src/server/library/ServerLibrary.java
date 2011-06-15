package server.library;

public interface ServerLibrary {
	
	public void initNodes();
	
	public NodeEnvironment getNodeEnvironment(String nodeName);
	
	public boolean decodeSmartPacket(byte[] content);

}
