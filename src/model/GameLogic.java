package model;

import view.GameUI;

public class GameLogic {
	
	private GameUI ui;
	private StateOperator state;
	private TileDefinition[][] tileStateHolder;
	
	//specs for array
	private int xValue;
	private int yValue;

	public GameLogic(GameUI ui, StateOperator state) {
		this.ui = ui;
		this.state = state;
		
	}
	
	void setTileStateHolder() {
		setSpecsForArray();
		
		tileStateHolder = new TileDefinition[xValue][yValue];
		
		for (int i=0; i<xValue; i++) {
			for (int j=0; j<yValue; j++) {
				tileStateHolder[i][j] = new TileDefinition();
			}
		}
	}
	
	void setSpecsForArray() {
		switch(state.getDifficulty()) {
		case easy:
			xValue = ui.getTileNumber()[0];
			yValue = ui.getTileNumber()[1];
		
		case medium:
			xValue = ui.getTileNumber()[0];
			yValue = ui.getTileNumber()[1];
		case hard:
			xValue = ui.getTileNumber()[0];
			yValue = ui.getTileNumber()[1];
		}
	}

	public TileDefinition[][] getTileStateHolder() {
		return tileStateHolder;
	}

}
