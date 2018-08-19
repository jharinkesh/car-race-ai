package com.demo.product;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class BgPanel extends JLabel {

	Image image;

	BgPanel(String fileName) {
		URL url = getClass().getResource(fileName);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		image = toolkit.getImage(url);
		setIcon(new ImageIcon(image));
	}

//	@Override
//	public void paintComponent(Graphics g) {
//		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//	}
}
