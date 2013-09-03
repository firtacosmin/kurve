package com.syntese.project.graphics.graph.graphs;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.syntese.graphics.GBC;
import com.syntese.language.LanguageFactory;

public class GraphicSSinglePanel extends JPanel {
	
	private static String TEXT_RASTER = "Graphic_raster_btn_title";
	private static String TEXT_SAVE = "SaveAsMenu";
	
	private JCheckBox _chk_s;
	private JCheckBox _chk_sprime;
	private JCheckBox _chk_ssecond;
	private JCheckBox _chk_raster;
	private JButton _btn_saveAs;
	private JPanel _plotPanel;
	
	private ArrayList<GraphicSSinglePanelActionListner> _theActionListners;
	

	/*
	 * PUBLIC
	 * */
	public GraphicSSinglePanel(JPanel plotPanel){
		super();
		_plotPanel = plotPanel;
		_theActionListners = new ArrayList<GraphicSSinglePanelActionListner>();
		InitComponents();
		AddComponents();
	}	
	
	/**
	 * Name: addActionListner
	 * Args: @param listner
	 * Return: void
	 * Desc: Method to register action listeners to the events
	 */
	public void addActionListner(GraphicSSinglePanelActionListner listner){
		_theActionListners.add(listner);
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

		_chk_raster = new JCheckBox(LanguageFactory.getInstance().getExpresion(TEXT_RASTER));
		_chk_raster.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				announceRasterChange(_chk_raster.isSelected());
			}
		});
		_chk_s = new JCheckBox("s/psi");
		_chk_s.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				announcesChange(_chk_s.isSelected());
			}
		});
		_chk_sprime = new JCheckBox("s'/psi");
		_chk_sprime.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				announcesPrimeChange(_chk_sprime.isSelected());
			}
		});
		_chk_ssecond = new JCheckBox("s''/psi");
		_chk_ssecond.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				announcesSecondChange(_chk_ssecond.isSelected());

			}
		});
		
		_btn_saveAs = new JButton(LanguageFactory.getInstance().getExpresion(TEXT_SAVE));
		_btn_saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				announceSaveAdBtnPressed();
			}
		});
		
	}
	
	/**
	 * @Name: AddComponents
	 * @Args: 
	 * @Return: void
	 * @Desc: method for adding the components to the panel 
	 */
	private void AddComponents() {
		setLayout(new GridBagLayout());
		
		/*add the button*/
		add(_btn_saveAs, new GBC(0,0));
		/*add the plot panel*/
		add(_plotPanel, new GBC(1,0).setFill(GBC.BOTH).setWeight(100, 100));
		/*add the checkboxes*/
		JPanel chkBoxPan = new JPanel();
		chkBoxPan.setLayout(new GridBagLayout());
		chkBoxPan.add(_chk_s,       new GBC(0,0));
		chkBoxPan.add(_chk_sprime,  new GBC(0,1));
		chkBoxPan.add(_chk_ssecond, new GBC(0,2));
		chkBoxPan.add(_chk_raster,  new GBC(0,3));
		add(chkBoxPan, new GBC(2,0).setFill(GBC.VERTICAL));
		
	}
	
	
	/**
	 * @Name: announceRasterChange
	 * @Args: @param active - if after the change the checkbox is active or not
	 * @Return: void
	 * @Desc: announces a change in the raster checkbox's state
	 */
	private void announceRasterChange(Boolean active){
		for (int i=0 ; i<_theActionListners.size() ; i++){
			_theActionListners.get(i).RasterStateChange(active);
		}
	}
	/**
	 * @Name: announcesChange
	 * @Args: @param active - if after the change the checkbox is active or not
	 * @Return: void
	 * @Desc: announces a change in the s checkbox's state
	 */
	private void announcesChange(Boolean active){
		for (int i=0 ; i<_theActionListners.size() ; i++){
			_theActionListners.get(i).sStateChange(active);
		}
	}
	/**
	 * @Name: announcesPrimeChange
	 * @Args: @param active - if after the change the checkbox is active or not
	 * @Return: void
	 * @Desc: announces a change in the sPrime checkbox's state
	 */
	private void announcesPrimeChange(Boolean active){
		for (int i=0 ; i<_theActionListners.size() ; i++){
			_theActionListners.get(i).sPrimeStateChange(active);
		}
	}
	
	/**
	 * @Name: announcesSecondChange
	 * @Args: @param active - if after the change the checkbox is active or not
	 * @Return: void
	 * @Desc: announces a change in the sSecond checkbox's state
	 */
	private void announcesSecondChange(Boolean active){
		for (int i=0 ; i<_theActionListners.size() ; i++){
			_theActionListners.get(i).sSecondStateChange(active);
		}
	}
	
	/**
	 * @Name: announceSaveAdBtnPressed
	 * @Args: 
	 * @Return: void
	 * @Desc: method for announcing the pressing of the save as btn
	 */
	private void announceSaveAdBtnPressed(){
		for (int i=0 ; i<_theActionListners.size() ; i++){
			_theActionListners.get(i).saveAsBtn_pressed();
		}
	}
}
