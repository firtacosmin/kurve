package com.syntese.project.graphics.graph.graphs;

import javax.swing.JPanel;

import com.syntese.graphics.plot.PlotMediator;

public class GraphicSSinglePanelMediator implements GraphicSSinglePanelActionListner{

	private GraphicSSinglePanel _thePanel;
	private PlotMediator _plot;
	
	
	
	/*
	 * 
	 * PUBLIC
	 * 
	 * */
	
	
	public GraphicSSinglePanelMediator(){
		_plot = new PlotMediator();
		_thePanel = new GraphicSSinglePanel(_plot.getPlotPanel());
		_thePanel.addActionListner(this);
	}
	
	/**
	 * Name: getSSinglePanel
	 * Args: @return
	 * Return: JPanel
	 * Desc: getter for the ssingle panel to which the class is a mediator
	 */
	public JPanel getSSinglePanel()
	{
		return _thePanel;
	}
	
	
	/*
	 * Overwritten from GraphicSSinglePanelActionListner
	 * */
	public void RasterStateChange(Boolean active){
		
	}
	public void sStateChange(Boolean active){
		
	}
	public void sPrimeStateChange(Boolean active){
		
	}
	public void sSecondStateChange(Boolean active){
		
	}

	public void saveAsBtn_pressed(){
		
	}
	
	/*
	 * 
	 * PRIVATE
	 * 
	 * */
	
}
