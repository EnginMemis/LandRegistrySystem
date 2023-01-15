package GUI;

import Models.LandRegistry;
import Models.Property;
import Models.User;
import Services.DbService;
import Services.LandRegistryService;
import Services.PropertyService;
import Services.UserService;

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
import java.sql.SQLException;
import java.util.ArrayList;

public class Bilgiler extends JFrame {
	String[][] veri;
	String[] baslik;
	private JPanel contentPane;
	private JTable table;
	LandRegistryService landRegistryService = new LandRegistryService(new DbService());
	UserService userService = new UserService(new DbService());
	PropertyService propertyService = new PropertyService(new DbService());
	

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
				 int i = 0;
				if(comboBox.getSelectedItem().toString().equals("Tapular")) {
					ArrayList<LandRegistry> landList = new ArrayList<>();
					baslik = new String[]{ "ID", "Property_ID", "Buyer SSN", "Seller SSN", "Price", "Issued at"};
					try {
						landList = landRegistryService.getAll();
						veri = new String[landList.size()][6];
						for(LandRegistry land : landList){
							if(land.isActive()){
								veri[i] = new String[] {String.format("%d",land.getId()),
										String.format("%d",land.getPropertyId()), land.getBuyerSsn().toString(),
										land.getSellerSsn().toString(), land.getPrice().toString(), land.getIssuedAt().toString()};
								i++;
							}
						}
					} catch (SQLException ex) {

					}
				}
				else if(comboBox.getSelectedItem().toString().equals("Mulkler")) {
					ArrayList<Property> propertyList = new ArrayList<>();
					baslik = new String[]{ "ID", "Address", "Land Type", "Value", "Area"};
					i = 0;
					try {
						propertyList = propertyService.getAll();
						veri = new String[propertyList.size()][5];
						for(Property p : propertyList){
							veri[i] = new String[] {p.getId().toString(), p.getAddress(),
													p.getType(), p.getValue().toString(), String.valueOf(p.getArea())};
							i++;
						}
					} catch (SQLException ex) {

					}
				}
				else {
					ArrayList<User> userList = new ArrayList<>();
					baslik = new String[]{ "SSN", "Name", "Surname", "Gender","Phone Number","E-mail", "Address", "Wallet"};
					i = 0;
					try {
						userList = userService.getAll();
						veri = new String[userList.size()][9];
						for(User u : userList){
							veri[i] = new String[]{u.getSsn().toString(), u.getFname(), u.getLname(),
													u.getGender(), u.getPhoneNumber(), u.getEmail(), u.getAddress(),
									String.valueOf(u.getWallet())};
							i++;
						}
					} catch (SQLException ex) {

					}
				}
				DefaultTableModel tablemodel = new DefaultTableModel(veri,baslik) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
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
