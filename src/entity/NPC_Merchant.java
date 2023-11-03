package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity{

	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		getImage();
		setDialogue();
		setItems();
	}
	
	public void getImage() {
				
		up1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		down1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right1 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogue[0][0] = "\n   Got a good selection of good things \n   on sale, stranger!";
		dialogue[1][0] = "\n     Come back anytime!";
		dialogue[2][0] = "\n     Not enough cash!";
		dialogue[3][0] = "\n   You can't carry anymore, stranger!";
		dialogue[4][0] = "\n  You're going to need that, stranger!";
		
	}
	
	public void setItems() {
		
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Sword_Normal(gp));
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Shield_Wood(gp));
		inventory.add(new OBJ_Shield_Blue(gp));

	}
	
	@Override
	public void speak() {
		
		gp.playSE(18);
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
