package com.syntese.project.graphics.graph.graphromiu;

import javax.swing.JPanel;

public class GraphicRoMiuPanelMediator {

	private GraphicRoMiuPanel _thePanel;
	private GraphicRoMiuSinglePanelMediator _singlePanUp;
	private GraphicRoMiuSinglePanelMediator _singlePanDown;
	
	
	/**
	 * @Name: GraphicRoMiuPanelMediator
	 * @Args: 
	 */
	public GraphicRoMiuPanelMediator(){
		_singlePanUp = new GraphicRoMiuSinglePanelMediator();
		_singlePanDown = new GraphicRoMiuSinglePanelMediator();
		_thePanel = new GraphicRoMiuPanel(_singlePanUp.getPanel(), _singlePanDown.getPanel());
	}


	/**
	 * @Name: getPanel
	 * @Args: @return
	 * @Return: JPanel
	 * @Desc: method for returning the panel for drawing
	 */
	public JPanel getPanel() {
		return _thePanel;
	}
	
}
