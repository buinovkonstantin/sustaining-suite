package client.library;

import java.util.Collection;

public interface Node {

	public enum Role { Access, Replication, Management, Storage };
	
	public String getName();
	
	public Collection<Role> getRoles();
}
