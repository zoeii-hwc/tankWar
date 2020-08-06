package org.game.tankwar;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * 坦克物件
 */
public class Tank extends GameObject {
//	private int x;
//	private int y;
	private int speed;
	private Direction direction;
	private boolean[] dirs = new boolean[4];
	private boolean enemy; // 敵方坦克

	// 維持我方坦克
	public Tank(int x, int y, Direction direction, Image[] iTankImage) {
		this(x, y, direction, false, iTankImage);
	}

	// 產生敵方坦克
	public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
		super(x,y,image);
		this.x = x;
		this.y = y;
		this.direction = direction;
		speed = 5;
		this.enemy = enemy;
	}

//	public int getX() {
//		return x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean[] getDirs() {
		return dirs;
	}

	public void setDirs(boolean[] dirs) {
		this.dirs = dirs;
	}

	public void move() {
		switch (direction) {
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;
			break;
		case LEFT:
			x -= speed;
			break;
		case RIGHT:
			x += speed;
			break;

		default:
			break;
		}
	}

	private void determinDirection() {
//		0:上 1:下 2:左 3:右
		if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) {
			direction = Direction.UP_LEFT;
		} else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) {
			direction = Direction.UP_RIGHT;
		} else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) {
			direction = Direction.DOWN_LEFT;
		} else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) {
			direction = Direction.DOWN_RIGHT;
		} else if (dirs[0] && !dirs[3] && !dirs[1] && !dirs[2]) {
			direction = Direction.UP;
		} else if (dirs[1] && !dirs[3] && !dirs[0] && !dirs[2]) {
			direction = Direction.DOWN;
		} else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) {
			direction = Direction.LEFT;
		} else if (dirs[3] && !dirs[1] && !dirs[0] && !dirs[2]) {
			direction = Direction.RIGHT;
		}
	}

	public Image getImage() {

		String name = enemy ? "etank" : "itank";// 更改敵方坦克顯示

		if (direction == Direction.UP) {
			return new ImageIcon("assets\\images\\" + name + "U.png").getImage();
		}
		if (direction == Direction.DOWN) {
			return new ImageIcon("assets\\images\\" + name + "D.png").getImage();
		}
		if (direction == Direction.LEFT) {
			return new ImageIcon("assets\\images\\" + name + "L.png").getImage();
		}
		if (direction == Direction.RIGHT) {
			return new ImageIcon("assets\\images\\" + name + "R.png").getImage();
		}
//		---
		if (direction == Direction.UP_LEFT) {
			return new ImageIcon("assets\\images\\" + name + "LU.png").getImage();
		}
		if (direction == Direction.UP_RIGHT) {
			return new ImageIcon("assets\\images\\" + name + "RU.png").getImage();
		}
		if (direction == Direction.DOWN_RIGHT) {
			return new ImageIcon("assets\\images\\" + name + "RD.png").getImage();
		}
		if (direction == Direction.DOWN_LEFT) {
			return new ImageIcon("assets\\images\\" + name + "LD.png").getImage();
		}
		return null;
	}

	public void draw(Graphics g) {
		if (!isStop()) {
			determinDirection();
			move();
		}
		g.drawImage(getImage(), x, y, null);
	}

	public boolean isStop() {
		for (boolean dir : dirs) {
			if (dir) {
				return false;
			}
		}
		return true;
	}
}
