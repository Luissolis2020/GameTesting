package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class IT_DestructibleWall extends InteractiveTile{

	GamePanel gp;
	
	public IT_DestructibleWall(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setUp("/tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 3;
	}
	
	@Override
	public boolean isCorrectItem(Entity entity) {
		
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_pickaxe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	
	@Override
	public void playSE() {
		gp.playSE(24);
	}
	
	@Override
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	
	@Override
	public Color getParticleColor() {
		Color color = new Color(65, 65, 65);
		return color;
	}
	@Override
	public int getParticleSize() {
		int size = 6; // 6 pixels
		return size;
	}
	@Override
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	@Override
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
//	@Override
//	public void checkDrop() {
//		
//		// CAST A DIE
//		int i = new Random().nextInt(100)+1;
//		
//		// SET THE MONSTER DROP
//		if(i < 50) {
//			dropItem(new OBJ_Coin_Bronze(gp));
//		}
//		if(i >= 50 && i < 75) {
//			dropItem(new OBJ_Heart(gp));
//		}
//		if(i >= 75 && i < 100) {
//			dropItem(new OBJ_SpellSlot(gp));
//		}
//		
//	}

}