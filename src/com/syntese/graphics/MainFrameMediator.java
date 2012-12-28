package com.syntese.graphics;

import com.syntese.language.LanguageFactory;
import com.syntese.settings.SettingsFactory;

public class MainFrameMediator implements MainFrameActionListener {

	private MainFrame _theFrame;
	
	/*
	 * public
	 * */
	
	public MainFrameMediator(){
		_theFrame = new MainFrame();
		_theFrame.addActionListener(this);
		_theFrame.setVisible(true);
	}
	
	@Override
	public void newMenuClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openMenuClick() {
		// TODO Auto-generated method stub
		
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

		
	/*
	 * PRIVATE
	 * */
}
