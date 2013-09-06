package com.syntese.project.graphics.graph.graphromiu;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.syntese.graphics.GBC;

/**
 * @author cfirta
 * @description panel for displaying the ro-miu graphic of the curve for the main frame.
 */
public class GraphicRoMiuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel _upPanel;
	private JPanel _downPanel;

	public GraphicRoMiuPanel(JPanel upPan, JPanel downPan){
		super();
		_upPanel = upPan;
		_downPanel = downPan;
		InitComponents();
		AddComponents();
	}
	
	/*
	 * PRIVATE
	 * */

	/**
	 * Name: InitComponents
	 * Args: 
	 * Return: void
	 * Desc: method for initializing the UI of the panel
	 */
	private void InitComponents() {
		/**/
	}
	/**
	 * Name: AddComponents
	 * Args: 
	 * Return: void
	 * Desc: method for adding the ui components to the layout
	 */
	private void AddComponents() {
		setLayout(new GridBagLayout());
		add(_upPanel,   new GBC(0,0).setFill(GBC.BOTH).setWeight(100, 100));
		add(_downPanel, new GBC(0,1).setFill(GBC.BOTH).setWeight(100, 100));		
	}

}
