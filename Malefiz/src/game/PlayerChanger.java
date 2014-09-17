package game;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import structure.BoardStructure;
import structure.Node;
import gui.Board;

public class PlayerChanger {
	
	private Board board;
	private int activePlayer;
	private ArrayList<Boolean> activePlayers;
	private BoardStructure structure;
	private TokenManager tokenManager;
	
	public PlayerChanger(Board board, BoardStructure structure) {
		this.board = board;
		this.structure = structure;
		activePlayer = 4;
		tokenManager = new TokenManager(structure);
	}
	
	public void changePlayer() {
		activePlayers = board.getActivePlayers();
		deactivatePlayer(activePlayer);
		activePlayer++;
		if (activePlayer > 4) activePlayer = 1;
		if (activePlayers.get(activePlayer-1) == false) {
			changePlayer();
			return;
		}
		activatePlayer(activePlayer);
		/**
		// check if player can perform a move
		boolean hasMoves = false; 
		if (activePlayer == 1) {
			ArrayList<Node> redTokens = tokenManager.getRedNodes();
			int range = board.getDiceResult();
			for (Node node : redTokens) {
				ArrayList<Node> possibleSteps = structure.getPossibleSteps(range, node);
				if (possibleSteps.size() > 0) {
					hasMoves = true; 
					continue;
				}
			}
		}
		else if (activePlayer == 2) {
			ArrayList<Node> greenTokens = tokenManager.getGreenNodes();
			int range = board.getDiceResult();
			for (Node node : greenTokens) {
				ArrayList<Node> possibleSteps = structure.getPossibleSteps(range, node);
				if (possibleSteps.size() > 0) {
					hasMoves = true; 
					continue;
				}
			}
		}
		else if (activePlayer == 3) {
			ArrayList<Node> yellowTokens = tokenManager.getYellowNodes();
			int range = board.getDiceResult();
			for (Node node : yellowTokens) {
				ArrayList<Node> possibleSteps = structure.getPossibleSteps(range, node);
				if (possibleSteps.size() > 0) {
					hasMoves = true; 
					continue;
				}
			}
		}
		else {
			ArrayList<Node> blueTokens = tokenManager.getBlueNodes();
			int range = board.getDiceResult();
			for (Node node : blueTokens) {
				ArrayList<Node> possibleSteps = structure.getPossibleSteps(range, node);
				if (possibleSteps.size() > 0) {
					hasMoves = true; 
					continue;
				}
			}
		}
		
		// if no moves possible
		if (!hasMoves) {
			System.out.println("No moves possible for player " + activePlayer +". Next player's turn.");
			changePlayer();
		} */
		board.activateDice();
	}
	
	public int getActivePlayer() {
		return activePlayer;
	}
	
	public TokenManager getTokenManager() {
		return tokenManager;
	}
	public void activatePlayer(int player) {
		board.setActivePlayer(player);
		if (player == 1) {
			ArrayList<Node> red = tokenManager.getRedNodes();
			ImageIcon image = new ImageIcon("images\\token red selectable.png");
			for (Node node : red) {
				node.setIcon(image);
			}
		}
		else if (player == 2) { 
			ArrayList<Node> green = tokenManager.getGreenNodes();
			ImageIcon image = new ImageIcon("images\\token green selectable.png");
			for (Node node : green) {
				node.setIcon(image);
			}
		}
		else if (player == 3) {
			ArrayList<Node> yellow = tokenManager.getYellowNodes();
			ImageIcon image = new ImageIcon("images\\token yellow selectable.png");
			for (Node node : yellow) {
				node.setIcon(image);
			}
		}
		else {
			ArrayList<Node> blue = tokenManager.getBlueNodes();
			ImageIcon image = new ImageIcon("images\\token blue selectable.png");
			for (Node node : blue) {
				node.setIcon(image);
			}
		}
		board.repaint();
	}
	
	private void deactivatePlayer(int player) {
		if (player == 1) {
			ArrayList<Node> red = tokenManager.getRedNodes();
			ImageIcon image = new ImageIcon("images\\token red.png");
			for (Node node : red) {
				node.setIcon(image);
			}
		}
		else if (player == 2) { 
			ArrayList<Node> green = tokenManager.getGreenNodes();
			ImageIcon image = new ImageIcon("images\\token green.png");
			for (Node node : green) {
				node.setIcon(image);
			}
		}
		else if (player == 3) {
			ArrayList<Node> yellow = tokenManager.getYellowNodes();
			ImageIcon image = new ImageIcon("images\\token yellow.png");
			for (Node node : yellow) {
				node.setIcon(image);
			}
		}
		else {
			ArrayList<Node> blue = tokenManager.getBlueNodes();
			ImageIcon image = new ImageIcon("images\\token blue.png");
			for (Node node : blue) {
				node.setIcon(image);
			}
		}
	}
}
