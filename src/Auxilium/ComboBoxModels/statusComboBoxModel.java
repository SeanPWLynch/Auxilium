package Auxilium.ComboBoxModels;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;

public class statusComboBoxModel extends AbstractListModel implements
		ComboBoxModel
{

//	Database db = new Database();
	private String[] statusNames;
	String selection = null;

	public statusComboBoxModel()
	{
	//	Connections.createConnection();
		Database.getAllStatus();

		try
		{
			Connections.rset.last();
			int numRows = Connections.rset.getRow();

			statusNames = new String[numRows];

			Connections.rset.beforeFirst();
			int i = 0;
			while (Connections.rset.next())
			{
				String currentStatus = null;
				currentStatus = Connections.rset.getString("STATUSID").trim() + " - " + Connections.rset.getString("STATUSNAME").trim();
				statusNames[i] = currentStatus;
				i++;
			}
			Connections.killRset();

		} catch (SQLException e)
		{
			// TODO Auto-generated statusch block
			e.printStackTrace();
		}

	}

	@Override
	public Object getElementAt(int index)
	{
		return statusNames[index];
	}

	@Override
	public int getSize()
	{
		return statusNames.length;

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
