package GUI;

import Services.DbService;
import Services.LandRegistryService;
import Services.PropertyService;
import Services.UserService;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Sil extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	UserService userService = new UserService(new DbService());
	LandRegistryService landRegistryService = new LandRegistryService(new DbService());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sil frame = new Sil("at");
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
	public Sil(String s) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(760, 405, 400, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(s);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel.setBounds(47, 63, 135, 36);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(192, 63, 135, 36);
		contentPane.add(textField);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(47, 132, 135, 36);
		contentPane.add(panel_2);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer ssn = Integer.parseInt(textField.getText());
				try {
					userService.delete(ssn);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Kullanıcı Silinemedi !!!");
				}
				try {
					landRegistryService.changeIsActive(ssn);
				} catch (SQLException ex) {

				}

			}
		});
		btnSil.setForeground(Color.WHITE);
		btnSil.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnSil.setFocusable(false);
		btnSil.setBackground(Color.DARK_GRAY);
		btnSil.setBounds(2, 2, 131, 32);
		panel_2.add(btnSil);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setForeground(Color.BLACK);
		panel_2_1.setBackground(Color.BLACK);
		panel_2_1.setBounds(192, 132, 135, 36);
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
	}
}
