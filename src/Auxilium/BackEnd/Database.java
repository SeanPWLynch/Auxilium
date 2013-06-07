package Auxilium.BackEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import Auxilium.BackEnd.Connections;
import Auxilium.UI.adminUI;

/**
 * This class is used to make connections to the database, as well as making
 * changes to it, such as adding, editing and deleting.
 * 
 * @author Gavin Kenna
 * @author Sean Lynch
 * @author Jamie Blackbyrne @
 */

public class Database // implements VALUES
{

	public static void main(String[] args)
	{

	}

	/**
	 * This method creates a new ticket and adds it to the database.
	 * 
	 * @param staffID
	 *            -> The ID of the user who created this ticket.
	 * @param ticketTitle
	 *            -> The name of the ticket, decided by the user.
	 * @param ticketDescription
	 *            -> This describes the ticket, and what issue the user is
	 *            experiencing.
	 * @param departmentID
	 *            -> Department in which the Ticket is from.
	 * @param statusID
	 *            -> This shows the current status of the ticket (i.e.,
	 *            opened,closed, on-going, problems solving ticket problem,
	 *            etc).
	 * @param categoryID
	 *            -> What category the ticket falls under (i.e., Networking
	 *            Issues, Printer Issues, Monitor Issues, etc).
	 * 
	 */
	public static void addTicket(String staffID, String ticketTitle,
			String ticketDescription, String departmentID, String statusID,
			String categoryID, String staffEmail)
	{

		String adminID = assignToAdmin();
		Connections.killRset();
		String dateCreated = getDate();
		int ticketPriority = getCategoryPriority(categoryID);
		Connections.killRset();
		boolean random = false;
		String ticketID = null;

		while (random == false)
		{
			Connections.killRset();
			ticketID = ("T" + String.valueOf(getRandomID()));
			try
			{

				String select = "SELECT * FROM Ticket WHERE ticketID = '"
						+ ticketID + "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}
				Connections.killRset();
			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally
			{
				Connections.killRset();
			}
			Connections.killRset();
		}

		try
		{

			String insertString = "INSERT INTO Ticket(ticketID, adminID, staffID, ticketTitle, ticketDescription, ticketPriority, departmentID, dateCreated, dateClosed, statusID, categoryID) values('"
					+ ticketID
					+ "','"
					+ adminID
					+ "','"
					+ staffID
					+ "','"
					+ ticketTitle
					+ "','"
					+ ticketDescription
					+ "', "
					+ ticketPriority
					+ " , '"
					+ departmentID
					+ "','"
					+ dateCreated.trim()
					+ "',?,'"
					+ statusID.trim()
					+ "','"
					+ categoryID.trim() + "')";
			System.out.println(insertString);
			Connections.pstmt = Connections.conn.prepareStatement(insertString);
			Connections.pstmt.setString(1, null);
			Connections.pstmt.executeUpdate();
			Connections.conn.commit();

			addLog(ticketID, "New Ticket : " + ticketTitle.trim()
					+ ", Issued to : " + getAdminName(adminID).trim(),
					ticketTitle, departmentID);
			Connections.killRset();
			System.out.println("Ticket Added");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		Database.postNewTicketToAdmin(ticketID);
	}

	/**
	 * This method edits a ticket and saves the changes to the database
	 * 
	 * @param ticketID
	 *            -> The ID of the ticket to edit.
	 * @param staffID
	 *            -> The ID of the user who created this ticket.
	 * @param ticketTitle
	 *            -> The name of the ticket, decided by the user.
	 * @param ticketDescription
	 *            -> This describes the ticket, and what issue the user is
	 *            experiencing.
	 * @param departmentID
	 *            -> Department in which the Ticket is from.
	 * @param statusID
	 *            -> This shows the current status of the ticket (i.e.,
	 *            opened,closed, on-going, problems solving ticket problem,
	 *            etc).
	 * @param categoryID
	 *            -> What category the ticket falls under (i.e., Networking
	 *            Issues, Printer Issues, Monitor Issues, etc). @
	 * 
	 */
	public static void editTicket(String ticketID, String adminID,
			String staffID, String ticketTitle, String ticketDescription,
			int ticketPriority, String departmentID, String dateClosed,
			String statusID, String categoryID)
	{

		try
		{

			String update = "UPDATE ticket SET adminID = '" + adminID
					+ "', staffID = '" + staffID + "',ticketTitle = '"
					+ ticketTitle + "'," + "ticketDescription = '"
					+ ticketDescription + "',ticketPriority = '"
					+ ticketPriority + "',departmentID = '" + departmentID
					+ "',dateClosed = ?, " + "statusID = '" + statusID
					+ "',categoryID = '" + categoryID + "' WHERE ticketID = '"
					+ ticketID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(update);
			Connections.pstmt.setString(1, dateClosed);
			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			Connections.killRset();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method deletes a ticket from the database.
	 * 
	 * @param ticketID
	 *            -> The ID of the ticket in which you would like deleted. @
	 * 
	 */
	public static void deleteTicket(String ticketID)
	{

		try
		{

			String delete = "DELETE Ticket WHERE ticketID = '" + ticketID
					+ "' ";

			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method creates a new staff member. A staff member reports problems
	 * in the form of Tickets, and sends them on to the Admins.
	 * 
	 * @param password
	 *            -> The ID of the user, to login.
	 * @param firstName
	 *            -> First name of the staff member.
	 * @param lastName
	 *            -> Last name of the member.
	 * @param email
	 *            -> The staff members email address.
	 * @param officeNumber
	 *            -> Office phone number of the user.
	 * @param mobileNumber
	 *            -> Mobile phone number of the user.
	 * @param departmentID
	 *            -> Department in which the staff member is from. This will be
	 *            used for when the staff member creates a Ticket.
	 * @param canCreateTicket
	 *            -> This boolean decides if the staff member can create a
	 *            Ticket.
	 * @param canEditTicket
	 *            -> This boolean decides if the staff member can edit their
	 *            created Tickets. @
	 */
	public static void addStaff(String password, String firstName,
			String lastName, String email, int officeNumber, int mobileNumber,
			String departmentID, boolean canCreateTicket, boolean canEditTicket)

	{
		// Connections.createConnection();
		boolean random = false;
		String staffID = null;

		do
		{
			try
			{
				if (Connections.rset != null)
				{
					// Connections.killRset();
				}
				if (Connections.pstmt != null)
				{
					// Connections.pstmt.clearBatch();
					Connections.pstmt.close();
				}

			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}

			staffID = ("U" + String.valueOf(getRandomID())).trim();
			try
			{

				String select = "SELECT * FROM Staff WHERE staffID = '"
						+ staffID + "'";
				Connections.pstmt = Connections.conn.prepareStatement(
						select.trim(), ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);

				Connections.rset = Connections.pstmt.executeQuery();
				// Connections.rset.next();
				if (Connections.rset.next() == false)
				{
					random = true;
				} else
				{
					random = false;
				}

				// Connections.killRset();
				Connections.pstmt.clearBatch();
				Connections.pstmt.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		} while (random == false);
		try
		{

			String insertString = "INSERT INTO staff(staffID, departmentID, password, firstName, lastName, email, officeNumber, mobileNumber, canCreateTicket, canEditTicket) values('"
					+ staffID
					+ "' , '"
					+ departmentID.trim()
					+ "' , '"
					+ password
					+ "' , '"
					+ firstName
					+ "' , '"
					+ lastName.replace('\'', ' ') // For surnames such as
													// O'Reilly
					+ "' , '"
					+ email
					+ "' , "
					+ officeNumber
					+ " , "
					+ mobileNumber
					+ " , '"
					+ canCreateTicket
					+ "' , '"
					+ canEditTicket + "')";
			insertString = insertString.trim();
			System.out.println(insertString);
			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

			// Connections.killRset();
			Connections.pstmt.clearBatch();
			Connections.pstmt.close();
			// Connections.killConnections();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method edits a Staff member and saves the changes to the database
	 * 
	 * @param staffID
	 *            -> The ID of the ticket to edit.
	 * @param password
	 *            -> The ID of the user, to login.
	 * @param firstName
	 *            -> First name of the staff member.
	 * @param lastName
	 *            -> Last name of the member.
	 * @param email
	 *            -> The staff members email address.
	 * @param officeNumber
	 *            -> Office phone number of the user.
	 * @param mobileNumber
	 *            -> Mobile phone number of the user.
	 * @param departmentID
	 *            -> Department in which the staff member is from. This will be
	 *            used for when the staff member creates a Ticket.
	 * @param canCreateTicket
	 *            -> This boolean decides if the staff member can create a
	 *            Ticket.
	 * @param canEditTicket
	 *            -> This boolean decides if the staff member can edit their
	 *            created Tickets. @
	 */
	public static void editStaff(String staffID, String password,
			String firstName, String lastName, String email, int officeNumber,
			int mobileNumber, String departmentID, boolean canCreateTicket,
			boolean canEditTicket)
	{

		try
		{

			String getStudent = "UPDATE staff SET password = '" + password
					+ "', firstName = '" + firstName + "',lastName = '"
					+ lastName + "','" + "' email = '" + email
					+ "',officeNumber = " + officeNumber + ",mobileNumber = "
					+ mobileNumber + ",departmentID = '" + departmentID + "',"
					+ "canCreateTicket = " + canCreateTicket
					+ ",canEditTicket = " + canEditTicket
					+ ", WHERE staffID = '" + staffID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(getStudent);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method deletes a choosen Staff member from the database.
	 * 
	 * @param staffID
	 *            -> The ID of the Staff member you would like to delete. @
	 */
	public static void deleteStaff(String staffID)
	{

		try
		{

			String delete = "DELETE Staff WHERE StaffID = '" + staffID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

			Connections.killRset();

			// Now also delete all tickets created by this user:

			delete = "DELETE Ticket where StaffID = '" + staffID + "'";
			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method creates a new admin member. An admin member can
	 * create/edit/delete users, departments, categories, statuses and solve
	 * tickets, .
	 * 
	 * @param password
	 *            -> The password for the admin to login.
	 * @param firstName
	 *            -> First name of the admin member.
	 * @param lastName
	 *            -> Last name of the admin.
	 * @param email
	 *            -> The admin members email address.
	 * @param officeNumber
	 *            -> Office phone number of the user.
	 * @param mobileNumber
	 *            -> Mobile phone number of the user.
	 * @param departmentID
	 *            -> Department in which the admin member is from. This will
	 *            decide which admins recieve what tickets.
	 * @param canCreateTicket
	 *            -> This boolean decides if the admin member can create a
	 *            Ticket.
	 * @param canEditTicket
	 *            -> This boolean decides if the admin member can edit Tickets.
	 * @param canCloseTicket
	 *            -> This boolean shows if the admin can close Tickets.
	 * @param canDeleteTicket
	 *            -> This boolean shows if the admin can delete Tickets.
	 * @param canTransferTicket
	 *            -> This boolean shows if the admin can transfer Tickets to
	 *            other admins. @
	 */
	public static void addAdmin(String password, String firstName,
			String lastName, String email, int officeNumber, int mobileNumber,
			boolean canCreateTicket, boolean canEditTicket,
			boolean canCloseTicket, boolean canDeleteTicket,
			boolean canTransferTicket)

	{
		// Connections.createConnection();
		boolean random = false;
		String adminID = null;

		while (random == false)
		{
			adminID = ("A" + String.valueOf(getRandomID()));
			try
			{

				String select = "SELECT * FROM Admin WHERE adminID = '"
						+ adminID.trim() + "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
		try
		{

			String insertString = "INSERT INTO Admin(adminID, password, firstName, lastName, email, officeNumber, mobileNumber, canCreateTicket, canEditTicket, canCloseTicket, canDeleteTicket, canTransferTicket) values('"
					+ adminID.trim()
					+ "','"
					+ password.trim()
					+ "','"
					+ firstName.trim()
					+ "','"
					+ lastName.trim().replace('\'', ' ')
					+ "','"
					+ email.trim()
					+ "',"
					+ officeNumber
					+ ","
					+ mobileNumber
					+ ",'"
					+ canCreateTicket
					+ "','"
					+ canEditTicket
					+ "','"
					+ canCloseTicket
					+ "','"
					+ canDeleteTicket
					+ "','"
					+ canTransferTicket + "')";
			System.out.println(insertString);
			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			// Connections.killConnections();
			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	public static void addAdmin(String userName, String password,
			String firstName, String lastName, String email, int officeNumber,
			int mobileNumber, boolean canCreateTicket, boolean canEditTicket,
			boolean canCloseTicket, boolean canDeleteTicket,
			boolean canTransferTicket)

	{
		// Connections.createConnection();
		boolean random = false;
		String adminID = null;

		while (random == false)
		{
			adminID = (userName);
			try
			{

				String select = "SELECT * FROM Admin WHERE adminID = '"
						+ adminID.trim() + "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
		try
		{

			String insertString = "INSERT INTO Admin(adminID, password, firstName, lastName, email, officeNumber, mobileNumber, canCreateTicket, canEditTicket, canCloseTicket, canDeleteTicket, canTransferTicket) values('"
					+ adminID.trim()
					+ "','"
					+ password.trim()
					+ "','"
					+ firstName.trim()
					+ "','"
					+ lastName.trim().replace('\'', ' ')
					+ "','"
					+ email.trim()
					+ "',"
					+ officeNumber
					+ ","
					+ mobileNumber
					+ ",'"
					+ canCreateTicket
					+ "','"
					+ canEditTicket
					+ "','"
					+ canCloseTicket
					+ "','"
					+ canDeleteTicket
					+ "','"
					+ canTransferTicket + "')";
			System.out.println(insertString);
			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			// Connections.killConnections();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method edits an Admin member and saves the changes to the database
	 * 
	 * @param adminID
	 *            -> The ID of the Admin to edit.
	 * @param password
	 *            -> The password for the admin to login.
	 * @param firstName
	 *            -> First name of the admin member.
	 * @param lastName
	 *            -> Last name of the admin.
	 * @param email
	 *            -> The admin members email address.
	 * @param officeNumber
	 *            -> Office phone number of the user.
	 * @param mobileNumber
	 *            -> Mobile phone number of the user.
	 * @param departmentID
	 *            -> Department in which the admin member is from. This will
	 *            decide which admins recieve what tickets.
	 */
	public static void editAdmin(String adminID, String password,
			String firstName, String lastName, String email, int officeNumber,
			int mobileNumber)

	{

		try
		{
			// Connections.createConnection();

			String editAdmin = "UPDATE ADMIN SET PASSWORD = '"
					+ password.trim() + "', FIRSTNAME = '" + firstName.trim()
					+ "', LASTNAME = '" + lastName.trim() + "', " + "EMAIL = '"
					+ email.trim() + "', OFFICENUMBER = '" + officeNumber
					+ "', MOBILENUMBER = '" + mobileNumber
					+ "' WHERE adminID = '" + adminID.trim() + "'";

			Connections.pstmt = Connections.conn.prepareStatement(editAdmin);

			Connections.pstmt.executeUpdate(editAdmin);

			Connections.conn.commit();
			// Connections.killConnections();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method deletes a choosen Admin from the database.
	 * 
	 * @param adminID
	 *            -> The ID of the Admin you would like to delete. @
	 */
	public static void deleteAdmin(String adminID)
	{

		try
		{

			String delete = "DELETE Admin WHERE AdminID = '" + adminID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

			// Assign another admin to the deleted admins tickets

			String getTickets = ("SELECT ticketID  FROM Ticket WHERE AdminId = '"
					+ adminID + "' AND DateClosed is null");

			Connections.pstmt = Connections.conn
					.prepareStatement(getTickets,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();

			Connections.md = Connections.rset.getMetaData();
			Connections.rset.beforeFirst();
			ResultSet r2 = Connections.rset;
			while (r2.next())
			{
				String ticketID = null;
				ticketID = r2.getString("TicketID").trim();
				Database.transferTicket(ticketID, assignToAdmin());
			}
			r2.close();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("Deleted admin :" + adminID);
		Connections.killRset();
	}

	/**
	 * This method creates a new Department. Departments are used to organise
	 * Admins and Staff members.
	 * 
	 * @param departmentName
	 *            -> The name of the Department (i.e, Marketing, IT). @
	 */
	public static void addDepartment(String departmentName,
			String departmentDescription, String departmentEmail)
	{
		// Connections.createConnection();
		boolean random = false;
		String departmentID = null;

		while (random == false)
		{
			departmentID = ("D" + String.valueOf(getRandomID()));
			try
			{

				String select = "SELECT * FROM Department WHERE departmentID = '"
						+ departmentID + "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
		try
		{

			String insertString = "INSERT INTO department(departmentID, departmentName, departmentDescription, departmentEmail) values('"
					+ departmentID
					+ "','"
					+ departmentName
					+ "','"
					+ departmentDescription + "','" + departmentEmail + "')";

			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			// Connections.killRset();
			// Connections.killConnections();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method edits a Department and saves the changes to the database
	 * 
	 * @param departmentID
	 *            -> The ID of the Department to edit.
	 * @param departmentName
	 *            -> The name of the Department. @
	 * @param departmentDescription
	 *            -> The description of the Department. @
	 * @param departmentEmail
	 *            -> The email of the Department. @
	 **/
	public static void editDepartment(String departmentID,
			String departmentName, String departmentEmail,
			String departmentDescription)

	{
		// Connections.createConnection();
		try
		{

			String editDept = "UPDATE department SET departmentName = '"
					+ departmentName + "' ,departmentEmail = '"
					+ departmentEmail + "',departmentDescription = '"
					+ departmentDescription + "' WHERE departmentID = '"
					+ departmentID + "'";

			Connections.pstmt = Connections.conn.prepareStatement(editDept);

			Connections.pstmt.executeUpdate(editDept);

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method deletes a choosen Department from the database.
	 * 
	 * @param departmentID
	 *            -> The ID of the Department you would like to delete. @
	 */
	public static void deleteDepartment(String departmentID)

	{

		try
		{

			String delete = "DELETE Department WHERE DepartmentID = '"
					+ departmentID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

			// Assign people of this dept to a placeholder called 'noDept'

			String noDeptID = null;
			String select = "SELECT * FROM Department WHERE DepartmentName = 'noDept'";
			Connections.pstmt = Connections.conn.prepareStatement(select);

			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				noDeptID = Connections.rset.getString("departmentID").trim();
			} else
			{
				Database.addDepartment(
						"noDept",
						"This is a place holder for empyees not yet assigned a department ",
						"blank");
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();
				Connections.rset.next();
				noDeptID = Connections.rset.getNString("DepartmentID");
			}

			// Update everyone now

			String update = "Update Staff SET DepartmentId ='" + noDeptID
					+ "' WHERE DepartmentID = '" + departmentID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(update);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

			update = "Update Ticket SET DepartmentId ='" + noDeptID
					+ "' WHERE DepartmentID = '" + departmentID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(update);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method creates a new Category. Categories are used to organise
	 * Tickets, such as Networking Issues, Monitor Issues, etc. From this, it
	 * will decide what priority the Ticket will be.
	 * 
	 * @param categoryName
	 *            -> The name of the Category, i.e. Networking Issues, Printer
	 *            Issues.
	 * @param categoryPriority
	 *            -> The priority of this Category, i.e Networking would have a
	 *            higher priority than Slight Monitor Issues. Range of priority
	 *            is 1-5. @
	 */
	public static void addCategory(String categoryName, int categoryPriority)

	{
		// //Connections.createConnection();
		boolean random = false;
		String categoryID = null;

		while (random == false)
		{
			categoryID = ("C" + String.valueOf(getRandomID()));
			try
			{

				String select = "SELECT * FROM Category WHERE categoryID = '"
						+ categoryID + "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
		try
		{

			String insertString = "INSERT INTO Category(categoryID,categoryName,categoryPriority) values('"
					+ categoryID
					+ "','"
					+ categoryName
					+ "','"
					+ categoryPriority + "')";

			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			// //Connections.killConnections();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method edits a Category and saves the changes to the database
	 * 
	 * @param categoryID
	 *            -> The ID of the Category to edit.
	 * @param categoryName
	 *            -> The name of the Category, i.e. Networking Issues, Printer
	 *            Issues.
	 * @param categoryPriority
	 *            -> The priority of this Category, i.e Networking would have a
	 *            higher priority than Slight Monitor Issues. Range of priority
	 *            is 1-5 @
	 * 
	 * 
	 **/
	public static void editCategory(String categoryID, String categoryName,
			int categoryPriority)
	{

		try
		{
			// //Connections.createConnection();

			String editCat = "UPDATE CATEGORY SET CATEGORYNAME ='"
					+ categoryName + "', CATEGORYPRIORITY ='"
					+ categoryPriority + "' WHERE CATEGORYID = '" + categoryID
					+ "'";

			Connections.pstmt = Connections.conn.prepareStatement(editCat);

			Connections.pstmt.executeUpdate(editCat);

			System.out.println(Connections.pstmt.executeUpdate(editCat)
					+ " rows updated");

			Connections.conn.commit();

			// //Connections.killConnections();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method deletes a choosen Category from the database.
	 * 
	 * @param categoryID
	 *            -> The ID of the Category you would like to delete. @
	 */
	public static void deleteCategory(String categoryID)
	{

		try
		{
			// //Connections.createConnection();
			String delete = "DELETE category WHERE categoryID = '" + categoryID
					+ "'";

			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate(delete);

			Connections.conn.commit();

			// //Connections.killConnections();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method creates a new Log entry. A log entry is made when a new
	 * Ticket is created.
	 * 
	 * @param ticketID
	 *            -> The ID of the ticket in which the log is being written for.
	 * @param logDescription
	 *            -> The description of the log. @
	 */
	public static void addLog(String ticketID, String logDescription,
			String logTitle, String logDepartment)

	{
		boolean random = false;
		String logID = null;

		while (random == false)
		{
			Connections.killRset();
			logID = ("L" + String.valueOf(getRandomID()));
			try
			{

				String select = "SELECT * FROM log WHERE logID = '" + logID
						+ "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}
				Connections.killRset();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			Connections.killRset();
		}
		try
		{

			String insertString = "INSERT INTO log(logID,ticketID,logDescription, logTitle, logDepartment, logDate) values('"
					+ logID
					+ "','"
					+ ticketID
					+ "','"
					+ logDescription
					+ "','"
					+ logTitle
					+ "','"
					+ logDepartment
					+ "',' "
					+ getDate()
					+ "')";

			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			System.out.println("Log Created");

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method edits a Log entry and saves the changes to the database
	 * 
	 * @param logID
	 *            -> The ID of the Log entry to edit.
	 * @param ticketID
	 *            -> The ID of the ticket in which the log is being written for.
	 * @param logDescription
	 *            -> The description of the log. @
	 * 
	 **/
	public static void editLog(String logID, String ticketID,
			String logDescription)
	{

		try
		{

			String getStudent = "UPDATE log SET ticketID = " + ticketID
					+ ", logDescription = " + logDescription
					+ " WHERE logID = " + logID + " ";

			Connections.pstmt = Connections.conn.prepareStatement(getStudent);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method deletes a choosen Log from the database.
	 * 
	 * @param logID
	 *            -> The ID of the Log you would like to delete. @
	 */
	public static void deleteLog(String logID)
	{

		try
		{

			String delete = "DELETE log WHERE logID = " + logID + " ";

			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method creates a new Status. Statuses are used to show the current
	 * status of a Ticket.
	 * 
	 * @param statusName
	 *            -> The name of the status, such as Closed, Open, In-Progress,
	 *            Delegating-To-Other-Admin, etc. @
	 */
	public static void addStatus(String statusName)
	{

		boolean random = false;
		String statusID = null;

		while (random == false)
		{
			statusID = ("S" + String.valueOf(getRandomID()));
			try
			{

				String select = "SELECT * FROM status WHERE statusID = '"
						+ statusID + "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}

			} catch (SQLException e)
			{
				e.printStackTrace();
			}

		}
		try
		{

			String insertString = "INSERT INTO status(statusID,statusName) values('"
					+ statusID + "','" + statusName + "')";

			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method edits a Status entry and saves the changes to the database
	 * 
	 * @param statusID
	 *            -> The ID of the Log entry to edit.
	 * @param statusName
	 *            -> The name of the Status to be edited. @
	 * 
	 **/
	public static void editStatus(String statusID, String statusName)

	{

		try
		{

			String getStudent = "UPDATE Status SET statusName = " + statusName
					+ " WHERE statusID = " + statusID + " ";

			Connections.pstmt = Connections.conn.prepareStatement(getStudent);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method deletes a choosen Status from the database.
	 * 
	 * @param statusID
	 *            -> The ID of the Status you would like to delete. @
	 */
	public static void deleteStatus(String statusID)
	{

		try
		{

			String delete = "DELETE status WHERE statusID = " + statusID + " ";

			Connections.pstmt = Connections.conn.prepareStatement(delete);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	/**
	 * This method assigns an admin to a new Ticket. It takes in the
	 * departmentID in which the Ticket was created, and decides which Admin to
	 * choose by finding out which admin has the least amount of work.
	 * 
	 * @param departmentID
	 *            -> The ID of the department to find the Admin in.
	 * @return adminID -> The ID of the Admin to choose.
	 */
	private static String assignToAdmin()

	{
		String adminID = "";

		/*
		 * String select =
		 * "SELECT admin.adminid, Count(ticket.adminID) AS Amount FROM Admin " +
		 * "LEFT JOIN Ticket ON Admin.adminid = Ticket.adminid WHERE admin.departmentID = '"
		 * + departmentID + "' GROUP " +
		 * "BY Admin.adminid ORDER BY Count(ticket.adminid) asc";
		 */

		String select = "select count(ticket.adminid) as numtickets, ticket.adminid as assignedadmin, admin.adminid as nonassignedadmin from ticket "
				+ "full join admin on  ticket.adminid = admin.adminid where admin.adminid is not null "
				+ "group by ticket.adminid, admin.adminid "
				+ "order by numtickets asc";
		System.out.println(select);
		try
		{

			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			Connections.rset.next();
			adminID = Connections.rset.getString("nonassignedadmin");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();

		return adminID;
	}

	/**
	 * This method returns the current date in the format DD-MMM-YY, such as
	 * 20-MAR-12, which is the default format for Oracle SQL.
	 * 
	 * @return dateNow -> Todays current date.
	 */
	public static String getDate()
	{
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	/**
	 * This method creates a Random ID in the range of 0 - 500. It is used to
	 * create the ID of Tickets, Departments, Staff members, Admin members,
	 * Status, Categories, Logs...
	 * 
	 * @return randomID -> The random ID (Int)
	 */
	private static int getRandomID()
	{
		int randomID = 0;
		Random id = new Random();
		randomID = id.nextInt(7000);
		return randomID;
	}

	/**
	 * This method takes in a Category, and takes the Categories priority to use
	 * in the Ticket. It returns an integer between 0 - 5.
	 * 
	 * @param categoryID
	 *            -> The ID of the Category of the Ticket.
	 * @return categoryPriority -> The Priority of the Category, to use in the
	 *         Ticket.
	 */
	private static int getCategoryPriority(String categoryID)

	{

		int categoryPriority = 0;

		String select = "SELECT categoryPriority FROM Category WHERE CATEGORYID = '"
				+ categoryID + "'";
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();

			categoryPriority = Connections.rset.getInt("CATEGORYPRIORITY");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connections.killRset();
		return categoryPriority;
	}

	/**
	 * This method takes an Admins ID and returns the name of the Admin
	 * 
	 * @param adminID
	 *            -> The ID of the Admin.
	 * @return adminName -> The first and last name of the Admin.
	 */
	public static String getAdminName(String adminID)
	{
		String adminName = null;
		String select = "SELECT firstName, lastName FROM Admin WHERE adminid = '"
				+ adminID + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				adminName = Connections.rset.getString("firstName").trim()
						+ " " + Connections.rset.getString("lastName").trim();
			}
			Connections.killRset();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		Connections.killRset();
		return adminName;
	}

	/**
	 * This method transfers a Ticket from one Admin to another.
	 * 
	 * @param ticketID
	 *            -> The ID of the Ticket which will be passed on to.
	 * @param toAdminID
	 *            -> The ID of the Admin in which the Ticket will be transfered
	 *            to. @
	 */
	public static void transferTicket(String ticketID, String toAdminID)

	{

		try
		{

			String update = "UPDATE ticket SET adminID = '" + toAdminID.trim()
					+ "' WHERE ticketID = '" + ticketID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(update);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		postTransferTicketToAdmin(ticketID);
	}

	public static void getAllLogs()
	{
		try
		{

			String getLogs = ("SELECT * FROM LOG");

			Connections.pstmt = Connections.conn
					.prepareStatement(getLogs, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();

			Connections.md = Connections.rset.getMetaData();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		// Connections.killRset();
	}

	public static void getAllStaff()
	{
		try
		{

			String getLogs = ("SELECT STAFFID, FIRSTNAME, LASTNAME, EMAIL, OFFICENUMBER, MOBILENUMBER, DepartmentId Dept  FROM Staff "
					+ "UNION ALL "
					+ "SELECT ADMINID , FIRSTNAME, LASTNAME, EMAIL,  OFFICENUMBER, MOBILENUMBER, '<null>' Dept FROM Admin");

			Connections.pstmt = Connections.conn
					.prepareStatement(getLogs, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();

			Connections.md = Connections.rset.getMetaData();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void getAllDepartments()
	{
		try
		{

			String getDepts = ("SELECT * FROM DEPARTMENT");

			Connections.pstmt = Connections.conn
					.prepareStatement(getDepts,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();

			Connections.md = Connections.rset.getMetaData();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public static void getDepartmentDetails(String deptartmentID)
	{
		try
		{
			// Connections.createConnection();
			String getDepts = ("SELECT * FROM DEPARTMENT WHERE DEPARTMENTID = '"
					+ deptartmentID + "'");

			Connections.pstmt = Connections.conn
					.prepareStatement(getDepts,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();

			Connections.md = Connections.rset.getMetaData();
			Connections.rset.next();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	public static String checkLogin(String userName, String Password)
	{
		// Connections.createConnection();
		String userType = null;
		String user = userName;
		if (user.toUpperCase().charAt(0) == 'A')
		{
			try
			{

				String check = ("SELECT * FROM admin WHERE adminid ='"
						+ userName.trim() + "' AND password ='"
						+ Password.trim() + "'");

				System.out.println(check);
				Connections.pstmt = Connections.conn.prepareStatement(check);

				Connections.rset = Connections.pstmt.executeQuery();

				userType = "ADMIN";

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else if (user.toUpperCase().charAt(0) == 'U')
		{
			try
			{

				String check = ("SELECT * FROM staff WHERE staffid ='"
						+ userName + "' AND password ='" + Password + "'");

				Connections.pstmt = Connections.conn.prepareStatement(check);

				Connections.rset = Connections.pstmt.executeQuery();

				userType = "STAFF";
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else
		{
			userType = "BADLOGIN";
		}
		// Connections.killRset();
		return userType;
	}

	public static void getCurrentAdminDetails(String currentAdminID)
	{
		// Connections.createConnection();
		String getDetails = "SELECT FIRSTNAME, LASTNAME, EMAIL, OFFICENUMBER, MOBILENUMBER FROM ADMIN WHERE ADMINID ='"
				+ currentAdminID + "'";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(getDetails);
			Connections.rset = Connections.pstmt.executeQuery();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getAllCategorys()
	{
		try
		{
			String getCategory = "SELECT * FROM CATEGORY";

			Connections.pstmt = Connections.conn
					.prepareStatement(getCategory,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			Connections.rset = Connections.pstmt.executeQuery();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getCurrentCatPriority(String catID)
	{
		// Connections.createConnection();
		String searchCat = catID;
		String returnString = "1";

		String catQuery = "SELECT CATEGORYPRIORITY FROM CATEGORY WHERE CATEGORYID = '"
				+ searchCat + "'";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(catQuery);
			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			returnString = Connections.rset.getString("CATEGORYPRIORITY");
			Connections.pstmt.close();
			// Connections.killRset();
			// Connections.killConnections();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		Connections.killRset();
		return returnString;

	}

	public String getCurrentCatName(String catID)
	{
		// Connections.createConnection();
		String searchCat = catID;
		String returnString = "1";

		String catQuery = "SELECT CATEGORYNAME FROM CATEGORY WHERE CATEGORYID = '"
				+ searchCat + "'";

		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(catQuery);
			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			returnString = Connections.rset.getString("CATEGORYNAME");
			Connections.pstmt.close();
			// Connections.killRset();
			// Connections.killConnections();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return returnString;

	}

	public static void getCurrentAdminTickets(String currentAdmin)
	{

		String queryString = "SELECT * FROM TICKET WHERE ADMINID = '"
				+ currentAdmin + "'";

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
			System.out.println("No Tickets in system");
			e.printStackTrace();
		}

	}

	public static void getAllTickets()
	{
		Connections.killRset();
		String queryString = "SELECT * FROM TICKET";

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
			System.out.println("No Tickets in system");
			e.printStackTrace();
		}

	}

	public static void getStaffDetails(String staffID)
	{
		Connections.killRset();
		if (staffID.toUpperCase().charAt(0) == 'A')
		{
			try
			{

				String check = ("SELECT * FROM admin WHERE adminid ='"
						+ staffID + "'");

				Connections.pstmt = Connections.conn.prepareStatement(check);

				Connections.rset = Connections.pstmt.executeQuery();
				Connections.rset.next();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		} else if (staffID.toUpperCase().charAt(0) == 'U')
		{
			try
			{

				String check = ("SELECT * FROM staff WHERE staffid ='"
						+ staffID + "'");

				Connections.pstmt = Connections.conn.prepareStatement(check);

				Connections.rset = Connections.pstmt.executeQuery();
				Connections.rset.next();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

	}

	public String getStaffDepartment(String deptID)
	{
		String deptName = null;

		Connections.killRset();

		String queryString = "SELECT DEPARTMENTNAME FROM DEPARTMENT WHERE DEPARTMENTID = '"
				+ deptID + "'";

		try
		{

			Connections.pstmt = Connections.conn
					.prepareStatement(queryString,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			deptName = Connections.rset.getString("DEPARTMENTNAME");

		} catch (SQLException e)
		{
			System.out.println("No Tickets in system");
			e.printStackTrace();
		}

		Connections.killRset();
		return deptName;

	}

	public static String getStaffName(String currentStaffID)
	{
		// Connections.createConnection();
		String staffName = null;

		String queryString = "SELECT FIRSTNAME, LASTNAME FROM STAFF WHERE STAFFID = '"
				+ currentStaffID + "'";

		try
		{

			Connections.pstmt = Connections.conn
					.prepareStatement(queryString,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			staffName = Connections.rset.getString("FIRSTNAME").trim() + " "
					+ Connections.rset.getString("LASTNAME").trim();
			// Connections.killConnections();

		} catch (SQLException e)
		{
			System.out.println("No Tickets in system");
			e.printStackTrace();
		}
		Connections.killRset();
		return staffName;
	}

	public static void getCurrentStaffTickets(String currentStaff)
	{
		Connections.killRset();
		String queryString = "SELECT TICKETID, TICKETTITLE, TICKETDESCRIPTION, DATECREATED, DATECLOSED, ADMINID FROM TICKET WHERE STAFFID = '"
				+ currentStaff + "'";

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
			System.out.println("No Tickets in system");
			e.printStackTrace();
		}

	}

	public static String getStatus(String statusName)
	{
		String statusID = null;

		String queryString = "SELECT STATUSID FROM STATUS WHERE STATUSNAME = '"
				+ statusName + "'";

		try
		{

			Connections.pstmt = Connections.conn
					.prepareStatement(queryString,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			statusID = Connections.rset.getString("STATUSID");

		} catch (SQLException e)
		{
			System.out.println("No Tickets in system");
			e.printStackTrace();
		}

		Connections.killRset();
		return statusID;

	}

	/**
	 * This method edits an admins details and saves the changes to the database
	 * 
	 * @param adminID
	 *            -> The ID of the ticket to edit.
	 * @param password
	 *            -> The Users Old/New Password.
	 * @param firstName
	 *            -> First name of the admin.
	 * @param lastName
	 *            -> Last name of the admin.
	 * @param email
	 *            -> The admin email address.
	 * @param officeNumber
	 *            -> Office phone number of the user.
	 * @param mobileNumber
	 *            -> Mobile Phone number
	 */
	public void editAdminDetails(String adminID, String password,
			String firstName, String lastName, String email, int officeNumber,
			int mobileNumber)
	{
		// Connections.createConnection();
		try
		{

			String updateString = "UPDATE ADMIN SET PASSWORD = '" + password
					+ "', FIRSTNAME = '" + firstName + "', LASTNAME = '"
					+ lastName + "', " + "EMAIL = '" + email
					+ "', OFFICENUMBER = '" + officeNumber
					+ "', MOBILENUMBER = '" + mobileNumber + "' "
					+ "WHERE ADMINID = '" + adminID + "'";

			Connections.pstmt = Connections.conn.prepareStatement(updateString);

			Connections.pstmt.executeUpdate(updateString);

			Connections.conn.commit();

			// Connections.killConnections();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	public static String getAdminPass(String currentAdminID)
	{
		// Connections.createConnection();
		String adminPass = null;

		String queryString = "SELECT PASSWORD FROM ADMIN WHERE ADMINID = '"
				+ currentAdminID + "'";

		try
		{

			Connections.pstmt = Connections.conn
					.prepareStatement(queryString,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			adminPass = Connections.rset.getString("PASSWORD");

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		// Connections.killConnections();
		Connections.killRset();
		return adminPass;

	}

	// Gavin added code:
	public static String getTicketTitle(String id)
	{
		String title = "";
		String select = "select ticketTitle from Ticket where ticketID = '"
				+ id + "'".trim();

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				title = Connections.rset.getString("ticketTitle").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return title;
	}

	public static String getTicketDescription(String id)
	{
		String description = "";
		String select = "SELECT ticketDescription from Ticket WHERE ticketID = '"
				+ id + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				description = Connections.rset.getString("ticketDescription")
						.trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return description;
	}

	public static String getTicketStatus(String id)
	{
		String status = "";
		String select = "SELECT statusID from Ticket WHERE ticketID = '" + id
				+ "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				status = Connections.rset.getString("statusID").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return status;
	}

	public static String getTicketDepartment(String id)
	{
		String department = "";
		String select = "SELECT departmentID from Ticket WHERE ticketID = '"
				+ id + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				department = Connections.rset.getString("departmentID").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return department;
	}

	public static String getTicketCat(String id)
	{
		String category = "";
		String select = "SELECT categoryID from Ticket WHERE ticketID = '" + id
				+ "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				category = Connections.rset.getString("categoryID").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return category;
	}

	public static String getTicketPriority(String id)
	{
		String priority = "";
		String select = "SELECT ticketPriority from Ticket WHERE ticketID = '"
				+ id + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				priority = Connections.rset.getString("ticketPriority").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return priority;
	}

	public static String getTicketAdmin(String id)
	{
		String adminID = "";
		String select = "SELECT adminID from Ticket WHERE ticketID = '" + id
				+ "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				adminID = Connections.rset.getString("adminID").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return adminID;
	}

	public static String getTicketUser(String id)
	{
		String userID = "";
		String select = "SELECT staffID from Ticket WHERE ticketID = '" + id
				+ "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				userID = Connections.rset.getString("staffID").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return userID;
	}

	public static String getTicketDateCreated(String id)
	{
		String dateCreated = "";
		String select = "SELECT dateCreated from Ticket WHERE ticketID = '"
				+ id + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				dateCreated = Connections.rset.getString("dateCreated").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return dateCreated;
	}

	public static String getTicketDateClosed(String id)
	{
		String dateClosed = "";
		String select = "SELECT dateClosed from Ticket WHERE ticketID = '" + id
				+ "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true
					&& Connections.rset.getString("dateClosed") != null)
			{
				dateClosed = Connections.rset.getString("dateClosed").trim();
			} else
			{
				System.out.println("Date closed is null");
				dateClosed = " ";
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return dateClosed;
	}

	public static void changeTicketStatus(String ticketID, String statusID)
	{
		try
		{
			System.out.println(ticketID);
			String getTicket = "UPDATE ticket SET statusID = " + statusID
					+ " WHERE ticketID = " + ticketID + "".trim();

			Connections.pstmt = Connections.conn.prepareStatement(getTicket);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	public static String[] getAllStatus()
	{
		String[] status = null;
		try
		{

			String select = "SELECT * FROM status";
			// Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.pstmt = Connections.conn
					.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			status = new String[Connections.rset.getFetchSize()];
			int i = 0;
			while (Connections.rset.next() == true)
			{

				status[i] = Connections.rset.getString(1).trim() + " - "
						+ Connections.rset.getString(2).trim();
				i++;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public static String[] getAllAdmins()
	{
		String[] admin = null;
		try
		{

			String select = "SELECT * FROM admin";
			Connections.pstmt = Connections.conn.prepareStatement(select);

			Connections.rset = Connections.pstmt.executeQuery();
			admin = new String[Connections.rset.getFetchSize()];
			int i = 0;
			while (Connections.rset.next() == true)
			{

				admin[i] = Connections.rset.getString(1).trim() + " - "
						+ Connections.rset.getString(4).trim() + " "
						+ Connections.rset.getString(5).trim();
				i++;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return admin;
	}

	public static void closeTicket(String ticketID)
	{
		try
		{
			addKnowledge(getTicketTitle(ticketID),
					getTicketDescription(ticketID), getTicketCat(ticketID),
					getTicketDepartment(ticketID));

			String getStudent = "UPDATE ticket SET dateClosed = '" + getDate()
					+ "', statusID = '" + getStatusID("Closed")
					+ "' WHERE ticketID = '" + ticketID + "' ";

			Connections.pstmt = Connections.conn.prepareStatement(getStudent);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		postClosedTicketToAdmin(ticketID);
	}

	public static String getStatusID(String name)
	{
		String status = "";
		String select = "SELECT statusID from Status WHERE statusName = '"
				+ name + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				status = Connections.rset.getString("statusID").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return status;
	}

	public static String getStatusName(String id)
	{
		String status = "";
		String select = "SELECT statusName from Status WHERE statusID = '"
				.trim() + id.trim() + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				status = Connections.rset.getString("statusName").trim();
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			Connections.conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
		return status;
	}

	public static String canAdminTransfer(String id)
	{
		String canTransfer = "false";
		String select = "SELECT cantransferticket from Admin WHERE AdminID = '"
				+ id + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();

			if (Connections.rset.next() == true)
			{
				canTransfer = Connections.rset.getString("cantransferticket"
						.trim());
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connections.killRset();
		return canTransfer.trim();
	}

	public void getTicketDetails(String ticketID)
	{
		String select = "SELECT * from ticket WHERE ticketID = '" + ticketID
				+ "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void postReplyToTicket(String ticketID, String reply,
			String fromAdmin)
	{
		try
		{
			System.out.println(ticketID);
			String getTicket = "UPDATE ticket SET ticketdescription = CONCAT(ticketdescription, '' ||CHR(10)||CHR(10)|| 'Message from "
					+ Database.getAdminName(fromAdmin)
					+ "( #"
					+ fromAdmin
					+ ")"
					+ " : "
					+ reply.replace("'", "''")
					+ "' ) WHERE ticketID = '" + ticketID + "'".trim();

			Connections.pstmt = Connections.conn.prepareStatement(getTicket);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			Connections.killRset();

			// SEND AN EMAIL HERE!
			String message = (Database.getAdminName(fromAdmin) + " (# "
					+ fromAdmin + ") has replied to your ticket : '"
					+ Database.getTicketTitle(ticketID)
					+ "'.\n\nThe reply message is :\n\n" + "''" + reply + "''\n\nPlease do not reply to this email. Have a good day.\n\n");

			email.newTicketConfirmMail(getDefaultAdminEmail(), message,
					"New Reply for Ticket # " + ticketID);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	public static void postNewTicketToAdmin(String ticketID)
	{
		String message = Database.getAdminName(Database
				.getTicketAdmin(ticketID))
				+ " , you have been assigned a new ticket titled : '"
				+ Database.getTicketTitle(ticketID)
				+ "'\n\nThe description is : '"
				+ Database.getTicketDescription(ticketID)
				+ "' \n\nPlease do not reply to this email. Have a good day.\n\n";

		email.newTicketConfirmMail(
				getDefaultAdminEmail(),
				message,
				"New Ticket # " + ticketID + " : "
						+ Database.getTicketTitle(ticketID));

		Connections.killRset();
	}

	public static void postTransferTicketToAdmin(String ticketID)
	{
		String message = Database.getAdminName(Database
				.getTicketAdmin(ticketID))
				+ " , a ticket has been transfered to you titled : '"
				+ Database.getTicketTitle(ticketID)
				+ "'\n\nThe description is : '"
				+ Database.getTicketDescription(ticketID)
				+ "' \n\nPlease do not reply to this email. Have a good day.\n\n";

		email.newTicketConfirmMail(
				getDefaultAdminEmail(),
				message,
				"Transfered Ticket # " + ticketID + " : "
						+ Database.getTicketTitle(ticketID));

		Connections.killRset();
	}

	public static void postClosedTicketToAdmin(String ticketID)
	{
		String message = Database.getAdminName(Database
				.getTicketAdmin(ticketID))
				+ " , the ticket '"
				+ Database.getTicketTitle(ticketID)
				+ "' has been closed by '"
				+ Database.getTicketUser(ticketID)
				+ "'\n\nIt was opened on "
				+ Database.getTicketDateCreated(ticketID).split(" ").clone()[0]
				+ " and closed on "
				+ Database.getTicketDateClosed(ticketID).split(" ").clone()[0]
				+ " \n\nPlease do not reply to this email. Have a good day.\n\n";

		email.newTicketConfirmMail(
				getDefaultAdminEmail(),
				message,
				"Closed Ticket # " + ticketID + " : "
						+ Database.getTicketTitle(ticketID));

		Connections.killRset();
	}

	public static void postClosedTicketToStaff(String ticketID)
	{
		String message = Database
				.getStaffName(Database.getTicketUser(ticketID))
				+ " , the ticket '"
				+ Database.getTicketTitle(ticketID)
				+ "' has been closed by '"
				+ Database.getAdminName(adminUI.currentAdminID)
				+ "'\n\nIt was opened on "
				+ Database.getTicketDateCreated(ticketID).split(" ").clone()[0]
				+ " and closed on "
				+ Database.getDate()
				+ " \n\nPlease do not reply to this email. Have a good day.\n\n";

		email.newTicketConfirmMail(
				getDefaultAdminEmail(),
				message,
				"Closed Ticket # " + ticketID + " : "
						+ Database.getTicketTitle(ticketID));

		Connections.killRset();
	}

	public static void postReplyToAdmin(String ticketID, String text,
			String ticketUser)
	{
		try
		{
			System.out.println(ticketID);
			String getTicket = "UPDATE ticket SET ticketdescription = CONCAT(ticketdescription, '' ||CHR(10)||CHR(10)|| 'Message from "
					+ Database.getStaffName(ticketUser)
					+ "( #"
					+ ticketUser
					+ ")"
					+ " : "
					+ text.replace("'", "''")
					+ "' ) WHERE ticketID = '" + ticketID + "'".trim();

			Connections.pstmt = Connections.conn.prepareStatement(getTicket);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			Connections.killRset();

			// SEND AN EMAIL HERE!
			String message = (Database.getStaffName(ticketUser) + " (# "
					+ ticketUser
					+ ") has sent you a message regarding their ticket : '"
					+ Database.getTicketTitle(ticketID)
					+ "'.\n\nThe message is :\n\n" + "''" + text + "''\n\nPlease do not reply to this email. Have a good day.\n\n");

			email.newTicketConfirmMail(getDefaultAdminEmail(), message,
					"New Reply for Ticket # " + ticketID);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();

	}

	public static void addKnowledge(String title, String description,
			String categoryID, String departmentID)
	{
		boolean random = false;
		String knowledgeID = null;

		while (random == false)
		{
			Connections.killRset();
			knowledgeID = ("K" + String.valueOf(getRandomID()));
			try
			{

				String select = "SELECT * FROM knowledgebase WHERE knowledgeID = '"
						+ knowledgeID + "'";
				Connections.pstmt = Connections.conn.prepareStatement(select);

				Connections.rset = Connections.pstmt.executeQuery();

				if (Connections.rset.next() == false)
				{
					random = true;
				}
				Connections.killRset();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			Connections.killRset();
		}
		try
		{

			String insertString = "INSERT INTO knowledgeBase (knowledgeID,title, Description, categoryID, DepartmentId) values('"
					+ knowledgeID
					+ "','"
					+ title
					+ "','"
					+ description
					+ "','"
					+ categoryID + "','" + departmentID + "')";

			Connections.pstmt = Connections.conn.prepareStatement(insertString);

			Connections.pstmt.executeUpdate();

			Connections.conn.commit();
			System.out.println("Knowledge Created");

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		Connections.killRset();
	}

	public static void getAllKnowledge()
	{
		try
		{

			String getLogs = ("SELECT * FROM KnowledgeBase");

			Connections.pstmt = Connections.conn
					.prepareStatement(getLogs, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();

			Connections.md = Connections.rset.getMetaData();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	// Added 20042012
	public static int getDepartmentTickets(String deptID)
	{
		int numTickets = 0;
		String select = "SELECT COUNT( '" + deptID.trim()
				+ "') AS numTickets FROM ticket WHERE departmentid = '"
				+ deptID + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			numTickets = Connections.rset.getInt("numTickets");
			Connections.killRset();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numTickets;
	}

	// Added 20042012
	public static String[] getDepartmentIDs()
	{

		String[] deptID = null;
		int arraysize = 0;
		try
		{
			// Connections.createConnection();
			String getDepts = ("SELECT Distinct DEPARTMENTID FROM DEPARTMENT");

			Connections.pstmt = Connections.conn
					.prepareStatement(getDepts,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.last();
			arraysize = Connections.rset.getRow();
			Connections.rset.beforeFirst();
			deptID = new String[arraysize];
			int i = 0;
			while (Connections.rset.next())
			{
				deptID[i] = Connections.rset.getString("DEPARTMENTID");
				i++;
			}
			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return deptID;

	}

	// Added 20042012

	public static int[] getTicketCloseTime()
	{

		int[] closeTimes = null;
		int arraysize = 0;
		try
		{
			// Connections.createConnection();SELECT (DATECLOSED - DATECREATED)
			// FROM TICKET WHERE DATECLOSED IS NOT NULL
			String getDepts = ("SELECT (DATECLOSED - DATECREATED) as closeTime FROM TICKET WHERE DATECLOSED IS NOT NULL");

			Connections.pstmt = Connections.conn
					.prepareStatement(getDepts,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.last();
			arraysize = Connections.rset.getRow();
			Connections.rset.beforeFirst();
			closeTimes = new int[arraysize];
			int i = 0;
			while (Connections.rset.next())
			{
				closeTimes[i] = Connections.rset.getInt("CLOSETIME");
				i++;
			}
			Connections.killRset();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return closeTimes;

	}

	// Added 20042012
	public static String[] getCatIDs()
	{

		String[] catIDs = null;
		int arraysize = 0;
		try
		{
			// Connections.createConnection();
			String getDepts = ("SELECT Distinct CATEGORYID FROM CATEGORY");

			Connections.pstmt = Connections.conn
					.prepareStatement(getDepts,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.last();
			arraysize = Connections.rset.getRow();
			Connections.rset.beforeFirst();
			catIDs = new String[arraysize];
			int i = 0;
			while (Connections.rset.next())
			{
				catIDs[i] = Connections.rset.getString("CATEGORYID");
				i++;
			}
			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return catIDs;

	}

	public static String getCatName(String catID)
	{

		String catName = null;
		try
		{
			// Connections.createConnection();
			String getDepts = ("SELECT CATEGORYNAME FROM CATEGORY WHERE CATEGORYID = '"
					+ catID.trim() + "'");

			Connections.pstmt = Connections.conn
					.prepareStatement(getDepts,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.last();
			Connections.rset.getRow();
			Connections.rset.beforeFirst();
			while (Connections.rset.next())
			{
				catName = Connections.rset.getString("CATEGORYNAME");
			}

			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return catName;

	}

	public static int getCatTickets(String catID)
	{
		int numTickets = 0;
		String select = "SELECT COUNT( '" + catID.trim()
				+ "') AS numTickets FROM ticket WHERE CATEGORYID = '"
				+ catID.trim() + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			numTickets = Connections.rset.getInt("numTickets");
			Connections.killRset();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numTickets;
	}

	public static int getPriorityTickets(int priority)
	{
		int numTickets = 0;
		String select = "SELECT COUNT( 'TICKETPRIORITY') AS numTickets FROM ticket WHERE TICKETPRIORITY = '"
				+ priority + "'";

		Connections.rset = null;
		try
		{
			Connections.pstmt = Connections.conn.prepareStatement(select);
			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();
			numTickets = Connections.rset.getInt("numTickets");
			Connections.killRset();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numTickets;
	}

	public static String getDeptName(String deptID)
	{

		String deptName = null;
		try
		{
			// Connections.createConnection();
			String getDepts = ("SELECT DEPARTMENTNAME FROM DEPARTMENT WHERE DEPARTMENTID = '"
					+ deptID.trim() + "'");

			Connections.pstmt = Connections.conn
					.prepareStatement(getDepts,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			while (Connections.rset.next())
			{
				deptName = Connections.rset.getString("DEPARTMENTNAME");
			}

			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return deptName.trim();

	}

	public static String getKnowledgeTitle(String knowledgeID)
	{
		String knowledgeTitle = null;
		try
		{
			// Connections.createConnection();
			String search = ("SELECT title FROM knowledgeBase WHERE knowledgeID = '"
					+ knowledgeID.trim() + "'");

			Connections.pstmt = Connections.conn
					.prepareStatement(search, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.last();
			Connections.rset.beforeFirst();
			while (Connections.rset.next())
			{
				knowledgeTitle = Connections.rset.getString("Title");
			}

			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return knowledgeTitle;
	}

	public static String getKnowledgeDescription(String knowledgeID)
	{
		String knowledgeDescription = null;
		try
		{
			// Connections.createConnection();
			String search = ("SELECT description FROM knowledgeBase WHERE knowledgeID = '"
					+ knowledgeID.trim() + "'");

			Connections.pstmt = Connections.conn
					.prepareStatement(search, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.last();
			Connections.rset.beforeFirst();
			while (Connections.rset.next())
			{
				knowledgeDescription = Connections.rset
						.getString("Description");
			}

			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return knowledgeDescription;
	}

	public static void getCurrentStaffClosedTickets(String currentStaff)
	{
		Connections.killRset();

		String queryString = "SELECT TICKETID, TICKETTITLE, TICKETDESCRIPTION, DATECREATED, DATECLOSED, ADMINID FROM TICKET WHERE STAFFID = '"
				+ currentStaff
				+ "' AND STATUSID = '"
				+ getStatus("Closed")
				+ "'";

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
			System.out.println("No Tickets in system");
			e.printStackTrace();
		}

	}

	public static String getDefaultAdminEmail()
	{
		//THIS WILL BE USED FOR TESTING PURPOSES AS WE CANNOT CREATE EMAIL ADDRESSES FOR OUR STAFF AND ADMIN USERS
		String email = null;

		try
		{
			// Connections.createConnection();
			String getEmail = ("SELECT Email FROM Admin WHERE AdminId = 'Auxilium'");

			Connections.pstmt = Connections.conn
					.prepareStatement(getEmail,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			Connections.rset = Connections.pstmt.executeQuery();
			Connections.rset.next();

			email = Connections.rset.getString("Email");

			Connections.killRset();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return email;
	}
}
