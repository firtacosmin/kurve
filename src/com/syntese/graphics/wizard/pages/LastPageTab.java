package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class LastPageTab extends JPanel{

	private GeometryDataPage _geomTab; 
	private JTabbedPane _theTabbedPane;
	
	public LastPageTab(int camType, int camProfile, int downStream){
		super();
		
		_theTabbedPane = new JTabbedPane();
		_geomTab = new GeometryDataPage(camType, camProfile, downStream);
		_theTabbedPane.addTab("Geometry", _geomTab);
		
		add(_theTabbedPane, BorderLayout.CENTER);
	}
	
}
