package Auxilium.BackEnd;

import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

public class InstallDatabase
{
	/**
	 * This class is used to install the database.
	 * 
	 * @author Gavin Kenna
	 * @author Sean Lynch
	 * @author Jamie Blackbyrne @
	 */
	public static void main(String[] args)
	{
		

	}

	public static void createDepartmentTable()
	{
		//new Database();

		String createString = "CREATE TABLE Department "
				+ "(departmentID CHAR(10) PRIMARY KEY, departmentName CHAR(50), departmentDescription CHAR(1000), departmentEmail CHAR(100))";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			//Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println("Done");
		Connections.killRset();

	}

	/**
	 * This method creates the Ticket table in the Database.
	 **/
	public static void createTicketTable()
	{
		//new Database();

		String createString = "CREATE TABLE Ticket "
				+ "(ticketID CHAR(10) PRIMARY KEY, adminID CHAR(10),"
				+ "staffID CHAR(10),ticketTitle CHAR(150), "
				+ "ticketDescription CLOB,ticketPriority NUMBER,"
				+ "departmentID CHAR(10), dateCreated DATE, dateClosed DATE,"
				+ "statusID CHAR(10), categoryID CHAR(10))";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			//Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println("Done");
		Connections.killRset();
	}

	/**
	 * This method creates the Category table in the Database.
	 **/
	public static void createCategoryTable()
	{
		//new Database();

		String createString = "CREATE TABLE Category "
				+ "(categoryID CHAR(10) PRIMARY KEY,categoryName CHAR(50),"
				+ "categoryPriority NUMBER)";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println("Done");
		Connections.killRset();
	}

	/**
	 * This method creates the Admin table in the Database.
	 **/
	public static void createAdminTable()
	{
		//new Database();

		String createString = "CREATE TABLE Admin "
				+ "(adminID CHAR(10) PRIMARY KEY,"
				+ "password CHAR(30),firstName CHAR(30), "
				+ "lastName CHAR(30),email char(50),"
				+ "officeNumber NUMBER, mobileNumber NUMBER, canCreateTicket char(10),"
				+ "canEditTicket char(10), canCloseTicket char(10), canDeleteTicket char(10), "
				+ "canTransferTicket char(10))";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println("Done");
		Connections.killRset();
	}

	/**
	 * This method fills the Category table with predefined values.
	 * 
	 * @
	 **/
	public static void fillCategoryTable()
	{
		//new Database();
		Database.addCategory("Networking Issue", 3);
		Database.addCategory("Hardware Issue", 3);
		Database.addCategory("Intranet Issue", 4);
		Database.addCategory("Internet Issue", 2);
		Database.addCategory("Software Issue", 5);
		Database.addCategory("Misc Issue", 1);
		
		System.out.println("Done");
	}

	/**
	 * This method fills the Status table with predefined values.
	 * 
	 * @
	 **/
	public static void fillStatusTable()
	{
		//new Database();
		Database.addStatus("Closed");
		Database.addStatus("Open");
		Database.addStatus("Delegating-To-Other-Admin");
	}

	public static void fillDepartmentTable()
	{
		//new Database();
		Database.addDepartment("Marketing", "Everything Marketing related. Head of department is Brian May. Office Number is 01 232323",
				"Marketing@company.com");
		Database.addDepartment("IT", "Everything IT related. Head of department is Sarah Connor. Office Number is 01 232323", "IT@company.com");
		Database.addDepartment("Corporate", "Everything Corporate related. Head of department is Lucy OCarroll. Office Number is 01 975645",
				"Corporate@company.com");
		Database.addDepartment("Advertising", "Everything Advertising related. Head of department is Luke Malloy. Office Number is 01 53446",
				"Advertising@company.com");
		Database.addDepartment("Finance", "Everything Finance related. Head of department is Li Chan. Office Number is 01 652312",
				"Finance@company.com");
		Database.addDepartment("Computing", "Everything Computing related with the exception of IT (see IT Department). Head of department is Linda Peterson. Office Number is 01 873452",
				"Computing@company.com");
		Database.addDepartment("Law", "Everything Law related. Head of department is Tim McGraw. Office Number is 01 6534654",
				"Law@company.com");

	}

	/**
	 * This method creates the Staff table in the Database.
	 **/
	public static void createStaffTable()
	{
		//new Database();

		String createString = "CREATE TABLE Staff "
				+ "(staffID CHAR(20) PRIMARY KEY, departmentID CHAR(10),"
				+ "password CHAR(10),firstName CHAR(30), "
				+ "lastName CHAR(30),email char(50),"
				+ "officeNumber NUMBER, mobileNumber NUMBER, canCreateTicket char(10),"
				+ "canEditTicket char(10))";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println("Done");
		Connections.killRset();
	}

	/**
	 * This method creates the Log table in the Database.
	 **/
	public static void createLogTable()
	{
		//new Database();

		String createString = "CREATE TABLE Log "
				+ "(logID CHAR(10) PRIMARY KEY, ticketID CHAR(10),"
				+ "logDescription CHAR(220), logTitle CHAR(150), logDepartment CHAR(150), logDate Date)";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println("Done");
		Connections.killRset();
	}

	/**
	 * This method creates the Status table in the Database.
	 **/
	public static void createStatusTable()
	{
		//new Database();

		String createString = "CREATE TABLE Status "
				+ "(statusID CHAR(10) PRIMARY KEY, statusName CHAR(30))";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		Connections.killRset();
		System.out.println("Done");
	}

	public static void fillStaffTable(int j)
	{
		for (int i = 0; i < j; i++)
		{
			String firstName = randomFirstName();
			String lastName = randomLastName();
			Database.addStaff("pswd", firstName,  lastName,
					 firstName +"_"+lastName+"@staff.com", randomOfficeNumber(),
					randomMobileNumber(), randomDepartmentID(), true, true);
		}
		System.out.println("Added all new staff...");
	}

	public static void fillAdminTable()
	{
		for (int i = 0; i < 10; i++)
		{
			Database.addAdmin("pswd", randomFirstName(), randomLastName(),
					"email@email.com", randomOfficeNumber(),
					randomMobileNumber(), true, true, true, true, true);
		}
		System.out.println("Added all new Admins...");
	}
	public static void fillAdminTable(int x)
	{
		for (int i = 0; i < x; i++)
		{
			String firstName = randomFirstName();
			String lastName = randomLastName();
			Database.addAdmin("pswd", firstName, lastName,
					firstName + "_" + lastName+"@admin.com", randomOfficeNumber(),
					randomMobileNumber(), true, true, true, true, true);
			try
			{
				Connections.rset.close();
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Added all new Admins...");
		Connections.killRset();
	}

	private static String randomDepartmentID()
	{
		String randomDepartmentID = null;
		String select = "SELECT departmentID FROM ( SELECT departmentID FROM Department ORDER BY dbms_random.value ) WHERE rownum = 1";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			randomDepartmentID = Connections.rset.getNString(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();
			Connections.rset.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return randomDepartmentID;
	}

	private static int randomMobileNumber()
	{
		Random r = new Random();
		int randomPhone = r.nextInt(999999) + 4000000;
		return randomPhone;
	}

	private static int randomOfficeNumber()
	{
		Random r = new Random();
		int randomPhone = r.nextInt(999999) + 4000000;
		return randomPhone;
	}

	private static String randomLastName()
	{
		// TODO Auto-generated method stub
		String second[] =
		{
				"Stark", "Lannister", "Holmes", "Dent",
				"Ford", "Frey", "Potter", "O Reilly", "Hinch",
				"Parker", "Pound", "Rich", "Neeson", "O MacIrish", "Ireland",
				"Awesome", "VonDoom", "Barateron", "Tully",
				"Targaryeon", "Mormont", "Lynch",
				"Blackbyrne", "Kenna", "Blog", "Seaworth", "Greyjoy", "Arryn",
				"Wilson", "Adams", "Deschannel", "Squarepants", "Grimmes",
				"Cartman", "Marsh", "Mackee", "Griffin", "Smith", "Brown",
				"Baggins", "Gamgee", "Watson", "Legend", "Epic"
		};
		Random n = new Random();

		int r1 = n.nextInt(second.length);
		return second[r1];
	}

	private static String randomFirstName()
	{
		String first[] =
		{
				"Gavin", "Sean", "Jamie", "Bill", "Frank", "Sarah", "Joan",
				"Emma", "Andrew", "Linda", "Peter", "Rick", "Carl", "Shane",
				"Lori", "Davos", "Robb", "Bran", "Tyrion", "Ned", "Jon",
				"Arya", "Sansa", "Sherlock", "Arthur", "Harry", "Lisa",
				"Claire", "Deadpool", "Kevin", "Catelyn", "Theon", "Daenerys",
				"Samwell", "Robert", "Jaime", "Wade", "Douglas", "Trisha",
				"Zaphod", "Marvin", "Random", "Zooey", "Eric", "Stan", "Kyle",
				"Kenny", "Hazel", "Emily", "Lauren", "Jessica", "Sandra",
				"Cleveland", "Frodo", "Bilbo", "Sam", "Merry", "Pippin"
		};

		Random n = new Random();

		int r1 = n.nextInt(first.length);
		return first[r1];
	}
	
	public static void createKnowledgeBaseTable()
	{
		String createString = "CREATE TABLE KnowledgeBase "
				+ "(knowledgeID CHAR(10) PRIMARY KEY, title CHAR(50), description CLOB, categoryID CHAR(10), departmentID CHAR(10))";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(createString);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();
			//Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane
			.showMessageDialog(null,
					"Error " + e,
					"Table Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		System.out.println("Done");
		Connections.killRset();
	}
}
