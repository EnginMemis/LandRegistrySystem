package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		ImageIcon bg = new ImageIcon("1.png");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(190, 232, 184, 98);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Devir Islemleri");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(2, 2, 180, 94);
		btnNewButton.setFocusable(false);
		panel_2.add(btnNewButton);
		btnNewButton.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(548, 232, 184, 98);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Bilgiler");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton_1.setBounds(2, 2, 180, 94);
		btnNewButton_1.setFocusable(false);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.BLACK);
		panel.setBounds(190, 386, 184, 98);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnVeriTabanGncelle = new JButton("<html>" + "Veri Tabanı" + "<br>" +"Güncelle" + "</html>");
		btnVeriTabanGncelle.setForeground(Color.WHITE);
		btnVeriTabanGncelle.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnVeriTabanGncelle.setBounds(2, 2, 180, 94);
		btnVeriTabanGncelle.setFocusable(false);
		panel.add(btnVeriTabanGncelle);
		btnVeriTabanGncelle.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel = new JLabel("Tapu Müdürlügü Bilgi Sistemi\r\n");
		lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 30));
		lblNewLabel.setBounds(190, 11, 601, 129);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("2.jpg"));
		lblNewLabel_1.setBounds(0, 0, 924, 681);
		contentPane.add(lblNewLabel_1);
	}
}
