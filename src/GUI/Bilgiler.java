package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bilgiler extends JFrame {
	String[][] veri;
	String[] baslik;
	private JPanel contentPane;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bilgiler frame = new Bilgiler();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bilgiler() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 170, 940, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] dizi = {"Tapular", "Mulkler", "Kisiler"};
		JComboBox comboBox = new JComboBox(dizi);
		comboBox.setBounds(46, 139, 106, 36);
		contentPane.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(46, 241, 106, 36);
		contentPane.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(188, 75, 703, 384);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Listele");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.out.println(comboBox.getSelectedItem().toString());
				 int size;
				if(comboBox.getSelectedItem().toString().equals("Tapular")) {
					String[] baslik = { "ID", "Property_ID", "Buyer SSN", "Seller SSN", "Price", "Issued at"};
					size = 6;
					
				}
				else if(comboBox.getSelectedItem().toString().equals("Mulkler")) {
					String[] baslik = { "ID", "Address", "Land Type", "Value", "Area"};
					size = 6;
					veri[1] = new String[] {"a","b","c","d","e"};
				}
				else {
					String[] baslik = { "SSN", "Name", "Surname", "Birth Date", "Gender","Phone Number","E-mail", "Address", "Wallet"};
					size = 6;
				}
				DefaultTableModel tablemodel = new DefaultTableModel(veri,baslik) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				veri = new String[size][];
				table = new JTable(tablemodel);
				table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);
				scrollPane.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(2, 2, 102, 32);
		panel_2.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("2.jpg"));
		lblNewLabel.setBounds(0, 0, 924, 681);
		contentPane.add(lblNewLabel);
		
		
	}
}
