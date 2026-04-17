package controller;

import model.GameLogic;
import model.StateOperator;
import view.GameUI;

public class Manager {
	
	private GameUI ui;
	private GameLogic logic;
	private StateOperator state;
	
	private int padding;
	private int margin;
	private int content;
	
	public Manager() {
		this.padding = 25;
		this.margin = 50;
		this.content = 700;
		
		initializeGame();
	}

	private void initializeGame() {
		this.state = new StateOperator();
		this.ui = new GameUI(padding, margin, content, state);
		this.logic = new GameLogic(ui, state);
		
		ui.setLogic(logic);
		state.setUi(ui);
		state.setLogic(logic);
		ui.setMouseHandler();
		state.initializeTileHolder();
	}

	public GameUI getUi() {
		return ui;
	}
	
	
	

}
