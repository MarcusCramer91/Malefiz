package game;

import java.util.ArrayList;

import structure.Node;
import structure.BoardStructure;

public class TokenManager {

	private BoardStructure structure;
	private ArrayList<Node> nodesRed;
	private ArrayList<Node> nodesGreen;
	private ArrayList<Node> nodesYellow;
	private ArrayList<Node> nodesBlue;
	
	public TokenManager(BoardStructure structure) {
		this.structure = structure;
		nodesRed = new ArrayList<Node>();
		nodesGreen = new ArrayList<Node>();
		nodesYellow = new ArrayList<Node>();
		nodesBlue = new ArrayList<Node>();
		getNodes();
	}

	/**
	 * Determines all nodes that currently inhabit a player token.
	 * Saves the node in the list for the respective player tokens
	 */
	public void getNodes() {
		nodesRed.clear();
		nodesGreen.clear();
		nodesYellow.clear();
		nodesBlue.clear();
		ArrayList<Node> allNodes = structure.getAllNodes();
		for (Node node : allNodes) {
			if (node.getPlayer() == 1) nodesRed.add(node);
			else if (node.getPlayer() == 2) nodesGreen.add(node);
			else if (node.getPlayer() == 3) nodesYellow.add(node);
			else if (node.getPlayer() == 4) nodesBlue.add(node);
		}
	}
	
	public ArrayList<Node> getRedNodes() {
		getNodes();
		return nodesRed;
	}
	
	public ArrayList<Node> getGreenNodes() {
		getNodes();
		return nodesGreen;
	}
	
	public ArrayList<Node> getYellowNodes() {
		getNodes();
		return nodesYellow;
	}
	
	public ArrayList<Node> getBlueNodes() {
		getNodes();
		return nodesBlue;
	}
}
