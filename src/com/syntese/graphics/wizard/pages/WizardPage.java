package com.syntese.graphics.wizard.pages;

import java.util.HashMap;

public interface WizardPage{
	public boolean areFieldsValid();
	public HashMap<String, String> getProperties(); 
}
	