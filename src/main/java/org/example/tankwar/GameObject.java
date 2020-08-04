package org.example.tankwar;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected Image image;

	public GameObject(int x, int y, Image image) {
		this.x = x;
		this.y = y;
		this.image = image;
	}
	abstract void draw(Graphics g);
}
