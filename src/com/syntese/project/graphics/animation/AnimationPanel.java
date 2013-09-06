package com.syntese.project.graphics.animation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteOrder;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.syntese.graphics.GBC;
import com.syntese.language.LanguageFactory;

/**
 * @author cfirta
 * @description panel for displaying an animation with the resulted kurve.
 */
public class AnimationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String SAVE_AS_BTN_TEXT = "SaveAsMenu";
	private static String ANIMATION_CHK_TEXT = "CHK_Animation_text";
	private static String CHANGE_COLOR_BTN_TEXT = "Btn_Chose_bg_color_text";
	private static String SLOWER_TEXT = "slower_text";
	private static String FASTER_TEXT = "faster_text";
	
	private JPanel _animatioPan;
	private JCheckBox _chkstartAnimation;
	private JSlider _sldSpeed;
	private JButton _btnSaveAs;
	private JButton _btnChangeColor;
	
	private int _sliderStart = 0;
	private int _sliderEnd = 10;
	
	

	public AnimationPanel(){
		super();
		InitComponents();
		AddComponents();
	}
	
	/*
	 * PRIVATE
	 * */

	/**
	 * @Name: AddComponents
	 * @Args: 
	 * @Return: void
	 * @Desc: 
	 */
	private void AddComponents() {
		setLayout(new GridBagLayout());
		
		JPanel leftPan = new JPanel();
		leftPan.setLayout(new BorderLayout());
		leftPan.add(_btnSaveAs, BorderLayout.NORTH);
		leftPan.add(_btnChangeColor, BorderLayout.SOUTH);
		add(leftPan, new GBC(0, 0));
		
		add(_animatioPan, new GBC(1,0).setFill(GBC.BOTH).setWeight(100, 100));
		
		JPanel rightPan = new JPanel();
		rightPan.setLayout(new BorderLayout());
		rightPan.add(_chkstartAnimation, BorderLayout.NORTH);
		rightPan.add(_sldSpeed, BorderLayout.SOUTH);
		add(rightPan, new GBC(2,0));
		
	}

	/**
	 * @Name: InitComponents
	 * @Args: 
	 * @Return: void
	 * @Desc: 
	 */
	private void InitComponents() {
		_btnChangeColor = new JButton(LanguageFactory.getInstance().getExpresion(CHANGE_COLOR_BTN_TEXT));
		_btnChangeColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeBgColor();
			}
		});
		
		_btnSaveAs = new JButton(LanguageFactory.getInstance().getExpresion(SAVE_AS_BTN_TEXT));
		_btnSaveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAnimation();
			}
		});
		
		_chkstartAnimation = new JCheckBox(LanguageFactory.getInstance().getExpresion(ANIMATION_CHK_TEXT));
		_chkstartAnimation.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				startAnimation(_chkstartAnimation.isSelected());
			}
		});
		
		_sldSpeed = new JSlider(SwingConstants.HORIZONTAL, _sliderStart, _sliderEnd, _sliderStart);
		_sldSpeed.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				changeSpeed(_sliderEnd - _sldSpeed.getValue());
			}
		});
		
		Hashtable<Integer, Component> lableTable = new Hashtable<Integer, Component>();
		lableTable.put(0, new JLabel(LanguageFactory.getInstance().getExpresion(FASTER_TEXT)));
		lableTable.put(10, new JLabel(LanguageFactory.getInstance().getExpresion(SLOWER_TEXT)));
		_sldSpeed.setLabelTable(lableTable); 
		_sldSpeed.setPaintLabels(true);
		_sldSpeed.setPaintTicks(true);

		
		_animatioPan = new JPanel();
		_animatioPan.setBackground(Color.gray);
	}
	
	
	/**
	 * @Name: changeBgColor
	 * @Args: 
	 * @Return: void
	 * @Desc: method called whe the change bg color btn is pressed.
	 * 		  Method will call a color dialog and will set the selected 
	 *        color as bg color to the amination
	 */
	private void changeBgColor(){
		
	}
	
	/**
	 * @Name: saveAnimation
	 * @Args: 
	 * @Return: void
	 * @Desc: method called when pressing the save btn.
	 * 		  method will call a file dialog and will save a png file
	 *        with the current image on the animation 
	 */
	private void saveAnimation(){
		
	}
	
	/**
	 * @Name: startAnimation
	 * @Args: @param start - true to start, false to stop
	 * @Return: void
	 * @Desc: method for starting or stoping the animation
	 */
	private void startAnimation(Boolean start){
		
	}
	
	/**
	 * @Name: changeSpeed
	 * @Args: @param speedValue
	 * @Return: void
	 * @Desc: method for changing the speed of the animation.
	 * 		  gets as parameter the speed value to change to 
	 */
	private void changeSpeed(int speedValue){
		
	}
}
