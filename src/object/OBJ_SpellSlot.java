package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SpellSlot extends Entity{

	GamePanel gp;
	
	public static final String objName = "Spell Slot";
	
	public OBJ_SpellSlot(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 1;
		down1 = setUp("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image = setUp("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image2 = setUp("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
	}
	
	@Override
	public boolean use(Entity entity) {
		
		gp.playSE(2);
		gp.ui.addMessage("Spell Slot +" + value);
		entity.spellSlot += value;
		return true;
	}

}
