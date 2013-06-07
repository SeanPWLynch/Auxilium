package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class staffHelp extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{

		try
		{
			staffHelp dialog = new staffHelp();
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
	public staffHelp()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(staffHelp.class.getResource("/Auxilium/Images/icon.png")));
		setTitle("Auxilium - Staff Help");

		setBounds(100, 100, 800, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JTextPane txtStaffGuide = new JTextPane();
				txtStaffGuide.setContentType("text/html");
				try
				{
					txtStaffGuide.setPage("http://friendlyblitzkrieg.com/portfolio/java/auxiliumhelp/staff/staffHelp.html");
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtStaffGuide.setEditable(false);
				scrollPane.setViewportView(txtStaffGuide);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						staffHelp.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
