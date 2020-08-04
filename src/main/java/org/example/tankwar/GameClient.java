package org.example.tankwar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GameClient extends JComponent {
	private int screenWidth;
	private int screenHeight;

	private Tank playerTank;
	private boolean stop;

	GameClient() {
		this(800, 600);
	}

	public GameClient(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));

		init();

		new Thread(() -> {
			while (!stop) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void init() {
		playerTank = new Tank(400, 100, Direction.DOWN);
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	@Override
	public void paintComponent(Graphics g) {
//		g.drawImage(new ImageIcon("assets\\images\\itankD.png").getImage(), getCenterPosX(47), getCenterPosY(47), null);
//		g.drawImage(playerTank.getImage(),playerTank.getX(),playerTank.getY(),null);
		playerTank.draw(g);
	}

	public void keyPressed(KeyEvent e) {
		boolean[] dirs = playerTank.getDirs();
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:
			dirs[0] = true;
			break;
		case KeyEvent.VK_DOWN:
			dirs[1] = true;
			break;
		case KeyEvent.VK_LEFT:
			dirs[2] = true;
			break;
		case KeyEvent.VK_RIGHT:
			dirs[3] = true;
			break;
		default:
		}
//		playerTank.move();
	}

	public void keyReleased(KeyEvent e) {
		boolean[] dirs = playerTank.getDirs();
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:
			dirs[0] = false;
			break;
		case KeyEvent.VK_DOWN:
			dirs[1] = false;
			break;
		case KeyEvent.VK_LEFT:
			dirs[2] = false;
			break;
		case KeyEvent.VK_RIGHT:
			dirs[3] = false;
			break;
		default:
		}
//		playerTank.move();
	}
}
