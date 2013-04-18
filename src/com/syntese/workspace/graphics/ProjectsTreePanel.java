package com.syntese.workspace.graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.syntese.project.data.ProjectData;

public class ProjectsTreePanel extends JPanel {

	/*UI CONSTANTS*/
	private static int WIDTH = 70;
	private DefaultMutableTreeNode rootNode;
	private JTree theTree;
	private DefaultTreeModel _treeModel;
	/*UI COMPONENTS*/
	
	
	ArrayList<ProjectData> _projects;
	
	public ProjectsTreePanel(ArrayList<ProjectData> projects)
	{
		super();
		_projects = projects;
		
		initUI();
	}
	
	public void addProject(ProjectData project)
	{
		DefaultMutableTreeNode projNode = new DefaultMutableTreeNode(project.get_proj_name());
		_treeModel.insertNodeInto(projNode, rootNode, rootNode.getChildCount());
		rootNode.add(projNode);
		theTree.repaint();
		repaint();
	}
	
	
	private void initUI()
	{
		//DefaultTreeModel theModel = new DefaultTreeModel ();
		rootNode = new DefaultMutableTreeNode("Workspace");
		_treeModel = new DefaultTreeModel(rootNode);
		for ( int i=0; i<_projects.size(); i++ )
		{
			DefaultMutableTreeNode projNode = new DefaultMutableTreeNode(_projects.get(i).get_proj_name());
			rootNode.add(projNode);
		}
		theTree = new JTree(_treeModel); 	
		add(theTree, BorderLayout.CENTER);
	}

}
