package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity{

	GamePanel gp;
	public static final String objName = "Blue Heart";
	
	public OBJ_BlueHeart(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		down1 = setUp("/objects/blueheart", gp.tileSize, gp.tileSize);
		
		setDialogues();
	}
	
	public void setDialogues() {
		
		dialogue[0][0] = "You pick up a beautiful blue gem";
		dialogue[0][1] = "You find the Blue Heart, the \nlegendary treasure!";
	}
	
	@Override
	public boolean use(Entity entity) {
		
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.ending;
		return true;
	}

}
