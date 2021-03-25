import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Container;

public class MenueWindow {
	public static void main(String[]args) {
	      // main to call methods
		  showWindow();
	}
	
	public static void showWindow() {
		
		
		JFrame frame = new JFrame ("Quoridor");    //new frame to create the menu window
		frame.setBounds(700,700,700,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");  //JLabel to insert image
		lblNewLabel.setIcon(new javax.swing.ImageIcon(MenueWindow.class.getResource("/Quoridor2.jpg")));
		lblNewLabel.setBounds(0, 0, 686, 800);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New Game"); // button to access the main game
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(270, 276, 123, 31);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainWindow window1 = new MainWindow();
				
			}
		});
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setOpaque(true);
		btnNewButton.setVisible(true);
		
		
		JButton btnNewButton_1 = new JButton("Load"); //button to load the game. ( not implemented).
		btnNewButton_1.setForeground(Color.BLACK);
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(269, 318, 124, 31);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setVisible(true);
		btnNewButton_1.setOpaque(true);
		
		JButton btnNewButton_2 = new JButton("Help"); //button to show the game rules.
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnNewButton_2.setBounds(270, 360, 123, 31);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GameRules window = new GameRules();
				window.rules();
			}
		});
		btnNewButton_2.setVisible(true);
		btnNewButton_2.setOpaque(true);
		
		frame.getContentPane().add(btnNewButton_2);
		frame.setVisible(true);
	
		
        frame.validate();
        
		
		frame.setVisible(true);
	}
}
