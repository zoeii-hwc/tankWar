package org.game.tankwar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GameClient extends JComponent {
	private int screenWidth;
	private int screenHeight;

	private Tank playerTank;// 玩家坦克
	private boolean stop;
	private List<GameObject>objects=new ArrayList<>();///
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
		Image[]brickImages= {Tools.getImage("brick.png")};
		Image[]iTankImage=new Image[8];
		Image[]eTankImage=new Image[8];
		String [] sub = {"U.png","D.png","L.png","R.png"
				,"LU.png","RU.png","LD.png","RD.png"};
		for (int i = 0; i < iTankImage.length; i++) {
			iTankImage[i]=Tools.getImage("itank"+sub[i]);
			eTankImage[i]=Tools.getImage("etank"+sub[i]);
		}
		playerTank=new Tank(500,100,Direction.DOWN,iTankImage);
		objects.add(playerTank);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				objects.add(new Tank(350 + j * 80, 400 + 80 * i, Direction.UP, true,eTankImage));// 敵方坦克
			}
		}
//		Image image = Tools.getImage("brick.png");
		objects.add(new Wall(250, 150, true, 15,brickImages));
		objects.add(new Wall(150, 200, false, 15,brickImages));//false是縱向
		objects.add(new Wall(800, 200, false, 15,brickImages));
//		
//		objects.add(playerTank);
//		objects.addAll((Collection<GameObject>)walls);
//		objects.addAll((Collection<GameObject>)enemyTanks);
//		for(GameObject object:objects) {
//			object.draw(g);
//		}
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
//		playerTank.draw(g);
//		for (Tank tank : enemyTanks) {
//			tank.draw(g);
//		}
//		for (Wall wall : walls) {
//			wall.draw(g);
//		}
		for (GameObject object : objects) {
			object.draw(g);
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
