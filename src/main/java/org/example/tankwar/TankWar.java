package org.example.tankwar;

import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

@SuppressWarnings("unused")
public class TankWar {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GameClient gameClient = new GameClient(1024,768);
		frame.add(new GameClient());
		frame.setTitle("TankWar");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();//排版方式
		
		gameClient.repaint(); //會呼叫paintComponent
	}
}
