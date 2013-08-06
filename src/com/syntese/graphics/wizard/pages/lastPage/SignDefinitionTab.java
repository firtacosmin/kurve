package com.syntese.graphics.wizard.pages.lastPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.syntese.graphics.GBC;
import com.syntese.language.LanguageFactory;

public class SignDefinitionTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************
	 * PROPERTIES
	 * *************/
	
	/*Titles for the texts that will be displayed*/
	private static final String ROTATION_CAM_SIGN_TEXT = "Wizard_SignDef_rotationCamSign";
	private static final String ROCKING_LEVER_SIGN_TEXT = "Wizard_SignDef_rockingLeverSign";
	private static final String POSITIV_TEXT = "Wizard_SignDef_PositivOption";
	private static final String NEGATIV_TEXT = "Wizard_SignDef_NegativOption";
	
	/*GUI components*/
	private JRadioButton _camPositiv_rd;
	private JRadioButton _camNegativ_rd;
	private ButtonGroup _camRdGroup;
	private JRadioButton _leverPositiv_rd;
	private JRadioButton _leverNegativ_rd;
	private ButtonGroup _leverRbGroup;
	
	
	
	
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	
	public SignDefinitionTab()
	{
		initializeGUIComponents();
		addGUIComponents();
	}

	/**
	 * Name: areFieldsValid
	 * Args: @return
	 * Return: Boolean
	 * Desc: Checks if the fields are validly completed.
	 */
	public Boolean areFieldsValid()
	{
		return true;
	}
	
	public Boolean getCamSign()
	{
		return _camPositiv_rd.isEnabled()?true:false;
	}
	public Boolean getLeverSign()
	{
		return _leverPositiv_rd.isEnabled()?true:false;
	}


	/*
	 * PRIVATE
	 * */
	
	
	/**
	 * Name: addGUIComponents
	 * Args: 
	 * Return: void
	 * Desc: Method used to add the GUI components to the panel
	 */
	private void addGUIComponents() {
		/*
		 * panel to hold the radio for selecting 
		 * the direction of the rotation of the cam
		 * */
		JPanel camSignPan = new JPanel();
		camSignPan.setBorder(BorderFactory.createTitledBorder(LanguageFactory.getInstance().getExpresion(ROTATION_CAM_SIGN_TEXT)));
		camSignPan.setLayout(new BorderLayout());
		camSignPan.setBackground(new Color(109,109,109));
		camSignPan.add(_camPositiv_rd, BorderLayout.NORTH);
		camSignPan.add(_camNegativ_rd, BorderLayout.SOUTH);
		
		
		/*
		 * panel to hold the radio for selecting the movement of the 
		 * rocking lever at the beginning of the movement
		 * */
		JPanel leverSignPan = new JPanel();
		leverSignPan.setBorder(BorderFactory.createTitledBorder(LanguageFactory.getInstance().getExpresion(ROCKING_LEVER_SIGN_TEXT)));
		leverSignPan.setLayout(new BorderLayout());
		leverSignPan.setBackground(new Color(109,109,109));
		leverSignPan.add(_leverPositiv_rd, BorderLayout.NORTH);
		leverSignPan.add(_leverNegativ_rd, BorderLayout.SOUTH);
		
		
		/*add the panels with the radio to the graphics*/
		setLayout(new GridBagLayout());
		setBackground(new Color(109,109,109));
		add(camSignPan, new GBC(0,0).setAnchor(GBC.CENTER).setInsets(20, 0, 20, 0).setFill(GBC.HORIZONTAL).setWeight(100, 100));
		add(leverSignPan, new GBC(0,1).setAnchor(GBC.CENTER).setInsets(20, 0, 20, 0).setFill(GBC.HORIZONTAL).setWeight(100, 100));
	}


	/**
	 * Name: initializeGUIComponents
	 * Args: 
	 * Return: void
	 * Desc: method used to initialize all the GUI components that will be displayed 
	 */
	private void initializeGUIComponents() {
		_camNegativ_rd = new JRadioButton(LanguageFactory.getInstance().getExpresion(NEGATIV_TEXT));
		_camNegativ_rd.setSelected(false);
		_camPositiv_rd = new JRadioButton(LanguageFactory.getInstance().getExpresion(POSITIV_TEXT));
		_camPositiv_rd.setSelected(true);
		
		_camRdGroup = new ButtonGroup();
		_camRdGroup.add(_camNegativ_rd);
		_camRdGroup.add(_camPositiv_rd);
		
		_leverNegativ_rd = new JRadioButton(LanguageFactory.getInstance().getExpresion(NEGATIV_TEXT));
		_leverNegativ_rd.setSelected(false);
		_leverPositiv_rd = new JRadioButton(LanguageFactory.getInstance().getExpresion(POSITIV_TEXT));
		_leverPositiv_rd.setSelected(true);
		
		_leverRbGroup = new ButtonGroup();
		_leverRbGroup.add(_leverNegativ_rd);
		_leverRbGroup.add(_leverPositiv_rd);
	}

}
