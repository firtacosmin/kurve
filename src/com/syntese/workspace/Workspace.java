package com.syntese.workspace;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.syntese.graphics.mainFrame.MainFrame;
import com.syntese.project.data.ProjectData;
import com.syntese.workspace.graphics.WorkSpaceMainPanel;

public class Workspace {

	/*PROPERTIES*/
	private JFrame _theMainFrame;
	private ArrayList<ProjectData> _projects;
	
	
	/*UI ELEMENTS*/
	WorkSpaceMainPanel _thePanel;
	
	/*PUBLIC METHODS*/
	public Workspace()
	{
		_projects = new ArrayList<ProjectData>();
		
		_thePanel = new WorkSpaceMainPanel(this);
		
	}
	
	public WorkSpaceMainPanel get_thePanel()
	{
		return _thePanel;
	}
	
	public void openProject(ProjectData newProj)
	{
		_projects.add(newProj);
		_thePanel.addProjToTree(newProj);
	}
	public ArrayList<ProjectData> get_projects()
	{
		return _projects;
	}
	
	public void printWorkspace()
	{
	}

	/*PRIVATE METHODS*/

}
