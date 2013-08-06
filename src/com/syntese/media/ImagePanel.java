package com.syntese.media;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2799250794878834452L;
	private Image _theImage;
	private int _width;
	private int _height;
	
	public ImagePanel(URL image) throws IOException{
		_theImage = ImageIO.read(image);
		_width=_theImage.getWidth(null);
		_height=_theImage.getHeight(null);
		setPreferredSize(new Dimension(_width, _height));
		setSize(new Dimension(_width, _height));
		BufferedImage myPicture = ImageIO.read(image);
		JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
		add( picLabel );
	}

//	public void paintComponent(Graphics g)
//	{
//		super.paintComponent(g);
//		g.drawImage(_theImage, 0, 0, null);
//	}

}
