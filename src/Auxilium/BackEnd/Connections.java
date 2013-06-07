package Auxilium.BackEnd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import oracle.jdbc.pool.OracleDataSource;

public class Connections
{
	public static ResultSet rset;
	public static Connection conn;
	public static PreparedStatement pstmt;
	public static ResultSetMetaData md;

	public static int AMOUNT_OF_CONNECTIONS;
	
	public static void createConnection()
	{
		
		AMOUNT_OF_CONNECTIONS++;
		System.out.println("AMOUNT_OF_CONNECTIONS : " +AMOUNT_OF_CONNECTIONS);
		try
		{
			
			OracleDataSource ods = new OracleDataSource();

			ods.setURL(VALUES.URL);
			ods.setUser(VALUES.USERNAME);
			ods.setPassword(VALUES.PASSWORD);
			

			conn = ods.getConnection();
			conn.setAutoCommit(true);

		} catch (SQLException e)
		{
			System.out.print("Could not connect " + e);
			JOptionPane
			.showMessageDialog(null,
					"Could not connect :"+ e,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}catch(Exception e2)
		{
			JOptionPane
			.showMessageDialog(null,
					"Could not connect :"+ e2,
					"Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public static void killConnections()
	{
		System.out.println("AMOUNT_OF_CONNECTIONS : " +AMOUNT_OF_CONNECTIONS);
		try
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if (rset != null)
			{
				try
				{
					rset.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			
		} finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if (rset != null)
			{
				try
				{
					rset.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static void killRset()
	{
		//System.out.println("Killing rset...");
		try
		{
			
			if (rset != null)
			{
				try
				{
					rset.close();
					if(rset.getStatement()!= null)
					{
						rset.getStatement().close();
					}
					
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			
		} finally
		{
			
			if (rset != null)
			{
				try
				{
					rset.close();
					if(rset.getStatement()!= null)
					{
						rset.getStatement().close();
					}
					
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if (pstmt != null)
			{
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			
		}
	}

	public static void countCursors()
	{
		String select = "select s.username, a.sid, a.value from v$sesstat a, v$statname b, v$session s where a.statistic# = b.statistic# And username = 'SYSTEM' and s.sid (+)= a.sid and b.name = 'opened cursors current' --and s.sid = (select sid from v$mystat where rownum = 1)";
		try
        {
	        pstmt = conn.prepareStatement(select);
	        rset = pstmt.executeQuery();
        } catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		try
        {
	        if (rset.next() == true)
	        {
	        	System.out.println("Open cursors : "+ rset.getInt("value"));
	        }
        } catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		try
        {
	        Connections.rset.close();
	        Connections.pstmt.close();
	        Connections.rset.getStatement().close();
        } catch (SQLException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		
		Connections.killRset();
		
	}
}
