package com.syntese.project.data;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ProjectData {

	private static final String ROOT_TAG_NAME = "PROJECT";
	private static final String CAM_TYPE_TAG_NAME = "TYPE";
	private static final String CAM_PROFILE_TAG_NAME = "PROFILE";
	private static final String CAM_DOWNSTREAM_TAG_NAME = "DOWNSTREAM";
	private static final String CAM_CAM_SIGN_TAG_NAME = "CAM_SIGN";
	private static final String CAM_LEVER_SIGN_TAG_NAME = "LEVER_SIGN";
	private static final String CAM_GEOM_DATA_TAG_NAME = "GEOMETRICS";
	private static final String CAM_PHI_TAG_NAME = "PHI";
	private static final String CAM_PSI_TAG_NAME = "PSI";
	private static final String CAM_NO_SEGMENTS_TAG_NAME = "NUMBER_SEGMENTS";
	private static final String CAM_TOTAL_PHI_TAG_NAME = "TOTAL_PHI";
	private static final String CAM_TOTAL_PSI_TAG_NAME = "TOTAL_PSI";
	private static final String CAM_SEGMENTS_TAG_NAME = "SEGMENTS";
	

	/*CONSTANT PROPARTIES VALUES*/
	/*CAM PROFILE TYPES*/
	public static final int NO_SELECTION = 0;
	public static final int LEVER_OUTER_CAM = 1;
	public static final int LEVER_DOUBLE_CAM = 2;
	public static final int LEVER_GROOVE_CAM = 3;
	public static final int LEVER_BEAD_CAM = 4;
	public static final int SLIDER_OUTER_CAM = 5;
	public static final int SLIDER_DOUBLE_CAM = 6;
	public static final int SLIDER_GROOVE_CAM = 7;
	public static final int SLIDER_BEAD_CAM= 8;
	/*CAM TYPES*/
	public static final int ROLLER_LEVER = 1;
	public static final int ROLLER_SLIDE = 2;
	/*CAM DOWNSTREAM TYPES*/
	public static final int NO_SELECTIONS = 0;
	public static final int LEVER_NO_DOWNSTREAM = 1;
	public static final int LEVER_CRANK = 2;
	public static final int LEVER_FOUR_JOIN = 3;
	public static final int LEVER_PUSHER_TUGS = 4;
	public static final int CAMS_NO_DOWNSTREAM = 5;
	public static final int CAMS_DOUBLE_SLIDE = 6;
	public static final int CAMS_CRANK = 7;
	
	/*PROJECT DATA*/
	private Integer _cam_type;
	private Integer _cam_profile;
	private Integer _cam_downstream;
	private Boolean _cam_positive_sign;
	private Boolean _lever_positive_sign;
	private HashMap<String,Float> _cam_geomData;
	private ArrayList<Float> _cam_phi;
	private ArrayList<Float> _cam_psi;
	private Integer _cam_no_segments;
	private Integer _cam_total_phi;
	private Integer _cam_total_psi;
	private ArrayList<String> _cam_segments;
	private String _proj_name;

	/*
	 * PUBLIC METHODS
	 * */
	

	public ProjectData()
	{
		_cam_type = 0;
		_cam_profile = 0;
		_cam_downstream =0 ;
		_cam_positive_sign = true;
		_lever_positive_sign = true;
		_cam_geomData = new HashMap<String, Float>(20);
		_cam_phi = new ArrayList<Float>(20);
		_cam_psi = new ArrayList<Float>(20);
		_cam_no_segments = 0;
		_cam_total_phi = 0;
		_cam_total_psi = 0;
		_proj_name = "";
		_cam_segments = new ArrayList<String>(20);
	}

	



	/**
	 * Name: saveProjectFile
	 * Args: @param path
	 * Args: @return
	 * Return: Boolean
	 * Desc: saves the project file in xml format into the specified path
	 */
	public Boolean saveProjectFile( String path )
	{
		Boolean ret = false;
		
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			/*create the new file*/
			Document newDoc = builder.newDocument();
			Element rootElement = newDoc.createElement(ROOT_TAG_NAME);
			/* Add the cam TYPE */
			addXmlElement(CAM_TYPE_TAG_NAME,_cam_type.toString(), rootElement, newDoc);
			/* Add the cam profile */
			addXmlElement(CAM_PROFILE_TAG_NAME,_cam_profile.toString(), rootElement, newDoc);
			/* Add the cam downstream */
			addXmlElement(CAM_DOWNSTREAM_TAG_NAME,_cam_downstream.toString(), rootElement, newDoc);
			/* add the segments */
			Element segmentsEl = newDoc.createElement(CAM_SEGMENTS_TAG_NAME);
			for ( Integer i=0; i<_cam_segments.size(); i++ )
			{
				addXmlElement("seg"+i.toString(), _cam_segments.get(i), segmentsEl, newDoc);
			}
			rootElement.appendChild(segmentsEl);
			/*add the phis*/
			Element phiEl = newDoc.createElement(CAM_PHI_TAG_NAME);
			for ( Integer i=0; i<_cam_phi.size(); i++ )
			{
				addXmlElement("phi_"+i.toString(), _cam_phi.get(i).toString(), phiEl, newDoc);
			}
			rootElement.appendChild(phiEl);
			/*add the psis*/
			Element psiEl = newDoc.createElement(CAM_PSI_TAG_NAME);
			for ( Integer i=0; i<_cam_psi.size(); i++ )
			{
				addXmlElement("psi_"+i.toString(), _cam_psi.get(i).toString(), psiEl, newDoc);
			}
			rootElement.appendChild(psiEl);
			/*add the number of segments*/
			addXmlElement(CAM_NO_SEGMENTS_TAG_NAME, _cam_no_segments.toString(), rootElement, newDoc);
			/*add the total phi*/
			addXmlElement(CAM_TOTAL_PHI_TAG_NAME, _cam_total_phi.toString(), rootElement, newDoc);
			/*add the total psi*/
			addXmlElement(CAM_TOTAL_PSI_TAG_NAME, _cam_total_psi.toString(), rootElement, newDoc);
			/*add the geometrical data*/
			Element geomEl = newDoc.createElement(CAM_GEOM_DATA_TAG_NAME);
			for ( Integer i=0; i<_cam_geomData.size(); i++ )
			{
				Object key = _cam_geomData.keySet().toArray()[i].toString();
				String value = _cam_geomData.get(key).toString();
				addXmlElement(key.toString().replace(" ", "_").replace(")", "_").replace("(", "_"), value, geomEl, newDoc);
			}
			rootElement.appendChild(geomEl);
			/*add the cam sigh*/
			addXmlElement(CAM_CAM_SIGN_TAG_NAME, _cam_positive_sign?"+":"-", rootElement, newDoc);
			/*add the lever sigh*/
			addXmlElement(CAM_LEVER_SIGN_TAG_NAME, _lever_positive_sign?"+":"-", rootElement, newDoc);
			/*add route element to the doc*/
			newDoc.appendChild(rootElement);
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(new DOMSource(newDoc), new StreamResult(new FileOutputStream(new File(path+"\\"+_proj_name+".xml"))));
			
			ret = true;
		}
		catch (Exception ex)
		{
			/*TODO: Error to log*/
			System.out.println(ex.getStackTrace());
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	
	public boolean open(String file)
	{
		Boolean ret = false;
		try
		{
			_proj_name = new File(file).getName();
			ret = true;
		}catch (  Exception ex)
		{
			//TODO: Error
		}
		return ret;
	}
	
	
	/*
	 * GETTERS AND  SETTERS FOR THE CAM DATA VARIABLES
	 * */
	public String get_proj_name() {
		return _proj_name;
	}


	public void set_proj_name(String _proj_name) {
		this._proj_name = _proj_name;
	}
	
	public int get_cam_type() {
		return _cam_type;
	}


	public void set_cam_type(int _cam_type) {
		this._cam_type = _cam_type;
	}


	public int get_cam_profile() {
		return _cam_profile;
	}


	public void set_cam_profile(int _cam_profile) {
		this._cam_profile = _cam_profile;
	}


	public int get_cam_downstream() {
		return _cam_downstream;
	}


	public void set_cam_downstream(int _cam_downstream) {
		this._cam_downstream = _cam_downstream;
	}


	public Boolean get_cam_positive_sign() {
		return _cam_positive_sign;
	}


	public void set_cam_positive_sign(Boolean _cam_positive_sign) {
		this._cam_positive_sign = _cam_positive_sign;
	}


	public Boolean get_lever_positive_sign() {
		return _lever_positive_sign;
	}


	public void set_lever_positive_sign(Boolean _lever_positive_sign) {
		this._lever_positive_sign = _lever_positive_sign;
	}


	public HashMap<String, Float> get_cam_geomData() {
		return _cam_geomData;
	}


	public void add_cam_geomData(String _cam_geom_name, Float _cam_geomData) {
		this._cam_geomData.put(_cam_geom_name, _cam_geomData);
	}


	public void set_cam_geomData(HashMap<String, Float> _cam_geomData) {
		this._cam_geomData = _cam_geomData;
	}


	public void set_cam_phi(ArrayList<Float> _cam_phi) {
		this._cam_phi = _cam_phi;
	}


	public void set_cam_psi(ArrayList<Float> _cam_psi) {
		this._cam_psi = _cam_psi;
	}


	public void set_cam_segments(ArrayList<String> _cam_segments) {
		this._cam_segments = _cam_segments;
	}


	public ArrayList<Float> get_cam_phi() {
		return _cam_phi;
	}


	public void add_cam_phi(Float _cam_phi) {
		this._cam_phi.add(_cam_phi);
	}


	public ArrayList<Float> get_cam_psi() {
		return _cam_psi;
	}


	public void add_cam_psi(Float _cam_psi) {
		this._cam_psi.add(_cam_psi);
	}


	public int get_cam_no_segments() {
		return _cam_no_segments;
	}


	public void set_cam_no_segments(int _cam_no_segments) {
		this._cam_no_segments = _cam_no_segments;
	}


	public int get_cam_total_phi() {
		return _cam_total_phi;
	}


	public void set_cam_total_phi(int _cam_total_phi) {
		this._cam_total_phi = _cam_total_phi;
	}


	public int get_cam_total_psi() {
		return _cam_total_psi;
	}


	public void set_cam_total_psi(int _cam_total_psi) {
		this._cam_total_psi = _cam_total_psi;
	}
	
	public ArrayList<String> get_cam_segments() {
		return _cam_segments;
	}


	public void add_cam_segments(String _cam_segments) {
		this._cam_segments.add(_cam_segments);
	}

	
	/*
	 * PRIVATE METHODS
	 * */
	
	private void addXmlElement( String name, String value, Element rootElement, Document newDoc)
	{
		Element el = newDoc.createElement(name);
		Text elValue = newDoc.createTextNode(value);
		el.appendChild(elValue);
		rootElement.appendChild(el);
	}



}
