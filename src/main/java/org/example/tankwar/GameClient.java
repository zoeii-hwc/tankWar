package org.example.tankwar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GameClient extends JComponent {
	private int screenWidth;
	private int screenHeight;

	private Tank playerTank;// 玩家坦克
	private boolean stop;
	private List<Tank> enemyTanks = new ArrayList<>();// 敵方坦克
	private List<Wall> walls = new ArrayList<>();// 方塊

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
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				enemyTanks.add(new Tank(350 + j * 80, 500 + 80 * i, Direction.UP, true));// 敵方坦克
			}
		}
		walls.add(new Wall(250, 150, true, 15));
		walls.add(new Wall(150, 200, true, 15));
		walls.add(new Wall(800, 200, true, 15));
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
		for (Tank tank : enemyTanks) {
			tank.draw(g);
		}
		for (Wall wall : walls) {
			wall.draw(g);
		}

	}

	public void keyPressed(KeyEvent e) {
		boolean[] dirs = playerTank.getDirs();
		System.out.println(playerTank.getDirs() == dirs);// 測試

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
