package com.syntese.graphics.mainFrame;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import com.syntese.graphics.wizard.WizardListener;
import com.syntese.graphics.wizard.WizardMediator;
import com.syntese.language.LanguageFactory;
import com.syntese.log.Log;
import com.syntese.project.data.ProjectData;
import com.syntese.settings.SettingsFactory;
import com.syntese.workspace.Workspace;

public class MainFrameMediator implements MainFrameActionListener,WizardListener {

	private MainFrame _theFrame;
	private WizardMediator _theWizard;
	private Workspace _theWorkspace;
	
	/*
	 * public
	 * */
	
	public MainFrameMediator(Workspace workspace, JPanel mainPan){
		_theWorkspace = workspace;
		_theFrame = new MainFrame(mainPan);
		_theWizard = new WizardMediator(_theFrame);
		
		_theFrame.addActionListener(this);
	}
	
	public void displayFrame(){
		_theFrame.setVisible(true);
	}
	
	@Override
	public void newMenuClick() {
		_theWizard.setWizardListener(this);
		_theWizard.start();
	}

	@Override
	public void openMenuClick() {
		JFileChooser filedlg = new JFileChooser();
		filedlg.setCurrentDirectory(new File("."));
		filedlg.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int res = filedlg.showOpenDialog(_theFrame);
		if ( res == JFileChooser.APPROVE_OPTION )
		{
			ProjectData newProj = new ProjectData();
			newProj.open(filedlg.getSelectedFile().getAbsolutePath());
			_theWorkspace.openProject(newProj);
			_theFrame.repaint();
		}
		
	}

	@Override
	public void closeMenuClick() {
		frameClosing();
		_theFrame.setVisible(false);
		System.exit(0);
	}

	@Override
	public void saveMenuClick(int saveType) {
		if ( saveType == 2 ){
		_theWorkspace.saveSelectedProject();
		}else if ( saveType == 1 ){
			_theWorkspace.saveAllProjects();
		}
	}

	@Override
	public void aboutMenuClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void languageMenuClick(String newLanguage) {
		if (LanguageFactory.getInstance().changeLanguage(newLanguage)){
			_theFrame.updateTexts();
		}
	}
	
	@Override
	public void frameClosing(){
		/*save the settings*/
		SettingsFactory.getInstance().save();
		_theWorkspace.saveWorkspace();
	}
	
	@Override
	public void wizardEnded(ProjectData theNewProject)
	{
		Log.syntese.debug("Created a new project: "+theNewProject.get_proj_name());
		/*save the newlly created project*/
		theNewProject.saveProjectFile(_theWorkspace.getWotkspacePath());
		Log.syntese.debug("Created a new project file: "+_theWorkspace.getWotkspacePath()+"/"+theNewProject.get_proj_name()+".xml");
		/*open it into the workspace*/
		_theWorkspace.openProject(theNewProject);
		/*repaint the workspace*/
		_theFrame.repaint();
	}

		
	/*
	 * PRIVATE
	 * */
}
