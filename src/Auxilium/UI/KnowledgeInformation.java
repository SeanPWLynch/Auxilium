package Auxilium.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Auxilium.BackEnd.Database;
import Auxilium.ComboBoxModels.KnowledgeComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class KnowledgeInformation extends JFrame
{
	
	private JPanel contentPane;
	private JTextField txtTitle;
	public JTextArea txtSolution;
	public static String knowledgeID;
	JScrollPane scrollPane = new JScrollPane();
	
	/**
	 * Launch the application.
	 */
	
	
	/**
	 * Create the frame.
	 */
	public KnowledgeInformation(String knowledgeID)
	{
		this.knowledgeID = knowledgeID;
		setTitle("Knowledge Base");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 615);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{8, 0, -18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(KnowledgeInformation.class.getResource("/Auxilium/Images/logoSmall.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.anchor = GridBagConstraints.WEST;
		gbc_lblLogo.gridwidth = 12;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogo.gridx = 3;
		gbc_lblLogo.gridy = 0;
		contentPane.add(lblLogo, gbc_lblLogo);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Knowledge Base Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 16;
		gbc_panel.gridheight = 15;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 66, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{19, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		final JComboBox cmbKnowledgeBase = new JComboBox();
		cmbKnowledgeBase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				KnowledgeInformation.knowledgeID = cmbKnowledgeBase.getSelectedItem().toString().split(" ").clone()[0];
				loadInfo();
			}
		});
		KnowledgeComboBoxModel kcbm = new KnowledgeComboBoxModel();
		cmbKnowledgeBase.setModel(kcbm);
		GridBagConstraints gbc_cmbKnowledgeBase = new GridBagConstraints();
		gbc_cmbKnowledgeBase.gridwidth = 10;
		gbc_cmbKnowledgeBase.insets = new Insets(0, 0, 5, 5);
		gbc_cmbKnowledgeBase.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbKnowledgeBase.gridx = 6;
		gbc_cmbKnowledgeBase.gridy = 1;
		panel.add(cmbKnowledgeBase, gbc_cmbKnowledgeBase);
		
		JLabel lblTitle = new JLabel("Title :");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 3;
		panel.add(lblTitle, gbc_lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.gridwidth = 12;
		gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.gridx = 4;
		gbc_txtTitle.gridy = 3;
		panel.add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);
		
		JLabel lblSolution = new JLabel("Solution :");
		GridBagConstraints gbc_lblSolution = new GridBagConstraints();
		gbc_lblSolution.gridwidth = 3;
		gbc_lblSolution.anchor = GridBagConstraints.WEST;
		gbc_lblSolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblSolution.gridx = 1;
		gbc_lblSolution.gridy = 4;
		panel.add(lblSolution, gbc_lblSolution);
		
		JPanel pnlSolution = new JPanel();
		GridBagConstraints gbc_pnlSolution = new GridBagConstraints();
		gbc_pnlSolution.gridheight = 8;
		gbc_pnlSolution.gridwidth = 17;
		gbc_pnlSolution.insets = new Insets(0, 0, 0, 5);
		gbc_pnlSolution.fill = GridBagConstraints.BOTH;
		gbc_pnlSolution.gridx = 1;
		gbc_pnlSolution.gridy = 5;
		panel.add(pnlSolution, gbc_pnlSolution);
		GridBagLayout gbl_pnlSolution = new GridBagLayout();
		gbl_pnlSolution.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_pnlSolution.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pnlSolution.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlSolution.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnlSolution.setLayout(gbl_pnlSolution);
		
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlSolution.add(scrollPane, gbc_scrollPane);
		
		txtSolution = new JTextArea();
		txtSolution.setLineWrap(true);
		txtSolution.setWrapStyleWord(true);
		txtSolution.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtSolution.setEditable(false);
		
		scrollPane.setViewportView(txtSolution);
		//panel.add(txtSolution, gbc_txtSolution);
		
		
		
		loadInfo();
		
		JButton btnClose = new JButton("Close");
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 5);
		gbc_btnClose.gridx = 10;
		gbc_btnClose.gridy = 16;
		contentPane.add(btnClose, gbc_btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KnowledgeInformation.this.dispose();
			}
		});
	}
	
	public void loadInfo()
	{
		txtTitle.setText(Database.getKnowledgeTitle(knowledgeID));
		txtSolution.setText(Database.getKnowledgeDescription(knowledgeID));
		txtSolution.setCaretPosition(0);
	}
}
