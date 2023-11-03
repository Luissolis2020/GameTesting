package monster;


import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Rock;

public class MON_GreenSlimeBattle extends Entity{

	GamePanel gp;
	
	public MON_GreenSlimeBattle(GamePanel gp) {
		
		super(gp);
		
		this.gp = gp;
		
		type = type_monster_battle;
		name = "Green Slime";
		defaultSpeed = 0;
		speed = defaultSpeed;
		maxLife = 3;
		life = maxLife;
		attack = 2;
		defense = 0;
		exp = 2;
		ammo = 999;
		projectile = new OBJ_Rock(gp);
		//hpBarOn = true;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
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
		
		up1 = setUp("/monster/greenslime_down_1", gp.tileSize*2, gp.tileSize*2);
		up2 = setUp("/monster/greenslime_down_2", gp.tileSize*2, gp.tileSize*2);
		down1 = setUp("/monster/greenslime_down_1", gp.tileSize*2, gp.tileSize*2);
		down2 = setUp("/monster/greenslime_down_2", gp.tileSize*2, gp.tileSize*2);
		left1 = setUp("/monster/greenslime_down_1", gp.tileSize*2, gp.tileSize*2);
		left2 = setUp("/monster/greenslime_down_2", gp.tileSize*2, gp.tileSize*2);
		right1 = setUp("/monster/greenslime_down_1", gp.tileSize*2, gp.tileSize*2);
		right2 = setUp("/monster/greenslime_down_2", gp.tileSize*2, gp.tileSize*2);	
	}
	
	@Override
	public void update() {
		
		super.update();
		
		
	}
	

}
