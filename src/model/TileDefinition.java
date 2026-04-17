package model;

public class TileDefinition {
	
	private int id;
	private static int counter = 0;
	private boolean clicked;
	private boolean bombed;
	
	public TileDefinition() {
		this.id = counter++;
		this.clicked = false;
	}

	public boolean isClicked() {
		return clicked;
	}

	public boolean isBombed() {
		return bombed;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public void setBombed(boolean bombed) {
		this.bombed = bombed;
	}

}
