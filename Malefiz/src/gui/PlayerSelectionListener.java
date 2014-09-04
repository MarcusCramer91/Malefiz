package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import structure.BoardStructure;
import structure.Node;
public class PlayerSelectionListener implements ActionListener {

	private JComboBox red;
	private JComboBox green;
	private JComboBox yellow;
	private JComboBox blue;
	private Board board; 
	private BoardStructure structure;
	
	public PlayerSelectionListener(JComboBox red, JComboBox green, JComboBox yellow, JComboBox blue, Board board, BoardStructure structure) {
		this.red = red;
		this.green = green;
		this.yellow = yellow;
		this.blue = blue;
		this.board = board;
		this.structure = structure;
	}
	public void actionPerformed(ActionEvent event) {
		
		// Fill the bases of the players
		if (red.getSelectedIndex() != 0) setUpRed();
		if (green.getSelectedIndex() != 0) setUpGreen();
		if (yellow.getSelectedIndex() != 0) setUpYellow();
		if (blue.getSelectedIndex() != 0) setUpBlue();
		ArrayList<Boolean> activePlayers = new ArrayList<Boolean>();
		
		// Tell the board which players are active
		if (red.getSelectedIndex() != 0) activePlayers.add(true);
		else activePlayers.add(false);
		if (green.getSelectedIndex() != 0) activePlayers.add(true);
		else activePlayers.add(false);
		if (yellow.getSelectedIndex() != 0) activePlayers.add(true);
		else activePlayers.add(false);
		if (blue.getSelectedIndex() != 0) activePlayers.add(true);
		else activePlayers.add(false);
		board.setActivePlayers(activePlayers);
		board.startGame();
	}

	private void setUpRed() {
		structure.fillRedBase();
	}
	
	private void setUpGreen() {
		structure.fillGreenBase();
	}
	
	private void setUpYellow() {
		structure.fillYellowBase();
	}
	
	private void setUpBlue() {
		structure.fillBlueBase();
	}
}
