package org.example.tankwar;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GameClient extends JComponent {
	private int screenWidth;
	private int screenHeight;

	GameClient() {
		this(1024, 768);
	}

	public GameClient(int screenWidth, int screenHeight) {
//		super();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.setPreferredSize(new Dimension(screenWidth, screenWidth));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(new ImageIcon("assets\\images\\itankD.png").getImage(),
				400,100,null);//x軸,y軸,observer
	}
}
