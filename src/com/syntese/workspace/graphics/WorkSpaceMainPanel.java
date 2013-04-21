package com.syntese.workspace.graphics;

import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.syntese.graphics.GBC;
import com.syntese.project.data.ProjectData;
import com.syntese.workspace.Workspace;

public class WorkSpaceMainPanel extends JPanel implements ProjectTreeActionListener {

	private ProjectsTreePanel _treePan;
	private ProjectMainPanel _mainPan;
	
	
	private Workspace _theWorkspace;
	
	private ArrayList<WorkspaceGraphicsListener> _workspaceListeners;
	
	public WorkSpaceMainPanel(Workspace theWorkspace)
	{
		super();
		_theWorkspace = theWorkspace;
		_workspaceListeners = new ArrayList<WorkspaceGraphicsListener>();
		initUI();
		addUI();
	}
	
	
	public void addWorkspaceListener(WorkspaceGraphicsListener lst){
		_workspaceListeners.add(lst);
	}


	public void addProjToTree(ProjectData newProj) {
		_treePan.addProject(newProj.get_proj_name());		
	}
	
	public void openProject(String projName, JPanel projPan){
		_mainPan.openProject(projName, projPan);
	}


	@Override
	public void projectDoubleClicked(String project) {
		for ( int i=0; i<_workspaceListeners.size(); i++ ){
			_workspaceListeners.get(i).openProject(project);
		}
	}
	
	/**
	 * Name: getActiveProjectName
	 * Args: @return
	 * Return: String
	 * Desc: returns the active project's name
	 */
	public String getActiveProjectName(){
		return _mainPan.getSelectedPanelTitle();
	}
	
	
	/*PRIVATE*/
	
	private void initUI()
	{
		ArrayList<String> projectsNames = new ArrayList<String>();
		for ( int i=0; i< _theWorkspace.get_projects().size(); i++ )
		{
			projectsNames.add(_theWorkspace.get_projects().get(i).getProjectName());
		}
		_treePan = new ProjectsTreePanel(projectsNames);
		_treePan.addProjTreeActionListener(this);
		_mainPan = new ProjectMainPanel();
	}
	
	private void addUI()
	{

		setLayout(new GridBagLayout());
		add(_treePan, new GBC(0,0).setWeight(0, 100).setFill(GBC.VERTICAL).setAnchor(GBC.NORTHWEST).setInsets(10, 15, 10, 15));
		add(_mainPan, new GBC(1,0).setWeight(100, 100).setFill(GBC.BOTH).setAnchor(GBC.NORTHWEST).setInsets(0,0,0,0));
	}
	
}
