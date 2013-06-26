package Auxilium.ComboBoxModels;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;

/**
 * This class is a custom made combo box to hold tickets.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */


public class ticketComboBoxModel extends AbstractListModel implements
		ComboBoxModel
{

//	Database db = new Database();
	private static String[] ticketNames;
	String selection = null;

	public ticketComboBoxModel()
	{
	//	Connections.createConnection();
		reload();

	}

	@Override
	public Object getElementAt(int index)
	{
		return ticketNames[index];
	}

	@Override
	public int getSize()
	{
		return ticketNames.length;

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

	public static void reload()
	{
		Database.getAllTickets();

		try
		{
			Connections.rset.last();
			int numRows = Connections.rset.getRow();

			ticketNames = new String[numRows];

			Connections.rset.beforeFirst();
			int i = 0;
			while (Connections.rset.next())
			{
				String currentStatus = null;
				currentStatus = Connections.rset.getString("TicketID").trim() + " - " + Connections.rset.getString("TicketTitle").trim();
				ticketNames[i] = currentStatus;
				i++;
			}
			Connections.killRset();

		} catch (SQLException e)
		{
			// TODO Auto-generated ticketch block
			e.printStackTrace();
		}

	}
}


