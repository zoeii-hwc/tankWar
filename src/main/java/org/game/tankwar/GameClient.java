package org.game.tankwar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComponent;

public class GameClient extends JComponent {
	private int screenWidth;
	private int screenHeight;
	public static Image[] bulletImage = new Image[8];

	private Tank playerTank;// 玩家坦克
	private boolean stop;
	private List<GameObject> objects = new ArrayList<>();///

//	Image[] bullet = new Image[8];

	GameClient() {
		this(800, 600);
	}

	public List<GameObject> getGameObjects() {
		return objects;
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

	public void addGameObject(GameObject object) {
		objects.add(object);
	}

	public void init() {
		Image[] brickImages = { Tools.getImage("brick.png") };
		Image[] iTankImage = new Image[8];
		Image[] eTankImage = new Image[8];

		String[] sub = { "U.png", "D.png", "L.png", "R.png", "LU.png", "RU.png", "LD.png", "RD.png" };
		for (int i = 0; i < iTankImage.length; i++) {
			iTankImage[i] = Tools.getImage("itank" + sub[i]);
			eTankImage[i] = Tools.getImage("etank" + sub[i]);
			bulletImage[i] = Tools.getImage("missile" + sub[i]);
		}
		playerTank = new Tank(500, 100, Direction.DOWN, iTankImage);
		objects.add(playerTank);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				objects.add(new Tank(350 + j * 80, 400 + 80 * i, Direction.UP, true, eTankImage));// 敵方坦克
			}
		}

		objects.add(new Wall(250, 150, true, 15, brickImages));
		objects.add(new Wall(150, 200, false, 15, brickImages));// false是縱向
		objects.add(new Wall(800, 200, false, 15, brickImages));

	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (GameObject object : objects) {
			object.draw(g);
		}
		Iterator<GameObject>iterator=objects.iterator();
		while(iterator.hasNext()) {
			if(!iterator.next().alive) {
				iterator.remove();
			}
		}
		System.out.println(getGameObjects().size());
	}
	
	private int getCenterPosX(int width) {
		return(screenWidth-width)/2;
	}
	private int getCenterPosY(int height) {
		return(screenHeight-height)/2;
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
		case KeyEvent.VK_CONTROL:
			playerTank.fire();
			break;
		default:
		}
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
	}
}
