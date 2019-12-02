package ua.nure.cs.savenkov.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ua.nure.cs.savenkov.usermanagement.User;
import ua.nure.cs.savenkov.usermanagement.db.DaoFactory;
import ua.nure.cs.savenkov.usermanagement.db.DataBaseException;
import ua.nure.cs.savenkov.usermanagement.db.UserDao;
import ua.nure.cs.savenkov.usermanagement.util.Messages;

public class DeletePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private Color bgColor;
	private User user;
	private UserDao userDao;
	private JLabel sureText;
	
	public DeletePanel(MainFrame parent, User usr) {
		this.parent = parent;
		initialize(usr);
	}
	
	private void initialize(User usr) {
		this.setName("deletePanel");
		this.setLayout(new BorderLayout());
		this.add(getTextPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		userDao = DaoFactory.getInstance().getUserDao();
		this.user = usr;
	}
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}
	
	private JPanel getTextPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getComfirmationText(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("AddPanel.cancel");
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("AddPanel.ok");
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}
	
	private JLabel getComfirmationText() {
		if (sureText == null) {
			sureText = new JLabel();
			sureText.setText("Delete this user?");
			sureText.setName("sureText");
		}
		return sureText;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
			try {
				userDao.delete(this.user);
			} catch (DataBaseException e1) {
				e1.printStackTrace();
			}
		}
		this.setVisible(false);
		parent.showBrowsePanel();
	}
}