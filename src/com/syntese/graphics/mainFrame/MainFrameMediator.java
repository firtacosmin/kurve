package com.syntese.graphics.mainFrame;

import java.io.File;

import javax.swing.JFileChooser;

import com.syntese.graphics.wizard.WizardListener;
import com.syntese.graphics.wizard.WizardMediator;
import com.syntese.language.LanguageFactory;
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
	
	public MainFrameMediator(){
		_theWorkspace = new Workspace();
		_theFrame = new MainFrame(_theWorkspace.get_thePanel());
		_theWizard = new WizardMediator(_theFrame);
		
		_theFrame.addActionListener(this);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveMenuClick(int saveType) {
		// TODO Auto-generated method stub
		
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
	}
	
	@Override
	public void wizardEnded(ProjectData theNewProject)
	{
		_theWorkspace.openProject(theNewProject);
		_theFrame.repaint();
	}

		
	/*
	 * PRIVATE
	 * */
}
