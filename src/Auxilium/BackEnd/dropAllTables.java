package Auxilium.BackEnd;

import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;

/**
 * This class is used for basically reseting the database, to delete all data.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class dropAllTables
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			OracleDataSource ods = new OracleDataSource();

			ods.setURL(VALUES.URL);
			ods.setUser(VALUES.USERNAME);
			ods.setPassword(VALUES.PASSWORD);
			Connection conn = ods.getConnection();

			System.out.println("Connecting...");

			System.out.println("Connected.");

			PreparedStatement pstmt;

			
			String dropString = "DROP TABLE Admin";

			
			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}
			

			System.out.println("Admin Table Dropped");

			
			dropString = "DROP TABLE Staff";

			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}
			
			System.out.println("Staff Table Dropped");

			
			dropString = "DROP TABLE Category";

			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}

			System.out.println("Category Table Dropped");

			dropString = "DROP TABLE Department";

			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}

			System.out.println("Department Table Dropped");
			
			dropString = "DROP TABLE Status";

			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}

			System.out.println("Status Table Dropped");
			
			
			dropString = "DROP TABLE Ticket";

			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}

			System.out.println("Ticket Table Dropped");
			
			dropString = "DROP TABLE Log";

			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}

			System.out.println("Log Table Dropped");
			
			dropString = "DROP TABLE KnowledgeBase";

			pstmt = conn.prepareStatement(dropString);

			try
			{
				pstmt.executeUpdate();
			}catch (SQLException e)
			{
				
			}
			
			System.out.println("Knowledge Table Dropped");
			
			conn.commit();
			pstmt.close();
			
			System.out.println("Finished");
			conn.close();

		} catch (SQLException e)
		{
			System.out.print("Could not connect " + e);
			System.exit(1);
		}

	}
}
