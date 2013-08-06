package com.syntese.workspace.graphics;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ProjectsTreePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*UI CONSTANTS*/
	private DefaultMutableTreeNode rootNode;
	private JTree theTree;
	private DefaultTreeModel _treeModel;
	/*UI COMPONENTS*/
	
	/*Action listeners*/
	private ArrayList<ProjectTreeActionListener> _listeners;
	
	
	ArrayList<String> _projects;
	
	
	/*PUBLIC*/
	
	
	public ProjectsTreePanel(ArrayList<String> projects)
	{
		super();
		_projects = projects;
		_listeners = new ArrayList<ProjectTreeActionListener>();
		initUI();
	}
	
	public void addProjTreeActionListener(ProjectTreeActionListener lst){
		_listeners.add(lst);
	}
	
	public void addProject(String project)
	{
		DefaultMutableTreeNode projNode = new DefaultMutableTreeNode(project);
		_treeModel.insertNodeInto(projNode, rootNode, rootNode.getChildCount());
		rootNode.add(projNode);
		theTree.repaint();
		repaint();
	}
	
	
	/*PRIVATE*/
	
	private void initUI()
	{
		//DefaultTreeModel theModel = new DefaultTreeModel ();
		rootNode = new DefaultMutableTreeNode("Workspace");
		_treeModel = new DefaultTreeModel(rootNode);
		for ( int i=0; i<_projects.size(); i++ )
		{
			DefaultMutableTreeNode projNode = new DefaultMutableTreeNode(_projects.get(i));
			rootNode.add(projNode);
		}
		theTree = new JTree(_treeModel); 	
		theTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				/**/
			}
		});
		theTree.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					TreePath selPath = theTree.getPathForLocation(e.getX(), e.getY());
					if ( selPath == null )
						return;
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
					if (node == null)
				    //Nothing is selected.  
				    return;
				    if (node.isLeaf()) {
						for ( int i=0; i< _listeners.size(); i++ ){
							_listeners.get(i).projectDoubleClicked(node.toString());
						}
				    	System.out.println(node.toString());
				    } else {
				    	/*if the selected node is not a leaf. Do nothing*/
				    	System.out.println("Not leaf:"+node.toString());
				    }
					 
				}
			}
		});
		add(theTree, BorderLayout.CENTER);
	}

}
