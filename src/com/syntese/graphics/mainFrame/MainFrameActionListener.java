/**
 * 
 */
package com.syntese.graphics.mainFrame;

/**
 * @author Cosmin
 *
 */
public interface MainFrameActionListener {
	/*
	 * The menu events
	 * */
	/*The file menu*/
	public void newMenuClick();
	public void openMenuClick();
	public void closeMenuClick();
	/**
	 * Name: SaveMenuClick
	 * Args: @param saveType - 0 : save as
	 * 						 - 1 : save all
	 * 						 - >1 : save 
	 * Return: void
	 * Desc: when one of the save menu buttons have been pressed
	 * 		
	 */
	public void saveMenuClick(int saveType);
	
	/*The help menu*/
	public void aboutMenuClick();
	public void languageMenuClick(String newLanguage);
	
	
	/*
	 * The Frame Events
	 * */
	public void frameClosing();
}
