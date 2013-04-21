package com.syntese.workspace.graphics;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.syntese.graphics.GBC;
import com.syntese.language.LanguageFactory;

public class ChoseWorkspaceDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4586127625713844662L;
	
	private static final String PATH_ERROR_DLG_TITLE = "ChoseWorkspace_pathIncorect_title";
	private static final String PATH_ERROR_DLG_CONTENT = "ChoseWorkspace_pathIncorect_content";
	
	private JComboBox<String> _combo_workspace;
	private ArrayList<String> _paths;
	private String selection;
	
	/*UI constants*/
	private static final int WIDTH=500;
	private static final int HEIGHT=150;
	
	public ChoseWorkspaceDialog(JFrame parent){
		super(parent);
		selection = null;
		_paths = new ArrayList<String>();
		initUI();
		addUI();
	}
	
	public void setPaths(ArrayList<String> paths){
		_paths = paths;
		for ( String path:paths ){
			_combo_workspace.addItem(path);
		}
	}
	
	public void addPath(String path){
		_paths.add(path);
		selection = path;
		_combo_workspace.addItem(path);
		_combo_workspace.setSelectedItem(path);
	}
	
	public String getSelection()
	{
		return selection;
	}
	
	private void initUI()
	{
		_combo_workspace = new JComboBox<String>();
		_combo_workspace.setPreferredSize(new Dimension(300,25));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				selection = null;
			}
		});
		setModal(true);
	}
	
	private void addUI()
	{
		setLayout(new GridBagLayout());
		
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(getPreferredSize());
		setSize(getPreferredSize());
		
		JButton browseBtn = new JButton("Browse");
		browseBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				browse();
			}
		});
		JButton selectBtn = new JButton("Select Workspace");
		selectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectPath();
			}
		});
		
		add(_combo_workspace, new GBC(0,0).setAnchor(GBC.CENTER).setWeight(100, 0) );
		add(selectBtn, new GBC(1,1).setAnchor(GBC.CENTER).setWeight(0, 0));
		add(browseBtn, new GBC(1,0).setAnchor(GBC.CENTER).setWeight(0, 0));
	}


	protected void browse() {
		JFileChooser filedlg = new JFileChooser();
		filedlg.setCurrentDirectory(new File("."));
		filedlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int res = filedlg.showSaveDialog(this);
		if ( res == JFileChooser.APPROVE_OPTION )
		{
			selection = filedlg.getSelectedFile().getPath();
			if ( !_paths.contains(selection) ){
				_combo_workspace.addItem(selection);
				_paths.add(selection);
			}
			_combo_workspace.setSelectedItem(selection);
		}
	}
	
	protected void selectPath(){
		Boolean pathOK = false;
		try{
			if ( new File(selection).isDirectory() )
			{
				pathOK = true;
			}
		}catch(Exception ex){
			
		}
		if ( !pathOK ){
			JOptionPane.showMessageDialog(this,
										LanguageFactory.getInstance().getExpresion(PATH_ERROR_DLG_TITLE), 
					       				LanguageFactory.getInstance().getExpresion(PATH_ERROR_DLG_CONTENT), 
					       				JOptionPane.ERROR_MESSAGE);
		}else{
			dispose();
		}
	}
	
}
