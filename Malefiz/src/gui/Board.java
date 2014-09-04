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
	
	public Board() {

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

		// Initialize board structure
		structure = new BoardStructure(this);
		structure.initializeStructure();
				
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
		PlayerChanger playerChanger = new PlayerChanger(this,structure);
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
		structure.resetRed();
		structure.resetGreen();
		structure.resetYellow();
		structure.resetBlue();
		
		// Reset player bases
		boardPane.validate();
		boardPane.repaint();
	}
	
	public int roll() {
		Random random = new Random();
		int result = (int) Math.ceil(random.nextInt(6))+1;
		diceResult.setText("You rolled: " + result);
		return result;
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
}
