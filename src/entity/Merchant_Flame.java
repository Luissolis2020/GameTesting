package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;


public class Merchant_Flame extends Entity {
	
	public Merchant_Flame(GamePanel gp) {
		super(gp);
		
		direction = "down";
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		getImage();
		
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
	}
	
	public void getImage() {
				
		up1 = setUp("/npc/blue_flame_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/npc/blue_flame_2", gp.tileSize, gp.tileSize);
		down1 = setUp("/npc/blue_flame_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/npc/blue_flame_4", gp.tileSize, gp.tileSize);
		left1 = setUp("/npc/blue_flame_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/npc/blue_flame_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/npc/blue_flame_2", gp.tileSize, gp.tileSize);
		right2 = setUp("/npc/blue_flame_4", gp.tileSize, gp.tileSize);
		
	}
	
	@Override
	public void speak() {
		gp.gameState = gp.playState;
	}
}
