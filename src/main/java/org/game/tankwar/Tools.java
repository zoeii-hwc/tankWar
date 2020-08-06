package org.game.tankwar;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tools {
	public static Image getImage(String fileName) {
		return new ImageIcon("assets/images/" + fileName).getImage();
	}
}
