package com.syntese.graphics.wizard.pages.lastPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.syntese.graphics.GBC;
import com.syntese.graphics.wizard.pages.CamProfilePage;
import com.syntese.graphics.wizard.pages.CamTypePage;
import com.syntese.graphics.wizard.pages.DownstreamPage;
import com.syntese.language.LanguageFactory;

public class GeometryDataTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************
	 * PROPERTIES
	 * *************/
	
	/*texts*/
	private static final String LENGTH_TITLE_NAME = "Wizard_GeomData_LengthTitle";
	private static final String ANGLE_TITLE_NAME = "Wizard_GeomData_AngleTitle";
	private static final String WAVE_RADIUS_LABEL_NAME = "Wizard_GeomData_WaveRad_Label";
	private static final String SEIBEN_RADIUS_LABEL_NAME = "Wizard_GeomData_SeibenRad_Label";
	private static final String DRAWING_BTN_NAME = "Wizard_GeomData_DrawingBtn";
	
	
	/*GUI Components*/
	private JTextField _X_A0;
	private JTextField _Y_A0;
	private JTextField _X_d;
	private JTextField _Y_d;
	private JTextField _X_s;
	private JTextField _Y_s;
	private JTextField _ro_min;
	private JTextField _L3;
	private JTextField _L31;
	private JTextField _L3p;
	private JTextField _L4;
	private JTextField _L5;
	private JTextField _L1;
	private JTextField _rR;
	private JTextField _rG;
	private JTextField _ep;
	private JTextField _y;
	private JTextField _delta;
	private JTextField _gama;
	private JTextField _miu_an_min;
	private JTextField _miu_ab_min;
	private JTextField _n;
	private JTextField _siebenRad;
	private JTextField _waveRad;
	private String _helpingImgPath;
	
	private JLabel _X_A0_lb;
	private JLabel _Y_A0_lb;
	private JLabel _X_d_lb;
	private JLabel _Y_d_lb;
	private JLabel _X_s_lb;
	private JLabel _Y_s_lb;
	private JLabel _ro_min_lb;
	private JLabel _L3_lb;
	private JLabel _L31_lb;
	private JLabel _L3p_lb;
	private JLabel _L4_lb;
	private JLabel _L5_lb;
	private JLabel _L1_lb;
	private JLabel _rR_lb;
	private JLabel _rG_lb;
	private JLabel _ep_lb;
	private JLabel _y_lb;
	private JLabel _delta_lb;
	private JLabel _gama_lb;
	private JLabel _miu_an_min_lb;
	private JLabel _miu_ab_min_lb;
	private JLabel _n_lb;
	
	private JButton _drawing_btn;
	
	
	/*prevously selected oprions*/
	private Integer _camType;
	private Integer _camProfile;
	private Integer _downStream;
	
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	
	public GeometryDataTab(int camType, int camProfile, int downStream){
		super();
		
		_camType = camType;
		_camProfile = camProfile;
		_downStream = downStream;
		
		initializeGUIComponents();
		addGUIComponents();
	}

	/*
	 * PRIVATE
	 * */
	
	
	private void initializeGUIComponents() {
		
		_X_A0 = new JTextField();
		_X_A0_lb = new JLabel("X_A0 (mm)");
		_Y_A0 = new JTextField();
		_Y_A0_lb = new JLabel("Y_A0 (mm)");
		_X_d = new JTextField();
		_X_d_lb = new JLabel("X_d (mm)");
		_Y_d = new JTextField();
		_Y_d_lb = new JLabel("Y_d (mm)");
		_X_s = new JTextField();
		_X_s_lb = new JLabel("X_s (mm)");
		_Y_s = new JTextField();
		_Y_s_lb = new JLabel("Y_s (mm)");
		_ro_min = new JTextField();
		_ro_min_lb = new JLabel("ro_min (mm)");
		_L3 = new JTextField();
		_L3_lb = new JLabel("L3 (mm)");
		_L31 = new JTextField();
		_L31_lb = new JLabel("L31 (mm)");
		_L3p = new JTextField();
		_L3p_lb = new JLabel("L3p (mm)");
		_L4 = new JTextField();
		_L4_lb = new JLabel("L4 (mm)");
		_L5 = new JTextField();
		_L5_lb = new JLabel("L5 (mm)");
		_L1 = new JTextField();
		_L1_lb = new JLabel("L1 (mm)");
		_rR = new JTextField();
		_rR_lb = new JLabel("rR (mm)");
		_rG = new JTextField();
		_rG_lb = new JLabel("rG (mm)");
		_ep = new JTextField();
		_ep_lb = new JLabel("ep (mm)");
		_y = new JTextField();
		_y_lb = new JLabel("y (mm)");
		_delta = new JTextField();
		_delta_lb = new JLabel("delta (grad)");
		_gama = new JTextField();
		_gama_lb = new JLabel("gama (grad)");
		_miu_an_min = new JTextField();
		_miu_an_min_lb = new JLabel("miu_an_min (grad)");
		_miu_ab_min = new JTextField();
		_miu_ab_min_lb = new JLabel("miu_ab_min (grad)");
		_n = new JTextField();
		_n_lb = new JLabel("n (grad)");
		_siebenRad = new JTextField();
		_waveRad = new JTextField();

		_drawing_btn = new JButton(LanguageFactory.getInstance().getExpresion(DRAWING_BTN_NAME));
		_drawing_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new ImageDialog(_helpingImgPath).setVisible(true);
				} catch (IOException e) {
					// TODO error to be delth with
					e.printStackTrace();
				}
				
			}
		});
		
		/*determine the used fields based on the previous selections*/
		selectFieldsToDisplay();
		getSampleImage();
		
	}

	
	/**
	 * Name: getSampleImage
	 * Args: 
	 * Return: void
	 * Desc: depending on the previous selections in the Wizzard this method will decide what image 
	 *       will be displayed on the sample btn
	 */
	private void getSampleImage() {
		_helpingImgPath = "Media\\"+_camType.toString()+_downStream.toString()+_camProfile.toString()+".bmp";
//		switch( _downStream ){
//		case DownstreamPage.LEVER_NO_DOWNSTREAM:
//			switch ( _camProfile )
//			{
//			case CamProfilePage.LEVER_BEAD_CAM:
//				break;
//			case CamProfilePage.LEVER_DOUBLE_CAM:
//				break;
//			case CamProfilePage.LEVER_GROOVE_CAM:
//				break;
//			case CamProfilePage.LEVER_OUTER_CAM:
//				break;	
//			}
//			break;
//		case DownstreamPage.LEVER_CRANK:
//			break;
//		case DownstreamPage.LEVER_FOUR_JOIN:
//			break;
//		case DownstreamPage.LEVER_PUSHER_TUGS:
//			break;
//		case DownstreamPage.CAMS_CRANK:
//			switch ( _camProfile )
//			{
//			case CamProfilePage.SLIDER_BEAD_CAM:
//				break;
//			case CamProfilePage.SLIDER_DOUBLE_CAM:
//				break;
//			case CamProfilePage.SLIDER_GROOVE_CAM:
//				break;
//			case CamProfilePage.SLIDER_OUTER_CAM:
//				break;
//			}
//			break;
//		case DownstreamPage.CAMS_DOUBLE_SLIDE:
//			break;
//		case DownstreamPage.CAMS_NO_DOWNSTREAM:
//			break;
//		}
		
	}

	/**
	 * Name: selectFieldsToDisplay
	 * Args: 
	 * Return: void
	 * Desc: depending on previous selections this method will disable the unnecessary fields.
	 */
	private void selectFieldsToDisplay()
	{
		_X_A0.setEnabled(true);
		_Y_A0.setEnabled(true);
		if ( _downStream == DownstreamPage.CAMS_NO_DOWNSTREAM || 
		     _downStream == DownstreamPage.LEVER_NO_DOWNSTREAM ||  
		     _downStream == DownstreamPage.LEVER_PUSHER_TUGS)
		{
			_X_d.setEnabled(false);
			_Y_d.setEnabled(false);
		}
		if ( _camType == CamTypePage.ROLLER_SLIDE || 
			 _downStream != DownstreamPage.LEVER_FOUR_JOIN)
		{
			_X_s.setEnabled(false);
			_Y_s.setEnabled(false);
		}
		_ro_min.setEnabled(true);
		_L3.setEnabled(true);
		if ( _camProfile != CamProfilePage.LEVER_DOUBLE_CAM &&
			 _camProfile != CamProfilePage.LEVER_BEAD_CAM &&
			 _camProfile != CamProfilePage.SLIDER_DOUBLE_CAM &&
			 _camProfile != CamProfilePage.SLIDER_BEAD_CAM)
		{
			_L31.setEnabled(false);
		}
		if ( _camType == CamTypePage.ROLLER_SLIDE ||
			 _camType == CamTypePage.ROLLER_LEVER && _downStream == DownstreamPage.LEVER_NO_DOWNSTREAM)
		{
			_L3p.setEnabled(false);
		}
		if ( _downStream == DownstreamPage.CAMS_NO_DOWNSTREAM || 
			 _downStream == DownstreamPage.LEVER_NO_DOWNSTREAM || 
			 _downStream == DownstreamPage.LEVER_PUSHER_TUGS)
		{
			_L4.setEnabled(false);
		}
		/*On L5 easier to use opposite logic.*/
		_L5.setEnabled(false);
		if ( _camType == CamTypePage.ROLLER_SLIDE && _downStream == DownstreamPage.CAMS_CRANK 
				||
			 _camType == CamTypePage.ROLLER_LEVER && _downStream == DownstreamPage.LEVER_FOUR_JOIN)
		{
			_L5.setEnabled(true);
		}
		_L1.setEnabled(false);
		_rR.setEnabled(true);
		_rG.setEnabled(true);
		/*On ep easier to use opposite logic.*/
		_ep.setEnabled(false);
		if ( _camProfile == CamProfilePage.SLIDER_DOUBLE_CAM )
		{
			_ep.setEnabled(true);
		}
		/*On y easier to use opposite logic.*/
		_y.setEnabled(false);
		if ( _camType == CamTypePage.ROLLER_SLIDE || _downStream == DownstreamPage.LEVER_PUSHER_TUGS)
		{
			_y.setEnabled(true);
		}
		if ( _downStream == DownstreamPage.LEVER_NO_DOWNSTREAM ||
			 _downStream == DownstreamPage.CAMS_CRANK)
		{
			_delta.setEnabled(false);
		}
		if (  _camProfile != CamProfilePage.LEVER_DOUBLE_CAM &&
			  _camProfile != CamProfilePage.LEVER_BEAD_CAM  )	
		{
			_gama.setEnabled(false);
		}
		_miu_an_min.setEnabled(true);
		_miu_ab_min.setEnabled(true);
		_n.setEnabled(true);
	}

	/**
	 * Name: addGUIComponents
	 * Args: 
	 * Return: void
	 * Desc: The method that adds the initialized components to the layout
	 */
	private void addGUIComponents() {
		
		/*length panel*/
		JPanel lengthPanel = new JPanel();
		lengthPanel.setBackground(new Color(109,109,109));
		GridLayout layout = new GridLayout(1,2);
		layout.setHgap(20);
		lengthPanel.setLayout(layout);

		JPanel lengthLeftPan = new JPanel();
		lengthLeftPan.setBackground(new Color(109,109,109));
		lengthLeftPan.setLayout(new GridLayout(9,2));
		lengthLeftPan.add(_X_A0_lb);
		lengthLeftPan.add(_X_A0);
		lengthLeftPan.add(_Y_A0_lb);
		lengthLeftPan.add(_Y_A0);
		lengthLeftPan.add(_X_d_lb);
		lengthLeftPan.add(_X_d);
		lengthLeftPan.add(_Y_d_lb);
		lengthLeftPan.add(_Y_d);
		lengthLeftPan.add(_X_s_lb);
		lengthLeftPan.add(_X_s);
		lengthLeftPan.add(_Y_s_lb);
		lengthLeftPan.add(_Y_s);
		lengthLeftPan.add(_ro_min_lb);
		lengthLeftPan.add(_ro_min);
		lengthLeftPan.add(new JLabel(""));
		lengthLeftPan.add(new JLabel(""));
		lengthLeftPan.add(new JLabel(""));
		lengthLeftPan.add(new JLabel(""));

		JPanel lengthRightPan = new JPanel(new GridLayout(10,2));
		lengthRightPan.setBackground(new Color(109,109,109));
		lengthRightPan.add(_L3_lb);
		lengthRightPan.add(_L3);
		lengthRightPan.add(_L31_lb);
		lengthRightPan.add(_L31);
		lengthRightPan.add(_L3p_lb);
		lengthRightPan.add(_L3p);
		lengthRightPan.add(_L4_lb);
		lengthRightPan.add(_L4);
		lengthRightPan.add(_L5_lb);
		lengthRightPan.add(_L5);
		lengthRightPan.add(_L1_lb);
		lengthRightPan.add(_L1);
		lengthRightPan.add(_rR_lb);
		lengthRightPan.add(_rR);
		lengthRightPan.add(_rG_lb);
		lengthRightPan.add(_rG);
		lengthRightPan.add(_ep_lb);
		lengthRightPan.add(_ep);
		lengthRightPan.add(_y_lb);
		lengthRightPan.add(_y);
		
		lengthPanel.add(lengthLeftPan);
		lengthPanel.add(lengthRightPan);
		
		/*angle panel*/
		JPanel anglePanel = new JPanel(new GridLayout(5,2));
		anglePanel.setBackground(new Color(109,109,109));
		anglePanel.add(_delta_lb);
		anglePanel.add(_delta);
		anglePanel.add(_gama_lb);
		anglePanel.add(_gama);
		anglePanel.add(_miu_an_min_lb);
		anglePanel.add(_miu_an_min);
		anglePanel.add(_miu_ab_min_lb);
		anglePanel.add(_miu_ab_min);
		anglePanel.add(_n_lb);
		anglePanel.add(_n);
		
		
		/*Title label for the length panel*/
		JLabel lengthPanelTitle = new JLabel(LanguageFactory.getInstance().getExpresion(LENGTH_TITLE_NAME));
		/*Title label for the angle panel*/
		JLabel anglePanelTitle = new JLabel(LanguageFactory.getInstance().getExpresion(ANGLE_TITLE_NAME));
		
		/*Wave radius textbox label*/
		JLabel waveRadius = new JLabel(LanguageFactory.getInstance().getExpresion(WAVE_RADIUS_LABEL_NAME));
		/*Seiben (???) radius textbox label*/
		JLabel seibenRadius = new JLabel(LanguageFactory.getInstance().getExpresion(SEIBEN_RADIUS_LABEL_NAME));
		
		
		JPanel radiusPan = new JPanel(new GridLayout(2,2));
		radiusPan.setBackground(new Color(109,109,109));
		radiusPan.add(waveRadius);
		radiusPan.add(_waveRad);
		radiusPan.add(seibenRadius);
		radiusPan.add(_siebenRad);
		
		
		JPanel leftPan = new JPanel(new GridBagLayout());
		leftPan.setBorder(BorderFactory.createRaisedBevelBorder());
		leftPan.setBackground(new Color(109,109,109));
		leftPan.add(lengthPanelTitle, new GBC(0,0).setFill(GBC.NONE).setAnchor(GBC.NORTH).setInsets(0,10,50,10));
		leftPan.add(lengthPanel, new GBC(0,1).setFill(GBC.NONE).setAnchor(GBC.NORTH).setInsets(0, 10, 0, 10));
		leftPan.add(radiusPan, new GBC(0,2).setFill(GBC.NONE).setAnchor(GBC.NORTHWEST).setInsets(20, 10, 0, 10));
		
		JPanel rightPan = new JPanel(new GridBagLayout());
		rightPan.setBorder(BorderFactory.createRaisedBevelBorder());
		rightPan.setBackground(new Color(109,109,109));
		rightPan.add(anglePanelTitle, new GBC(1,0).setFill(GBC.NONE).setAnchor(GBC.NORTH).setInsets(0,10,50,10));
		rightPan.add(anglePanel, new GBC(1,1).setFill(GBC.NONE).setAnchor(GBC.NORTH).setInsets(0, 10, 0, 10));
		
		/*adding the two panels to the layout*/
		JPanel mainPan = new JPanel(new GridBagLayout());
		mainPan.add(leftPan, new GBC(0, 0).setFill(GBC.VERTICAL).setAnchor(GBC.NORTH).setInsets(0, 0, 0, 20).setWeight(100, 100));
		mainPan.add(rightPan, new GBC(1, 0).setFill(GBC.VERTICAL).setAnchor(GBC.NORTH).setInsets(0, 20, 0, 0).setWeight(100, 100));
		mainPan.add(_drawing_btn, new GBC(2, 0, 1, 2).setFill(GBC.NONE).setAnchor(GBC.NORTH));
		
		setLayout(new BorderLayout());
		add(mainPan, BorderLayout.CENTER);
		setPreferredSize(new Dimension(800, 400));
		setSize(getPreferredSize());

	}
	
	
}
