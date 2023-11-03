package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_SpellSlot;

public class MON_Mimic extends Entity{

	GamePanel gp;
	
	public MON_Mimic(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		collision = true;
		name = "Mimic";
		defaultSpeed = 0;
		speed = defaultSpeed;
		maxLife = 5;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
	//	hpBarOn = false;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setUp("/mimic/mimic_backside", gp.tileSize, gp.tileSize);
		up2 = setUp("/mimic/mimic_backside_flipped", gp.tileSize, gp.tileSize);
		down1 = setUp("/mimic/mimic_revealed", gp.tileSize, gp.tileSize);
		down2 = setUp("/mimic/mimic_revealed_flipped", gp.tileSize, gp.tileSize);
		left1 = setUp("/mimic/mimic_left1", gp.tileSize, gp.tileSize);
		left2 = setUp("/mimic/mimic_left2", gp.tileSize, gp.tileSize);
		right1 = setUp("/mimic/mimic_right1", gp.tileSize, gp.tileSize);
		right2 = setUp("/mimic/mimic_right2", gp.tileSize, gp.tileSize);	
		empty = setUp("/mimic/mimic_Hide", gp.tileSize, gp.tileSize);	
	}
	
	public void getAttackImage() {
		
		attackUp1 = setUp("/mimic/mimic_backside", gp.tileSize, gp.tileSize);
		attackUp2 = setUp("/mimic/mimic_backside_flipped", gp.tileSize, gp.tileSize);
		attackDown1 = setUp("/mimic/mimic_revealed", gp.tileSize, gp.tileSize);
		attackDown2 = setUp("/mimic/mimic_revealed_flipped", gp.tileSize, gp.tileSize);
		attackLeft1 = setUp("/mimic/mimic_left1", gp.tileSize, gp.tileSize);
		attackLeft2 = setUp("/mimic/mimic_left2", gp.tileSize, gp.tileSize);
		attackRight1 = setUp("/mimic/mimic_right1", gp.tileSize, gp.tileSize);
		attackRight2 = setUp("/mimic/mimic_right2", gp.tileSize, gp.tileSize);
	}
	
	@Override
	public void startAnimation() {
		animation0 = setUp("/mimic/mimic_Hide", gp.tileSize, gp.tileSize);
		animation1 = setUp("/mimic/mimic_opening1", gp.tileSize, gp.tileSize);
		animation2 = setUp("/mimic/mimic_opening2", gp.tileSize, gp.tileSize);
		animation3 = setUp("/mimic/mimic_revealed", gp.tileSize, gp.tileSize);
		
		animationCounter++;
		
		System.out.println(animationCounter);
		
			if(animationCounter == 5) {
				direction = "up";
				up1 = animation0;
				System.out.println("up");
			}
			if(animationCounter == 10) {
				direction = "down";
				down1 = animation1;
				System.out.println("down");
			}
			if(animationCounter == 15) {
				direction = "left";
				left1 = animation2;
				System.out.println("left");
			}
			if(animationCounter == 20) {
				direction = "right";
				right1 = animation3;
				System.out.println("right");
		}
	
			animationCounter = 0;
			animationPlayed = true;
	}
	
	@Override
	public void interact() {
		startAnimation();
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i].name == "Mimic") {
				gp.monster[gp.currentMap][i] = new MON_Mimic(gp);
				gp.monster[gp.currentMap][i].worldX = gp.obj[gp.currentMap][i].worldX;
				gp.monster[gp.currentMap][i].worldY = gp.obj[gp.currentMap][i].worldY;
				gp.obj[gp.currentMap][i] = null;
				break;
			}
		}
		type = type_monster;
		collision = false;
	}
	
	@Override
	public void setAction() {
		
		if(onPath == true) {
			
			up1 = attackUp1;
			up2 = attackUp2;
			down1 = attackDown1;
			down2 = attackDown2;
			left1 = attackLeft1;
			left2 = attackLeft2;
			right1 = attackRight1;
			right2 = attackRight2;
			
			speed = 1;
			
			//Check if it stops chasing
			checkStopChasingOrNot(gp.player, 15, 100);
			
			// Search the direction to go
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
		} else {
			
			// Check if it starts chasing
			checkStartChasingOrNot(gp.player, 2, 10);
				
			up1 = empty;
			up2 = empty;
			down1 = empty;
			down2 = empty;
			left1 = empty;
			left2 = empty;
			right1 = empty;
			right2 = empty;
			animationPlayed = false;
			speed = 0;
			
		}
		
	}
	
	@Override
	public void damageReaction() {
		
		speed = 1;
		actionLockCounter = 0;
//		direction = gp.player.direction;
		onPath = true;
	}
	
	@Override
	public void checkDrop() {
		
		// CAST A DIE
		int i = new Random().nextInt(100)+1;
		
		// SET THE MONSTER DROP
		if(i < 50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i >= 75 && i < 100) {
			dropItem(new OBJ_SpellSlot(gp));
		}
		
	}

}
