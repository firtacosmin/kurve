/**
 * 
 */
package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

import com.syntese.language.LanguageFactory;
import com.syntese.media.MediaPanel;


/**
 * @author Cosmin
 * The page of the wizard where the cam type is selected
 */
public class CamTypePage extends JPanel {
	
	/****************
	 * PROPERTIES
	 * *************/
	
	/*Texts*/
	private static final String PANEL_TITLE_NAME = "Wizard_CamType_title";
	private static final String NAME_TEXTFIELD_LABLE_TEXT_NAME = "Wizard_CamType_name";
	private static final String ROLLER_LEVEL_CATEGORY_NAME = "Wizard_CamType_rollerLevel";
	private static final String ROLLER_CAMS_CATEGORY_NAME = "Wizard_CamType_rollerCams";
	
	
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
		_levelRadio.setLabel(LanguageFactory.getInstance().getExpresion(ROLLER_LEVEL_CATEGORY_NAME));
		
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
		
//		Box centralLeftBox = Box.createVerticalBox();
//		centralLeftBox.add(_levelRadio);
//		centralLeftBox.add(Box.createVerticalStrut(15));
//		centralLeftBox.setBorder(BorderFactory.createLineBorder(Color.black));
//		try {
//			MediaPanel p = new MediaPanel(new URL("file:Media\\oscil. pt. program1.avi"));
//			p.setBackground(Color.blue);
//			centralLeftBox.add(p);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		centralLeftBox.add(Box.createVerticalGlue());
//		
//		Box centralRightBox = Box.createVerticalBox();
//		centralRightBox.add(_camsRadio);
//		centralRightBox.add(Box.createVerticalStrut(15));
//		centralRightBox.setBorder(BorderFactory.createLineBorder(Color.black));
//		try {
//			MediaPanel p = new MediaPanel(new URL("file:Media\\tran pt prog bun 2.avi"));
//			p.setBackground(Color.blue);
//			centralRightBox.add(p);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		centralRightBox.add(Box.createVerticalGlue());
//		
		
		
//		
//		Box centralBox = Box.createHorizontalBox();
//		centralBox.setBorder(BorderFactory.createLineBorder(Color.black));
//		centralBox.add(centralLeftBox);
//		//centralBox.add(Box.createHorizontalGlue());
//		centralBox.add(centralRightBox);
		
		
		
		Box radioBtnBox = Box.createHorizontalBox();
		radioBtnBox.add(_levelRadio);
		radioBtnBox.add(Box.createHorizontalGlue());
		radioBtnBox.add(_camsRadio);
		
		Box movieBox = Box.createHorizontalBox();
		movieBox.setBorder(BorderFactory.createLineBorder(Color.black));
		try {
			MediaPanel p = new MediaPanel(new URL("file:Media\\oscil. pt. program1.avi"));
			p.stop();
			movieBox.add(p);
//			movieBox.add(Box.createHorizontalGlue());
			MediaPanel p2 = new MediaPanel(new URL("file:Media\\tran pt prog bun 2.avi"));
			p2.stop();
			movieBox.add(p2);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
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
