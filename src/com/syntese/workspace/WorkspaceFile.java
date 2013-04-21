package com.syntese.workspace;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class WorkspaceFile {

	private static final String FILE_NAME=".kurveworkspace";
	private static final String ROOT_TAG_NAME="KURVEWORKSPACE";
	private static final String PROJ_TAG_NAME="project";
	private String _workspacePath;
	
	private Boolean _existingFile;
	
	private ArrayList<String> _workspaceProjects;
	
	public WorkspaceFile(String path){
		_workspacePath = path;
		_workspaceProjects = new ArrayList<String>();
		createOrReadFile();
	}
	
	public void addProjectToWorkspace(String newProj){
		_workspaceProjects.add(newProj);
	}
	
	public ArrayList<String> getProjects()
	{
		return _workspaceProjects;
	}
	
	public Boolean isPrevWorkspaceFile()
	{
		return _existingFile;
	}


	public void saveWorkspaceFile() {
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			/*create the new file*/
			Document newDoc = builder.newDocument();
			Element rootElement = newDoc.createElement(ROOT_TAG_NAME);
			for ( String proj:_workspaceProjects ){
				Element el = newDoc.createElement(PROJ_TAG_NAME);
				Text elValue = newDoc.createTextNode(proj);
				el.appendChild(elValue);
				rootElement.appendChild(el);
			}
			newDoc.appendChild(rootElement);
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(new DOMSource(newDoc), new StreamResult(new FileOutputStream(new File(_workspacePath+"\\"+FILE_NAME))));
			
		}catch(Exception ex){
			//TODO Error
		}
	}
	/*PRIVATE*/
	private void createOrReadFile() {
		File fl = new File(_workspacePath+"\\"+FILE_NAME);
		/*check to see if a workspace file exists*/
		if ( fl.exists() ){
			readProjects(fl);
			_existingFile = true;
		}else{
			_existingFile=false;
//			createWorkspaceFile();
		}
	}

	private void readProjects(File fl) {
		DocumentBuilder builder;
		try {
			
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document porjDoc = builder.parse(fl);
			Element root = porjDoc.getDocumentElement();
			if ( root.getNodeName().equals(ROOT_TAG_NAME) )
			{
				NodeList set = root.getChildNodes();
				for( int i=0; i<set.getLength(); i++ ){
					String nodeName = set.item(i).getNodeName();
					if ( !nodeName.equals("#text") )
					{
						/*if the node is not a text node*/
						Node currentNode = set.item(i);
						if ( currentNode.getNodeName().equals(PROJ_TAG_NAME) )
						{
							_workspaceProjects.add(getStringValue(currentNode));
						}
					}
				}
			}else{
				//TODO error
			}
		}catch(Exception ex){
			ex.printStackTrace();
			//TODO Error
		}
	}

	
	
	/**
	 * Name: getStringValue
	 * Args: @param node
	 * Args: @return
	 * Return: String
	 * Desc: returns the value of xml node in string format
	 */
	private String getStringValue(Node node)
	{
		String value = "";
		NodeList children = node.getChildNodes();
		for( int i=0; i<children.getLength(); i++ ){
			value = (children.item(i).getNodeValue());
//			value = val.getData();
		}
		
		return value;
	}
}
