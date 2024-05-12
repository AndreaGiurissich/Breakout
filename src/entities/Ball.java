package entities;

import java.awt.*;

import main.BreakoutGame;

public class Ball {
	private int x, y, xSpeed, ySpeed, initialSpeed;
	public final int SIZE = 20;

	public Ball(int x, int y, int initialSpeed) {
		this.x = x;
		this.y = y;
		this.xSpeed = initialSpeed;
		this.ySpeed = -initialSpeed;
		this.initialSpeed = initialSpeed;
	}

	public void move() {
		x += xSpeed;
		y += ySpeed;
	}

	public void reflectOffPaddle(int paddleX, int paddleWidth) {
		// Calculate the relative position of the ball on the paddle.
		int relativePosition = x - paddleX;

		int maxSpeed = 3;
		// Calculate a reflection angle based on where the ball hits the paddle.
		// You can adjust these values as needed to control the reflection angle.
		int reflectionAngleFactor = 60; // Adjust this factor for precision
		int reflectionAngle = (relativePosition * reflectionAngleFactor)/ (paddleWidth / 2);

		// Determine the new direction of the ball's xSpeed based on the reflection
		// angle.
		if (relativePosition < paddleWidth / 2) {
			// Ball hit the left half of the paddle.
			xSpeed = -Math.abs((reflectionAngle * maxSpeed) / reflectionAngleFactor);
		} else {
			// Ball hit the right half of the paddle.
			xSpeed = Math.abs((reflectionAngle * maxSpeed) / reflectionAngleFactor);
		}

		// Invert the ySpeed to change vertical direction (assuming ySpeed represents
		// vertical velocity).
		ySpeed = -ySpeed;
	}

	public void reflectOffBrick(Brick brick) {
		// Calculate the center of the ball
		int ballCenterX = x + SIZE / 2;
		int ballCenterY = y + SIZE / 2;

		// Calculate the half-width and half-height of the brick
		int brickHalfWidth = Brick.getWIDTH() / 2;
		int brickHalfHeight = Brick.getHEIGHT() / 2;

		// Calculate the center of the brick
		int brickCenterX = brick.getBrickX() + brickHalfWidth;
		int brickCenterY = brick.getBrickY() + brickHalfHeight;

		// Calculate the offset between the ball's center and the brick's center
		int offsetX = ballCenterX - brickCenterX;
		int offsetY = ballCenterY - brickCenterY;

		// Calculate the combined half-widths and half-heights
		int combinedHalfWidths = SIZE / 2 + brickHalfWidth;
		int combinedHalfHeights = SIZE / 2 + brickHalfHeight;

		// Check if the ball is colliding with the brick
		if (Math.abs(offsetX) <= combinedHalfWidths && Math.abs(offsetY) <= combinedHalfHeights) {
			// Determine which side the ball is hitting
			int overlapX = combinedHalfWidths - Math.abs(offsetX);
			int overlapY = combinedHalfHeights - Math.abs(offsetY);

			if (overlapX > overlapY) {
				// Collision is vertical (top or bottom)
				if (offsetY > 0) {
					// Ball is hitting the top of the brick
					this.reflectY();
				} else {
					// Ball is hitting the bottom of the brick
					this.reflectY();
				}
			} else {
				// Collision is horizontal (left or right)
				if (offsetX > 0) {
					// Ball is hitting the left side of the brick
					this.reflectX();
				} else {
					// Ball is hitting the right side of the brick
					this.reflectX();
				}
			}

			// Update brick type and remove if necessary
			brick.setBrickType(brick.getBrickType() - 1);
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval(x, y, SIZE, SIZE);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, SIZE, SIZE);
	}

	public void reflectX() {
		xSpeed = -xSpeed;
	}

	public int getX() {
		return x;
	}

	public void reflectY() {
		ySpeed = -ySpeed;
	}

	public int getY() {
		return y;
	}

	public void reset() {
		this.x = 295;
		this.y = 370;
		this.initialSpeed = 1;
	}
}
