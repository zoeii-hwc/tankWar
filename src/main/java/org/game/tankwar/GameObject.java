package org.game.tankwar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class GameObject {
	protected int oldX;
	protected int oldY;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Image[] image;
	protected boolean alive;

	public GameObject(int x, int y, Image[] image) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.alive=true;
		width=image[0].getWidth(null);
		height=image[0].getHeight(null);
	}
	public Rectangle getRectangle() {
		return new Rectangle(x,y,width,height);
	}
	
	
	public abstract void draw(Graphics g);
}
