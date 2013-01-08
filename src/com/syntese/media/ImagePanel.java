package com.syntese.media;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	private Image _theImage;
	private int _width;
	private int _height;
	
	public ImagePanel(URL image) throws IOException{
		_theImage = ImageIO.read(image);
		_width=_theImage.getWidth(null);
		_height=_theImage.getHeight(null);
		setPreferredSize(new Dimension(_width, _height));
	}

	public void paintComponent(Graphics g)
	{
		g.drawImage(_theImage, 0, 0, null);
	}

}
