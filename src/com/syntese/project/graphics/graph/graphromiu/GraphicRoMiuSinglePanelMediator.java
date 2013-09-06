package com.syntese.project.graphics.graph.graphromiu;

import javax.swing.JPanel;

import com.syntese.graphics.plot.PlotMediator;

public class GraphicRoMiuSinglePanelMediator implements GraphicRoMiuSinglePanelActionListner {

	private GraphicRoMiuSinglePanel _thePanel;
	private PlotMediator _plot;
	
	/*
	 * Public
	 * */
	
	public GraphicRoMiuSinglePanelMediator(){
		_plot = new PlotMediator();
		_thePanel = new GraphicRoMiuSinglePanel(_plot.getPlotPanel());
		_thePanel.addActionListner(this);
	}
	
	/**
	 * Name: getPanel
	 * Args: @return
	 * Return: JPanel
	 * Desc: method for returning the single panel for drawing
	 */
	public JPanel getPanel()
	{
		return _thePanel;
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
