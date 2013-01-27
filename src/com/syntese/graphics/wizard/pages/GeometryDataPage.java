package com.syntese.graphics.wizard.pages;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeometryDataPage extends JPanel {

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
	private Image _helpingImg;
	
//	private ArrayList<JTextField> _lever_noDownstream_outerProfile;
//	private ArrayList<JTextField> _lever_noDownstream_double;
//	private ArrayList<JTextField> _lever_noDownstream_groove;
//	private ArrayList<JTextField> _lever_noDownstream_bead;
//	private ArrayList<JTextField> _lever_crank_outerProfile;
//	private ArrayList<JTextField> _lever_crank_double;
//	private ArrayList<JTextField> _lever_crank_groove;
//	private ArrayList<JTextField> _lever_crank_bead;
//	private ArrayList<JTextField> _lever_fourJoint_outerProfile;
//	private ArrayList<JTextField> _lever_fourJoint_double;
//	private ArrayList<JTextField> _lever_fourJoint_groove;
//	private ArrayList<JTextField> _lever_fourJoint_bead;
//	private ArrayList<JTextField> _lever_pusher_outerProfile;
//	private ArrayList<JTextField> _lever_pusher_double;
//	private ArrayList<JTextField> _lever_pusher_groove;
//	private ArrayList<JTextField> _lever_pusher_bead;
//
//	private ArrayList<JTextField> _slider_noDownstream_outerProfile;
//	private ArrayList<JTextField> _slider_noDownstream_double;
//	private ArrayList<JTextField> _slider_noDownstream_groove;
//	private ArrayList<JTextField> _slider_noDownstream_bead;
//	private ArrayList<JTextField> _slider_doubleSlide_outerProfile;
//	private ArrayList<JTextField> _slider_doubleSlide_double;
//	private ArrayList<JTextField> _slider_doubleSlide_groove;
//	private ArrayList<JTextField> _slider_doubleSlide_bead;
//	private ArrayList<JTextField> _slider_crank_outerProfile;
//	private ArrayList<JTextField> _slider_crank_double;
//	private ArrayList<JTextField> _slider_crank_groove;
//	private ArrayList<JTextField> _slider_crank_bead;
	
	private ArrayList<JTextField> _theLengthUserFields;
	private ArrayList<JTextField> _theAngleUserFields;
	
	/*prevously selected oprions*/
	private int _camType;
	private int _camProfile;
	private int _downStream;
	
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	
	public GeometryDataPage(int camType, int camProfile, int downStream){
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
		
		_X_A0 = new JTextField("X_A0 (mm)");
		_Y_A0 = new JTextField("Y_A0 (mm)");
		_X_d = new JTextField("X_d (mm)");
		_Y_d = new JTextField("Y_d (mm)");
		_X_s = new JTextField("X_s (mm)");
		_Y_s = new JTextField("Y_s (mm)");
		_ro_min = new JTextField("ro_min (mm)");
		_L3 = new JTextField("L3 (mm)");
		_L31 = new JTextField("L31 (mm)");
		_L3p = new JTextField("L3p (mm)");
		_L4 = new JTextField("L4 (mm)");
		_L5 = new JTextField("L5 (mm)");
		_L1 = new JTextField("L1 (mm)");
		_rR = new JTextField("rR (mm)");
		_rG = new JTextField("rG (mm)");
		_ep = new JTextField("ep (mm)");
		_y = new JTextField("y (mm)");
		_delta = new JTextField("delta (grad)");
		_gama = new JTextField("gama (grad)");
		_miu_an_min = new JTextField("miu_an_min (grad)");
		_miu_ab_min = new JTextField("miu_ab_min (grad)");
		_n = new JTextField("n (grad)");
		_siebenRad = new JTextField("siebenRad (mm)");
		_waveRad = new JTextField("waveRad (mm)");

		_theAngleUserFields = new ArrayList<JTextField>();
		_theLengthUserFields = new ArrayList<JTextField>();
		
		/*determine the used fields based on the previous selections*/
		selectFieldsToDisplay();
		
	}
	

	/**
	 * Name: selectFieldsToDisplay
	 * Args: 
	 * Return: void
	 * Desc: determine the used fields based on the previous selections
	 */
	private void selectFieldsToDisplay() {
		switch( _camType ){
		case CamTypePage.ROLLER_LEVER:
			break;
		case CamTypePage.ROLLER_SLIDE:
			printRollerSlide_Options();
			break;
		default:
			/*TODO: Error*/
		}
	}
	
	
	/**
	 * Name: printRollerLever_Options
	 * Args: 
	 * Return: void
	 * Desc: method that will print the Geometry data of a lever cam type,
	 * 		 depending on the downstream selection and 
	 * 		 the cam profile selection 
	 */
	private void printRollerLever_Options(){
		
		switch(_downStream){
		case DownstreamPage.LEVER_NO_DOWNSTREAM:
			printRollerLever_NoDownstream_options();
			break;
		case DownstreamPage.LEVER_CRANK:
			printRollerLever_Crank_options();
			break;
		case DownstreamPage.LEVER_FOUR_JOIN:
			printRollerLever_FourJoin_options();
			break;
		case DownstreamPage.LEVER_PUSHER_TUGS:
			printRollerLever_PusherTugs_options();
			break;
		default:
			_theLengthUserFields = null;
			/*TODO: Error*/
		}
	}
	
	/**
	 * Name: printRollerLever_NoDownstream_options
	 * Args: 
	 * Return: void
	 * Desc: printing options for Lever cam with no downstream
	 */
	private void printRollerLever_NoDownstream_options(){

		_theLengthUserFields.add(_X_A0);
		_theLengthUserFields.add(_Y_A0);
		_theLengthUserFields.add(_ro_min);
		_theLengthUserFields.add(_L3);
		_theLengthUserFields.add(_rR);
		_theLengthUserFields.add(_rG);
		
		_theAngleUserFields.add(_miu_ab_min);
		_theAngleUserFields.add(_miu_an_min);
		_theAngleUserFields.add(_n);
		switch( _camProfile ){
		case CamProfilePage.LEVER_OUTER_CAM :

			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\11osc. simpla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_DOUBLE_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			
			
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\12osc. cama dubla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_GROOVE_CAM :

			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\13osc. cama cu canal.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_BEAD_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\14osc. cama cu nervuri.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		default:
			/*TODO: Error*/
		}
	}
	
	/**
	 * Name: printRollerLever_Crank_options
	 * Args: 
	 * Return: void
	 * Desc: printing options for Lever cam with Crank in downstream
	 */
	private void printRollerLever_Crank_options(){

		_theLengthUserFields.add(_X_A0);
		_theLengthUserFields.add(_Y_A0);
		_theLengthUserFields.add(_X_d);
		_theLengthUserFields.add(_Y_d);
		_theLengthUserFields.add(_ro_min);
		_theLengthUserFields.add(_L3);
		_theLengthUserFields.add(_L3p);
		_theLengthUserFields.add(_L4);
		_theLengthUserFields.add(_rR);
		_theLengthUserFields.add(_rG);
		
		_theAngleUserFields.add(_delta);
		_theAngleUserFields.add(_miu_ab_min);
		_theAngleUserFields.add(_miu_an_min);
		_theAngleUserFields.add(_n);
		
		switch( _camProfile ){
		case CamProfilePage.LEVER_OUTER_CAM:
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\21osc. cu piston simpla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_DOUBLE_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\22osc. cu piston cama dubla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
			//
		case CamProfilePage.LEVER_GROOVE_CAM:
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\23osc. cu piston cama cu canal.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_BEAD_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\24osc. cu piston cama cu nervura.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		default:
			/*TODO: Error*/
		}
	}
	
	/**
	 * Name: printRollerLever_FourJoin_options
	 * Args: 
	 * Return: void
	 * Desc: printing options for Lever cam with FourJoin in downstream
	 */
	private void printRollerLever_FourJoin_options(){


		_theLengthUserFields.add(_X_A0);
		_theLengthUserFields.add(_Y_A0);
		_theLengthUserFields.add(_X_d);
		_theLengthUserFields.add(_Y_d);
		_theLengthUserFields.add(_X_s);
		_theLengthUserFields.add(_Y_s);
		_theLengthUserFields.add(_ro_min);
		_theLengthUserFields.add(_L3);
		_theLengthUserFields.add(_L3p);
		_theLengthUserFields.add(_L4);
		_theLengthUserFields.add(_L5);
		_theLengthUserFields.add(_rR);
		_theLengthUserFields.add(_rG);
		
		_theAngleUserFields.add(_delta);
		_theAngleUserFields.add(_miu_ab_min);
		_theAngleUserFields.add(_miu_an_min);
		_theAngleUserFields.add(_n);
		switch( _camProfile ){
		case CamProfilePage.LEVER_OUTER_CAM:
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\31osc. cu patrulater simpla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_DOUBLE_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\32osc. cu patrulater cama dubla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_GROOVE_CAM:
			_theLengthUserFields.add(_L31);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\33osc. cu patrulater cama cu canal.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_BEAD_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\34osc. cu patrulater cama cu nervura.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		default:
			/*TODO: Error*/
		}
	}
	
	/**
	 * Name: printRollerLever_PusherTugs_options
	 * Args: 
	 * Return: void
	 * Desc: printing options for Lever cam with PucherTugs in downstream
	 */
	private void printRollerLever_PusherTugs_options(){

		_theLengthUserFields.add(_X_A0);
		_theLengthUserFields.add(_Y_A0);
		_theLengthUserFields.add(_ro_min);
		_theLengthUserFields.add(_L3);
		_theLengthUserFields.add(_L3p);
		_theLengthUserFields.add(_rR);
		_theLengthUserFields.add(_rG);
		_theLengthUserFields.add(_y);
		
		_theAngleUserFields.add(_delta);
		_theAngleUserFields.add(_miu_ab_min);
		_theAngleUserFields.add(_miu_an_min);
		_theAngleUserFields.add(_n);
		
		switch( _camProfile ){
		case CamProfilePage.LEVER_OUTER_CAM:
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\41osc. cu culisa simpla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_DOUBLE_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\42osc. cu culisa cama dubla.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_GROOVE_CAM:
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\43osc. cu culisa cama cu canal.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		case CamProfilePage.LEVER_BEAD_CAM:
			_theLengthUserFields.add(_L31);
			_theAngleUserFields.add(_gama);
			try {
				_helpingImg = ImageIO.read(new URL("file:Media\\44osc. cu culisa cama cu nervuri.bmp"));
			} catch (IOException e) {
				// TODO ERROR
				e.printStackTrace();
			}
			break;
		default:
			/*TODO: Error*/
		}
	}
	
	/**
	 * Name: printRollerSlide_Options
	 * Args: 
	 * Return: void
	 * Desc: method that will print the Geometry data of a Slide cam type,
	 * 		 depending on the downstream selection and 
	 * 		 the cam profile selection
	 */
	private void printRollerSlide_Options(){

		switch(_downStream){
		case DownstreamPage.CAMS_NO_DOWNSTREAM:
			switch( _camProfile ){
			case CamProfilePage.SLIDER_OUTER_CAM:
				break;
			case CamProfilePage.SLIDER_DOUBLE_CAM:
				break;
			case CamProfilePage.SLIDER_GROOVE_CAM:
				break;
			case CamProfilePage.SLIDER_BEAD_CAM:
				break;
			default:
				/*TODO: Error*/
			}
			break;
		case DownstreamPage.CAMS_DOUBLE_SLIDE:
			switch( _camProfile ){
			case CamProfilePage.SLIDER_OUTER_CAM:
				break;
			case CamProfilePage.SLIDER_DOUBLE_CAM:
				break;
			case CamProfilePage.SLIDER_GROOVE_CAM:
				break;
			case CamProfilePage.SLIDER_BEAD_CAM:
				break;
			default:
				/*TODO: Error*/
			}
			break;
		case DownstreamPage.CAMS_CRANK:
			switch( _camProfile ){
			case CamProfilePage.SLIDER_OUTER_CAM:
				break;
			case CamProfilePage.SLIDER_DOUBLE_CAM:
				break;
			case CamProfilePage.SLIDER_GROOVE_CAM:
				break;
			case CamProfilePage.SLIDER_BEAD_CAM:
				break;
			default:
				/*TODO: Error*/
			}
			break;
		default:
			/*TODO: Error*/
		}
		
	}

	/**
	 * Name: addGUIComponents
	 * Args: 
	 * Return: void
	 * Desc: The method that adds the initialized components to the layout
	 */
	private void addGUIComponents() {
		
	}
	
}
