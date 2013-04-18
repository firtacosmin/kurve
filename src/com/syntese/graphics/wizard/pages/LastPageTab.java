package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTabbedPane;

import com.syntese.graphics.wizard.pages.lastPage.GeometryDataTab;
import com.syntese.graphics.wizard.pages.lastPage.MotionTab;
import com.syntese.graphics.wizard.pages.lastPage.SignDefinitionTab;
import com.syntese.language.LanguageFactory;

public class LastPageTab extends WizardPage{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String GEOMETRY_TAB_TITLE = "Wizard_GeomData_TabTitle";
	private static final String SIGN_DEFINITION_TAB_TITLE = "Wizard_SignDef_tabTitle";
	private static final String MOTION_TAB_TITLE = "Wizard_Motion_TabTitle";
	
	private GeometryDataTab _geomTab; 
	private SignDefinitionTab _signTab;
	private JTabbedPane _theTabbedPane;
	private MotionTab _theMotionTab;
	
	
	public LastPageTab(int camType, int camProfile, int downStream){
		super();
		
		setLayout(new BorderLayout());
		
		_theTabbedPane = new JTabbedPane();
		_geomTab = new GeometryDataTab(camType, camProfile, downStream);
		_signTab = new SignDefinitionTab();
		_theMotionTab = new MotionTab();
		_theTabbedPane.addTab(LanguageFactory.getInstance().getExpresion(MOTION_TAB_TITLE), _theMotionTab);
		_theTabbedPane.addTab(LanguageFactory.getInstance().getExpresion(GEOMETRY_TAB_TITLE), _geomTab);
		_theTabbedPane.addTab(LanguageFactory.getInstance().getExpresion(SIGN_DEFINITION_TAB_TITLE), _signTab);
		
		add(_theTabbedPane, BorderLayout.CENTER);
		Dimension d = _geomTab.getSize();
		setPreferredSize(d);
		setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
	}
	
	@Override
	public boolean areFieldsValid()
	{
		return true;
	}
	
	/**
	 * Name: getSegments
	 * Args: @return
	 * Return: ArrayList<String>
	 * Desc: returns a list with the selected segments
	 */
	public ArrayList<String> getSegments()
	{
		return _theMotionTab.getSelectedSegments();
	}
	
	public int getSegmentNo()
	{
		return _theMotionTab.getNoOfSegments();
	}
	
	public ArrayList<Float> getSelectedPHI()
	{
		return _theMotionTab.getPHI();
	}

	public ArrayList<Float> getSelectedPSI()
	{
		return _theMotionTab.getPSI();
	}
	
	public HashMap<String, Float> getGoem()
	{
		return _geomTab.getGeometricSelections();
	}
	public Boolean getCamSign()
	{
		return _signTab.getCamSign();
	}
	
	public Boolean getLevelSign()
	{
		return _signTab.getLeverSign();
	}
	public Integer getTotalPHI()
	{
		return _theMotionTab.getTotalPhi();
	}
	public Integer getTotalPSI()
	{
		return _theMotionTab.getTotalPsi();
	}
}
