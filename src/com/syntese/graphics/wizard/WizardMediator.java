package com.syntese.graphics.wizard;

import javax.swing.JFrame;

import com.syntese.graphics.wizard.dialog.WizardDialog;
import com.syntese.graphics.wizard.pages.CamTypePage;

/**
 * @author Cosmin
 * Desc : The class that mediates the wizard 
 */
public class WizardMediator {

	/**************
	 * PROPERTIES
	 * ************/
	private WizardDialog _theDialog;
	
	
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
	}
	
	
	public void start(){
		_theDialog.addMainPanel(new CamTypePage());
		_theDialog.setVisible(true);
	}
	
	/*
	 * PRIVATE
	 * */
}
