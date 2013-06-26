package Auxilium.ComboBoxModels;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;

/**
 * This class is a custom made combo box to hold departments.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class staffDeptComboModel extends AbstractListModel implements
		ComboBoxModel
{

	//Database db = new Database();
	private String[] deptNames;
	String selection = null;

	public staffDeptComboModel()
	{
		//Connections.createConnection();
		Database.getAllDepartments();

		try
		{
			Connections.rset.last();
			int numRows = Connections.rset.getRow();

			deptNames = new String[numRows];

			Connections.rset.beforeFirst();
			int i = 0;
			while (Connections.rset.next())
			{
				deptNames[i] = Connections.rset.getString("DEPARTMENTID").trim() + " - " + Connections.rset.getString("DEPARTMENTNAME").trim();
				i++;
			}
			Connections.killRset();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Object getElementAt(int index)
	{
		return deptNames[index];
	}

	@Override
	public int getSize()
	{
		return deptNames.length;

	}

	@Override
	public Object getSelectedItem()
	{
		return selection;
	}

	@Override
	public void setSelectedItem(Object anItem)
	{
		selection = (String) anItem;
	}
}
