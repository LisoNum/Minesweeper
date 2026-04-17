package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.GameLogic;
import model.MouseHandler;
import model.StateOperator;
import model.StateOperator.gameDifficulty;

public class GameUI extends JPanel {

	//constants for color
	private final static Color BACKGROUND_COLOR = Color.decode("#ade8ad");
	private final static Color BORDER_COLOR = Color.decode("#4a752c");
	private final static Color ELEMENT_COLOR = Color.decode("#4a752c");
	private final static Color DARK_GREEN = Color.decode("#a2d149");
	private final static Color LIGHT_GREEN = Color.decode("#aad751");
	private final static Color DARK_BROWN = Color.decode("#d7b899");
	private final static Color LIGHT_BROWN = Color.decode("#e5c29f");
	private final static Font LABEL_FONT = new Font("Badoni MT", Font.BOLD, 16);
	
	//constants for game
	private final static int[][] TILE_NUMBER = {{10, 8}, {18, 14}, {24, 20}};
	private final static int[] BOMB_NUMBER = {10, 40, 99};
	
	//diffrent parts
	private GameLogic logic;
	private MouseHandler handler;
	private StateOperator operator;
	private ButtonSetUp buttons;  
	//TODO implement classes for diffrent ui elements (flag, bomb, etc.)
	
	//aspects for ui
	private int padding;
	private int margin;
	private int content;
	private int screenWidth;
	private int[] boardWith = {0, 0, 0, 0};
	
	//variables for field
	private int bombNumber;
	private int[] tileNumber = {0, 0};
	private int[] windowSpecs = {0, 0};
	
	public GameUI(int padding, int margin, int content, StateOperator state) {
		this.padding = padding;
		this.margin = margin;
		this.content = content;
		this.operator = state;
		this.buttons = new ButtonSetUp(this, operator);
		this.screenWidth = 2*padding + content;
		
		this.setBackground(BACKGROUND_COLOR);
		this.setPreferredSize(new Dimension(2*padding + content, 2*padding + content));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		//set size Factors
		getDifficulty();
		
		drawBoard(g2);
		drawHeader(g2);
		//TODO implement drawing clicked fields diffrently
		
	}

	private void drawBoard(Graphics2D g2) {
		
		int height = windowSpecs[1];
		int width = windowSpecs[0];
		int arc = 10;
		int headerHight = 75;
		int xGap = width/tileNumber[0];
		int yGap = height/tileNumber[1];
		int xOffset = (screenWidth - width)/2;
		int yOffset = padding + 75;
		
		drawGitter(g2, xGap, yGap, xOffset, yOffset);
		
		g2.setColor(BORDER_COLOR);
		g2.setStroke(new BasicStroke(2));
		g2.drawRoundRect(xOffset, padding, width, height + headerHight, arc, arc);
		setBoardWith(xOffset, padding, width, height + headerHight);
		
	}

	private void drawGitter(Graphics2D g2, int xGap, int yGap, int xOffset, int yOffset) {
		
		enum brightness {
			dark, light
		}
		brightness brightness = null; 
		Color color = BACKGROUND_COLOR;
		
		for (int i=0; i<tileNumber[0]; i++) {
			for (int j=0; j<tileNumber[1]; j++) {
				
				brightness = (j %2 == 1 && i %2 == 1 || j %2 == 0 && i %2 == 0 ) ?  brightness.light : brightness.dark;
				if (logic.getTileStateHolder()[i][j].isClicked()) {
					color = (brightness == brightness.light) ? LIGHT_BROWN : DARK_BROWN;
				}
				else {
					color = (brightness == brightness.light) ? LIGHT_GREEN : DARK_GREEN;
				}
				g2.setColor(color);
				g2.fillRect(xOffset + i*xGap, yOffset + j*yGap, xGap, yGap);
			}
		}
		
	}

	private void drawHeader(Graphics2D g2) {
		
		int height = 75;
		int width = windowSpecs[0];
		int xOffset = (screenWidth - width) / 2;
		
		g2.setColor(ELEMENT_COLOR);
		g2.fillRect(xOffset, padding, width, height);
		g2.setColor(BORDER_COLOR);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(xOffset, padding, width, height);
		
		buttons.drawButtons(g2, xOffset, padding);
		
	}
	
	private void getDifficulty() {
		if (operator.getDifficulty() == gameDifficulty.easy) {
			tileNumber[0] = TILE_NUMBER[0][0];
			tileNumber[1] = TILE_NUMBER[0][1];
			bombNumber = BOMB_NUMBER[0];
			windowSpecs[0] = 50 * tileNumber[0]; // x
			windowSpecs[1] = 50 * tileNumber[1]; //y
		}
		if (operator.getDifficulty() == gameDifficulty.medium) {
			tileNumber[0] = TILE_NUMBER[1][0];
			tileNumber[1] = TILE_NUMBER[1][1];
			bombNumber = BOMB_NUMBER[1];
			windowSpecs[0] = 34 * tileNumber[0];
			windowSpecs[1] = 35 * tileNumber[1];
		}
		if (operator.getDifficulty() == gameDifficulty.hard) {
			tileNumber[0] = TILE_NUMBER[2][0];
			tileNumber[1] = TILE_NUMBER[2][1];
			bombNumber = BOMB_NUMBER[2];
			windowSpecs[0] = 31 * tileNumber[0];
			windowSpecs[1] =  30 * tileNumber[1];
		}
	}

	public void setLogic(GameLogic logic) {
		this.logic = logic;
	}
	
	public void setOperator(StateOperator operator) {
		this.operator = operator;
	}

	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}

	public static Color getBorderColor() {
		return BORDER_COLOR;
	}

	public static Color getElementColor() {
		return ELEMENT_COLOR;
	}

	public Font getLabelFont() {
		return LABEL_FONT;
	}

	public int[] getWindowSpecs() {
		return windowSpecs;
	}

	public int[] getTileNumber() {
		switch(operator.getDifficulty()) {
		case easy:
			return TILE_NUMBER[0];
		case medium:
			return TILE_NUMBER[1];
		case hard:
			return TILE_NUMBER[2];
		default:
			return null;
		}
	}
	
	private void setBoardWith(int x, int y, int width, int height) {
		
		boardWith[0] = x;
		boardWith[1] = y;
		boardWith[2] = width;
		boardWith[3] = height;
	}
	
	public int[] getBoardWith() {
		return boardWith;
	}

	public void setMouseHandler() {
		this.handler = new MouseHandler(logic, operator, buttons, this);
		this.addMouseListener(handler);
	}
}
