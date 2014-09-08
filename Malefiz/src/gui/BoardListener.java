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
		if (board.isTokenSelectionPhase()) {
			board.showMoves(event.getLocationOnScreen());
			board.endTokenSelectionPhase();
			board.beginMoveSelectionPhase();
		}
		else if (board.isMoveSelectionPhase()) {
			board.endMoveSelectionPhase();
			board.moveToken(event.getLocationOnScreen());
			board.nextPlayer();
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
