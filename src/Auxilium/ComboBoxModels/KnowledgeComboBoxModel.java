package Auxilium.ComboBoxModels;

import java.sql.SQLException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;


/**
 * This class is a custom made combo box to hold knowledge base values.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class KnowledgeComboBoxModel extends AbstractListModel implements
		ComboBoxModel
{

//	Database db = new Database();
	private String[] knowledgeNames;
	String selection = null;

	public KnowledgeComboBoxModel()
	{
	//	Connections.createConnection();
		Database.getAllKnowledge();

		try
		{
			Connections.rset.last();
			int numRows = Connections.rset.getRow();

			knowledgeNames = new String[numRows];

			Connections.rset.beforeFirst();
			int i = 0;
			while (Connections.rset.next())
			{
				String currentStatus = null;
				currentStatus = Connections.rset.getString("KnowledgeID").trim() + " - " + Connections.rset.getString("Title").trim();
				knowledgeNames[i] = currentStatus;
				i++;
			}
			Connections.killRset();

		} catch (SQLException e)
		{
			// TODO Auto-generated statusch block
			e.printStackTrace();
		}
		
		Connections.killRset();

	}

	@Override
	public Object getElementAt(int index)
	{
		return knowledgeNames[index];
	}

	@Override
	public int getSize()
	{
		return knowledgeNames.length;

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
