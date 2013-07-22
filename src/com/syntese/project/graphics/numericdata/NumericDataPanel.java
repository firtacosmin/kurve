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
	private static final String TEXT_BTN_CLEAR = "InputParams_btn_clear";
	private static final String TEXT_VALUES_PREFIX = "InputParams_disp_";
	
	
	/*UI COMPONENTS*/
	private JTextArea _outputArea;
	private JCheckBox _chk_inputParams;
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
		_chk_inputParams.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
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
		chkPan.add(_chk_inputParams, new GBC(0,0).setAnchor(GBC.NORTH));
		chkPan.add(_btn_clear, new GBC(0,1).setAnchor(GBC.SOUTH));
		chkPan.setBackground(new Color(109,109,109));
		
		/*add UI to the main panel*/
		add(scrollPane, new GBC(0, 0).setFill(GBC.BOTH).setWeight(100, 100));
		add(chkPan, new GBC(1,0).setFill(GBC.BOTH).setWeight(100, 100));
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

