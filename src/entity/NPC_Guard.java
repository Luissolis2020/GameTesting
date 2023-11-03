package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Guard extends Entity{

	public static final String npcName = "Guard";
	
	public NPC_Guard(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 0;
		name = npcName;
		cutsceneNPC = true;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		dialogueSet = -1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		up2 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		down1 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		down2 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		left1 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		left2 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		right1 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		right2 = setUp("/npc/apple-1", gp.tileSize, gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogue[0][0] = "Merchant: Hello would you like to buy \nan apple?";
		dialogue[0][1] = "Rythian: How much does it cost?";
		dialogue[0][2] = "Merchant: 1 gold please";
		dialogue[0][3] = "Mike: 1 GOLD no way";
		
		dialogue[1][0] = "If you become tired, rest at the water.";
		dialogue[1][1] = "However, the monsters reappear \nif you rest.";
		dialogue[1][2] = "In any case, don't push yourself \ntoo hard";
		
		dialogue[2][0] = "I wonder how to open that door...";
	}
	
	@Override
	public void setAction() {
		
	}
	
	@Override
	public void speak() {
		
		facePlayer();
		startDialogue(this,dialogueSet);
		gp.csManager.sceneNum = gp.csManager.appleMerch;
		
		dialogueSet++;
		
		if(dialogue[dialogueSet][0] == null) {
			
	//		dialogueSet = 0;
			dialogueSet--;
		}
		
	}

}
