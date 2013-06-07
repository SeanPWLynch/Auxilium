package Auxilium.UI;

import java.io.File;

import Auxilium.BackEnd.VALUES;

public class AuxiliumMain
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		boolean exists = (new File("sqlInfo.aux")).exists();
		if (exists) {
			
		   System.out.println("Exists");
		   Auxilium.BackEnd.SqlInformation sql = new Auxilium.BackEnd.SqlInformation();
		 
		   VALUES.PASSWORD = sql.getSqlPass();
		   VALUES.URL = sql.getSqlURL();
		   VALUES.USERNAME = sql.getSqlUser();
		   Auxilium.BackEnd.Connections.createConnection();
		 //  InstallDatabase.createKnowledgeBaseTable();
		   loginUI.main(null);
		} else {
			//Connections.createConnection();
		    InstallAuxilium.main(null);
		    
		}
	}
	
}
