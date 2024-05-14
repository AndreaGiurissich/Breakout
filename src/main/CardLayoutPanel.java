package main;

import java.awt.CardLayout;

import javax.swing.JPanel;

import cards.*;
import utils.SoundPlayer;

public class CardLayoutPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	BreakoutGame game;
	CardLayout cardLayout;
	StartMenu menu;
	GameOverMenu gameOverMenu;
	LevelSelect levelMenu;
	GameWin winScreen;
	SoundPlayer soundPlayer;

	public CardLayoutPanel() {
		
		soundPlayer = new SoundPlayer();

		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		game = new BreakoutGame(this, soundPlayer);
		this.add(game, "1");
		
		levelMenu = new LevelSelect(this, game);
		this.add(levelMenu, "4");
		
		menu = new StartMenu(this);
		this.add(menu, "2");
		cardLayout.show(this, "2");
		
		winScreen = new GameWin(this, game);
		this.add(winScreen, "5");
		
		
		gameOverMenu = new GameOverMenu(this, game);
		this.add(gameOverMenu, "3");
		
		this.requestFocus();
	}

	// starts game
	public void changeScreen1() {
		soundPlayer.stopSong();
		soundPlayer.gameStartEffect();
		cardLayout.show(this, "1");
		game.restartGame();
		game.requestFocus();	
	}

	// menu
	public void changeScreen2() {
		soundPlayer.playSong(0);
		cardLayout.show(this, "2");
		menu.requestFocus();
	}
	
	public void changeScreen4() {
		cardLayout.show(this, "4");
		levelMenu.requestFocus();
	}

	// Game Over
	public void changeScreen3() {
		game.gameRunning = false;
		cardLayout.show(this, "3");
		gameOverMenu.requestFocus();
	}

	public void changeScreen5() {
		cardLayout.show(this, "5");
		winScreen.requestFocus();
		
	}

}
