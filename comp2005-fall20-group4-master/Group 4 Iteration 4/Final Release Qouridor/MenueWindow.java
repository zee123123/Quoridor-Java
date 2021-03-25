

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MenueWindow {
	public static void main(String[]args) {
	      // main to call methods
		  showWindow();
	}
	
	public static void showWindow() {
		
		
		JFrame frame = new JFrame ("");    //new frame to create the menu window
		frame.setBounds(700,700,700,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");  //JLabel to insert image
		lblNewLabel.setIcon(new javax.swing.ImageIcon(MenueWindow.class.getResource("/Quoridor2.jpg")));
		lblNewLabel.setBounds(0, 0, 686, 800);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New Game"); // button to access the main game
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainWindow window1 = new MainWindow();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(270, 276, 123, 31);
		
		frame.getContentPane().add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Load"); //button to load the game. (still not implemented).

		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.YELLOW);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(269, 318, 124, 31);
		
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Help"); //button to show the game rules.
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GameRules window = new GameRules();
				window.rules();
			}
		});
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBackground(Color.YELLOW);
		btnNewButton_2.setBounds(270, 360, 123, 31);
		
		frame.getContentPane().add(btnNewButton_2);
		
	
		
        frame.validate();
        
		
		frame.setVisible(true);
	}
}
