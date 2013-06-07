package Auxilium.ListModels;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;

public class catListModel extends AbstractListModel
{

	Database db = new Database();
	private String[] catNames;
	public catListModel()
	{
	//	Connections.createConnection();
		db.getAllCategorys();

		try
		{
			Connections.rset.last();
			int numRows = Connections.rset.getRow();

			catNames = new String[numRows];

			Connections.rset.beforeFirst();
			int i = 0;
			while (Connections.rset.next())
			{
				catNames[i] = Connections.rset.getString("CATEGORYID").trim() + " - " +Connections.rset.getString("CATEGORYNAME").trim();
				i++;
			}
			
		//	Connections.killConnections();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Object getElementAt(int index)
	{
		return catNames[index];
	}

	@Override
	public int getSize()
	{
		return catNames.length;
	}
	
}
