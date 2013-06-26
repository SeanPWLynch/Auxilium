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
 * This class is a custom made table model to hold knowledge base logs.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class departmentTableModel extends AbstractTableModel
{

	private static Object[][] content;
	private static String[] colNames;

	public departmentTableModel()
	{
		try
		{
			//Connections.createConnection();
			Database.getAllDepartments();
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
	
	public static void filterRows(String ID, String title, String email)
	{
		//20/4/12 added new code here - Gavin
		
		try
		{
			String queryString = "SELECT * FROM Department  where DepartmentID like '"+ID.toUpperCase()+"%' and departmentname like '"+title+"%' and departmentemail like '"+email+"%'" ;
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
				System.out.println("No Departments in system");
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
