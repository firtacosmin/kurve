package com.syntese.graphics.wizard.pages;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.syntese.settings.SettingsFactory;

public class TrajectoriesPage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8462036502855241681L;

	/****************
	 * PROPERTIES
	 * *************/
	/*settings name*/
	private static final String MAX_MOTION_SEGMENTS_SETTING_NAME = "MaxMotionSegmets";
	
	/*TEXTS*/
	private static final String SECTIONS_TAG_NAME = "";
	private static final String PHI_TAG_NAME = "";
	private static final String MM_PSI_TAG_NAME = "";
	private static final String NO_MOTION_SEG_TAG_NAME = "";
	private static final String SUM_PHI_TAG_NAME = "";
	private static final String SUM_MM_PSI_TAG_NAME = "";
	
	
	/*GUI components*/
	private JComboBox[] _setions;
	private JTextField[] _phi;
	private JTextField[] _mmPsi;
	private JComboBox _noMotionSegments;
	private JTextField _sumPhi;
	private JTextField _sumMmPsi;
	
	private int _maxMotionSegments; 
	
	
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	public TrajectoriesPage(){
		super();
		
		try{
			/*first try to get the current setting*/
			_maxMotionSegments = Integer.parseInt(SettingsFactory.getInstance().getCurrentSetting(MAX_MOTION_SEGMENTS_SETTING_NAME));
		}catch(NumberFormatException ex){
			try {
				/*if current setting is not correct then get the default setting*/
				_maxMotionSegments = Integer.parseInt(SettingsFactory.getInstance().getDefaultSetting(MAX_MOTION_SEGMENTS_SETTING_NAME));
			}catch(NumberFormatException ex2){
				/*if the default setting is also incorrect then set the maximum number of segments to 12*/
				_maxMotionSegments = 12;
			}
		}
		
		inializeGuiComponents();
		addGuiComponents();
	}


	
	/*
	 * PRIVATE
	 * */
	private void inializeGuiComponents() {
		
		/*initializing the arrays*/
		_setions = new JComboBox[_maxMotionSegments];
		_mmPsi = new JTextField[_maxMotionSegments];
		_phi = new JTextField[_maxMotionSegments];
		
		_noMotionSegments = new JComboBox();
		
		_sumPhi = new JTextField();
		
		_sumMmPsi = new JTextField();
	}

	private void addGuiComponents() {
		
	}
}
