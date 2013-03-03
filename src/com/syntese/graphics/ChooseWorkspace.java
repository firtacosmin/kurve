package com.syntese.graphics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ChooseWorkspace {

  private JFrame frame;
  private JComboBox workspaceComboBox;
  private JLabel chooseWorkspaceLabel;
  private JButton btnBrowse;
  private JButton btnOk;
  private JButton clearWorkspaceHsitoryButton;
  ArrayList<String> workspaceSavedList;

  public static void main(String[] args) {  

	  EventQueue.invokeLater(
			  new Runnable() {  
				  public void run() {  
				      try {
				    	  ChooseWorkspace window = new ChooseWorkspace();
				          window.frame.setVisible(true);
				      } catch (Exception e) {
				          e.printStackTrace();
				      }
				  }
			  	});
  }
 
  public ChooseWorkspace() {
    try {
		initialize();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
 
  private void initialize() throws IOException {
     
	readWorkspaceList();
    makeFramesAndCustomize();
    addActionListeners();
  }
  
  private void readWorkspaceList(){
	  
	  String pathToWorkSpaceListFile= System.getProperty("user.dir");
	  pathToWorkSpaceListFile = pathToWorkSpaceListFile.concat("WorkspaceList.txt");
	  
	  File workSpaceFile = new File(pathToWorkSpaceListFile);
	
	  if(!workSpaceFile.exists()) {
		  try {
			workSpaceFile.createNewFile();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	  } 
	  
	  BufferedReader in = null;
	  ArrayList<String> myList = new ArrayList<String>();
	  try {   
	      in = new BufferedReader(new FileReader(pathToWorkSpaceListFile));
	      String str;
	      
	      while ((str = in.readLine()) != null) {
	          myList.add(str);
	      }
	  } catch (FileNotFoundException e) {
	      e.printStackTrace();
	  } catch (IOException e) {
	      e.printStackTrace();
	  } finally {
	      if (in != null) {
	          try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	      }
	  }
	  
	  workspaceSavedList = myList;
  }
  
  private void makeFramesAndCustomize(){
	  	frame = new JFrame();
	    frame.setBounds(100, 100, 750, 250);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    
	    JPanel whitePanel = new JPanel();
	    whitePanel.setBounds(0, 0, 750, 50);
	    whitePanel.setBackground(Color.white);
	    whitePanel.setLayout(null);
	    frame.getContentPane().add(whitePanel);
	    
	    //    
	    chooseWorkspaceLabel = new JLabel("Please choose your workspace");
	    chooseWorkspaceLabel.setBounds(10, 15, 600, 21);
	    whitePanel.add(chooseWorkspaceLabel);
	    
	    //
	    workspaceComboBox = new JComboBox();
	    workspaceComboBox.setBounds(10, 90, 600, 21);
	    frame.getContentPane().add(workspaceComboBox);
	    
	    for(int i=workspaceSavedList.size()-1; i>=0; i--){
	    	workspaceComboBox.addItem(workspaceSavedList.get(i));	
	    }

	    //
	    btnBrowse = new JButton("Browse");
	    btnBrowse.setBounds(620, 90, 100, 21);
	    frame.getContentPane().add(btnBrowse);
	    
	    btnOk = new JButton("Ok");
	    btnOk.setBounds(620, 130, 100, 21);
	    frame.getContentPane().add(btnOk);

	    clearWorkspaceHsitoryButton = new JButton("Clear List");
	    clearWorkspaceHsitoryButton.setBounds(620, 170, 100, 21);
	    frame.getContentPane().add(clearWorkspaceHsitoryButton);
	    
  }

  private void addActionListeners(){    
	  
	    workspaceComboBox.addActionListener(new ActionListener() {
	    	
	        public void actionPerformed(ActionEvent e) {
	            JComboBox cb = (JComboBox)e.getSource();
	            String workspacePath = (String)cb.getSelectedItem();
//	            updateLabel(workspacePath);
	        }
	    });
	    
	    btnBrowse.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
		    	JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		         //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        fileChooser.setAcceptAllFileFilterUsed(false);
		        int rVal = fileChooser.showOpenDialog(null);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		        	workspaceComboBox.insertItemAt(fileChooser.getSelectedFile().toString(),0);
		        	workspaceComboBox.setSelectedIndex(0);
		        	addWorkspaceToWorkpsaceList(fileChooser.getSelectedFile().toString());
		        }
	      }
	    });

	    btnOk.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		//UNIMPLEMENTED CODE
	    		//UNIMPLEMENTED CODE
	    		//UNIMPLEMENTED CODE
	      }
	    });
	    
	    clearWorkspaceHsitoryButton.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e) {
	    		workspaceComboBox.removeAllItems();
	    		
	    		String pathToWorkSpaceListFile= System.getProperty("user.dir");
	    		pathToWorkSpaceListFile = pathToWorkSpaceListFile.concat("WorkspaceList.txt");
	    		File workSpaceFile = new File(pathToWorkSpaceListFile);
	    		workSpaceFile.delete();
	      }
	    });
	    
  }
  
  private void addWorkspaceToWorkpsaceList(String pathSelected){
	  String pathToWorkSpaceListFile= System.getProperty("user.dir");
	  pathToWorkSpaceListFile = pathToWorkSpaceListFile.concat("WorkspaceList.txt");
	
	  File workSpaceFile = new File(pathToWorkSpaceListFile);
	  if(!workSpaceFile.exists()) {
		  try {
			workSpaceFile.createNewFile();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	  } 
	  
	  try {
		  	FileWriter fW = new FileWriter(pathToWorkSpaceListFile, true);
		  	BufferedWriter bW = new BufferedWriter(fW);
		    PrintWriter out = new PrintWriter(bW);
		    out.println(pathSelected);
		    out.close();
		} catch (IOException e) {

		}
	  
  }
	  
}
