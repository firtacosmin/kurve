package com.syntese.workspace;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.syntese.graphics.mainFrame.MainFrameMediator;
import com.syntese.language.Language;
import com.syntese.language.LanguageFactory;
import com.syntese.log.Log;
import com.syntese.project.data.ProjectData;
import com.syntese.project.data.ProjectMediator;
import com.syntese.settings.SettingsFactory;
import com.syntese.workspace.graphics.ChoseWorkspaceDialog;
import com.syntese.workspace.graphics.WorkSpaceMainPanel;
import com.syntese.workspace.graphics.WorkspaceGraphicsListener;

public class Workspace implements WorkspaceGraphicsListener {

	/*PROPERTIES*/
	private static final String WORKSPACE_PATH_SETING_NAME="workspacePath";
	
	private JFrame _theMainFrame;
	private ArrayList<ProjectMediator> _projects;
	private WorkspaceFile _workspaceFile;
	
	
	/*UI ELEMENTS*/
	WorkSpaceMainPanel _thePanel;
	private MainFrameMediator _mainFrame;
	
	private String _workspacePath;
	
	/*PUBLIC METHODS*/
	/**
	 * @Name: Workspace constructor
	 * @Args: none 
	 * @desc: constructs the workspace class. 
	 *        Opens dialog for workspace folder.
	 *        opens MainFrame with selected workspace 
	 */
	public Workspace()
	{
		_projects = new ArrayList<ProjectMediator>();
		
		_thePanel = new WorkSpaceMainPanel(this);
		
		_thePanel.addWorkspaceListener(this);
		_mainFrame = new MainFrameMediator(this, _thePanel);
		
		/*ask for workspace path*/
		askForWorkspace();
		
	}

	/**
	 * Name: saveSelectedProjecT
	 * Args: 
	 * Return: void
	 * Desc: Saves the project that is activ
	 */
	public void saveSelectedProject()
	{
		String activeProject = _thePanel.getActiveProjectName();
		for ( ProjectMediator proj : _projects ){
			if ( proj.getProjectName().equals(activeProject) ){
				proj.saveProject(_workspacePath);
				break;
			}
		}
	}
	
	public void saveAllProjects()
	{
		for ( ProjectMediator proj : _projects ){
			proj.saveProject(_workspacePath);
		}
	}
	
	public WorkSpaceMainPanel get_thePanel()
	{
		return _thePanel;
	}
	
	public void openProject(ProjectData newProj)
	{
		_projects.add(new ProjectMediator(newProj));
		_thePanel.addProjToTree(newProj);
		_workspaceFile.addProjectToWorkspace(newProj.get_proj_name());
	}
	/**
	 * Name: openProjectFromFile
	 * Args: @param newProj
	 * Return: void
	 * Desc: loads a project from a file placed into the workspace
	 */
	public void openProjectFromFile(String newProj)
	{
		ProjectData newProjDt = new ProjectData();
		newProjDt.open(_workspacePath+"\\"+newProj);
		openProject(newProjDt);
	}
	public ArrayList<ProjectMediator> get_projects()
	{
		return _projects;
	}
	
	public void printWorkspace()
	{
	}


	@Override
	public void openProject(String ProjectTitle) {
		System.out.println(ProjectTitle);
		Boolean found = false;
		ProjectMediator projToOpen = null;
		for ( int i=0; i<_projects.size() && !found; i++ ){
			if ( _projects.get(i).getProjectName().equals(ProjectTitle) )
			{
				projToOpen = _projects.get(i);
				found = true;
			}
		}
		if ( projToOpen != null ){
			_thePanel.openProject(ProjectTitle, projToOpen.getInputParamsPan());
		}
		
	}
	/**
	 * Name: saveWorkspace
	 * Args: 
	 * Return: void
	 * Desc: method to save the workspace file
	 */
	public void saveWorkspace()
	{
		_workspaceFile.saveWorkspaceFile();
	}

	/**
	 * @Name: getWotkspacePath
	 * @Args: null
	 * @Return: String
	 * @Desc: returns the path to the workspace folder.
	 */
	public String getWotkspacePath() {
		return _workspacePath;
	}
	/*PRIVATE METHODS*/
	
	/**
	 * @Name: askForWorkspace
	 * @Args: 
	 * @Return: void
	 * @Desc: method that displays a dialog asking for the workspace folder.
	 */
	private void askForWorkspace(){
		ChoseWorkspaceDialog dlg = new ChoseWorkspaceDialog(null);
		String pastPath = SettingsFactory.getInstance().getCurrentSetting(WORKSPACE_PATH_SETING_NAME);
		if ( pastPath != null ){
			dlg.addPath(pastPath);
		}
		dlg.setVisible(true); 
		_workspacePath = dlg.getSelection(); 
		Log.syntese.debug("Application Started. Selected Workspace="+_workspacePath);
		if ( checkWorkspacePath() ){
			startMainFrame();
		}else{
			/*exit*/
			Log.syntese.debug("No workspace selected. Will exit the application");
			System.exit(0);
		}
	}
	
	/**
	 * @Name: startMainFrame
	 * @Args: 
	 * @Return: void
	 * @Desc: method that starts the mainFrame. 
	 * @Worning: Should not be started with unexisting workspace folder 
	 *  
	 */
	private void startMainFrame(){

		Log.syntese.debug("Starting application with workspace:" + _workspacePath);
		SettingsFactory.getInstance().setCurrentSetting(WORKSPACE_PATH_SETING_NAME, _workspacePath);
		SettingsFactory.getInstance().save();
		_workspaceFile = new WorkspaceFile(_workspacePath);
		Log.syntese.debug("Starting application with workspace file:" + _workspacePath);
		loadWorkspaceProjects();
		_mainFrame.displayFrame();
	}
	
	/**
	 * Name: checkWorkspacePath
	 * Args: null
	 * Return: Boolean
	 * @Desc: check if the selected workspace is an existing folder. 
	 */
	private Boolean checkWorkspacePath()
	{
		try{
			if ( new File(_workspacePath).isDirectory() )
			{
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		
		return false;
	}
	
	/**
	 * Name: loadWorkspaceProjects
	 * Args: 
	 * Return: void
	 * Desc: loads the projects from the workspace file into the software
	 */
	private void loadWorkspaceProjects() {
		if ( _workspaceFile.isPrevWorkspaceFile() ){
			for ( String projName : _workspaceFile.getProjects() ){
				try {
					ProjectData newProjDt = new ProjectData();
					if (newProjDt.open(_workspacePath+"\\"+projName+".xml"))
					{
						_projects.add(new ProjectMediator(newProjDt));
						_thePanel.addProjToTree(newProjDt);
					}
				} catch (Exception e) {
					//TODO error
					e.printStackTrace();
				}
			}
		}
	}


}
