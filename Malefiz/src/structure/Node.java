package structure;

import gui.Board;

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
	
	// Occupied by player (0 for no player)
	private int player;
	private ArrayList<Node> neighbours;
	
	public Node(int x, int y, int id) {
		this.x = x;
		this.y = y;
		blocked = false;
		player = 0;
		this.id = id;
		this.setBounds(x,y,Board.TOKENWIDTH, Board.TOKENHEIGHT);
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
		else blocked = true;
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
}
