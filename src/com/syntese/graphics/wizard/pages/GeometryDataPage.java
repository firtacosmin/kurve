package com.syntese.graphics.wizard.pages;

import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeometryDataPage extends JPanel {

	/****************
	 * PROPERTIES
	 * *************/
	
	/*texts*/
	private static final String LENGTH_TITLE_NAME = "Wizard_GeomData_LengthTitle";
	private static final String ANGLE_TITLE_NAME = "Wizard_GeomData_AngleTitle";
	private static final String WAVE_RADIUS_LABEL_NAME = "Wizard_GeomData_WaveRad_Label";
	private static final String SEIBEN_RADIUS_LABEL_NAME = "Wizard_GeomData_SeibenRad_Label";
	private static final String DRAWING_BTN_NAME = "Wizard_GeomData_DrawingBtn";
	
	
	/*GUI Components*/
	private JTextField _X_A0;
	private JTextField _Y_A0;
	private JTextField _X_d;
	private JTextField _Y_d;
	private JTextField _X_s;
	private JTextField _ro_min;
	private JTextField _L3;
	private JTextField _L31;
	private JTextField _L3p;
	private JTextField _L4;
	private JTextField _L5;
	private JTextField _L1;
	private JTextField _rR;
	private JTextField _rG;
	private JTextField _ep;
	private JTextField _y;
	private JTextField _delta;
	private JTextField _gama;
	private JTextField _miu_an_min;
	private JTextField _miu_ab_min;
	private JTextField _n;
	private JTextField _siebenRad;
	private JTextField _waveRad;
	private Image _helpingImg;
	
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	
	public GeometryDataPage(){
		super();
		initializeGUIComponents();
		addGUIComponents();
	}

	/*
	 * PRIVATE
	 * */
	
	
	private void initializeGUIComponents() {
		
		_X_A0 = new JTextField("X_A0 (mm)");
		_Y_A0 = new JTextField("Y_A0 (mm)");
		_X_d = new JTextField("X_d (mm)");
		_Y_d = new JTextField("Y_d (mm)");
		_X_s = new JTextField("X_s (mm)");
		_ro_min = new JTextField("ro_min (mm)");
		_L3 = new JTextField("L3 (mm)");
		_L31 = new JTextField("L31 (mm)");
		_L3p = new JTextField("L3p (mm)");
		_L4 = new JTextField("L4 (mm)");
		_L5 = new JTextField("L5 (mm)");
		_L1 = new JTextField("L1 (mm)");
		_rR = new JTextField("rR (mm)");
		_rG = new JTextField("rG (mm)");
		_ep = new JTextField("ep (mm)");
		_y = new JTextField("y (mm)");
		_delta = new JTextField("delta (grad)");
		_gama = new JTextField("gama (grad)");
		_miu_an_min = new JTextField("miu_an_min (grad)");
		_miu_ab_min = new JTextField("miu_ab_min (grad)");
		_n = new JTextField("n (grad)");
		_siebenRad = new JTextField("siebenRad (mm)");
		_waveRad = new JTextField("waveRad (mm)");

		
	}
	

	private void addGUIComponents() {
		
	}
	
}
