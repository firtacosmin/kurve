package com.syntese.settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
	private static final String ROOT_TAG_NAME = "settings";
	private static final String DEFAULT_TAG_NAME = "default";
	private static final String CURRENT_TAG_NAME = "current";
	
	
	private HashMap<String, String> _currentSettings;
	private HashMap<String, String> _defaultSettings;
	
	
	private boolean _modified = false;
	
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
	
	
	/**
	 * Name: setCurrentSetting
	 * Args: @param settingName
	 * Args: @param settingValue
	 * Args: @return
	 * Return: boolean
	 * Desc: the method will see if the passed setting name exists into the setting map, 
	 *       if not it will return false. If the setting exists then it's value will be 
	 *       modified with the passed value
	 */
	public boolean setCurrentSetting(String settingName, String settingValue){
		if ( _currentSettings.containsKey(settingName) ){
			_currentSettings.remove(settingName);
			_currentSettings.put(settingName, settingValue);
			_modified = true;
			return true;
		}else{
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.syntese.settings.ISettings#save()
	 */
	public void save(){
		if ( _modified ){
			DocumentBuilder builder;
			try {
				builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				/*create the new file*/
				Document newDoc = builder.newDocument();
				Element rootElement = newDoc.createElement(ROOT_TAG_NAME);
				Element currentElement = newDoc.createElement(CURRENT_TAG_NAME);
				Element defaultElement = newDoc.createElement(DEFAULT_TAG_NAME);
				/*add values to the current element*/
				for ( String key : _currentSettings.keySet() ){
					Element el = newDoc.createElement(key);
					Text elValue = newDoc.createTextNode(_currentSettings.get(key));
					el.appendChild(elValue);
					currentElement.appendChild(el);
				}
				/*add values to the default element*/
				for ( String key : _defaultSettings.keySet() ){
					Element el = newDoc.createElement(key);
					Text elValue = newDoc.createTextNode(_defaultSettings.get(key));
					el.appendChild(elValue);
					defaultElement.appendChild(el);
				}
				/*add default and current elements to the root element*/
				rootElement.appendChild(defaultElement);
				rootElement.appendChild(currentElement);
				/*add route element to the doc*/
				newDoc.appendChild(rootElement);
				/*save the document*/
				Transformer t = TransformerFactory.newInstance().newTransformer();
				t.transform(new DOMSource(newDoc), new StreamResult(new FileOutputStream(new File(SETTINGS_FILE))));
				
			}catch ( Exception e ){
				/*TODO: error to log*/
			}
		}
		else
		{
			/*if not modified then there is nothing to save*/
		}
	}
	
	/*PRIVATE*/


	/**
	 * Name: updateCurrentSettings
	 * Args: @param item the current settings node
	 * Return: void
	 * Desc: updates the current settings with the new settings
	 */
	@SuppressWarnings("unused")
	private void updateCurrentSettings(Node item) {
		for(String key : _currentSettings.keySet()) {
			 ((Element)item).getElementsByTagName(key).item(0).setNodeValue(_currentSettings.get(key));
		}
	}

	/**
	 * Name: parseSettingsFile
	 * Args: 
	 * Return: void
	 * Desc: Parsses the settings file to extract the setti.ngs and saves them
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
				try{
				Text value = (Text) children.item(i).getFirstChild();
				hash.put(child.getNodeName(), value.getData());
				}catch(Exception ex){
					//TODO: error
					ex.printStackTrace();
					hash.put(child.getNodeName(), "");
				}
			}
		}
	}
	
}
