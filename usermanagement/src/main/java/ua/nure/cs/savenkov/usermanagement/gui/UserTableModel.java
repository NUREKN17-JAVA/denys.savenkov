package ua.nure.cs.savenkov.usermanagement.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.cs.savenkov.usermanagement.User;

public class UserTableModel extends AbstractTableModel {
	
	private List users = null;
	private static final String[] COLUMN_NAMES = {"ID", "Имя", "Фамилия"};
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	
	public UserTableModel() {
		this.users = null;
	}
	
	public UserTableModel(Collection users) {
		this.users = new ArrayList(users);
	}
	
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}
	
	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}
	
	public String getColumnName(int columnIndex) {
		return COLUMN_NAMES[columnIndex];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}

}