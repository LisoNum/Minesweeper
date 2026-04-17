package model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.StateOperator.buttonState;
import model.StateOperator.gameDifficulty;
import model.StateOperator.gameState;
import view.ButtonSetUp;
import view.GameUI;

public class MouseHandler extends MouseAdapter {

	private GameLogic logic;
	private StateOperator operator;
	private ButtonSetUp buttons;
	private GameUI ui;
	
	//tracks number of clicks in game
	private static int clickNum;
	
	public MouseHandler(GameLogic logic, StateOperator operator, ButtonSetUp buttons, GameUI gameUI) {
		this.logic = logic;
		this.operator = operator;
		this.buttons = buttons;
		this.ui = gameUI;
	}
	
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		handleClicks(mouseX, mouseY);
	}

	private void handleClicks(int mouseX, int mouseY) {
		if (operator.getState() == gameState.duringGame) {
			handleButtonClicks(mouseX, mouseY);
			if (validateClick(mouseX, mouseY)) {
				clickNum++;
				gameClick(mouseX, mouseY);
			}
			
		}
		
		
	}

	private void handleButtonClicks(int mouseX, int mouseY) {
		
		//TODO implement bomb distribution on first valid click
		if (operator.getButState() == buttonState.inActiv) {
			if (mouseX >= buttons.preDropdown[0] && mouseX <= buttons.preDropdown[1] && mouseY >= buttons.preDropdown[2] && mouseY <= buttons.preDropdown[3]) {
				operator.setButState(buttonState.activ);
			}
			return;
		}
		
		if (operator.getButState() == buttonState.activ) {
			if (mouseX >= buttons.dropDownEasy[0] && mouseX <= buttons.dropDownEasy[1] && mouseY >= buttons.dropDownEasy[2] && mouseY <= buttons.dropDownEasy[3]) {
				operator.setDifficulty(gameDifficulty.easy);
				operator.setButState(buttonState.inActiv);
			}
			if (mouseX >= buttons.dropDownMedium[0] && mouseX <= buttons.dropDownMedium[1] && mouseY >= buttons.dropDownMedium[2] && mouseY <= buttons.dropDownMedium[3]) {
				operator.setDifficulty(gameDifficulty.medium);
				operator.setButState(buttonState.inActiv);
			}
			if (mouseX >= buttons.dropDownHard[0] && mouseX <= buttons.dropDownHard[1] && mouseY >= buttons.dropDownHard[2] && mouseY <= buttons.dropDownHard[3]) {
				operator.setDifficulty(gameDifficulty.hard);
				operator.setButState(buttonState.inActiv);
			}
			else {
				operator.setButState(buttonState.inActiv);
			}
			return;
		}
		
	}
	
	private boolean validateClick(int x, int y) {
		if (ui.getBoardWith()[0] <= x && ui.getBoardWith()[0] + ui.getBoardWith()[2] >= x && ui.getBoardWith()[1] <= y && ui.getBoardWith()[1] + ui.getBoardWith()[3] >= y) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int[] cordinatesToField(int mouseX, int mouseY) {
		int xPositionOnBoard = mouseX - ui.getBoardWith()[0];
		int yPositionOnBoard = mouseY - ui.getBoardWith()[1];
		int[] xyField = {0, 0};
		int keinAhnungwiseoaberfunktioniert = 2;
		
		xyField[0] = xPositionOnBoard / (ui.getWindowSpecs()[0] / ui.getTileNumber()[0]);
		xyField[1] = (yPositionOnBoard / (ui.getWindowSpecs()[1] / ui.getTileNumber()[1]) - keinAhnungwiseoaberfunktioniert);
		return xyField;
		
		
	}
	
	private void gameClick(int mouseX, int mouseY) {
		//TODO add more logic like bombs etc. 
		logic.getTileStateHolder()[cordinatesToField(mouseX, mouseY)[0]][cordinatesToField(mouseX, mouseY)[1]].setClicked(true);
		ui.repaint();
	}
	
	public void setButtons(ButtonSetUp buttons) {
		this.buttons = buttons;
	}
	

}
