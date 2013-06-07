package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import Auxilium.BackEnd.Connections;
import Auxilium.BackEnd.Database;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class loginUI extends JFrame
{
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwPassword;
	static int attempts = 0;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args)
	{
		Connections.killRset();
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					loginUI frame = new loginUI();
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
	 */
	public loginUI()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(
		        loginUI.class.getResource("/Auxilium/Images/icon.png")));
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Auxilium Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 270);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlLogo = new JPanel();
		pnlLogo.setBackground(Color.WHITE);
		contentPane.add(pnlLogo, BorderLayout.NORTH);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(loginUI.class
		        .getResource("/Auxilium/Images/logoSmall.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		pnlLogo.add(lblLogo);
		
		JPanel pnlLogin = new JPanel();
		pnlLogin.setBackground(Color.WHITE);
		pnlLogin.setBorder(new TitledBorder(null, "Log In",
		        TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlLogin, BorderLayout.CENTER);
		GridBagLayout gbl_pnlLogin = new GridBagLayout();
		gbl_pnlLogin.columnWidths = new int[]
		{ 10, 0, 250, 45, 10, 0 };
		gbl_pnlLogin.rowHeights = new int[]
		{ 0, 30, 30, 35, 0, 0 };
		gbl_pnlLogin.columnWeights = new double[]
		{ 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnlLogin.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlLogin.setLayout(gbl_pnlLogin);
		
		JLabel lblUserName = new JLabel("User ID or Email: ");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.fill = GridBagConstraints.VERTICAL;
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 1;
		gbc_lblUserName.gridy = 1;
		pnlLogin.add(lblUserName, gbc_lblUserName);
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.gridwidth = 2;
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.BOTH;
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 1;
		pnlLogin.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassWord = new JLabel("Password: ");
		GridBagConstraints gbc_lblPassWord = new GridBagConstraints();
		gbc_lblPassWord.fill = GridBagConstraints.VERTICAL;
		gbc_lblPassWord.anchor = GridBagConstraints.EAST;
		gbc_lblPassWord.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassWord.gridx = 1;
		gbc_lblPassWord.gridy = 2;
		pnlLogin.add(lblPassWord, gbc_lblPassWord);
		
		pwPassword = new JPasswordField();
		GridBagConstraints gbc_pwPassword = new GridBagConstraints();
		gbc_pwPassword.gridwidth = 2;
		gbc_pwPassword.insets = new Insets(0, 0, 5, 5);
		gbc_pwPassword.fill = GridBagConstraints.BOTH;
		gbc_pwPassword.gridx = 2;
		gbc_pwPassword.gridy = 2;
		pnlLogin.add(pwPassword, gbc_pwPassword);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Connections.killConnections();
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.VERTICAL;
		gbc_btnExit.anchor = GridBagConstraints.EAST;
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 2;
		gbc_btnExit.gridy = 3;
		pnlLogin.add(btnExit, gbc_btnExit);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String pass = "";
				char[] passarray = pwPassword.getPassword();
				String username = txtUsername.getText();
				
				for (int i = 0; i < passarray.length; i++)
				{
					pass = pass + passarray[i];
					pass.trim();
				}
				if (username.contains("@"))
				{
					try
					{
						
						String check = ("select a.adminid from admin a where a.email ="
						        + "'" + username.toString()
						        + "' union select s.staffid from"
						        + " staff s where s.email = '" + username.toString() + "'");
						System.out.println(check);
						Connections.pstmt = Connections.conn.prepareStatement(check);
						
						Connections.rset = Connections.pstmt.executeQuery();
						if(Connections.rset.next() == true)
						{
							username = Connections.rset.getString(1).trim();
						} 
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				
				login(username, pass, attempts);
				
			}
			
			private void login(String username, String pass, int attempts)
			{
				String userType = null;
				userType = Database.checkLogin(username, pass);
				if (userType.equals("ADMIN"))
				{
					if (loginUI.attempts < 3)
					{
						try
						{
							if (Auxilium.BackEnd.Connections.rset.next() == true)
							{
								Connections.killRset();
								System.out.println("Welcome back commander");
								// Connections.killRset();
								adminUI app = new adminUI(username
								        .trim());
								app.setVisible(true);
								loginUI.this.dispose();
								// Connections.rset.close();
								// Connections.rset.getStatement().close();
								// Connections.pstmt.close();
								Connections.killRset();
							} else
							{
								Connections.killRset();
								loginUI.attempts++;
								JOptionPane.showMessageDialog(null,
								        "The username and/or password is incorrect, (You have "
								                + (3 - attempts)
								                + " attempts remaining )",
								        "Incorrent login",
								        JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e1)
						{
							e1.printStackTrace();
						}
					} else
					{
						System.out.println("System Locked");
						JOptionPane
						        .showMessageDialog(
						                null,
						                "Too Many Bad Logins, The System Has Been Disabled",
						                "System Disabled",
						                JOptionPane.ERROR_MESSAGE);
						txtUsername.setEditable(false);
						pwPassword.setEditable(false);
					}
				} else if (userType.equals("STAFF"))
				{
					if (attempts < 3)
					{
						try
						{
							if (Auxilium.BackEnd.Connections.rset.next() == true)
							{
								Connections.killRset();
								System.out.println("Welcome back user");
								// Connections.killConnections();
								staffUI app = new staffUI(username
								        .trim());
								app.setVisible(true);
								setVisible(false);
							} else
							{
								Connections.killRset();
								loginUI.attempts++;
								JOptionPane.showMessageDialog(null,
								        "The username and/or password is incorrect, (You have "
								                + (3 - attempts)
								                + " attempts remaining )",
								        "Incorrent login",
								        JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e1)
						{
							e1.printStackTrace();
						}
					} else
					{
						System.out.println("System Locked");
						// Connections.killConnections();
						JOptionPane
						        .showMessageDialog(
						                null,
						                "Too Many Bad Logins, The System Has Been Disabled",
						                "System Disabled",
						                JOptionPane.ERROR_MESSAGE);
						txtUsername.setEditable(false);
						pwPassword.setEditable(false);
					}
				} else if (userType.equals("BADLOGIN"))
				{
					Connections.killRset();
					JOptionPane
					        .showMessageDialog(
					                null,
					                "Please log in with your admin or user id, Axxx or Uxxx",
					                "Bad Login", JOptionPane.ERROR_MESSAGE);
				}
				// Connections.killConnections();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
		
		);
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.EAST;
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.fill = GridBagConstraints.VERTICAL;
		gbc_btnLogin.gridx = 3;
		gbc_btnLogin.gridy = 3;
		pnlLogin.add(btnLogin, gbc_btnLogin);
	}
	
}
