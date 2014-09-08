package structure;

import gui.Board;

import java.awt.Point;
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
	
	// Array list for search for selectable nodes
	private ArrayList<Node> alreadyVisited;
	
	// The board
	private Board board;
	
	public BoardStructure(Board board) {
		this.board = board;
		allNodes = new ArrayList<Node>();
	}
	
	public void initializeStructure() {
		
		// Creates all nodes
		initBoard();
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
	
	public void resetField() {
		for (Node node : allNodes) {
			node.removePlayer();
			node.unblock();
			node.undoSelectable();
			if (node.getID() == 300 || 
				node.getID() == 304 ||
				node.getID() == 308 ||
				node.getID() == 312 ||
				node.getID() == 316 ||
				node.getID() == 702 ||
				node.getID() == 706 ||
				node.getID() == 902 ||
				node.getID() == 1000 ||
				node.getID() == 1108 ||
				node.getID() == 1308) { 
				node.block();
			}
		}
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
	public ArrayList<Node> getPossibleSteps(int range, Node startNode) {
		alreadyVisited = new ArrayList<Node>();
		return getPossibleSteps2(range, startNode, startNode.getPlayer());
	}
	
	private ArrayList<Node> getPossibleSteps2(int range, Node startNode, int player) {
		alreadyVisited.add(startNode);
		if (range == 0) {
			ArrayList<Node> nodes = new ArrayList<Node>();
			if(startNode.getPlayer() != player) nodes.add(startNode);
			return nodes;
		}
		else {
			ArrayList<Node> nodes = new ArrayList<Node>();
			if (!startNode.isBlocked()) {
				for(Node node : startNode.getNeighbours()) {
					if (!alreadyVisited.contains(node)) {
						nodes.addAll(getPossibleSteps2(range-1,node,player));							
					}		
				}
			}
			return nodes;
		}
	}
	
	public void moveToken(Point point, ArrayList<Node> possibleMoves, Node selectedNode) {
		Node selectedMove = getClickedNode(point.x,point.y);
		if (possibleMoves.contains(selectedMove)) {
			selectedMove.addPlayer(selectedNode.getPlayer());
			selectedNode.removePlayer();
		}
	}
	
	public Node getClickedNode(int x, int y) {
		y = y - 30;
		for (Node node : allNodes) {
			if (node.getX() < x && (node.getX() + Board.TOKENWIDTH) > x) {
				if (node.getY() < y && (node.getY() + Board.TOKENHEIGHT) > y) {
					return node;
				}
			}
		}
		return null;
	}
	

	private void initBoard() {
		
		// first row
		Node node100 = new Node(31,675,100,false);
		allNodes.add(node100);
		Node node101 = new Node(81,675,101,false);
		allNodes.add(node101);
		Node node102 = new Node(131,675,102,false);
		allNodes.add(node102);
		Node node103 = new Node(181,675,103,false);
		allNodes.add(node103);
		Node node104 = new Node(232,675,104,false);
		allNodes.add(node104);
		Node node105 = new Node(282,675,105,false);
		allNodes.add(node105);
		Node node106 = new Node(332,675,106,false);
		allNodes.add(node106);
		Node node107 = new Node(382,675,107,false);
		allNodes.add(node107);
		Node node108 = new Node(433,675,108,false);
		allNodes.add(node108);
		Node node109 = new Node(483,675,109,false);
		allNodes.add(node109);
		Node node110 = new Node(533,675,110,false);
		allNodes.add(node110);
		Node node111 = new Node(583,675,111,false);
		allNodes.add(node111);
		Node node112 = new Node(634,675,112,false);
		allNodes.add(node112);
		Node node113 = new Node(684,675,113,false);
		allNodes.add(node113);
		Node node114 = new Node(734,675,114,false);
		allNodes.add(node114);
		Node node115 = new Node(784,675,115,false);
		allNodes.add(node115);
		Node node116 = new Node(835,675,116,false);
		allNodes.add(node116);
		
		// second row
		Node node200 = new Node(31,625,200,false);
		allNodes.add(node200);
		Node node201 = new Node(232,625,201,false);
		allNodes.add(node201);
		Node node202 = new Node(433,625,202,false);
		allNodes.add(node202);
		Node node203 = new Node(634,625,203,false);
		allNodes.add(node203);
		Node node204 = new Node(835,625,204,false);
		allNodes.add(node204);
		
		
		// third row
		Node node300 = new Node(31,575,300,true);
		node300.block();
		allNodes.add(node300);
		Node node301 = new Node(81,575,301,true);
		allNodes.add(node301);
		Node node302 = new Node(131,575,302,true);
		allNodes.add(node302);
		Node node303 = new Node(181,575,303,true);
		allNodes.add(node303);
		Node node304 = new Node(232,575,304,true);
		node304.block();
		allNodes.add(node304);
		Node node305 = new Node(282,575,305,true);
		allNodes.add(node305);
		Node node306 = new Node(332,575,306,true);
		allNodes.add(node306);
		Node node307 = new Node(382,575,307,true);
		allNodes.add(node307);
		Node node308 = new Node(433,575,308,true);
		node308.block();
		allNodes.add(node308);
		Node node309 = new Node(483,575,309,true);
		allNodes.add(node309);
		Node node310 = new Node(533,575,310,true);
		allNodes.add(node310);
		Node node311 = new Node(583,575,311,true);
		allNodes.add(node311);
		Node node312 = new Node(634,575,312,true);
		node312.block();
		allNodes.add(node312);
		Node node313 = new Node(684,575,313,true);
		allNodes.add(node313);
		Node node314 = new Node(734,575,314,true);
		allNodes.add(node314);
		Node node315 = new Node(784,575,315,true);
		allNodes.add(node315);
		Node node316 = new Node(835,575,316,true);
		allNodes.add(node316);	
		node316.block();
		
		// fourth row
		Node node400 = new Node(131,525,400,true);
		allNodes.add(node400);
		Node node401 = new Node(332,525,401,true);
		allNodes.add(node401);
		Node node402 = new Node(533,525,402,true);
		allNodes.add(node402);
		Node node403 = new Node(734,525,403,true);
		allNodes.add(node403);
		
		// fifth row
		Node node500 = new Node(131,475,500,true);
		allNodes.add(node500);
		Node node501 = new Node(181,475,501,true);
		allNodes.add(node501);
		Node node502 = new Node(232,475,502,true);
		allNodes.add(node502);
		Node node503 = new Node(282,475,503,true);
		allNodes.add(node503);
		Node node504 = new Node(332,475,504,true);
		allNodes.add(node504);
		Node node505 = new Node(382,475,505,true);
		allNodes.add(node505);
		Node node506 = new Node(433,475,506,true);
		allNodes.add(node506);
		Node node507 = new Node(483,475,507,true);
		allNodes.add(node507);
		Node node508 = new Node(533,475,508,true);
		allNodes.add(node508);
		Node node509 = new Node(583,475,509,true);
		allNodes.add(node509);
		Node node510 = new Node(634,475,510,true);
		allNodes.add(node510);
		Node node511 = new Node(684,475,511,true);
		allNodes.add(node511);
		Node node512 = new Node(734,475,512,true);
		allNodes.add(node512);
		
		// sixth row
		Node node600 = new Node(232,425,600,true);
		allNodes.add(node600);
		Node node601 = new Node(634,425,600,true);
		allNodes.add(node601);
		
		// seventh row
		Node node700 = new Node(232,375,700,true);
		allNodes.add(node700);
		Node node701 = new Node(282,375,701,true);
		allNodes.add(node701);
		Node node702 = new Node(332,375,702,true);
		node702.block();
		allNodes.add(node702);
		Node node703 = new Node(382,375,703,true);
		allNodes.add(node703);
		Node node704 = new Node(433,375,704,true);
		allNodes.add(node704);
		Node node705 = new Node(483,375,705,true);
		allNodes.add(node705);
		Node node706 = new Node(533,375,706,true);
		node706.block();
		allNodes.add(node706);
		Node node707 = new Node(583,375,707,true);
		allNodes.add(node707);
		Node node708 = new Node(634,375,708,true);
		allNodes.add(node708);
		
		// eighth row
		Node node800 = new Node(332,325,800,true);
		allNodes.add(node800);
		Node node801 = new Node(533,325,801,true);
		allNodes.add(node801);
		
		// ninth row
		Node node900 = new Node(332,275,900,true);
		allNodes.add(node900);
		Node node901 = new Node(382,275,901,true);
		allNodes.add(node901);
		Node node902 = new Node(433,275,902,true);
		node902.block();
		allNodes.add(node902);
		Node node903 = new Node(483,275,903,true);
		allNodes.add(node903);
		Node node904 = new Node(533,275,904,true);
		allNodes.add(node904);
		
		// tenth row
		Node node1000 = new Node(433,225,1000,true);
		node1000.block();
		allNodes.add(node1000);
		
		// eleventh row
		Node node1100 = new Node(31,175,1100,true);
		allNodes.add(node1100);
		Node node1101 = new Node(81,175,1101,true);
		allNodes.add(node1101);
		Node node1102 = new Node(131,175,1102,true);
		allNodes.add(node1102);
		Node node1103 = new Node(181,175,1103,true);
		allNodes.add(node1103);
		Node node1104 = new Node(232,175,1104,true);
		allNodes.add(node1104);
		Node node1105 = new Node(282,175,1105,true);
		allNodes.add(node1105);
		Node node1106 = new Node(332,175,1106,true);
		allNodes.add(node1106);
		Node node1107 = new Node(382,175,1107,true);
		allNodes.add(node1107);
		Node node1108 = new Node(433,175,1108,true);
		node1108.block();
		allNodes.add(node1108);
		Node node1109 = new Node(483,175,1109,true);
		allNodes.add(node1109);
		Node node1110 = new Node(533,175,1110,true);
		allNodes.add(node1110);
		Node node1111 = new Node(583,175,1111,true);
		allNodes.add(node1111);
		Node node1112 = new Node(634,175,1112,true);
		allNodes.add(node1112);
		Node node1113 = new Node(684,175,1113,true);
		allNodes.add(node1113);
		Node node1114 = new Node(734,175,1114,true);
		allNodes.add(node1114);
		Node node1115 = new Node(784,175,1115,true);
		allNodes.add(node1115);
		Node node1116 = new Node(835,175,1116,true);
		allNodes.add(node1116);	
		
		// twelvth row
		Node node1200 = new Node(31,125,1200,true);
		allNodes.add(node1200);
		Node node1201 = new Node(835,125,1216,true);
		allNodes.add(node1201);	
		
		// thirteenth row
		Node node1300 = new Node(31,75,1300,true);
		allNodes.add(node1300);
		Node node1301 = new Node(81,75,1301,true);
		allNodes.add(node1301);
		Node node1302 = new Node(131,75,1302,true);
		allNodes.add(node1302);
		Node node1303 = new Node(181,75,1303,true);
		allNodes.add(node1303);
		Node node1304 = new Node(232,75,1304,true);
		allNodes.add(node1304);
		Node node1305 = new Node(282,75,1305,true);
		allNodes.add(node1305);
		Node node1306 = new Node(332,75,1306,true);
		allNodes.add(node1306);
		Node node1307 = new Node(382,75,1307,true);
		allNodes.add(node1307);
		Node node1308 = new Node(433,75,1308,true);
		node1308.block();
		allNodes.add(node1308);
		Node node1309 = new Node(483,75,1309,true);
		allNodes.add(node1309);
		Node node1310 = new Node(533,75,1310,true);
		allNodes.add(node1310);
		Node node1311 = new Node(583,75,1311,true);
		allNodes.add(node1311);
		Node node1312 = new Node(634,75,1312,true);
		allNodes.add(node1312);
		Node node1313 = new Node(684,75,1313,true);
		allNodes.add(node1313);
		Node node1314 = new Node(734,75,1314,true);
		allNodes.add(node1314);
		Node node1315 = new Node(784,75,1315,true);
		allNodes.add(node1315);
		Node node1316 = new Node(835,75,1316,true);
		allNodes.add(node1316);	
		
		// fourteenth row
		Node node1400 = new Node(433,15,1400,true);
		allNodes.add(node1400);
		
		// init red base
		redBase = new ArrayList<Node>();
		redBase.add(new Node(130,725,0,false));
		redBase.add(new Node(70,770,1,false));
		redBase.add(new Node(190,770,2,false));
		redBase.add(new Node(95,840,3,false));
		redBase.add(new Node(170,840,4,false));
		for (Node node : redBase) {
			node.addNeighbour(node102);
			allNodes.add(node);
		}
		
		// init green base
		greenBase = new ArrayList<Node>();
		greenBase.add(new Node(331,725,5,false));
		greenBase.add(new Node(271,770,6,false));
		greenBase.add(new Node(391,770,7,false));
		greenBase.add(new Node(296,840,8,false));
		greenBase.add(new Node(371,840,9,false));
		for (Node node : greenBase) {
			node.addNeighbour(node106);
			allNodes.add(node);
		}
		
		// init yellow base
		yellowBase = new ArrayList<Node>();
		yellowBase.add(new Node(532,725,10,false));
		yellowBase.add(new Node(472,770,11,false));
		yellowBase.add(new Node(592,770,12,false));
		yellowBase.add(new Node(497,840,13,false));
		yellowBase.add(new Node(572,840,14,false));
		for (Node node : yellowBase) {
			node.addNeighbour(node110);
			allNodes.add(node);
		}
		
		// init blue base
		blueBase = new ArrayList<Node>();
		blueBase.add(new Node(733,725,15,false));
		blueBase.add(new Node(673,770,16,false));
		blueBase.add(new Node(793,770,17,false));
		blueBase.add(new Node(698,840,18,false));
		blueBase.add(new Node(773,840,19,false));
		for (Node node : blueBase) {
			node.addNeighbour(node114);
			allNodes.add(node);
		}
				
		// add neighbours of first row
		node100.addNeighbour(node101);
		node100.addNeighbour(node200);
		
		node101.addNeighbour(node100);
		node101.addNeighbour(node102);
		
		node102.addNeighbour(node101);
		node102.addNeighbour(node103);
		
		node103.addNeighbour(node102);
		node103.addNeighbour(node104);
		
		node104.addNeighbour(node103);
		node104.addNeighbour(node105);
		node104.addNeighbour(node201);
		
		node105.addNeighbour(node104);
		node105.addNeighbour(node106);
		
		node106.addNeighbour(node105);
		node106.addNeighbour(node107);

		node107.addNeighbour(node106);
		node107.addNeighbour(node108);
		
		node108.addNeighbour(node107);
		node108.addNeighbour(node109);
		node108.addNeighbour(node202);

		node109.addNeighbour(node108);
		node109.addNeighbour(node110);
		
		node110.addNeighbour(node109);
		node110.addNeighbour(node111);
		
		node111.addNeighbour(node110);
		node111.addNeighbour(node112);
		
		node112.addNeighbour(node111);
		node112.addNeighbour(node113);
		node112.addNeighbour(node203);

		node113.addNeighbour(node112);
		node113.addNeighbour(node114);
		
		node114.addNeighbour(node113);
		node114.addNeighbour(node115);
		
		node115.addNeighbour(node114);
		node115.addNeighbour(node116);
		
		node116.addNeighbour(node115);
		node116.addNeighbour(node204);

		// add neighbours for the second row
		node200.addNeighbour(node100);
		node200.addNeighbour(node300);

		node201.addNeighbour(node104);
		node201.addNeighbour(node304);

		node202.addNeighbour(node108);
		node202.addNeighbour(node308);

		node203.addNeighbour(node112);
		node203.addNeighbour(node312);

		node204.addNeighbour(node116);
		node204.addNeighbour(node316);	
		
		// add neighbours for the third row
		node300.addNeighbour(node301);
		node300.addNeighbour(node200);
		
		node301.addNeighbour(node300);
		node301.addNeighbour(node302);
		
		node302.addNeighbour(node301);
		node302.addNeighbour(node303);
		node302.addNeighbour(node400);
		
		node303.addNeighbour(node302);
		node303.addNeighbour(node304);
		
		node304.addNeighbour(node303);
		node304.addNeighbour(node305);
		node304.addNeighbour(node201);
		
		node305.addNeighbour(node304);
		node305.addNeighbour(node306);
		
		node306.addNeighbour(node305);
		node306.addNeighbour(node307);
		node306.addNeighbour(node401);

		node307.addNeighbour(node306);
		node307.addNeighbour(node308);
		
		node308.addNeighbour(node307);
		node308.addNeighbour(node309);
		node308.addNeighbour(node201);

		node309.addNeighbour(node308);
		node309.addNeighbour(node310);
		
		node310.addNeighbour(node309);
		node310.addNeighbour(node311);
		node310.addNeighbour(node402);
		
		node311.addNeighbour(node310);
		node311.addNeighbour(node312);
		
		node312.addNeighbour(node311);
		node312.addNeighbour(node313);
		node312.addNeighbour(node203);

		node313.addNeighbour(node312);
		node313.addNeighbour(node314);
		
		node314.addNeighbour(node313);
		node314.addNeighbour(node315);
		node314.addNeighbour(node403);
		
		node315.addNeighbour(node314);
		node315.addNeighbour(node316);
		
		node316.addNeighbour(node315);
		node316.addNeighbour(node204);
		
		// add neighbours for the fourth row
		node400.addNeighbour(node302);
		node400.addNeighbour(node500);

		node401.addNeighbour(node306);
		node401.addNeighbour(node504);

		node402.addNeighbour(node310);
		node402.addNeighbour(node508);

		node403.addNeighbour(node314);
		node403.addNeighbour(node512);
		
		// add neighbours for the fifth row
		node500.addNeighbour(node400);
		node500.addNeighbour(node501);
		
		node501.addNeighbour(node500);
		node501.addNeighbour(node502);
		
		node502.addNeighbour(node501);
		node502.addNeighbour(node503);
		
		node503.addNeighbour(node502);
		node503.addNeighbour(node504);
		
		node504.addNeighbour(node503);
		node504.addNeighbour(node505);
		node504.addNeighbour(node401);

		node505.addNeighbour(node504);
		node505.addNeighbour(node506);
		
		node506.addNeighbour(node505);
		node506.addNeighbour(node507);

		node507.addNeighbour(node506);
		node507.addNeighbour(node508);
		
		node508.addNeighbour(node507);
		node508.addNeighbour(node509);
		node508.addNeighbour(node402);
		
		node509.addNeighbour(node508);
		node509.addNeighbour(node510);
		
		node510.addNeighbour(node509);
		node510.addNeighbour(node511);

		node511.addNeighbour(node510);
		node511.addNeighbour(node512);
		
		node512.addNeighbour(node511);
		node512.addNeighbour(node403);
		
		// add neighbours for sixth row
		node600.addNeighbour(node502);
		node600.addNeighbour(node700);
		
		node601.addNeighbour(node510);
		node601.addNeighbour(node708);
		
		// add neighbours for seventh row
		node700.addNeighbour(node600);
		node700.addNeighbour(node701);
		
		node701.addNeighbour(node700);
		node701.addNeighbour(node702);
		
		node702.addNeighbour(node701);
		node702.addNeighbour(node703);

		node703.addNeighbour(node702);
		node703.addNeighbour(node704);
		
		node704.addNeighbour(node703);
		node704.addNeighbour(node705);

		node705.addNeighbour(node704);
		node705.addNeighbour(node706);
		
		node706.addNeighbour(node705);
		node706.addNeighbour(node707);
		
		node707.addNeighbour(node706);
		node707.addNeighbour(node708);
		
		node708.addNeighbour(node706);
		node708.addNeighbour(node601);
		
		// add neighbours for the eighth row
		node800.addNeighbour(node702);
		node800.addNeighbour(node900);
		
		node801.addNeighbour(node706);
		node801.addNeighbour(node904);
		
		// add neighbours for the ninth row
		node900.addNeighbour(node800);
		node900.addNeighbour(node901);

		node901.addNeighbour(node900);
		node901.addNeighbour(node902);
		
		node902.addNeighbour(node901);
		node902.addNeighbour(node903);
		node902.addNeighbour(node1000);
		
		node903.addNeighbour(node902);
		node903.addNeighbour(node904);
		
		node904.addNeighbour(node903);
		node904.addNeighbour(node801);
		
		// add neighbours for the tenth row
		node1000.addNeighbour(node902);
		node1000.addNeighbour(node1108);
		
		// add neighbours for the eleventh row
		node1100.addNeighbour(node1101);
		node1100.addNeighbour(node1200);
		
		node1101.addNeighbour(node1100);
		node1101.addNeighbour(node1102);
		
		node1102.addNeighbour(node1101);
		node1102.addNeighbour(node1103);
		
		node1103.addNeighbour(node1102);
		node1103.addNeighbour(node1104);
		
		node1104.addNeighbour(node1103);
		node1104.addNeighbour(node1105);
		
		node1105.addNeighbour(node1104);
		node1105.addNeighbour(node1106);
		
		node1106.addNeighbour(node1105);
		node1106.addNeighbour(node1107);

		node1107.addNeighbour(node1106);
		node1107.addNeighbour(node1108);
		
		node1108.addNeighbour(node1107);
		node1108.addNeighbour(node1109);

		node1109.addNeighbour(node1108);
		node1109.addNeighbour(node1110);
		
		node1110.addNeighbour(node1109);
		node1110.addNeighbour(node1111);
		
		node1111.addNeighbour(node1110);
		node1111.addNeighbour(node1112);
		
		node1112.addNeighbour(node1111);
		node1112.addNeighbour(node1113);

		node1113.addNeighbour(node1112);
		node1113.addNeighbour(node1114);
		
		node1114.addNeighbour(node1113);
		node1114.addNeighbour(node1115);
		
		node1115.addNeighbour(node1114);
		node1115.addNeighbour(node1116);
		
		node1116.addNeighbour(node1115);
		node1116.addNeighbour(node1201);
		
		// add neighbours for the twelveth row
		node1200.addNeighbour(node1100);
		node1200.addNeighbour(node1300);
		
		node1201.addNeighbour(node1116);
		node1201.addNeighbour(node1316);
		
		// add neighbours for the thirteenth row
		node1300.addNeighbour(node1301);
		node1300.addNeighbour(node1200);
		
		node1301.addNeighbour(node1300);
		node1301.addNeighbour(node1302);
		
		node1302.addNeighbour(node1301);
		node1302.addNeighbour(node1303);
		
		node1303.addNeighbour(node1302);
		node1303.addNeighbour(node1304);
		
		node1304.addNeighbour(node1303);
		node1304.addNeighbour(node1305);
		
		node1305.addNeighbour(node1304);
		node1305.addNeighbour(node1306);
		
		node1306.addNeighbour(node1305);
		node1306.addNeighbour(node1307);

		node1307.addNeighbour(node1306);
		node1307.addNeighbour(node1308);
		
		node1308.addNeighbour(node1307);
		node1308.addNeighbour(node1309);
		node1308.addNeighbour(node1400);

		node1309.addNeighbour(node1308);
		node1309.addNeighbour(node1310);
		
		node1310.addNeighbour(node1309);
		node1310.addNeighbour(node1311);
		
		node1311.addNeighbour(node1310);
		node1311.addNeighbour(node1312);
		
		node1312.addNeighbour(node1311);
		node1312.addNeighbour(node1313);

		node1313.addNeighbour(node1312);
		node1313.addNeighbour(node1314);
		
		node1314.addNeighbour(node1313);
		node1314.addNeighbour(node1315);
		
		node1315.addNeighbour(node1314);
		node1315.addNeighbour(node1316);
		
		node1316.addNeighbour(node1315);
		node1316.addNeighbour(node1201);
		
		// add neighbours for the fourteenth row
		node1400.addNeighbour(node1308);
		
		for (Node node : allNodes) {
			//node.addPlayer(1);
			board.drawNode(node);
		}
	}
}
