package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Invisible_Wall extends Entity{

	GamePanel gp;
	
	public static final String objName = "Invisible Wall";
	
	public OBJ_Invisible_Wall(GamePanel gp) {

		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		down1 = setUp("/objects/invisible_wall", gp.tileSize, gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	
	}

}
