package com.syntese.graphics.wizard.pages.lastPage;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.syntese.graphics.GBC;
import com.syntese.graphics.NumericTextField;
import com.syntese.language.LanguageFactory;
import com.syntese.segment.SegmentFactory;
import com.syntese.settings.SettingsFactory;

public class MotionTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/****************
	 * PROPERTIES
	 * *************/
	private static final Integer DEFAULT_NO_SEGMENTS = 12;
	
	/*texts*/
	private static final String SECTION_LABEL = "Wizard_Motion_SegmentLabel";
	private static final String NUMBER_OF_SEGMENTS_LABEL = "Wizard_Motion_NoOfSegmentLabel";
	private static final String PHI_SUM_LABEL = "Wizard_Motion_PHISum";
	private static final String PSI_SUM_LABEL = "Wizard_Motion_PSISum";
	private static final String PHI_SUM_ERROR_MSJ = "Wizard_Motion_PHISum_error";
	private static final String PSI_SUM_ERROR_MSJ = "Wizard_Motion_PSISum_error";
	private static final String ERROR_DLG_TITLE = "Error_dialog_title";
	private static final String NO_SEGMENTS_ERROR_MSJ = "Wizard_Motion_NoSegments_error";
	
	
	/*workspace properties title*/
	private static final String NO_SEGMENTS = "MaxMotionSegmets";
	
	
	/*class properties*/
	private Integer _no_segments;
	private Integer _enabledFields;
	
	
	/*GUI*/
	private ArrayList<JComboBox<String>> _segmentNameList;
	private ArrayList<JTextField> _phiList;
	private ArrayList<JTextField> _psiList;
	private JComboBox<Integer> _selectedNoOfSegments;
	private JTextField _totalPHI;
	private JTextField _totalPSI;
	
	/****************
	 * METHODS
	 * *************/
	
	/*
	 * PUBLIC
	 * */
	/**
	 * Name: MotionTab
	 * Args: none
	 */
	public MotionTab()
	{
		super();
		InitializeComponents();
		addComponentsToGUI();
	}
	
	/**
	 * Name: areFieldsValid
	 * Args: @return
	 * Return: Boolean
	 * Desc: Checks if the fields are validly completed.
	 */
	public Boolean areFieldsValid()
	{
		/*Check for the selected number of segments*/
		if (getNoOfSegments() == 0)
		{
			JOptionPane.showMessageDialog(this, 
					LanguageFactory.getInstance().getExpresion(ERROR_DLG_TITLE) + ":\n" +LanguageFactory.getInstance().getExpresion(NO_SEGMENTS_ERROR_MSJ), 
                    LanguageFactory.getInstance().getExpresion(ERROR_DLG_TITLE), 
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		/*check for the total PHI and PSI values*/
		if ( getTotalPhi() == 360 && getTotalPsi() == 0 )
		{
			return true;
		}else{
			String msj = LanguageFactory.getInstance().getExpresion(ERROR_DLG_TITLE) + ":\n";
			if ( getTotalPhi() != 360 ){
				msj += LanguageFactory.getInstance().getExpresion(PHI_SUM_ERROR_MSJ)+"\n";
			}
			if ( getTotalPsi() != 0 ){
				msj += LanguageFactory.getInstance().getExpresion(PSI_SUM_ERROR_MSJ)+"\n";
			}
			JOptionPane.showMessageDialog(this, 
					                      msj, 
					                      LanguageFactory.getInstance().getExpresion(ERROR_DLG_TITLE), 
					                      JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	/*
	 * getters for the selections
	 * */
	
	public int getNoOfSegments()
	{
		return Integer.parseInt(_selectedNoOfSegments.getSelectedItem().toString());
	}
	
	public int getTotalPhi()
	{
		return Integer.parseInt(_totalPHI.getText());
	}
	
	public int getTotalPsi()
	{
		return Integer.parseInt(_totalPSI.getText());
	}
	
	public ArrayList<String> getSelectedSegments()
	{
		ArrayList<String> segments = new ArrayList<String>(_enabledFields);
		for ( int i=0; i < _enabledFields; i++ )
		{
			segments.add(_segmentNameList.get(i).getSelectedItem().toString());
		}
		return segments;
	}
	
	public ArrayList<Float> getPHI()
	{

		ArrayList<Float> phi = new ArrayList<Float>(_enabledFields);
		for ( int i=0; i < _enabledFields; i++ )
		{
			phi.add(Float.parseFloat(_phiList.get(i).getText()));
		}
		return phi;
	}
	
	public ArrayList<Float> getPSI()
	{
		ArrayList<Float> psi = new ArrayList<Float>(_enabledFields);
		for ( int i=0; i < _enabledFields; i++ )
		{
			psi.add(Float.parseFloat(_psiList.get(i).getText()));
		}
		return psi;
		
	}
	


	
	/*
	 * PRIVATE
	 * */

	/**
	 * Name: InitializeComponents
	 * Args: 
	 * Return: void
	 * Desc: method that initializes all the GUI components
	 */
	private void InitializeComponents() {
		getMaxNoOfSegments();
		_enabledFields = 0;
//		ArrayList<String> segments = SegmentFactory.getSegmentTitleList();
		String[] segmentsString = new String[SegmentFactory.getSegmentTitleList().size()];
		segmentsString = SegmentFactory.getSegmentTitleList().toArray(segmentsString);
		
		_segmentNameList = new ArrayList<JComboBox<String>>();
		_phiList = new ArrayList<JTextField>(_no_segments);
		_psiList = new ArrayList<JTextField>(_no_segments);
		/*the combo box that contains the number of segments*/
		_selectedNoOfSegments = new JComboBox<Integer>();
		
		/*populate the combo boxes*/
		/*disable the fields*/
		int i;
		for (i=0; i<_no_segments; i++)
		{
			JComboBox<String> combo = new JComboBox<String>(segmentsString);
//			combo.setEditable(true);
			_segmentNameList.add(combo);
			
			/*initially all the fields are disabled*/
			combo.setEnabled(false);
			NumericTextField phi = new NumericTextField();
			phi.setText("0");
			phi.setEnabled(false);
			phi.addCaretListener(new CaretListener() {
				
				@Override
				public void caretUpdate(CaretEvent arg0) {
					// TODO Auto-generated method stub
					calculateSum(_phiList, _totalPHI);
					
				}
			});
			_phiList.add(phi);
			JTextField psi = new JTextField();
			psi.setEnabled(false);
			psi.setText("0");
			psi.addCaretListener(new CaretListener() {
				
				@Override
				public void caretUpdate(CaretEvent arg0) {
					calculateSum(_psiList, _totalPSI);
				}
			});
			_psiList.add(psi);
			
			
			_selectedNoOfSegments.addItem(i);
		}
		/*one more item so that all the segments ( 0-_no_segments ) can be selected*/
		_selectedNoOfSegments.addItem(i);
		
		/*the textbox where the sum of angles is calculated*/
		_totalPHI = new JTextField("0");
		/*the textBox where the sum of PSI is calculated*/
		_totalPSI = new JTextField("0");
		
		/*add action listener to the combo with the selected number of segments*/
		_selectedNoOfSegments.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sel = _selectedNoOfSegments.getSelectedItem().toString();
				enableFields(Integer.parseInt(sel));
			}
		});
		
		
	}
	
	/**
	 * Name: addComponentsToGUI
	 * Args: 
	 * Return: void
	 * Desc: METHOD that will add the GUI components to the layout
	 */
	private void addComponentsToGUI() {
		setLayout(new GridBagLayout());
//		setPreferredSize(new Dimension(600,500));
//		setMinimumSize(getPreferredSize());
//		setSize(getPreferredSize());

		GridLayout lay = new GridLayout(_no_segments+1,2);
		lay.setHgap(5);
		lay.setVgap(5);
		int i;
		JPanel segmentPan = new JPanel(lay);
		segmentPan.setBackground(new Color(109,109,109));
		segmentPan.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel phiPan = new JPanel(lay);
		phiPan.setBackground(new Color(109,109,109));
		phiPan.setBorder(BorderFactory.createRaisedBevelBorder());
		JPanel psiPan = new JPanel(lay);
		psiPan.setBackground(new Color(109,109,109));
		psiPan.setBorder(BorderFactory.createRaisedBevelBorder());
		/*add all the disabled fields*/
		for ( i=0; i < _no_segments; i++ )
		{
			/*add segment combobox with layout*/
			segmentPan.add(new JLabel(LanguageFactory.getInstance().getExpresion(SECTION_LABEL) + i));
			segmentPan.add(_segmentNameList.get(i));
			
			/*add phi with label*/
			phiPan.add(new JLabel ( "phi"+i+"(grad)" ));
			phiPan.add(_phiList.get(i));
			
			/*add psi with label*/
			psiPan.add(new JLabel ( "s"+i+"mm/psi"+i+"(grad)" ));
			psiPan.add(_psiList.get(i));
		}
		
		add(psiPan, new GBC(4,0,2,1).setWeight(100, 100).setFill(GBC.VERTICAL).setAnchor(GBC.NORTH).setInsets(10, 15, 10, 15));
		add(segmentPan, new GBC(0,0,2,1).setWeight(100, 100).setFill(GBC.BOTH).setAnchor(GBC.NORTH).setInsets(10, 15, 10, 15));
		add(phiPan, new GBC(2,0,2,1).setWeight(100, 100).setFill(GBC.VERTICAL).setAnchor(GBC.NORTH).setInsets(10, 15, 10, 15));

		
		/*add the active filed selector*/
		add(new JLabel(LanguageFactory.getInstance().getExpresion(NUMBER_OF_SEGMENTS_LABEL)), new GBC(0,1));
		add(_selectedNoOfSegments, new GBC(1,1));
		
			
		/*add the total psi*/
		add(new JLabel(LanguageFactory.getInstance().getExpresion(PSI_SUM_LABEL)), new GBC(4,1));
		add(_totalPSI, new GBC(5,1).setFill(GBC.HORIZONTAL));
		
		/*add the total PHI*/
		add(new JLabel(LanguageFactory.getInstance().getExpresion(PHI_SUM_LABEL)), new GBC(2,1));
		add(_totalPHI, new GBC(3,1).setFill(GBC.HORIZONTAL));
		
		
	}
	
	/**
	 * Name: getMaxNoOfSegments
	 * Args: 
	 * Return: void
	 * Desc: gets the maximum number of segments saved in the settings file.
	 */
	private void getMaxNoOfSegments()
	{
		try/*try to get from current setting*/
		{
			/*get maximum number of segments*/
			_no_segments = Integer.valueOf(SettingsFactory.getInstance().getCurrentSetting(NO_SEGMENTS));
		}catch ( Exception ex )
		{
			//TODO: error
			try/*if error when trying to get from current setting*/
			   /*try to get from default setting*/
			{
				_no_segments = Integer.valueOf(SettingsFactory.getInstance().getDefaultSetting(NO_SEGMENTS));
			}catch ( Exception ex2 )
			{
				//TODO:error
				/*if error when trying to get from default setting*/
				/*save a hardcoded default value*/
				_no_segments = DEFAULT_NO_SEGMENTS;
			}
		}
		
	}
	
	/**
	 * Name: enableFields
	 * Args: @param noOfFields
	 * Return: void
	 * Desc: Enables only the first noOfFields fields.The rest will be disabled 
	 */
	private void enableFields(Integer noOfFields)
	{
		_enabledFields = noOfFields;
		for ( int i=0; i<_no_segments; i++ )
		{
			if ( i<noOfFields )
			{
				_segmentNameList.get(i).setEnabled(true);
				_phiList.get(i).setEnabled(true);
				_psiList.get(i).setEnabled(true);
			}
			else
			{
				_segmentNameList.get(i).setEnabled(false);
				_phiList.get(i).setEnabled(false);
				_psiList.get(i).setEnabled(false);
			}
		}
	}
	
	/**
	 * Name: calculateSum
	 * Args: @param textFields - the list with JTextFields with the values to be added
	 * Args: @param totalField - the destination JTextField that will contain the result
	 * Return: void
	 * Desc: method that calculates in the same way the sum for phi and psi
	 */
	private void calculateSum(ArrayList<JTextField> textFields, JTextField totalField)
	{
		Integer sum = 0;
		Boolean err = false;
		for ( int i=0;i<_enabledFields; i++ )
		{
			try{
				int newVal = Integer.parseInt(textFields.get(i).getText());
				sum += newVal;
			}catch ( Exception ex )
			{
				err = true;
			}
		}
		if ( !err )
		{
			totalField.setText(sum.toString());
		}else{
			totalField.setText("Err");
		}
	}

}
