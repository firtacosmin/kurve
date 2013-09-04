package com.syntese.project.graphics.graph.graphromiu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.syntese.language.LanguageFactory;

public class GraphicRoMiuSinglePanel extends JPanel {

	private static String TEXT_RASTER = "Graphic_raster_btn_title";
	private static String TEXT_SAVE = "SaveAsMenu";
	
	private ArrayList<GraphicRoMiuSinglePanelActionListner> _actionListners;
	
	private JCheckBox _chkRaster;
	private JButton _btnSaveAs;
	private JPanel _plot;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * PUBLIC
	 * */

	/**
	 * Name: GraphicRoMiuSinglePanel
	 * Args: 
	 */
	public GraphicRoMiuSinglePanel(JPanel plot){
		super();
		_actionListners = new ArrayList<GraphicRoMiuSinglePanelActionListner>();
		_plot = plot;
		InitComponents();
		AddComponents();
	}



	/**
	 * Name: addActionListner
	 * Args: @param listner
	 * Return: void
	 * Desc: method for ading a new action listner
	 */
	public void addActionListner( GraphicRoMiuSinglePanelActionListner listner) {
		_actionListners.add(listner);		
	}
	
	/*
	 * PRIVATE
	 * */
	
	
	/**
	 * Name: InitComponents
	 * Args: 
	 * Return: void
	 * Desc: method for initializing the components 
	 */
	private void InitComponents() {
		_btnSaveAs = new JButton(LanguageFactory.getInstance().getExpresion(TEXT_SAVE));
		_btnSaveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				anounceSaveAsBtnClick();
			}
		});
		
		_chkRaster = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_RASTER));
		_chkRaster.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				anounceRasterStateChange(_chkRaster.isEnabled());
			}
		});
	}

	/**
	 * Name: AddComponents
	 * Args: 
	 * Return: void
	 * Desc: method for adding the components to the layout 
	 */
	private void AddComponents() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Name: anounceRasterStateChange
	 * Args: @param state
	 * Return: void
	 * Desc: method that announces the change of the raster 
	 * 		 chk btn state change to all the action listeners registered.
	 */
	private void anounceRasterStateChange(Boolean state){
		for (int i=0; i<_actionListners.size(); i++){
			_actionListners.get(i).rasterStateShanged(state);
		}
	}
	
	/**
	 * Name: anounceSaveAsBtnClick
	 * Args: 
	 * Return: void
	 * Desc: method that announces the click event of 
	 *       the save as btn to all the action listeners registered.
	 */
	private void anounceSaveAsBtnClick(){
		for (int i=0; i<_actionListners.size(); i++){
			_actionListners.get(i).saveAsBtnClicked();
		}
	}
	
}
