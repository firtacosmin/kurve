package com.syntese.graphics.wizard.pages.lastPage;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * @author Cosmin
 * @desc class made to display a dialog with an image on it.
 */
public class ImageDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image _imgToDisplay;
	
	public ImageDialog(String imgPath) throws IOException{
		_imgToDisplay = ImageIO.read(new File(imgPath));
		setSize(_imgToDisplay.getWidth(this) + 20, _imgToDisplay.getHeight(this) + 50);
		
		add(new ImagePanel());
	}
	
	/**
	 * @author Cosmin
	 * @desc inner class made to create a panel for an image
	 */
	private class ImagePanel extends JPanel
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			if ( _imgToDisplay == null ) return;
			g.drawImage(_imgToDisplay, 0, 0, null);
		}
	}
	
}
