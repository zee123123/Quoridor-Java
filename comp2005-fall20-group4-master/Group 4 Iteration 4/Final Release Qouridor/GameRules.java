

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameRules {
	
	public static void main(String[]args) {
		
		//call the method here
		rules();
	}
	
	public static void rules() {
		JFrame frame = new JFrame ("");
		frame.setBounds(700,700,700,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Back"); //button to access the main menu from the board.
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				MenueWindow preWindow1 = new MenueWindow();
				preWindow1.showWindow();
			}
		});
		btnNewButton.setBounds(0, 0, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTextArea txtrDscd = new JTextArea(); // text area that show the game rules. 
		txtrDscd.setBackground(Color.YELLOW);
		txtrDscd.setFont(new Font("Arial", Font.BOLD, 13));
		txtrDscd.setText("Welcome to Qouridor game !!!!!!!\r\n\r\nQuoridor is played on a game board of 81 square spaces (9x9). Each player is represented\r\n by a pawn which begins at the center space of one edge of the board (in a two-player\r\n game, the pawns begin opposite each other). The objective is to be the first player to\r\n move their pawn to any space on the opposite side of the gameboard from which it \r\nbegins.\r\n\r\nThe distinguishing characteristic of Quoridor is its twenty walls. Walls are flat two-space-wide\r\n pieces which can be placed in the groove that runs between the spaces. Walls block the path\r\n of all pawns, which must go around them. The walls are divided equally among the players at\r\n the start of the game, and once placed, cannot be moved or removed. On a turn, a player may either \r\nmove their pawn, or, if possible, place a wall.\r\n\r\nPawns can be moved to any space at a right angle (but not diagonally). If adjacent to another\r\n pawn, the pawn may jump over that pawn. If an adjacent pawn has a third pawn or a wall on\r\n the other side of it, the player may move to either space that is immediately adjacent\r\n (left or right) to the first pawn. Multiple pawns may not be jumped. Walls may not be jumped, \r\nincluding when moving laterally due to a pawn or wall being behind a jumped pawn.\r\n\r\nWalls can be placed directly between two spaces, in any groove not already occupied by a wall. \r\nHowever, a wall may not be placed which cuts off the only remaining path of any pawn to the side\r\n of the board it must reach.");
		txtrDscd.setBounds(10, 24, 676, 676);
		frame.getContentPane().add(txtrDscd);
		
		
		frame.setVisible(true);
	}
}
