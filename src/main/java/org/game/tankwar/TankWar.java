package org.game.tankwar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

public class TankWar {
	public static GameClient gameClient;/////////////////// question

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		gameClient = new GameClient(1024, 768);
		frame.add(gameClient);
		frame.setTitle("TankWar");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();// 排版方式

		// gameClient.repaint(); //會呼叫paintComponent

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				gameClient.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				gameClient.keyReleased(e);
			}
		});
	}
}
