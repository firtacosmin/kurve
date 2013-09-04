package com.syntese.project.graphics.graph.graphromiu;

import javax.swing.JPanel;

public class GraphicRoMiuSinglePanelMediator implements GraphicRoMiuSinglePanelActionListner {

	private GraphicRoMiuSinglePanel _thePanel;
	
	/*
	 * Public
	 * */
	
	public GraphicRoMiuSinglePanelMediator(){
		_thePanel = new GraphicRoMiuSinglePanel(new JPanel());
		_thePanel.addActionListner(this);
	}
	
	/*
	 * Overwritten from GraphicRoMiuSinglePanelActionListner
	 * */
	@Override
	public void rasterStateShanged(Boolean state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAsBtnClicked() {
		// TODO Auto-generated method stub
		
	}
	
	
	/*
	 * Private
	 * */

}
