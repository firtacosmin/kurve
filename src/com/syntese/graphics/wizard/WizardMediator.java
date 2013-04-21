package com.syntese.graphics.wizard;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.syntese.graphics.wizard.dialog.WizardDialog;
import com.syntese.graphics.wizard.dialog.WizardDialogActionListener;
import com.syntese.graphics.wizard.pages.CamProfilePage;
import com.syntese.graphics.wizard.pages.CamTypePage;
import com.syntese.graphics.wizard.pages.DownstreamPage;
import com.syntese.graphics.wizard.pages.LastPageTab;
import com.syntese.graphics.wizard.pages.WizardPage;
import com.syntese.project.data.ProjectData;

/**
 * @author Cosmin
 * Desc : The class that mediates the wizard 
 */
public class WizardMediator implements WizardDialogActionListener{

	/**************
	 * PROPERTIES
	 * ************/
	private static final int CAM_TYPE = 0;
	private static final int DOWNSTREAM_COMPONENTS = 1;
	private static final int CAM_PROFILE = 2;

	private JFrame _mainFrame;
	
	private WizardDialog _theDialog;
	/*the wizard pages*/
	private ArrayList<WizardPage> _pages;  
	
	/*the current page*/
	private WizardPage _currentPage;
	/*teh configured type of cams*/
	private int _camType;
	private int _camProfile;
	private int _camDownstream;
	private String _projName;
	private int _currentState;
	
	private WizardListener _theListener;
	
	private ProjectData _theNewProject;
	/**************
	 * METHODS
	 * ************/
	
	/*
	 * PUBLIC
	 * */
	
	public WizardMediator(JFrame mainFrame)
	{
		/*create the dialog*/
		_theDialog = new WizardDialog(mainFrame);
		_theDialog.addActionLisner(this);
		_currentState = CAM_TYPE;
		_pages = new ArrayList<WizardPage>();
		_mainFrame = mainFrame;
		_theListener = null;
		
	}
	
	public void setWizardListener(WizardListener lst){
		_theListener = lst;
	}
	
	
	public void start(){
		_currentPage = new CamTypePage();
		_currentState = 0;
		_theDialog.addMainPanel(_currentPage);
//		_theDialog.addMainPanel(new CamProfilePage(CamProfilePage.ROLLER_SLIDE));
//		_theDialog.addMainPanel(new CamProfilePage(CamProfilePage.ROLLER_SLIDE));
//		_theDialog.addMainPanel(new DownstreamPage(DownstreamPage.ROLLER_LEVER));
		_theDialog.setVisible(true);
	}


	@Override
	public void next() {
		if ( currentPageCorect() ){
			goToNextPage();
		}
	}





	@Override
	public void previous() {
		goToPreviousPage();
	}


	@Override
	public void cancel() {
		_theDialog.setVisible(false);
	}
	
	/*
	 * PRIVATE
	 * */


	private boolean currentPageCorect() {
		return true;
	}

	/**
	 * Name: goToNextPage
	 * Args: 
	 * Return: void
	 * Desc: going to the next page
	 */
	private void goToNextPage() {
		switch (_currentState) {
		case 0:
			
			/*if the fields of the current page are valid then go to the next one*/
			if ( _currentPage.areFieldsValid() )
			{
				_camType = ((CamTypePage)_currentPage).getSelectedType();
				_projName = ((CamTypePage)_currentPage).getImputName();
				_currentState++;
				_currentPage = new DownstreamPage(_camType);
				_theDialog.addMainPanel(_currentPage);
				_theDialog.repaint();
			}

			//_camProfile = ((CamProfilePage)_currentPage).getSelection();
//			_currentState = 3;
//			_currentPage = new LastPageTab(0,1,1);
//			_theDialog.addMainPanel(_currentPage);
//			_theDialog.repaint();
			break;
		case 1:
			
			/*if the fields of the current page are valid then go to the next one*/
			if ( _currentPage.areFieldsValid() )
			{
				_camDownstream = ((DownstreamPage)_currentPage).getSelectedCategory();
				_currentState++;
				_currentPage = new CamProfilePage(_camType);
				_theDialog.addMainPanel(_currentPage);
				_theDialog.repaint();
			}
			break;

		case 2:
			
			/*if the fields of the current page are valid then go to the next one*/
			if ( _currentPage.areFieldsValid() )
			{
				_camProfile = ((CamProfilePage)_currentPage).getSelection();
				_currentState++;
				_currentPage = new LastPageTab(_camType, _camProfile, _camDownstream);
				_theDialog.addMainPanel(_currentPage);
				_theDialog.repaint();
			}
			break;
		case 3:
			/*create the project*/
			createProject();
//			JFileChooser filedlg = new JFileChooser();
//			filedlg.setCurrentDirectory(new File("."));
//			filedlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//			int res = filedlg.showSaveDialog(_mainFrame);
//			Boolean saved = false;
//			if ( res == JFileChooser.APPROVE_OPTION )
//			{
//				saved = _theNewProject.saveProjectFile(filedlg.getSelectedFile().getPath());
//			}
//			if ( saved == true )
//			{
				_theDialog.setVisible(false);
				if ( _theListener != null ){
					_theListener.wizardEnded(_theNewProject);
				}
//			}
			
			break;
		default:
			break;
		}		
	}
	


	/**
	 * Name: goToPreviousPage
	 * Args: 
	 * Return: void
	 * Desc: going to the previous page
	 */
	private void goToPreviousPage() {

		switch (_currentState) {
		case 0:
			/*do nothing*/
			break;
		case 1:
			_currentState--;
			_currentPage = new CamTypePage();
			_theDialog.addMainPanel(_currentPage);
			_theDialog.repaint();
			break;
		case 2:
			_currentState--;
			_currentPage = new DownstreamPage(_camType);
			_theDialog.addMainPanel(_currentPage);
			_theDialog.repaint();
			break;
		case 3:
			_currentState--;
			_currentPage = new CamProfilePage(_camType);
			_theDialog.addMainPanel(_currentPage);
			_theDialog.repaint();
			break;
		default:
			break;
		}	
	}
	
	/**
	 * Name: createProject
	 * Args: 
	 * Return: void
	 * Desc: created the ProjectData object with the selected configurations
	 */
	private void createProject() {
		_theNewProject = new ProjectData();
		/*add cam type*/
		_theNewProject.set_cam_type(_camType);
		/*add cam profile*/
		_theNewProject.set_cam_profile(_camProfile);
		/*add cam downstream*/
		_theNewProject.set_cam_downstream(_camDownstream);
		
		LastPageTab lastP = (LastPageTab)_currentPage;
		/*add can segments*/
		_theNewProject.set_cam_segments(lastP.getSegments());
		/*add the geom data*/
		_theNewProject.set_cam_geomData(lastP.getGoem());
		/*add segments no*/
		_theNewProject.set_cam_no_segments(lastP.getSegmentNo());
		/*add the phi*/
		_theNewProject.set_cam_phi(lastP.getSelectedPHI());
		/*add total phi*/
		_theNewProject.set_cam_total_phi(lastP.getTotalPHI());
		/*add the psi*/
		_theNewProject.set_cam_psi(lastP.getSelectedPSI());
		/*add total phi*/
		_theNewProject.set_cam_total_psi(lastP.getTotalPSI());
		/*set cam sign*/
		_theNewProject.set_cam_positive_sign(lastP.getCamSign());
		/*set lever sign*/
		_theNewProject.set_lever_positive_sign(lastP.getLevelSign());
		/*set the proj name*/
		_theNewProject.set_proj_name(_projName);
		
		//_theNewProject.saveProjectFile("c://ceva.xml");
	}

}
