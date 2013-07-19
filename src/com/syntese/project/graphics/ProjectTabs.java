package com.syntese.project.graphics;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @author cfirta
 * @description this panel contains all the tabed data about a project.
 */
public class ProjectTabs extends JPanel {

	/*PRIVATE PROPERTIES*/
	private InputParamsPanel _imputParamsPan;
	private JTabbedPane _theTabbedPane;
	
	/*PUBLIC PROPERTIES*/
	
	/*
	 * PUBLIC METHODS
	 * */
	public ProjectTabs(){
		super();
		
		InitComponents();
		AddComponents();
	}

	/**
	 * @Name: setGeomParams
	 * @Args: @param params
	 * @Return: void
	 * @Desc: passes the Geom parameters to the imputParams panel.
	 */
	public void setGeomParams(HashMap<String, Float> params)
	{
		_imputParamsPan.setGeomParams(params);
	}

	/*
	 * 
	 * PRIVATE
	 * 
	 * */
	
	/**
	 * @Name: AddComponents
	 * @Args: 
	 * @Return: void
	 * @Desc:  adds the components tabs
	 */
	private void AddComponents() {
		//TODO get title of tab from language
		_theTabbedPane.add("Imput Params",_imputParamsPan);
		setLayout(new BorderLayout());
		add(_theTabbedPane, BorderLayout.CENTER);
	}

	/**
	 * @Name: InitComponents
	 * @Args: 
	 * @Return: void
	 * @Desc:  initializes all the tabs.
	 */
	private void InitComponents() {
		_theTabbedPane = new JTabbedPane();
		_imputParamsPan = new InputParamsPanel();
	}
	
}
