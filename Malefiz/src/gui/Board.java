package gui;

import game.PlayerChanger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import structure.BoardStructure;
import structure.Node;

public class Board {

	private JFrame frame;
	private JLayeredPane boardPane;
	private JButton diceButton;
	private JButton restart;
	private JButton startGame;
	private JLabel diceResult;
	
	private JComboBox<String> playerOneSelection;
	private JComboBox<String> playerTwoSelection;
	private JComboBox<String> playerThreeSelection;
	private JComboBox<String> playerFourSelection;
	public static final int TOKENHEIGHT = 51;
	public static final int TOKENWIDTH = 39;
	
	// phases of the game
	// Can tokens be selected for moving at this point?
	private boolean isTokenSelectionPhase;
	
	// Can tokens be moved at this point?
	private boolean isMoveSelectionPhase;
	
	// Can stones be moved at this point
	private boolean isStoneMovingPhase;
	
	
	private PlayerChanger playerChanger;
	
	// Moves currently possible
	private ArrayList<Node> possibleMoves;
	
	// Token currently selected
	private Node selectedNode;
	// result of the current dice roll
	private int diceNumber = 1;
	
	// displays the currently active player
	private JLabel activePlayer2;

	// ArrayList to save labels of tokens for visual updates
	private ArrayList<JLabel> redTokens;
	private ArrayList<JLabel> greenTokens;
	private ArrayList<JLabel> yellowTokens;
	private ArrayList<JLabel> blueTokens;
	
	// Active players
	private ArrayList<Boolean> activePlayers;
	
	private BoardStructure structure;
	
	// Node currently selected
	private Node currentNode;
	
	// Nodes that can be selected for stone moving
	private ArrayList<Node> possibleStoneMoves;
	
	public Board() {

		// initialize phases
		isTokenSelectionPhase = false;
		isMoveSelectionPhase = false;
		isStoneMovingPhase = false;

		
		redTokens = new ArrayList<JLabel>();
		greenTokens = new ArrayList<JLabel>();
		yellowTokens = new ArrayList<JLabel>();
		blueTokens = new ArrayList<JLabel>();
		
		frame = new JFrame("Malefiz");
		frame.setLayout(new BorderLayout());
		frame.setSize(1500,950);
		frame.setDefaultCloseOperation(2);
		
		// Malefiz board image
		ImageIcon boardIimage = new ImageIcon("images\\board.jpg");
		JLabel boardLabel = new JLabel(boardIimage);
		boardLabel.setBounds(0,0,900,900);
		
		// Malefiz board
		boardPane = new JLayeredPane();
		boardPane.setPreferredSize(new Dimension(900,900));
		boardPane.setLayout(null);
		boardPane.add(boardLabel,JLayeredPane.DEFAULT_LAYER);
		boardPane.addMouseListener(new BoardListener(this,playerChanger));

		// Initialize board structure
		structure = new BoardStructure(this);
		structure.initializeStructure();
		
		// Initialize player changer
		playerChanger = new PlayerChanger(this,structure);
				
		// Info area
		JPanel gameInfo = new JPanel();
		gameInfo.setLayout(new GridLayout(4,1));
		
		// Player selection
		JPanel playerSelection = new JPanel();
		playerSelection.setLayout(new GridLayout(5,2));
		
		// Player one
		JLabel playerOne = new JLabel("Player one");
		playerOne.setFont(new Font("Arial",Font.PLAIN,20));
		playerOne.setForeground(Color.RED);
		playerSelection.add(playerOne);
		
		playerOneSelection = new JComboBox<String>();
		playerOneSelection.addItem("disabled");
		playerOneSelection.addItem("Human player");
		playerOneSelection.addItem("AI player");
		playerOneSelection.setFont(new Font("Arial",Font.PLAIN,20));
		playerSelection.add(playerOneSelection);
		
		// Player two
		JLabel playerTwo = new JLabel("Player two");
		playerTwo.setFont(new Font("Arial",Font.PLAIN,20));
		playerTwo.setForeground(Color.GREEN);
		playerSelection.add(playerTwo);
		
		playerTwoSelection = new JComboBox<String>();
		playerTwoSelection.addItem("disabled");
		playerTwoSelection.addItem("Human player");
		playerTwoSelection.addItem("AI player");
		playerTwoSelection.setFont(new Font("Arial",Font.PLAIN,20));
		playerSelection.add(playerTwoSelection);
		
		// Player three
		JLabel playerThree = new JLabel("Player three");
		playerThree.setFont(new Font("Arial",Font.PLAIN,20));
		playerThree.setForeground(Color.YELLOW);
		playerSelection.add(playerThree);
		
		playerThreeSelection = new JComboBox<String>();
		playerThreeSelection.addItem("disabled");
		playerThreeSelection.addItem("Human player");
		playerThreeSelection.addItem("AI player");
		playerThreeSelection.setFont(new Font("Arial",Font.PLAIN,20));
		playerSelection.add(playerThreeSelection);
		
		// Player four
		JLabel playerFour = new JLabel("Player four");
		playerFour.setFont(new Font("Arial",Font.PLAIN,20));
		playerFour.setForeground(Color.BLUE);
		playerSelection.add(playerFour);
		
		playerFourSelection = new JComboBox<String>();
		playerFourSelection.addItem("disabled");
		playerFourSelection.addItem("Human player");
		playerFourSelection.addItem("AI player");
		playerFourSelection.setFont(new Font("Arial",Font.PLAIN,20));
		playerSelection.add(playerFourSelection);
		
		// Start with current selection
		startGame = new JButton("Start game");
		startGame.setFont(new Font("Arial",Font.PLAIN,20));
		startGame.addActionListener(new PlayerSelectionListener(playerOneSelection, playerTwoSelection, playerThreeSelection, playerFourSelection, this, structure));
		playerSelection.add(startGame);
		
		gameInfo.add(playerSelection);
		
		// Active player
		JPanel activePlayerPanel = new JPanel();
		activePlayerPanel.setLayout(new GridLayout(1,2));
		
		JLabel activePlayer1 = new JLabel("Active player:");
		activePlayer1.setFont(new Font("Arial",Font.PLAIN,20));
		activePlayerPanel.add(activePlayer1);
		
		activePlayer2 = new JLabel();
		activePlayer2.setFont(new Font("Arial",Font.PLAIN,20));
		activePlayerPanel.add(activePlayer2);
		
		gameInfo.add(activePlayerPanel);
		
		// Dice
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new GridLayout(1,2));
		
		ImageIcon diceImage = new ImageIcon("images\\dice.jpg");
		diceButton = new JButton(diceImage);
		diceButton.setEnabled(false);
		diceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				roll();
			};
		});
		dicePanel.add(diceButton);
		
		// Dice result
		diceResult = new JLabel();
		diceResult.setFont(new Font("Arial",Font.PLAIN,20));
		dicePanel.add(diceResult);
		
		gameInfo.add(dicePanel);
		
		// Game settings
		JPanel gameSettingsPanel = new JPanel();
		diceResult.setFont(new Font("Arial",Font.PLAIN,20));
		gameSettingsPanel.setLayout(new GridLayout(1,1));
		
		// Restart 
		restart = new JButton("Restart");
		restart.setFont(new Font("Arial",Font.PLAIN,20));
		restart.setEnabled(false);
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				restart();
			};
		});
		gameSettingsPanel.add(restart);
		
		gameInfo.add(gameSettingsPanel);
		
		frame.add(BorderLayout.WEST,boardPane);
		frame.add(BorderLayout.EAST,gameInfo);
		frame.setVisible(true);
	}
	
	public void drawNode(Node node) {
		boardPane.add(node, JLayeredPane.PALETTE_LAYER);
	}
	
	/** public JLabel drawToken(int x, int y, int player) {
		ImageIcon image = null;
		if (player == 1) image = new ImageIcon("images\\token red.png");
		else if (player == 2) image = new ImageIcon("images\\token green.png");
		else if (player == 3) image = new ImageIcon("images\\token yellow.png");
		else image = new ImageIcon("images\\token blue.png");
		JLabel token = new JLabel(image);
		token.setBounds(x,y,TOKENWIDTH,TOKENHEIGHT);
		//token.setOpaque(true);
		//token.setBackground(Color.RED);
		boardPane.add(token, JLayeredPane.PALETTE_LAYER);
		if (player == 1) redTokens.add(token);
		if (player == 2) greenTokens.add(token);
		if (player == 3) yellowTokens.add(token);
		if (player == 4) blueTokens.add(token);
		return token;
	} */
	
	
	public void startGame() {
		startGame.setEnabled(false);
		diceButton.setEnabled(true);
		restart.setEnabled(true);
		playerOneSelection.setEnabled(false);
		playerTwoSelection.setEnabled(false);
		playerThreeSelection.setEnabled(false);
		playerFourSelection.setEnabled(false);
		playerChanger = new PlayerChanger(this,structure);
		playerChanger.changePlayer();
	}
	
	public void restart() {
		startGame.setEnabled(true);
		diceButton.setEnabled(false);
		restart.setEnabled(false);
		playerOneSelection.setEnabled(true);
		playerTwoSelection.setEnabled(true);
		playerThreeSelection.setEnabled(true);
		playerFourSelection.setEnabled(true);
		diceResult.setText("");
		activePlayer2.setText("");
		setActivePlayers(null);
		structure.resetField();
		boardPane.validate();
		boardPane.repaint();
	}
	
	public void roll() {
		Random random = new Random();
	 	diceNumber = (int) Math.ceil(random.nextInt(6))+1;
		diceResult.setText("You rolled: " + diceNumber);
		deactivateDice();
		isTokenSelectionPhase = true;
		
	}
	
	public void repaint() {
		boardPane.repaint();
		frame.repaint();
	}
	
	public void setActivePlayers(ArrayList<Boolean> activePlayers) {
		this.activePlayers = activePlayers;
	}
	
	public ArrayList<Boolean> getActivePlayers() {
		return activePlayers;
	}
	
	public void setActivePlayer(int player) {
		if (player == 1) {
			activePlayer2.setText("Player one");
			activePlayer2.setForeground(Color.RED);
		}
		else if (player == 2) {
			activePlayer2.setText("Player two");
			activePlayer2.setForeground(Color.GREEN);
		}
		else if (player == 3) {
			activePlayer2.setText("Player three");
			activePlayer2.setForeground(Color.YELLOW);
		}
		else {
			activePlayer2.setText("Player four");
			activePlayer2.setForeground(Color.BLUE);
		}
	}
	
	public boolean isTokenSelectionPhase() {
		return isTokenSelectionPhase;
	}
	
	public boolean isMoveSelectionPhase() {
		return isMoveSelectionPhase;
	}
	
	public boolean isStoneMovingPhase() {
		return isStoneMovingPhase;
	}
	
	public void endMoveSelectionPhase() {
		isMoveSelectionPhase = false;
		for (Node node : possibleMoves) {
			node.undoSelectable();
		}
	}
	
	private void beginMoveSelectionPhase() {
		isMoveSelectionPhase = true;
	}
	
	private void beginStoneMovingPhase() {
		isStoneMovingPhase = true;
		possibleStoneMoves = structure.showStoneMoves();
	}
	
	public void unselectToken() {
		isTokenSelectionPhase = true;
		endMoveSelectionPhase();	
	}
	
	/**
	 * 
	 * @param point destination node selected by user
	 * @return false if stone could not be moved, true if stone was successfully moved
	 */
	public boolean moveStone(Point point) {
		currentNode = structure.getClickedNode(point.x,point.y);
		if (possibleStoneMoves.contains(currentNode)) {
			currentNode.block();
			return true;
		}
		return false;
	}
	
	public void endStoneMovingPhase() {
		isStoneMovingPhase = false;
		for (Node node : possibleStoneMoves) {
			node.undoSelectable();
		}
		playerChanger.changePlayer();
	}
	
	public void endTokenSelectionPhase() {
		isTokenSelectionPhase = false;
		beginMoveSelectionPhase();
	}
	
	public void activateDice() {
		diceButton.setEnabled(true);
	}
	
	public void deactivateDice() {
		diceButton.setEnabled(false);
	}
	
	/**
	 * 
	 * @param point token node selected by user
	 * @return false if no valid token was selected, true if a valid token was selected
	 */
	public boolean showMoves(Point point) {
		selectedNode = structure.getClickedNode(point.x, point.y);
		if (selectedNode == null) return false;
		else if (selectedNode.getPlayer() != playerChanger.getActivePlayer()) return false;
		possibleMoves = structure.getPossibleSteps(diceNumber, selectedNode);
		for (Node node : possibleMoves) {
			node.setSelectable();
		}
		return true;
	}
	
	/**
	 * 
	 * @param point destination point clicked on by the user
	 * @return false if no move could be done, true if token was successfully moved
	 */
	public boolean moveToken(Point point) {
		currentNode = structure.getClickedNode(point.x, point.y);
		if (currentNode == null) return false;
		if (structure.moveToken(currentNode,possibleMoves,selectedNode)) {
			if (currentNode.isBlocked()) {
				currentNode.unblock();
				beginStoneMovingPhase();
			}
			else playerChanger.changePlayer();
			return true;
		}
		return false;
	}
	
	public void nextPlayer() {
		playerChanger.changePlayer();
	}
	
	public int getDiceResult() {
		return diceNumber;
	}
}
