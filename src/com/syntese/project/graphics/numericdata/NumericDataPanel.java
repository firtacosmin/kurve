package com.syntese.project.graphics.numericdata;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.syntese.graphics.GBC;
import com.syntese.language.LanguageFactory;

/**
 * @author cfirta
 * @description panel with the imputed parameters and the 
 * 				preliminary calculations that are displayed on the main frame
 */
public class NumericDataPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Texts*/
	private static final String TEXT_CHK_INPUT_PARAMS = "InputParams_chk_inputParams";
	private static final String TEXT_CHK_OUTER_PROFILE = "InputParams_chk_outerProfile";
	private static final String TEXT_CHK_INNER_PROFILE = "InputParams_chk_innerProfile";
	private static final String TEXT_CHK_CENTER_AGAINST_CAM = "InputParams_chk_centerAgainstCam";
	private static final String TEXT_CHK_PROFILE_AGAINST_CAM = "InputParams_chk_profileAgainstCam";
	private static final String TEXT_CHK_CENTER_OF_FLOOD = "InputParams_chk_centerOfFlood";
	private static final String TEXT_CHK_PROFILE_OF_FLOOD = "InputParams_chk_profileOfFlood";
	private static final String TEXT_BTN_CLEAR = "InputParams_btn_clear";
	private static final String TEXT_VALUES_PREFIX = "InputParams_disp_";
	
	
	/*UI COMPONENTS*/
	private JTextArea _outputArea;
	private JCheckBox _chk_inputParams;
	private JCheckBox _chk_middlePoint;
	private JCheckBox _chk_outerProfile;
	private JCheckBox _chk_InnerProfile;
	private JCheckBox _chk_centerAgainstCam;
	private JCheckBox _chk_profileAgainstCam;
	private JCheckBox _chk_centerOfFlood;
	private JCheckBox _chk_profileOfFlood;
	private JButton _btn_clear;
	
	private HashMap<String,Float> _geomParams;
	
	/*
	 * PUBLIC
	 * */
	
	public NumericDataPanel()
	{
		super();
		_geomParams = new HashMap<String, Float>();
		
		InitComponents();
		AddComponents();
	}
	
	public void setGeomParams(HashMap<String, Float> params)
	{
		_geomParams = params;
	}

	/*
	 * PRIVATE
	 * */

	/**
	 * Name: InitComponents
	 * Args: 
	 * Return: void
	 * Desc: Initializing the UI components
	 */
	private void InitComponents() {
		_outputArea = new JTextArea();
		
		/**/
		_chk_inputParams = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_INPUT_PARAMS));
		_outputArea.setEditable(false);
		_chk_inputParams.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateOutPutData();
			}
		});
		_chk_middlePoint = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_INPUT_PARAMS));
		_chk_middlePoint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		_chk_outerProfile = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_OUTER_PROFILE));
		_chk_outerProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		_chk_InnerProfile = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_INNER_PROFILE));
		_chk_InnerProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		_chk_centerAgainstCam = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_CENTER_AGAINST_CAM));
		_chk_centerAgainstCam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		_chk_profileAgainstCam = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_PROFILE_AGAINST_CAM));
		_chk_profileAgainstCam.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		_chk_centerOfFlood = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_CENTER_OF_FLOOD));
		_chk_centerOfFlood.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		_chk_profileOfFlood = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_CHK_PROFILE_OF_FLOOD));
		_chk_profileOfFlood.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		_btn_clear = new JButton(LanguageFactory.getInstance().getExpresion(TEXT_BTN_CLEAR));
		_btn_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearOutput();
			}
		});
	}
	
	/**
	 * Name: AddComponents
	 * Args: 
	 * Return: void
	 * Desc: Adding the ui components to the panel
	 */
	private void AddComponents() {
		setLayout(new GridBagLayout());
		
		/*add the output textarea*/
		JScrollPane scrollPane = new JScrollPane(_outputArea);
		
		/*panel for the checkboxes*/
		JPanel chkPan = new JPanel();
		chkPan.setLayout(new GridBagLayout());
		chkPan.add(_chk_inputParams, new GBC(0,0).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_chk_middlePoint, new GBC(0,1).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_chk_outerProfile, new GBC(0,2).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_chk_InnerProfile, new GBC(0,3).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_chk_centerAgainstCam, new GBC(0,4).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_chk_profileAgainstCam, new GBC(0,5).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_chk_centerOfFlood, new GBC(0,6).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_chk_profileOfFlood, new GBC(0,7).setIpad(0, 10).setAnchor(GBC.WEST));
		chkPan.add(_btn_clear, new GBC(0,8).setIpad(0, 20).setAnchor(GBC.SOUTH));
		chkPan.setBackground(new Color(109,109,109));
		
		/*add UI to the main panel*/
		add(scrollPane, new GBC(0, 0).setFill(GBC.BOTH).setWeight(200, 100));
		add(chkPan, new GBC(1,0).setFill(GBC.BOTH).setWeight(50, 100));
	}
	
	/**
	 * Name: updateOutPutData
	 * Args: 
	 * Return: void
	 * Desc: updates the text area with the data from the checked fields
	 */
	private void updateOutPutData()
	{ 
		_outputArea.setText("");
		if ( _chk_inputParams.isSelected() ){
			_outputArea.append(LanguageFactory.getInstance().getExpresion(TEXT_CHK_INPUT_PARAMS)+"\n");
			for ( Entry<String, Float> entry : _geomParams.entrySet() ){
				   String key = entry.getKey().toLowerCase();
				   Float value = entry.getValue();
				   /*get text*/
				   String text = LanguageFactory.getInstance().getExpresion(TEXT_VALUES_PREFIX+key);
				   _outputArea.append(text+value.toString()+"\n");
			}
		}
	}
	
	private void clearOutput()
	{
		_chk_inputParams.setSelected(false);
		_outputArea.setText("");
	}

}

