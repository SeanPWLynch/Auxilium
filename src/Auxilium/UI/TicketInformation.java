package Auxilium.UI;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.JButton;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;
import Auxilium.ComboBoxModels.categoryComboBoxModel;
import Auxilium.ComboBoxModels.staffComboBoxModel;
import Auxilium.ComboBoxModels.staffDeptComboModel;
import Auxilium.ComboBoxModels.statusComboBoxModel;
import Auxilium.ComboBoxModels.ticketComboBoxModel;
import Auxilium.TableModels.allAdminTicketTableModel;
import Auxilium.TableModels.currentAdminTicketTableModel;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

public class TicketInformation extends JDialog
{
	private JTextField txtTicketName;
	private JTextField txtDateCreated;
	private JTextField txtDateClosed;

	JLabel lblTicketID = new JLabel("ticket#");
	JComboBox cmbToAdmin = new JComboBox();
	JComboBox cmbFromUser = new JComboBox();
	JComboBox cmbTicket = new JComboBox();
	JSpinner numPriority = new JSpinner();
	JComboBox cmbDepartment = new JComboBox();
	JComboBox cmbStatus = new JComboBox();
	JComboBox cmbCategory = new JComboBox();
	public static JTextArea txtDescription = new JTextArea();
	private JTextArea txtTicketReply;
	private JButton btnTicketReply;

	/**
	 * Create the panel.
	 */
	public TicketInformation(final String ticketID)
	{
		Connections.killRset();
		setBounds(100, 100, 701, 851);
		setTitle("View and Edit Ticket");
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 28, 53, 10, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 70, 35, 26, 0, 0, 0, 0, 0, 0, 55, 90, 10, 10, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TicketInformation.class
				.getResource("/Auxilium/Images/logoSmall.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.anchor = GridBagConstraints.WEST;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogo.gridx = 1;
		gbc_lblLogo.gridy = 0;
		getContentPane().add(lblLogo, gbc_lblLogo);

		JPanel pnlChooseTicket = new JPanel();
		pnlChooseTicket.setBackground(Color.WHITE);
		pnlChooseTicket.setBorder(new TitledBorder(null, "Ticket",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlChooseTicket = new GridBagConstraints();
		gbc_pnlChooseTicket.gridwidth = 2;
		gbc_pnlChooseTicket.insets = new Insets(0, 0, 5, 5);
		gbc_pnlChooseTicket.fill = GridBagConstraints.BOTH;
		gbc_pnlChooseTicket.gridx = 1;
		gbc_pnlChooseTicket.gridy = 1;
		getContentPane().add(pnlChooseTicket, gbc_pnlChooseTicket);
		GridBagLayout gbl_pnlChooseTicket = new GridBagLayout();
		gbl_pnlChooseTicket.columnWidths = new int[]
		{ 260, 0 };
		gbl_pnlChooseTicket.rowHeights = new int[]
		{ 0, 8, 0 };
		gbl_pnlChooseTicket.columnWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		gbl_pnlChooseTicket.rowWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		pnlChooseTicket.setLayout(gbl_pnlChooseTicket);

		JLabel lblChooseTicket = new JLabel("Choose Ticket :");
		GridBagConstraints gbc_lblChooseTicket = new GridBagConstraints();
		gbc_lblChooseTicket.anchor = GridBagConstraints.WEST;
		gbc_lblChooseTicket.insets = new Insets(0, 0, 5, 0);
		gbc_lblChooseTicket.gridx = 0;
		gbc_lblChooseTicket.gridy = 0;
		pnlChooseTicket.add(lblChooseTicket, gbc_lblChooseTicket);

		ticketComboBoxModel tcm = new ticketComboBoxModel();
		cmbTicket.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cmbTicket.getSelectedIndex() >= 0)
				{
					setInfo(cmbTicket.getSelectedItem().toString().split(" ")
							.clone()[0]);
				}

			}

		});
		cmbTicket.setModel(tcm);
		Connections.killRset();
		GridBagConstraints gbc_cmbTicket = new GridBagConstraints();
		gbc_cmbTicket.anchor = GridBagConstraints.SOUTH;
		gbc_cmbTicket.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTicket.gridx = 0;
		gbc_cmbTicket.gridy = 1;
		pnlChooseTicket.add(cmbTicket, gbc_cmbTicket);

		JPanel pnlTicketDetails = new JPanel();
		pnlTicketDetails.setBackground(Color.WHITE);
		pnlTicketDetails.setBorder(new TitledBorder(null, "Ticket Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlTicketDetails = new GridBagConstraints();
		gbc_pnlTicketDetails.gridheight = 11;
		gbc_pnlTicketDetails.gridwidth = 2;
		gbc_pnlTicketDetails.insets = new Insets(0, 0, 5, 5);
		gbc_pnlTicketDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlTicketDetails.gridx = 1;
		gbc_pnlTicketDetails.gridy = 2;
		getContentPane().add(pnlTicketDetails, gbc_pnlTicketDetails);
		GridBagLayout gbl_pnlTicketDetails = new GridBagLayout();
		gbl_pnlTicketDetails.columnWidths = new int[]
		{ 0, 180, 0, 0 };
		gbl_pnlTicketDetails.rowHeights = new int[]
		{ 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 0, 0, 0, 20 };
		gbl_pnlTicketDetails.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlTicketDetails.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		pnlTicketDetails.setLayout(gbl_pnlTicketDetails);

		final JLabel lblTicketId = new JLabel("Ticket ID :");
		lblTicketId.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblTicketId = new GridBagConstraints();
		gbc_lblTicketId.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketId.anchor = GridBagConstraints.WEST;
		gbc_lblTicketId.gridx = 0;
		gbc_lblTicketId.gridy = 0;
		pnlTicketDetails.add(lblTicketId, gbc_lblTicketId);

		GridBagConstraints gbc_lblTicketID = new GridBagConstraints();
		gbc_lblTicketID.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketID.gridx = 1;
		gbc_lblTicketID.gridy = 0;
		pnlTicketDetails.add(lblTicketID, gbc_lblTicketID);

		JLabel lblTicketDescription = new JLabel("Ticket Description :");
		lblTicketDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblTicketDescription = new GridBagConstraints();
		gbc_lblTicketDescription.anchor = GridBagConstraints.WEST;
		gbc_lblTicketDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblTicketDescription.gridx = 2;
		gbc_lblTicketDescription.gridy = 0;
		pnlTicketDetails.add(lblTicketDescription, gbc_lblTicketDescription);

		JLabel lblTicketName = new JLabel("Ticket Name :");
		lblTicketName.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblTicketName = new GridBagConstraints();
		gbc_lblTicketName.anchor = GridBagConstraints.WEST;
		gbc_lblTicketName.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketName.gridx = 0;
		gbc_lblTicketName.gridy = 1;
		pnlTicketDetails.add(lblTicketName, gbc_lblTicketName);

		txtTicketName = new JTextField();
		GridBagConstraints gbc_txtTicketName = new GridBagConstraints();
		gbc_txtTicketName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTicketName.insets = new Insets(0, 0, 5, 5);
		gbc_txtTicketName.gridx = 1;
		gbc_txtTicketName.gridy = 1;
		pnlTicketDetails.add(txtTicketName, gbc_txtTicketName);
		txtTicketName.setColumns(10);

		JPanel pnlTicketDescription = new JPanel();
		pnlTicketDescription.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlTicketDescription = new GridBagConstraints();
		gbc_pnlTicketDescription.fill = GridBagConstraints.BOTH;
		gbc_pnlTicketDescription.gridheight = 9;
		gbc_pnlTicketDescription.insets = new Insets(0, 0, 5, 0);
		gbc_pnlTicketDescription.gridx = 2;
		gbc_pnlTicketDescription.gridy = 1;
		pnlTicketDetails.add(pnlTicketDescription, gbc_pnlTicketDescription);
		GridBagLayout gbl_pnlTicketDescription = new GridBagLayout();
		gbl_pnlTicketDescription.columnWidths = new int[]
		{ 0, 0 };
		gbl_pnlTicketDescription.rowHeights = new int[]
		{ 20, 0 };
		gbl_pnlTicketDescription.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gbl_pnlTicketDescription.rowWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		pnlTicketDescription.setLayout(gbl_pnlTicketDescription);

		JScrollPane spDescription = new JScrollPane();
		GridBagConstraints gbc_spDescription = new GridBagConstraints();
		gbc_spDescription.fill = GridBagConstraints.BOTH;
		gbc_spDescription.gridx = 0;
		gbc_spDescription.gridy = 0;
		pnlTicketDescription.add(spDescription, gbc_spDescription);
		txtDescription.setEditable(false);

		txtDescription.setWrapStyleWord(true);
		spDescription.setViewportView(txtDescription);
		txtDescription.setLineWrap(true);

		JLabel lblFromUser = new JLabel("From User :");
		lblFromUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblFromUser = new GridBagConstraints();
		gbc_lblFromUser.anchor = GridBagConstraints.WEST;
		gbc_lblFromUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblFromUser.gridx = 0;
		gbc_lblFromUser.gridy = 2;
		pnlTicketDetails.add(lblFromUser, gbc_lblFromUser);

		cmbFromUser.setEnabled(false);
		staffComboBoxModel scm = new staffComboBoxModel();
		cmbFromUser.setModel(scm);
		GridBagConstraints gbc_cmbFromUser = new GridBagConstraints();
		gbc_cmbFromUser.insets = new Insets(0, 0, 5, 5);
		gbc_cmbFromUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFromUser.gridx = 1;
		gbc_cmbFromUser.gridy = 2;
		pnlTicketDetails.add(cmbFromUser, gbc_cmbFromUser);

		JLabel lblAssignedToAdmin = new JLabel("Assigned To Admin :");
		lblAssignedToAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblAssignedToAdmin = new GridBagConstraints();
		gbc_lblAssignedToAdmin.anchor = GridBagConstraints.WEST;
		gbc_lblAssignedToAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssignedToAdmin.gridx = 0;
		gbc_lblAssignedToAdmin.gridy = 3;
		pnlTicketDetails.add(lblAssignedToAdmin, gbc_lblAssignedToAdmin);

		staffComboBoxModel admins = new staffComboBoxModel();
		cmbToAdmin.setModel(admins);
		Connections.killRset();
		GridBagConstraints gbc_cmbToAdmin = new GridBagConstraints();
		gbc_cmbToAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_cmbToAdmin.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbToAdmin.gridx = 1;
		gbc_cmbToAdmin.gridy = 3;
		pnlTicketDetails.add(cmbToAdmin, gbc_cmbToAdmin);

		JLabel lblCurrentStatus = new JLabel("Current Status :");
		lblCurrentStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblCurrentStatus = new GridBagConstraints();
		gbc_lblCurrentStatus.anchor = GridBagConstraints.WEST;
		gbc_lblCurrentStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentStatus.gridx = 0;
		gbc_lblCurrentStatus.gridy = 4;
		pnlTicketDetails.add(lblCurrentStatus, gbc_lblCurrentStatus);

		statusComboBoxModel scbm = new statusComboBoxModel();
		cmbStatus.setModel(scbm);
		Connections.killRset();
		GridBagConstraints gbc_cmbStatus = new GridBagConstraints();
		gbc_cmbStatus.insets = new Insets(0, 0, 5, 5);
		gbc_cmbStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbStatus.gridx = 1;
		gbc_cmbStatus.gridy = 4;
		pnlTicketDetails.add(cmbStatus, gbc_cmbStatus);

		JLabel lblPriority = new JLabel("Priority :");
		lblPriority.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.anchor = GridBagConstraints.WEST;
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 5;
		pnlTicketDetails.add(lblPriority, gbc_lblPriority);

		GridBagConstraints gbc_numPriority = new GridBagConstraints();
		gbc_numPriority.fill = GridBagConstraints.HORIZONTAL;
		gbc_numPriority.insets = new Insets(0, 0, 5, 5);
		gbc_numPriority.gridx = 1;
		gbc_numPriority.gridy = 5;
		numPriority.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		pnlTicketDetails.add(numPriority, gbc_numPriority);

		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.WEST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 0;
		gbc_lblCategory.gridy = 6;
		pnlTicketDetails.add(lblCategory, gbc_lblCategory);

		categoryComboBoxModel cbm = new categoryComboBoxModel();
		cmbCategory.setModel(cbm);
		Connections.killRset();
		GridBagConstraints gbc_cmbCategory = new GridBagConstraints();
		gbc_cmbCategory.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCategory.gridx = 1;
		gbc_cmbCategory.gridy = 6;
		pnlTicketDetails.add(cmbCategory, gbc_cmbCategory);

		JLabel lblDepartment = new JLabel("Department :");
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.WEST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 7;
		pnlTicketDetails.add(lblDepartment, gbc_lblDepartment);

		staffDeptComboModel dcm = new staffDeptComboModel();
		cmbDepartment.setModel(dcm);
		Connections.killRset();
		GridBagConstraints gbc_cmbDepartment = new GridBagConstraints();
		gbc_cmbDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_cmbDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDepartment.gridx = 1;
		gbc_cmbDepartment.gridy = 7;
		pnlTicketDetails.add(cmbDepartment, gbc_cmbDepartment);

		JLabel lblDateCreated = new JLabel("Date Created :");
		lblDateCreated.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblDateCreated = new GridBagConstraints();
		gbc_lblDateCreated.anchor = GridBagConstraints.WEST;
		gbc_lblDateCreated.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateCreated.gridx = 0;
		gbc_lblDateCreated.gridy = 8;
		pnlTicketDetails.add(lblDateCreated, gbc_lblDateCreated);

		txtDateCreated = new JTextField();
		txtDateCreated.setEditable(false);
		GridBagConstraints gbc_txtDateCreated = new GridBagConstraints();
		gbc_txtDateCreated.insets = new Insets(0, 0, 5, 5);
		gbc_txtDateCreated.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDateCreated.gridx = 1;
		gbc_txtDateCreated.gridy = 8;
		pnlTicketDetails.add(txtDateCreated, gbc_txtDateCreated);
		txtDateCreated.setColumns(10);

		JLabel lblDateClosed = new JLabel("Date Closed :");
		lblDateClosed.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblDateClosed = new GridBagConstraints();
		gbc_lblDateClosed.anchor = GridBagConstraints.WEST;
		gbc_lblDateClosed.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateClosed.gridx = 0;
		gbc_lblDateClosed.gridy = 9;
		pnlTicketDetails.add(lblDateClosed, gbc_lblDateClosed);

		txtDateClosed = new JTextField();
		txtDateClosed.setEditable(false);
		GridBagConstraints gbc_txtDateClosed = new GridBagConstraints();
		gbc_txtDateClosed.insets = new Insets(0, 0, 5, 5);
		gbc_txtDateClosed.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDateClosed.gridx = 1;
		gbc_txtDateClosed.gridy = 9;
		pnlTicketDetails.add(txtDateClosed, gbc_txtDateClosed);
		txtDateClosed.setColumns(10);

		JLabel lblTicketReply = new JLabel("Ticket Reply: ");
		GridBagConstraints gbc_lblTicketReply = new GridBagConstraints();
		gbc_lblTicketReply.anchor = GridBagConstraints.WEST;
		gbc_lblTicketReply.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketReply.gridx = 0;
		gbc_lblTicketReply.gridy = 10;
		pnlTicketDetails.add(lblTicketReply, gbc_lblTicketReply);
		lblTicketReply.setBackground(Color.WHITE);
		lblTicketReply.setFont(new Font("Tahoma", Font.BOLD, 11));

		JPanel pnlTicketReply = new JPanel();
		pnlTicketReply.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING,

				TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlTicketReply = new GridBagConstraints();
		gbc_pnlTicketReply.gridwidth = 3;
		gbc_pnlTicketReply.insets = new Insets(0, 0, 5, 0);
		gbc_pnlTicketReply.fill = GridBagConstraints.BOTH;
		gbc_pnlTicketReply.gridx = 0;
		gbc_pnlTicketReply.gridy = 11;
		pnlTicketDetails.add(pnlTicketReply, gbc_pnlTicketReply);
		GridBagLayout gbl_pnlTicketReply = new GridBagLayout();
		gbl_pnlTicketReply.columnWidths = new int[]
		{ 0, 0 };
		gbl_pnlTicketReply.rowHeights = new int[]
		{ 20, 0 };
		gbl_pnlTicketReply.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gbl_pnlTicketReply.rowWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		pnlTicketReply.setLayout(gbl_pnlTicketReply);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlTicketReply.add(scrollPane, gbc_scrollPane);

		txtTicketReply = new JTextArea();
		txtTicketReply.setEditable(false);
		txtTicketReply.setLineWrap(true);
		scrollPane.setViewportView(txtTicketReply);

		JPanel btnControls = new JPanel();
		btnControls.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnControls = new GridBagConstraints();
		gbc_btnControls.gridwidth = 2;
		gbc_btnControls.fill = GridBagConstraints.BOTH;
		gbc_btnControls.gridx = 1;
		gbc_btnControls.gridy = 12;
		pnlTicketDetails.add(btnControls, gbc_btnControls);

		JButton btnUpdateInfo = new JButton("Update Info");
		btnUpdateInfo.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String dateClosed = null;

				if (txtDateClosed.getText().equals((" ")))
				{
					if (cmbStatus.getSelectedItem().toString().split(" ")
							.clone()[2].equals("Closed"))
					{
						Database.closeTicket(ticketID);
						txtDateClosed.setText(Database.getDate());
						dateClosed = txtDateClosed.getText();
						Database.postClosedTicketToStaff(ticketID);
					} else
					{
						dateClosed = null;
						txtDateClosed.setText("");
					}
				} else
				{
					if (cmbStatus.getSelectedItem().toString().split(" ")
							.clone()[2].equals("Open"))
					{
						txtDateClosed.setText("");
						dateClosed = txtDateClosed.getText();
					} else
					{
						dateClosed = txtDateClosed.getText();
					}
				}
				if (cmbToAdmin.getSelectedItem().toString().split(" ").clone()[0]
						.equals(Database.getTicketAdmin(cmbTicket
								.getSelectedItem().toString().split(" ")
								.clone()[0])))
				{
				} else
				{
					Database.transferTicket(cmbTicket.getSelectedItem()
							.toString().split(" ").clone()[0], cmbToAdmin
							.getSelectedItem().toString().split(" ").clone()[0]);
				}
				Database.editTicket(cmbTicket.getSelectedItem().toString()
						.split(" ").clone()[0], cmbToAdmin.getSelectedItem()
						.toString().split(" ").clone()[0], cmbFromUser
						.getSelectedItem().toString().split(" ").clone()[0],
						txtTicketName.getText(), txtDescription.getText()
								.replace("'", "''"), Integer
								.parseInt(numPriority.getValue().toString()),
						cmbDepartment.getSelectedItem().toString().split(" ")
								.clone()[0], dateClosed, cmbStatus
								.getSelectedItem().toString().split(" ")
								.clone()[0], cmbCategory.getSelectedItem()
								.toString().split(" ").clone()[0]);
				System.out.println("Edited ticket: " + lblTicketId.getText());
				currentAdminTicketTableModel cattm = new currentAdminTicketTableModel(
						adminUI.currentAdminID);

				adminUI.tblAssignedTickets.setModel(cattm);
				adminUI.tblAssignedTickets.revalidate();
				adminUI.tblAssignedTickets.repaint();
				allAdminTicketTableModel aatm = new allAdminTicketTableModel();
				adminUI.tblAllTickets.setModel(aatm);
				adminUI.tblAllTickets.revalidate();
				adminUI.tblAllTickets.repaint();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null,
						"Ticket Information Updated", "Information Message",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnControls.add(btnUpdateInfo);

		btnTicketReply = new JButton("Reply To Ticket");
		btnTicketReply.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (btnTicketReply.getText().equals("Reply To Ticket"))
				{
					txtTicketReply.setEditable(true);
					txtTicketReply.grabFocus();
					btnTicketReply.setText("Send Reply");
				} else
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					Database.postReplyToTicket(lblTicketID.getText().trim(),
							txtTicketReply.getText(), adminUI.currentAdminID);
					TicketInformation.txtDescription.setText(Database
							.getTicketDescription(lblTicketID.getText().trim()));
					txtTicketReply.setEditable(false);
					btnTicketReply.grabFocus();
					JOptionPane.showMessageDialog(null, "Ticket Updated",
							"Information Message", JOptionPane.PLAIN_MESSAGE);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					btnTicketReply.setText("Reply To Ticket");

				}

			}
		});
		btnControls.add(btnTicketReply);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				adminUI.tblAssignedTickets.revalidate();
				adminUI.tblAssignedTickets.repaint();
				TicketInformation.this.dispose();
			}
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{

				JOptionPane pane = new JOptionPane(
						"Are you sure you want to delete the ticket : "
								+ Database.getTicketTitle(ticketID));
				Object[] options = new String[]
				{ "Yes", "No" };
				pane.setOptions(options);
				JDialog dialog = pane.createDialog(new JFrame(),
						"Are you sure?");
				dialog.show();
				if (pane.getValue().toString().equals("Yes"))
				{
					Database.deleteTicket(ticketID);
					adminUI.tblAssignedTickets.revalidate();
					adminUI.tblAssignedTickets.repaint();
					adminUI.tblAllTickets.repaint();
					// cmbTicket.removeAllItems();
					ticketComboBoxModel.reload();
					// cmbTicket.setModel(cmbTicket.getModel());
					cmbTicket.revalidate();
					cmbTicket.repaint();
					cmbTicket.setSelectedIndex(-1);

					lblTicketID.setText("");
					txtTicketName.setText("");
					txtDescription.setText("");
					cmbFromUser.setSelectedIndex(-1);
					cmbToAdmin.setSelectedIndex(-1);
					cmbCategory.setSelectedIndex(-1);
					cmbStatus.setSelectedIndex(-1);
					// setInfo(cmbTicket.getSelectedItem().toString().split(" ").clone()[0]);
					// btnClear
				}

			}
		});
		btnControls.add(btnDelete);
		btnControls.add(btnCancel);

		Connections.killRset();
		setInfo(ticketID);

	}

	public void setInfo(final String ticketID)
	{
		lblTicketID.setText(ticketID);
		txtTicketName.setText(Database.getTicketTitle(ticketID));
		txtDescription.setText(Database.getTicketDescription(ticketID).replace(
				"''", "'"));
		Connections.killRset();
		cmbFromUser.setSelectedItem(Database.getTicketUser(ticketID));
		Connections.killRset();
		for (int i = 0; i <= cmbFromUser.getItemCount(); i++)
		{
			if (cmbFromUser.getItemAt(i).toString()
					.startsWith(Database.getTicketUser(ticketID).trim()))
			{
				cmbFromUser.setSelectedIndex(i);
				System.out.println(Database.getTicketUser(ticketID).toString());
				Connections.killRset();
				System.out.println(i);
				Connections.killRset();
				break;
			}
			Connections.killRset();
		}
		for (int i = 0; i <= cmbToAdmin.getItemCount(); i++)
		{
			if (cmbToAdmin.getItemAt(i).toString()
					.startsWith(Database.getTicketAdmin(ticketID).trim()))
			{
				cmbToAdmin.setSelectedIndex(i);
				System.out
						.println(Database.getTicketAdmin(ticketID).toString());
				Connections.killRset();
				System.out.println(i);
				Connections.killRset();
				break;
			}
			Connections.killRset();
		}
		for (int i = 0; i <= cmbCategory.getItemCount(); i++)
		{
			if (cmbCategory.getItemAt(i).toString()
					.startsWith(Database.getTicketCat(ticketID.trim())))
			{
				cmbCategory.setSelectedIndex(i);
				System.out.println(Database.getTicketCat(ticketID).toString());
				Connections.killRset();
				System.out.println(i);
				Connections.killRset();
				break;
			}
			Connections.killRset();
		}
		for (int i = 0; i <= cmbDepartment.getItemCount(); i++)
		{
			if (cmbDepartment.getItemAt(i).toString()
					.startsWith(Database.getTicketDepartment(ticketID.trim())))
			{
				cmbDepartment.setSelectedIndex(i);
				System.out.println(Database.getTicketDepartment(ticketID)
						.toString());
				Connections.killRset();
				System.out.println(i);
				Connections.killRset();
				break;
			}
			Connections.killRset();
		}
		for (int i = 0; i <= cmbStatus.getItemCount(); i++)
		{
			if (cmbStatus.getItemAt(i).toString()
					.startsWith(Database.getTicketStatus(ticketID.trim())))
			{
				cmbStatus.setSelectedIndex(i);
				System.out.println(Database.getTicketStatus(ticketID)
						.toString());
				Connections.killRset();
				System.out.println(i);
				Connections.killRset();
				break;
			}
			Connections.killRset();
		}
		for (int i = 0; i <= cmbTicket.getItemCount(); i++)
		{
			if (cmbTicket.getItemAt(i).toString().startsWith((ticketID.trim())))
			{
				cmbTicket.setSelectedIndex(i);
				// System.out.println(Database.getTicketStatus(ticketID).toString());
				System.out.println(i);
				Connections.killRset();
				break;
			}
			Connections.killRset();
		}

		if (Database.canAdminTransfer(adminUI.currentAdminID).equals("true"))
		{
			cmbToAdmin.setEnabled(true);
		} else
		{
			cmbToAdmin.setEnabled(false);
		}
		Connections.killRset();

		numPriority.setValue(Integer.parseInt(Database
				.getTicketPriority(ticketID)));
		Connections.killRset();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat old = new SimpleDateFormat("yyyy-MM-dd");
		String created = null;
		try
		{
			created = formatter.format(old.parse(Database
					.getTicketDateCreated(ticketID).split(" ").clone()[0]));
		} catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtDateCreated.setText(created.toString());
		Connections.killRset();
		if (Database.getTicketDateClosed(ticketID).equals(null)
				|| Database.getTicketDateClosed(ticketID).equals(" "))
		{
			Connections.killRset();
			txtDateClosed.setText(" ");
		} else
		{
			String closed = "";
			try
			{
				closed = formatter.format(old.parse(Database
						.getTicketDateClosed(ticketID).toString().split(" ")
						.clone()[0]));
			} catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtDateClosed.setText(closed.toString());
		}
		Connections.killRset();
		try
		{
			Connections.rset.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
