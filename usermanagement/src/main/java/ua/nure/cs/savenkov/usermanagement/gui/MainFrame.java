package ua.nure.cs.savenkov.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.cs.savenkov.usermanagement.util.Messages;

public class MainFrame extends JFrame {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	
	public MainFrame() {
		super();
		// dao
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); // localize //$NON-NLS-1$
		this.setContentPane(getContentPanel());
		
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}

	private BrowsePanel getBrowsePanel() {
		if (browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		}
		return browsePanel;
	}
	private AddPanel getAddPanel() {
		if (addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}
	
	
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	}
	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
	}

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
		
	}
}
