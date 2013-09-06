package com.syntese.graphics.plot;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author Cosmin
 * @desc class for displaying the JPannel where the plot will be drown
 */
public class PlotPanel extends JPanel {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 2025922411859487889L;

	/*
	 * 
	 * PUBLIC
	 * 
	 * */
	/**
	 * Name: PlotPanel
	 * Args: 
	 */
	public PlotPanel(){
		super();
		InitComponents();
		AddComponents();
	}
	
	
	/*
	 * 
	 * PRIVATE
	 * 
	 * */

	/**
	 * Name: AddComponents
	 * Args: 
	 * Return: void
	 * Desc: method for adding the components to the panel 
	 */
	private void AddComponents() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Name: InitComponents
	 * Args: 
	 * Return: void
	 * Desc: method to initialize the components 
	 */
	private void InitComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.darkGray);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
}
