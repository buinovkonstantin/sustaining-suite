package client.model;

public class SessionsRepository {

	public static SessionContext createSession() {
		return new SessionContext();
	}
}
