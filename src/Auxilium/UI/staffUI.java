package Auxilium.UI;

//Comment

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;
import Auxilium.ComboBoxModels.categoryComboBoxModel;
import Auxilium.ComboBoxModels.staffDeptComboModel;
import Auxilium.TableModels.currentStaffTicketTableModel;
import Auxilium.TableModels.currentStaffTicketTableModelClosed;
import Auxilium.TableModels.knowledgeBaseTableModel;

import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class staffUI extends JFrame
{

	private static String currentStaffID;

	private JPanel contentPane;

	private JPanel pnlTicketScreens;

	private JTextField txtQueryTicket;

	public static JTable tblStaffTicket;

	public static JTable tblKnowledge;

	private JTextField txtNewTicketTitle;

	private JTextArea txtNewTicketDescription;

	private JTextField txtKnowledgeTitle;

	private JTextField txtClosedTitle;
	private JPanel pnlTickets;
	private JPanel pnlKnowledgeBase;
	private JTabbedPane tabbedPane;
	private JTable tblClosedTickets;
	currentStaffTicketTableModelClosed csttmc = new currentStaffTicketTableModelClosed(
			currentStaffID);

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
					staffUI frame = new staffUI(currentStaffID);
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param string
	 */
	public staffUI(String staffID)
	{

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				staffUI.class.getResource("/Auxilium/Images/icon.png")));
		setTitle("Auxilium");
		currentStaffID = staffID;
		System.out.println("Current User is " + currentStaffID);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 580);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnTickets = new JMenu("Tickets");
		menuBar.add(mnTickets);

		JMenuItem mntmViewTickets = new JMenuItem("View Tickets");
		mntmViewTickets.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabbedPane.setSelectedComponent(pnlTickets);
				CardLayout tickets = (CardLayout) (pnlTicketScreens.getLayout());
				tickets.show(pnlTicketScreens, "mainTickets");
			}
		});
		mnTickets.add(mntmViewTickets);

		JMenuItem mntmCreateTicket = new JMenuItem("Create Ticket");
		mntmCreateTicket.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				tabbedPane.setSelectedComponent(pnlTickets);
				CardLayout tickets = (CardLayout) (pnlTicketScreens.getLayout());
				tickets.show(pnlTicketScreens, "newTicket");
			}
		});
		mnTickets.add(mntmCreateTicket);

		JMenuItem mntmViewClosed = new JMenuItem("View Closed");
		mntmViewClosed.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabbedPane.setSelectedComponent(pnlTickets);
				CardLayout tickets = (CardLayout) (pnlTicketScreens.getLayout());
				tickets.show(pnlTicketScreens, "closedTicket");

			}
		});
		mnTickets.add(mntmViewClosed);

		JMenu mnKnowledgeBase = new JMenu("Knowledge Base");
		menuBar.add(mnKnowledgeBase);

		JMenuItem mntmViewBase = new JMenuItem("View Knowledge Base");
		mntmViewBase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabbedPane.setSelectedComponent(pnlKnowledgeBase);
			}
		});
		mnKnowledgeBase.add(mntmViewBase);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				staffHelp.main(null);
			}
		});
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				about.main(null);
			}
		});
		mnHelp.add(mntmAbout);
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

				System.out.println("System closing...");

				Connections.killConnections();

				System.exit(0);
			}

		});

		JPanel pnlLogo = new JPanel();
		contentPane.add(pnlLogo, BorderLayout.NORTH);
		GridBagLayout gbl_pnlLogo = new GridBagLayout();
		gbl_pnlLogo.columnWidths = new int[]
		{ 0, 94, 325, 0 };
		gbl_pnlLogo.rowHeights = new int[]
		{ 30, 0 };
		gbl_pnlLogo.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlLogo.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlLogo.setLayout(gbl_pnlLogo);

		JLabel lblLogo = new JLabel("");

		lblLogo.setIcon(new ImageIcon(staffUI.class
				.getResource("/Auxilium/Images/logoSmall.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.fill = GridBagConstraints.BOTH;
		gbc_lblLogo.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 0;
		pnlLogo.add(lblLogo, gbc_lblLogo);

		JPanel pnlLogOut = new JPanel();
		pnlLogOut.setBorder(new TitledBorder(null, "",

		TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlLogOut = new GridBagConstraints();
		gbc_pnlLogOut.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlLogOut.anchor = GridBagConstraints.NORTH;
		gbc_pnlLogOut.gridx = 2;
		gbc_pnlLogOut.gridy = 0;
		pnlLogo.add(pnlLogOut, gbc_pnlLogOut);
		GridBagLayout gbl_pnlLogOut = new GridBagLayout();
		gbl_pnlLogOut.columnWidths = new int[]
		{ 90, 60, 0, 0, 0, 0, 0 };
		gbl_pnlLogOut.rowHeights = new int[]
		{ 25, 0 };
		gbl_pnlLogOut.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_pnlLogOut.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlLogOut.setLayout(gbl_pnlLogOut);

		String staffName = Database.getStaffName(currentStaffID);
		Connections.killRset();
		JLabel lblWelcome = new JLabel("Welcome Back, " + staffName);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.fill = GridBagConstraints.VERTICAL;
		gbc_lblWelcome.anchor = GridBagConstraints.EAST;
		gbc_lblWelcome.gridwidth = 2;
		gbc_lblWelcome.insets = new Insets(0, 0, 0, 5);
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 0;
		pnlLogOut.add(lblWelcome, gbc_lblWelcome);

		JLabel lblPreferences = new JLabel("|Preferences|");
		lblPreferences.setForeground(new Color(255, 140, 0));
		lblPreferences.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPreferences = new GridBagConstraints();
		gbc_lblPreferences.fill = GridBagConstraints.BOTH;
		gbc_lblPreferences.insets = new Insets(0, 0, 0, 5);
		gbc_lblPreferences.gridx = 3;
		gbc_lblPreferences.gridy = 0;
		pnlLogOut.add(lblPreferences, gbc_lblPreferences);

		JLabel lblLogOut = new JLabel("|Logout|");
		lblLogOut.addMouseListener(new MouseAdapter()
		{

			public void mouseEntered(MouseEvent arg0)
			{

				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0)
			{

				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});

		lblLogOut.addMouseListener(new MouseAdapter()
		{

			public void mouseClicked(MouseEvent arg0)
			{

				System.out.println(currentStaffID + " Logout");
				Connections.killRset();
				loginUI app = new loginUI();
				app.setVisible(true);
				staffUI.this.dispose();
				Connections.killRset();
			}

		});
		lblLogOut.setForeground(new Color(255, 140, 0));
		lblLogOut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLogOut = new GridBagConstraints();
		gbc_lblLogOut.fill = GridBagConstraints.BOTH;
		gbc_lblLogOut.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogOut.gridx = 4;
		gbc_lblLogOut.gridy = 0;
		pnlLogOut.add(lblLogOut, gbc_lblLogOut);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.menu);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		pnlTickets = new JPanel();
		pnlTickets.setBackground(Color.WHITE);
		tabbedPane.addTab("Tickets", null, pnlTickets, null);
		pnlTickets.setLayout(new BorderLayout(0, 0));

		JToolBar tbTicketButtons = new JToolBar();
		tbTicketButtons.setFloatable(false);
		pnlTickets.add(tbTicketButtons, BorderLayout.NORTH);

		JButton btnNewTicket = new JButton("Create Ticket");
		btnNewTicket.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				CardLayout tickets = (CardLayout) (pnlTicketScreens.getLayout());
				tickets.show(pnlTicketScreens, "newTicket");
			}
		});
		tbTicketButtons.add(btnNewTicket);

		JButton btnViewClosed = new JButton("View Closed Tickets");
		btnViewClosed.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				CardLayout tickets = (CardLayout) (pnlTicketScreens.getLayout());
				tickets.show(pnlTicketScreens, "closedTicket");
			}
		});
		tbTicketButtons.add(btnViewClosed);

		pnlTicketScreens = new JPanel();
		pnlTickets.add(pnlTicketScreens, BorderLayout.CENTER);
		pnlTicketScreens.setLayout(new CardLayout(0, 0));

		final JPanel pnlMainTicket = new JPanel();
		pnlMainTicket.setBackground(Color.WHITE);
		pnlTicketScreens.add(pnlMainTicket, "mainTickets");
		GridBagLayout gbl_pnlMainTicket = new GridBagLayout();
		gbl_pnlMainTicket.columnWidths = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlMainTicket.rowHeights = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlMainTicket.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlMainTicket.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlMainTicket.setLayout(gbl_pnlMainTicket);

		JPanel pnlMainTicketContainer = new JPanel();
		pnlMainTicketContainer.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlMainTicketContainer = new GridBagConstraints();
		gbc_pnlMainTicketContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlMainTicketContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlMainTicketContainer.gridx = 1;
		gbc_pnlMainTicketContainer.gridy = 1;
		pnlMainTicket.add(pnlMainTicketContainer, gbc_pnlMainTicketContainer);
		GridBagLayout gbl_pnlMainTicketContainer = new GridBagLayout();
		gbl_pnlMainTicketContainer.columnWidths = new int[]
		{ 5, 300, 0, 5, 0 };
		gbl_pnlMainTicketContainer.rowHeights = new int[]
		{ 50, 0, 0, 0 };
		gbl_pnlMainTicketContainer.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlMainTicketContainer.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlMainTicketContainer.setLayout(gbl_pnlMainTicketContainer);

		JPanel pnlQueryTicket = new JPanel();
		pnlQueryTicket.setBackground(Color.WHITE);
		pnlQueryTicket.setBorder(new TitledBorder(null, "Query Tickets",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlQueryTicket = new GridBagConstraints();
		gbc_pnlQueryTicket.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQueryTicket.fill = GridBagConstraints.BOTH;
		gbc_pnlQueryTicket.gridx = 1;
		gbc_pnlQueryTicket.gridy = 0;
		pnlMainTicketContainer.add(pnlQueryTicket, gbc_pnlQueryTicket);
		GridBagLayout gbl_pnlQueryTicket = new GridBagLayout();
		gbl_pnlQueryTicket.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gbl_pnlQueryTicket.rowHeights = new int[]
		{ 35, 0 };
		gbl_pnlQueryTicket.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlQueryTicket.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlQueryTicket.setLayout(gbl_pnlQueryTicket);

		JLabel lblQueryTicket = new JLabel("Title: ");
		GridBagConstraints gbc_lblQueryTicket = new GridBagConstraints();
		gbc_lblQueryTicket.insets = new Insets(0, 0, 0, 5);
		gbc_lblQueryTicket.anchor = GridBagConstraints.EAST;
		gbc_lblQueryTicket.fill = GridBagConstraints.VERTICAL;
		gbc_lblQueryTicket.gridx = 0;
		gbc_lblQueryTicket.gridy = 0;
		pnlQueryTicket.add(lblQueryTicket, gbc_lblQueryTicket);

		txtQueryTicket = new JTextField();
		GridBagConstraints gbc_txtQueryTicket = new GridBagConstraints();
		gbc_txtQueryTicket.insets = new Insets(0, 0, 0, 5);
		gbc_txtQueryTicket.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQueryTicket.gridx = 1;
		gbc_txtQueryTicket.gridy = 0;
		pnlQueryTicket.add(txtQueryTicket, gbc_txtQueryTicket);
		txtQueryTicket.setColumns(10);

		JButton btnQueryTicket = new JButton("Query");
		btnQueryTicket.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				currentStaffTicketTableModel.filterRows(txtQueryTicket
						.getText());
				tblStaffTicket.revalidate();
				tblStaffTicket.repaint();

			}
		});
		GridBagConstraints gbc_btnQueryTicket = new GridBagConstraints();
		gbc_btnQueryTicket.gridx = 2;
		gbc_btnQueryTicket.gridy = 0;
		pnlQueryTicket.add(btnQueryTicket, gbc_btnQueryTicket);

		JScrollPane spTicketTable = new JScrollPane();
		spTicketTable.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

			}
		});
		GridBagConstraints gbc_spTicketTable = new GridBagConstraints();
		gbc_spTicketTable.insets = new Insets(0, 0, 5, 5);
		gbc_spTicketTable.gridwidth = 2;
		gbc_spTicketTable.fill = GridBagConstraints.BOTH;
		gbc_spTicketTable.gridx = 1;
		gbc_spTicketTable.gridy = 1;
		pnlMainTicketContainer.add(spTicketTable, gbc_spTicketTable);
		final currentStaffTicketTableModel csttm = new currentStaffTicketTableModel(
				currentStaffID);
		tblStaffTicket = new JTable();
		tblStaffTicket.addMouseListener(new MouseAdapter()

		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

				if (e.getClickCount() > 1)
				{
					System.out.println("double click");
					int r = tblStaffTicket.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblStaffTicket.getRowCount())
					{
						tblStaffTicket.setRowSelectionInterval(r, r);
					} else
					{
						tblStaffTicket.clearSelection();
					}

					int rowindex = tblStaffTicket.getSelectedRow();
					if (rowindex < 0)
					{
						return;
					}

					int col = tblStaffTicket.getColumnModel().getColumnIndex(
							"TICKETID");
					System.out.println(col);
					String ticketID = tblStaffTicket.getValueAt(rowindex, col)
							.toString();

					userTicketView ticketView = new userTicketView(ticketID);
					ticketView.setVisible(true);
					ticketView.btnSubmit.setEnabled(true);
				}
			}
		});
		tblStaffTicket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblStaffTicket.setModel(csttm);
		spTicketTable.setViewportView(tblStaffTicket);
		// Connections.killConnections();

		JPanel pnlTicketButtons = new JPanel();
		pnlTicketButtons.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) pnlTicketButtons.getLayout();
		flowLayout.setHgap(25);
		GridBagConstraints gbc_pnlTicketButtons = new GridBagConstraints();
		gbc_pnlTicketButtons.gridwidth = 2;
		gbc_pnlTicketButtons.insets = new Insets(0, 0, 0, 5);
		gbc_pnlTicketButtons.fill = GridBagConstraints.BOTH;
		gbc_pnlTicketButtons.gridx = 1;
		gbc_pnlTicketButtons.gridy = 2;
		pnlMainTicketContainer.add(pnlTicketButtons, gbc_pnlTicketButtons);

		JButton btnCloseTicket = new JButton("Close Ticket");
		pnlTicketButtons.add(btnCloseTicket);

		JButton btnViewTicket = new JButton("View Ticket");
		
		pnlTicketButtons.add(btnViewTicket);

		JPanel pnlNewTicket = new JPanel();
		pnlNewTicket.setBackground(Color.WHITE);
		pnlTicketScreens.add(pnlNewTicket, "newTicket");
		GridBagLayout gbl_pnlNewTicket = new GridBagLayout();
		gbl_pnlNewTicket.columnWidths = new int[]
		{ 180, 0, 180, 0 };
		gbl_pnlNewTicket.rowHeights = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlNewTicket.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlNewTicket.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlNewTicket.setLayout(gbl_pnlNewTicket);

		JPanel pnlNewTicketDetails = new JPanel();
		pnlNewTicketDetails.setBackground(Color.WHITE);
		pnlNewTicketDetails.setBorder(new TitledBorder(null, "New Ticket",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlNewTicketDetails = new GridBagConstraints();
		gbc_pnlNewTicketDetails.insets = new Insets(0, 0, 5, 5);
		gbc_pnlNewTicketDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlNewTicketDetails.gridx = 1;
		gbc_pnlNewTicketDetails.gridy = 1;
		pnlNewTicket.add(pnlNewTicketDetails, gbc_pnlNewTicketDetails);
		GridBagLayout gbl_pnlNewTicketDetails = new GridBagLayout();
		gbl_pnlNewTicketDetails.columnWidths = new int[]
		{ 5, 0, 0, 10, 0 };
		gbl_pnlNewTicketDetails.rowHeights = new int[]
		{ 5, 25, 25, 25, 0, 0 };
		gbl_pnlNewTicketDetails.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlNewTicketDetails.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlNewTicketDetails.setLayout(gbl_pnlNewTicketDetails);

		JLabel lblNewTicketTitle = new JLabel("Title: ");
		GridBagConstraints gbc_lblNewTicketTitle = new GridBagConstraints();
		gbc_lblNewTicketTitle.anchor = GridBagConstraints.EAST;
		gbc_lblNewTicketTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewTicketTitle.gridx = 1;
		gbc_lblNewTicketTitle.gridy = 1;
		pnlNewTicketDetails.add(lblNewTicketTitle, gbc_lblNewTicketTitle);

		txtNewTicketTitle = new JTextField();
		GridBagConstraints gbc_txtNewTicketTitle = new GridBagConstraints();
		gbc_txtNewTicketTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtNewTicketTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewTicketTitle.gridx = 2;
		gbc_txtNewTicketTitle.gridy = 1;
		pnlNewTicketDetails.add(txtNewTicketTitle, gbc_txtNewTicketTitle);
		txtNewTicketTitle.setColumns(10);

		JLabel lnlNewTicketCategory = new JLabel("Category: ");
		GridBagConstraints gbc_lnlNewTicketCategory = new GridBagConstraints();
		gbc_lnlNewTicketCategory.anchor = GridBagConstraints.EAST;
		gbc_lnlNewTicketCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lnlNewTicketCategory.gridx = 1;
		gbc_lnlNewTicketCategory.gridy = 2;
		pnlNewTicketDetails.add(lnlNewTicketCategory, gbc_lnlNewTicketCategory);

		categoryComboBoxModel ccbm = new categoryComboBoxModel();
		final JComboBox cmbNewTicketCategory = new JComboBox();
		cmbNewTicketCategory.setModel(ccbm);
		GridBagConstraints gbc_cmbNewTicketCategory = new GridBagConstraints();
		gbc_cmbNewTicketCategory.insets = new Insets(0, 0, 5, 5);
		gbc_cmbNewTicketCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbNewTicketCategory.gridx = 2;
		gbc_cmbNewTicketCategory.gridy = 2;
		pnlNewTicketDetails.add(cmbNewTicketCategory, gbc_cmbNewTicketCategory);

		JLabel lblNewTicketDescription = new JLabel("Description: ");
		GridBagConstraints gbc_lblNewTicketDescription = new GridBagConstraints();
		gbc_lblNewTicketDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewTicketDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewTicketDescription.gridx = 1;
		gbc_lblNewTicketDescription.gridy = 3;
		pnlNewTicketDetails.add(lblNewTicketDescription,
				gbc_lblNewTicketDescription);

		JScrollPane spNewTicketDescription = new JScrollPane();
		GridBagConstraints gbc_spNewTicketDescription = new GridBagConstraints();
		gbc_spNewTicketDescription.insets = new Insets(0, 0, 5, 5);
		gbc_spNewTicketDescription.fill = GridBagConstraints.BOTH;
		gbc_spNewTicketDescription.gridx = 2;
		gbc_spNewTicketDescription.gridy = 3;
		pnlNewTicketDetails.add(spNewTicketDescription,
				gbc_spNewTicketDescription);

		txtNewTicketDescription = new JTextArea();
		txtNewTicketDescription.setWrapStyleWord(true);
		txtNewTicketDescription.setLineWrap(true);
		spNewTicketDescription.setViewportView(txtNewTicketDescription);

		JPanel pnlNewTicketButtons = new JPanel();
		pnlNewTicketButtons.setBackground(Color.WHITE);
		FlowLayout fl_pnlNewTicketButtons = (FlowLayout) pnlNewTicketButtons
				.getLayout();
		fl_pnlNewTicketButtons.setHgap(30);
		GridBagConstraints gbc_pnlNewTicketButtons = new GridBagConstraints();
		gbc_pnlNewTicketButtons.gridwidth = 2;
		gbc_pnlNewTicketButtons.insets = new Insets(0, 0, 0, 5);
		gbc_pnlNewTicketButtons.fill = GridBagConstraints.BOTH;
		gbc_pnlNewTicketButtons.gridx = 1;
		gbc_pnlNewTicketButtons.gridy = 4;
		pnlNewTicketDetails.add(pnlNewTicketButtons, gbc_pnlNewTicketButtons);

		JButton btnSubmitTicket = new JButton("Submit");
		btnSubmitTicket.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				// Connections.createConnection();
				if (txtNewTicketTitle.getText() == null
						|| txtNewTicketTitle.getText().equals("")
						|| txtNewTicketDescription.getText() == null
						|| txtNewTicketDescription.getText().equals("")
						|| cmbNewTicketCategory.getSelectedIndex() == -1)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					JOptionPane.showMessageDialog(null,
							"Please fill in a category name.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else
				{
					String staffEmail = null;
					String departmentID = null;
					String staffID = currentStaffID;
					String ticketTitle = txtNewTicketTitle.getText();
					String ticketDescription = txtNewTicketDescription
							.getText();
					Database.getStaffDetails(staffID);

					try
					{
						// Connections.rset.next();
						departmentID = Connections.rset.getString(
								"DEPARTMENTID").trim();
						staffEmail = Connections.rset.getString("EMAIL");
						Connections.killRset();
						// Connections.rset.close();
					} catch (SQLException e)
					{
						e.printStackTrace();
					}

					String statusID = Database.getStatus("Open");
					Connections.killRset();
					String getCategoryID = (String) cmbNewTicketCategory
							.getItemAt(cmbNewTicketCategory.getSelectedIndex());
					String y[] = getCategoryID.split(" ");
					String categoryID = y[0];
					System.out.println(categoryID);
					Connections.killRset();
					Database.addTicket(staffID, ticketTitle, ticketDescription,
							departmentID, statusID, categoryID, staffEmail);

					Connections.killRset();
					System.out.println("RSET Closed....");
					// Connections.countCursors();
					Connections.killRset();
					try
					{
						Connections.rset.close();
						Connections.pstmt.close();
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Connections.killConnections();
					CardLayout tickets = (CardLayout) (pnlTicketScreens
							.getLayout());
					tickets.show(pnlTicketScreens, "mainTickets");
					currentStaffTicketTableModel.filterRows("");
					// tblStaffTicket.setModel(tblStaffTicket.getModel());
					tblStaffTicket.revalidate();
					tblStaffTicket.repaint();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					Connections.killRset();
				}
			}
		});
		pnlNewTicketButtons.add(btnSubmitTicket);

		JButton btnResetTicket = new JButton("Reset");
		pnlNewTicketButtons.add(btnResetTicket);

		JButton btnCancelTicket = new JButton("Cancel");
		btnCancelTicket.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				CardLayout tickets = (CardLayout) (pnlTicketScreens.getLayout());
				tickets.show(pnlTicketScreens, "mainTickets");
			}
		});
		pnlNewTicketButtons.add(btnCancelTicket);

		JPanel pnlClosedTickets = new JPanel();
		pnlClosedTickets.setBackground(Color.WHITE);
		pnlTicketScreens.add(pnlClosedTickets, "closedTicket");
		GridBagLayout gbl_pnlClosedTickets = new GridBagLayout();
		gbl_pnlClosedTickets.columnWidths = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlClosedTickets.rowHeights = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlClosedTickets.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlClosedTickets.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlClosedTickets.setLayout(gbl_pnlClosedTickets);

		JPanel pnlClosedTicketContainer = new JPanel();
		pnlClosedTicketContainer.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlClosedTicketContainer = new GridBagConstraints();
		gbc_pnlClosedTicketContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlClosedTicketContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlClosedTicketContainer.gridx = 1;
		gbc_pnlClosedTicketContainer.gridy = 1;
		pnlClosedTickets.add(pnlClosedTicketContainer,
				gbc_pnlClosedTicketContainer);
		GridBagLayout gbl_pnlClosedTicketContainer = new GridBagLayout();
		gbl_pnlClosedTicketContainer.columnWidths = new int[]
		{ 5, 300, 0, 5, 0 };
		gbl_pnlClosedTicketContainer.rowHeights = new int[]
		{ 50, 0, 0, 0 };
		gbl_pnlClosedTicketContainer.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlClosedTicketContainer.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlClosedTicketContainer.setLayout(gbl_pnlClosedTicketContainer);

		JPanel pnlQueryClosed = new JPanel();
		pnlQueryClosed.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Query Closed Tickets",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlQueryClosed.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlQueryClosed = new GridBagConstraints();
		gbc_pnlQueryClosed.fill = GridBagConstraints.BOTH;
		gbc_pnlQueryClosed.insets = new Insets(0, 0, 5, 5);
		gbc_pnlQueryClosed.gridx = 1;
		gbc_pnlQueryClosed.gridy = 0;
		pnlClosedTicketContainer.add(pnlQueryClosed, gbc_pnlQueryClosed);
		GridBagLayout gbl_pnlQueryClosed = new GridBagLayout();
		gbl_pnlQueryClosed.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gbl_pnlQueryClosed.rowHeights = new int[]
		{ 35, 0 };
		gbl_pnlQueryClosed.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlQueryClosed.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlQueryClosed.setLayout(gbl_pnlQueryClosed);

		JLabel lblClosedTitle = new JLabel("Title: ");
		GridBagConstraints gbc_lblClosedTitle = new GridBagConstraints();
		gbc_lblClosedTitle.fill = GridBagConstraints.VERTICAL;
		gbc_lblClosedTitle.anchor = GridBagConstraints.EAST;
		gbc_lblClosedTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblClosedTitle.gridx = 0;
		gbc_lblClosedTitle.gridy = 0;
		pnlQueryClosed.add(lblClosedTitle, gbc_lblClosedTitle);

		txtClosedTitle = new JTextField();
		txtClosedTitle.setColumns(10);
		GridBagConstraints gbc_txtClosedTitle = new GridBagConstraints();
		gbc_txtClosedTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtClosedTitle.insets = new Insets(0, 0, 0, 5);
		gbc_txtClosedTitle.gridx = 1;
		gbc_txtClosedTitle.gridy = 0;
		pnlQueryClosed.add(txtClosedTitle, gbc_txtClosedTitle);

		JButton btnClosedQuery = new JButton("Query");
		GridBagConstraints gbc_btnClosedQuery = new GridBagConstraints();
		gbc_btnClosedQuery.gridx = 2;
		gbc_btnClosedQuery.gridy = 0;
		pnlQueryClosed.add(btnClosedQuery, gbc_btnClosedQuery);

		btnClosedQuery.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				currentStaffTicketTableModelClosed.filterRows(txtClosedTitle.getText());

				tblClosedTickets.revalidate();
				tblClosedTickets.repaint();

				Connections.killRset();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		pnlClosedTicketContainer.add(scrollPane, gbc_scrollPane);

		tblClosedTickets = new JTable();
		csttmc = new currentStaffTicketTableModelClosed(staffID);
		tblClosedTickets.setModel(csttmc);
		scrollPane.setViewportView(tblClosedTickets);

		tblClosedTickets.addMouseListener(new MouseAdapter()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{

				if (e.getClickCount() > 1)
				{
					System.out.println("double click");
					int r = tblClosedTickets.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblClosedTickets.getRowCount())
					{
						tblClosedTickets.setRowSelectionInterval(r, r);
					} else
					{
						tblClosedTickets.clearSelection();
					}

					int rowindex = tblClosedTickets.getSelectedRow();
					if (rowindex < 0)
					{
						return;
					}

					int col = tblClosedTickets.getColumnModel().getColumnIndex(
							"TICKETID");
					System.out.println(col);
					String ticketID = tblClosedTickets.getValueAt(rowindex, col)
							.toString();

					userTicketView ticketView = new userTicketView(ticketID);
					ticketView.setVisible(true);

					ticketView.btnSubmit.setEnabled(false);
				}
			}
		});
		
		JPanel pnlClosedTicketControls = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlClosedTicketControls
				.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnlClosedTicketControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlClosedTicketControls = new GridBagConstraints();
		gbc_pnlClosedTicketControls.fill = GridBagConstraints.BOTH;
		gbc_pnlClosedTicketControls.gridwidth = 2;
		gbc_pnlClosedTicketControls.insets = new Insets(0, 0, 0, 5);
		gbc_pnlClosedTicketControls.gridx = 1;
		gbc_pnlClosedTicketControls.gridy = 2;
		pnlClosedTicketContainer.add(pnlClosedTicketControls,
				gbc_pnlClosedTicketControls);

		JButton btnClosedTicketView = new JButton("View Ticket");
		pnlClosedTicketControls.add(btnClosedTicketView);

		btnClosedTicketView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tabbedPane.setSelectedComponent(pnlTickets);
				CardLayout tickets = (CardLayout) (pnlTicketScreens.getLayout());
				tickets.show(pnlTicketScreens, "mainTickets");
			}
		});
		pnlKnowledgeBase = new JPanel();
		pnlKnowledgeBase.setBackground(Color.WHITE);
		tabbedPane.addTab("Knowledge Base", null, pnlKnowledgeBase, null);
		pnlKnowledgeBase.setLayout(null);

		JPanel pnlKnowledgeBaseScreen = new JPanel();
		pnlKnowledgeBaseScreen.setBackground(Color.WHITE);
		pnlKnowledgeBaseScreen.setBounds(0, 0, 779, 406);
		pnlKnowledgeBase.add(pnlKnowledgeBaseScreen);
		GridBagLayout gbl_pnlKnowledgeBaseScreen = new GridBagLayout();
		gbl_pnlKnowledgeBaseScreen.columnWidths = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlKnowledgeBaseScreen.rowHeights = new int[]
		{ 10, 0, 10, 0 };
		gbl_pnlKnowledgeBaseScreen.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlKnowledgeBaseScreen.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlKnowledgeBaseScreen.setLayout(gbl_pnlKnowledgeBaseScreen);

		JPanel pnlKnowledgeBaseTableContainer = new JPanel();
		pnlKnowledgeBaseTableContainer.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlKnowledgeBaseTableContainer = new GridBagConstraints();
		gbc_pnlKnowledgeBaseTableContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlKnowledgeBaseTableContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlKnowledgeBaseTableContainer.gridx = 1;
		gbc_pnlKnowledgeBaseTableContainer.gridy = 1;
		pnlKnowledgeBaseScreen.add(pnlKnowledgeBaseTableContainer,
				gbc_pnlKnowledgeBaseTableContainer);
		GridBagLayout gbl_pnlKnowledgeBaseTableContainer = new GridBagLayout();
		gbl_pnlKnowledgeBaseTableContainer.columnWidths = new int[]
		{ 5, 300, 0, 5, 0 };
		gbl_pnlKnowledgeBaseTableContainer.rowHeights = new int[]
		{ 50, 0, 0, 0 };
		gbl_pnlKnowledgeBaseTableContainer.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlKnowledgeBaseTableContainer.rowWeights = new double[]
		{ 0.0, 1.0, 0.0, Double.MIN_VALUE };
		pnlKnowledgeBaseTableContainer
				.setLayout(gbl_pnlKnowledgeBaseTableContainer);

		JPanel pnlKnowledgeQueryContainer = new JPanel();
		pnlKnowledgeQueryContainer.setBackground(Color.WHITE);
		pnlKnowledgeQueryContainer.setBorder(new TitledBorder(null,
				"Query Knowledge Base", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		GridBagConstraints gbc_pnlKnowledgeQueryContainer = new GridBagConstraints();
		gbc_pnlKnowledgeQueryContainer.gridwidth = 2;
		gbc_pnlKnowledgeQueryContainer.fill = GridBagConstraints.BOTH;
		gbc_pnlKnowledgeQueryContainer.insets = new Insets(0, 0, 5, 5);
		gbc_pnlKnowledgeQueryContainer.gridx = 1;
		gbc_pnlKnowledgeQueryContainer.gridy = 0;
		pnlKnowledgeBaseTableContainer.add(pnlKnowledgeQueryContainer,
				gbc_pnlKnowledgeQueryContainer);
		GridBagLayout gbl_pnlKnowledgeQueryContainer = new GridBagLayout();
		gbl_pnlKnowledgeQueryContainer.columnWidths = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pnlKnowledgeQueryContainer.rowHeights = new int[]
		{ 35, 0 };
		gbl_pnlKnowledgeQueryContainer.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_pnlKnowledgeQueryContainer.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		pnlKnowledgeQueryContainer.setLayout(gbl_pnlKnowledgeQueryContainer);

		JLabel label = new JLabel("Title: ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		pnlKnowledgeQueryContainer.add(label, gbc_label);

		txtKnowledgeTitle = new JTextField();
		txtKnowledgeTitle.setColumns(10);
		GridBagConstraints gbc_txtKnowledgeTitle = new GridBagConstraints();
		gbc_txtKnowledgeTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtKnowledgeTitle.insets = new Insets(0, 0, 0, 5);
		gbc_txtKnowledgeTitle.gridx = 1;
		gbc_txtKnowledgeTitle.gridy = 0;
		pnlKnowledgeQueryContainer
				.add(txtKnowledgeTitle, gbc_txtKnowledgeTitle);

		JLabel lblCategory = new JLabel("Category");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.EAST;
		gbc_lblCategory.insets = new Insets(0, 0, 0, 5);
		gbc_lblCategory.gridx = 3;
		gbc_lblCategory.gridy = 0;
		pnlKnowledgeQueryContainer.add(lblCategory, gbc_lblCategory);

		final JComboBox cmbCategory = new JComboBox();
		categoryComboBoxModel catboxmod = new categoryComboBoxModel();
		cmbCategory.setModel(catboxmod);
		GridBagConstraints gbc_cmbCategory = new GridBagConstraints();
		gbc_cmbCategory.gridwidth = 4;
		gbc_cmbCategory.insets = new Insets(0, 0, 0, 5);
		gbc_cmbCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCategory.gridx = 4;
		gbc_cmbCategory.gridy = 0;
		pnlKnowledgeQueryContainer.add(cmbCategory, gbc_cmbCategory);

		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 0, 5);
		gbc_lblDepartment.gridx = 8;
		gbc_lblDepartment.gridy = 0;
		pnlKnowledgeQueryContainer.add(lblDepartment, gbc_lblDepartment);

		final JComboBox cmbDept = new JComboBox();
		staffDeptComboModel sdcm = new staffDeptComboModel();
		cmbDept.setModel(sdcm);
		GridBagConstraints gbc_cmbDept = new GridBagConstraints();
		gbc_cmbDept.gridwidth = 3;
		gbc_cmbDept.insets = new Insets(0, 0, 0, 5);
		gbc_cmbDept.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDept.gridx = 9;
		gbc_cmbDept.gridy = 0;
		pnlKnowledgeQueryContainer.add(cmbDept, gbc_cmbDept);

		JButton btnKnowledgeQuery = new JButton("Query");
		btnKnowledgeQuery.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{

				String deptID = "";
				String catID = "";

				if (cmbDept.getSelectedIndex() < 0)
				{
					deptID = "";
				} else
				{
					deptID = cmbDept.getSelectedItem().toString().split(" ")
							.clone()[0];
				}
				if (cmbCategory.getSelectedIndex() < 0)
				{
					catID = "";
				} else
				{
					catID = cmbCategory.getSelectedItem().toString().split(" ")
							.clone()[0];
				}

				knowledgeBaseTableModel.filterRows(txtKnowledgeTitle.getText(),
						deptID, catID);

				tblKnowledge.repaint();

				Connections.killRset();
			}
		});
		GridBagConstraints gbc_btnKnowledgeQuery = new GridBagConstraints();
		gbc_btnKnowledgeQuery.gridx = 13;
		gbc_btnKnowledgeQuery.gridy = 0;
		pnlKnowledgeQueryContainer
				.add(btnKnowledgeQuery, gbc_btnKnowledgeQuery);

		tblKnowledge = new JTable();
		tblKnowledge.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final knowledgeBaseTableModel kbtm = new knowledgeBaseTableModel();
		tblKnowledge.setModel(kbtm);

		JScrollPane knowledgeTable = new JScrollPane();
		// knowledgeTable.setViewportView(knowledgeTable);
		GridBagConstraints gbc_knowledgeTable = new GridBagConstraints();
		gbc_knowledgeTable.fill = GridBagConstraints.BOTH;
		gbc_knowledgeTable.gridwidth = 2;
		gbc_knowledgeTable.insets = new Insets(0, 0, 5, 5);
		gbc_knowledgeTable.gridx = 1;
		gbc_knowledgeTable.gridy = 1;
		pnlKnowledgeBaseTableContainer.add(knowledgeTable, gbc_knowledgeTable);

		knowledgeTable.setViewportView(tblKnowledge);

		tblKnowledge.addMouseListener(new MouseAdapter()
		{

			public void mouseReleased(MouseEvent e)
			{

				Connections.killRset();
				if (e.getClickCount() > 1
						&& e.getButton() == java.awt.event.MouseEvent.BUTTON1)
				{
					System.out.println("Double Click");
					int r = tblKnowledge.rowAtPoint(e.getPoint());
					if (r >= 0 && r < tblKnowledge.getRowCount())
					{
						tblKnowledge.setRowSelectionInterval(r, r);
					} else
					{
						tblKnowledge.clearSelection();
					}

					int rowindex = tblKnowledge.getSelectedRow();
					if (rowindex < 0)
					{
						return;
					}

					int col = tblKnowledge.getColumnModel().getColumnIndex(
							"KNOWLEDGEID");
					System.out.println(col);
					String knowledgeID = tblKnowledge.getValueAt(rowindex, col)
							.toString();
					Connections.killRset();
					System.out.println("Knowledge #  : " + knowledgeID);

					KnowledgeInformation knowledge = new KnowledgeInformation(
							knowledgeID);
					knowledge.show();
					Connections.killRset();
				}

			}
		}

		);

		JPanel pnlKnowledgeControls = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlKnowledgeControls.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pnlKnowledgeControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlKnowledgeControls = new GridBagConstraints();
		gbc_pnlKnowledgeControls.fill = GridBagConstraints.BOTH;
		gbc_pnlKnowledgeControls.gridwidth = 2;
		gbc_pnlKnowledgeControls.insets = new Insets(0, 0, 0, 5);
		gbc_pnlKnowledgeControls.gridx = 1;
		gbc_pnlKnowledgeControls.gridy = 2;
		pnlKnowledgeBaseTableContainer.add(pnlKnowledgeControls,
				gbc_pnlKnowledgeControls);

		JButton btnKnowledgeView = new JButton("View Ticket");
		pnlKnowledgeControls.add(btnKnowledgeView);
	}
}
