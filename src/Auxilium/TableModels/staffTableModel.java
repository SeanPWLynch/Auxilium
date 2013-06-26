package Auxilium.TableModels;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;

/**
 * This class is a custom made table model to hold all staff members.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class staffTableModel extends AbstractTableModel
{

	private static Object[][] content;
	private static String[] colNames;

	public staffTableModel()
	{
		try
		{
			//Connections.createConnection();
			Database.getAllStaff();
			content = getTableContent();
			colNames = getTableColumnNames();
			Connections.killRset();
		} catch (SQLException sqx)
		{
			content = new Object[][]
			{
				{
					""
				}
			};
			colNames = new String[]
			{
				"Error"
			};
			System.err.println("Could not pull data from database");
		}
	}

	public int getColumnCount()
	{
		return colNames.length;
	}

	public int getRowCount()
	{
		return content.length;
	}

	public Object getValueAt(int arg0, int arg1)
	{
		return content[arg0][arg1];
	}

	public boolean isCellEditable(int row, int col)
	{
		return false;
	}

	public void setValueAt(Object aValue, int row, int col)
	{
		content[row][col] = aValue;
	}

	public String getColumnName(int col)
	{
		return colNames[col];
	}

	public static String[] getTableColumnClasses() throws SQLException
	{

		String[] colClasses = new String[Connections.md.getColumnCount()];

		for (int i = 0; i < Connections.md.getColumnCount(); i++)
		{
			colClasses[i] = Connections.md.getColumnClassName(i + 1);
		}
		return colClasses;
	}

	public static String[] getTableColumnNames() throws SQLException
	{

		String[] colNames = new String[Connections.md.getColumnCount()];

		for (int i = 0; i < Connections.md.getColumnCount(); i++)
		{
			colNames[i] = Connections.md.getColumnName(i + 1);
		}

		return colNames;
	}

	public static Object[][] getTableContent() throws SQLException
	{
		String[] colNames = getTableColumnNames();
		String[] colClasses = getTableColumnClasses();

		ArrayList<Object[]> rowList = new ArrayList<Object[]>();
		while (Connections.rset.next())
		{
			ArrayList<Object> cellList = new ArrayList<Object>();
			for (int i = 0; i < colClasses.length; i++)
			{
				Object cellValue = null;

				cellValue = Connections.rset.getString(colNames[i]).trim();
				
				cellList.add(cellValue);
			}
			Object[] cells = cellList.toArray();
			rowList.add(cells);
		}

		Object[][] content = new Object[rowList.size()][];
		for (int i = 0; i < content.length; i++)
		{
			content[i] = rowList.get(i);
		}

		return content;
	}
	public static void filterRows(String ID, String name, String email, String departmentID)
	{
		//21/4/12 added new code here - Gavin
		
		try
		{
			String queryString = ("SELECT STAFFID, FIRSTNAME, LASTNAME, EMAIL, OFFICENUMBER, " +
					"MOBILENUMBER, DepartmentId Dept  FROM Staff WHERE staffid like '%"+ID+"%'" +
					"AND concat(firstname, lastname) like '%"+name+"%' " +
					"AND email like '%"+email+"%' " +
					"AND departmentID like '%"+departmentID+"%'"
			        + "UNION ALL "
			        + "SELECT ADMINID , FIRSTNAME, LASTNAME, EMAIL,  " +
			        "OFFICENUMBER, MOBILENUMBER, '<null>' Dept FROM Admin " +
			        "WHERE adminID like '%"+ID+"%' " +
			        "AND concat(firstname, lastname) like '%"+name+"%' " +
					"AND email like '%"+email+"%' " +
					"");
			System.out.println(queryString);
			try
			{
				
				Connections.pstmt = Connections.conn
				        .prepareStatement(queryString,
				                ResultSet.TYPE_SCROLL_SENSITIVE,
				                ResultSet.CONCUR_UPDATABLE);
				
				Connections.rset = Connections.pstmt.executeQuery();
				
				Connections.md = Connections.rset.getMetaData();
				
			} catch (SQLException e)
			{
				System.out.println("No Staff in system");
				e.printStackTrace();
			}
			
			colNames = getTableColumnNames();
			content = getTableContent();
			Connections.killRset();
		} catch (SQLException sqx)
		{
			content = new Object[][]
			{
				{
					""
				}
			};
			colNames = new String[]
			{
				"Error"
			};
			System.err.println("Could not pull data from database");
		}
		
	}

	
}
