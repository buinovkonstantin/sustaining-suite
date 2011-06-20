package client.library;

import java.util.Arrays;

import com.jcraft.jsch.UserInfo;

public class ClientSessionUserInfo implements UserInfo {

	private char[] password;
	private ClientUserInterface userInterface;

	public ClientSessionUserInfo(char[] password, ClientUserInterface userInterface) {
		if(password != null)
			this.password = Arrays.copyOf(password, password.length);
		else
			this.password = new char[0];
		 this.userInterface = userInterface;
	}

	@Override
	public void showMessage(String message) {
		if(userInterface != null)
			userInterface.showMessage(message);
		else
			System.err.println("UserInfo.showMessage["+message+"]");
	}
	
	@Override
	public boolean promptYesNo(String message) {
		if(userInterface != null)
			return userInterface.promptYesNo(message);
		else {
			System.err.println("UserInfo.promptYesNo["+message+"]");
			return false;
		}
	}
	
	@Override
	public boolean promptPassword(String message) {
		return true;
	}
	
	@Override
	public boolean promptPassphrase(String message) {
		System.err.println("UserInfo.promptPassphrase["+message+"]");
		return false;
	}
	
	@Override
	public String getPassword() {
		return new String(password);
	}
	
	@Override
	public String getPassphrase() {
		System.err.println("UserInfo.getPassphrase");
		return null;
	}

}
