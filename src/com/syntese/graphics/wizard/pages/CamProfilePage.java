package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.syntese.language.LanguageFactory;
import com.syntese.media.ImagePanel;

public class CamProfilePage extends JPanel{

	/****************
	 * PROPERTIES
	 * *************/
	private static int TOTAL_INSTANTES = 0;
	
	/****************
	 * PROPERTIES
	 * *************/
	
	/*texts*/
	private static final String SLIDE_PANEL_TITLE_NAME = "Wizard_CamProfile_Slide_Title";
	private static final String LEVER_PANEL_TITLE_NAME = "Wizard_CamProfile_Lever_Title";
	private static final String LEVER_CAT1_NAME = "Wizard_CamProfile_Lever_Cat1";
	private static final String LEVER_CAT2_NAME = "Wizard_CamProfile_Lever_Cat2";
	private static final String LEVER_CAT3_NAME = "Wizard_CamProfile_Lever_Cat3";
	private static final String LEVER_CAT4_NAME = "Wizard_CamProfile_Lever_Cat4";
	
	private static final String SLIDE_CAT1_NAME = "Wizard_CamProfile_Slide_Cat1";
	private static final String SLIDE_CAT2_NAME = "Wizard_CamProfile_Slide_Cat2";
	private static final String SLIDE_CAT3_NAME = "Wizard_CamProfile_Slide_Cat3";
	private static final String SLIDE_CAT4_NAME = "Wizard_CamProfile_Slide_Cat4";
	/*UI Components*/
	private Checkbox _levelCat1Chk;
	private Checkbox _levelCat2Chk;
	private Checkbox _levelCat3Chk;
	private Checkbox _levelCat4Chk;
	
	private Checkbox _slideCat1Chk;
	private Checkbox _slideCat2Chk;
	private Checkbox _slideCat3Chk;
	private Checkbox _slideCat4Chk;
	private CheckboxGroup _chkGroup;
	
	private ImagePanel _CatImg;
	/*selected cam type*/
	private int _selectedCamType;
	
	
	private int _instanceNo;
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	public CamProfilePage(int selCamType){
		super();		
		TOTAL_INSTANTES++;
		_instanceNo = TOTAL_INSTANTES;
		_selectedCamType = selCamType;
		createUIComponents();
		addUIComponents();
		
	}
	
	public int getInstanceNo(){
		return _instanceNo;
	}
	
	/*
	 * PRIVATE
	 * */
	/**
	 * Name: createUIComponents
	 * Args: 
	 * Return: void
	 * Desc: CREATING AND INITIALIZING THE COMPONENTS AND THE PANEL
	 */
	private void createUIComponents() {
		_chkGroup = new CheckboxGroup();
		
		/*creating the ckeckboxes*/
		
		_levelCat1Chk = new Checkbox();
		_levelCat1Chk.setCheckboxGroup(_chkGroup);
		_levelCat1Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVER_CAT1_NAME));

		_levelCat2Chk = new Checkbox();
		_levelCat2Chk.setCheckboxGroup(_chkGroup);
		_levelCat2Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVER_CAT2_NAME));

		_levelCat3Chk = new Checkbox();
		_levelCat3Chk.setCheckboxGroup(_chkGroup);
		_levelCat3Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVER_CAT3_NAME));

		_levelCat4Chk = new Checkbox();
		_levelCat4Chk.setCheckboxGroup(_chkGroup);
		_levelCat4Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVER_CAT4_NAME));
		
		_slideCat1Chk = new Checkbox();
		_slideCat1Chk.setCheckboxGroup(_chkGroup);
		_slideCat1Chk.setLabel(LanguageFactory.getInstance().getExpresion(SLIDE_CAT1_NAME));

		_slideCat2Chk = new Checkbox();
		_slideCat2Chk.setCheckboxGroup(_chkGroup);
		_slideCat2Chk.setLabel(LanguageFactory.getInstance().getExpresion(SLIDE_CAT2_NAME));

		_slideCat3Chk = new Checkbox();
		_slideCat3Chk.setCheckboxGroup(_chkGroup);
		_slideCat3Chk.setLabel(LanguageFactory.getInstance().getExpresion(SLIDE_CAT3_NAME));
		
		_slideCat4Chk = new Checkbox();
		_slideCat4Chk.setCheckboxGroup(_chkGroup);
		_slideCat4Chk.setLabel(LanguageFactory.getInstance().getExpresion(SLIDE_CAT4_NAME));	

		/*creating the images*/
		URL imgUrl = null;
		try {
			if ( _selectedCamType == CamTypePage.ROLLER_SLIDE ){
				imgUrl = new URL("file:Media\\camProfile_rollerSlide.JPG");
			}else if( _selectedCamType == CamTypePage.ROLLER_LEVER ){
				imgUrl = new URL("file:Media\\camProfile_rollerLever.JPG");
			}
		} catch (MalformedURLException e) {
			// TODO ERROR
			e.printStackTrace();
		}
		try {
			_CatImg = new ImagePanel(imgUrl);
		} catch (IOException e) {
			// TODO ERROR
			e.printStackTrace();
		}
	}

	/**
	 * Name: addUIComponents
	 * Args: 
	 * Return: void
	 * Desc: ADDING THE COMPONENTS TO THE PANEL
	 */
	private void addUIComponents() {
		

		setLayout(new BorderLayout());
		
		if ( _selectedCamType == CamTypePage.ROLLER_SLIDE ){
			addCamsUI();		
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
					  LanguageFactory.getInstance().getExpresion(SLIDE_PANEL_TITLE_NAME)));
		}else if( _selectedCamType == CamTypePage.ROLLER_LEVER ){
			addLeverUI();		
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
					  LanguageFactory.getInstance().getExpresion(LEVER_PANEL_TITLE_NAME)));
		}
		
		add(_CatImg, BorderLayout.SOUTH);
		
		Dimension dim = new Dimension();
		dim.width = _CatImg.getSize().width + 50;
		dim.height = _CatImg.getSize().height + 30 + 40;
		setSize(dim);
		setPreferredSize(getSize());
		setMaximumSize(getSize());
		setMaximumSize(getSize());
	}

	/**
	 * Name: addRollerUI
	 * Args: 
	 * Return: void
	 * Desc: it prints the UI components for the ROLLER type selection
	 */
	private void addLeverUI() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,4));
		p.add(_levelCat1Chk);
		p.add(_levelCat2Chk);
		p.add(_levelCat3Chk);
		p.add(_levelCat4Chk);
		add(p, BorderLayout.NORTH);
	}

	/**
	 * Name: addCamsUI
	 * Args: 
	 * Return: void
	 * Desc: it prints the UI components for the CAMS type selection
	 */
	private void addCamsUI() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,4));
		p.add(_slideCat1Chk);
		p.add(_slideCat2Chk);
		p.add(_slideCat3Chk);
		p.add(_slideCat4Chk);
		add(p, BorderLayout.NORTH);
	}
}
