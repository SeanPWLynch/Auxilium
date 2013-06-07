package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class userTicketView extends JFrame
{

	private JPanel contentPane;
	private JTextField txtTicketID;
	private JTextField txtTicketTitle;
	private JTextField txtTicketCategory;
	private JTextField txtAdminName;
	private static String ticketID;
	Database db = new Database();
	private JTextArea txtTicketDescription;

	public JButton btnSubmit;
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
					userTicketView frame = new userTicketView(ticketID);
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
	 * @param ticketID 
	 */
	public userTicketView(final String ticketID)
	{
		userTicketView.ticketID = ticketID;
		setTitle(ticketID.trim() + " - Reply");
		setIconImage(Toolkit.getDefaultToolkit().getImage(userTicketView.class.getResource("/Auxilium/Images/icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{5, 0, 5, 0};
		gbl_contentPane.rowHeights = new int[]{5, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBackground(Color.WHITE);
		lblLogo.setIcon(new ImageIcon(userTicketView.class.getResource("/Auxilium/Images/logoSmall.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogo.gridx = 1;
		gbc_lblLogo.gridy = 0;
		contentPane.add(lblLogo, gbc_lblLogo);
		
		JPanel pnlTicketDetails = new JPanel();
		pnlTicketDetails.setBorder(new TitledBorder(null, "Ticket Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTicketDetails.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlTicketDetails = new GridBagConstraints();
		gbc_pnlTicketDetails.insets = new Insets(0, 0, 5, 5);
		gbc_pnlTicketDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlTicketDetails.gridx = 1;
		gbc_pnlTicketDetails.gridy = 1;
		contentPane.add(pnlTicketDetails, gbc_pnlTicketDetails);
		GridBagLayout gbl_pnlTicketDetails = new GridBagLayout();
		gbl_pnlTicketDetails.columnWidths = new int[]{100, 0, 0};
		gbl_pnlTicketDetails.rowHeights = new int[]{30, 30, 30, 30, 30, 30, 0};
		gbl_pnlTicketDetails.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlTicketDetails.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		pnlTicketDetails.setLayout(gbl_pnlTicketDetails);
		
		JLabel lblTicketID = new JLabel("Ticket ID: ");
		GridBagConstraints gbc_lblTicketID = new GridBagConstraints();
		gbc_lblTicketID.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketID.anchor = GridBagConstraints.EAST;
		gbc_lblTicketID.gridx = 0;
		gbc_lblTicketID.gridy = 0;
		pnlTicketDetails.add(lblTicketID, gbc_lblTicketID);
		
		txtTicketID = new JTextField();
		txtTicketID.setEditable(false);
		GridBagConstraints gbc_txtTicketID = new GridBagConstraints();
		gbc_txtTicketID.insets = new Insets(0, 0, 5, 0);
		gbc_txtTicketID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTicketID.gridx = 1;
		gbc_txtTicketID.gridy = 0;
		pnlTicketDetails.add(txtTicketID, gbc_txtTicketID);
		txtTicketID.setColumns(10);
		
		JLabel lblTicketTitle = new JLabel("Ticket Title: ");
		GridBagConstraints gbc_lblTicketTitle = new GridBagConstraints();
		gbc_lblTicketTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTicketTitle.gridx = 0;
		gbc_lblTicketTitle.gridy = 1;
		pnlTicketDetails.add(lblTicketTitle, gbc_lblTicketTitle);
		
		txtTicketTitle = new JTextField();
		txtTicketTitle.setEditable(false);
		txtTicketTitle.setColumns(10);
		GridBagConstraints gbc_txtTicketTitle = new GridBagConstraints();
		gbc_txtTicketTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtTicketTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTicketTitle.gridx = 1;
		gbc_txtTicketTitle.gridy = 1;
		pnlTicketDetails.add(txtTicketTitle, gbc_txtTicketTitle);
		
		JLabel lblTicketCategory = new JLabel("Ticket Category: ");
		GridBagConstraints gbc_lblTicketCategory = new GridBagConstraints();
		gbc_lblTicketCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketCategory.anchor = GridBagConstraints.EAST;
		gbc_lblTicketCategory.gridx = 0;
		gbc_lblTicketCategory.gridy = 2;
		pnlTicketDetails.add(lblTicketCategory, gbc_lblTicketCategory);
		
		txtTicketCategory = new JTextField();
		txtTicketCategory.setEditable(false);
		txtTicketCategory.setColumns(10);
		GridBagConstraints gbc_txtTicketCategory = new GridBagConstraints();
		gbc_txtTicketCategory.insets = new Insets(0, 0, 5, 0);
		gbc_txtTicketCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTicketCategory.gridx = 1;
		gbc_txtTicketCategory.gridy = 2;
		pnlTicketDetails.add(txtTicketCategory, gbc_txtTicketCategory);
		
		JLabel lblAdminName = new JLabel("Admin Name: ");
		GridBagConstraints gbc_lblAdminName = new GridBagConstraints();
		gbc_lblAdminName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdminName.fill = GridBagConstraints.VERTICAL;
		gbc_lblAdminName.anchor = GridBagConstraints.EAST;
		gbc_lblAdminName.gridx = 0;
		gbc_lblAdminName.gridy = 3;
		pnlTicketDetails.add(lblAdminName, gbc_lblAdminName);
		
		txtAdminName = new JTextField();
		txtAdminName.setEditable(false);
		txtAdminName.setColumns(10);
		GridBagConstraints gbc_txtAdminName = new GridBagConstraints();
		gbc_txtAdminName.insets = new Insets(0, 0, 5, 0);
		gbc_txtAdminName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAdminName.gridx = 1;
		gbc_txtAdminName.gridy = 3;
		pnlTicketDetails.add(txtAdminName, gbc_txtAdminName);
		
		JLabel lblTicketDescription = new JLabel("Ticket Description: ");
		GridBagConstraints gbc_lblTicketDescription = new GridBagConstraints();
		gbc_lblTicketDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblTicketDescription.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblTicketDescription.gridx = 0;
		gbc_lblTicketDescription.gridy = 4;
		pnlTicketDetails.add(lblTicketDescription, gbc_lblTicketDescription);
		
		JScrollPane spTicketDescription = new JScrollPane();
		GridBagConstraints gbc_spTicketDescription = new GridBagConstraints();
		gbc_spTicketDescription.insets = new Insets(0, 0, 5, 0);
		gbc_spTicketDescription.fill = GridBagConstraints.BOTH;
		gbc_spTicketDescription.gridx = 1;
		gbc_spTicketDescription.gridy = 4;
		pnlTicketDetails.add(spTicketDescription, gbc_spTicketDescription);
		
		txtTicketDescription = new JTextArea();
		txtTicketDescription.setWrapStyleWord(true);
		txtTicketDescription.setLineWrap(true);
		txtTicketDescription.setBackground(new Color(230, 230, 250));
		txtTicketDescription.setEditable(false);
		spTicketDescription.setViewportView(txtTicketDescription);
		
		JLabel lblUpdateTicket = new JLabel("Reply: ");
		GridBagConstraints gbc_lblUpdateTicket = new GridBagConstraints();
		gbc_lblUpdateTicket.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblUpdateTicket.insets = new Insets(0, 0, 0, 5);
		gbc_lblUpdateTicket.gridx = 0;
		gbc_lblUpdateTicket.gridy = 5;
		pnlTicketDetails.add(lblUpdateTicket, gbc_lblUpdateTicket);
		
		JScrollPane spTicketReply = new JScrollPane();
		GridBagConstraints gbc_spTicketReply = new GridBagConstraints();
		gbc_spTicketReply.fill = GridBagConstraints.BOTH;
		gbc_spTicketReply.gridx = 1;
		gbc_spTicketReply.gridy = 5;
		pnlTicketDetails.add(spTicketReply, gbc_spTicketReply);
		
		final JTextArea txtTicketReply = new JTextArea();
		txtTicketReply.setWrapStyleWord(true);
		txtTicketReply.setLineWrap(true);
		spTicketReply.setViewportView(txtTicketReply);
		
		JPanel pnlReplyControls = new JPanel();
		pnlReplyControls.setBorder(new TitledBorder(null, "Controls", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlReplyControls.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) pnlReplyControls.getLayout();
		flowLayout.setHgap(30);
		GridBagConstraints gbc_pnlReplyControls = new GridBagConstraints();
		gbc_pnlReplyControls.insets = new Insets(0, 0, 0, 5);
		gbc_pnlReplyControls.fill = GridBagConstraints.BOTH;
		gbc_pnlReplyControls.gridx = 1;
		gbc_pnlReplyControls.gridy = 2;
		contentPane.add(pnlReplyControls, gbc_pnlReplyControls);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userTicketView.this.dispose();
			}
		});
		pnlReplyControls.add(btnCancel);
		
		JButton btnClear = new JButton("Clear");
		pnlReplyControls.add(btnClear);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Database.postReplyToAdmin(ticketID, txtTicketReply.getText(), Database.getTicketUser(ticketID));
				TicketInformation.txtDescription.setText(Database.getTicketDescription(ticketID));
				//userTicketView.this.dispose();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				txtTicketDescription.setText(Database.getTicketDescription(ticketID));
			}
		});
		
		JButton btnCloseticket = new JButton("Close Ticket");
		btnCloseticket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Database.closeTicket(ticketID);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null,
						"Ticket Closed Successfully.", "Closed",
						JOptionPane.DEFAULT_OPTION);
				staffUI.tblKnowledge.setModel(staffUI.tblKnowledge.getModel());
				staffUI.tblKnowledge.revalidate();
				staffUI.tblKnowledge.repaint();
				staffUI.tblStaffTicket.setModel(staffUI.tblStaffTicket.getModel());
				staffUI.tblStaffTicket.revalidate();
				staffUI.tblStaffTicket.repaint();
				userTicketView.this.dispose();
				
			}
		});
		pnlReplyControls.add(btnCloseticket);
		pnlReplyControls.add(btnSubmit);
		
		setTicketDetails();

	}
	
	

	private void setTicketDetails()
	{
		
		db.getTicketDetails(ticketID.trim());
		
		try
		{
			String adminid = Connections.rset.getString("adminid");
			String catID = Connections.rset.getString("CATEGORYID");
			txtTicketID.setText(Connections.rset.getString("TICKETID").trim());
			txtTicketTitle.setText(Connections.rset.getString("TICKETTITLE").trim());
			txtTicketDescription.setText(Connections.rset.getString("TICKETDESCRIPTION").trim());
			Connections.rset.close();
			
			txtTicketCategory.setText(db.getCurrentCatName(catID));
			Connections.rset.close();
			txtAdminName.setText(Database.getAdminName(adminid));
			Connections.rset.close();
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
