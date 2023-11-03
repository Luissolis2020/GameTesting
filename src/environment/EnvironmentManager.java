package environment;

import java.awt.Graphics2D;

import main.GamePanel;

public class EnvironmentManager {
	
	GamePanel gp;
	public Lighting lighting;
	
	public EnvironmentManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setup() {
		
		lighting = new Lighting(gp); //CANNOT BE BIGGER THEN SCREENX OR SCREEN Y (576)
	}
	
	public void update() {
		
		lighting.update();
	}
	
	public void draw(Graphics2D g2) {
		
		lighting.draw(g2);
	}
}
