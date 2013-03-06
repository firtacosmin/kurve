package com.syntese.graphics.wizard.pages.lastPage;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.syntese.graphics.GBC;
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
	
	
	/*workspace properties title*/
	private static final String NO_SEGMENTS = "MaxMotionSegmets";
	
	
	/*class properties*/
	private Integer _no_segments;
	
	
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
		
//		ArrayList<String> segments = SegmentFactory.getSegmentTitleList();
		String[] segmentsString = new String[_no_segments];
		segmentsString = SegmentFactory.getSegmentTitleList().toArray(segmentsString);
		
		_segmentNameList = new ArrayList<JComboBox<String>>();
		_phiList = new ArrayList<JTextField>(_no_segments);
		_psiList = new ArrayList<JTextField>(_no_segments);
		/*the combo box that contains the number of segments*/
		_selectedNoOfSegments = new JComboBox<Integer>();
		
		/*populate the combo boxes*/
		/*disable the fields*/
		for (int i=0; i<_no_segments; i++)
		{
			JComboBox<String> combo = new JComboBox<String>(segmentsString);
			combo.setEditable(true);
			_segmentNameList.add(combo);
			
			/*initially all the fields are disabled*/
			combo.setEnabled(false);
			_phiList.add(new JTextField());
			_psiList.add(new JTextField());
			
			_selectedNoOfSegments.addItem(i);
		}
		
		/*the textbox where the sum of angles is calculated*/
		_totalPHI = new JTextField("0");
		/*the textBox where the sum of PSI is calculated*/
		_totalPSI = new JTextField("0");
		
		/*add action listener to the combo with the selected number of segments*/
		_selectedNoOfSegments.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				enableFields(Integer.parseInt((String) _selectedNoOfSegments.getSelectedItem()));
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
		
		for ( int i=0; i < _no_segments; i++ )
		{
			/*add segment combobox with layout*/
			add(new JLabel(LanguageFactory.getInstance().getExpresion(SECTION_LABEL) + i), new GBC(0,i));
			add(_segmentNameList.get(i), new GBC(1,i));
			
			/*add phi with label*/
			add(new JLabel ( "phi"+i+"(grad)" ), new GBC(2,i));
			add(_phiList.get(i), new GBC(3,i));
			
			/*add psi with label*/
			add(new JLabel ( "s"+i+"mm/psi"+i+"(grad)" ), new GBC(4,i));
			add(_psiList.get(i), new GBC(5,i));
			
		}
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

}
