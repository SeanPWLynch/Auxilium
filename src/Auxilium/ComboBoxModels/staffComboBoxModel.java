package Auxilium.ComboBoxModels;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;

/**
 * This class is a custom made combo box to hold staff users.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class staffComboBoxModel extends AbstractListModel implements
		ComboBoxModel
{

	//Database db = new Database();
	private String[] staffNames;
	String selection = null;

	public staffComboBoxModel()
	{
	//	Connections.createConnection();
		Database.getAllStaff();

		try
		{
			Connections.rset.last();
			int numRows = Connections.rset.getRow();

			staffNames = new String[numRows];

			Connections.rset.beforeFirst();
			int i = 0;
			while (Connections.rset.next())
			{
				String nameID = null;
				nameID = Connections.rset.getString("STAFFID").trim() + " - " + Connections.rset.getString("FIRSTNAME").trim() + " " + Connections.rset.getString("LASTNAME").trim();
				staffNames[i] = nameID;
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
		return staffNames[index];
	}

	@Override
	public int getSize()
	{
		return staffNames.length;

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
