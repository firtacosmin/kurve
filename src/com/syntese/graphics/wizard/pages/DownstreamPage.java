package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.syntese.language.LanguageFactory;
import com.syntese.media.ImagePanel;

public class DownstreamPage extends JPanel{

	/****************
	 * PROPERTIES
	 * *************/
	/*types of cams*/
	public static final int ROLLER_LEVEL = 1;
	public static final int ROLLER_CAMS = 2;
	
	/*texts*/
	private static final String PANEL_TITLE_NAME = "Wizard_Downstream_Title";
	private static final String LEVEL_CAT1_NAME = "Wizard_Downstream_Level_Cat1";
	private static final String LEVEL_CAT2_NAME = "Wizard_Downstream_Level_Cat2";
	private static final String LEVEL_CAT3_NAME = "Wizard_Downstream_Level_Cat3";
	private static final String LEVEL_CAT4_NAME = "Wizard_Downstream_Level_Cat4";

	private static final String CAMS_CAT1_NAME = "Wizard_Downstream_Cams_Level_Cat1";
	private static final String CAMS_CAT2_NAME = "Wizard_Downstream_Cams_Level_Cat2";
	private static final String CAMS_CAT3_NAME = "Wizard_Downstream_Cams_Level_Cat3";
	
	
	/*UI Components*/
	private Checkbox _camCat1Chk;
	private Checkbox _camCat2Chk;
	private Checkbox _camCat3Chk;

	private Checkbox _levelCat1Chk;
	private Checkbox _levelCat2Chk;
	private Checkbox _levelCat3Chk;
	private Checkbox _levelCat4Chk;
	private CheckboxGroup _chkGroup;
	
	private ImagePanel _CatImg;
//	private ImagePanel _levelCat2Img;
//	private ImagePanel _levelCat3Img;
//	private ImagePanel _levelCat4Img;

//	private ImagePanel _camCatImg;
//	private ImagePanel _camCat2Img;
//	private ImagePanel _camCat3Img;
		
	/*selected cam type*/
	private int _selectedCamType;
	
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	
	
	/**
	 * Name: DownstreamPage
	 * Args: 
	 */
	public DownstreamPage(int selCamType) {
		super();

		_selectedCamType = selCamType;
		
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
	 * Desc: CREATING AND INITIALIZING THE COMPONENTS AND THE PANEL
	 */
	private void createUIComponents() {
		_chkGroup = new CheckboxGroup();
		
		/*creating the ckeckboxes*/
		
		_levelCat1Chk = new Checkbox();
		_levelCat1Chk.setCheckboxGroup(_chkGroup);
		_levelCat1Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVEL_CAT1_NAME));

		_levelCat2Chk = new Checkbox();
		_levelCat2Chk.setCheckboxGroup(_chkGroup);
		_levelCat2Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVEL_CAT2_NAME));

		_levelCat3Chk = new Checkbox();
		_levelCat3Chk.setCheckboxGroup(_chkGroup);
		_levelCat3Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVEL_CAT3_NAME));

		_levelCat4Chk = new Checkbox();
		_levelCat4Chk.setCheckboxGroup(_chkGroup);
		_levelCat4Chk.setLabel(LanguageFactory.getInstance().getExpresion(LEVEL_CAT4_NAME));
		
		_camCat1Chk = new Checkbox();
		_camCat1Chk.setCheckboxGroup(_chkGroup);
		_camCat1Chk.setLabel(LanguageFactory.getInstance().getExpresion(CAMS_CAT1_NAME));

		_camCat2Chk = new Checkbox();
		_camCat2Chk.setCheckboxGroup(_chkGroup);
		_camCat2Chk.setLabel(LanguageFactory.getInstance().getExpresion(CAMS_CAT2_NAME));

		_camCat3Chk = new Checkbox();
		_camCat3Chk.setCheckboxGroup(_chkGroup);
		_camCat3Chk.setLabel(LanguageFactory.getInstance().getExpresion(CAMS_CAT3_NAME));		

		/*creating the images*/
		URL imgUrl = null;
		try {
			if ( _selectedCamType == ROLLER_CAMS ){
				imgUrl = new URL("file:Media\\LevelUpstreamImg.JPG");
			}else if( _selectedCamType == ROLLER_LEVEL ){
				imgUrl = new URL("file:Media\\LevelUpstreamImg.JPG");
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
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				  LanguageFactory.getInstance().getExpresion(PANEL_TITLE_NAME)));
		setLayout(new BorderLayout());
		
		if ( _selectedCamType == ROLLER_CAMS ){
			addCamsUI();
		}else if( _selectedCamType == ROLLER_LEVEL ){
			addLevelUI();
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
	private void addLevelUI() {
		JPanel p = new JPanel();
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
		p.add(_camCat1Chk);
		p.add(_camCat2Chk);
		p.add(_camCat3Chk);
		add(p, BorderLayout.NORTH);
	}
}
