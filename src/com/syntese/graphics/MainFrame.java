package com.syntese.graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

/*controls*/
private JMenu _fileMenu;
private JMenuItem _newMenuItem;
private JMenuItem _openMenuItem;
private JMenuItem _closeMenuItem;
private JMenuItem _saveMenuItem;
private JMenuItem _saveAsMenuItem;
private JMenuItem _saveAllMenuItem;

/*The Help Menu*/
private JMenu _helpMenu;
private JMenuItem _aboutMenuItem;
private JMenu _languageMenuItem;


private static final String HELP_MENU_EXPRESSION = "HelpMenu";
private static final String LANGUAGE_MENU_EXPRESSION = "LanguageMenu";
private static final String ABOUT_MENU_EXPRESSION = "AboutMenu";


private MainFrameActionListener _theEventHandler;
/*PUBLIC*/

public MainFrame(){
	super();
	
	setAtributes();
	addComponents();
	
}


/**
 * Name: addActionListener
 * Args: @param hdl
 * Return: void
 * Desc: adds the hdl that will listen too all the actions of the frame
 */
public void addActionListener(MainFrameActionListener hdl){
	_theEventHandler = hdl;
}

public void updateTexts(){
	/*The File menu*/
	 _fileMenu.setText(LanguageFactory.getInstance().getExpresion(FILE_MENU_EXPRESSION));
	 _newMenuItem.setText(LanguageFactory.getInstance().getExpresion(NEW_MENU_EXPRESSION));
	 _openMenuItem.setText(LanguageFactory.getInstance().getExpresion(OPEN_MENU_EXPRESSION));
	 _closeMenuItem.setText(LanguageFactory.getInstance().getExpresion(CLOSE_MENU_EXPRESSION));
	 _saveMenuItem.setText(LanguageFactory.getInstance().getExpresion(SAVE_MENU_EXPRESSION));
	 _saveAsMenuItem.setText(LanguageFactory.getInstance().getExpresion(SAVE_AS_MENU_EXPRESSION));
	 _saveAllMenuItem.setText(LanguageFactory.getInstance().getExpresion(SAVE_ALL_MENU_EXPRESSION));
	
	/*The Help Menu*/
	 _helpMenu.setText(LanguageFactory.getInstance().getExpresion(HELP_MENU_EXPRESSION));
	 _aboutMenuItem.setText(LanguageFactory.getInstance().getExpresion(ABOUT_MENU_EXPRESSION));
	 _languageMenuItem.setText(LanguageFactory.getInstance().getExpresion(LANGUAGE_MENU_EXPRESSION));
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
	 _fileMenu = new JMenu(LanguageFactory.getInstance().getExpresion(FILE_MENU_EXPRESSION));
	 _newMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(NEW_MENU_EXPRESSION));
	 _openMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(OPEN_MENU_EXPRESSION));
	 _closeMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(CLOSE_MENU_EXPRESSION));
	 _saveMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(SAVE_MENU_EXPRESSION));
	 _saveAsMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(SAVE_AS_MENU_EXPRESSION));
	 _saveAllMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(SAVE_ALL_MENU_EXPRESSION));
	
	/*The Help Menu*/
	 _helpMenu = new JMenu(LanguageFactory.getInstance().getExpresion(HELP_MENU_EXPRESSION));
	 _aboutMenuItem = new JMenuItem(LanguageFactory.getInstance().getExpresion(ABOUT_MENU_EXPRESSION));
	 _languageMenuItem = new JMenu(LanguageFactory.getInstance().getExpresion(LANGUAGE_MENU_EXPRESSION));
	addLanguagesToMenu();
	
	
	/*Adding menu item to menus*/
	/*File menu*/
	_fileMenu.add(_newMenuItem);
	_fileMenu.add(_openMenuItem);
	_fileMenu.add(_closeMenuItem);
	_fileMenu.addSeparator();
	_fileMenu.add(_saveMenuItem);
	_fileMenu.add(_saveAsMenuItem);
	_fileMenu.add(_saveAllMenuItem);
	
	_helpMenu.add(_aboutMenuItem);
	_helpMenu.add(_languageMenuItem);
	JMenuBar theMenuBar = new JMenuBar();
	theMenuBar.add(_fileMenu);
	theMenuBar.add(_helpMenu);
	setJMenuBar(theMenuBar);
	
	/*add click event handlers for the menus*/
	_newMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_theEventHandler.newMenuClick();
		}
	});
	_openMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_theEventHandler.openMenuClick();
		}
	});
	_closeMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_theEventHandler.closeMenuClick();
		}
	});
	_saveMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_theEventHandler.saveMenuClick(2);
		}
	});
	_saveAllMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_theEventHandler.saveMenuClick(1);
		}
	});
	_saveAsMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_theEventHandler.saveMenuClick(0);
		}
	});
	_aboutMenuItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			_theEventHandler.aboutMenuClick();
		}
	});
	
	
	
	/*frame events*/
	addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent arg0) {
			_theEventHandler.frameClosing();
		}
		
		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
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
	private void addLanguagesToMenu() {
		ArrayList<String> langs = LanguageFactory.getInstance().getAvailableLanguages();
		
		for ( int i=0; i<langs.size(); i++ ){
			JMenuItem lang = new JMenuItem(langs.get(i));
			_languageMenuItem.add(lang);
			lang.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					_theEventHandler.languageMenuClick(((JMenuItem)e.getSource()).getText());
				}
			});
		}
		
	}
}

