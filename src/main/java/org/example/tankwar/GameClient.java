package org.example.tankwar;

import java.awt.Dimension;

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
}
