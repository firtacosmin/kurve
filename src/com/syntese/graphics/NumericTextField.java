package com.syntese.graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * @author Cosmin
 * Desc: class created to implement a text field that will only get numeric inputs
 *
 */
public class NumericTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String prevVal;
	
	public NumericTextField()
	{
		super();
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

//				if ((arg0.getKeyCode() < KeyEvent.VK_0 ||
//					arg0.getKeyCode() > KeyEvent.VK_9) &&
//					arg0.getKeyCode() != KeyEvent.VK_BACK_SPACE &&
//					arg0.getKeyCode() != KeyEvent.VK_TAB)
//				{
//					/*ignore key*/
//					setText(prevVal);
//				}
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				prevVal = getText();
			}
		});
	}
	
}
