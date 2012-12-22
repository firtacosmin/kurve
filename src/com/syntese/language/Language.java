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

public class Language {
	private static final String ROOT_NODE_NAME = "language";
	private static final String LANGUAGE_FOLDER = "Language";
	private static final String LANGUAGE_SETTING_NAME = "language";
	private static final String EXPRESION = "expresion";

	
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
		getAvailableLanguages();
		/*get expresions from current language*/
		getExpresions();
	}

	/*PRIVATE*/
	
	/**
	 * Name: getExpresions
	 * Args: 
	 * Return: void
	 * Desc: scans Language folder for xml files that have the root node called "language"
	 */
	private void getExpresions() {
		File languageFolder = new File(LANGUAGE_FOLDER);
		for (File f : languageFolder.listFiles()){
			if ( f.getName().split(".")[1].equals("xml") ){
				if ( isLanguageFile(f) ){
					_languages.add(f.getName().split(".")[0]);
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
			if( root.getNodeName() == ROOT_NODE_NAME )
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
	 * Name: getAvailableLanguages
	 * Args: 
	 * Return: void
	 * Desc: gets the current or the default language file from the 
	 *       settings and reads from it all the saved expressions 
	 *       and saves them into the hash MAP
	 */
	private void getAvailableLanguages() {
		String defaultLanguage = SettingsFactory.getInstance().getDefaultSetting(LANGUAGE_SETTING_NAME);
		String currentLanguage = SettingsFactory.getInstance().getCurrentSetting(LANGUAGE_SETTING_NAME);
		
		if ( currentLanguage != null &&
			 _languages.contains(currentLanguage)){
			/*Use the current language*/
			DocumentBuilder builder;
			try {
				builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				Document lang = builder.parse(new File(LANGUAGE_FOLDER+"/"+currentLanguage+".xml"));
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
		} else {
			/*use the default language*/
		}
	}
	
	
	/**
	 * Name: getExpresion
	 * Args: @param item
	 * Return: void
	 * Desc: gets the expresion name and value from the passed expresion node
	 */
	private void getExpresion(Node item) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Name: getExpresions
	 * Args: @param item
	 * Args: @param _currentSettings2
	 * Return: void
	 * Desc: gets the children from the passed node and saved them into the passed hashMap
	 */
	private void getExpresions(Node item, HashMap<String, String> hash ) {
		NodeList children = item.getChildNodes();
		for( int i=0; i<children.getLength(); i++ ){
			if ( children.item(i).getNodeName() != "#text" ){
				Node child = children.item(i);
				Text value = (Text) children.item(i).getFirstChild();
				hash.put(child.getNodeName(), value.getData());
			}
		}
	}
}
