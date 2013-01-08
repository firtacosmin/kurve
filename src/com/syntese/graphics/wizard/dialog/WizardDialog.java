package com.syntese.graphics.wizard.dialog;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.syntese.language.LanguageFactory;

public class WizardDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***********
	 * PROPERTIES
	 * **********/
	/*Texts*/
	private static final String WIZARD_DLG_TITLE_NAME = "WizardDlgTitle";
	private static final String NEXT_BTN_TEXT_NAME = "WizardDlgNextBtnText";
	private static final String PREVIOUS_BTN_TEXT_NAME = "WizardDlgPreviousBtnText";
	private static final String CANCEL_BTN_TEXT_NAME = "WizardDlgCancelBtnText";
	/*dialog properties*/
	private static final int DLG_WIDTH = 800;
	private static final int DLG_HEIGHT = 600;
	private static final int SOUTHERNBOX_WIDTH = DLG_WIDTH;
	private static final int SOUTHERNBOX_HEIGHT = 20;
	private static final int BTN_WIDTH = 70;
	private static final int BTN_HEIGHT = 30;
	
	
	/*UI*/
	private Button _nextBtn;
	private Button _previousBtn;
	private Button _cancelBtn;
	private Panel _centerPanel;
	private Panel _southernPanel;
	private Box _southernBox;
	
	/*action listener*/
	private WizardDialogActionListener _theActionListener = null;
	
	
	
	/*********
	 * METHODS
	 * ********/
	
	
	/*
	 * PUBLIC
	 * */
	/**
	 * Name: WizardDialog
	 * Args: the constructor of the dialog. It create the UI components and 
	 * 		 adds them to the layout of the dialog 
	 */
	public WizardDialog(JFrame owner){
		super(owner);
		
		createUIComponents();
		addUiComponents();
		addActionListeners();
	}


	/**
	 * Name: addMainPanel
	 * Args: @param p
	 * Return: void
	 * Desc: Adds the passed panel to the panel in the center
	 */
	public void addMainPanel(Panel p){
		setSize(new Dimension(p.getWidth() + 60, p.getHeight() + SOUTHERNBOX_HEIGHT));
		_centerPanel.add(p);
	}
	
	/**
	 * Name: addMainPanel
	 * Args: @param p
	 * Return: void
	 * Desc: Adds the passed panel to the panel in the center
	 */
	public void addMainPanel(JPanel p){
		setSize(new Dimension(p.getWidth() + 60, p.getHeight() + SOUTHERNBOX_HEIGHT));
		_centerPanel.add(p);
	}
	
	/**
	 * Name: addActionLisner
	 * Args: @param listner
	 * Return: void
	 * Desc: saves the passed action listener
	 */
	public void addActionLisner(WizardDialogActionListener listner){
		_theActionListener = listner;
	}
	
	
	/**
	 * Name: displayNextButton
	 * Args: @param display
	 * Return: void
	 * Desc: modifies the visibility of the next button 
	 */
	public void displayNextButton(Boolean display){
		_nextBtn.setVisible(display);
	}
	/**
	 * Name: displayPreviousButton
	 * Args: @param display
	 * Return: void
	 * Desc: modifies the visibility of the previous button 
	 */
	public void displayPreviousButton(Boolean display){
		_previousBtn.setVisible(display);
	}
	
	
	/*
	 * PRIVATE
	 * */
	

	/**
	 * Name: createUIComponents
	 * Args: 
	 * Return: void
	 * Desc: initializes the ui components
	 */
	private void createUIComponents() {
		
		/*Dialog properties*/
		setSize(DLG_WIDTH, DLG_HEIGHT);
		setTitle(LanguageFactory.getInstance().getExpresion(WIZARD_DLG_TITLE_NAME));
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setLocation((screenSize.width - DLG_WIDTH)/2, (screenSize.height - DLG_HEIGHT)/2);
		
		/*dialog components*/
		_nextBtn = new Button(LanguageFactory.getInstance().getExpresion(NEXT_BTN_TEXT_NAME));
		_previousBtn = new Button(LanguageFactory.getInstance().getExpresion(PREVIOUS_BTN_TEXT_NAME));
		_cancelBtn = new Button(LanguageFactory.getInstance().getExpresion(CANCEL_BTN_TEXT_NAME));
		
		_nextBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		_nextBtn.setMaximumSize(_nextBtn.getPreferredSize());
		_previousBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		_previousBtn.setMaximumSize(_previousBtn.getPreferredSize());
		_cancelBtn.setPreferredSize(new Dimension(BTN_WIDTH, BTN_HEIGHT));
		_cancelBtn.setMaximumSize(_cancelBtn.getPreferredSize());
		
		/*central pannel*/
		_centerPanel = new Panel();
		
		/*southern panel*/
		_southernPanel = new Panel();
		
		/*southern box*/
		_southernBox = Box.createHorizontalBox();
		_southernBox.setPreferredSize(new Dimension(SOUTHERNBOX_WIDTH, SOUTHERNBOX_HEIGHT));
		_southernBox.setMaximumSize(_southernBox.getPreferredSize());
	}

	/**
	 * Name: addUiComponents
	 * Args: 
	 * Return: void
	 * Desc: Adds the ui components and to the layout of the dialog
	 */
	private void addUiComponents() {
		/*setting the layout*/
		setLayout(new BorderLayout());
		
		_southernBox.add(Box.createHorizontalGlue());
		_southernBox.add(_previousBtn);
		_southernBox.add(Box.createHorizontalStrut(5));
		_southernBox.add(_nextBtn);
		_southernBox.add(Box.createHorizontalStrut(5));
		_southernBox.add(_cancelBtn);
		_southernBox.add(Box.createHorizontalStrut(5));
		
		_southernPanel.add(_southernBox);
		
		add(_centerPanel,BorderLayout.CENTER);
		add(_southernPanel, BorderLayout.SOUTH);
		
	}
	


	/**
	 * Name: addActionListeners
	 * Args: 
	 * Return: void
	 * Desc: binds action listeners to the actions of the UI components
	 */
	private void addActionListeners() {
		_nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ( _theActionListener != null )
					_theActionListener.next();
			}
		});
		_previousBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( _theActionListener != null )
					_theActionListener.previous();
			}
		});
		_cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( _theActionListener != null )
					_theActionListener.cancel();
			}
		});
	}



	
}
