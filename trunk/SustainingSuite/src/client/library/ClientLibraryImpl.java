package client.library;

import java.io.IOException;
import java.io.InputStream;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ClientLibraryImpl implements ClientLibrary {

	private static final int SSH_PORT_NUMBER = 22;
	private static final String EXEC_CHANNEL = "exec";

	@Override
	public ClientSession createClientSession() {
		return new ClientSessionImpl();
	}

	@Override
	public void connect(ClientSession session, String host, String login,
			char[] password, ClientUserInterface userInterface) throws ClientLibraryException {
		// create new instance of JSch client
		JSch jsch = new JSch();
		
		Session sshSession = null;
		Channel channel = null;
		try {
			sshSession = jsch.getSession(login, host, SSH_PORT_NUMBER);
			sshSession.setUserInfo(new ClientSessionUserInfo(password, userInterface));
			sshSession.connect();
			channel = sshSession.openChannel(EXEC_CHANNEL);
		} catch (JSchException jsException) {
			throw new ClientLibraryException(
					"Can't create new SSH session to the host ["+host+":"+SSH_PORT_NUMBER+"] with login ["+login+"]",
					jsException);
		}
		
		((ChannelExec)channel).setCommand("ps ax\n");
		channel.setInputStream(null);
		((ChannelExec)channel).setErrStream(System.err);
		InputStream execOutput = null;
		try {
			execOutput = channel.getInputStream();
			channel.connect();
			
			byte[] tmp=new byte[1024];
			while(true){
				while(execOutput.available()>0){
					int i=execOutput.read(tmp, 0, 1024);
					if(i<0)break;
					System.out.print(new String(tmp, 0, i));
				}
				if(channel.isClosed()){
					System.out.println("exit-status: "+channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}
		} catch (IOException ioException) {
			throw new ClientLibraryException(
					"Can't get output for executed command on ["+host+":"+SSH_PORT_NUMBER+"] with login ["+login+"]",
					ioException);
		} catch (JSchException jsException) {
			throw new ClientLibraryException(
					"Can't connect command channel to the host ["+host+":"+SSH_PORT_NUMBER+"] with login ["+login+"]",
					jsException);
		}
		channel.disconnect();
		sshSession.disconnect();
	}

}
