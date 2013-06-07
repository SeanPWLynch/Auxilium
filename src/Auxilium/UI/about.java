package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class about extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextArea txtAuxiliumServiceDesk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{

		try
		{
			about dialog = new about();
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
	public about()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(about.class.getResource("/Auxilium/Images/icon.png")));
		setTitle("About Auxilium ");

		setBounds(100, 100, 480, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{155, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(about.class.getResource("/Auxilium/Images/logoSmall.png")));
			GridBagConstraints gbc_lblLogo = new GridBagConstraints();
			gbc_lblLogo.anchor = GridBagConstraints.WEST;
			gbc_lblLogo.gridwidth = 2;
			gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
			gbc_lblLogo.gridx = 0;
			gbc_lblLogo.gridy = 0;
			contentPanel.add(lblLogo, gbc_lblLogo);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 1;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				txtAuxiliumServiceDesk = new JTextArea();
				txtAuxiliumServiceDesk.setFont(new Font("Verdana", Font.PLAIN, 13));
				txtAuxiliumServiceDesk.setBorder(null);
				txtAuxiliumServiceDesk.setEditable(false);
				txtAuxiliumServiceDesk.setLineWrap(true);
				scrollPane.setViewportView(txtAuxiliumServiceDesk);
				txtAuxiliumServiceDesk.setText("Auxilium Service Desk System\r\n\r\nVersion 0.8.2\r\n\r\nThis product uses: \r\n\tJava 1.6\r\n\tOracle Database 11g\r\n\tJava Mail 1.4.5\r\n\tJFreechart 1.0.14\r\n\tJCalander 1.4\r\n\r\nProduct Developed By: \r\n\tSean Lynch\r\n\tGavin Kenna\r\n\tJames Blackbyrne");
				txtAuxiliumServiceDesk.setColumns(10);
			}
		}
		{
			JPanel pnlButtons = new JPanel();
			pnlButtons.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			pnlButtons.setBackground(Color.WHITE);
			getContentPane().add(pnlButtons, BorderLayout.SOUTH);
			GridBagLayout gbl_pnlButtons = new GridBagLayout();
			gbl_pnlButtons.columnWidths = new int[]{89, 0, 0, 0, 0, 0};
			gbl_pnlButtons.rowHeights = new int[]{23, 0};
			gbl_pnlButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_pnlButtons.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			pnlButtons.setLayout(gbl_pnlButtons);
			{
				JLabel lblAuxLogo = new JLabel("");
				lblAuxLogo.setIcon(new ImageIcon(about.class.getResource("/Auxilium/Images/auxlogo.png")));
				GridBagConstraints gbc_lblAuxLogo = new GridBagConstraints();
				gbc_lblAuxLogo.insets = new Insets(0, 0, 0, 5);
				gbc_lblAuxLogo.gridx = 0;
				gbc_lblAuxLogo.gridy = 0;
				pnlButtons.add(lblAuxLogo, gbc_lblAuxLogo);
			}
			{
				JLabel lblJavaLogo = new JLabel("");
				lblJavaLogo.setIcon(new ImageIcon(about.class.getResource("/Auxilium/Images/javalogo.png")));
				GridBagConstraints gbc_lblJavaLogo = new GridBagConstraints();
				gbc_lblJavaLogo.insets = new Insets(0, 0, 0, 5);
				gbc_lblJavaLogo.gridx = 1;
				gbc_lblJavaLogo.gridy = 0;
				pnlButtons.add(lblJavaLogo, gbc_lblJavaLogo);
			}
			{
				JLabel lblOrcLogo = new JLabel("");
				lblOrcLogo.setIcon(new ImageIcon(about.class.getResource("/Auxilium/Images/oraclelogo.png")));
				GridBagConstraints gbc_lblOrcLogo = new GridBagConstraints();
				gbc_lblOrcLogo.insets = new Insets(0, 0, 0, 5);
				gbc_lblOrcLogo.gridx = 2;
				gbc_lblOrcLogo.gridy = 0;
				pnlButtons.add(lblOrcLogo, gbc_lblOrcLogo);
			}
			{
				JButton btnAboutOK = new JButton("OK");
				btnAboutOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						about.this.dispose();
					}
				});
				GridBagConstraints gbc_btnAboutOK = new GridBagConstraints();
				gbc_btnAboutOK.insets = new Insets(0, 0, 0, 5);
				gbc_btnAboutOK.anchor = GridBagConstraints.EAST;
				gbc_btnAboutOK.gridx = 3;
				gbc_btnAboutOK.gridy = 0;
				pnlButtons.add(btnAboutOK, gbc_btnAboutOK);
			}
		}
	}

}
