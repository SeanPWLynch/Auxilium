package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;

import Auxilium.BackEnd.Database;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminReply extends JDialog
{
	
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static String ticketID;
	public static String fromAdmin;
	
	
	/**
	 * Create the dialog.
	 */
	public AdminReply(final String ticketID, String adminId)
	{
		this.ticketID = ticketID;
		fromAdmin = adminId;
		setTitle("Post Reply");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{164, 106, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		final JEditorPane txtReply = new JEditorPane();
		contentPanel.setLayout(gbl_contentPanel);
		{
			
			GridBagConstraints gbc_txtReply = new GridBagConstraints();
			gbc_txtReply.gridwidth = 2;
			gbc_txtReply.fill = GridBagConstraints.BOTH;
			gbc_txtReply.gridx = 0;
			gbc_txtReply.gridy = 0;
			contentPanel.add(txtReply, gbc_txtReply);
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
						Database.postReplyToTicket(ticketID, txtReply.getText(), fromAdmin);
						TicketInformation.txtDescription.setText(Database.getTicketDescription(ticketID));
						AdminReply.this.dispose();
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AdminReply.this.dispose();
					}
					
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}
