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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bilgiler extends JFrame {
	String[][] veri;
	String[] baslik;
	private JPanel contentPane;
	private JTable table;
	JComboBox comboBox2;
	LandRegistryService landRegistryService = new LandRegistryService(new DbService());
	UserService userService = new UserService(new DbService());
	PropertyService propertyService = new PropertyService(new DbService());
	private JTextField textField;
	

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
		setBounds(490, 180, 940, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(46, 186, 106, 36);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(46, 233, 106, 36);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		panel_4.setBounds(46, 280, 106, 36);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(2, 2, 102, 32);
		panel_4.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		String[] dizi = {"Tapular", "Mulkler", "Kisiler"};
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(46, 139, 106, 36);
		contentPane.add(panel);
		panel.setLayout(null);
		JComboBox comboBox = new JComboBox(dizi);
		comboBox.setBounds(2, 2, 102, 32);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Kisiler")) {
					comboBox2.setVisible(true);
					panel_2.setBounds(46, 233, 106, 36);
				}
				else {
					comboBox2.setVisible(false);
					panel_2.setBounds(46, 186, 106, 36);
				}
			}
		});
		
		String[] dizi2 = {"Tümü","Kişiye Göre","Adrese Göre En Zengini Bul","Role Göre"};
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(46, 186, 106, 36);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		comboBox2 = new JComboBox(dizi2);
		comboBox2.setBounds(2, 2, 102, 32);
		panel_1.add(comboBox2);
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox2.getSelectedItem().equals("Tümü")) {
					panel_2.setBounds(46, 233, 106, 36);
					lblNewLabel_1.setVisible(false);
					textField.setVisible(false);
				}
				else {
					panel_2.setBounds(46, 327, 106, 36);
					lblNewLabel_1.setVisible(true);
					if(comboBox2.getSelectedItem().equals("Kişiye Göre")) {
						lblNewLabel_1.setText("Kullanıcı SSN :");
					}
					else if(comboBox2.getSelectedItem().equals("Adrese Göre En Zengini Bul")) {
						lblNewLabel_1.setText("Adres :");
					}
					else {
						lblNewLabel_1.setText("Rol :");
					}
					textField.setVisible(true);
					panel_4.setVisible(true);
				}
			}
		});
		comboBox2.setVisible(false);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(188, 75, 707, 388);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 4, 699, 380);
		panel_3.add(scrollPane);
		
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
					comboBox2.setVisible(true);
					if(comboBox2.getSelectedItem().toString().equals("Tümü")){
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
					} else if (comboBox2.getSelectedItem().toString().equals("Kişiye Göre")) {
						try{
							Integer ssn = Integer.parseInt(textField.getText());
							User u = userService.get(ssn);
							veri = new String[1][9];
							veri[0] = new String[]{u.getSsn().toString(), u.getFname(), u.getLname(),
									u.getGender(), u.getPhoneNumber(), u.getEmail(), u.getAddress(),
									String.valueOf(u.getWallet())};
						}catch (Exception e1){
							JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı !!!");
							veri = null;
						}
					} else if (comboBox2.getSelectedItem().toString().equals("Adrese Göre En Zengini Bul")) {
						String address = textField.getText();
						try {
							Integer ssn = userService.getRichestUser(address);
							JOptionPane.showMessageDialog(null, "Bu adresteki en zengin kişi "+ssn.toString()+" ssn'li kisidir.");
						} catch (SQLException ex) {
						}
					} else if (comboBox2.getSelectedItem().toString().equals("Role Göre")) {
						String role = textField.getText();
						if(role.equals("Employee")){
							try {
								userList = userService.listEmployee();
								veri = new String[userList.size()][9];
								i = 0;
								for(User u : userList){
									veri[i] = new String[]{u.getSsn().toString(), u.getFname(), u.getLname(),
											u.getGender(), u.getPhoneNumber(), u.getEmail(), u.getAddress(),
											String.valueOf(u.getWallet())};
									i++;
								}
							} catch (SQLException ex) {

							}
						} else if (role.equals("Customer")) {
							try {
								userList = userService.listCustomer();
								veri = new String[userList.size()][9];
								i = 0;
								for(User u : userList){
									veri[i] = new String[]{u.getSsn().toString(), u.getFname(), u.getLname(),
											u.getGender(), u.getPhoneNumber(), u.getEmail(), u.getAddress(),
											String.valueOf(u.getWallet())};
									i++;
								}
							} catch (SQLException ex) {

							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Yanlış Rol Girildi !!!");
						}
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
		

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setForeground(Color.BLACK);
		panel_3_1.setBackground(Color.BLACK);
		panel_3_1.setBounds(46, 370, 122, 36);
		contentPane.add(panel_3_1);
		
		JButton btnNewButton_3 = new JButton("Geri Dön");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu main = new MainMenu();
				main.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setBackground(Color.DARK_GRAY);
		btnNewButton_3.setBounds(2, 2, 118, 32);
		panel_3_1.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("2.jpg"));
		lblNewLabel.setBounds(0, 0, 924, 681);
		contentPane.add(lblNewLabel);
		
	}
}
