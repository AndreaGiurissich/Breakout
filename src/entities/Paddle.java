package entities;

import java.awt.*;

import main.BreakoutGame;

public class Paddle {
    private int x, y, speed;
    private final int WIDTH = 80;
    private final int HEIGHT = 10;

    public Paddle(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void moveLeft() {
    	
    	if(x > 0)
    		x -= speed;
    }

    public void moveRight() {
       
        if(x + WIDTH < BreakoutGame.getPlayAreaWidth())
        	x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

	public int getX() {
		return x;
	}
}
