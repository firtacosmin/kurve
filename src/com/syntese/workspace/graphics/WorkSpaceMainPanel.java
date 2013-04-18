package com.syntese.workspace.graphics;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.syntese.graphics.GBC;
import com.syntese.project.data.ProjectData;
import com.syntese.workspace.Workspace;

public class WorkSpaceMainPanel extends JPanel {

	private ProjectsTreePanel _treePan;
	private ProjectMainPanel _mainPan;
	
	
	private Workspace _theWorkspace;
	
	public WorkSpaceMainPanel(Workspace theWorkspace)
	{
		super();
		_theWorkspace = theWorkspace;
		initUI();
		addUI();
	}
	
	
	private void initUI()
	{
		
		
		_treePan = new ProjectsTreePanel(_theWorkspace.get_projects());
		_mainPan = new ProjectMainPanel();
	}
	
	private void addUI()
	{

		setLayout(new GridBagLayout());
		add(_treePan, new GBC(0,0).setWeight(0, 100).setFill(GBC.VERTICAL).setAnchor(GBC.NORTHWEST).setInsets(10, 15, 10, 15));
		add(_mainPan, new GBC(1,0).setWeight(100, 100).setFill(GBC.BOTH).setAnchor(GBC.NORTHWEST).setInsets(0,0,0,0));
	}


	public void addProjToTree(ProjectData newProj) {
		_treePan.addProject(newProj);		
	}
	
}
