package utils;

import entities.Ball;
import entities.Brick;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrickManager {
    private List<Brick> bricks;
    private final int BRICK_WIDTH = 70;
    private final int BRICK_HEIGHT = 30;

    public BrickManager(int[][] level) {
        bricks = new ArrayList<>();
        
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == 1) {
                    int brickX = j * (BRICK_WIDTH);
                    int brickY = i * (BRICK_HEIGHT);
                    bricks.add(new Brick(brickX, brickY, 1));
                }
                if (level[i][j] == 2) {
                        int brickX = j * (BRICK_WIDTH);
                        int brickY = i * (BRICK_HEIGHT);
                        bricks.add(new Brick(brickX, brickY, 2));
                }
                if (level[i][j] == 3) {
                    int brickX = j * (BRICK_WIDTH);
                    int brickY = i * (BRICK_HEIGHT);
                    bricks.add(new Brick(brickX, brickY, 3));
            }
            }
        }
    }

    public void draw(Graphics g) {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    public boolean isEmpty() {
        return bricks.isEmpty();
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void removeBrick() {
    	for (Iterator<Brick> it = bricks.iterator(); it.hasNext(); ) {
    	    Brick brick = it.next();
    	    if (brick.getBrickType() == 0) {
    	        it.remove();
    	    }
    	}
    }
}
