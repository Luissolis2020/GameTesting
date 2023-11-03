package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

public class TurnBasedCombat{

	GamePanel gp;
	Graphics2D g2;
	int commandNum = 0;
	int subState = 0;
	Font arial_40, arial_80B;
	boolean damageDone;
	int damage;
	public int target = 0;
	boolean allowAttack = false;
	
	boolean rythianAttack = false;
	boolean scaleyAttack = false;
	
	public TurnBasedCombat(GamePanel gp) {
		
		this.gp = gp;	
		this.g2 = gp.ui.g2;
		this.commandNum = gp.ui.commandNum;
		this.subState = gp.ui.subState;
		this.target = gp.ui.target;
		
	}
	
	public void fight_options() {
		
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(30F));
		
			int textX = gp.tileSize*3;
			int textY = gp.tileSize*10;
			String text = "Attack";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 0) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					gp.ui.subState = 1;
				}
			}
			
			textX = (int)(gp.tileSize*8.5);
			text = "Items";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 1) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					gp.ui.subState = 2;
				}
			}
			
			textX = gp.tileSize*14;
			text = "Run";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 2) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					gp.ui.subState = 3;
				}
			}
	}
	
	public void fight_attacks() {
		
		int textX = gp.tileSize*2;
		int textY = gp.tileSize*10;
		g2.setFont(g2.getFont().deriveFont(25F));
		
		if(gp.ui.currentPlayer == "Rythian") {						//RYTHIAN
			String text = "Sword Swipe";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 0) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					gp.ui.subState = 4;
				}
			}
			
			textX = (int)(gp.tileSize*6.5);
			text = "Fire Ball";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 1) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 2;
				}
			}
			
			textX = gp.tileSize*10;
			text = "Lay on Hands";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 2) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 3;
				}
			}

			textX = gp.tileSize*15-20;
			text = "Multi Attack";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 3) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 3;
				}
			}
		}
		else if(gp.ui.currentPlayer == "Scaley") {						//SCALEY
			
			 textX = gp.tileSize*2;
			 textY = gp.tileSize*10;
			g2.setFont(g2.getFont().deriveFont(25F));
			
			String text = "Punch";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 0) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 1;
				}
			}
			
			textX = (int)(gp.tileSize*5.5);
			text = "Water Whip";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 1) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 2;
				}
			}
			
			textX = gp.tileSize*10;
			text = "Rock Throw";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 2) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 3;
				}
			}
			
			textX = gp.tileSize*14;
			text = "Fury of Blows";
			g2.drawString(text, textX, textY);
			
			if(commandNum == 3) {
				g2.drawString(">", textX-20, textY);
				if(gp.keyH.enterPressed == true) {
					subState = 3;
				}
			}
		}
		
		 textX = (int)(gp.tileSize*15.5);
		 textY = (int)(gp.tileSize*11.5);
		 String text = "|ESC| BACK";
		 g2.drawString(text, textX, textY);
	}
	
	public void fight_items() {
		
	}
	
	public void fight_run() {
		
		gp.player.worldX = gp.tileSize * 36;
		gp.player.worldY = gp.tileSize * 29;
		gp.player.turnBattleState = false;
		gp.eHandler.complete = true;
		gp.ui.counter = 0;
		gp.ui.subState = 0;
		gp.stopMusic();
		gp.playMusic(0);
		gp.gameState = gp.playState;
		gp.currentMap = 0;
	}
	
	public void chooseTarget() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(30F));
		
		String text = "Choose who to attack";
		int textX = gp.ui.getXforCenteredText(text);
		int textY = gp.tileSize*10;

		g2.drawString(text, textX-15, textY);
				
		if(commandNum == 0 && gp.monster[gp.currentMap][1] != null) {
			g2.drawRect(gp.tileSize*16-24, gp.tileSize, gp.tileSize*2, gp.tileSize*2-30);
			if(gp.keyH.enterPressed == true) {
				gp.ui.commandNum = 0;
				gp.ui.target = 1;
				gp.ui.subState = 5;
			}
		}else if(commandNum == 0 && gp.monster[gp.currentMap][1] == null) {
			gp.ui.commandNum = 1;
		}
		
		if(commandNum == 1 && gp.monster[gp.currentMap][2] != null) {
			g2.drawRect(gp.tileSize*15-24, (int)(gp.tileSize*3.5)-5, gp.tileSize*2, gp.tileSize*2-30);
			if(gp.keyH.enterPressed == true) {
				gp.ui.commandNum = 0;
				gp.ui.target = 2;
				gp.ui.subState = 5;
			}
		} else if(commandNum == 1 && gp.monster[gp.currentMap][2] == null) {
			gp.ui.commandNum = 2;
		}
		
		if(commandNum == 2 && gp.monster[gp.currentMap][3] != null) {
			g2.drawRect(gp.tileSize*16-24, gp.tileSize*6-10, gp.tileSize*2, gp.tileSize*2-30);
			if(gp.keyH.enterPressed == true) {
				gp.ui.commandNum = 0;
				gp.ui.target = 3;
				gp.ui.subState = 5;
			}
		}else if(commandNum == 2 && gp.monster[gp.currentMap][3] == null) {
			gp.ui.commandNum = 0;
		}
	}
	
	public void attack(int i) {
		
		g2.setColor(new Color(139, 0, 0));
		g2.setFont(g2.getFont().deriveFont(20F));
		
		String text = "";
		int textX = 0;
		int textY = 0;
		
		if(i == 1) {
			textX = gp.tileSize*18;
			textY = gp.tileSize*0+33;
		}
		if(i == 2) {
			textX = gp.tileSize*17;
			textY = gp.tileSize*3+5;
		}
		if(i == 3) {
			textX = gp.tileSize*18;
			textY = gp.tileSize*5+24;
		}
		
		gp.ui.counter++;
			
		damage = gp.player.attack - gp.monster[gp.currentMap][i].defense;
			
		text = "-" + damage;
		g2.drawString(text, textX, textY);
			
		if(damage < 0) {
			damage = 0;
		}
			
		if(gp.ui.counter < 2) {
		gp.monster[gp.currentMap][i].life -= damage;
		gp.monster[gp.currentMap][i].invincible = true;
		}
			
		if(gp.ui.counter == 40) {
			gp.ui.counter = 0;
			gp.ui.subState = 6;	
		}
		
		if(gp.monster[gp.currentMap][i].life <= 0) {
			gp.monster[gp.currentMap][i].dying = true;
		}
	}
	
	public void waitForPlayer() {
		
	}
	
	public void enemyAttackTurn() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(30F));
		
		String text = "Enemy's Turn";
		int textX = gp.ui.getXforCenteredText(text);
		int textY = gp.tileSize*10;

		g2.drawString(text, textX-15, textY);
		
		gp.ui.counter++;
		
		if(gp.ui.counter == 50) {
			
		
		if(gp.monster[gp.currentMap][1] != null || gp.monster[gp.currentMap][2] != null || gp.monster[gp.currentMap][3] != null) {
		
			int choosePlayer = 0;
			int choosePlayer2 = 0;
			int choosePlayer3 = 0;
			
			Random random = new Random();
			
			int min = 0;
			int max = 0;
			
			if(gp.ui.Player1 != null && gp.ui.Player2 != null && gp.ui.Player3 != null) {
				
				min = 1;
				max = 3;
				
				choosePlayer = random.nextInt(max + min)+min;							// 1 - 3
				choosePlayer2 = random.nextInt(max + min)+min;
				choosePlayer3 = random.nextInt(max + min)+min;
			} 
			else if(gp.ui.Player1 != null && gp.ui.Player2 != null && gp.ui.Player3 == null) {
				
				min = 1;
				max = 1;
				
				choosePlayer = random.nextInt(max + min)+min;							// 1 - 2
				choosePlayer2 = random.nextInt(max + min)+min;
				choosePlayer3 = random.nextInt(max + min)+min;
			}
			else if(gp.ui.Player1 != null && gp.ui.Player2 == null && gp.ui.Player3 == null) {
				choosePlayer = 1;
				choosePlayer2 = 1;
				choosePlayer3 = 1;
			}
			
			if(gp.monster[gp.currentMap][1] == null) {
				choosePlayer = 0;
			}
			if(gp.monster[gp.currentMap][2] == null) {
				choosePlayer2 = 0;
			}
		    if(gp.monster[gp.currentMap][3] == null) {
		    	choosePlayer3 = 0;
			}
			
			System.out.println(choosePlayer);
			System.out.println(choosePlayer2);
			System.out.println(choosePlayer3);
			
			int monster1;
			int monster2;
			int monster3;
			
			if(choosePlayer == 1) {
				monster1 = gp.monster[gp.currentMap][1].attack - gp.ui.Player1.defense;
				
				if(monster1 <= 0) {
					monster1 = 1;
				}
				
				gp.ui.Player1.life -= monster1;
			} 
			else if(choosePlayer == 2) {
				monster1 = gp.monster[gp.currentMap][1].attack - gp.ui.Player2.defense;
				
				if(monster1 <= 0) {
					monster1 = 1;
				}
				
				gp.ui.Player2.life -= monster1;
			}
			else if(choosePlayer == 3) {
				monster1 = gp.monster[gp.currentMap][1].attack - gp.ui.Player3.defense;
				
				if(monster1 <= 0) {
					monster1 = 1;
				}
				
				gp.ui.Player3.life -= monster1;
			}
			
			if(choosePlayer2 == 1) {
				monster2 = gp.monster[gp.currentMap][2].attack - gp.ui.Player1.defense;
				
				if(monster2 <= 0) {
					monster2 = 1;
				}
				
				gp.ui.Player1.life -= monster2;
			} 
			else if(choosePlayer2 == 2) {
				monster2 = gp.monster[gp.currentMap][2].attack - gp.ui.Player2.defense;
				
				if(monster2 <= 0) {
					monster2 = 1;
				}
				
				gp.ui.Player2.life -= monster2;
			}
			else if(choosePlayer2 == 3) {
				monster2 = gp.monster[gp.currentMap][2].attack - gp.ui.Player3.defense;
				
				if(monster2 <= 0) {
					monster2 = 1;
				}
				
				gp.ui.Player3.life -= monster2;
			}
			
			if(choosePlayer3 == 1) {
				monster3 = gp.monster[gp.currentMap][3].attack - gp.ui.Player1.defense;
				
				if(monster3 <= 0) {
					monster3 = 1;
				}
				
				gp.ui.Player1.life -= monster3;
			} 
			else if(choosePlayer3 == 2) {
				monster3 = gp.monster[gp.currentMap][3].attack - gp.ui.Player2.defense;
				
				if(monster3 <= 0) {
					monster3 = 1;
				}
				
				gp.ui.Player2.life -= monster3;
			}
			else if(choosePlayer3 == 3) {
				monster3 = gp.monster[gp.currentMap][3].attack - gp.ui.Player3.defense;
				
				if(monster3 <= 0) {
					monster3 = 1;
				}
				
				gp.ui.Player3.life -= monster3;
			}	
			
		} else {
			
			gp.ui.subState = 0;
		}
		
	}
		
		if(gp.ui.counter == 60) {
			gp.ui.counter = 0;
			gp.ui.subState = 0;
			allowAttack = false;	
		}
	}
	
}
