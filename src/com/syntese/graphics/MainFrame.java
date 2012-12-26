package com.syntese.graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.syntese.language.LanguageFactory;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7500375602802826284L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	/*message expression names*/
	private static final String TITLE_EXPRESSION = "MainFremeTitle";
	private static final String FILE_MENU_EXPRESSION = "FileMenu";
	private static final String NEW_MENU_EXPRESSION = "NewMenu";
	private static final String OPEN_MENU_EXPRESSION = "OpenMenu";
	private static final String CLOSE_MENU_EXPRESSION = "CloseMenu";
	private static final String SAVE_MENU_EXPRESSION = "SaveMenu";
	private static final String SAVE_AS_MENU_EXPRESSION = "SaveAsMenu";
	private static final String SAVE_ALL_MENU_EXPRESSION = "SaveAllMenu";
	
	private static final String HELP_MENU_EXPRESSION = "HelpMenu";
	private static final String LANGUAGE_MENU_EXPRESSION = "LanguageMenu";
	private static final String ABOUT_MENU_EXPRESSION = "AboutMenu";
	
	/*PUBLIC*/
	
	public MainFrame(){
		super();
		
		setAtributes();
		addComponents();
		
	}
	
	
	/*PRIVATE*/

	/**
	 * Name: addComponents
	 * Args: 
	 * Return: void
	 * Desc: adds the components to the frame 
	 */
	private void addComponents() {
		
		/*Adding the menu*/
		
		/*The File menu*/
		JMenu fileMenu = new JMenu(LanguageFactory.getInstance().getExpresion(FILE_MENU_EXPRESSION));
		JMenuItem newMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(NEW_MENU_EXPRESSION));
		JMenuItem openMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(OPEN_MENU_EXPRESSION));
		JMenuItem closeMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(CLOSE_MENU_EXPRESSION));
		JMenuItem saveMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(SAVE_MENU_EXPRESSION));
		JMenuItem saveAsMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(SAVE_AS_MENU_EXPRESSION));
		JMenuItem saveAllMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(SAVE_ALL_MENU_EXPRESSION));
		
		/*The Help Menu*/
		JMenu helpMenu = new JMenu(LanguageFactory.getInstance().getExpresion(HELP_MENU_EXPRESSION));
		JMenuItem aboutMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(ABOUT_MENU_EXPRESSION));
		JMenu languageMenuItem = new JMenu(LanguageFactory.getInstance().getExpresion(LANGUAGE_MENU_EXPRESSION));
		addLanguagesToMenu(languageMenuItem);
		
		
		/*Adding menu item to menus*/
		/*File menu*/
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(closeMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(saveAllMenuItem);
		
		helpMenu.add(aboutMenuItem);
		helpMenu.add(languageMenuItem);
		JMenuBar theMenuBar = new JMenuBar();
		theMenuBar.add(fileMenu);
		theMenuBar.add(helpMenu);
		setJMenuBar(theMenuBar);
		
		
	}


	/**
	 * Name: setAtributes
	 * Args: 
	 * Return: void
	 * Desc: sets the attributes ( width, height, close operation ETC ) of the frame
	 */ 
	private void setAtributes() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(WIDTH, HEIGHT);
		setTitle(LanguageFactory.getInstance().getExpresion(TITLE_EXPRESSION));
		setLocation((screenSize.width - WIDTH)/2, (screenSize.height - HEIGHT)/2);
		Image img = kit.getImage("Images/copy_icon.gif");
		setIconImage(img);

		
	}

	/**
	 * Name: addLanguagesToMenu
	 * Args: @param languageMenuItem
	 * Return: void
	 * Desc: gets the available languages and adds them to the language menu item from the help menu
	 */
	private void addLanguagesToMenu(JMenu languageMenuItem) {
		ArrayList<String> langs = LanguageFactory.getInstance().getAvailableLanguages();
		
		for ( int i=0; i<langs.size(); i++ ){
			JMenuItem lang = new JMenuItem(langs.get(i));
			languageMenuItem.add(lang);
		}
		
	}
}

