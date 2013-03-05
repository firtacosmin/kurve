/**
 * 
 */
package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.syntese.language.LanguageFactory;
import com.syntese.media.ImagePanel;


/**
 * @author Cosmin
 * The page of the wizard where the cam type is selected
 */
public class CamTypePage extends WizardPage {
	
	/****************
	 * PROPERTIES
	 * *************/
	
	public static final int ROLLER_LEVER = 1;
	public static final int ROLLER_SLIDE = 2;
	
	/*Texts for GUI*/
	private static final String PANEL_TITLE_NAME = "Wizard_CamType_title";
	private static final String NAME_TEXTFIELD_LABLE_TEXT_NAME = "Wizard_CamType_name";
	private static final String ROLLER_LEVER_CATEGORY_NAME = "Wizard_CamType_rollerLever";
	private static final String ROLLER_CAMS_CATEGORY_NAME = "Wizard_CamType_rollerCams";
	/*Texts for ERROR*/
	private static final String ERROR_MSG_START = "Wizard_error_start";
	private static final String ERROR_MSG_TITLE = "Wizard_CamType_error_noTitle";
	private static final String ERROR_MSG_TYPE_SELECTION = "Wizard_CamType_error_noCamTypeSelected";
	private static final String ERROR_DLG_TITLE = "Error_dialog_title";
	
	
	/*UI Components*/
	private CheckboxGroup _buttonGroup;
	private Checkbox _levelRadio;
	private Checkbox _camsRadio;
	private TextField _nameTextField;
	

	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	
	/**
	 * Name: CamTypePage
	 * Args: 
	 */
	public CamTypePage(){
		super();
		
		createUIComponents();
		addUIComponents();
		
	}
	
	public int getSelectedType(){
		return _camsRadio.getState()?ROLLER_SLIDE:ROLLER_LEVER;
	}
	
	public String getImputName(){
		return _nameTextField.getText();
	}

	@Override
	public boolean areFieldsValid() {
		Boolean returnValue = true;
		String errorMsg = LanguageFactory.getInstance().getExpresion(ERROR_MSG_START);
		
		/*Check the title field*/
		if ( _nameTextField.getText().isEmpty() || 
			 _nameTextField.getText().length() < 3 )
		{
			errorMsg += "\n- " + LanguageFactory.getInstance().getExpresion(ERROR_MSG_TITLE);
			returnValue = false;
		}
		
		/*check if any radio is selected*/
		if ( !_camsRadio.getState() && !_levelRadio.getState() )
		{
			errorMsg += "\n- " + LanguageFactory.getInstance().getExpresion(ERROR_MSG_TYPE_SELECTION);
			returnValue = false;
		}
		
		/*if error then display the msg*/
		if ( !returnValue )
		{
			JOptionPane.showMessageDialog(this, errorMsg, LanguageFactory.getInstance().getExpresion(ERROR_DLG_TITLE), JOptionPane.ERROR_MESSAGE);
		}
		
		return returnValue;
	}


	@Override
	public HashMap<String, String> getProperties() {
		return null;
	}
	
	
	
	

	/*
	 * PRIVATE
	 * */
	
	

	/**
	 * Name: createUIComponents
	 * Args: 
	 * Return: void
	 * Desc: CREATES THE UI COMPONENTS
	 */
	private void createUIComponents() {
		
		
		_buttonGroup = new CheckboxGroup();
		
		_camsRadio = new Checkbox();
		_camsRadio.setCheckboxGroup(_buttonGroup);
		_camsRadio.setLabel(LanguageFactory.getInstance().getExpresion(ROLLER_CAMS_CATEGORY_NAME));
		
		_levelRadio = new Checkbox();
		_levelRadio.setCheckboxGroup(_buttonGroup);
		_levelRadio.setLabel(LanguageFactory.getInstance().getExpresion(ROLLER_LEVER_CATEGORY_NAME));
		
		_nameTextField = new TextField();
		
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				  LanguageFactory.getInstance().getExpresion(PANEL_TITLE_NAME)));
		setLayout(new BorderLayout());
	}
	
	/**
	 * Name: addUIComponents
	 * Args: 
	 * Return: void
	 * Desc: places the ui components into the layout of the frame
	 */
	private void addUIComponents() {
		Box northernBox = Box.createHorizontalBox();
		northernBox.add(new Label(LanguageFactory.getInstance().getExpresion(NAME_TEXTFIELD_LABLE_TEXT_NAME)));
		northernBox.add(_nameTextField);
		

		
		
		Box radioBtnBox = Box.createHorizontalBox();
		radioBtnBox.add(_levelRadio);
		radioBtnBox.add(Box.createHorizontalGlue());
		radioBtnBox.add(_camsRadio);
		
		JPanel movieBox = new JPanel();
		try {
			GridLayout l = new GridLayout(1,2);
			l.setVgap(10);
			l.setHgap(20);
			movieBox.setLayout(l);
			ImagePanel img1 = new ImagePanel(new URL("file:Media\\111.bmp"));
			movieBox.add(img1);
			ImagePanel img2 = new ImagePanel(new URL("file:Media\\211.bmp"));	
			movieBox.add(img2);
			
			int width = img1.getWidth() + 40 + img2.getWidth(); 
			int height = (img1.getHeight() > img2.getHeight() ? img1.getHeight():img2.getHeight()) + 100 ;
			setSize(width , height );
			setPreferredSize(getSize());
			setMinimumSize(getSize());
			setMaximumSize(getSize());
			
			
			img1.setBackground(new Color(109,109,109));
			img2.setBackground(new Color(109,109,109));
			
		} catch (IOException e) {
			// TODO ERROR
			e.printStackTrace();
		}
		movieBox.setBackground(new Color(109, 109, 109));
		
		
		Box centralBox = Box.createVerticalBox();
		centralBox.add(radioBtnBox);
		centralBox.add(Box.createHorizontalStrut(20));
		centralBox.add(movieBox);
		
		add(northernBox, BorderLayout.NORTH);
		add(centralBox, BorderLayout.CENTER);
	}


}
