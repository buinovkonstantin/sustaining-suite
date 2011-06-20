package client.library;

public class ClientLibraryException extends Exception {

	public ClientLibraryException(String message) {
		super(message);
	}

	public ClientLibraryException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
