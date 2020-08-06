package org.game.tankwar;

import java.awt.Graphics;
import java.awt.Image;

public class Wall extends GameObject {

	protected boolean horizontal;
	protected int bricks;
	//protected Image image;

	Wall(int x, int y, boolean horizontal, int bricks, Image[] image) {
		super(x, y, image);
//		this.x=x;
//		this.y=y;
		this.horizontal = horizontal;
		this.bricks = bricks;
//		image = Tools.getImage("brick.png");
	}

	@Override
	public void draw(Graphics g) {
		if (horizontal) {
			for (int i = 0; i < bricks; i++) {
				g.drawImage(image[0], x + i * width, y, null);
			}
		} else {
			for (int i = 0; i < bricks; i++) {
				g.drawImage(image[0], x, y + i * height, null);

			}
		}
	}
}
