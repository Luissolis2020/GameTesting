package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.MikeDummy;
import entity.NPC_IntroMerch;
import entity.PlayerDummy;
import entity.ScaleyDummy;
import monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;
import object.OBJ_Invisible_Wall;

public class CutsceneManager {

	GamePanel gp;
	Graphics2D g2;
	public int sceneNum;
	public int scenePhase;
	int counter = 0;
	float alpha = 0f;
	int y;
	String endCredit;
	public boolean dialogue = false;
	
	// Scene Number
	public final int NA = 0;
	public final int skeletonLord = 1;
	public final int ending = 2;
	public final int intro = 3;
	public final int appleMerch = 4;
	
	public CutsceneManager(GamePanel gp) {
		this.gp = gp;
		
		endCredit = "Made by \n"
				+ "Luis Solis"
				+ "\n\n\n\n\n\n\n\n\n\n"
				+ "Special Thanks to \n"
				+ "Brandon\n"
				+ "Ruben\n"
				+ "Derek\n"
				+ "Corey\n"
				+ "David\n"
				+ "Julius\n"
				+ "Sal\n"
				+ "for their support\n\n\n\n\n\n"
				+ "Thanks you for playing!";
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(sceneNum) {
		case skeletonLord: scene_skeletonLord(); break;
		case ending: scene_ending(); break;
		case intro: scene_intro(); break;
		case appleMerch: scene_appleMerch(); break;
		}
	}
	
	public void scene_skeletonLord() {
		
		if(scenePhase == 0) {
			gp.bossBattleOn = true;
			
			// Shut the iron door
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*25;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*28;
					gp.obj[gp.currentMap][i].temp = true;
					gp.playSE(25);
					break;
				}
			}
			
			//Search a vacant slot for the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
					gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
					gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
					gp.npc[gp.currentMap][i].direction = gp.player.direction;
					break;
				}
			}
			
			gp.player.drawing = false;
			scenePhase++;
		}
		
		if(scenePhase == 1) {
			
			gp.player.worldY -= 2;
			
			if(gp.player.worldY < gp.tileSize*18) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 2) {
			
			for(int i = 0; i < gp.monster[1].length; i++) {
				
				if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName) {
					
					gp.monster[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.monster[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		
		if(scenePhase == 3) {
			
			// The boss speaks
			gp.ui.drawDialogueScreen();
		}
		
		if(scenePhase == 4) {
			
			// Return to the player
			
			// Search the dummy
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
					// Restore the players position
					gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
					gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
					// Delete the dummy
					gp.npc[gp.currentMap][i] = null;
					break;
				}
			}
			
			//Start drawing the player
			gp.player.drawing = true;
			
			//Reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
			
			// Change the music
			gp.stopMusic();
			gp.playMusic(26);
		}
		
	}
	
	public void scene_ending() {
		
		if(scenePhase == 0) {
			
			gp.stopMusic();
			gp.ui.npc = new OBJ_BlueHeart(gp);
			scenePhase++;
		}
		if(scenePhase == 1) {
			
			//Display dialogues
			gp.ui.drawDialogueScreen();
		}
		if(scenePhase == 2) {
			
			//Play the fan fare
			gp.playSE(4);
			scenePhase++;
		}
		if(scenePhase == 3) {
			
			// Wait until the sound effect ends
			if(counterReached(300) == true) {
				scenePhase++;
			}
			
		}
		if(scenePhase == 4) {
			
			// The screen gets darker
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			drawBlackBackGround(alpha);
			
			if(alpha == 1) {
				alpha = 0;
				scenePhase++;
			}
		}
		if(scenePhase == 5) {
			
			drawBlackBackGround(1f);
			
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			
			String text = "After the fierce battle with the Skeleton Lord,\n"
						+ "Rythian finally found the legendary treasure.\n"
						+ "But this is not the end of his journey.\n"
						+ "Rythian's adventure has just begun!";
			drawString(alpha, 32f, 200, text, 70);
			
			if(counterReached(600) == true) {
				gp.playMusic(0);
				scenePhase++;
			}
			
		}
		if(scenePhase == 6) {
			
			drawBlackBackGround(1f);
			
			drawString(1f, 120f, gp.screenHeight/2, "D&D at 5am", 40);
			
			if(counterReached(480) == true) {
				scenePhase++;
			}
		}
		if(scenePhase == 7) {
			
			drawBlackBackGround(1f);
			
			y = gp.screenHeight/2;
			drawString(1f, 38f, y, endCredit, 40);
			
			if(counterReached(480) == true) {
				scenePhase++;
			}
		}
		if(scenePhase == 8) {
			
			drawBlackBackGround(1f);
			
			// Scrolling the credit
			y--;
			drawString(1f, 38f, y, endCredit, 40);
			
			if(counterReached(1500) == true) {
				
				//Reset
				sceneNum = NA;
				scenePhase = 0;
				gp.gameState = gp.titleState;
				
				gp.resetGame(true);
			}
		}
	}
	
	public void scene_intro() {

		if(scenePhase == 0) {
			
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new ScaleyDummy(gp);
					gp.npc[gp.currentMap][i].worldX = 19;
					gp.npc[gp.currentMap][i].worldY = 25;
					gp.npc[gp.currentMap][i].direction = "right";
					break;
				}
			}
			
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] == null) {
					gp.npc[gp.currentMap][i] = new MikeDummy(gp);
					gp.npc[gp.currentMap][i].worldX = 19;
					gp.npc[gp.currentMap][i].worldY = 23;
					gp.npc[gp.currentMap][i].direction = "up";
					break;
				}
			}
			
			// Put down barriers
			for(int i = 0; i < gp.obj[1].length; i++) {
							
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = new OBJ_Invisible_Wall(gp);
				gp.obj[gp.currentMap][i].worldX = gp.tileSize*23;
				gp.obj[gp.currentMap][i].worldY = gp.tileSize*23;
				gp.obj[gp.currentMap][i].temp = true;
				break;
			}
		}
			
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Invisible_Wall(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*23;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*24;
					gp.obj[gp.currentMap][i].temp = true;
					break;
				}
			}
			
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Invisible_Wall(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*23;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*25;
					gp.obj[gp.currentMap][i].temp = true;
					break;
				}
			}
			
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Invisible_Wall(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*23;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*26;
					gp.obj[gp.currentMap][i].temp = true;
					break;
				}
			}
			
			for(int i = 0; i < gp.obj[1].length; i++) {
				
				if(gp.obj[gp.currentMap][i] == null) {
					gp.obj[gp.currentMap][i] = new OBJ_Invisible_Wall(gp);
					gp.obj[gp.currentMap][i].worldX = gp.tileSize*23;
					gp.obj[gp.currentMap][i].worldY = gp.tileSize*27;
					gp.obj[gp.currentMap][i].temp = true;
					break;
				}
			}
			
			drawBlackBackGround(1f);
			
			//Fade Text in
			
			alpha += 0.005f;
			if(alpha > 1f) {
				alpha = 1f;
			}
			
			String text = "A Bag of Magic Wands...\n"
						+ "The Harpers sent out 3 agents to test the items.\n"
						+ "The journey begins with a simple task.\n"
						+ "If only it were so easy...";
			drawString(alpha, 32f, 200, text, 70);
			
			if(counterReached(600) == true) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 1) {
			
			// Fade Text Out
			
			drawBlackBackGround(1f);
	
			alpha -= 0.005f;
			if(alpha < 0f) {
				alpha = 0f;
			}
			
			String text = "A Bag of Magic Wands...\n"
					+ "The Harpers sent out 3 agents to test the items.\n"
					+ "The journey begins with a simple task.\n"
					+ "If only it were so easy...";
			
			drawString(alpha, 32f, 200, text, 70);
			
			if(counterReached(300) == true) {
				alpha = 1f;
				scenePhase++;
			}
		}
		
		if(scenePhase == 2) {
			
			//Fade out background
			
			alpha -= 0.005f;
			if(alpha < 0f) {
				alpha = 0f;
			}
			
			drawBlackBackGround(alpha);
			
			if(alpha == 0f) {
				scenePhase++;
			}
		}
		
		if(scenePhase == 3) {
			
			//Reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
		}
	}
	
	public void scene_appleMerch() {
		
		if(scenePhase == 0) {
			// Remove Barriers
			gp.removeTempEntity();
			
			for(int i = 0; i < gp.npc[1].length; i++) {
				
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name == NPC_IntroMerch.npcName) {
					
					gp.npc[gp.currentMap][i].sleep = false;
					gp.ui.npc = gp.npc[gp.currentMap][i];
					scenePhase++;
					break;
				}
			}
		}
		
		if(scenePhase == 1) {
			
			//Play Dialogue
			gp.ui.drawDialogueScreen();
		}
		
		if(scenePhase == 2) {
			scenePhase++;
			for(int i = 0; i < gp.npc[1].length; i++) {
				if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name == NPC_IntroMerch.npcName) {
					gp.npc[gp.currentMap][i].dialogueSet++;
				}
			}
		}
		
		if(scenePhase == 3) {
			gp.ui.drawDialogueScreen();
		}
		
		if(scenePhase == 4) {
			
			//Reset
			sceneNum = NA;
			scenePhase = 0;
			gp.gameState = gp.playState;
		}
	}
	
	public boolean counterReached(int target) {
		
		boolean counterReached = false;
		counter++;
		
		if(counter > target) {
			counterReached = true;
			counter = 0;
		}
		
		return counterReached;
	}
	
	public void drawBlackBackGround(float alpha) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(fontSize));
		
		for(String line: text.split("\n")) {
			int x = gp.ui.getXforCenteredText(line);
			g2.drawString(line, x, y);
			y += lineHeight;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
}
