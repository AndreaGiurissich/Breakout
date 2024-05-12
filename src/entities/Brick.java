package entities;

import java.awt.*;

public class Brick {
    private int x, y, brickType;
    private final static int WIDTH = 70;
    private final static int HEIGHT = 30;

    public Brick(int x, int y, int brickType) {
        this.x = x;
        this.y = y;
        this.brickType = brickType;
    }

    public void draw(Graphics g) {
        switch (brickType) {
            case 1:
                g.setColor(Color.green);
                break;
            case 2:
                g.setColor(Color.red);
                break;
            case 3:
                g.setColor(Color.blue);
                break;
                default:
                break;
        }

        g.fillRect(x, y, getWIDTH(), HEIGHT);
        g.setColor(Color.black);
        g.drawRect(x, y, getWIDTH(), HEIGHT);
    }

    
    
    public int getBrickX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getBrickY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBounds() {
        return new Rectangle(x, y, getWIDTH(), HEIGHT);
    }
    
    public int getBrickType() {
    	return brickType;
    }
    
    public static int getWIDTH() {
		return WIDTH;
	}

	public void setBrickType(int i) {
    	this.brickType = i;
    }

	public static int getHEIGHT() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}
    
}

