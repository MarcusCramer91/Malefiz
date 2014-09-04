package structure;

import gui.Board;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardStructure {

	// Player bases
	private ArrayList<Node> redBase;
	private ArrayList<Node> greenBase;
	private ArrayList<Node> yellowBase;
	private ArrayList<Node> blueBase;
	
	// All nodes on the  field sorted by their internal ID
	private ArrayList<Node> allNodes;
	
	// The board
	private Board board;
	
	public BoardStructure(Board board) {
		this.board = board;
		allNodes = new ArrayList<Node>();
	}
	
	public void initializeStructure() {
		
		// Creates the nodes of the bases
		initRed();
		initGreen();
		initYellow();
		initBlue();
	}
	
	private void initRed() {
		redBase = new ArrayList<Node>();
		redBase.add(new Node(130,725,0));
		redBase.add(new Node(70,770,1));
		redBase.add(new Node(190,770,2));
		redBase.add(new Node(95,840,3));
		redBase.add(new Node(170,840,4));
		for (Node node : redBase) {
			allNodes.add(node);
			board.drawNode(node);
		}
		board.repaint();
	}
	
	public void fillRedBase() {
		for (Node node : getRedBase()) {
			node.addPlayer(1);
		}
	}
	
	public ArrayList<Node> getRedBase() {
		return redBase;
	}

	public void resetRed() {
		for (Node node : getRedBase()) {
			node.removePlayer();
		}
	}
	
	private void initGreen() {
		greenBase = new ArrayList<Node>();
		greenBase.add(new Node(330,725,5));
		greenBase.add(new Node(270,770,6));
		greenBase.add(new Node(390,770,7));
		greenBase.add(new Node(295,840,8));
		greenBase.add(new Node(370,840,9));
		for (Node node : greenBase) {
			allNodes.add(node);
			board.drawNode(node);
		}
		board.repaint();
	}
	
	public void fillGreenBase() {
		for (Node node : getGreenBase()) {
			node.addPlayer(2);
		}
	}
	
	public ArrayList<Node> getGreenBase() {
		return greenBase;
	}

	public void resetGreen() {
		for (Node node : getGreenBase()) {
			node.removePlayer();
		}
	}
	
	private void initYellow() {
		yellowBase = new ArrayList<Node>();
		yellowBase.add(new Node(530,725,10));
		yellowBase.add(new Node(470,770,11));
		yellowBase.add(new Node(590,770,12));
		yellowBase.add(new Node(495,840,13));
		yellowBase.add(new Node(570,840,14));
		for (Node node : yellowBase) {
			allNodes.add(node);
			board.drawNode(node);
		}
		board.repaint();
	}
	
	public void fillYellowBase() {
		for (Node node : getYellowBase()) {
			node.addPlayer(3);
		}
	}
	
	public ArrayList<Node> getYellowBase() {
		return yellowBase;
	}

	public void resetYellow() {
		for (Node node : getYellowBase()) {
			node.removePlayer();
		}
	}
	
	private void initBlue() {
		blueBase = new ArrayList<Node>();
		blueBase.add(new Node(730,725,15));
		blueBase.add(new Node(670,770,16));
		blueBase.add(new Node(790,770,17));
		blueBase.add(new Node(695,840,18));
		blueBase.add(new Node(770,840,19));
		for (Node node : blueBase) {
			allNodes.add(node);
			board.drawNode(node);
		}
		board.repaint();
	}
	
	public void fillBlueBase() {
		for (Node node : getBlueBase()) {
			node.addPlayer(4);
		}
	}
	
	public void resetBlue() {
		for (Node node : getBlueBase()) {
			node.removePlayer();
		}
	}
	
	public ArrayList<Node> getBlueBase() {
		return blueBase;
	}
	
	public ArrayList<Node> getAllNodes() {
		return allNodes;
	}
	
	/**
	 * Returns all the nodes for possible movement of the token
	 * @param range determined by the result of the dice
	 * @return Nodes the selected token can be moved to
	 */
	public ArrayList<Node> getPossibleSteps(int range) {
		return null;
	}
}
