package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.syntese.graphics.wizard.pages.lastPage.GeometryDataTab;
import com.syntese.graphics.wizard.pages.lastPage.SignDefinitionTab;
import com.syntese.language.LanguageFactory;

public class LastPageTab extends WizardPage{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String GEOMETRY_TAB_TITLE = "Wizard_GeomData_TabTitle";
	private static final String SIGN_DEFINITION_TAB_TITLE = "Wizard_SignDef_tabTitle";
	
	private GeometryDataTab _geomTab; 
	private SignDefinitionTab _signTab;
	private JTabbedPane _theTabbedPane;
	
	
	public LastPageTab(int camType, int camProfile, int downStream){
		super();
		
		setLayout(new BorderLayout());
		
		_theTabbedPane = new JTabbedPane();
		_geomTab = new GeometryDataTab(camType, camProfile, downStream);
		_signTab = new SignDefinitionTab();
		_theTabbedPane.addTab(LanguageFactory.getInstance().getExpresion(GEOMETRY_TAB_TITLE), _geomTab);
		_theTabbedPane.addTab(LanguageFactory.getInstance().getExpresion(SIGN_DEFINITION_TAB_TITLE), _signTab);
		
		add(_theTabbedPane, BorderLayout.CENTER);
		Dimension d = _geomTab.getSize();
		setPreferredSize(d);
		setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
	}
	
}
