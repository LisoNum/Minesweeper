package model;

import view.GameUI;

public class StateOperator {
	
	private GameUI ui;
	private GameLogic logic;

	private gameDifficulty difficulty;
	private gameState state;
	private buttonState butState;

	public enum gameDifficulty {
		easy, medium, hard
	}
	
	public enum gameState {
		duringGame, postGame
	}
	
	public enum buttonState {
		inActiv, activ
	}
	
	public StateOperator() {
		difficulty = gameDifficulty.medium;
		butState = buttonState.inActiv;
		state = gameState.duringGame;
		
	}
	
	public void setButState(buttonState butState) {
		this.butState = butState;
		ui.repaint();
	}

	public gameDifficulty getDifficulty() {
		return difficulty;
	}

	public gameState getState() {
		return state;
	}

	public buttonState getButState() {
		return butState;
	}

	public void setUi(GameUI ui) {
		this.ui = ui;
	}
	
	public void setDifficulty(gameDifficulty difficulty) {
		this.difficulty = difficulty;
		logic.setTileStateHolder();
		ui.repaint();
		//TODO state of game should be saved during switches into same difficulty
	}

	public void setLogic(GameLogic logic) {
		this.logic = logic;
	}
	
	public void initializeTileHolder() {
		logic.setTileStateHolder();
	}
}
