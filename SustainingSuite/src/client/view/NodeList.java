package client.view;

import java.util.Collection;
import java.util.HashSet;

import client.library.Node;

public class NodeList {
	
	private Collection<Node> allNodes;
	private Collection<Node> selectedNodes;
	private Preset preset;
	
	public enum Preset { All, Access, Replication, Management, Storage, Frontend, Backend, Custom };

	public void setAllNodes(Collection<Node> nodes) {
		this.allNodes = nodes;
		setPreset(preset);
	}
	
	public Collection<Node> getAllNodes() {
		return allNodes;
	}
	
	public Collection<Node> setSelectedNodes(Collection<Node> nodes) {
		selectedNodes.clear();
		selectedNodes.addAll(nodes);
		
		Collection<Node> absentNodes = new HashSet<Node>(nodes);
		absentNodes.removeAll(allNodes);
		if(!absentNodes.isEmpty()) {
			selectedNodes.removeAll(absentNodes);
		}
		
		return selectedNodes;
	}
	
	public Collection<Node> getSelectedNodes() {
		return selectedNodes;
	}

	public void setPreset(Preset preset) {
		//TODO filtering of all nodes by preset
		this.preset = preset;
	}

	public Preset getPreset() {
		return preset;
	}
}
