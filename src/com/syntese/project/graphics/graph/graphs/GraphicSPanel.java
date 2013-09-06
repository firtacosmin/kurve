package com.syntese.project.graphics.graph.graphs;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.syntese.graphics.GBC;

/**
 * @author cfirta
 * @description panel that displays the graphical illustration of the curve.
 */
public class GraphicSPanel extends JPanel {

	
	private JPanel _upPanel;
	private JPanel _downPanel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GraphicSPanel(JPanel upGraphicPanel, JPanel downGraphicPanel){
		super();
		_upPanel = upGraphicPanel;
		_downPanel = downGraphicPanel;
		InitComponents();
		AddComponents();
	}
	
	/*
	 * PRIVATE
	 * */



	/**
	 * @Name: InitComponents
	 * @Args: 
	 * @Return: void
	 * @Desc: method to initialize the components of the panel 
	 */
	private void InitComponents() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @Name: AddComponents
	 * @Args: 
	 * @Return: void
	 * @Desc: method for adding the components to the panel 
	 */
	private void AddComponents() {
		setLayout(new GridBagLayout());
		add(_upPanel,   new GBC(0,0).setFill(GBC.BOTH).setWeight(100, 100));
		add(_downPanel, new GBC(0,1).setFill(GBC.BOTH).setWeight(100, 100));
	}
}
