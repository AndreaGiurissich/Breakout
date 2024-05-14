package main;

import javax.swing.*;

import cards.GameOverMenu;
import entities.Ball;
import entities.Brick;
import entities.Paddle;
import utils.BrickManager;
import utils.LevelBuilder;
import utils.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BreakoutGame extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private final static int PLAY_AREA_WIDTH = 630;
	private final static int PLAY_AREA_HEIGHT = 400;

	private Ball ball;
	private Paddle paddle;
	private BrickManager brickManager;
	private Timer timer;
	private GameOverMenu gOmenu;
	private SoundPlayer sound;
	private int score = 0;
	private String levelSelected = "resource/lvl/level1.txt";

	private boolean collided = false;
	public boolean gameRunning = false;
	public boolean movingLeft, movingRight;

	private CardLayoutPanel c;

	public BreakoutGame(CardLayoutPanel c, SoundPlayer sound) {
		this.sound = sound;
		this.c = c;
		initClasses();
		this.setPreferredSize(new Dimension(PLAY_AREA_WIDTH, PLAY_AREA_HEIGHT));

		addKeyListener(this);
		/*
		 * setFocusable(true); setFocusTraversalKeysEnabled(false); this.requestFocus();
		 */
	}

	public void levelSelect(int lvl) {
		switch (lvl) {
		case 1:
			this.levelSelected = "resource/lvl/level1.txt";
			break;
		case 2:
			this.levelSelected = "resource/lvl/level2.txt";
			break;
		case 3:
			this.levelSelected = "resource/lvl/level3.txt";
			break;
			default:		
		}
	}

	public void initClasses() {
		ball = new Ball(295, 370, 1);
		paddle = new Paddle(275, 390, 5);
		LevelBuilder levelBuilder = new LevelBuilder(levelSelected);
		int[][] level = levelBuilder.getLevel();
		brickManager = new BrickManager(level);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		}

	public void draw(Graphics g) {
		if (gameRunning) {
			this.setBackground(Color.black);
			ball.draw(g);
			getPaddle().draw(g);
			getBrickManager().draw(g);

			// Display the score
			g.setColor(Color.WHITE);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Score: " + score, 10, 20);

			}
	}
	
	
	public void restartGame() {
		
		start();
		initClasses();
		ball.reset();
		resetScore();
		
		if (gOmenu != null) {
			remove(gOmenu);
			gOmenu = null;
			revalidate();
			this.requestFocus();
		}

	}

	public void start() {
		timer = new Timer(10, this);
		timer.start();
		gameRunning = true;
	}

	public void stop() {

		timer.stop();
	}

	public void actionPerformed(ActionEvent e) {
		if (gameRunning) {
			ball.move();
			if(movingLeft)
				getPaddle().moveLeft();
			if(movingRight)
				getPaddle().moveRight();
			checkCollisions();
			repaint();
			System.out.println(score);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			movingLeft = true;
		}
		if (key == KeyEvent.VK_RIGHT) {
			movingRight = true;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			movingLeft = false;
		}
		if (key == KeyEvent.VK_RIGHT) {
			movingRight = false;
		}
	}

	public void checkCollision() {
		Rectangle ballBounds = ball.getBounds();

		long lastCollisionTime = 0; // Initialize to 0 to allow the first collision immediately
		int collisionCooldownMillis = 2000; // Set the cooldown period to 1 second (adjust as needed)

		if (ballBounds.intersects(paddle.getBounds())) {
			ball.reflectOffPaddle(paddle.getX(), 80);
			sound.bounceEffect();
		}

		// Check collision walls and roof
		if (ball.getX() < 0 || ball.getX() + ball.SIZE > PLAY_AREA_WIDTH) {
			ball.reflectX();
			sound.bounceEffect();
			
		}
		if (ball.getY() < 0) {
			ball.reflectY();
			sound.bounceEffect();
		}
		if (ball.getY() + ball.SIZE > PLAY_AREA_HEIGHT) {
			gameRunning = false;
			c.changeScreen3();
			stop();
		}
		// Inside your collision detection loop
		if (System.currentTimeMillis() - lastCollisionTime >= collisionCooldownMillis) {
			for (int i = 0; i < brickManager.getBricks().size(); i++) {
				Brick brick = brickManager.getBricks().get(i);
				// Check for collision and handle it as needed
				if (brick.getBounds().intersects(ballBounds)) {
					if (brick.getBrickType() != 0) {
						ball.reflectOffBrick(brick);
						score += 100;
						brickManager.removeBrick();
						sound.breakEffect();
					}
				} // Handle collision actions here

				// Update the last collision time

			}
			lastCollisionTime = System.currentTimeMillis();
		}

	}
	
	private void checkCollisions() {

		if(!collided) {
		checkCollision();
		collided = false;
		}
			if (brickManager.isEmpty()) {
				gameRunning = false;
				c.changeScreen5();
		}
	}
	
	public int getScore() {
		return score;
	}

	public void resetScore() {
		score = 0;
	}
	
	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	public BrickManager getBrickManager() {
		return brickManager;
	}

	public void setBrickManager(BrickManager brickManager) {
		this.brickManager = brickManager;
	}

	public static int getPlayAreaWidth() {
		return PLAY_AREA_WIDTH;
	}

	public static int getPlayAreaHeight() {
		return PLAY_AREA_HEIGHT;
	}

}
