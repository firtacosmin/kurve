/**
 * 
 */
package com.syntese.graphics.wizard.dialog;

/**
 * @author Cosmin
 *	This is the interface that needs to be implemented so that a 
 *  class can listen for the WizardDialog actions
 */
public interface WizardDialogActionListener {

	public void next();
	public void previous();
	public void cancel();
	
	
}
