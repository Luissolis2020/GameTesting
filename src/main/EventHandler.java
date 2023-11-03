package main;

import data.Progress;
import entity.Entity;

public class EventHandler {

		GamePanel gp;
		EventRect eventRect[][][];
		Entity eventMaster;
	
		int previousEventX, previousEventY;
		boolean canTouchEvent = true;
		int tempMap, tempCol, tempRow;
		boolean complete = false;
	
		public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventMaster = new Entity(gp);
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
		
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		
		setDialogue();
	}
	
	public void setDialogue() {
		
		eventMaster.dialogue[0][0] = "You fall into a pit!";
		eventMaster.dialogue[1][0] = "You drink the water.\nYour life has been recovered! \n" + "(Your progress has been saved)";
	}
		
	public void checkEvent() {
		
		// Check if the player character is more than 1 tile away from the last event
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			//28 17 so one less of each one
			if(hit(0, 27, 16, "right") == true) { damagePit(gp.dialogueState); }
//			if(hit(23, 19, "any") == true) {	damagePit(23, 19,gp.dialogueState); }
//			if(hit(27, 16, "right") == true) {	teleport(27, 16, gp.dialogueState); }
			else if(hit(0, 23, 12, "up") == true) { healingPool(gp.dialogueState); }
			else if(hit(0, 10, 39, "any") == true) { changeMap(1, 12, 13, gp.indoor); } // to merchants
			else if(hit(1, 12, 13, "any") == true) { changeMap(0, 10, 39, gp.outside); } // to outside
			else if(hit(1, 12, 9, "up") == true) { speak(gp.npc[1][0]); }
			else if(hit(0, 36, 30, "any") == true) { battleEvent(2, 10, 10, gp.turnBattleState); }
			else if(hit(0, 12, 9, "any") == true) { changeMap(3, 9, 41, gp.dungeon); } // to dungeon
			else if(hit(3, 9, 41, "any") == true) { changeMap(0, 12, 9, gp.outside); } // to outside
			else if(hit(3, 8, 7, "any") == true) { changeMap(4, 26, 41, gp.dungeon); } // to B2
			else if(hit(4, 26, 41, "any") == true) { changeMap(3, 8, 7, gp.dungeon); } // to B1
			else if(hit(4, 25, 27, "any") == true) { skeletonLord(); } // BOSS
		}
	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
	
		return hit;
	}
	
	public void teleport(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!";
		gp.player.worldX = gp.tileSize*37;
		gp.player.worldY = gp.tileSize*10;
	}
	
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.playSE(6);
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life -= 1; 
		canTouchEvent = false;
	}
	
	public void healingPool(int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			eventMaster.startDialogue(eventMaster, 1);
			gp.player.life = gp.player.maxLife;
			gp.player.spellSlot = gp.player.maxSpellSlot;
			gp.aSetter.setMonster();
			gp.saveLoad.save();
		}
	}
	
	public void changeMap(int map, int col, int row, int area) {
		
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		canTouchEvent = false;
		gp.playSE(13);
	}
	
	public void speak(Entity entity) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
	
	public void skeletonLord() {
		
		if(gp.bossBattleOn == false && Progress.skeletonLordDefeated == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.skeletonLord;
		}
	}
	
	public void battleEvent(int map, int col, int row, int gameState) {
		
		if(complete == false) {
		
			//	gp.gameState = gp.dialogueState;
			//	gp.ui.currentDialogue = "You entered a Battle!";
				gp.currentMap = map;
				gp.player.worldX = gp.tileSize * col;
				gp.player.worldY = gp.tileSize * row;
				gp.player.turnBattleState = true;
				canTouchEvent = false;
				gp.ui.Player1 = gp.player;
				gp.ui.Player2 = gp.scaley;
				gp.ui.Player3 = null;
				gp.stopMusic();
				gp.playMusic(19);
				gp.gameState = gameState;
		}
		
	}
}
