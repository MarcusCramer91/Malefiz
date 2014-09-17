package gui;

import game.PlayerChanger;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;




import structure.Node;

public class BoardListener implements MouseListener {

	private Board board;
	private PlayerChanger playerChanger;
	public BoardListener(Board board, PlayerChanger playerChanger) {
		this.board = board;
		this.playerChanger = playerChanger;
	}
	public void mouseClicked(MouseEvent event) {
		// left mouse click triggers selection
		if (event.getButton() == MouseEvent.BUTTON1) {
			if (board.isTokenSelectionPhase()) {
				if (board.showMoves(event.getLocationOnScreen())) {
					board.endTokenSelectionPhase();
				}
			}
			else if (board.isMoveSelectionPhase()) {
				if (board.moveToken(event.getLocationOnScreen())) {
					board.endMoveSelectionPhase();
				}
			}
			else if (board.isStoneMovingPhase()) {
				if (board.moveStone(event.getLocationOnScreen())) {
					board.endStoneMovingPhase();
				}			
			}	
		}
		// right mouse click triggers deselection
		else if (event.getButton() == MouseEvent.BUTTON3) {
			if (board.isMoveSelectionPhase()) {
				board.unselectToken();
			}
		}
	}

	public void mouseEntered(MouseEvent event) {
		
	}

	public void mouseExited(MouseEvent event) {
		
	}

	public void mousePressed(MouseEvent event) {
		
	}

	public void mouseReleased(MouseEvent event) {
		
	}
}
