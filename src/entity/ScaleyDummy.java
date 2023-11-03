package entity;

import main.GamePanel;

public class ScaleyDummy extends Entity{
	
	public static final String npcName = "ScaleyDummy";
	
	public ScaleyDummy(GamePanel gp) {
		super(gp);
		
		name = npcName;
		getImage();
		getAttackImage();
		
		if (attacking == true) {
			attacking();
		}
		
	}
	
	public void getImage() {
		
		up1 = setUp("/Scaley/scaley_up_1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Scaley/scaley_up_2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Scaley/scaley_down_1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Scaley/scaley_down_2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Scaley/scaley_left_1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Scaley/scaley_left_2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Scaley/scaley_right_1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Scaley/scaley_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void getAttackImage() {
		
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
