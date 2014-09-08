package structure;

import gui.Board;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Node extends JLabel {
	
	private int x;
	private int y;
	
	// ID for matching to visual JLabel objects
	private int id;
	
	// Blocked by stone?
	private boolean blocked;
	
	// Can be blocked by stone?
	private boolean blockable;
	
	// Occupied by player (0 for no player)
	private int player;
	private ArrayList<Node> neighbours;
	
	public Node(int x, int y, int id, boolean blockable) {
		this.x = x;
		this.y = y;
		blocked = false;
		this.blockable = blockable;
		player = 0;
		this.id = id;
		this.setBounds(x,y,Board.TOKENWIDTH, Board.TOKENHEIGHT);
		neighbours = new ArrayList<Node>();
	}

	public int getID() {
		return id;
	}
	public void addNeighbours(ArrayList<Node> neighbours) {
		this.neighbours = neighbours;
	}
	
	public void addNeighbour(Node neighbour) {
		neighbours.add(neighbour);
	}
	
	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}
	
	public void addPlayer(int player) {
		if (this.player != 0) System.out.println("Node already occupied by a player");
		else {
			this.player = player;
			ImageIcon image = null;
			if (player == 1) image = new ImageIcon("images\\token red.png");
			else if (player == 2) image = new ImageIcon("images\\token green.png");
			else if (player == 3) image = new ImageIcon("images\\token yellow.png");
			else image = new ImageIcon("images\\token blue.png");
			super.setIcon(image);
		}
	}
	
	public void removePlayer() {
		if (player == 0) System.out.println("Node not occupied by a player");
		else {
			this.player = 0;
			super.setIcon(null);
		}
	}
	
	public void block() {
		if (blocked) System.out.println("Node already blocked");
		else {
			blocked = true;
			ImageIcon image = new ImageIcon("images\\stone.png");
			super.setIcon(image);
		}
	}
	
	public void unblock() {
		if (!blocked) System.out.println("Node not blocked");
		else blocked = false;
	}
	
	public int getPlayer() {
		return player;
	}
	
	public boolean isBlocked() {
		return blocked;
	}
	
	public Point getCoordinates() {
		return new Point(x,y);
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isBlockable() {
		return blockable;
	}
	
	public void setSelectable() {
		if (player == 0 && blocked == false) {
			ImageIcon image = new ImageIcon("images\\selectable.png");
			super.setIcon(image);
		}
		else if (player != 0 && blocked == false) {
			ImageIcon image = null;
			if (player == 1) image = new ImageIcon("images\\token red selectable.png");
			else if (player == 2) image = new ImageIcon("images\\token green selectable.png");
			else if (player == 3) image = new ImageIcon("images\\token yellow selectable.png");
			else image = new ImageIcon("images\\token blue selectable.png");
			super.setIcon(image);	
		}
		else {
			ImageIcon image = new ImageIcon("images\\selectable stone.png");
			super.setIcon(image);
		}
	}
	
	public void undoSelectable() {
		ImageIcon image = null;
		if (player == 1) image = new ImageIcon("images\\token red.png");
		else if (player == 2) image = new ImageIcon("images\\token green.png");
		else if (player == 3) image = new ImageIcon("images\\token yellow.png");
		else if (player == 4) image = new ImageIcon("images\\token blue.png");
		else if (blocked) image = new ImageIcon("images\\stone.png");
		super.setIcon(image);
	}
}
