package org.example.tankwar;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall {
	private int x;
	private int y;
	// 水平或垂直
	private boolean horizontal;
	private int bricks;
	
	private Image image;
	Wall(int x, int y, boolean horizontal, int bricks) {
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.bricks = bricks;

		image = new ImageIcon("assets/images/brick.png").getImage();
	}

	public void draw(Graphics g) {
		if (horizontal) {
			for (int i = 0; i < bricks; i++) {
				g.drawImage(image, x + i * image.getWidth(null), y, null);
			}
		} else {
			for (int i = 0; i < bricks; i++) {
				g.drawImage(image, x, y + i * image.getHeight(null), null);

			}
		}
	}
}
