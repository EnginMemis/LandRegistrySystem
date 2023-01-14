package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.Format;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Models.LandRegistry;
import Models.Property;
import Models.User;
import Services.DbService;
import Services.UserService;
import Services.PropertyService;
import Services.LandRegistryService;

public class Devir extends JFrame {
	String[][] veri;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JTable table;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;
	private JPanel panel;
	private JButton btnNewButton_1;
	private JPanel panel_1;
	private JButton btnNewButton_2;
	UserService userService = new UserService(new DbService());
	PropertyService propertyService = new PropertyService(new DbService());
	LandRegistryService landRegistryService = new LandRegistryService(new DbService());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Devir frame = new Devir();
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
	public Devir() {
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 935, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(64, 110, 106, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel.setBounds(64, 74, 84, 25);
		contentPane.add(lblNewLabel);

		
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.BLACK);
		panel_2.setLayout(null);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(195, 110, 106, 36);
		contentPane.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 110, 524, 319);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);


		JButton btnNewButton = new JButton("Listele");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = 0;
					String[] baslik = { "ID", "Address", "Land Type", "Value", "Area"};
					Integer tmp = Integer.parseInt(textField.getText());
					User person = userService.get(tmp);
					ArrayList<LandRegistry> landList = landRegistryService.getPropertyID(person.getSsn());
					veri = new String[landList.size()][5];
					for(LandRegistry landRegistry : landList){
						Property property = propertyService.get(landRegistry.getPropertyId());
						veri[i] = new String[]{property.getId().toString(), property.getAddress(),
								property.getType(), String.format("%.3f", property.getValue()), String.format("%.2f", property.getArea())};
						i++;
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
					textField_1.setVisible(true);
					lblNewLabel_1.setVisible(true);
					scrollPane.setVisible(true);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı !!!");
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(2, 2, 102, 32);
		panel_2.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(64, 172, 84, 25);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(64, 199, 106, 36);
		contentPane.add(textField_1);
		textField_1.setVisible(false);
		
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.BLACK);
		panel.setBounds(64, 276, 172, 70);
		contentPane.add(panel);
		
		btnNewButton_1 = new JButton("Satıs Yap");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Integer tmp2 = Integer.parseInt(textField_1.getText());		
						int i = table.getSelectedRow();
						if(i != -1) {
							System.out.println(veri[table.getSelectedRow()][1]);
						}
						else {
							JOptionPane.showMessageDialog(null, "Tapu seçilmedi !!!");
						}
						
						
					
					} catch(Exception e1){
						e1.printStackTrace();
					}
		}});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(2, 2, 168, 66);
		panel.add(btnNewButton_1);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(64, 359, 172, 70);
		contentPane.add(panel_1);
		
		btnNewButton_2 = new JButton("Bagıs Yap");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.setBackground(Color.DARK_GRAY);
		btnNewButton_2.setBounds(2, 2, 168, 66);
		panel_1.add(btnNewButton_2);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("2.jpg"));
		lblNewLabel_2.setBounds(0, 0, 919, 673);
		contentPane.add(lblNewLabel_2);
	}
}
