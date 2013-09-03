package com.syntese.graphics.plot;

import javax.swing.JPanel;

/**
 * @author Cosmin
 * @desc class for mediating the plot Panel and functionality
 *
 */
public class PlotMediator {

	private PlotPanel _thePanel;
	
	/*
	 * 
	 * PUBLIC
	 * 
	 * */
	/**
	 * Name: PlotMediator
	 * Args: 
	 */
	public PlotMediator(){
		_thePanel = new PlotPanel();
	}
	
	
	/**
	 * Name: getPlotPanel
	 * Args: @return
	 * Return: JPanel
	 * Desc: method that returns the plor pannel for being displayed
	 */
	public JPanel getPlotPanel()
	{
		return _thePanel;
	}
	
	/*
	 * 
	 * PRIVATE
	 * 
	*/
}
