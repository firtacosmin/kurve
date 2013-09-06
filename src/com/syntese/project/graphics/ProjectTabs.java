package com.syntese.project.graphics;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.syntese.language.LanguageFactory;
import com.syntese.project.graphics.animation.AnimationPanel;
import com.syntese.project.graphics.curveProfile.CurveProfilePanel;
import com.syntese.project.graphics.graph.graphromiu.GraphicRoMiuPanelMediator;
import com.syntese.project.graphics.graph.graphs.GraphicSPanelMediator;
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
	public static String ANIMATION_TAB_TITLE_TEXT = "Animation_ProjectPanel_title";
	public static String GRAPHIC_ROMIU_TAB_TITLE_TEXT = "Graphic_romiu_ProjectPanel_title";
	
	/*PRIVATE PROPERTIES*/
	private NumericDataPanel _imputParamsPan;
//	private GraphicRoMiuPanel _graphicRoMiuPan;
	private JPanel _graphicRoMiuPan;
//	private GraphicSPanel _graphicSPan;
	private JPanel _graphicSPan;
	private CurveProfilePanel _curveProfilePan;
	private AnimationPanel _animationPan;
	private JTabbedPane _theTabbedPane;
	
	
	private GraphicRoMiuPanelMediator _romiuPanelMed;
	private GraphicSPanelMediator _sPanelMed;
	
	
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
	
	/**
	 * Name: updateTexts
	 * Args: 
	 * Return: void
	 * Desc: method to re-get all the tests from the language factory
	 */
	public void updateTexts(){
		_theTabbedPane.setTitleAt(0, LanguageFactory.getInstance().getExpresion(INPUTPARAMS_TAB_TITLE_TEXT));
		_theTabbedPane.setTitleAt(1, LanguageFactory.getInstance().getExpresion(GRAPHIC_S_TAB_TITLE_TEXT));
		_theTabbedPane.setTitleAt(2, LanguageFactory.getInstance().getExpresion(CURVEPROFILE_TAB_TITLE_TEXT));
		_theTabbedPane.setTitleAt(3, LanguageFactory.getInstance().getExpresion(GRAPHIC_ROMIU_TAB_TITLE_TEXT));
		_theTabbedPane.setTitleAt(4, LanguageFactory.getInstance().getExpresion(ANIMATION_TAB_TITLE_TEXT));
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
		_romiuPanelMed = new GraphicRoMiuPanelMediator();
		_sPanelMed = new GraphicSPanelMediator();
		
		_theTabbedPane = new JTabbedPane();
		_imputParamsPan = new NumericDataPanel();
		_graphicRoMiuPan = _romiuPanelMed.getPanel();
		_graphicSPan = _sPanelMed.getPanel();
		_curveProfilePan = new CurveProfilePanel();
		_animationPan = new AnimationPanel();
		
	}
	
}
