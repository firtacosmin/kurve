package com.syntese.graphics.wizard.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class LastPageTab extends JPanel{

	private GeometryDataPage _geomTab; 
	private JTabbedPane _theTabbedPane;
	
	public LastPageTab(int camType, int camProfile, int downStream){
		super();
		
		setLayout(new BorderLayout());
		
		_theTabbedPane = new JTabbedPane();
		_geomTab = new GeometryDataPage(camType, camProfile, downStream);
		_theTabbedPane.addTab("Geometry", _geomTab);
		
		add(_theTabbedPane, BorderLayout.CENTER);
		Dimension d = _geomTab.getSize();
		setPreferredSize(d);
		setSize(getPreferredSize());
		setMinimumSize(getPreferredSize());
		setMaximumSize(getPreferredSize());
	}
	
}
