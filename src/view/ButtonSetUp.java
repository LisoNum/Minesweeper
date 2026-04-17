package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import model.StateOperator;
import model.StateOperator.buttonState;

public class ButtonSetUp {

	private GameUI ui;
	private StateOperator operator;
	
	private int width;
	private int headerHeight;
	
	//values for button Handler
	public int[] preDropdown = {0, 0, 0, 0}; //xmin-xmax-ymin-ymax
	public int[] dropDownEasy = {0, 0, 0, 0};
	public int[] dropDownMedium = {0, 0, 0, 0};
	public int[] dropDownHard = {0, 0, 0, 0};
	
	public ButtonSetUp(GameUI ui, StateOperator operator) {
		this.ui = ui;
		this.operator = operator;
		this.headerHeight = 75;
	}
	
	public void drawButtons(Graphics2D g2, int xOffset, int padding) {
		
		int widthButton;
		int height = 31;
		int yOffset = 20;
		int xMargin = 25;
		int arc = 10;
		String text = chooseText();
		g2.setFont(ui.getLabelFont());
		
		FontMetrics fm = g2.getFontMetrics();
		widthButton = fm.stringWidth(text) + 20;
		
		if (operator.getButState() == buttonState.inActiv) {
			drawPreDropDownButton(g2, xOffset, text, xMargin, padding, yOffset, height, arc, fm, widthButton);
		}
		if (operator.getButState() == buttonState.activ) {
			drawPreDropDownButton(g2, xOffset, text, xMargin, padding, yOffset, height, arc, fm, widthButton);
			drawDuringDropDownButton(g2, arc, fm);
		}
		
		
		
		
		
	}
	
	private void drawDuringDropDownButton(Graphics2D g2, int arc, FontMetrics fm) {
		 
		String easy = "Easy";
		String medium = "Medium";
		String hard = "Hard";
		
		//Drop-Down Easy
		g2.setColor(Color.WHITE); 
		g2.fillRect(preDropdown[0], preDropdown[3], (int) ((preDropdown[1] - preDropdown[0])*1.6), preDropdown[3] - preDropdown[2]);
		g2.setColor(Color.BLACK);
		setSpecs(dropDownEasy, preDropdown[0], preDropdown[0] + (int) ((preDropdown[1] - preDropdown[0])*1.6), preDropdown[3], preDropdown[3] + (preDropdown[3] - preDropdown[2]));
		g2.drawString(easy, (int) (dropDownEasy[0] +(dropDownEasy[1] - dropDownEasy[0])/3.5), dropDownEasy[2] + (dropDownEasy[3] - dropDownEasy[2])/2 + fm.getHeight()/4);
		//Drop-Down Medium
		g2.setColor(Color.WHITE); 
		g2.fillRect(dropDownEasy[0], dropDownEasy[3], dropDownEasy[1] - dropDownEasy[0], dropDownEasy[3] - dropDownEasy[2]);
		g2.setColor(Color.BLACK);
		setSpecs(dropDownMedium, dropDownEasy[0], dropDownEasy[1], dropDownEasy[3], dropDownEasy[3] + (dropDownEasy[3] - dropDownEasy[2]));
		g2.drawString(medium, (int) (dropDownEasy[0] +(dropDownEasy[1] - dropDownEasy[0])/3.5), dropDownMedium[2] + (dropDownMedium[3] - dropDownMedium[2])/2 + fm.getHeight()/4);
		//Drop-Down Hard
		g2.setColor(Color.WHITE); 
		g2.fillRect(dropDownMedium[0], dropDownMedium[3], dropDownMedium[1] - dropDownMedium[0], dropDownMedium[3] - dropDownMedium[2]);
		g2.setColor(Color.BLACK);
		setSpecs(dropDownHard, dropDownMedium[0], dropDownMedium[1], dropDownMedium[3], dropDownMedium[3] + (dropDownMedium[3] - dropDownMedium[2]));
		g2.drawString(hard, (int) (dropDownEasy[0] +(dropDownEasy[1] - dropDownEasy[0])/3.5), dropDownHard[2] + (dropDownHard[3] - dropDownHard[2])/2 + fm.getHeight()/4);
		//going down step by step, too lazy for clean code
		
		//clean edges
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(dropDownEasy[0], dropDownEasy[2],dropDownEasy[1] - dropDownEasy[0] ,dropDownHard[3] - dropDownEasy[2] , arc, arc);
		
		//show selected dificulty
		String check = "✓";
		g2.setColor(Color.BLACK);
		FontMetrics ch = g2.getFontMetrics();
		g2.drawString(check, dropDownEasy[0] +10, dropDownEasy[2] + (dropDownEasy[3] - dropDownEasy[2])/2 + ch.getHeight()/4 + (dropDownEasy[3] - dropDownEasy[2])*getFactor());
	}

	private int getFactor() {
		switch (operator.getDifficulty()) {
		case easy:
			return 0;
		case medium:
			return 1;
		case hard:
			return 2;
		default:
			return 0;
		}
		
	}

	private void drawPreDropDownButton(Graphics2D g2, int xOffset, String text, int xMargin, int padding, int yOffset, int height,
			int arc, FontMetrics fm, int widthButton) {
		
		//first part of buton
		g2.setColor(Color.WHITE);
		g2.fillRect(xOffset + xMargin, padding + yOffset, widthButton, height);
		g2.setColor(Color.BLACK);
		g2.drawString(text, xOffset + xMargin + 5, padding + yOffset + fm.getHeight()/4 + (height)/2);
		//quality of life improvement
		setSpecs(preDropdown, xOffset+xMargin, xOffset+xMargin+widthButton, padding+yOffset, padding+yOffset+height);
		
		
		String arrowDown = "⯆";
		FontMetrics ad = g2.getFontMetrics();
		int widthArrow = ad.stringWidth(arrowDown) + 10;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(preDropdown[1], preDropdown[2], widthArrow, height);
		g2.setColor(Color.BLACK);
		g2.drawString(arrowDown,preDropdown[1] + widthArrow/2 - ad.stringWidth(arrowDown)/2 , preDropdown[2] + height/2 + ad.getHeight()/4);
		//quality of life improvment
		setSpecs(preDropdown, preDropdown[0], preDropdown[1] + widthArrow, preDropdown[2], preDropdown[3]);
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(preDropdown[0], preDropdown[2], preDropdown[1] - preDropdown[0], preDropdown[3] - preDropdown[2], arc, arc); //Round Edges
		
	}

	public String chooseText() {
		switch(operator.getDifficulty()) {
		case easy:
			return "Easy";
		case medium:
			return "Medium";
		case hard:
			return "Hard";
		default:
			return "";
		}
	}
	
	public void setSpecs(int[] specs, int xmi, int xma, int ymi, int yma) {
		
		specs[0] = xmi;
		specs[1] = xma;
		specs[2] = ymi;
		specs[3] = yma;
	}
	
	public void setWidth(int height) {
		width = (int) (height * 1.25);
	}
}
