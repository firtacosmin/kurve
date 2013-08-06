package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.syntese.language.LanguageFactory;
import com.syntese.media.ImagePanel;
import com.syntese.project.data.ProjectData;

public class DownstreamPage extends WizardPage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6817402251516347159L;

	private static int TOTAL_INSTANTES = 0;
	
	/****************
	 * PROPERTIES
	 * *************/
	
	/*CONSTANT PROPARTIES VALUES*/

	
	/*texts GUI*/
	private static final String PANEL_TITLE_NAME = "Wizard_Downstream_Title";
	private static final String LEVER_CAT1_NAME = "Wizard_Downstream_Lever_Cat1";
	private static final String LEVER_CAT2_NAME = "Wizard_Downstream_Lever_Cat2";
	private static final String LEVER_CAT3_NAME = "Wizard_Downstream_Lever_Cat3";
	private static final String LEVER_CAT4_NAME = "Wizard_Downstream_Lever_Cat4";

	private static final String CAMS_CAT1_NAME = "Wizard_Downstream_Cams_Cat1";
	private static final String CAMS_CAT2_NAME = "Wizard_Downstream_Cams_Cat2";
	private static final String CAMS_CAT3_NAME = "Wizard_Downstream_Cams_Cat3";
	/*texts ERROR*/
	private static final String ERROR_MSG_START = "Wizard_error_start";
	private static final String ERROR_MSG_NO_SELECTION = "Wizard_Downstream_error_noSelection";
	private static final String ERROR_DLG_TITLE = "Error_dialog_title";
	
	
	
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
	
	
	private int _instanceNo;
	
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
		TOTAL_INSTANTES++;
		_instanceNo = TOTAL_INSTANTES;
		_selectedCamType = selCamType;
		createUIComponents();
		addUIComponents();
	}
	
	public int getInstanceNo(){
		return _instanceNo;
	}
	
	/**
	 * Name: getSelectedCategory
	 * Args: @return
	 * Return: int
	 * Desc: returns the selected category code
	 */
	public int getSelectedCategory(){
		if ( _camCat1Chk.getState() ){
			return ProjectData.CAMS_NO_DOWNSTREAM;
		}else if ( _camCat2Chk.getState() ){
			return ProjectData.CAMS_DOUBLE_SLIDE;
		}else if ( _camCat3Chk.getState() ){
			return ProjectData.CAMS_CRANK;
		}else if ( _levelCat1Chk.getState() ){
			return ProjectData.LEVER_NO_DOWNSTREAM;
		}else if ( _levelCat2Chk.getState() ){
			return ProjectData.LEVER_CRANK;
		}else if ( _levelCat3Chk.getState() ){
			return ProjectData.LEVER_FOUR_JOIN;
		}else if ( _levelCat4Chk.getState() ){
			return ProjectData.LEVER_PUSHER_TUGS;
		}else {
			return ProjectData.NO_SELECTIONS;
		}
	}
	

	@Override
	public boolean areFieldsValid() {
		Boolean returnValue = true;
		String errorMsg = LanguageFactory.getInstance().getExpresion(ERROR_MSG_START);
		
		if ( !_camCat1Chk.getState() &&
			 !_camCat2Chk.getState() &&
			 !_camCat3Chk.getState() &&
			 !_levelCat1Chk.getState() &&
			 !_levelCat2Chk.getState() &&
			 !_levelCat3Chk.getState() &&
			 !_levelCat4Chk.getState())
		{
			errorMsg += "\n- "+LanguageFactory.getInstance().getExpresion(ERROR_MSG_NO_SELECTION);
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
	 * Desc: CREATING AND INITIALIZING THE COMPONENTS AND THE PANEL
	 */
	private void createUIComponents() {
		_chkGroup = new CheckboxGroup();
		
		/*creating the ckeckboxes*/
		
		_levelCat1Chk = new Checkbox();
		_levelCat1Chk.setState(true);
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
		
		_camCat1Chk = new Checkbox();
		_camCat1Chk.setState(true);
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
			if ( _selectedCamType == ProjectData.ROLLER_SLIDE ){
				imgUrl = new URL("file:Media\\CamsUpstreamImg.JPG");
			}else if( _selectedCamType == ProjectData.ROLLER_LEVER ){
				imgUrl = new URL("file:Media\\LeverUpstreamImg.JPG");
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
		
		if ( _selectedCamType == ProjectData.ROLLER_SLIDE ){
			addCamsUI();
		}else if( _selectedCamType == ProjectData.ROLLER_LEVER ){
			addLeverUI();
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
		p.setLayout(new GridLayout(1,3));
		p.add(_camCat1Chk);
		p.add(_camCat2Chk);
		p.add(_camCat3Chk);
		add(p, BorderLayout.NORTH);
	}
}
