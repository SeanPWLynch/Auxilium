package Auxilium.BackEnd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class is used to make a file which holds the database sql log in details.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class SqlInformation implements Serializable
{
	/**
	 * 
	 */
	private String sqlUser, sqlPass, sqlURL;
	
	public SqlInformation(String sqlUser, char[] cs, String sqlURL)
	{
		this.setSqlPass( cs);
		this.setSqlURL(sqlURL);
		this.setSqlUser(sqlUser);
	}
	public SqlInformation()
	{
		readData();
	}
	
	public String getSqlURL()
	{
		return this.sqlURL;
	}
	
	private void setSqlURL(String sqlURL)
    {
	   this.sqlURL = sqlURL;
    }
	public String getSqlPass()
	{
		return this.sqlPass;
	}
	private void setSqlPass(char[] cs)
    {
		
			this.sqlPass = String.copyValueOf(cs);
		
	    
    }

	public String getSqlUser()
	{
		return sqlUser;
	}
	
	public void setSqlUser(String sqlUser)
	{
		this.sqlUser = sqlUser;
	}


	public void writeData()
	{
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("sqlInfo.aux");
	         ObjectOutputStream out =
	                            new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		VALUES.PASSWORD = this.getSqlPass();
		VALUES.URL = this.getSqlURL();
		VALUES.USERNAME = this.getSqlUser();
	
	}
	private void readData()
	{
		SqlInformation s;
		try
        {
           FileInputStream fileIn =
                         new FileInputStream("sqlInfo.aux");
           ObjectInputStream in = new ObjectInputStream(fileIn);
           s = (SqlInformation) in.readObject();
           in.close();
           fileIn.close();
       }catch(IOException i)
       {
           i.printStackTrace();
           return;
       }catch(ClassNotFoundException c)
       {
           c.printStackTrace();
           return;
       }
		this.setSqlPass(s.sqlPass.toCharArray());
		this.setSqlURL(s.getSqlURL());
		this.setSqlUser(s.getSqlUser());
	}
}
