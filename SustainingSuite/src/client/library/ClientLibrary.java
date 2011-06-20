package client.library;

public interface ClientLibrary {

	public ClientSession createClientSession();
	
	public void connect(ClientSession session, String host, String login, char[] password, ClientUserInterface userInterface) throws ClientLibraryException;
}
