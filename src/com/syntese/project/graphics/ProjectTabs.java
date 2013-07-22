package com.syntese.project.graphics;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.syntese.language.LanguageFactory;
import com.syntese.project.graphics.animation.AnimationPanel;
import com.syntese.project.graphics.curveProfile.CurveProfilePanel;
import com.syntese.project.graphics.graph.GraphicRoMiuPanel;
import com.syntese.project.graphics.graph.GraphicSPanel;
import com.syntese.project.graphics.numericdata.NumericDataPanel;

/**
 * @author cfirta
 * @description this panel contains all the tabed data about a project.
 */
public class ProjectTabs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Texts*/
	public static String INPUTPARAMS_TAB_TITLE_TEXT = "NumericData_ProjectPanel_title";
	public static String GRAPHIC_S_TAB_TITLE_TEXT = "Graphic_S_ProjectPanel_title";
	public static String CURVEPROFILE_TAB_TITLE_TEXT = "CurveProfile_ProjectPanel_title";
	public static String ANIMATION_TAB_TITLE_TEXT = "Graphic_romiu_ProjectPanel_title";
	public static String GRAPHIC_ROMIU_TAB_TITLE_TEXT = "Animation_ProjectPanel_title";
	
	/*PRIVATE PROPERTIES*/
	private NumericDataPanel _imputParamsPan;
	private GraphicRoMiuPanel _graphicRoMiuPan;
	private GraphicSPanel _graphicSPan;
	private CurveProfilePanel _curveProfilePan;
	private AnimationPanel _animationPan;
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
		
		_theTabbedPane.add(LanguageFactory.getInstance().getExpresion(INPUTPARAMS_TAB_TITLE_TEXT),_imputParamsPan);
		_theTabbedPane.add(LanguageFactory.getInstance().getExpresion(GRAPHIC_S_TAB_TITLE_TEXT),_graphicSPan);
		_theTabbedPane.add(LanguageFactory.getInstance().getExpresion(CURVEPROFILE_TAB_TITLE_TEXT),_curveProfilePan);
		_theTabbedPane.add(LanguageFactory.getInstance().getExpresion(GRAPHIC_ROMIU_TAB_TITLE_TEXT),_graphicRoMiuPan);
		_theTabbedPane.add(LanguageFactory.getInstance().getExpresion(ANIMATION_TAB_TITLE_TEXT),_animationPan);
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
		_imputParamsPan = new NumericDataPanel();
		_graphicRoMiuPan = new GraphicRoMiuPanel();
		_graphicSPan = new GraphicSPanel();
		_animationPan = new AnimationPanel();
		_curveProfilePan = new CurveProfilePanel();
		
	}
	
}
