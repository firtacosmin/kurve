package com.syntese.settings;

import java.io.File;
import java.io.IOException;
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

/*
 * class that provides the settings of the application.
 * The settings are saved into etc/settigns.xml file
 * */
public class Settings implements ISettings {
	
	private static final String SETTINGS_FILE = "etc/settings.xml";
	
	
	private HashMap<String, String> _currentSettings;
	private HashMap<String, String> _defaultSettings;
	
	/*PUBLIC*/
	
	
	/**
	 * Name: Settings
	 * Args: 
	 */
	public Settings(){
		/*Initialize the settings arrays*/
		_currentSettings = new HashMap<String, String>();
		_defaultSettings = new HashMap<String, String>();
		
		parseSettingsFile();
	}
	
	/**
	 * Name: getCurrentSetting
	 * Args: @param settingName
	 * Args: @return
	 * Return: String
	 * Desc: returns the value of the current setting with the passed name
	 */
	public String getCurrentSetting(String settingName){
		return _currentSettings.get(settingName);
	}
	
	/**
	 * Name: getDefaultSetting
	 * Args: @param settingName
	 * Args: @return
	 * Return: String
	 * Desc: returns the value of the default setting with the passed name
	 */
	public String getDefaultSetting(String settingName){
		return _defaultSettings.get(settingName);
	}
	
	/*PRIVATE*/


	/**
	 * Name: parseSettingsFile
	 * Args: 
	 * Return: void
	 * Desc: Parsses the settings file to extract the settings and saves them
	 */
	private void parseSettingsFile() {

		DocumentBuilder builder;
		try {
			
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document lang = builder.parse(new File(SETTINGS_FILE));
			Element root = lang.getDocumentElement();
			NodeList set = root.getChildNodes();
			for( int i=0; i<set.getLength(); i++ ){
				if (set.item(i).getNodeName() == "default"){
					getSettings(set.item(i), _defaultSettings);
				}else if ( set.item(i).getNodeName() == "current" ){
					getSettings(set.item(i), _currentSettings);
				}
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			//TODO: ERROR To be managed by an error manager
			e.printStackTrace();
		}
	}

	/**
	 * Name: getSettings
	 * Args: @param item
	 * Args: @param _currentSettings2
	 * Return: void
	 * Desc: gets the children from the passed node and saved them into the passed hashMap
	 */
	private void getSettings(Node item, HashMap<String, String> hash ) {
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
