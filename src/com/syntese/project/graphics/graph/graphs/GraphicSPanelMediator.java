package com.syntese.project.graphics.graph.graphs;

import javax.swing.JPanel;

public class GraphicSPanelMediator {

	private GraphicSPanel _thePanel;
	private GraphicSSinglePanelMediator _ssinglePanel_up;
	private GraphicSSinglePanelMediator _ssinglePanel_down;
	
	public GraphicSPanelMediator(){
		_ssinglePanel_up = new GraphicSSinglePanelMediator();
		_ssinglePanel_down = new GraphicSSinglePanelMediator();
		_thePanel = new GraphicSPanel(_ssinglePanel_up.getSSinglePanel(), 
				                      _ssinglePanel_down.getSSinglePanel());
	}
	
	/**
	 * @Name: getPanel
	 * @Args: @return
	 * @Return: JPanel
	 * @Desc: returns the panel to be printed 
	 */
	public JPanel getPanel()
	{
		return _thePanel;
	}
	
}
