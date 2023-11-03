package entity;

import main.KeyHandler;
import object.OBJ_Axe;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Scaley extends Player{
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;
	
	public Scaley(GamePanel gp, KeyHandler keyH) {
		
		super(gp, keyH);
		
		this.keyH = keyH;
		
		currentPlayer = mike;
		gp.ui.currentPlayer = "Scaley";
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
	
		//ATTACK AREA
//		attackArea.width = 36;
//		attackArea.height = 36;
		
		
		setDefaultValues();
	}
	
	@Override
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
//		worldX = gp.tileSize * 36;
//		worldY = gp.tileSize * 29;
	//	gp.currentMap = 1;
		
		defaultSpeed = 5;
		speed = defaultSpeed;
		direction = "down";
			
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxSpellSlot = 4;
		spellSlot = maxSpellSlot;
		strength = 2; // More Strength = more damage
		dexterity = 1; // More Dexterity = more defense
		exp = 0;
		nextLevelExp = 5;
		coin = 500;
		currentWeapon = new OBJ_Sword_Normal(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		currentLight = null;
		projectile = new OBJ_Fireball(gp);
		attack = getAttack(); // Attack = weapon * strength
		defense = getDefense(); // Defense = shield * dexterity
			
		getImage();
		getAttackImage();
		getGuardImage();
		setItems();
		setDialogue();
	}
	
	@Override
	public void setDefaultPositions() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;		
		direction = "down";
	}
	
	@Override
	public void restoreStatus() {
		
		life = maxLife;
		spellSlot = maxSpellSlot;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
	}
	
	@Override
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Axe(gp));
		
	}
	
	@Override
	public void getImage() {
		
		up1 = setUp("/Scaley/scaley_up_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Scaley/scaley_up_2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Scaley/scaley_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Scaley/scaley_down_2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Scaley/scaley_left_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Scaley/scaley_left_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Scaley/scaley_right_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Scaley/scaley_right_2", gp.tileSize, gp.tileSize);
		
		inv = setUp("/Rythian/rythian_inv", gp.tileSize, gp.tileSize);
		battleState = setUp("/Scaley/scaley_fight", gp.tileSize*2, gp.tileSize*3);
		empty = setUp("/Scaley/scaley_empty", gp.tileSize*2, gp.tileSize*2);
	}
	
	@Override
	public void getSleepingImage(BufferedImage image) {
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		left1 = image;
		left2 = image;
		right1 = image;
		right2 = image;
	}
	
	@Override
	public void getAttackImage() {
		
		if(currentWeapon.type == type_sword) {
			
			attackUp1 = setUp("/Rythian/rythian_attack_up_1", gp.tileSize, gp.tileSize*2);
			attackUp2 = setUp("/Rythian/rythian_attack_up_2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setUp("/Rythian/rythian_attack_down_1", gp.tileSize, gp.tileSize*2);
			attackDown2 = setUp("/Rythian/rythian_attack_down_2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setUp("/Rythian/rythian_attack_left_1", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setUp("/Rythian/rythian_attack_left_2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setUp("/Rythian/rythian_attack_right_1", gp.tileSize*2, gp.tileSize);
			attackRight2 = setUp("/Rythian/rythian_attack_right_2", gp.tileSize*2, gp.tileSize);
		}
		if(currentWeapon.type == type_axe) {
			
			attackUp1 = setUp("/Rythian/rythian_attack_up_1", gp.tileSize, gp.tileSize*2);
			attackUp2 = setUp("/Rythian/rythian_attack_up_2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setUp("/Rythian/rythian_attack_down_1", gp.tileSize, gp.tileSize*2);
			attackDown2 = setUp("/Rythian/rythian_attack_down_2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setUp("/Rythian/rythian_attack_left_1", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setUp("/Rythian/rythian_attack_left_2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setUp("/Rythian/rythian_attack_right_1", gp.tileSize*2, gp.tileSize);
			attackRight2 = setUp("/Rythian/rythian_attack_right_2", gp.tileSize*2, gp.tileSize);
		}

	}
	
	@Override
	public void getGuardImage() {
		
		guardUp = setUp("/Rythian/rythian_guard_up", gp.tileSize, gp.tileSize);
		guardDown = setUp("/Rythian/rythian_guard_down", gp.tileSize, gp.tileSize);
		guardLeft = setUp("/Rythian/rythian_guard_left", gp.tileSize, gp.tileSize);
		guardRight = setUp("/Rythian/rythian_guard_right", gp.tileSize, gp.tileSize);
	}
}
