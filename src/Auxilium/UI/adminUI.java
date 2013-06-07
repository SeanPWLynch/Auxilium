package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Panel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JToolBar;
import java.awt.SystemColor;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;
import Auxilium.BackEnd.PDF;
import Auxilium.BackEnd.email;
import Auxilium.BackEnd.graphs;
import Auxilium.ComboBoxModels.staffComboBoxModel;
import Auxilium.ComboBoxModels.staffDeptComboModel;
import Auxilium.ListModels.catListModel;
import Auxilium.TableModels.allAdminTicketTableModel;
import Auxilium.TableModels.currentAdminTicketTableModel;
import Auxilium.TableModels.departmentTableModel;
import Auxilium.TableModels.logTableModel;
import Auxilium.TableModels.staffTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Toolkit;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.FlowLayout;

public class adminUI extends JFrame
{

	public static String currentAdminID;

	private JPanel contentPane;

	private JTable logTable;

	private JTextField txtAdminName;

	private JTextField txtAdminEmail;

	private JTextField txtAdminPhone;

	private JTextField txtAdminMobile;

	private JPasswordField pwAdminOldPass;

	private JPasswordField pwAdminNewPass;

	private JPasswordField pwAdminNewPassCheck;

	private JTextField txtCatName;

	private JComboBox cmbCatPriority;

	static JTable tblAllTickets;

	private JTextField txtQTicketID;

	public static JTable tblAssignedTickets;

	private JTextField txtQStaffID;

	private JTextField txtQStaffName;

	private JTextField txtQStaffEmail;

	private JTable tblQStaffTable;

	private JTextField txtQDeptID;

	private JTextField txtQDeptEmail;

	private JTextField txtQDeptName;

	private JTable tblQDeptTable;

	private JTextField txtAddStaffFirstName;

	private JComboBox cmbAddStaffDept;

	private JTextField txtAddStaffLastName;

	private JTextField txtAddStaffEmail;

	private JPasswordField pwAddStaffPassword;

	private JPasswordField pwAddStaffPasswordVer;

	private JTextField txtAddStaffOfficePhone;

	private JTextField txtAddStaffMobilePhone;

	private JPanel pnlStaffScreens;

	private final ButtonGroup AddStaffUserType = new ButtonGroup();

	private JTextField txtEditStaffFirstName;

	private JTextField txtEditStaffLastName;

	private JTextField txtEditStaffEmail;

	private JPasswordField pwEditStaffPassword;

	private JTextField txtEditStaffOfficePhone;

	private JPasswordField pwEditStaffPasswordVer;

	private JTextField txtEditStaffMobilePhone;

	private final ButtonGroup EditStaffUserType = new ButtonGroup();

	private JTextField txtNewDepartmentName;

	private JTextField txtNewDepartmentEmail;

	private JTextField txtEditDepartmentName;

	private JTextField txtEditDepartmentEmail;

	private JButton btnAddStaffCancel;

	private JPanel pnlAddDepartment;

	private JPanel pnlDepartmentContainer;

	catListModel clm = new catListModel();

	staffDeptComboModel stcm = new staffDeptComboModel();

	staffComboBoxModel scm = new staffComboBoxModel();

	departmentTableModel dtm = new departmentTableModel();

	private JPanel pnlEditDepartments;

	private JPanel pnlQDepartments;

	private JList lstCategory;

	private JRadioButton rdbEditStaffTypeAdmin;

	private JRadioButton rdbEditStaffTypeStaff;

	private JComboBox cmbEditStaffDept;

	private JPanel pnlIssues;

	private JRadioButton rdbtnAddStaffTypeAdmin;

	private JRadioButton rdbtnAddStaffTypeStaff;

	private JTextArea txtNewDepartmentDescription;

	private JComboBox cmbQStaffDept;

	private JPanel pnlEditDepartmentSelect;

	private JComboBox cmbEditDepartmentSelect;

	private JTextArea txtEditDepartmentDescription;

	public JComboBox cmdQPriority = new JComboBox();

	TicketInformation pnlTicketInfo;

	public JPopupMenu popup;

	public Panel pnlTickets;

	private JDateChooser dcTo;

	private JDateChooser dcFrom;

	public JPanel pnlAllTickets = new JPanel();

	public JPanel pnlReports = new JPanel();

	public Panel pnlDepartments = new Panel();

	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	public Panel pnlSettings = new Panel();

	public Panel pnlStaff = new Panel();

	private JComboBox cmbReportType;

	private JComboBox cmbReportView;

	private ChartPanel chartPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{

		EventQueue.invokeLater(new Runnable()
		{

			public void run()
			{

				try
				{
					Connections.killRset();
					adminUI frame = new adminUI(currentAdminID);
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private JPopupMenu createPopup(final String ticketID)
	{

		JPopupMenu pop = new JPopupMenu("Ticket");
		JMenuItem item = new JMenuItem("View Ticket");
		item.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				System.out.println(ticketID);
				Connections.killRset();
				pnlTicketInfo = new TicketInformation(ticketID);
				pnlTicketInfo.setVisible(true);
				Connections.killRset();
				// pnlTicketInfo.setSize(580, 500);
				// pnlTickets.
			}
		});
		pop.add(item);
		return pop;
	}

	/**
	 * Create the frame.
	 * 
	 * @param string
	 * @param string
	 */
	public adminUI(String adminID)
	{

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				adminUI.class.getResource("/Auxilium/Images/icon.png")));
		currentAdminID = adminID;
		System.out.println("Current User is " + currentAdminID);
		setTitle("Auxilium Admin Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 730);

		// Start of seans code 22-04-2012

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				System.out.println("System is now closing...");

				Connections.killConnections();

				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);

		JMenuItem mntmChangeDetails = new JMenuItem("Change Details");
		mntmChangeDetails.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				tabbedPane.setSelectedComponent(pnlSettings);
				txtAdminName.grabFocus();
			}
		});
		mnSettings.add(mntmChangeDetails);

		JMenuItem mntmChangeCat = new JMenuItem("Edit Categorys");
		mntmChangeCat.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlSettings);
				txtCatName.grabFocus();
			}
		});
		mnSettings.add(mntmChangeCat);

		JMenu mnTickets = new JMenu("Tickets");
		menuBar.add(mnTickets);

		JMenuItem mntmViewTickets = new JMenuItem("View Tickets");
		mntmViewTickets.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlTickets);
			}
		});
		mnTickets.add(mntmViewTickets);

		JMenu mnStaff = new JMenu("Staff");
		menuBar.add(mnStaff);

		JMenuItem mntmViewStaff = new JMenuItem("View Staff");
		mntmViewStaff.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlStaff);
				CardLayout staff = (CardLayout) (pnlStaffScreens.getLayout());
				staff.show(pnlStaffScreens, "queryStaff");

			}
		});

		mnStaff.add(mntmViewStaff);

		JMenuItem mntmAddNewStaff = new JMenuItem("Add New Staff ");
		mntmAddNewStaff.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlStaff);
				CardLayout staff = (CardLayout) (pnlStaffScreens.getLayout());
				staff.show(pnlStaffScreens, "addStaff");
			}
		});
		mnStaff.add(mntmAddNewStaff);

		JMenuItem mntmEditStaff = new JMenuItem("Edit Staff Member");
		mntmEditStaff.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlStaff);
				CardLayout staff = (CardLayout) (pnlStaffScreens.getLayout());
				staff.show(pnlStaffScreens, "editStaff");
			}
		});
		mnStaff.add(mntmEditStaff);

		JMenu mnDepartments = new JMenu("Departments");
		menuBar.add(mnDepartments);

		JMenuItem mntmViewDept = new JMenuItem("View Departments");
		mntmViewDept.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlDepartments);
				CardLayout Departments = (CardLayout) (pnlDepartmentContainer
						.getLayout());
				Departments.show(pnlDepartmentContainer, "queryDept");
			}
		});
		mnDepartments.add(mntmViewDept);

		JMenuItem mntmAddDept = new JMenuItem("Add New Department");
		mntmAddDept.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlDepartments);
				CardLayout Departments = (CardLayout) (pnlDepartmentContainer
						.getLayout());
				Departments.show(pnlDepartmentContainer, "addDept");
			}
		});
		mnDepartments.add(mntmAddDept);

		JMenuItem mntmEditDept = new JMenuItem("Edit Department");
		mntmEditDept.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlDepartments);
				CardLayout Departments = (CardLayout) (pnlDepartmentContainer
						.getLayout());
				Departments.show(pnlDepartmentContainer, "editDept");

			}
		});
		mnDepartments.add(mntmEditDept);

		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);

		JMenuItem mntmViewReports = new JMenuItem("View Reports");
		mntmViewReports.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlReports);
			}
		});
		mnReports.add(mntmViewReports);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				adminHelp.main(null);
			}
		});
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				about.main(null);
			}
		});
		mnHelp.add(mntmAbout);

		// End of seans code
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/**
		 * This window listener checks to see if the Window is closing, and if
		 * it is, it closes all the connections
		 */
		this.addWindowListener(new WindowAdapter()
		{

			public void windowClosing(WindowEvent e)
			{

				System.out.println("System is now closing...");

				Connections.killConnections();

				System.exit(0);
			}

		});

		JPanel pnlLogo = new JPanel();
		contentPane.add(pnlLogo, BorderLayout.NORTH);
		GridBagLayout gbl_pnlLogo = new GridBagLayout();
		gbl_pnlLogo.columnWidths = new int[]
		{ 0, 250, 325, 0 };
		gbl_pnlLogo.rowHeights = new int[]
		{ 30, 0 };
		gbl_pnlLogo.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlLogo.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlLogo.setLayout(gbl_pnlLogo);

		JLabel lblLogo = new JLabel("");
		lblLogo.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent arg0)
			{

			}
		});
		lblLogo.setIcon(new ImageIcon(adminUI.class
				.getResource("/Auxilium/Images/logoSmall.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogo.fill = GridBagConstraints.BOTH;
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 0;
		pnlLogo.add(lblLogo, gbc_lblLogo);

		JPanel pnlUserLogOut = new JPanel();
		pnlUserLogOut.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlUserLogOut = new GridBagConstraints();
		gbc_pnlUserLogOut.anchor = GridBagConstraints.NORTH;
		gbc_pnlUserLogOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlUserLogOut.gridx = 2;
		gbc_pnlUserLogOut.gridy = 0;
		pnlLogo.add(pnlUserLogOut, gbc_pnlUserLogOut);
		GridBagLayout gbl_pnlUserLogOut = new GridBagLayout();
		gbl_pnlUserLogOut.columnWidths = new int[]
		{ 90, 60, 0, 0, 0, 0, 0 };
		gbl_pnlUserLogOut.rowHeights = new int[]
		{ 25, 0 };
		gbl_pnlUserLogOut.columnWeights = new double[]
		{ 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlUserLogOut.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlUserLogOut.setLayout(gbl_pnlUserLogOut);
		// Connections.createConnection();
		String adminName = Database.getAdminName(currentAdminID);
		Connections.killRset();
		JLabel lblWelcome = new JLabel("Welcome Back, " + adminName);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.gridwidth = 2;
		gbc_lblWelcome.insets = new Insets(0, 0, 0, 5);
		gbc_lblWelcome.anchor = GridBagConstraints.EAST;
		gbc_lblWelcome.fill = GridBagConstraints.VERTICAL;
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 0;
		pnlUserLogOut.add(lblWelcome, gbc_lblWelcome);

		JLabel lblPreferences = new JLabel("|Preferences|");
		lblPreferences.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent arg0)
			{

				System.out.println("Loads Preferences");
			}
		});
		lblPreferences.setForeground(new Color(255, 140, 0));
		lblPreferences.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPreferences = new GridBagConstraints();
		gbc_lblPreferences.insets = new Insets(0, 0, 0, 5);
		gbc_lblPreferences.fill = GridBagConstraints.BOTH;
		gbc_lblPreferences.gridx = 3;
		gbc_lblPreferences.gridy = 0;
		pnlUserLogOut.add(lblPreferences, gbc_lblPreferences);

		JLabel lblLogout = new JLabel("|Logout|");
		lblLogout.addFocusListener(new FocusAdapter()
		{

			@Override
			public void focusGained(FocusEvent arg0)
			{

				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void focusLost(FocusEvent arg0)
			{

				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLogout.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

				System.out.println(currentAdminID + " Logout");
				// Connections.killConnections();
				loginUI app = new loginUI();
				app.setVisible(true);
				adminUI.this.dispose();
				Connections.killRset();
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{

				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0)
			{

				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

		});
		lblLogout.setForeground(new Color(255, 140, 0));
		lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLogout = new GridBagConstraints();
		gbc_lblLogout.fill = GridBagConstraints.BOTH;
		gbc_lblLogout.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogout.gridx = 4;
		gbc_lblLogout.gridy = 0;
		pnlUserLogOut.add(lblLogout, gbc_lblLogout);

		tabbedPane.setBackground(SystemColor.control);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		ChangeListener tabChange = new ChangeListener()
		{

			public void stateChanged(ChangeEvent changeEvent)
			{

				JTabbedPane tabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = tabbedPane.getSelectedIndex();
				if (tabbedPane.getTitleAt(index).equals("Dashboard"))
				{
					System.out.println("Dashboard Pane Open");

				}

				else if (tabbedPane.getTitleAt(index).equals("Settings"))

				{
					System.out.println("Settings Tab Open");
					Database.getCurrentAdminDetails(currentAdminID);
					try
					{
						Connections.rset.next();
						txtAdminName
								.setText(Connections.rset
										.getString("FIRSTNAME").trim()
										+ " "
										+ Connections.rset
												.getString("LASTNAME").trim());
						txtAdminEmail.setText(Connections.rset.getString(
								"EMAIL").trim());
						txtAdminPhone.setText(Connections.rset.getString(
								"OFFICENUMBER").trim());
						txtAdminMobile.setText(Connections.rset.getString(
								"MOBILENUMBER").trim());
						Connections.killRset();

					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};

		tabbedPane.addChangeListener(tabChange);

		JPanel lblMainDashBoard = new JPanel();
		lblMainDashBoard.setBackground(Color.WHITE);
		tabbedPane.addTab("Dashboard", null, lblMainDashBoard, null);
		GridBagLayout gbl_lblMainDashBoard = new GridBagLayout();
		gbl_lblMainDashBoard.columnWidths = new int[]
		{ 25, 75, 450, 75, 25, 0 };
		gbl_lblMainDashBoard.rowHeights = new int[]
		{ 10, 85, 10, 0, 20, 0 };
		gbl_lblMainDashBoard.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_lblMainDashBoard.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		lblMainDashBoard.setLayout(gbl_lblMainDashBoard);

		JPanel pnlQueryLogs = new JPanel();
		pnlQueryLogs.setBackground(Color.WHITE);
		pnlQueryLogs.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Query Logs",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlQueryLogs = new GridBagConstraints();
		gbc_pnlQueryLogs.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQueryLogs.fill = GridBagConstraints.BOTH;
		gbc_pnlQueryLogs.gridx = 2;
		gbc_pnlQueryLogs.gridy = 1;
		lblMainDashBoard.add(pnlQueryLogs, gbc_pnlQueryLogs);
		GridBagLayout gbl_pnlQueryLogs = new GridBagLayout();
		gbl_pnlQueryLogs.columnWidths = new int[]
		{ 480, 0, 0, 0 };
		gbl_pnlQueryLogs.rowHeights = new int[]
		{ 57, 0 };
		gbl_pnlQueryLogs.columnWeights = new double[]
		{ 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_pnlQueryLogs.rowWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		pnlQueryLogs.setLayout(gbl_pnlQueryLogs);

		Panel pblDateContainer = new Panel();
		GridBagConstraints gbc_pblDateContainer = new GridBagConstraints();
		gbc_pblDateContainer.insets = new Insets(0, 0, 0, 5);
		gbc_pblDateContainer.fill = GridBagConstraints.BOTH;
		gbc_pblDateContainer.gridx = 0;
		gbc_pblDateContainer.gridy = 0;
		pnlQueryLogs.add(pblDateContainer, gbc_pblDateContainer);
		GridBagLayout gbl_pblDateContainer = new GridBagLayout();
		gbl_pblDateContainer.columnWidths = new int[]
		{ 0, 75, 0, 0, 0, 0, 0 };
		gbl_pblDateContainer.rowHeights = new int[]
		{ 50, 0 };
		gbl_pblDateContainer.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pblDateContainer.rowWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		pblDateContainer.setLayout(gbl_pblDateContainer);

		JLabel lblDashDateFrom = new JLabel("From: ");
		GridBagConstraints gbc_lblDashDateFrom = new GridBagConstraints();
		gbc_lblDashDateFrom.insets = new Insets(0, 0, 0, 5);
		gbc_lblDashDateFrom.anchor = GridBagConstraints.EAST;
		gbc_lblDashDateFrom.gridx = 0;
		gbc_lblDashDateFrom.gridy = 0;
		pblDateContainer.add(lblDashDateFrom, gbc_lblDashDateFrom);

		dcFrom = new JDateChooser();
		GridBagConstraints gbc_dcFrom = new GridBagConstraints();
		gbc_dcFrom.gridwidth = 2;
		gbc_dcFrom.insets = new Insets(0, 0, 0, 5);
		gbc_dcFrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_dcFrom.gridx = 1;
		gbc_dcFrom.gridy = 0;
		pblDateContainer.add(dcFrom, gbc_dcFrom);

		JLabel lblDashDateTo = new JLabel("To: ");
		GridBagConstraints gbc_lblDashDateTo = new GridBagConstraints();
		gbc_lblDashDateTo.insets = new Insets(0, 0, 0, 5);
		gbc_lblDashDateTo.anchor = GridBagConstraints.EAST;
		gbc_lblDashDateTo.gridx = 3;
		gbc_lblDashDateTo.gridy = 0;
		pblDateContainer.add(lblDashDateTo, gbc_lblDashDateTo);

		dcTo = new JDateChooser();
		GridBagConstraints gbc_dcTo = new GridBagConstraints();
		gbc_dcTo.gridwidth = 2;
		gbc_dcTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_dcTo.gridx = 4;
		gbc_dcTo.gridy = 0;
		pblDateContainer.add(dcTo, gbc_dcTo);

		JButton btnQueryClear = new JButton("Clear");
		btnQueryClear.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				dcFrom.setDate(null);
				dcTo.setDate(null);
			}
		});
		GridBagConstraints gbc_btnQueryClear = new GridBagConstraints();
		gbc_btnQueryClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnQueryClear.gridx = 1;
		gbc_btnQueryClear.gridy = 0;
		pnlQueryLogs.add(btnQueryClear, gbc_btnQueryClear);

		JButton btnQuery = new JButton("Query");
		btnQuery.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				// 20/4/12 added new code here - Gavin
				if (dcFrom.getDate() == null || dcTo.getDate() == null)
				{
					JOptionPane
							.showMessageDialog(null,
									"Please set a From and To date.",
									"Incorrent Date Formate",
									JOptionPane.ERROR_MESSAGE);
				} else
				{
					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd-MMM-yy");
					String to = formatter.format(dcTo.getDate().getTime());
					String from = formatter.format(dcFrom.getDate().getTime());
					logTableModel.filterRows(from, to);

					logTable.revalidate();
					logTable.repaint();
					System.out.println(from);
					System.out.println(to);
				}

			}
		});
		GridBagConstraints gbc_btnQuery = new GridBagConstraints();
		gbc_btnQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuery.gridx = 2;
		gbc_btnQuery.gridy = 0;
		pnlQueryLogs.add(btnQuery, gbc_btnQuery);

		JPanel pnlTableContainer = new JPanel();
		GridBagConstraints gbc_pnlTableContainer = new GridBagConstraints();
		gbc_pnlTableContainer.gridwidth = 3;
		gbc_pnlTableContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlTableContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlTableContainer.gridx = 1;
		gbc_pnlTableContainer.gridy = 3;
		lblMainDashBoard.add(pnlTableContainer, gbc_pnlTableContainer);
		pnlTableContainer.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane logScrollPane = new JScrollPane();
		logScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		pnlTableContainer.add(logScrollPane);

		logTableModel ltm = new logTableModel();

		logTable = new JTable();
		logTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		logTable.setFillsViewportHeight(true);
		logScrollPane.setViewportView(logTable);
		logTable.setModel(ltm);

		pnlSettings = new Panel();
		pnlSettings.setBackground(Color.WHITE);
		tabbedPane.addTab("Settings", null, pnlSettings, null);
		GridBagLayout gbl_pnlSettings = new GridBagLayout();
		gbl_pnlSettings.columnWidths = new int[]
		{ 5, 250, 5, 430, 5, 0 };
		gbl_pnlSettings.rowHeights = new int[]
		{ 10, 300, 10, 0 };
		gbl_pnlSettings.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlSettings.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlSettings.setLayout(gbl_pnlSettings);

		JPanel pnlAdminInfo = new JPanel();
		pnlAdminInfo.setBackground(Color.WHITE);
		pnlAdminInfo.setBorder(new TitledBorder(null, "Your Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlAdminInfo = new GridBagConstraints();
		gbc_pnlAdminInfo.insets = new Insets(0, 0, 5, 5);
		gbc_pnlAdminInfo.fill = GridBagConstraints.BOTH;
		gbc_pnlAdminInfo.gridx = 1;
		gbc_pnlAdminInfo.gridy = 1;
		pnlSettings.add(pnlAdminInfo, gbc_pnlAdminInfo);
		GridBagLayout gbl_pnlAdminInfo = new GridBagLayout();
		gbl_pnlAdminInfo.columnWidths = new int[]
		{ 40, 0, 0 };
		gbl_pnlAdminInfo.rowHeights = new int[]
		{ 30, 30, 30, 30, 30, 30, 30, 5, 40, 5, 0 };
		gbl_pnlAdminInfo.columnWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlAdminInfo.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlAdminInfo.setLayout(gbl_pnlAdminInfo);

		JLabel lblAdminName = new JLabel("Name: ");
		GridBagConstraints gbc_lblAdminName = new GridBagConstraints();
		gbc_lblAdminName.anchor = GridBagConstraints.EAST;
		gbc_lblAdminName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdminName.gridx = 0;
		gbc_lblAdminName.gridy = 0;
		pnlAdminInfo.add(lblAdminName, gbc_lblAdminName);

		txtAdminName = new JTextField();
		txtAdminName.setBorder(new LineBorder(Color.BLACK));
		GridBagConstraints gbc_txtAdminName = new GridBagConstraints();
		gbc_txtAdminName.insets = new Insets(0, 0, 5, 0);
		gbc_txtAdminName.fill = GridBagConstraints.BOTH;
		gbc_txtAdminName.gridx = 1;
		gbc_txtAdminName.gridy = 0;
		pnlAdminInfo.add(txtAdminName, gbc_txtAdminName);
		txtAdminName.setColumns(10);

		JLabel lblAdminEmail = new JLabel("Email: ");
		GridBagConstraints gbc_lblAdminEmail = new GridBagConstraints();
		gbc_lblAdminEmail.anchor = GridBagConstraints.EAST;
		gbc_lblAdminEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdminEmail.gridx = 0;
		gbc_lblAdminEmail.gridy = 1;
		pnlAdminInfo.add(lblAdminEmail, gbc_lblAdminEmail);

		txtAdminEmail = new JTextField();
		txtAdminEmail.setBorder(new LineBorder(Color.BLACK));
		txtAdminEmail.setColumns(10);
		GridBagConstraints gbc_txtAdminEmail = new GridBagConstraints();
		gbc_txtAdminEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtAdminEmail.fill = GridBagConstraints.BOTH;
		gbc_txtAdminEmail.gridx = 1;
		gbc_txtAdminEmail.gridy = 1;
		pnlAdminInfo.add(txtAdminEmail, gbc_txtAdminEmail);

		JLabel lblPhone = new JLabel("Phone: ");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 0;
		gbc_lblPhone.gridy = 2;
		pnlAdminInfo.add(lblPhone, gbc_lblPhone);

		txtAdminPhone = new JTextField();
		txtAdminPhone.setBorder(new LineBorder(Color.BLACK));
		txtAdminPhone.setColumns(10);
		GridBagConstraints gbc_txtAdminPhone = new GridBagConstraints();
		gbc_txtAdminPhone.insets = new Insets(0, 0, 5, 0);
		gbc_txtAdminPhone.fill = GridBagConstraints.BOTH;
		gbc_txtAdminPhone.gridx = 1;
		gbc_txtAdminPhone.gridy = 2;
		pnlAdminInfo.add(txtAdminPhone, gbc_txtAdminPhone);

		JLabel lblMobile = new JLabel("Mobile: ");
		GridBagConstraints gbc_lblMobile = new GridBagConstraints();
		gbc_lblMobile.anchor = GridBagConstraints.EAST;
		gbc_lblMobile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMobile.gridx = 0;
		gbc_lblMobile.gridy = 3;
		pnlAdminInfo.add(lblMobile, gbc_lblMobile);

		txtAdminMobile = new JTextField();
		txtAdminMobile.setBorder(new LineBorder(Color.BLACK));
		txtAdminMobile.setColumns(10);
		GridBagConstraints gbc_txtAdminMobile = new GridBagConstraints();
		gbc_txtAdminMobile.insets = new Insets(0, 0, 5, 0);
		gbc_txtAdminMobile.fill = GridBagConstraints.BOTH;
		gbc_txtAdminMobile.gridx = 1;
		gbc_txtAdminMobile.gridy = 3;
		pnlAdminInfo.add(txtAdminMobile, gbc_txtAdminMobile);

		JLabel lblOldPassword = new JLabel("Old Password: ");
		GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
		gbc_lblOldPassword.anchor = GridBagConstraints.EAST;
		gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblOldPassword.gridx = 0;
		gbc_lblOldPassword.gridy = 4;
		pnlAdminInfo.add(lblOldPassword, gbc_lblOldPassword);

		pwAdminOldPass = new JPasswordField();
		pwAdminOldPass.setBorder(new LineBorder(Color.BLACK));
		pwAdminOldPass.setColumns(10);
		GridBagConstraints gbc_pwAdminOldPass = new GridBagConstraints();
		gbc_pwAdminOldPass.insets = new Insets(0, 0, 5, 0);
		gbc_pwAdminOldPass.fill = GridBagConstraints.BOTH;
		gbc_pwAdminOldPass.gridx = 1;
		gbc_pwAdminOldPass.gridy = 4;
		pnlAdminInfo.add(pwAdminOldPass, gbc_pwAdminOldPass);

		JLabel lblNewPassword = new JLabel("New Password: ");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPassword.gridx = 0;
		gbc_lblNewPassword.gridy = 5;
		pnlAdminInfo.add(lblNewPassword, gbc_lblNewPassword);

		pwAdminNewPass = new JPasswordField();
		pwAdminNewPass.setBorder(new LineBorder(Color.BLACK));
		pwAdminNewPass.setColumns(10);
		GridBagConstraints gbc_pwAdminNewPass = new GridBagConstraints();
		gbc_pwAdminNewPass.insets = new Insets(0, 0, 5, 0);
		gbc_pwAdminNewPass.fill = GridBagConstraints.BOTH;
		gbc_pwAdminNewPass.gridx = 1;
		gbc_pwAdminNewPass.gridy = 5;
		pnlAdminInfo.add(pwAdminNewPass, gbc_pwAdminNewPass);

		JLabel lblPasswordVerification = new JLabel("Password Verification: ");
		GridBagConstraints gbc_lblPasswordVerification = new GridBagConstraints();
		gbc_lblPasswordVerification.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordVerification.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordVerification.gridx = 0;
		gbc_lblPasswordVerification.gridy = 6;
		pnlAdminInfo.add(lblPasswordVerification, gbc_lblPasswordVerification);

		pwAdminNewPassCheck = new JPasswordField();
		pwAdminNewPassCheck.setBorder(new LineBorder(Color.BLACK));
		pwAdminNewPassCheck.setColumns(10);
		GridBagConstraints gbc_pwAdminNewPassCheck = new GridBagConstraints();
		gbc_pwAdminNewPassCheck.insets = new Insets(0, 0, 5, 0);
		gbc_pwAdminNewPassCheck.fill = GridBagConstraints.BOTH;
		gbc_pwAdminNewPassCheck.gridx = 1;
		gbc_pwAdminNewPassCheck.gridy = 6;
		pnlAdminInfo.add(pwAdminNewPassCheck, gbc_pwAdminNewPassCheck);

		JButton btnUpdate = new JButton("Update Information");
		btnUpdate.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				String[] getName = null;
				String adminID = null;
				String firstName = null;
				String lastName = null;
				String newPassword = null;
				String newPasswordVer = null;
				String oldPassword = null;
				String oldPasswordCheck = null;
				String email = null;
				int officeNumber = 0;
				int mobileNumber = 0;

				oldPassword = Database.getAdminPass(currentAdminID);

				if (txtAdminName.getText().trim().equals(null)
						|| txtAdminName.getText().trim().equals("")
						|| txtAdminEmail.getText().trim().equals(null)
						|| txtAdminEmail.getText().trim().equals("")
						|| txtAdminPhone.getText().trim().equals(null)
						|| txtAdminPhone.getText().trim().equals("")
						|| txtAdminMobile.getText().trim().equals(null)
						|| txtAdminMobile.getText().trim().equals("")
						|| pwAdminOldPass.getPassword().length <= 0)
				{
					JOptionPane.showMessageDialog(null,
							"Please fill out all fields.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else
				{
					getName = txtAdminName.getText().split(" ");
					firstName = getName[0];
					lastName = getName[1];
					adminID = currentAdminID;
					email = txtAdminEmail.getText();
					officeNumber = Integer.parseInt(txtAdminPhone.getText());
					mobileNumber = Integer.parseInt(txtAdminMobile.getText());

					System.out.println(adminID);
					System.out.println(newPassword);
					System.out.println(firstName);
					System.out.println(lastName);
					System.out.println(email);
					System.out.println(officeNumber);
					System.out.println(mobileNumber);

					if (pwAdminOldPass.getPassword().length > 0
							&& pwAdminNewPass.getPassword().length > 0
							&& pwAdminNewPassCheck.getPassword().length > 0)
					{
						oldPasswordCheck = new String(pwAdminOldPass
								.getPassword());
						newPassword = new String(pwAdminNewPass.getPassword());
						newPasswordVer = new String(pwAdminNewPassCheck
								.getPassword());
						if (oldPassword.trim().equals(oldPasswordCheck.trim()))
						{
							if (newPassword.equals(newPasswordVer))
							{
								System.out.println("Password Match");
								Database.editAdmin(currentAdminID, newPassword,
										firstName, lastName, email,
										officeNumber, mobileNumber);
								staffTableModel stm = new staffTableModel();
								tblQStaffTable.setModel(stm);
								tblQStaffTable.revalidate();
								tblQStaffTable.repaint();
								JOptionPane.showMessageDialog(null,
										"Information Updated", "Updated",
										JOptionPane.PLAIN_MESSAGE);

							} else
							{
								JOptionPane.showMessageDialog(null,
										"Passwords do not match.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} else
						{
							JOptionPane.showMessageDialog(null,
									"Old password does not match.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} else if (pwAdminOldPass.getPassword().length == 0
							&& pwAdminNewPass.getPassword().length == 0
							&& pwAdminNewPassCheck.getPassword().length == 0)
					{
						newPassword = oldPassword;
						Database.editAdmin(currentAdminID, newPassword,
								firstName, lastName, email, officeNumber,
								mobileNumber);
						staffTableModel stm = new staffTableModel();
						tblQStaffTable.setModel(stm);
						tblQStaffTable.revalidate();
						tblQStaffTable.repaint();
					} else if (pwAdminOldPass.getPassword().length == 0
							&& pwAdminNewPass.getPassword().length > 0
							&& pwAdminNewPassCheck.getPassword().length > 0)
					{
						System.out.println("Please enter old pass");
					} else if (pwAdminOldPass.getPassword().length > 0
							&& pwAdminNewPass.getPassword().length == 0
							&& pwAdminNewPassCheck.getPassword().length == 0)
					{
						newPassword = oldPassword;
						Database.editAdmin(currentAdminID, newPassword,
								firstName, lastName, email, officeNumber,
								mobileNumber);
						staffTableModel stm = new staffTableModel();
						tblQStaffTable.setModel(stm);
						tblQStaffTable.revalidate();
						tblQStaffTable.repaint();
					}

				}
			}
		});
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.VERTICAL;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 0);
		gbc_btnUpdate.gridwidth = 2;
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 8;
		pnlAdminInfo.add(btnUpdate, gbc_btnUpdate);

		pnlIssues = new JPanel();
		pnlIssues.setBackground(Color.WHITE);
		pnlIssues.setBorder(new TitledBorder(null, "Issues",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlIssues = new GridBagConstraints();
		gbc_pnlIssues.insets = new Insets(0, 0, 5, 5);
		gbc_pnlIssues.fill = GridBagConstraints.BOTH;
		gbc_pnlIssues.gridx = 3;
		gbc_pnlIssues.gridy = 1;
		pnlSettings.add(pnlIssues, gbc_pnlIssues);
		GridBagLayout gbl_pnlIssues = new GridBagLayout();
		gbl_pnlIssues.columnWidths = new int[]
		{ 100, 0, 0 };
		gbl_pnlIssues.rowHeights = new int[]
		{ 3, 0, 5, 0 };
		gbl_pnlIssues.columnWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlIssues.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlIssues.setLayout(gbl_pnlIssues);

		lstCategory = new JList();
		lstCategory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstCategory.setBorder(new TitledBorder(new LineBorder(
				new Color(0, 0, 0)), "Category Name", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		lstCategory.setModel(clm);
		GridBagConstraints gbc_lstCategory = new GridBagConstraints();
		gbc_lstCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lstCategory.fill = GridBagConstraints.BOTH;
		gbc_lstCategory.gridx = 0;
		gbc_lstCategory.gridy = 1;
		lstCategory.addListSelectionListener(new ListSelectionListener()
		{

			public void valueChanged(ListSelectionEvent arg0)
			{

				if (lstCategory.getSelectedIndex() == -1)
				{
					System.out.println("derp");
					lstCategory.revalidate();
					lstCategory.repaint();
					pnlIssues.revalidate();
					pnlIssues.repaint();
					lstCategory.clearSelection();
				} else
				{
					String x;
					String[] getCatName;

					x = (String) lstCategory.getSelectedValue();
					getCatName = x.split(" ");
					String catName = "";
					for (int i = 2; i < getCatName.length; i++)
					{
						catName = catName.concat(getCatName[i]) + " ";
					}
					txtCatName.setText(catName.trim());
					String[] getID = txtCatName.getText().split(" ");
					String catPriority = getCatName[0];
					catPriority = Database.getCurrentCatPriority(catPriority)
							.trim();
					Connections.killRset();
					if (catPriority.equals("1"))
					{
						cmbCatPriority.setSelectedIndex(4);
					} else if (catPriority.equals("2"))
					{
						cmbCatPriority.setSelectedIndex(3);
					} else if (catPriority.equals("3"))
					{
						cmbCatPriority.setSelectedIndex(2);
					} else if (catPriority.equals("4"))
					{
						cmbCatPriority.setSelectedIndex(1);
					} else if (catPriority.equals("5"))
					{
						cmbCatPriority.setSelectedIndex(0);
					}

				}

				cmbCatPriority.revalidate();

			}

		});
		pnlIssues.add(lstCategory, gbc_lstCategory);

		JPanel pnlCatInfo = new JPanel();
		pnlCatInfo.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlCatInfo = new GridBagConstraints();
		gbc_pnlCatInfo.insets = new Insets(0, 0, 5, 0);
		gbc_pnlCatInfo.fill = GridBagConstraints.BOTH;
		gbc_pnlCatInfo.gridx = 1;
		gbc_pnlCatInfo.gridy = 1;
		pnlIssues.add(pnlCatInfo, gbc_pnlCatInfo);
		GridBagLayout gbl_pnlCatInfo = new GridBagLayout();
		gbl_pnlCatInfo.columnWidths = new int[]
		{ 0, 0, 0 };
		gbl_pnlCatInfo.rowHeights = new int[]
		{ 70, 30, 30, 80, 10, 0, 0 };
		gbl_pnlCatInfo.columnWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlCatInfo.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlCatInfo.setLayout(gbl_pnlCatInfo);

		JLabel lblCatName = new JLabel("Category Name: ");
		GridBagConstraints gbc_lblCatName = new GridBagConstraints();
		gbc_lblCatName.anchor = GridBagConstraints.EAST;
		gbc_lblCatName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCatName.gridx = 0;
		gbc_lblCatName.gridy = 1;
		pnlCatInfo.add(lblCatName, gbc_lblCatName);

		txtCatName = new JTextField();
		GridBagConstraints gbc_txtCatName = new GridBagConstraints();
		gbc_txtCatName.insets = new Insets(0, 0, 5, 0);
		gbc_txtCatName.fill = GridBagConstraints.BOTH;
		gbc_txtCatName.gridx = 1;
		gbc_txtCatName.gridy = 1;
		pnlCatInfo.add(txtCatName, gbc_txtCatName);
		txtCatName.setColumns(10);

		JLabel lblCatPriority = new JLabel("Category Default Priority: ");
		GridBagConstraints gbc_lblCatPriority = new GridBagConstraints();
		gbc_lblCatPriority.anchor = GridBagConstraints.EAST;
		gbc_lblCatPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblCatPriority.gridx = 0;
		gbc_lblCatPriority.gridy = 2;
		pnlCatInfo.add(lblCatPriority, gbc_lblCatPriority);

		cmbCatPriority = new JComboBox();
		cmbCatPriority.setEditable(true);
		cmbCatPriority.setBorder(new LineBorder(Color.BLACK));
		cmbCatPriority.setBackground(Color.WHITE);
		cmbCatPriority.setModel(new DefaultComboBoxModel(new String[]
		{ "5 - Critical", "4 - Urgent", "3 - High", "2 - Medium", "1 - Low" }));
		GridBagConstraints gbc_cmbCatPriority = new GridBagConstraints();
		gbc_cmbCatPriority.insets = new Insets(0, 0, 5, 0);
		gbc_cmbCatPriority.fill = GridBagConstraints.BOTH;
		gbc_cmbCatPriority.gridx = 1;
		gbc_cmbCatPriority.gridy = 2;
		pnlCatInfo.add(cmbCatPriority, gbc_cmbCatPriority);

		JPanel pnlIssueControls = new JPanel();
		pnlIssueControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlIssueControls = new GridBagConstraints();
		gbc_pnlIssueControls.gridwidth = 2;
		gbc_pnlIssueControls.fill = GridBagConstraints.BOTH;
		gbc_pnlIssueControls.gridx = 0;
		gbc_pnlIssueControls.gridy = 5;
		pnlCatInfo.add(pnlIssueControls, gbc_pnlIssueControls);
		pnlIssueControls.setLayout(new GridLayout(0, 3, 10, 5));

		JButton btnUpdateIssue = new JButton("Update");
		btnUpdateIssue.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				if (txtCatName.getText() == null
						|| txtCatName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,
							"Please fill in a category name.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else
				{
					int selected = 0;
					selected = lstCategory.getSelectedIndex();
					String x = (String) lstCategory.getSelectedValue();
					String[] getCatID = x.split(" ");
					String categoryID = getCatID[0];

					String categoryName = txtCatName.getText();
					String getPriority = (String) cmbCatPriority
							.getSelectedItem();
					String[] y = getPriority.split(" ");
					int categoryPriority = Integer.parseInt(y[0]);

					categoryID.trim();
					categoryName.trim();

					System.out.println(categoryID);
					System.out.println(categoryName);
					System.out.println(categoryPriority);

					Database.editCategory(categoryID, categoryName,
							categoryPriority);

					lstCategory.setSelectedIndex(-1);
					clm = new catListModel();
					lstCategory.setModel(clm);
					lstCategory.revalidate();
					lstCategory.repaint();
					lstCategory.setSelectedIndex(selected);
				}
				// Connections.killConnections();
				Connections.killRset();
			}
		});
		pnlIssueControls.add(btnUpdateIssue);

		JButton btnSaveNewIssue = new JButton("Save As New");
		btnSaveNewIssue.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				if (txtCatName.getText() == null
						|| txtCatName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,
							"Please fill in a category name.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else
				{
					int selected = 0;
					selected = lstCategory.getSelectedIndex();

					String categoryName = txtCatName.getText();

					String getPriority = (String) cmbCatPriority
							.getSelectedItem();
					String[] y = getPriority.split(" ");
					int categoryPriority = Integer.parseInt(y[0]);

					Database.addCategory(categoryName, categoryPriority);

					lstCategory.setSelectedIndex(-1);
					clm = new catListModel();
					lstCategory.setModel(clm);
					lstCategory.revalidate();
					lstCategory.repaint();
					lstCategory.setSelectedIndex(selected);
				}
				// Connections.killConnections();
				Connections.killRset();
			}
		});
		pnlIssueControls.add(btnSaveNewIssue);

		JButton btnDeleteIssue = new JButton("Delete");
		btnDeleteIssue.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				if (txtCatName.getText() == null
						|| txtCatName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,
							"Please fill in a category name.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else
				{
					int selected = 0;
					selected = lstCategory.getSelectedIndex();
					String x = (String) lstCategory.getSelectedValue();
					String[] getCatID = x.split(" ");
					String categoryID = getCatID[0];

					Database.deleteCategory(categoryID);

					lstCategory.setSelectedIndex(-1);
					clm = new catListModel();
					lstCategory.setModel(clm);
					lstCategory.revalidate();
					lstCategory.repaint();
					lstCategory.setSelectedIndex(selected);
				}
				Connections.killRset();
				// Connections.killConnections();
			}
		});
		pnlIssueControls.add(btnDeleteIssue);

		pnlTickets = new Panel();
		pnlTickets.setBackground(Color.WHITE);
		tabbedPane.addTab("Tickets", null, pnlTickets, null);
		GridBagLayout gbl_pnlTickets = new GridBagLayout();
		gbl_pnlTickets.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gbl_pnlTickets.rowHeights = new int[]
		{ 0, 2, 0, 5, 0 };
		gbl_pnlTickets.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlTickets.rowWeights = new double[]
		{ 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlTickets.setLayout(gbl_pnlTickets);

		final JPanel pnlTicketsAssigned = new JPanel();
		pnlTicketsAssigned.setBackground(Color.WHITE);
		pnlTicketsAssigned.setBorder(new TitledBorder(null,
				"Tickets Assigned to You", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlTicketsAssigned = new GridBagConstraints();
		gbc_pnlTicketsAssigned.insets = new Insets(0, 0, 5, 5);
		gbc_pnlTicketsAssigned.fill = GridBagConstraints.BOTH;
		gbc_pnlTicketsAssigned.gridx = 1;
		gbc_pnlTicketsAssigned.gridy = 0;
		pnlTickets.add(pnlTicketsAssigned, gbc_pnlTicketsAssigned);
		GridBagLayout gbl_pnlTicketsAssigned = new GridBagLayout();
		gbl_pnlTicketsAssigned.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gbl_pnlTicketsAssigned.rowHeights = new int[]
		{ 40, 0, 0 };
		gbl_pnlTicketsAssigned.columnWeights = new double[]
		{ 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlTicketsAssigned.rowWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		pnlTicketsAssigned.setLayout(gbl_pnlTicketsAssigned);

		final JPanel pnlQControl = new JPanel();
		pnlQControl.setBackground(Color.WHITE);
		pnlQControl.setBorder(new TitledBorder(null, "Query Ticket",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlQControl = new GridBagConstraints();
		gbc_pnlQControl.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQControl.fill = GridBagConstraints.BOTH;
		gbc_pnlQControl.gridx = 1;
		gbc_pnlQControl.gridy = 0;
		pnlTicketsAssigned.add(pnlQControl, gbc_pnlQControl);
		GridBagLayout gbl_pnlQControl = new GridBagLayout();
		gbl_pnlQControl.columnWidths = new int[]
		{ 78, 78, 78, 78, 78, 78, 0, 78, 0 };
		gbl_pnlQControl.rowHeights = new int[]
		{ 23, 0 };
		gbl_pnlQControl.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlQControl.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlQControl.setLayout(gbl_pnlQControl);

		JLabel lnlQID = new JLabel("ID: ");
		lnlQID.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lnlQID = new GridBagConstraints();
		gbc_lnlQID.anchor = GridBagConstraints.EAST;
		gbc_lnlQID.fill = GridBagConstraints.VERTICAL;
		gbc_lnlQID.insets = new Insets(0, 0, 0, 5);
		gbc_lnlQID.gridx = 0;
		gbc_lnlQID.gridy = 0;
		pnlQControl.add(lnlQID, gbc_lnlQID);

		txtQTicketID = new JTextField();
		txtQTicketID.setText(" ");
		GridBagConstraints gbc_txtQTicketID = new GridBagConstraints();
		gbc_txtQTicketID.fill = GridBagConstraints.BOTH;
		gbc_txtQTicketID.insets = new Insets(0, 0, 0, 5);
		gbc_txtQTicketID.gridx = 1;
		gbc_txtQTicketID.gridy = 0;
		pnlQControl.add(txtQTicketID, gbc_txtQTicketID);
		txtQTicketID.setColumns(10);

		JLabel lblQDepartment = new JLabel("Department: ");
		lblQDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblQDepartment = new GridBagConstraints();
		gbc_lblQDepartment.fill = GridBagConstraints.BOTH;
		gbc_lblQDepartment.insets = new Insets(0, 0, 0, 5);
		gbc_lblQDepartment.gridx = 2;
		gbc_lblQDepartment.gridy = 0;
		pnlQControl.add(lblQDepartment, gbc_lblQDepartment);

		final JComboBox cmbQDepartment = new JComboBox();
		staffDeptComboModel dcbm = new staffDeptComboModel();
		cmbQDepartment.setModel(dcbm);
		GridBagConstraints gbc_cmbQDepartment = new GridBagConstraints();
		gbc_cmbQDepartment.fill = GridBagConstraints.BOTH;
		gbc_cmbQDepartment.insets = new Insets(0, 0, 0, 5);
		gbc_cmbQDepartment.gridx = 3;
		gbc_cmbQDepartment.gridy = 0;
		pnlQControl.add(cmbQDepartment, gbc_cmbQDepartment);

		JLabel lblQPriority = new JLabel("Priority: ");
		lblQPriority.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblQPriority = new GridBagConstraints();
		gbc_lblQPriority.anchor = GridBagConstraints.EAST;
		gbc_lblQPriority.fill = GridBagConstraints.VERTICAL;
		gbc_lblQPriority.insets = new Insets(0, 0, 0, 5);
		gbc_lblQPriority.gridx = 4;
		gbc_lblQPriority.gridy = 0;
		pnlQControl.add(lblQPriority, gbc_lblQPriority);
		cmdQPriority.addPropertyChangeListener(new PropertyChangeListener()
		{

			public void propertyChange(PropertyChangeEvent arg0)
			{

				cmdQPriority.invalidate();
				cmdQPriority.revalidate();
				cmdQPriority.repaint();
			}
		});

		cmdQPriority.setModel(new DefaultComboBoxModel(new String[]
		{ "5 - Critical", "4 - Urgent", "3 - High", "2 - Medium", "1 - Low" }));
		cmdQPriority.setSelectedIndex(0);
		GridBagConstraints gbc_cmdQPriority = new GridBagConstraints();
		gbc_cmdQPriority.fill = GridBagConstraints.BOTH;
		gbc_cmdQPriority.insets = new Insets(0, 0, 0, 5);
		gbc_cmdQPriority.gridx = 5;
		gbc_cmdQPriority.gridy = 0;
		pnlQControl.add(cmdQPriority, gbc_cmdQPriority);

		JButton btnQueryTickets = new JButton("Query");
		btnQueryTickets.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				String department = null;
				if (cmbQDepartment.getSelectedIndex() == -1
						|| cmbQDepartment.getSelectedItem().toString()
								.equals(""))
				{
					department = null;
				} else
				{
					department = cmbQDepartment.getSelectedItem().toString()
							.split(" ").clone()[0];
				}
				currentAdminTicketTableModel.filterRows(
						txtQTicketID.getText(),
						department,
						Integer.parseInt(cmdQPriority.getSelectedItem()
								.toString().split(" ").clone()[0]));
				allAdminTicketTableModel.filterRows(
						txtQTicketID.getText(),
						department,
						Integer.parseInt(cmdQPriority.getSelectedItem()
								.toString().split(" ").clone()[0]));
				// /tblAssignedTickets.revalidate();
				// tblAssignedTickets.repaint();
				// tblAllTickets.revalidate();
				// tblAllTickets.repaint();

				contentPane.repaint();
				contentPane.revalidate();
				pnlTickets.repaint();
				cmdQPriority.invalidate();
				contentPane.invalidate();
				contentPane.revalidate();
				cmdQPriority.updateUI();
				// tblAssignedTickets.revalidate();
				// tblAllTickets.revalidate();
				// tblAssignedTickets.repaint();
				// tblAllTickets.repaint();
				// Connections.killRset();
				// cmdQPriority.revalidate();
				pnlQControl.revalidate();
				pnlQControl.repaint();
				pnlAllTickets.revalidate();
				pnlAllTickets.repaint();
				cmbQDepartment.setModel(cmbQDepartment.getModel());
				pnlQControl.repaint();
				pnlQControl.revalidate();
				tabbedPane.revalidate();
				tabbedPane.repaint();
				cmdQPriority.repaint();
				pnlQControl.requestDefaultFocus();
				pnlQControl.requestFocus();
				pnlQControl.requestFocusInWindow();
				pnlQControl.doLayout();
				cmbQDepartment.requestFocus();

				pnlQControl.grabFocus();
				adminUI.this.repaint();
				adminUI.this.setSize(adminUI.this.getWidth(),
						adminUI.this.getHeight());
				adminUI.this.invalidate();
				adminUI.this.validate();
				pnlQControl.invalidate();
				pnlQControl.validate();
				cmdQPriority.invalidate();
				cmdQPriority.validate();

				cmdQPriority = cmdQPriority;
			}

		});

		JButton btnClearTickets = new JButton("Clear");
		btnClearTickets.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				txtQTicketID.setText("");
				cmdQPriority.setSelectedIndex(0);
				cmbQDepartment.setSelectedItem(null);
				allAdminTicketTableModel.filterRows("", "", -1);
				currentAdminTicketTableModel.filterRows("", "", -1);
				cmdQPriority.revalidate();
				cmdQPriority.repaint();
				cmbQDepartment.revalidate();
				cmbQDepartment.repaint();
				tblAssignedTickets.revalidate();
				tblAssignedTickets.repaint();
				tblAllTickets.revalidate();
				tblAllTickets.repaint();
			}
		});
		GridBagConstraints gbc_btnClearTickets = new GridBagConstraints();
		gbc_btnClearTickets.insets = new Insets(0, 0, 0, 5);
		gbc_btnClearTickets.gridx = 6;
		gbc_btnClearTickets.gridy = 0;
		pnlQControl.add(btnClearTickets, gbc_btnClearTickets);

		GridBagConstraints gbc_btnQueryTickets = new GridBagConstraints();
		gbc_btnQueryTickets.fill = GridBagConstraints.BOTH;
		gbc_btnQueryTickets.gridx = 7;
		gbc_btnQueryTickets.gridy = 0;
		pnlQControl.add(btnQueryTickets, gbc_btnQueryTickets);

		JPanel pnlAssignTicketsTable = new JPanel();
		GridBagConstraints gbc_pnlAssignTicketsTable = new GridBagConstraints();
		gbc_pnlAssignTicketsTable.gridwidth = 3;
		gbc_pnlAssignTicketsTable.insets = new Insets(0, 0, 0, 5);
		gbc_pnlAssignTicketsTable.fill = GridBagConstraints.BOTH;
		gbc_pnlAssignTicketsTable.gridx = 0;
		gbc_pnlAssignTicketsTable.gridy = 1;
		pnlTicketsAssigned
				.add(pnlAssignTicketsTable, gbc_pnlAssignTicketsTable);
		pnlAssignTicketsTable.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane currentAdminTicketScrollPane = new JScrollPane();

		pnlAssignTicketsTable.add(currentAdminTicketScrollPane);

		currentAdminTicketTableModel catm = new currentAdminTicketTableModel(
				currentAdminID);
		tblAssignedTickets = new JTable();
		tblAssignedTickets
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAssignedTickets.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblAssignedTickets.setModel(catm);
		currentAdminTicketScrollPane.setViewportView(tblAssignedTickets);
		tblAssignedTickets.addMouseListener(new MouseAdapter()
		{

			public void mouseReleased(MouseEvent e)
			{

				Connections.killRset();
				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3)
				{
					System.out.println("Right");
					int r = tblAssignedTickets.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblAssignedTickets.getRowCount())
					{
						tblAssignedTickets.setRowSelectionInterval(r, r);
					} else
					{
						tblAssignedTickets.clearSelection();
					}

					int rowindex = tblAssignedTickets.getSelectedRow();
					if (rowindex < 0)
					{
						return;
					}

					int col = tblAssignedTickets.getColumnModel()
							.getColumnIndex("TICKETID");
					System.out.println(col);
					String ticketID = tblAssignedTickets.getValueAt(rowindex,
							col).toString();
					popup = createPopup(ticketID);
					popup.show(e.getComponent(), e.getX(), e.getY());
					System.out.println(rowindex);
				} else if (e.getClickCount() > 1
						&& e.getButton() == java.awt.event.MouseEvent.BUTTON1)
				{
					System.out.println("Double Click");
					int r = tblAssignedTickets.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblAssignedTickets.getRowCount())
					{
						tblAssignedTickets.setRowSelectionInterval(r, r);
					} else
					{
						tblAssignedTickets.clearSelection();
					}

					int rowindex = tblAssignedTickets.getSelectedRow();
					if (rowindex < 0)
					{
						return;
					}

					int col = tblAssignedTickets.getColumnModel()
							.getColumnIndex("TICKETID");
					System.out.println(col);
					String ticketID = tblAssignedTickets.getValueAt(rowindex,
							col).toString();
					Connections.killRset();
					System.out.println("Tick #  : " + ticketID);
					pnlTicketInfo = new TicketInformation(ticketID);
					pnlTicketInfo.setVisible(true);
					Connections.killRset();
				}

			}
		}

		);

		pnlAllTickets.setBackground(Color.WHITE);
		pnlAllTickets.setBorder(new TitledBorder(null, "All Tickets",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlAllTickets = new GridBagConstraints();
		gbc_pnlAllTickets.insets = new Insets(0, 0, 5, 5);
		gbc_pnlAllTickets.fill = GridBagConstraints.BOTH;
		gbc_pnlAllTickets.gridx = 1;
		gbc_pnlAllTickets.gridy = 2;
		pnlTickets.add(pnlAllTickets, gbc_pnlAllTickets);
		pnlAllTickets.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane allTicketScrollPane = new JScrollPane();
		pnlAllTickets.add(allTicketScrollPane);

		allAdminTicketTableModel aatm = new allAdminTicketTableModel();
		tblAllTickets = new JTable();

		tblAllTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAllTickets.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		allTicketScrollPane.setViewportView(tblAllTickets);
		tblAllTickets.setModel(aatm);

		tblAllTickets.addMouseListener(new MouseAdapter()
		{

			public void mouseReleased(MouseEvent e)
			{

				if (e.getButton() == java.awt.event.MouseEvent.BUTTON3)
				{
					System.out.println("Right");
					int r = tblAllTickets.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblAllTickets.getRowCount())
					{
						tblAllTickets.setRowSelectionInterval(r, r);
					} else
					{
						tblAllTickets.clearSelection();
					}

					int rowindex = tblAllTickets.getSelectedRow();
					if (rowindex < 0)
					{
						return;
					}

					int col = tblAllTickets.getColumnModel().getColumnIndex(
							"TICKETID");
					System.out.println(col);
					String ticketID = tblAllTickets.getValueAt(rowindex, col)
							.toString();
					popup = createPopup(ticketID);
					popup.show(e.getComponent(), e.getX(), e.getY());
					System.out.println(rowindex);
				} else if (e.getClickCount() > 1
						&& e.getButton() == java.awt.event.MouseEvent.BUTTON1)
				{
					System.out.println("Double Click");
					int r = tblAllTickets.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblAllTickets.getRowCount())
					{
						tblAllTickets.setRowSelectionInterval(r, r);
					} else
					{
						tblAllTickets.clearSelection();
					}

					int rowindex = tblAllTickets.getSelectedRow();
					if (rowindex < 0)
					{
						return;
					}

					int col = tblAllTickets.getColumnModel().getColumnIndex(
							"TICKETID");
					System.out.println(col);
					String ticketID = tblAllTickets.getValueAt(rowindex, col)
							.toString();
					Connections.killRset();
					pnlTicketInfo = new TicketInformation(ticketID);
					pnlTicketInfo.setVisible(true);
					Connections.killRset();
				}

				Connections.killRset();
			}
		});

		tabbedPane.addTab("Staff", null, pnlStaff, null);
		GridBagLayout gbl_pnlStaff = new GridBagLayout();
		gbl_pnlStaff.columnWidths = new int[]
		{ 0, 0 };
		gbl_pnlStaff.rowHeights = new int[]
		{ 35, 90, 0 };
		gbl_pnlStaff.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gbl_pnlStaff.rowWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		pnlStaff.setLayout(gbl_pnlStaff);

		JToolBar toolbarQueryChangeScreens = new JToolBar();
		toolbarQueryChangeScreens.setFloatable(false);
		toolbarQueryChangeScreens.setBackground(SystemColor.menu);
		// fl_pnlQueryChangeScreens.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_toolbarQueryChangeScreens = new GridBagConstraints();
		gbc_toolbarQueryChangeScreens.insets = new Insets(0, 0, 5, 0);
		gbc_toolbarQueryChangeScreens.fill = GridBagConstraints.BOTH;
		gbc_toolbarQueryChangeScreens.gridx = 0;
		gbc_toolbarQueryChangeScreens.gridy = 0;
		pnlStaff.add(toolbarQueryChangeScreens, gbc_toolbarQueryChangeScreens);

		JButton btnAddNewStaff = new JButton("Add New Staff");
		btnAddNewStaff.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				CardLayout staff = (CardLayout) (pnlStaffScreens.getLayout());
				staff.show(pnlStaffScreens, "addStaff");

			}
		});
		toolbarQueryChangeScreens.add(btnAddNewStaff);

		JButton btnEditStaff = new JButton("Edit Staff");
		btnEditStaff.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				CardLayout staff = (CardLayout) (pnlStaffScreens.getLayout());
				staff.show(pnlStaffScreens, "editStaff");
			}
		});
		toolbarQueryChangeScreens.add(btnEditStaff);

		pnlStaffScreens = new JPanel();
		GridBagConstraints gbc_pnlStaffScreens = new GridBagConstraints();
		gbc_pnlStaffScreens.fill = GridBagConstraints.BOTH;
		gbc_pnlStaffScreens.gridx = 0;
		gbc_pnlStaffScreens.gridy = 1;
		pnlStaff.add(pnlStaffScreens, gbc_pnlStaffScreens);
		pnlStaffScreens.setLayout(new CardLayout(0, 0));
		CardLayout cl_pnlStaffScreens = (CardLayout) (pnlStaffScreens
				.getLayout());

		JPanel pnlQStaffContainer = new JPanel();
		pnlQStaffContainer.setBackground(Color.WHITE);
		pnlStaffScreens.add(pnlQStaffContainer, "queryStaff");
		GridBagLayout gbl_pnlQStaffContainer = new GridBagLayout();
		gbl_pnlQStaffContainer.columnWidths = new int[]
		{ 10, 10, 55, 0, 55, 10, 10, 0 };
		gbl_pnlQStaffContainer.rowHeights = new int[]
		{ 10, 0, 10, 0, 10, 0 };
		gbl_pnlQStaffContainer.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlQStaffContainer.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlQStaffContainer.setLayout(gbl_pnlQStaffContainer);

		JPanel pnlQStaffControls = new JPanel();
		pnlQStaffControls.setBorder(new TitledBorder(null, "Query Staff",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlQStaffControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlQStaffControls = new GridBagConstraints();
		gbc_pnlQStaffControls.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQStaffControls.fill = GridBagConstraints.BOTH;
		gbc_pnlQStaffControls.gridx = 3;
		gbc_pnlQStaffControls.gridy = 1;
		pnlQStaffContainer.add(pnlQStaffControls, gbc_pnlQStaffControls);
		GridBagLayout gbl_pnlQStaffControls = new GridBagLayout();
		gbl_pnlQStaffControls.columnWidths = new int[]
		{ 0, 0, 0, 0, 0, 100, 0, 0, 0 };
		gbl_pnlQStaffControls.rowHeights = new int[]
		{ 0, 0, 0 };
		gbl_pnlQStaffControls.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlQStaffControls.rowWeights = new double[]
		{ 1.0, 1.0, Double.MIN_VALUE };
		pnlQStaffControls.setLayout(gbl_pnlQStaffControls);

		JLabel lblQStaffID = new JLabel("ID: ");
		lblQStaffID.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblQStaffID = new GridBagConstraints();
		gbc_lblQStaffID.anchor = GridBagConstraints.EAST;
		gbc_lblQStaffID.insets = new Insets(0, 0, 5, 5);
		gbc_lblQStaffID.gridx = 0;
		gbc_lblQStaffID.gridy = 0;
		pnlQStaffControls.add(lblQStaffID, gbc_lblQStaffID);

		txtQStaffID = new JTextField();
		txtQStaffID.setColumns(10);
		GridBagConstraints gbc_txtQStaffID = new GridBagConstraints();
		gbc_txtQStaffID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQStaffID.insets = new Insets(0, 0, 5, 5);
		gbc_txtQStaffID.gridx = 1;
		gbc_txtQStaffID.gridy = 0;
		pnlQStaffControls.add(txtQStaffID, gbc_txtQStaffID);

		JLabel lblQStaffName = new JLabel("Name: ");
		GridBagConstraints gbc_lblQStaffName = new GridBagConstraints();
		gbc_lblQStaffName.anchor = GridBagConstraints.EAST;
		gbc_lblQStaffName.insets = new Insets(0, 0, 5, 5);
		gbc_lblQStaffName.gridx = 2;
		gbc_lblQStaffName.gridy = 0;
		pnlQStaffControls.add(lblQStaffName, gbc_lblQStaffName);

		txtQStaffName = new JTextField();
		txtQStaffName.setColumns(10);
		GridBagConstraints gbc_txtQStaffName = new GridBagConstraints();
		gbc_txtQStaffName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQStaffName.insets = new Insets(0, 0, 5, 5);
		gbc_txtQStaffName.gridx = 3;
		gbc_txtQStaffName.gridy = 0;
		pnlQStaffControls.add(txtQStaffName, gbc_txtQStaffName);

		JLabel label_2 = new JLabel("Email: ");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.gridwidth = 2;
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 4;
		gbc_label_2.gridy = 0;
		pnlQStaffControls.add(label_2, gbc_label_2);

		txtQStaffEmail = new JTextField();
		txtQStaffEmail.setColumns(10);
		GridBagConstraints gbc_txtQStaffEmail = new GridBagConstraints();
		gbc_txtQStaffEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQStaffEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtQStaffEmail.gridx = 6;
		gbc_txtQStaffEmail.gridy = 0;
		pnlQStaffControls.add(txtQStaffEmail, gbc_txtQStaffEmail);

		JLabel lblQStaffType = new JLabel("Type: ");
		GridBagConstraints gbc_lblQStaffType = new GridBagConstraints();
		gbc_lblQStaffType.anchor = GridBagConstraints.EAST;
		gbc_lblQStaffType.insets = new Insets(0, 0, 0, 5);
		gbc_lblQStaffType.gridx = 0;
		gbc_lblQStaffType.gridy = 1;
		pnlQStaffControls.add(lblQStaffType, gbc_lblQStaffType);

		JComboBox cmbQStaffType = new JComboBox();
		GridBagConstraints gbc_cmbQStaffType = new GridBagConstraints();
		gbc_cmbQStaffType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbQStaffType.insets = new Insets(0, 0, 0, 5);
		gbc_cmbQStaffType.gridx = 1;
		gbc_cmbQStaffType.gridy = 1;
		pnlQStaffControls.add(cmbQStaffType, gbc_cmbQStaffType);

		JLabel lblQStaffDept = new JLabel("Department: ");
		GridBagConstraints gbc_lblQStaffDept = new GridBagConstraints();
		gbc_lblQStaffDept.anchor = GridBagConstraints.EAST;
		gbc_lblQStaffDept.insets = new Insets(0, 0, 0, 5);
		gbc_lblQStaffDept.gridx = 2;
		gbc_lblQStaffDept.gridy = 1;
		pnlQStaffControls.add(lblQStaffDept, gbc_lblQStaffDept);

		cmbQStaffDept = new JComboBox();
		cmbQStaffDept.setModel(stcm);
		GridBagConstraints gbc_cmbQStaffDept = new GridBagConstraints();
		gbc_cmbQStaffDept.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbQStaffDept.insets = new Insets(0, 0, 0, 5);
		gbc_cmbQStaffDept.gridx = 3;
		gbc_cmbQStaffDept.gridy = 1;
		pnlQStaffControls.add(cmbQStaffDept, gbc_cmbQStaffDept);

		JButton btnQStaffQuery = new JButton("Query");
		btnQStaffQuery.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				String department = null;
				if (cmbQStaffDept.getSelectedIndex() == -1
						|| cmbQStaffDept.getSelectedItem().toString()
								.equals(""))
				{
					department = " ";
				} else
				{
					department = cmbQStaffDept.getSelectedItem().toString()
							.split(" ").clone()[0];
				}
				staffTableModel.filterRows(txtQStaffID.getText(), txtQStaffName
						.getText().replace(" ", "%"), txtQStaffEmail.getText(),
						department);
				// tblQStaffTable.revalidate();
				tblQStaffTable.repaint();
			}
		});

		JButton btnClearStaff = new JButton("Clear");
		btnClearStaff.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				txtQStaffID.setText("");
				txtQStaffName.setText("");
				txtQStaffEmail.setText("");
				cmbQStaffDept.setSelectedIndex(-1);

			}
		});
		GridBagConstraints gbc_btnClearStaff = new GridBagConstraints();
		gbc_btnClearStaff.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClearStaff.insets = new Insets(0, 0, 0, 5);
		gbc_btnClearStaff.gridx = 5;
		gbc_btnClearStaff.gridy = 1;
		pnlQStaffControls.add(btnClearStaff, gbc_btnClearStaff);
		GridBagConstraints gbc_btnQStaffQuery = new GridBagConstraints();
		gbc_btnQStaffQuery.anchor = GridBagConstraints.EAST;
		gbc_btnQStaffQuery.insets = new Insets(0, 0, 0, 5);
		gbc_btnQStaffQuery.gridx = 6;
		gbc_btnQStaffQuery.gridy = 1;
		pnlQStaffControls.add(btnQStaffQuery, gbc_btnQStaffQuery);

		JPanel pnlQStaffTableContainer = new JPanel();
		GridBagConstraints gbc_pnlQStaffTableContainer = new GridBagConstraints();
		gbc_pnlQStaffTableContainer.gridwidth = 5;
		gbc_pnlQStaffTableContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQStaffTableContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlQStaffTableContainer.gridx = 1;
		gbc_pnlQStaffTableContainer.gridy = 3;
		pnlQStaffContainer.add(pnlQStaffTableContainer,
				gbc_pnlQStaffTableContainer);
		pnlQStaffTableContainer.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane QStaffTableScroll = new JScrollPane();
		pnlQStaffTableContainer.add(QStaffTableScroll);

		staffTableModel stm = new staffTableModel();
		tblQStaffTable = new JTable();
		tblQStaffTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		QStaffTableScroll.setViewportView(tblQStaffTable);
		tblQStaffTable.setModel(stm);

		JPanel pnlAddStaffContainer = new JPanel();
		pnlAddStaffContainer.setBackground(Color.WHITE);
		pnlStaffScreens.add(pnlAddStaffContainer, "addStaff");
		GridBagLayout gbl_pnlAddStaffContainer = new GridBagLayout();
		gbl_pnlAddStaffContainer.columnWidths = new int[]
		{ 55, 0, 55, 0 };
		gbl_pnlAddStaffContainer.rowHeights = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlAddStaffContainer.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlAddStaffContainer.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlAddStaffContainer.setLayout(gbl_pnlAddStaffContainer);

		JPanel pnlAddStaffNewContainer = new JPanel();
		pnlAddStaffNewContainer.setBackground(Color.WHITE);
		pnlAddStaffNewContainer.setBorder(new TitledBorder(null,
				"New Staff Member", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		GridBagConstraints gbc_pnlAddStaffNewContainer = new GridBagConstraints();
		gbc_pnlAddStaffNewContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlAddStaffNewContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlAddStaffNewContainer.gridx = 1;
		gbc_pnlAddStaffNewContainer.gridy = 1;
		pnlAddStaffContainer.add(pnlAddStaffNewContainer,
				gbc_pnlAddStaffNewContainer);
		GridBagLayout gbl_pnlAddStaffNewContainer = new GridBagLayout();
		gbl_pnlAddStaffNewContainer.columnWidths = new int[]
		{ 10, 0, 200, 10, 0, 200, 10, 0 };
		gbl_pnlAddStaffNewContainer.rowHeights = new int[]
		{ 0, 20, 20, 20, 20, 5, 45, 0 };
		gbl_pnlAddStaffNewContainer.columnWeights = new double[]
		{ 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlAddStaffNewContainer.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlAddStaffNewContainer.setLayout(gbl_pnlAddStaffNewContainer);

		JLabel lblAddStaffFirstName = new JLabel("First Name: ");
		GridBagConstraints gbc_lblAddStaffFirstName = new GridBagConstraints();
		gbc_lblAddStaffFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffFirstName.gridx = 1;
		gbc_lblAddStaffFirstName.gridy = 1;
		pnlAddStaffNewContainer.add(lblAddStaffFirstName,
				gbc_lblAddStaffFirstName);

		txtAddStaffFirstName = new JTextField();
		GridBagConstraints gbc_txtAddStaffFirstName = new GridBagConstraints();
		gbc_txtAddStaffFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddStaffFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddStaffFirstName.gridx = 2;
		gbc_txtAddStaffFirstName.gridy = 1;
		pnlAddStaffNewContainer.add(txtAddStaffFirstName,
				gbc_txtAddStaffFirstName);
		txtAddStaffFirstName.setColumns(10);

		JLabel lblAddStaffDept = new JLabel("Department: ");
		GridBagConstraints gbc_lblAddStaffDept = new GridBagConstraints();
		gbc_lblAddStaffDept.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffDept.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffDept.gridx = 4;
		gbc_lblAddStaffDept.gridy = 1;
		pnlAddStaffNewContainer.add(lblAddStaffDept, gbc_lblAddStaffDept);

		cmbAddStaffDept = new JComboBox();
		cmbAddStaffDept.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				cmbAddStaffDept.revalidate();
				cmbAddStaffDept.repaint();
			}
		});
		cmbAddStaffDept.setModel(stcm);
		GridBagConstraints gbc_cmbAddStaffDept = new GridBagConstraints();
		gbc_cmbAddStaffDept.insets = new Insets(0, 0, 5, 5);
		gbc_cmbAddStaffDept.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbAddStaffDept.gridx = 5;
		gbc_cmbAddStaffDept.gridy = 1;
		pnlAddStaffNewContainer.add(cmbAddStaffDept, gbc_cmbAddStaffDept);

		JLabel lblAddStaffLastName = new JLabel("Last Name: ");
		GridBagConstraints gbc_lblAddStaffLastName = new GridBagConstraints();
		gbc_lblAddStaffLastName.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffLastName.gridx = 1;
		gbc_lblAddStaffLastName.gridy = 2;
		pnlAddStaffNewContainer.add(lblAddStaffLastName,
				gbc_lblAddStaffLastName);

		txtAddStaffLastName = new JTextField();
		GridBagConstraints gbc_txtAddStaffLastName = new GridBagConstraints();
		gbc_txtAddStaffLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddStaffLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddStaffLastName.gridx = 2;
		gbc_txtAddStaffLastName.gridy = 2;
		pnlAddStaffNewContainer.add(txtAddStaffLastName,
				gbc_txtAddStaffLastName);
		txtAddStaffLastName.setColumns(10);

		JLabel lblAddStaffEmail = new JLabel("Email: ");
		GridBagConstraints gbc_lblAddStaffEmail = new GridBagConstraints();
		gbc_lblAddStaffEmail.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffEmail.gridx = 4;
		gbc_lblAddStaffEmail.gridy = 2;
		pnlAddStaffNewContainer.add(lblAddStaffEmail, gbc_lblAddStaffEmail);

		txtAddStaffEmail = new JTextField();
		GridBagConstraints gbc_txtAddStaffEmail = new GridBagConstraints();
		gbc_txtAddStaffEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddStaffEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddStaffEmail.gridx = 5;
		gbc_txtAddStaffEmail.gridy = 2;
		pnlAddStaffNewContainer.add(txtAddStaffEmail, gbc_txtAddStaffEmail);
		txtAddStaffEmail.setColumns(10);

		JLabel lblAddStaffPassword = new JLabel("Password: ");
		GridBagConstraints gbc_lblAddStaffPassword = new GridBagConstraints();
		gbc_lblAddStaffPassword.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffPassword.gridx = 1;
		gbc_lblAddStaffPassword.gridy = 3;
		pnlAddStaffNewContainer.add(lblAddStaffPassword,
				gbc_lblAddStaffPassword);

		pwAddStaffPassword = new JPasswordField();
		GridBagConstraints gbc_pwAddStaffPassword = new GridBagConstraints();
		gbc_pwAddStaffPassword.insets = new Insets(0, 0, 5, 5);
		gbc_pwAddStaffPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwAddStaffPassword.gridx = 2;
		gbc_pwAddStaffPassword.gridy = 3;
		pnlAddStaffNewContainer.add(pwAddStaffPassword, gbc_pwAddStaffPassword);

		JLabel lblAddStaffOfficePhone = new JLabel("Office Phone: ");
		GridBagConstraints gbc_lblAddStaffOfficePhone = new GridBagConstraints();
		gbc_lblAddStaffOfficePhone.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffOfficePhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffOfficePhone.gridx = 4;
		gbc_lblAddStaffOfficePhone.gridy = 3;
		pnlAddStaffNewContainer.add(lblAddStaffOfficePhone,
				gbc_lblAddStaffOfficePhone);

		txtAddStaffOfficePhone = new JTextField();
		GridBagConstraints gbc_txtAddStaffOfficePhone = new GridBagConstraints();
		gbc_txtAddStaffOfficePhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddStaffOfficePhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddStaffOfficePhone.gridx = 5;
		gbc_txtAddStaffOfficePhone.gridy = 3;
		pnlAddStaffNewContainer.add(txtAddStaffOfficePhone,
				gbc_txtAddStaffOfficePhone);
		txtAddStaffOfficePhone.setColumns(10);

		JLabel lblAddStaffPasswordVer = new JLabel("Password Verification: ");
		GridBagConstraints gbc_lblAddStaffPasswordVer = new GridBagConstraints();
		gbc_lblAddStaffPasswordVer.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffPasswordVer.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffPasswordVer.gridx = 1;
		gbc_lblAddStaffPasswordVer.gridy = 4;
		pnlAddStaffNewContainer.add(lblAddStaffPasswordVer,
				gbc_lblAddStaffPasswordVer);

		pwAddStaffPasswordVer = new JPasswordField();
		GridBagConstraints gbc_pwAddStaffPasswordVer = new GridBagConstraints();
		gbc_pwAddStaffPasswordVer.insets = new Insets(0, 0, 5, 5);
		gbc_pwAddStaffPasswordVer.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwAddStaffPasswordVer.gridx = 2;
		gbc_pwAddStaffPasswordVer.gridy = 4;
		pnlAddStaffNewContainer.add(pwAddStaffPasswordVer,
				gbc_pwAddStaffPasswordVer);

		JLabel lblAddStaffMobilePhone = new JLabel("Mobile Phone: ");
		GridBagConstraints gbc_lblAddStaffMobilePhone = new GridBagConstraints();
		gbc_lblAddStaffMobilePhone.anchor = GridBagConstraints.EAST;
		gbc_lblAddStaffMobilePhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddStaffMobilePhone.gridx = 4;
		gbc_lblAddStaffMobilePhone.gridy = 4;
		pnlAddStaffNewContainer.add(lblAddStaffMobilePhone,
				gbc_lblAddStaffMobilePhone);

		txtAddStaffMobilePhone = new JTextField();
		GridBagConstraints gbc_txtAddStaffMobilePhone = new GridBagConstraints();
		gbc_txtAddStaffMobilePhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddStaffMobilePhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddStaffMobilePhone.gridx = 5;
		gbc_txtAddStaffMobilePhone.gridy = 4;
		pnlAddStaffNewContainer.add(txtAddStaffMobilePhone,
				gbc_txtAddStaffMobilePhone);
		txtAddStaffMobilePhone.setColumns(10);

		JPanel pnlAddStaffUserType = new JPanel();
		pnlAddStaffUserType.setBackground(Color.WHITE);
		pnlAddStaffUserType.setBorder(new TitledBorder(null, "User Type",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlAddStaffUserType = new GridBagConstraints();
		gbc_pnlAddStaffUserType.insets = new Insets(0, 0, 0, 5);
		gbc_pnlAddStaffUserType.fill = GridBagConstraints.BOTH;
		gbc_pnlAddStaffUserType.gridx = 1;
		gbc_pnlAddStaffUserType.gridy = 6;
		pnlAddStaffNewContainer.add(pnlAddStaffUserType,
				gbc_pnlAddStaffUserType);

		rdbtnAddStaffTypeAdmin = new JRadioButton("Admin");
		rdbtnAddStaffTypeAdmin.setBackground(Color.WHITE);
		rdbtnAddStaffTypeAdmin.setForeground(Color.RED);

		rdbtnAddStaffTypeStaff = new JRadioButton("Staff");
		rdbtnAddStaffTypeStaff.setBackground(Color.WHITE);
		rdbtnAddStaffTypeStaff.setSelected(true);
		pnlAddStaffUserType.setLayout(new GridLayout(0, 1, 0, 0));
		pnlAddStaffUserType.add(rdbtnAddStaffTypeAdmin);
		pnlAddStaffUserType.add(rdbtnAddStaffTypeStaff);

		AddStaffUserType.add(rdbtnAddStaffTypeStaff);
		AddStaffUserType.add(rdbtnAddStaffTypeAdmin);

		JPanel pnlAddStaffDetails = new JPanel();
		pnlAddStaffDetails.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlAddStaffDetails = new GridBagConstraints();
		gbc_pnlAddStaffDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlAddStaffDetails.gridwidth = 3;
		gbc_pnlAddStaffDetails.insets = new Insets(0, 0, 0, 5);
		gbc_pnlAddStaffDetails.gridx = 3;
		gbc_pnlAddStaffDetails.gridy = 6;
		pnlAddStaffNewContainer.add(pnlAddStaffDetails, gbc_pnlAddStaffDetails);
		GridBagLayout gbl_pnlAddStaffDetails = new GridBagLayout();
		gbl_pnlAddStaffDetails.columnWidths = new int[]
		{ 96, 96, 96, 0 };
		gbl_pnlAddStaffDetails.rowHeights = new int[]
		{ 0, 34, 5, 0 };
		gbl_pnlAddStaffDetails.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlAddStaffDetails.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlAddStaffDetails.setLayout(gbl_pnlAddStaffDetails);

		JButton btnAddStaffSubmit = new JButton("Submit");
		btnAddStaffSubmit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				String firstName = null;
				String lastName = null;
				char[] getPassword = null;
				char[] getPasswordVer = null;
				String[] getID = null;
				String password = "";
				String passwordVer = "";
				String email = null;
				int officeNumber = 0;
				int mobileNumber = 0;
				String departmentID = null;

				if (txtAddStaffFirstName.getText().trim().equals(null)
						|| txtAddStaffFirstName.getText().trim().equals("")
						|| pwAddStaffPassword.getPassword().length == 0
						|| pwAddStaffPasswordVer.getPassword().length == 0
						|| txtAddStaffEmail.getText().trim().equals(null)
						|| txtAddStaffEmail.getText().trim().equals("")
						|| txtAddStaffOfficePhone.getText().trim().equals(null)
						|| txtAddStaffOfficePhone.getText().trim().equals("")
						|| txtAddStaffMobilePhone.getText().trim().equals(null)
						|| txtAddStaffMobilePhone.getText().trim().equals("")
						|| cmbAddStaffDept.getSelectedIndex() == -1)
				{
					System.out.println("Please Fill Out All Fields");
				} else
				{

					if (pwAddStaffPassword.getPassword().length > 0
							&& pwAddStaffPasswordVer.getPassword().length > 0)
					{
						getPassword = pwAddStaffPassword.getPassword();
						getPasswordVer = pwAddStaffPasswordVer.getPassword();

						for (int i = 0; i < getPassword.length; i++)
						{
							password = password + getPassword[i];
						}

						for (int i = 0; i < getPasswordVer.length; i++)
						{
							passwordVer = passwordVer + getPasswordVer[i];
						}

						if (password.equals(passwordVer))
						{
							System.out.println("Matching Password");
							firstName = txtAddStaffFirstName.getText().trim();
							lastName = txtAddStaffLastName.getText().trim();
							email = txtAddStaffEmail.getText().trim();
							officeNumber = Integer
									.parseInt(txtAddStaffOfficePhone.getText()
											.trim());
							mobileNumber = Integer
									.parseInt(txtAddStaffMobilePhone.getText()
											.trim());
							String x = (String) cmbAddStaffDept
									.getSelectedItem();
							getID = x.split(" ");
							departmentID = getID[0];
							if (rdbtnAddStaffTypeAdmin.isSelected())
							{
								Database.addAdmin(password, firstName,
										lastName, email, officeNumber,
										mobileNumber, true, true, true, true,
										true);
								staffTableModel stm = new staffTableModel();
								tblQStaffTable.setModel(stm);
								tblQStaffTable.revalidate();
								tblQStaffTable.repaint();

							} else
							{
								Database.addStaff(password, firstName,
										lastName, email, officeNumber,
										mobileNumber, departmentID, true, true);
								staffTableModel stm = new staffTableModel();
								tblQStaffTable.setModel(stm);
								tblQStaffTable.revalidate();
								tblQStaffTable.repaint();
							}

						} else
						{
							System.out.println("Non-Matching Password");

						}
					} else
					{
						System.out.println("Enter Password");
					}
				}

			}
		});
		GridBagConstraints gbc_btnAddStaffSubmit = new GridBagConstraints();
		gbc_btnAddStaffSubmit.fill = GridBagConstraints.BOTH;
		gbc_btnAddStaffSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddStaffSubmit.gridx = 0;
		gbc_btnAddStaffSubmit.gridy = 1;
		pnlAddStaffDetails.add(btnAddStaffSubmit, gbc_btnAddStaffSubmit);

		JButton btnAddStaffReset = new JButton("Reset");
		GridBagConstraints gbc_btnAddStaffReset = new GridBagConstraints();
		gbc_btnAddStaffReset.fill = GridBagConstraints.BOTH;
		gbc_btnAddStaffReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddStaffReset.gridx = 1;
		gbc_btnAddStaffReset.gridy = 1;
		pnlAddStaffDetails.add(btnAddStaffReset, gbc_btnAddStaffReset);

		btnAddStaffCancel = new JButton("Cancel");
		btnAddStaffCancel.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				CardLayout staff = (CardLayout) (pnlStaffScreens.getLayout());
				staff.show(pnlStaffScreens, "queryStaff");
			}
		});
		GridBagConstraints gbc_btnAddStaffCancel = new GridBagConstraints();
		gbc_btnAddStaffCancel.fill = GridBagConstraints.BOTH;
		gbc_btnAddStaffCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddStaffCancel.gridx = 2;
		gbc_btnAddStaffCancel.gridy = 1;
		pnlAddStaffDetails.add(btnAddStaffCancel, gbc_btnAddStaffCancel);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		pnlAddStaffDetails.add(label, gbc_label);

		final JPanel pnlEditStaff = new JPanel();
		pnlEditStaff.setBackground(Color.WHITE);
		pnlStaffScreens.add(pnlEditStaff, "editStaff");
		GridBagLayout gbl_pnlEditStaff = new GridBagLayout();
		gbl_pnlEditStaff.columnWidths = new int[]
		{ 55, 265, 0, 55, 0 };
		gbl_pnlEditStaff.rowHeights = new int[]
		{ 10, 2, 0, 0, 10, 0 };
		gbl_pnlEditStaff.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlEditStaff.rowWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlEditStaff.setLayout(gbl_pnlEditStaff);

		final JPanel pnlEditUserChoose = new JPanel();
		pnlEditUserChoose.setBackground(Color.WHITE);
		pnlEditUserChoose.setBorder(new TitledBorder(null, "Select User",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlEditUserChoose = new GridBagConstraints();
		gbc_pnlEditUserChoose.insets = new Insets(0, 0, 5, 5);
		gbc_pnlEditUserChoose.fill = GridBagConstraints.BOTH;
		gbc_pnlEditUserChoose.gridx = 1;
		gbc_pnlEditUserChoose.gridy = 0;
		pnlEditStaff.add(pnlEditUserChoose, gbc_pnlEditUserChoose);
		GridBagLayout gbl_pnlEditUserChoose = new GridBagLayout();
		gbl_pnlEditUserChoose.columnWidths = new int[]
		{ 0, 0, 0 };
		gbl_pnlEditUserChoose.rowHeights = new int[]
		{ 25, 0 };
		gbl_pnlEditUserChoose.columnWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlEditUserChoose.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlEditUserChoose.setLayout(gbl_pnlEditUserChoose);

		JLabel lblEditChooseName = new JLabel("Name: ");
		GridBagConstraints gbc_lblEditChooseName = new GridBagConstraints();
		gbc_lblEditChooseName.insets = new Insets(0, 0, 0, 5);
		gbc_lblEditChooseName.anchor = GridBagConstraints.EAST;
		gbc_lblEditChooseName.gridx = 0;
		gbc_lblEditChooseName.gridy = 0;
		pnlEditUserChoose.add(lblEditChooseName, gbc_lblEditChooseName);

		scm = new staffComboBoxModel();
		final JComboBox cmbEditUserChooseName = new JComboBox();
		cmbEditUserChooseName.setModel(scm);
		cmbEditUserChooseName.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				int index = cmbEditUserChooseName.getSelectedIndex();
				String getID = (String) cmbEditUserChooseName.getItemAt(index);
				String deptID = null;
				String getDeptID;
				String[] x;
				String[] y = getID.split(" ");
				String staffID = y[0];
				Database.getStaffDetails(staffID);
				try
				{
					txtEditStaffFirstName.setText(Connections.rset.getString(
							"FIRSTNAME").trim());
					txtEditStaffLastName.setText(Connections.rset.getString(
							"LASTNAME").trim());
					if (staffID.toUpperCase().charAt(0) == 'A')
					{
						cmbEditStaffDept.setEnabled(false);
						rdbEditStaffTypeAdmin.setSelected(true);
					} else
					{
						cmbEditStaffDept.setEnabled(true);
						rdbEditStaffTypeStaff.setSelected(true);
						deptID = Connections.rset.getString("DEPARTMENTID")
								.trim();
					}

					txtEditStaffEmail.setText(Connections.rset.getString(
							"EMAIL").trim());
					txtEditStaffOfficePhone.setText(Connections.rset
							.getString("OFFICENUMBER"));
					txtEditStaffMobilePhone.setText(Connections.rset
							.getString("MOBILENUMBER"));

					// Connections.killConnections();

					for (int i = 0; i < cmbEditStaffDept.getItemCount(); i++)
					{
						getDeptID = (String) cmbEditStaffDept.getItemAt(i);
						y = getDeptID.split(" ");
						if (deptID == null)
						{
							cmbEditStaffDept.setSelectedItem(null);
						} else if (deptID.equals(y[0]))
						{
							cmbEditStaffDept.setSelectedIndex(i);
						}

					}

					// Connections.killConnections();
					pnlEditStaff.repaint();
					cmbEditUserChooseName.revalidate();
					cmbEditStaffDept.revalidate();
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				Connections.killRset();
				// Connections.killConnections();
			}
		});
		GridBagConstraints gbc_cmbEditUserChooseName = new GridBagConstraints();
		gbc_cmbEditUserChooseName.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbEditUserChooseName.gridx = 1;
		gbc_cmbEditUserChooseName.gridy = 0;
		pnlEditUserChoose.add(cmbEditUserChooseName, gbc_cmbEditUserChooseName);

		JPanel pnlEditStaffDetails = new JPanel();
		pnlEditStaffDetails.setBorder(new TitledBorder(null, "User Details",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlEditStaffDetails.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlEditStaffDetails = new GridBagConstraints();
		gbc_pnlEditStaffDetails.gridheight = 2;
		gbc_pnlEditStaffDetails.gridwidth = 2;
		gbc_pnlEditStaffDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlEditStaffDetails.insets = new Insets(0, 0, 5, 5);
		gbc_pnlEditStaffDetails.gridx = 1;
		gbc_pnlEditStaffDetails.gridy = 2;
		pnlEditStaff.add(pnlEditStaffDetails, gbc_pnlEditStaffDetails);
		GridBagLayout gbl_pnlEditStaffDetails = new GridBagLayout();
		gbl_pnlEditStaffDetails.columnWidths = new int[]
		{ 10, 0, 200, 10, 0, 200, 10, 0 };
		gbl_pnlEditStaffDetails.rowHeights = new int[]
		{ 0, 20, 20, 20, 20, 45, 0 };
		gbl_pnlEditStaffDetails.columnWeights = new double[]
		{ 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlEditStaffDetails.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlEditStaffDetails.setLayout(gbl_pnlEditStaffDetails);

		JLabel lblEditFirstName = new JLabel("First Name: ");
		GridBagConstraints gbc_lblEditFirstName = new GridBagConstraints();
		gbc_lblEditFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblEditFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditFirstName.gridx = 1;
		gbc_lblEditFirstName.gridy = 1;
		pnlEditStaffDetails.add(lblEditFirstName, gbc_lblEditFirstName);

		txtEditStaffFirstName = new JTextField();
		txtEditStaffFirstName.setColumns(10);
		GridBagConstraints gbc_txtEditStaffFirstName = new GridBagConstraints();
		gbc_txtEditStaffFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditStaffFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtEditStaffFirstName.gridx = 2;
		gbc_txtEditStaffFirstName.gridy = 1;
		pnlEditStaffDetails.add(txtEditStaffFirstName,
				gbc_txtEditStaffFirstName);

		JLabel lblEditStaffDept = new JLabel("Department: ");
		GridBagConstraints gbc_lblEditStaffDept = new GridBagConstraints();
		gbc_lblEditStaffDept.anchor = GridBagConstraints.EAST;
		gbc_lblEditStaffDept.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditStaffDept.gridx = 4;
		gbc_lblEditStaffDept.gridy = 1;
		pnlEditStaffDetails.add(lblEditStaffDept, gbc_lblEditStaffDept);

		cmbEditStaffDept = new JComboBox();
		cmbEditStaffDept.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				cmbEditStaffDept.revalidate();
				cmbEditStaffDept.repaint();
			}
		});
		cmbEditStaffDept.setModel(stcm);
		GridBagConstraints gbc_cmbEditStaffDept = new GridBagConstraints();
		gbc_cmbEditStaffDept.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbEditStaffDept.insets = new Insets(0, 0, 5, 5);
		gbc_cmbEditStaffDept.gridx = 5;
		gbc_cmbEditStaffDept.gridy = 1;
		pnlEditStaffDetails.add(cmbEditStaffDept, gbc_cmbEditStaffDept);

		JLabel lblEditStaffLastName = new JLabel("Last Name: ");
		GridBagConstraints gbc_lblEditStaffLastName = new GridBagConstraints();
		gbc_lblEditStaffLastName.anchor = GridBagConstraints.EAST;
		gbc_lblEditStaffLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditStaffLastName.gridx = 1;
		gbc_lblEditStaffLastName.gridy = 2;
		pnlEditStaffDetails.add(lblEditStaffLastName, gbc_lblEditStaffLastName);

		txtEditStaffLastName = new JTextField();
		txtEditStaffLastName.setColumns(10);
		GridBagConstraints gbc_txtEditStaffLastName = new GridBagConstraints();
		gbc_txtEditStaffLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditStaffLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtEditStaffLastName.gridx = 2;
		gbc_txtEditStaffLastName.gridy = 2;
		pnlEditStaffDetails.add(txtEditStaffLastName, gbc_txtEditStaffLastName);

		JLabel lblEditStaffEmail = new JLabel("Email: ");
		GridBagConstraints gbc_lblEditStaffEmail = new GridBagConstraints();
		gbc_lblEditStaffEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEditStaffEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditStaffEmail.gridx = 4;
		gbc_lblEditStaffEmail.gridy = 2;
		pnlEditStaffDetails.add(lblEditStaffEmail, gbc_lblEditStaffEmail);

		txtEditStaffEmail = new JTextField();
		txtEditStaffEmail.setColumns(10);
		GridBagConstraints gbc_txtEditStaffEmail = new GridBagConstraints();
		gbc_txtEditStaffEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditStaffEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEditStaffEmail.gridx = 5;
		gbc_txtEditStaffEmail.gridy = 2;
		pnlEditStaffDetails.add(txtEditStaffEmail, gbc_txtEditStaffEmail);

		JLabel lblEditStaffPassword = new JLabel("Password: ");
		GridBagConstraints gbc_lblEditStaffPassword = new GridBagConstraints();
		gbc_lblEditStaffPassword.anchor = GridBagConstraints.EAST;
		gbc_lblEditStaffPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditStaffPassword.gridx = 1;
		gbc_lblEditStaffPassword.gridy = 3;
		pnlEditStaffDetails.add(lblEditStaffPassword, gbc_lblEditStaffPassword);

		pwEditStaffPassword = new JPasswordField();
		GridBagConstraints gbc_pwEditStaffPassword = new GridBagConstraints();
		gbc_pwEditStaffPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwEditStaffPassword.insets = new Insets(0, 0, 5, 5);
		gbc_pwEditStaffPassword.gridx = 2;
		gbc_pwEditStaffPassword.gridy = 3;
		pnlEditStaffDetails.add(pwEditStaffPassword, gbc_pwEditStaffPassword);

		JLabel lblEditStaffOfficePhone = new JLabel("Office Phone: ");
		GridBagConstraints gbc_lblEditStaffOfficePhone = new GridBagConstraints();
		gbc_lblEditStaffOfficePhone.anchor = GridBagConstraints.EAST;
		gbc_lblEditStaffOfficePhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditStaffOfficePhone.gridx = 4;
		gbc_lblEditStaffOfficePhone.gridy = 3;
		pnlEditStaffDetails.add(lblEditStaffOfficePhone,
				gbc_lblEditStaffOfficePhone);

		txtEditStaffOfficePhone = new JTextField();
		txtEditStaffOfficePhone.setColumns(10);
		GridBagConstraints gbc_txtEditStaffOfficePhone = new GridBagConstraints();
		gbc_txtEditStaffOfficePhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditStaffOfficePhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtEditStaffOfficePhone.gridx = 5;
		gbc_txtEditStaffOfficePhone.gridy = 3;
		pnlEditStaffDetails.add(txtEditStaffOfficePhone,
				gbc_txtEditStaffOfficePhone);

		JLabel lblEditStaffPasswordVer = new JLabel("Password Verification: ");
		GridBagConstraints gbc_lblEditStaffPasswordVer = new GridBagConstraints();
		gbc_lblEditStaffPasswordVer.anchor = GridBagConstraints.EAST;
		gbc_lblEditStaffPasswordVer.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditStaffPasswordVer.gridx = 1;
		gbc_lblEditStaffPasswordVer.gridy = 4;
		pnlEditStaffDetails.add(lblEditStaffPasswordVer,
				gbc_lblEditStaffPasswordVer);

		pwEditStaffPasswordVer = new JPasswordField();
		GridBagConstraints gbc_pwEditStaffPasswordVer = new GridBagConstraints();
		gbc_pwEditStaffPasswordVer.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwEditStaffPasswordVer.insets = new Insets(0, 0, 5, 5);
		gbc_pwEditStaffPasswordVer.gridx = 2;
		gbc_pwEditStaffPasswordVer.gridy = 4;
		pnlEditStaffDetails.add(pwEditStaffPasswordVer,
				gbc_pwEditStaffPasswordVer);

		JLabel lblEditStaffMobilePhone = new JLabel("Mobile Phone: ");
		GridBagConstraints gbc_lblEditStaffMobilePhone = new GridBagConstraints();
		gbc_lblEditStaffMobilePhone.anchor = GridBagConstraints.EAST;
		gbc_lblEditStaffMobilePhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditStaffMobilePhone.gridx = 4;
		gbc_lblEditStaffMobilePhone.gridy = 4;
		pnlEditStaffDetails.add(lblEditStaffMobilePhone,
				gbc_lblEditStaffMobilePhone);

		txtEditStaffMobilePhone = new JTextField();
		txtEditStaffMobilePhone.setColumns(10);
		GridBagConstraints gbc_txtEditStaffMobilePhone = new GridBagConstraints();
		gbc_txtEditStaffMobilePhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditStaffMobilePhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtEditStaffMobilePhone.gridx = 5;
		gbc_txtEditStaffMobilePhone.gridy = 4;
		pnlEditStaffDetails.add(txtEditStaffMobilePhone,
				gbc_txtEditStaffMobilePhone);

		JPanel pnlEditStaffUserType = new JPanel();
		pnlEditStaffUserType.setBorder(new TitledBorder(null, "User Type",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlEditStaffUserType.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlEditStaffUserType = new GridBagConstraints();
		gbc_pnlEditStaffUserType.fill = GridBagConstraints.BOTH;
		gbc_pnlEditStaffUserType.insets = new Insets(0, 0, 0, 5);
		gbc_pnlEditStaffUserType.gridx = 1;
		gbc_pnlEditStaffUserType.gridy = 5;
		pnlEditStaffDetails.add(pnlEditStaffUserType, gbc_pnlEditStaffUserType);
		pnlEditStaffUserType.setLayout(new GridLayout(0, 1, 0, 0));

		rdbEditStaffTypeAdmin = new JRadioButton("Admin");
		rdbEditStaffTypeAdmin.setForeground(Color.RED);
		rdbEditStaffTypeAdmin.setBackground(Color.WHITE);
		pnlEditStaffUserType.add(rdbEditStaffTypeAdmin);

		rdbEditStaffTypeStaff = new JRadioButton("Staff");
		rdbEditStaffTypeStaff.setSelected(true);
		rdbEditStaffTypeStaff.setBackground(Color.WHITE);
		pnlEditStaffUserType.add(rdbEditStaffTypeStaff);

		EditStaffUserType.add(rdbEditStaffTypeStaff);
		EditStaffUserType.add(rdbEditStaffTypeAdmin);

		JPanel pnlEditStaffControls = new JPanel();
		pnlEditStaffControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlEditStaffControls = new GridBagConstraints();
		gbc_pnlEditStaffControls.fill = GridBagConstraints.BOTH;
		gbc_pnlEditStaffControls.gridwidth = 3;
		gbc_pnlEditStaffControls.insets = new Insets(0, 0, 0, 5);
		gbc_pnlEditStaffControls.gridx = 3;
		gbc_pnlEditStaffControls.gridy = 5;
		pnlEditStaffDetails.add(pnlEditStaffControls, gbc_pnlEditStaffControls);
		GridBagLayout gbl_pnlEditStaffControls = new GridBagLayout();
		gbl_pnlEditStaffControls.columnWidths = new int[]
		{ 96, 96, 0, 96, 0 };
		gbl_pnlEditStaffControls.rowHeights = new int[]
		{ 0, 34, 5, 0 };
		gbl_pnlEditStaffControls.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlEditStaffControls.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlEditStaffControls.setLayout(gbl_pnlEditStaffControls);

		JButton btnEditStaffSubmit = new JButton("Submit");
		btnEditStaffSubmit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				String staffMemberID = null;
				String firstName = null;
				String lastName = null;
				char[] getPassword = null;
				char[] getPasswordVer = null;
				String[] getID = null;
				String getStaffMemberID = null;
				String[] y = null;
				String password = "";
				String passwordVer = "";
				String email = null;
				int officeNumber = 0;
				int mobileNumber = 0;
				String departmentID = null;
				int selections = 0;
				selections = cmbEditUserChooseName.getSelectedIndex();

				if (txtEditStaffFirstName.getText().trim().equals(null)
						|| txtEditStaffFirstName.getText().trim().equals("")
						|| pwEditStaffPassword.getPassword().length == 0
						|| pwEditStaffPasswordVer.getPassword().length == 0
						|| txtEditStaffEmail.getText().trim().equals(null)
						|| txtEditStaffEmail.getText().trim().equals("")
						|| txtEditStaffOfficePhone.getText().trim()
								.equals(null)
						|| txtEditStaffOfficePhone.getText().trim().equals("")
						|| txtEditStaffMobilePhone.getText().trim()
								.equals(null)
						|| txtEditStaffMobilePhone.getText().trim().equals(""))
				{
					System.out.println("Please Fill Out All Fields");
				} else
				{
					if (pwEditStaffPassword.getPassword().length > 0
							&& pwEditStaffPasswordVer.getPassword().length > 0)
					{
						getPassword = pwEditStaffPassword.getPassword();
						getPasswordVer = pwEditStaffPasswordVer.getPassword();

						for (int i = 0; i < getPassword.length; i++)
						{
							password = password + getPassword[i];
						}

						for (int i = 0; i < getPasswordVer.length; i++)
						{
							passwordVer = passwordVer + getPasswordVer[i];
						}

						if (password.equals(passwordVer))
						{
							System.out.println("Matching Password");
							firstName = txtEditStaffFirstName.getText().trim();
							lastName = txtEditStaffLastName.getText().trim();
							email = txtEditStaffEmail.getText().trim();
							getStaffMemberID = (String) cmbEditUserChooseName
									.getSelectedItem();
							y = getStaffMemberID.split(" ");
							staffMemberID = y[0];
							officeNumber = Integer
									.parseInt(txtEditStaffOfficePhone.getText()
											.trim());
							mobileNumber = Integer
									.parseInt(txtEditStaffMobilePhone.getText()
											.trim());
							if (cmbEditStaffDept.getSelectedItem() != null)
							{
								String x = (String) cmbEditStaffDept
										.getSelectedItem();
								getID = x.split(" ");
								departmentID = getID[0];
							}

							if (rdbEditStaffTypeAdmin.isSelected())
							{
								Database.editAdmin(staffMemberID, password,
										firstName, lastName, email,
										officeNumber, mobileNumber);
								staffTableModel stm = new staffTableModel();
								tblQStaffTable.setModel(stm);
								tblQStaffTable.revalidate();
								tblQStaffTable.repaint();
							} else
							{
								Database.editStaff(staffMemberID, password,
										firstName, lastName, email,
										officeNumber, mobileNumber,
										departmentID, true, true);
								staffTableModel stm = new staffTableModel();
								tblQStaffTable.setModel(stm);
								tblQStaffTable.revalidate();
								tblQStaffTable.repaint();
							}

						} else
						{
							System.out.println("Non-Matching Password");

						}
					} else
					{
						System.out.println("Enter Password");
					}
				}

				scm = new staffComboBoxModel();
				cmbEditUserChooseName.setModel(scm);
				cmbEditUserChooseName.setSelectedIndex(selections);

			}
		});
		GridBagConstraints gbc_btnEditStaffSubmit = new GridBagConstraints();
		gbc_btnEditStaffSubmit.fill = GridBagConstraints.BOTH;
		gbc_btnEditStaffSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditStaffSubmit.gridx = 0;
		gbc_btnEditStaffSubmit.gridy = 1;
		pnlEditStaffControls.add(btnEditStaffSubmit, gbc_btnEditStaffSubmit);

		JButton btnEditStaffReset = new JButton("Reset");
		btnEditStaffReset.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				cmbEditStaffDept.setSelectedIndex(0);
				cmbEditUserChooseName.setSelectedIndex(0);
				txtEditStaffFirstName.setText(null);
				txtEditStaffLastName.setText(null);
				pwEditStaffPassword.setText(null);
				pwEditStaffPasswordVer.setText(null);
				txtEditStaffEmail.setText(null);
				txtEditStaffMobilePhone.setText(null);
				txtEditStaffOfficePhone.setText(null);

			}
		});
		GridBagConstraints gbc_btnEditStaffReset = new GridBagConstraints();
		gbc_btnEditStaffReset.fill = GridBagConstraints.BOTH;
		gbc_btnEditStaffReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditStaffReset.gridx = 1;
		gbc_btnEditStaffReset.gridy = 1;
		pnlEditStaffControls.add(btnEditStaffReset, gbc_btnEditStaffReset);

		JButton btnEditStaffCancel = new JButton("Cancel");
		btnEditStaffCancel.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				CardLayout staff = (CardLayout) (pnlStaffScreens.getLayout());
				staff.show(pnlStaffScreens, "queryStaff");
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				JOptionPane pane = new JOptionPane(
						"Are you sure you want to delete the user "
								+ txtEditStaffFirstName.getText() + " "
								+ txtEditStaffLastName.getText());
				Object[] options = new String[]
				{ "Yes", "No" };
				pane.setOptions(options);
				JDialog dialog = pane.createDialog(new JFrame(),
						"Are you sure?");
				dialog.show();
				if (pane.getValue().toString().equals("Yes"))
				{
					if (cmbEditUserChooseName.getSelectedIndex() >= 0)
					{
						String id = cmbEditUserChooseName.getSelectedItem()
								.toString().split(" ").clone()[0];
						if (cmbEditUserChooseName.getSelectedItem().toString()
								.charAt(0) == 'U')
						{
							Database.deleteStaff(id);
						} else
						{
							Database.deleteAdmin(id);
						}
					}else
					{
						JOptionPane.showMessageDialog(null,
								"Please select a user to delete",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
				tblQStaffTable.revalidate();
				tblQStaffTable.repaint();
				cmbEditUserChooseName.revalidate();
				cmbEditUserChooseName.repaint();
				cmbEditStaffDept.setSelectedIndex(0);
				cmbEditUserChooseName.setSelectedIndex(0);
				txtEditStaffFirstName.setText(null);
				txtEditStaffLastName.setText(null);
				pwEditStaffPassword.setText(null);
				pwEditStaffPasswordVer.setText(null);
				txtEditStaffEmail.setText(null);
				txtEditStaffMobilePhone.setText(null);
				txtEditStaffOfficePhone.setText(null);
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 1;
		pnlEditStaffControls.add(btnDelete, gbc_btnDelete);
		GridBagConstraints gbc_btnEditStaffCancel = new GridBagConstraints();
		gbc_btnEditStaffCancel.fill = GridBagConstraints.BOTH;
		gbc_btnEditStaffCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnEditStaffCancel.gridx = 3;
		gbc_btnEditStaffCancel.gridy = 1;
		pnlEditStaffControls.add(btnEditStaffCancel, gbc_btnEditStaffCancel);

		tabbedPane.addTab("Departments", null, pnlDepartments, null);
		GridBagLayout gbl_pnlDepartments = new GridBagLayout();
		gbl_pnlDepartments.columnWidths = new int[]
		{ 0, 0 };
		gbl_pnlDepartments.rowHeights = new int[]
		{ 0, 0, 0 };
		gbl_pnlDepartments.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gbl_pnlDepartments.rowWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		pnlDepartments.setLayout(gbl_pnlDepartments);

		JToolBar toolBarDepartments = new JToolBar();
		toolBarDepartments.setFloatable(false);
		GridBagConstraints gbc_toolBarDepartments = new GridBagConstraints();
		gbc_toolBarDepartments.insets = new Insets(0, 0, 5, 0);
		gbc_toolBarDepartments.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBarDepartments.gridx = 0;
		gbc_toolBarDepartments.gridy = 0;
		pnlDepartments.add(toolBarDepartments, gbc_toolBarDepartments);

		JButton btnAddNewDepartmentScreen = new JButton("Add New Department |");
		btnAddNewDepartmentScreen.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				CardLayout Departments = (CardLayout) (pnlDepartmentContainer
						.getLayout());
				Departments.show(pnlDepartmentContainer, "addDept");
			}
		});
		btnAddNewDepartmentScreen.setHorizontalAlignment(SwingConstants.LEFT);
		toolBarDepartments.add(btnAddNewDepartmentScreen);

		JButton btnEditDepartmentScreen = new JButton("Edit Department");
		btnEditDepartmentScreen.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				CardLayout Departments = (CardLayout) (pnlDepartmentContainer
						.getLayout());
				Departments.show(pnlDepartmentContainer, "editDept");
			}
		});
		btnEditDepartmentScreen.setHorizontalAlignment(SwingConstants.LEFT);
		toolBarDepartments.add(btnEditDepartmentScreen);

		pnlDepartmentContainer = new JPanel();
		pnlDepartmentContainer.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlDepartmentContainer = new GridBagConstraints();
		gbc_pnlDepartmentContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlDepartmentContainer.gridx = 0;
		gbc_pnlDepartmentContainer.gridy = 1;
		pnlDepartments.add(pnlDepartmentContainer, gbc_pnlDepartmentContainer);
		pnlDepartmentContainer.setLayout(new CardLayout(0, 0));
		CardLayout cl_pnlDepartmentContainer = (CardLayout) (pnlDepartmentContainer
				.getLayout());
		pnlQDepartments = new JPanel();
		pnlQDepartments.setBackground(Color.WHITE);
		pnlDepartmentContainer.add(pnlQDepartments, "queryDept");
		GridBagLayout gbl_pnlQDepartments = new GridBagLayout();
		gbl_pnlQDepartments.columnWidths = new int[]
		{ 10, 10, 0, 10, 10, 0 };
		gbl_pnlQDepartments.rowHeights = new int[]
		{ 10, 60, 10, 0, 10, 0 };
		gbl_pnlQDepartments.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlQDepartments.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlQDepartments.setLayout(gbl_pnlQDepartments);

		JPanel pnlQDeptControls = new JPanel();
		pnlQDeptControls.setBackground(Color.WHITE);
		pnlQDeptControls.setBorder(new TitledBorder(null, "Query Department",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlQDeptControls = new GridBagConstraints();
		gbc_pnlQDeptControls.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQDeptControls.fill = GridBagConstraints.BOTH;
		gbc_pnlQDeptControls.gridx = 2;
		gbc_pnlQDeptControls.gridy = 1;
		pnlQDepartments.add(pnlQDeptControls, gbc_pnlQDeptControls);
		GridBagLayout gbl_pnlQDeptControls = new GridBagLayout();
		gbl_pnlQDeptControls.columnWidths = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlQDeptControls.rowHeights = new int[]
		{ 0, 0 };
		gbl_pnlQDeptControls.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlQDeptControls.rowWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		pnlQDeptControls.setLayout(gbl_pnlQDeptControls);

		JLabel lblQDeptID = new JLabel("ID: ");
		GridBagConstraints gbc_lblQDeptID = new GridBagConstraints();
		gbc_lblQDeptID.insets = new Insets(0, 0, 0, 5);
		gbc_lblQDeptID.anchor = GridBagConstraints.EAST;
		gbc_lblQDeptID.gridx = 0;
		gbc_lblQDeptID.gridy = 0;
		pnlQDeptControls.add(lblQDeptID, gbc_lblQDeptID);

		txtQDeptID = new JTextField();
		GridBagConstraints gbc_txtQDeptID = new GridBagConstraints();
		gbc_txtQDeptID.insets = new Insets(0, 0, 0, 5);
		gbc_txtQDeptID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQDeptID.gridx = 1;
		gbc_txtQDeptID.gridy = 0;
		pnlQDeptControls.add(txtQDeptID, gbc_txtQDeptID);
		txtQDeptID.setColumns(10);

		JLabel lblQDeptName = new JLabel("Name: ");
		GridBagConstraints gbc_lblQDeptName = new GridBagConstraints();
		gbc_lblQDeptName.insets = new Insets(0, 0, 0, 5);
		gbc_lblQDeptName.anchor = GridBagConstraints.EAST;
		gbc_lblQDeptName.gridx = 2;
		gbc_lblQDeptName.gridy = 0;
		pnlQDeptControls.add(lblQDeptName, gbc_lblQDeptName);

		txtQDeptName = new JTextField();
		GridBagConstraints gbc_txtQDeptName = new GridBagConstraints();
		gbc_txtQDeptName.insets = new Insets(0, 0, 0, 5);
		gbc_txtQDeptName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQDeptName.gridx = 3;
		gbc_txtQDeptName.gridy = 0;
		pnlQDeptControls.add(txtQDeptName, gbc_txtQDeptName);
		txtQDeptName.setColumns(10);

		JLabel lblQDeptEmail = new JLabel("Email: ");
		GridBagConstraints gbc_lblQDeptEmail = new GridBagConstraints();
		gbc_lblQDeptEmail.insets = new Insets(0, 0, 0, 5);
		gbc_lblQDeptEmail.anchor = GridBagConstraints.EAST;
		gbc_lblQDeptEmail.gridx = 4;
		gbc_lblQDeptEmail.gridy = 0;
		pnlQDeptControls.add(lblQDeptEmail, gbc_lblQDeptEmail);

		txtQDeptEmail = new JTextField();
		GridBagConstraints gbc_txtQDeptEmail = new GridBagConstraints();
		gbc_txtQDeptEmail.insets = new Insets(0, 0, 0, 5);
		gbc_txtQDeptEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQDeptEmail.gridx = 5;
		gbc_txtQDeptEmail.gridy = 0;
		pnlQDeptControls.add(txtQDeptEmail, gbc_txtQDeptEmail);
		txtQDeptEmail.setColumns(10);

		JButton btnQueryDept = new JButton("Query");
		btnQueryDept.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				departmentTableModel.filterRows(txtQDeptID.getText(),
						txtQDeptName.getText(), txtQDeptEmail.getText());

				tblQDeptTable.revalidate();
				tblQDeptTable.repaint();

			}
		});

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				txtQDeptID.setText("");
				txtQDeptName.setText("");
				txtQDeptEmail.setText("");
			}
		});
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridx = 6;
		gbc_btnClear.gridy = 0;
		pnlQDeptControls.add(btnClear, gbc_btnClear);
		GridBagConstraints gbc_btnQueryDept = new GridBagConstraints();
		gbc_btnQueryDept.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQueryDept.gridx = 7;
		gbc_btnQueryDept.gridy = 0;
		pnlQDeptControls.add(btnQueryDept, gbc_btnQueryDept);

		JPanel pnlQDeptTableContainer = new JPanel();
		GridBagConstraints gbc_pnlQDeptTableContainer = new GridBagConstraints();
		gbc_pnlQDeptTableContainer.gridwidth = 3;
		gbc_pnlQDeptTableContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQDeptTableContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlQDeptTableContainer.gridx = 1;
		gbc_pnlQDeptTableContainer.gridy = 3;
		pnlQDepartments.add(pnlQDeptTableContainer, gbc_pnlQDeptTableContainer);
		pnlQDeptTableContainer.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane deptTableScrollPane = new JScrollPane();
		pnlQDeptTableContainer.add(deptTableScrollPane);

		dtm = new departmentTableModel();

		tblQDeptTable = new JTable();
		tblQDeptTable.setModel(dtm);

		deptTableScrollPane.setViewportView(tblQDeptTable);

		pnlAddDepartment = new JPanel();
		pnlAddDepartment.setBackground(Color.WHITE);
		pnlDepartmentContainer.add(pnlAddDepartment, "addDept");
		GridBagLayout gbl_pnlAddDepartment = new GridBagLayout();
		gbl_pnlAddDepartment.columnWidths = new int[]
		{ 160, 0, 160, 0 };
		gbl_pnlAddDepartment.rowHeights = new int[]
		{ 40, 0, 10, 0 };
		gbl_pnlAddDepartment.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlAddDepartment.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlAddDepartment.setLayout(gbl_pnlAddDepartment);

		JPanel pnlAddDepartmentDetails = new JPanel();
		pnlAddDepartmentDetails.setBackground(Color.WHITE);
		pnlAddDepartmentDetails.setBorder(new TitledBorder(null,
				"New Department", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		GridBagConstraints gbc_pnlAddDepartmentDetails = new GridBagConstraints();
		gbc_pnlAddDepartmentDetails.insets = new Insets(0, 0, 5, 5);
		gbc_pnlAddDepartmentDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlAddDepartmentDetails.gridx = 1;
		gbc_pnlAddDepartmentDetails.gridy = 1;
		pnlAddDepartment.add(pnlAddDepartmentDetails,
				gbc_pnlAddDepartmentDetails);
		GridBagLayout gbl_pnlAddDepartmentDetails = new GridBagLayout();
		gbl_pnlAddDepartmentDetails.columnWidths = new int[]
		{ 0, 0, 0 };
		gbl_pnlAddDepartmentDetails.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0 };
		gbl_pnlAddDepartmentDetails.columnWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlAddDepartmentDetails.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlAddDepartmentDetails.setLayout(gbl_pnlAddDepartmentDetails);

		JLabel lblNewDepartmentName = new JLabel("Department Name: ");
		GridBagConstraints gbc_lblNewDepartmentName = new GridBagConstraints();
		gbc_lblNewDepartmentName.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewDepartmentName.anchor = GridBagConstraints.EAST;
		gbc_lblNewDepartmentName.gridx = 0;
		gbc_lblNewDepartmentName.gridy = 0;
		pnlAddDepartmentDetails.add(lblNewDepartmentName,
				gbc_lblNewDepartmentName);

		txtNewDepartmentName = new JTextField();
		GridBagConstraints gbc_txtNewDepartmentName = new GridBagConstraints();
		gbc_txtNewDepartmentName.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewDepartmentName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewDepartmentName.gridx = 1;
		gbc_txtNewDepartmentName.gridy = 0;
		pnlAddDepartmentDetails.add(txtNewDepartmentName,
				gbc_txtNewDepartmentName);
		txtNewDepartmentName.setColumns(10);

		JLabel lblNewDepartmentEmail = new JLabel("Email: ");
		GridBagConstraints gbc_lblNewDepartmentEmail = new GridBagConstraints();
		gbc_lblNewDepartmentEmail.anchor = GridBagConstraints.EAST;
		gbc_lblNewDepartmentEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewDepartmentEmail.gridx = 0;
		gbc_lblNewDepartmentEmail.gridy = 1;
		pnlAddDepartmentDetails.add(lblNewDepartmentEmail,
				gbc_lblNewDepartmentEmail);

		txtNewDepartmentEmail = new JTextField();
		GridBagConstraints gbc_txtNewDepartmentEmail = new GridBagConstraints();
		gbc_txtNewDepartmentEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewDepartmentEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewDepartmentEmail.gridx = 1;
		gbc_txtNewDepartmentEmail.gridy = 1;
		pnlAddDepartmentDetails.add(txtNewDepartmentEmail,
				gbc_txtNewDepartmentEmail);
		txtNewDepartmentEmail.setColumns(10);

		JLabel lblNewDepartmentDescription = new JLabel("Description: ");
		GridBagConstraints gbc_lblNewDepartmentDescription = new GridBagConstraints();
		gbc_lblNewDepartmentDescription.anchor = GridBagConstraints.EAST;
		gbc_lblNewDepartmentDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewDepartmentDescription.gridx = 0;
		gbc_lblNewDepartmentDescription.gridy = 2;
		pnlAddDepartmentDetails.add(lblNewDepartmentDescription,
				gbc_lblNewDepartmentDescription);

		JScrollPane spNewDepartmentDescription = new JScrollPane();
		GridBagConstraints gbc_spNewDepartmentDescription = new GridBagConstraints();
		gbc_spNewDepartmentDescription.gridheight = 2;
		gbc_spNewDepartmentDescription.fill = GridBagConstraints.BOTH;
		gbc_spNewDepartmentDescription.insets = new Insets(0, 0, 5, 0);
		gbc_spNewDepartmentDescription.gridx = 1;
		gbc_spNewDepartmentDescription.gridy = 2;
		pnlAddDepartmentDetails.add(spNewDepartmentDescription,
				gbc_spNewDepartmentDescription);

		txtNewDepartmentDescription = new JTextArea();
		spNewDepartmentDescription.setViewportView(txtNewDepartmentDescription);

		JPanel pnlAddDeptControls = new JPanel();
		pnlAddDeptControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlAddDeptControls = new GridBagConstraints();
		gbc_pnlAddDeptControls.gridwidth = 2;
		gbc_pnlAddDeptControls.fill = GridBagConstraints.BOTH;
		gbc_pnlAddDeptControls.gridx = 0;
		gbc_pnlAddDeptControls.gridy = 4;
		pnlAddDepartmentDetails.add(pnlAddDeptControls, gbc_pnlAddDeptControls);
		pnlAddDeptControls.setLayout(new GridLayout(0, 3, 5, 5));

		JButton btnAddDeptSubmit = new JButton("Submit");
		btnAddDeptSubmit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				if (txtNewDepartmentName.getText().trim().equals(null)
						|| txtNewDepartmentName.getText().trim().equals("")
						|| txtNewDepartmentEmail.getText().trim().equals(null)
						|| txtNewDepartmentEmail.getText().trim().equals("")
						|| txtNewDepartmentDescription.getText().trim()
								.equals(null)
						|| txtNewDepartmentDescription.getText().trim()
								.equals(""))
				{
					System.out.println("Please fill Out All Fields");
				} else
				{

					String departmentName = txtNewDepartmentName.getText()
							.trim();
					String departmentEmail = txtNewDepartmentEmail.getText()
							.trim();
					String departmentDescription = txtNewDepartmentDescription
							.getText().trim();

					Database.addDepartment(departmentName,
							departmentDescription, departmentEmail);

					stcm = new staffDeptComboModel();
					dtm = new departmentTableModel();

					cmbQStaffDept.setModel(stcm);
					cmbQStaffDept.revalidate();
					cmbQStaffDept.repaint();
					cmbAddStaffDept.setModel(stcm);
					cmbAddStaffDept.repaint();
					cmbAddStaffDept.revalidate();
					cmbEditStaffDept.setModel(stcm);
					cmbEditStaffDept.repaint();
					cmbEditStaffDept.revalidate();
					tblQDeptTable.setModel(dtm);
					tblQDeptTable.revalidate();
					cmbEditDepartmentSelect.setModel(stcm);
					cmbEditDepartmentSelect.revalidate();
					cmbEditDepartmentSelect.repaint();
					pnlEditDepartmentSelect.revalidate();
					pnlEditDepartmentSelect.repaint();
				}

			}
		});
		pnlAddDeptControls.add(btnAddDeptSubmit);

		JButton btnAddDeptReset = new JButton("Reset");
		pnlAddDeptControls.add(btnAddDeptReset);

		JButton btnAddDeptCancel = new JButton("Cancel");
		btnAddDeptCancel.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				CardLayout Departments = (CardLayout) (pnlDepartmentContainer
						.getLayout());
				Departments.show(pnlDepartmentContainer, "queryDept");
			}
		});
		pnlAddDeptControls.add(btnAddDeptCancel);

		pnlEditDepartments = new JPanel();
		pnlEditDepartments.setBackground(Color.WHITE);
		pnlDepartmentContainer.add(pnlEditDepartments, "editDept");
		GridBagLayout gbl_pnlEditDepartments = new GridBagLayout();
		gbl_pnlEditDepartments.columnWidths = new int[]
		{ 160, 0, 160, 0 };
		gbl_pnlEditDepartments.rowHeights = new int[]
		{ 5, 55, 5, 0, 10, 0 };
		gbl_pnlEditDepartments.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlEditDepartments.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlEditDepartments.setLayout(gbl_pnlEditDepartments);

		pnlEditDepartmentSelect = new JPanel();
		pnlEditDepartmentSelect.setBackground(Color.WHITE);
		pnlEditDepartmentSelect.setBorder(new TitledBorder(null,
				"Select Department", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		GridBagConstraints gbc_pnlEditDepartmentSelect = new GridBagConstraints();
		gbc_pnlEditDepartmentSelect.insets = new Insets(0, 0, 5, 5);
		gbc_pnlEditDepartmentSelect.fill = GridBagConstraints.BOTH;
		gbc_pnlEditDepartmentSelect.gridx = 1;
		gbc_pnlEditDepartmentSelect.gridy = 1;
		pnlEditDepartments.add(pnlEditDepartmentSelect,
				gbc_pnlEditDepartmentSelect);
		GridBagLayout gbl_pnlEditDepartmentSelect = new GridBagLayout();
		gbl_pnlEditDepartmentSelect.columnWidths = new int[]
		{ 0, 0, 10, 0 };
		gbl_pnlEditDepartmentSelect.rowHeights = new int[]
		{ 0, 0 };
		gbl_pnlEditDepartmentSelect.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlEditDepartmentSelect.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlEditDepartmentSelect.setLayout(gbl_pnlEditDepartmentSelect);

		JLabel lblEditDeptarmentSelect = new JLabel("Department Name: ");
		GridBagConstraints gbc_lblEditDeptarmentSelect = new GridBagConstraints();
		gbc_lblEditDeptarmentSelect.insets = new Insets(0, 0, 0, 5);
		gbc_lblEditDeptarmentSelect.anchor = GridBagConstraints.EAST;
		gbc_lblEditDeptarmentSelect.gridx = 0;
		gbc_lblEditDeptarmentSelect.gridy = 0;
		pnlEditDepartmentSelect.add(lblEditDeptarmentSelect,
				gbc_lblEditDeptarmentSelect);

		cmbEditDepartmentSelect = new JComboBox();
		cmbEditDepartmentSelect.setModel(stcm);
		cmbEditDepartmentSelect.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				String getDeptID;
				String[] x;
				String deptID = "";

				getDeptID = ((String) cmbEditDepartmentSelect.getSelectedItem())
						.trim();

				x = getDeptID.split(" ");

				deptID = x[0];

				Database.getDepartmentDetails(deptID); // maybe back to db?

				try
				{
					txtEditDepartmentName.setText(Connections.rset.getString(
							"DEPARTMENTNAME").trim());
					txtEditDepartmentEmail.setText(Connections.rset.getString(
							"DEPARTMENTEMAIL").trim());
					txtEditDepartmentDescription.setText(Connections.rset
							.getString("DEPARTMENTDESCRIPTION").trim());
					// Connections.killConnections();
					Connections.killRset();
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}

				cmbEditDepartmentSelect.revalidate();
				cmbEditDepartmentSelect.repaint();

				// Connections.killConnections();
			}
		});
		GridBagConstraints gbc_cmbEditDepartmentSelect = new GridBagConstraints();
		gbc_cmbEditDepartmentSelect.insets = new Insets(0, 0, 0, 5);
		gbc_cmbEditDepartmentSelect.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbEditDepartmentSelect.gridx = 1;
		gbc_cmbEditDepartmentSelect.gridy = 0;
		pnlEditDepartmentSelect.add(cmbEditDepartmentSelect,
				gbc_cmbEditDepartmentSelect);

		JPanel pnlEditDeptDetails = new JPanel();
		pnlEditDeptDetails.setBorder(new TitledBorder(null,
				"Department Details", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		pnlEditDeptDetails.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlEditDeptDetails = new GridBagConstraints();
		gbc_pnlEditDeptDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlEditDeptDetails.insets = new Insets(0, 0, 5, 5);
		gbc_pnlEditDeptDetails.gridx = 1;
		gbc_pnlEditDeptDetails.gridy = 3;
		pnlEditDepartments.add(pnlEditDeptDetails, gbc_pnlEditDeptDetails);
		GridBagLayout gbl_pnlEditDeptDetails = new GridBagLayout();
		gbl_pnlEditDeptDetails.columnWidths = new int[]
		{ 0, 0, 0 };
		gbl_pnlEditDeptDetails.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0 };
		gbl_pnlEditDeptDetails.columnWeights = new double[]
		{ 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlEditDeptDetails.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlEditDeptDetails.setLayout(gbl_pnlEditDeptDetails);

		JLabel lblEditDepartmentName = new JLabel("Department Name: ");
		GridBagConstraints gbc_lblEditDepartmentName = new GridBagConstraints();
		gbc_lblEditDepartmentName.anchor = GridBagConstraints.EAST;
		gbc_lblEditDepartmentName.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditDepartmentName.gridx = 0;
		gbc_lblEditDepartmentName.gridy = 0;
		pnlEditDeptDetails
				.add(lblEditDepartmentName, gbc_lblEditDepartmentName);

		txtEditDepartmentName = new JTextField();
		txtEditDepartmentName.setColumns(10);
		GridBagConstraints gbc_txtEditDepartmentName = new GridBagConstraints();
		gbc_txtEditDepartmentName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditDepartmentName.insets = new Insets(0, 0, 5, 0);
		gbc_txtEditDepartmentName.gridx = 1;
		gbc_txtEditDepartmentName.gridy = 0;
		pnlEditDeptDetails
				.add(txtEditDepartmentName, gbc_txtEditDepartmentName);

		JLabel lblEditDepartmentEmail = new JLabel("Email: ");
		GridBagConstraints gbc_lblEditDepartmentEmail = new GridBagConstraints();
		gbc_lblEditDepartmentEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEditDepartmentEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditDepartmentEmail.gridx = 0;
		gbc_lblEditDepartmentEmail.gridy = 1;
		pnlEditDeptDetails.add(lblEditDepartmentEmail,
				gbc_lblEditDepartmentEmail);

		txtEditDepartmentEmail = new JTextField();
		txtEditDepartmentEmail.setColumns(10);
		GridBagConstraints gbc_txtEditDepartmentEmail = new GridBagConstraints();
		gbc_txtEditDepartmentEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEditDepartmentEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEditDepartmentEmail.gridx = 1;
		gbc_txtEditDepartmentEmail.gridy = 1;
		pnlEditDeptDetails.add(txtEditDepartmentEmail,
				gbc_txtEditDepartmentEmail);

		JLabel lblEditDepartmentDescription = new JLabel("Description: ");
		GridBagConstraints gbc_lblEditDepartmentDescription = new GridBagConstraints();
		gbc_lblEditDepartmentDescription.anchor = GridBagConstraints.EAST;
		gbc_lblEditDepartmentDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditDepartmentDescription.gridx = 0;
		gbc_lblEditDepartmentDescription.gridy = 2;
		pnlEditDeptDetails.add(lblEditDepartmentDescription,
				gbc_lblEditDepartmentDescription);

		JScrollPane spEditDepartmentDescription = new JScrollPane();
		GridBagConstraints gbc_spEditDepartmentDescription = new GridBagConstraints();
		gbc_spEditDepartmentDescription.gridheight = 2;
		gbc_spEditDepartmentDescription.fill = GridBagConstraints.BOTH;
		gbc_spEditDepartmentDescription.insets = new Insets(0, 0, 5, 0);
		gbc_spEditDepartmentDescription.gridx = 1;
		gbc_spEditDepartmentDescription.gridy = 2;
		pnlEditDeptDetails.add(spEditDepartmentDescription,
				gbc_spEditDepartmentDescription);

		txtEditDepartmentDescription = new JTextArea();
		spEditDepartmentDescription
				.setViewportView(txtEditDepartmentDescription);

		JPanel pnlEditDepartmentControls = new JPanel();
		pnlEditDepartmentControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlEditDepartmentControls = new GridBagConstraints();
		gbc_pnlEditDepartmentControls.fill = GridBagConstraints.BOTH;
		gbc_pnlEditDepartmentControls.gridwidth = 2;
		gbc_pnlEditDepartmentControls.gridx = 0;
		gbc_pnlEditDepartmentControls.gridy = 4;
		pnlEditDeptDetails.add(pnlEditDepartmentControls,
				gbc_pnlEditDepartmentControls);

		JButton btnEditDeptartmentSubmit = new JButton("Submit");
		btnEditDeptartmentSubmit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				int selection = cmbEditDepartmentSelect.getSelectedIndex();
				String departmentID = null;
				String departmentName = null;
				String departmentEmail = null;
				String departmentDescription = null;
				String getDeptID = null;
				String x[];

				getDeptID = (String) cmbEditDepartmentSelect.getSelectedItem();
				x = getDeptID.split(" ");
				departmentID = x[0];

				if (txtEditDepartmentName.getText().trim().equals(null)
						|| txtEditDepartmentName.getText().trim().equals("")
						|| txtEditDepartmentEmail.getText().trim().equals(null)
						|| txtEditDepartmentEmail.getText().trim().equals("")
						|| txtEditDepartmentDescription.getText().trim()
								.equals(null)
						|| txtEditDepartmentDescription.getText().trim()
								.equals(""))
				{
					System.out.println("Please fill Out All Fields");
				} else
				{
					departmentName = txtEditDepartmentName.getText().trim();
					departmentEmail = txtEditDepartmentEmail.getText().trim();
					departmentDescription = txtEditDepartmentDescription
							.getText();

					Database.editDepartment(departmentID, departmentName,
							departmentEmail, departmentDescription);
					// Connections.killConnections();
					stcm = new staffDeptComboModel();
					dtm = new departmentTableModel();

					cmbQStaffDept.setModel(stcm);
					cmbQStaffDept.revalidate();
					cmbQStaffDept.repaint();
					cmbAddStaffDept.setModel(stcm);
					cmbAddStaffDept.repaint();
					cmbAddStaffDept.revalidate();
					cmbEditStaffDept.setModel(stcm);
					cmbEditStaffDept.repaint();
					cmbEditStaffDept.revalidate();
					tblQDeptTable.setModel(dtm);
					tblQDeptTable.revalidate();
					cmbEditDepartmentSelect.setModel(stcm);
					cmbEditDepartmentSelect.revalidate();
					cmbEditDepartmentSelect.repaint();
					pnlEditDepartmentSelect.revalidate();
					pnlEditDepartmentSelect.repaint();
					cmbEditDepartmentSelect.setSelectedIndex(selection);
				}
			}
		});
		pnlEditDepartmentControls.setLayout(new FlowLayout(FlowLayout.CENTER,
				5, 5));
		pnlEditDepartmentControls.add(btnEditDeptartmentSubmit);

		JButton btnEditDepartmentReset = new JButton("Reset");
		pnlEditDepartmentControls.add(btnEditDepartmentReset);

		JButton btnEditDepartmentCancel = new JButton("Cancel");
		btnEditDepartmentCancel.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				CardLayout Departments = (CardLayout) (pnlDepartmentContainer
						.getLayout());
				Departments.show(pnlDepartmentContainer, "queryDept");
			}
		});

		JButton btnDeleteDept = new JButton("Delete");
		btnDeleteDept.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(cmbEditDepartmentSelect.getSelectedIndex() >= 0)
				{
					Database.deleteDepartment(cmbEditDepartmentSelect
						.getSelectedItem().toString().split(" ").clone()[0]);
				cmbEditDepartmentSelect.revalidate();
				cmbEditDepartmentSelect.repaint();
				}else
				{
					JOptionPane
					.showMessageDialog(null,
							"Please select a department to delete",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
				

			}
		});
		pnlEditDepartmentControls.add(btnDeleteDept);
		pnlEditDepartmentControls.add(btnEditDepartmentCancel);
		pnlReports.setBackground(Color.WHITE);

		tabbedPane.addTab("Reports", null, pnlReports, null);
		GridBagLayout gbl_pnlReports = new GridBagLayout();
		gbl_pnlReports.columnWidths = new int[]
		{ 50, 40, 250, 0, 50, 0 };
		gbl_pnlReports.rowHeights = new int[]
		{ 10, 0, 10, 0, 10, 0 };
		gbl_pnlReports.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlReports.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlReports.setLayout(gbl_pnlReports);

		JLabel lblReportType = new JLabel("Report Type: ");
		GridBagConstraints gbc_lblReportType = new GridBagConstraints();
		gbc_lblReportType.insets = new Insets(0, 0, 5, 5);
		gbc_lblReportType.anchor = GridBagConstraints.EAST;
		gbc_lblReportType.gridx = 1;
		gbc_lblReportType.gridy = 1;
		pnlReports.add(lblReportType, gbc_lblReportType);

		cmbReportType = new JComboBox();
		cmbReportType.addItemListener(new ItemListener()
		{

			public void itemStateChanged(ItemEvent arg0)
			{

				if (cmbReportType.getSelectedIndex() == 0)
				{
					cmbReportView.setSelectedIndex(1);
					cmbReportView.setSelectedIndex(0);
				} else if (cmbReportType.getSelectedIndex() == 1)
				{
					cmbReportView.setSelectedIndex(1);

					cmbReportView.setSelectedIndex(0);

				} else if (cmbReportType.getSelectedIndex() == 2)
				{
					cmbReportView.setSelectedIndex(1);

					cmbReportView.setSelectedIndex(0);

				} else if (cmbReportType.getSelectedIndex() == 3)
				{
					cmbReportView.setSelectedIndex(1);

					cmbReportView.setSelectedIndex(0);
				}

				cmbReportType.revalidate();
				cmbReportType.repaint();
				cmbReportView.revalidate();
				cmbReportView.repaint();

			}
		});
		cmbReportType.setModel(new DefaultComboBoxModel(new String[]
		{ "Ticket By Department", "Ticket By Priority", "Ticket By Category",
				"Ticket Close Time" }));

		GridBagConstraints gbc_cmbReportType = new GridBagConstraints();
		gbc_cmbReportType.insets = new Insets(0, 0, 5, 5);
		gbc_cmbReportType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbReportType.gridx = 2;
		gbc_cmbReportType.gridy = 1;
		pnlReports.add(cmbReportType, gbc_cmbReportType);

		JButton btnCreateReport = new JButton(
				"Create Report File and email to default admin");
		btnCreateReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				JFreeChart[] charts =
				{ graphs.ticketByDeptPie(), graphs.ticketByDeptBar(),
						graphs.closeTimePie(), graphs.closeTimeBar(),
						graphs.ticketByCatPie(), graphs.ticketByCatBar(),
						graphs.ticketByPriorityPie(),
						graphs.ticketByPriorityBar() };

				String[] images = new String[charts.length];

				for (int i = 0; i < charts.length; i++)
				{
					try
					{
						ChartUtilities.saveChartAsPNG(new File(charts[i]
								.getTitle().getText() + ".png"), charts[i],
								500, 300);
						images[i] = charts[i].getTitle().getText();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				PDF report = new PDF("AuxiliumReport", images);
				try
				{
				email.emailReportToAdmins();
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,
							"Email " + e
									+ Database.getDefaultAdminEmail(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				
				JOptionPane.showMessageDialog(null,
						"Report saved as AuxiliumReport.pdf and was emailed off to "
								+ Database.getDefaultAdminEmail(), "Success",
						JOptionPane.DEFAULT_OPTION);
			}
		});
		GridBagConstraints gbc_btnCreateReport = new GridBagConstraints();
		gbc_btnCreateReport.anchor = GridBagConstraints.EAST;
		gbc_btnCreateReport.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateReport.gridx = 3;
		gbc_btnCreateReport.gridy = 1;
		pnlReports.add(btnCreateReport, gbc_btnCreateReport);

		JLabel lblReportView = new JLabel("Report View: ");
		GridBagConstraints gbc_lblReportView = new GridBagConstraints();
		gbc_lblReportView.insets = new Insets(0, 0, 5, 5);
		gbc_lblReportView.anchor = GridBagConstraints.EAST;
		gbc_lblReportView.gridx = 1;
		gbc_lblReportView.gridy = 2;
		pnlReports.add(lblReportView, gbc_lblReportView);

		cmbReportView = new JComboBox();
		cmbReportView.setModel(new DefaultComboBoxModel(new String[]
		{ "Pie Chart", "Bar Chart" }));
		cmbReportView.addItemListener(new ItemListener()
		{

			public void itemStateChanged(ItemEvent e)
			{

				{
					if (cmbReportType.getSelectedIndex() == 0
							&& cmbReportView.getSelectedIndex() == 0)
					{
						chartPanel.setChart(graphs.ticketByDeptPie());
					} else if (cmbReportType.getSelectedIndex() == 0
							&& cmbReportView.getSelectedIndex() == 1)
					{
						chartPanel.setChart(graphs.ticketByDeptBar());
					} else if (cmbReportType.getSelectedIndex() == 1
							&& cmbReportView.getSelectedIndex() == 0)
					{
						chartPanel.setChart(graphs.ticketByPriorityPie());

					} else if (cmbReportType.getSelectedIndex() == 1
							&& cmbReportView.getSelectedIndex() == 1)
					{
						chartPanel.setChart(graphs.ticketByPriorityBar());

					} else if (cmbReportType.getSelectedIndex() == 2
							&& cmbReportView.getSelectedIndex() == 0)
					{
						chartPanel.setChart(graphs.ticketByCatPie());

					} else if (cmbReportType.getSelectedIndex() == 2
							&& cmbReportView.getSelectedIndex() == 1)
					{
						chartPanel.setChart(graphs.ticketByCatBar());

					} else if (cmbReportType.getSelectedIndex() == 3
							&& cmbReportView.getSelectedIndex() == 0)
					{
						chartPanel.setChart(graphs.closeTimePie());

					} else if (cmbReportType.getSelectedIndex() == 3
							&& cmbReportView.getSelectedIndex() == 1)
					{
						chartPanel.setChart(graphs.closeTimeBar());
					}
					cmbReportType.revalidate();
					cmbReportType.repaint();
					cmbReportView.revalidate();
					cmbReportView.repaint();

				}
			}
		});
		GridBagConstraints gbc_cmbReportView = new GridBagConstraints();
		gbc_cmbReportView.insets = new Insets(0, 0, 5, 5);
		gbc_cmbReportView.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbReportView.gridx = 2;
		gbc_cmbReportView.gridy = 2;
		pnlReports.add(cmbReportView, gbc_cmbReportView);
		chartPanel = new ChartPanel(null);
		GridBagConstraints gbc_chartPanel = new GridBagConstraints();
		gbc_chartPanel.insets = new Insets(0, 0, 5, 5);
		gbc_chartPanel.gridwidth = 3;
		gbc_chartPanel.fill = GridBagConstraints.BOTH;
		gbc_chartPanel.gridx = 1;
		gbc_chartPanel.gridy = 3;
		pnlReports.add(chartPanel, gbc_chartPanel);
		chartPanel.setChart(graphs.ticketByDeptPie());

	}

	private void reloadComboBox()
	{

		contentPane.revalidate();
		cmdQPriority.revalidate();
		cmdQPriority.repaint();
		cmdQPriority.doLayout();
		cmdQPriority.invalidate();
	}

}
