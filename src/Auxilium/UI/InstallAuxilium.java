package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.InstallDatabase;
import Auxilium.BackEnd.dropAllTables;

public class InstallAuxilium extends JDialog
{
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtSqlUser;
	private JTextField txtUrl;
	private JTextField txtAdminUser;
	private JPasswordField txtSqlPass;
	private JPasswordField txtAdminPass;
	private JTextField txtFullName;
	private JTextField txtEmail;
	private JTextField txtOffice;
	private JTextField txtMobile;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			InstallAuxilium dialog = new InstallAuxilium();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public InstallAuxilium()
	{
		setTitle("Install Auxilium");
		setBounds(100, 100, 415, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 153, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("SQL Username : ");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			txtSqlUser = new JTextField();
			GridBagConstraints gbc_txtSqlUser = new GridBagConstraints();
			gbc_txtSqlUser.gridwidth = 2;
			gbc_txtSqlUser.insets = new Insets(0, 0, 5, 0);
			gbc_txtSqlUser.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSqlUser.gridx = 2;
			gbc_txtSqlUser.gridy = 1;
			contentPanel.add(txtSqlUser, gbc_txtSqlUser);
			txtSqlUser.setColumns(10);
		}
		{
			JLabel lblSqlPassword = new JLabel("SQL Password : ");
			lblSqlPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblSqlPassword = new GridBagConstraints();
			gbc_lblSqlPassword.anchor = GridBagConstraints.WEST;
			gbc_lblSqlPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblSqlPassword.gridx = 1;
			gbc_lblSqlPassword.gridy = 2;
			contentPanel.add(lblSqlPassword, gbc_lblSqlPassword);
		}
		{
			txtSqlPass = new JPasswordField();
			GridBagConstraints gbc_txtSqlPass = new GridBagConstraints();
			gbc_txtSqlPass.gridwidth = 2;
			gbc_txtSqlPass.insets = new Insets(0, 0, 5, 0);
			gbc_txtSqlPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtSqlPass.gridx = 2;
			gbc_txtSqlPass.gridy = 2;
			contentPanel.add(txtSqlPass, gbc_txtSqlPass);
		}
		{
			JLabel lblSqlUrl = new JLabel("SQL URL: ");
			lblSqlUrl.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblSqlUrl = new GridBagConstraints();
			gbc_lblSqlUrl.anchor = GridBagConstraints.WEST;
			gbc_lblSqlUrl.insets = new Insets(0, 0, 5, 5);
			gbc_lblSqlUrl.gridx = 1;
			gbc_lblSqlUrl.gridy = 3;
			contentPanel.add(lblSqlUrl, gbc_lblSqlUrl);
		}
		{
			txtUrl = new JTextField();
			txtUrl.setText("jdbc:oracle:thin:@//");
			txtUrl.setFont(new Font("Tahoma", Font.PLAIN, 9));
			txtUrl.setColumns(10);
			GridBagConstraints gbc_txtUrl = new GridBagConstraints();
			gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUrl.gridwidth = 2;
			gbc_txtUrl.insets = new Insets(0, 0, 5, 0);
			gbc_txtUrl.gridx = 2;
			gbc_txtUrl.gridy = 3;
			contentPanel.add(txtUrl, gbc_txtUrl);
		}
		{
			JLabel lblDefaultAdminUsername = new JLabel("Default Admin Username :");
			lblDefaultAdminUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblDefaultAdminUsername = new GridBagConstraints();
			gbc_lblDefaultAdminUsername.anchor = GridBagConstraints.WEST;
			gbc_lblDefaultAdminUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblDefaultAdminUsername.gridx = 1;
			gbc_lblDefaultAdminUsername.gridy = 5;
			contentPanel.add(lblDefaultAdminUsername, gbc_lblDefaultAdminUsername);
		}
		{
			txtAdminUser = new JTextField();
			txtAdminUser.setColumns(10);
			GridBagConstraints gbc_txtAdminUser = new GridBagConstraints();
			gbc_txtAdminUser.insets = new Insets(0, 0, 5, 0);
			gbc_txtAdminUser.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAdminUser.gridx = 3;
			gbc_txtAdminUser.gridy = 5;
			contentPanel.add(txtAdminUser, gbc_txtAdminUser);
		}
		{
			JLabel lblAdminPassword = new JLabel("Admin Password :");
			lblAdminPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblAdminPassword = new GridBagConstraints();
			gbc_lblAdminPassword.anchor = GridBagConstraints.WEST;
			gbc_lblAdminPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblAdminPassword.gridx = 1;
			gbc_lblAdminPassword.gridy = 6;
			contentPanel.add(lblAdminPassword, gbc_lblAdminPassword);
		}
		{
			txtAdminPass = new JPasswordField();
			GridBagConstraints gbc_txtAdminPass = new GridBagConstraints();
			gbc_txtAdminPass.insets = new Insets(0, 0, 5, 0);
			gbc_txtAdminPass.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAdminPass.gridx = 3;
			gbc_txtAdminPass.gridy = 6;
			contentPanel.add(txtAdminPass, gbc_txtAdminPass);
		}
		{
			JLabel lblFullName = new JLabel("Full Name :");
			lblFullName.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblFullName = new GridBagConstraints();
			gbc_lblFullName.anchor = GridBagConstraints.WEST;
			gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
			gbc_lblFullName.gridx = 1;
			gbc_lblFullName.gridy = 7;
			contentPanel.add(lblFullName, gbc_lblFullName);
		}
		{
			txtFullName = new JTextField();
			txtFullName.setColumns(10);
			GridBagConstraints gbc_txtFullName = new GridBagConstraints();
			gbc_txtFullName.insets = new Insets(0, 0, 5, 0);
			gbc_txtFullName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtFullName.gridx = 3;
			gbc_txtFullName.gridy = 7;
			contentPanel.add(txtFullName, gbc_txtFullName);
		}
		{
			JLabel lblEmail = new JLabel("Email :");
			lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.WEST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 1;
			gbc_lblEmail.gridy = 8;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridx = 3;
			gbc_txtEmail.gridy = 8;
			contentPanel.add(txtEmail, gbc_txtEmail);
		}
		{
			JLabel lblOfficeNumber = new JLabel("Office Number :");
			lblOfficeNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblOfficeNumber = new GridBagConstraints();
			gbc_lblOfficeNumber.anchor = GridBagConstraints.WEST;
			gbc_lblOfficeNumber.insets = new Insets(0, 0, 5, 5);
			gbc_lblOfficeNumber.gridx = 1;
			gbc_lblOfficeNumber.gridy = 9;
			contentPanel.add(lblOfficeNumber, gbc_lblOfficeNumber);
		}
		{
			txtOffice = new JTextField();
			txtOffice.setColumns(10);
			GridBagConstraints gbc_txtOffice = new GridBagConstraints();
			gbc_txtOffice.insets = new Insets(0, 0, 5, 0);
			gbc_txtOffice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtOffice.gridx = 3;
			gbc_txtOffice.gridy = 9;
			contentPanel.add(txtOffice, gbc_txtOffice);
		}
		{
			JLabel lblMobile = new JLabel("Mobile :");
			lblMobile.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblMobile = new GridBagConstraints();
			gbc_lblMobile.anchor = GridBagConstraints.WEST;
			gbc_lblMobile.insets = new Insets(0, 0, 5, 5);
			gbc_lblMobile.gridx = 1;
			gbc_lblMobile.gridy = 10;
			contentPanel.add(lblMobile, gbc_lblMobile);
		}
		{
			txtMobile = new JTextField();
			txtMobile.setColumns(10);
			GridBagConstraints gbc_txtMobile = new GridBagConstraints();
			gbc_txtMobile.insets = new Insets(0, 0, 5, 0);
			gbc_txtMobile.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMobile.gridx = 3;
			gbc_txtMobile.gridy = 10;
			contentPanel.add(txtMobile, gbc_txtMobile);
		}
		final JCheckBox chkAdmins = new JCheckBox("Generate Random Admins");
		{
			
			GridBagConstraints gbc_chkAdmins = new GridBagConstraints();
			gbc_chkAdmins.anchor = GridBagConstraints.WEST;
			gbc_chkAdmins.insets = new Insets(0, 0, 5, 5);
			gbc_chkAdmins.gridx = 1;
			gbc_chkAdmins.gridy = 11;
			contentPanel.add(chkAdmins, gbc_chkAdmins);
		}
		final JSpinner numAdmins = new JSpinner();
		{
			
			numAdmins.setModel(new SpinnerNumberModel(20, 0, 500, 1));
			numAdmins.setToolTipText("How many admins?");
			GridBagConstraints gbc_numAdmins = new GridBagConstraints();
			gbc_numAdmins.insets = new Insets(0, 0, 5, 0);
			gbc_numAdmins.gridx = 3;
			gbc_numAdmins.gridy = 11;
			contentPanel.add(numAdmins, gbc_numAdmins);
		}
		final JCheckBox chkStaff = new JCheckBox("Generate Random Staff Members");
		{
			
			GridBagConstraints gbc_chkStaff = new GridBagConstraints();
			gbc_chkStaff.anchor = GridBagConstraints.WEST;
			gbc_chkStaff.insets = new Insets(0, 0, 0, 5);
			gbc_chkStaff.gridx = 1;
			gbc_chkStaff.gridy = 12;
			contentPanel.add(chkStaff, gbc_chkStaff);
		}
		final JSpinner numStaff = new JSpinner();
		{
			
			numStaff.setModel(new SpinnerNumberModel(20, 0, 500, 1));
			numStaff.setToolTipText("How many staff members");
			GridBagConstraints gbc_numStaff = new GridBagConstraints();
			gbc_numStaff.gridx = 3;
			gbc_numStaff.gridy = 12;
			contentPanel.add(numStaff, gbc_numStaff);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						Auxilium.BackEnd.SqlInformation sql = new Auxilium.BackEnd.SqlInformation(txtSqlUser.getText(), txtSqlPass.getPassword(), txtUrl.getText());
						String[] name = txtFullName.getText().split(" ");
						sql.writeData();
						Connections.createConnection();
						dropAllTables.main(null);
						Auxilium.BackEnd.InstallDatabase.createTicketTable();
						
						Auxilium.BackEnd.InstallDatabase.createDepartmentTable();

						Auxilium.BackEnd.InstallDatabase.fillDepartmentTable();

						Auxilium.BackEnd.InstallDatabase.createCategoryTable();

						Auxilium.BackEnd.InstallDatabase.createAdminTable();
				
						Auxilium.BackEnd.InstallDatabase.createStaffTable();

						Auxilium.BackEnd.InstallDatabase.createStatusTable();

						Auxilium.BackEnd.InstallDatabase.fillStatusTable();

						Auxilium.BackEnd.InstallDatabase.createLogTable();
				
						Auxilium.BackEnd.InstallDatabase.fillCategoryTable();
						
						Auxilium.BackEnd.InstallDatabase.createKnowledgeBaseTable();
						
						Auxilium.BackEnd.Database.addAdmin(txtAdminUser.getText().trim(), txtAdminPass.getText(), name[0] , name[1], txtEmail.getText(), Integer.parseInt(txtOffice.getText()),  Integer.parseInt(txtMobile.getText()),  true, true, true, true, true);
						System.out.println(Integer.parseInt(numAdmins.getValue().toString()));
						if(chkAdmins.isSelected())
						{
								InstallDatabase.fillAdminTable(Integer.parseInt(numAdmins.getValue().toString()));
						
						}
						if(chkStaff.isSelected())
						{
								InstallDatabase.fillStaffTable(Integer.parseInt(numStaff.getValue().toString()));
						
						}
						JOptionPane
						.showMessageDialog(null,
								"Finished Installation! ",
								"Success",
								JOptionPane.DEFAULT_OPTION);
						loginUI.main(null);
						InstallAuxilium.this.dispose();
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}
