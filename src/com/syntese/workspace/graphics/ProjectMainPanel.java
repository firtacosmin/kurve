package com.syntese.workspace.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ProjectMainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3802315373185822874L;
	/*UI COMPONENTS*/
	private JTabbedPane _theTabPanel;
	
	
	private ArrayList<String> _projectsTitle;
	/*PUBLIC*/
	public ProjectMainPanel()
	{
		super();
		_projectsTitle = new ArrayList<String>();
		initComponents();
		addComponents();
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Name: getSelectedPanelTitle
	 * Args: @return
	 * Return: String
	 * Desc: returns the title of the selected ( active ) tab
	 */
	public String getSelectedPanelTitle()
	{
		return _projectsTitle.get(_theTabPanel.getSelectedIndex());
	}
	
	/*PRIVATE*/

	private void initComponents() {
		_theTabPanel = new JTabbedPane();
	}

	private void addComponents() {
		setLayout(new BorderLayout());
		add(_theTabPanel, BorderLayout.CENTER);
	}
	
	public void openProject(String projTitle, JPanel projPanel)
	{
		if ( !_projectsTitle.contains(projTitle) ){
			_theTabPanel.add(projTitle, projPanel);
			_projectsTitle.add(projTitle);
			_theTabPanel.setSelectedIndex(_projectsTitle.indexOf(projTitle));
		}else{
			_theTabPanel.setSelectedIndex(_projectsTitle.indexOf(projTitle));
		}
	}
}
