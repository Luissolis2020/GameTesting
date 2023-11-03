package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{

	GamePanel gp;
	
	public static final String objName = "Tent";
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setUp("/objects/tent", gp.tileSize, gp.tileSize);
		description = "[Tent]\nAllows you to sleep\nthrough the night.";
		price = 300;
		stackable = true;
	}

	@Override
	public boolean use(Entity entity) {
		
		gp.gameState = gp.sleepState;
		gp.player.life = gp.player.maxLife;
		gp.player.spellSlot = gp.player.maxSpellSlot;
		gp.player.getSleepingImage(down1);
		
		return false;
	}

}
