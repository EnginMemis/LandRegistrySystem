package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MülkEkle extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MülkEkle frame = new MülkEkle();
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
	public MülkEkle() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 400, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEklenecekMiktar = new JLabel("Address :");
		lblEklenecekMiktar.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar.setBounds(47, 55, 135, 36);
		contentPane.add(lblEklenecekMiktar);
		
		JLabel lblEklenecekMiktar_1 = new JLabel("Type :");
		lblEklenecekMiktar_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_1.setBounds(47, 102, 135, 36);
		contentPane.add(lblEklenecekMiktar_1);
		
		JLabel lblEklenecekMiktar_2 = new JLabel("Value :");
		lblEklenecekMiktar_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_2.setBounds(47, 149, 135, 36);
		contentPane.add(lblEklenecekMiktar_2);
		
		JLabel lblEklenecekMiktar_3 = new JLabel("Area :");
		lblEklenecekMiktar_3.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_3.setBounds(47, 196, 135, 36);
		contentPane.add(lblEklenecekMiktar_3);
		
		JLabel lblEklenecekMiktar_4 = new JLabel("Title :");
		lblEklenecekMiktar_4.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_4.setBounds(47, 243, 135, 36);
		contentPane.add(lblEklenecekMiktar_4);
		
		JLabel lblEklenecekMiktar_5 = new JLabel("F-Value :");
		lblEklenecekMiktar_5.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEklenecekMiktar_5.setBounds(47, 290, 135, 36);
		contentPane.add(lblEklenecekMiktar_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(47, 359, 135, 36);
		contentPane.add(panel_2);
		
		JButton btnEkle = new JButton("Ekle");
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
		panel_2_1.setBounds(192, 359, 135, 36);
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
		
		String[] dizi = {"Customer","Employee"};
	}

}
