package com.syntese.project.data;

import javax.swing.JPanel;

import com.syntese.language.LanguageFactory;
import com.syntese.project.graphics.InputParamsPanel;

public class ProjectMediator {
	
	private ProjectData _projDt;
	private static final String TEXT_VALUES_PREFIX = "InputParams_disp_";
	
	/*GRAPHICS*/
	private InputParamsPanel _inputParamsPan;
	

	/*PUBLIC*/
	
	/**
	 * Name: ProjectMediator
	 * Args: Constructor that construct the project based on a project File
	 */
	public ProjectMediator(String projectFile) throws ProjectException{
		_projDt = new ProjectData();
		if ( !_projDt.open(projectFile) )
		{
			throw new ProjectException();
		}
		_inputParamsPan = new InputParamsPanel();
		_inputParamsPan.setGeomParams(_projDt.get_cam_geomData());
	}
	
	/**
	 * Name: ProjectMediator
	 * Args: Constructor that constructs a project based on a projectData object
	 */
	public ProjectMediator(ProjectData projData){
		_projDt = new ProjectData(projData);
		_inputParamsPan = new InputParamsPanel();
		_inputParamsPan.setGeomParams(_projDt.get_cam_geomData());
	}
	
	/**
	 * Name: saveProject
	 * Args: @param folder
	 * Return: void
	 * Desc: saves the project into the specified folder
	 */
	public void saveProject(String folder){
		_projDt.saveProjectFile(folder);
	}
	

	public JPanel getInputParamsPan()
	{
		return _inputParamsPan;
	}
	
	public String getProjectName()
	{
		return _projDt.get_proj_name();
	}
	
	/*PRIVATE*/
}
