package GUI;

import Services.DbService;
import Services.UserService;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class BakiyeEkle extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	UserService userService = new UserService(new DbService());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BakiyeEkle frame = new BakiyeEkle();
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
	public BakiyeEkle() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(760, 381, 400, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKullancSsn = new JLabel("Kullanıcı SSN :");
		lblKullancSsn.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblKullancSsn.setBounds(47, 62, 135, 36);
		contentPane.add(lblKullancSsn);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(192, 62, 135, 36);
		contentPane.add(textField);
		
		JLabel lblEklenecekMiktar = new JLabel("Miktar :");
		lblEklenecekMiktar.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar.setBounds(47, 117, 135, 36);
		contentPane.add(lblEklenecekMiktar);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 117, 135, 36);
		contentPane.add(textField_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(47, 179, 135, 36);
		contentPane.add(panel_2);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer ssn = Integer.parseInt(textField.getText());
				Integer balance = Integer.parseInt(textField_1.getText());

				try {
					userService.updateBalance(ssn, balance);
					MainMenu main = new MainMenu();
					main.setVisible(true);
					dispose();
				} catch (SQLException ex) {
					ex.printStackTrace();
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
		panel_2_1.setBounds(192, 179, 135, 36);
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
