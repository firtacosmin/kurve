package com.syntese.language;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.syntese.settings.SettingsFactory;

public class Language implements ILanguage{
	private static final String ROOT_NODE_NAME = "language";
	private static final String LANGUAGE_FOLDER = "Language";
	private static final String LANGUAGE_SETTING_NAME = "language";
	private static final String EXPRESION = "expression";
	private static final String NAME_TAG = "name";
	private static final String VALUE_TAG = "value";

	
	private HashMap<String, String> _expresions;
	private ArrayList<String> _languages;
	
	/*PUBLIC*/
	
	/**
	 * Name: Language
	 * Args: 
	 */
	public Language(){
		/*init words*/
		_expresions = new HashMap<String, String>();
		_languages = new ArrayList<String>();
		/*get the languages*/
		getLanguages();
		/*get expresions from current language*/
		getExpresions();
	}
	
	
	/**
	 * Name: getExpresion
	 * Args: @param name
	 * Args: @return
	 * Return: String
	 * Desc: returns the value of the expression with the passed name
	 */
	public String getExpresion(String name){
		return _expresions.get(name);
	}
	

	/* (non-Javadoc)
	 * @see com.syntese.language.ILanguage#getAvailableLanguages()
	 */
	@Override
	public ArrayList<String> getAvailableLanguages() {
		return _languages;
	}

	/* (non-Javadoc)
	 * @see com.syntese.language.ILanguage#ChangeLanguage(java.lang.String)
	 */
	@Override
	public boolean changeLanguage(String newLanguage) {
		if ( _languages.contains(newLanguage) ){
			_expresions.clear();
			SettingsFactory.getInstance().setCurrentSetting(LANGUAGE_SETTING_NAME, newLanguage);
			getExpresions();
			return true;
		}
		else{
			return false;
		}
	}
	

	/*PRIVATE*/
	
	/**
	 * Name: getExpresions
	 * Args: 
	 * Return: void
	 * Desc: scans Language folder for xml files that have the root node called "language"
	 */
	private void getLanguages() {
		File languageFolder = new File(LANGUAGE_FOLDER);
		for (File f : languageFolder.listFiles()){
			if ( f.getName().indexOf(".xml") != -1 ){
				if ( isLanguageFile(f) ){
					_languages.add(f.getName().split(".xml")[0]);
				}
			}
		}
	}

	/**
	 * Name: isLanguageFile
	 * Args: @param f
	 * Args: @return
	 * Return: boolean
	 * Desc: gets the root node of the passed file and returns true if the node's name 
	 * 		 is the language node
	 */
	private boolean isLanguageFile(File f) {
		try {
			DocumentBuilder builder;
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document lang = builder.parse(f);
			Element root = lang.getDocumentElement();
			if( root.getNodeName() == ROOT_NODE_NAME && !root.getAttribute("type").isEmpty() )
			{
				return true;
			}else{
				return false;
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO ERROR
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Name: getExpresions
	 * Args: 
	 * Return: void
	 * Desc: gets the current or the default language file from the 
	 *       settings and reads from it all the saved expressions 
	 *       and saves them into the hash MAP
	 */
	private void getExpresions() {
		String defaultLanguage = SettingsFactory.getInstance().getDefaultSetting(LANGUAGE_SETTING_NAME);
		String currentLanguage = SettingsFactory.getInstance().getCurrentSetting(LANGUAGE_SETTING_NAME);
		
		if ( currentLanguage != null &&
			 _languages.contains(currentLanguage)){
			/*Use the current language*/
			getLanguageExpresions(currentLanguage);
		} else {
			/*use the default language*/
			getLanguageExpresions(defaultLanguage);
		}
	}
	
	
	
	/**
	 * Name: getLanguageExpresions
	 * Args: @param language
	 * Return: void
	 * Desc: method that gets the expressions from the passed language
	 */
	private void getLanguageExpresions(String language){
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document lang = builder.parse(new File(LANGUAGE_FOLDER+"/"+language+".xml"));
			Element root = lang.getDocumentElement();
			NodeList set = root.getChildNodes();
			for( int i=0; i<set.getLength(); i++ ){
				if (set.item(i).getNodeName() == EXPRESION){
					getExpresion(set.item(i));
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Name: getExpresion
	 * Args: @param item
	 * Return: void
	 * Desc: gets the expression name and value from the passed expresion node
	 * 		 The passed node will have 2 children name and value node.
	 */
	private void getExpresion(Node item) {
		NodeList thenodes = item.getChildNodes();
		String value = "";
		String name = "";
		for ( int i=0; i< thenodes.getLength(); i++ ){
			if ( thenodes.item(i).getNodeName() == NAME_TAG ){
				Text val = (Text)(thenodes.item(i).getFirstChild());
				name = val.getData();
			}else if ( thenodes.item(i).getNodeName() == VALUE_TAG ){
				Text val = (Text)(thenodes.item(i).getFirstChild());
				value = val.getData();
			}
		}
		_expresions.put(name, value);
	}


	
	
}
