package com.syntese.language;


public class LanguageFactory {
	private static boolean instance = false;
	private static Language _theLanguage = null;
	
	public static ILanguage getInstance(){
		if ( !instance ){
			_theLanguage = new Language();
			instance = true;
		}
		
		return _theLanguage;
	}
}
