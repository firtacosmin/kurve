package com.syntese.settings;

public class SettingsFactory {

	private static boolean instance = false;
	private static Settings _theSettings = null;
	
	public static ISettings getInstance(){
		if ( !instance ){
			_theSettings = new Settings();
			instance = true;
		}
		
		return _theSettings;
	}
	
}
