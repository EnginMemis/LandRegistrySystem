package GUI;

import Models.User;
import Services.DbService;
import Services.UserService;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class KullanıcıEkle extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JComboBox comboBox;

	UserService userService = new UserService(new DbService());
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullanıcıEkle frame = new KullanıcıEkle();
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
	public KullanıcıEkle() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(760, 258, 400, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEklenecekMiktar = new JLabel("İsim :");
		lblEklenecekMiktar.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar.setBounds(47, 55, 135, 36);
		contentPane.add(lblEklenecekMiktar);
		
		JLabel lblEklenecekMiktar_1 = new JLabel("Soyisim :");
		lblEklenecekMiktar_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_1.setBounds(47, 102, 135, 36);
		contentPane.add(lblEklenecekMiktar_1);
		
		JLabel lblEklenecekMiktar_2 = new JLabel("Birhdate :");
		lblEklenecekMiktar_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_2.setBounds(47, 149, 135, 36);
		contentPane.add(lblEklenecekMiktar_2);
		
		JLabel lblEklenecekMiktar_3 = new JLabel("Gender :");
		lblEklenecekMiktar_3.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_3.setBounds(47, 196, 135, 36);
		contentPane.add(lblEklenecekMiktar_3);
		
		JLabel lblEklenecekMiktar_4 = new JLabel("Phone :");
		lblEklenecekMiktar_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_4.setBounds(47, 243, 135, 36);
		contentPane.add(lblEklenecekMiktar_4);
		
		JLabel lblEklenecekMiktar_5 = new JLabel("E-mail :");
		lblEklenecekMiktar_5.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_5.setBounds(47, 290, 135, 36);
		contentPane.add(lblEklenecekMiktar_5);
		
		JLabel lblEklenecekMiktar_6 = new JLabel("Address :");
		lblEklenecekMiktar_6.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_6.setBounds(47, 337, 135, 36);
		contentPane.add(lblEklenecekMiktar_6);
		
		JLabel lblEklenecekMiktar_7 = new JLabel("Role :");
		lblEklenecekMiktar_7.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_7.setBounds(47, 384, 135, 36);
		contentPane.add(lblEklenecekMiktar_7);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(47, 445, 135, 36);
		contentPane.add(panel_2);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = textField.getText();
				String lname = textField_1.getText();
				try {
					Date date = Date.valueOf(textField_2.getText());
					String gender = textField_3.getText();
					String phone = textField_4.getText();
					String email = textField_5.getText();
					String address = textField_6.getText();
					String role = comboBox.getSelectedItem().toString();

					User user = new User(fname, lname, date, gender, phone, email, address,0, role);
					userService.create(user);
				}catch (Exception e1){
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"(yyyy-mm-dd) Şeklinde Giriniz!!!");
				}

			}
		});
		btnEkle.setForeground(Color.WHITE);
		btnEkle.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnEkle.setFocusable(false);
		btnEkle.setBackground(Color.DARK_GRAY);
		btnEkle.setBounds(2, 2, 131, 32);
		panel_2.add(btnEkle);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setBackground(Color.BLACK);
		panel_2_1.setBounds(192, 445, 135, 36);
		contentPane.add(panel_2_1);
		
		JButton btnGeriDn = new JButton("Geri dön");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu main = new MainMenu();
				main.setVisible(true);
				dispose();
			}
		});
		btnGeriDn.setForeground(Color.WHITE);
		btnGeriDn.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnGeriDn.setFocusable(false);
		btnGeriDn.setBackground(Color.DARK_GRAY);
		btnGeriDn.setBounds(2, 2, 131, 32);
		panel_2_1.add(btnGeriDn);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(192, 55, 135, 36);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 102, 135, 36);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(192, 149, 135, 36);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(192, 196, 135, 36);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(192, 243, 135, 36);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(192, 290, 135, 36);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(192, 337, 135, 36);
		contentPane.add(textField_6);
		
		String[] dizi = {"Customer","Employee"};
		comboBox = new JComboBox(dizi);
		comboBox.setBounds(192, 384, 135, 36);
		contentPane.add(comboBox);
	}
}
