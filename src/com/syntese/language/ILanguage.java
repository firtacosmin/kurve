package com.syntese.language;

import java.util.ArrayList;

public interface ILanguage {
	public String getExpresion(String name);
	public ArrayList<String> getAvailableLanguages();
	public boolean changeLanguage(String newLanguage);
}
