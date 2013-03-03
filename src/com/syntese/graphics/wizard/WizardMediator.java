package com.syntese.graphics.wizard;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.syntese.graphics.wizard.dialog.WizardDialog;
import com.syntese.graphics.wizard.dialog.WizardDialogActionListener;
import com.syntese.graphics.wizard.pages.CamProfilePage;
import com.syntese.graphics.wizard.pages.CamTypePage;
import com.syntese.graphics.wizard.pages.DownstreamPage;
import com.syntese.graphics.wizard.pages.LastPageTab;
import com.syntese.graphics.wizard.pages.WizardPage;

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
	
	private WizardDialog _theDialog;
	/*the wizard pages*/
	private ArrayList<WizardPage> _pages;  
	
	/*the current page*/
	private JPanel _currentPage;
	/*teh configured type of cams*/
	private int _camType;
	private int _camProfile;
	private int _camDownstream;
	
	private int _currentState;
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
			_camType = ((CamTypePage)_currentPage).getSelectedType();
			_currentState++;
			_currentPage = new DownstreamPage(_camType);
			_theDialog.addMainPanel(_currentPage);
			_theDialog.repaint();

			//_camProfile = ((CamProfilePage)_currentPage).getSelection();
//			_currentState = 3;
//			_currentPage = new LastPageTab(0,1,1);
//			_theDialog.addMainPanel(_currentPage);
//			_theDialog.repaint();
			break;
		case 1:
			_camDownstream = ((DownstreamPage)_currentPage).getSelectedCategory();
			_currentState++;
			_currentPage = new CamProfilePage(_camType);
			_theDialog.addMainPanel(_currentPage);
			_theDialog.repaint();
			break;

		case 2:
			_camProfile = ((CamProfilePage)_currentPage).getSelection();
			_currentState++;
			_currentPage = new LastPageTab(_camType, _camProfile, _camDownstream);
			_theDialog.addMainPanel(_currentPage);
			_theDialog.repaint();
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

}
