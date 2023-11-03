package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;


public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 2;
		
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
		
//		up1 = setUp("/npc/oldman_up_1");
//		up2 = setUp("/npc/oldman_up_2");
//		down1 = setUp("/npc/oldman_down_1");
//		down2 = setUp("/npc/oldman_down_2");
//		left1 = setUp("/npc/oldman_left_1");
//		left2 = setUp("/npc/oldman_left_2");
//		right1 = setUp("/npc/oldman_right_1");
//		right2 = setUp("/npc/oldman_right_2");
		
		up1 = setUp("/Scaley/scaley_up_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Scaley/scaley_up_2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Scaley/scaley_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Scaley/scaley_down_2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Scaley/scaley_left_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Scaley/scaley_left_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Scaley/scaley_right_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Scaley/scaley_right_2", gp.tileSize, gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogue[0][0] = "Hello";
		dialogue[0][1] = "So you've come to this island \n to find treasure?";
		dialogue[0][2] = "Well god hasn't created that \npart of the game yet..";
		dialogue[0][3] = "Good luck to you";
		
		dialogue[1][0] = "If you become tired, rest at the water.";
		dialogue[1][1] = "However, the monsters reappear \nif you rest.";
		dialogue[1][2] = "In any case, don't push yourself \ntoo hard";
		
		dialogue[2][0] = "I wonder how to open that door...";
	}
	
	@Override
	public void setAction() {
		
		if(onPath == true) {
			
			int goalCol = 30;
			int goalRow = 21;
//			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
//			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;		
			
			searchPath(goalCol, goalRow);
			reachTargetGoal(goalCol, goalRow);
			
		} 
		else {
			
			actionLockCounter ++;
			
			if(actionLockCounter == 120) {
			
				Random random = new Random();
				int i = random.nextInt(100)+1; //Pick random number 1-100
				
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i<= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
		}
		
	}
	
	@Override
	public void speak() {
		
		facePlayer();
		startDialogue(this,dialogueSet);
		
		dialogueSet++;
		
		if(dialogue[dialogueSet][0] == null) {
			
	//		dialogueSet = 0;
			dialogueSet--;
		}
		
		onPath = true;
		
	}
	
}
