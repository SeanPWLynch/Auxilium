package Auxilium.ComboBoxModels;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;

public class categoryComboBoxModel extends AbstractListModel implements
		ComboBoxModel
{

	//Database db = new Database();
	private String[] catNames;
	String selection = null;

	public categoryComboBoxModel()
	{
	//	Connections.createConnection();
		Database.getAllCategorys();

		try
		{
			Connections.rset.last();
			int numRows = Connections.rset.getRow();

			catNames = new String[numRows];

			Connections.rset.beforeFirst();
			int i = 0;
			while (Connections.rset.next())
			{
				String currentCat = null;
				currentCat = Connections.rset.getString("CATEGORYID").trim() + " - " + Connections.rset.getString("CATEGORYNAME").trim();
				catNames[i] = currentCat;
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
		return catNames[index];
	}

	@Override
	public int getSize()
	{
		return catNames.length;

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
