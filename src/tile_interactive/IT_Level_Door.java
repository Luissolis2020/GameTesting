package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_Level_Door extends InteractiveTile{

	public static final String IT_Name = "Level Door";
	
	public IT_Level_Door(GamePanel gp, int col, int row, int level) {
		super(gp, col, row);
		this.gp = gp;
		
		this.level = level;
		
		name = IT_Name;
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setUp("/objects/Door", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 1;
		
		setDialogue();
	}
	
	public void setDialogue() {
		
		dialogue[0][0] = "You need to be level " + level + " to open this door!";
		
	}
	
	@Override
	public boolean isCorrectLevel(Entity entity) {
		
		boolean isCorrectLevel = false;
		if(entity.level >= level) {
			isCorrectLevel = true;
		} else {
			startDialogue(this, 0);
			gp.player.attacking = false;
			}
		return isCorrectLevel;
	}
	
	@Override
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	
	@Override
	public void playSE() {
		gp.playSE(11);
	}

}
