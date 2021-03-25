import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainWindow implements ActionListener,MouseListener{
	public static final Color BGC = new Color(0x09,0xC7,0xF7);
	public static final int ROW=17;
	public static final int COL=17;
	private JFrame f;
	private JPanel topPanel, bottomPanel;
	private PosButton pb[][];
	private JLabel title;
	
	private int turn;//1 stands for player 1; 2 stands for player 2
	private int player1Steps;  //steps of player 1
	private int player2Steps;  //steps of player 2
	private int player3Steps;  //steps of player 3
	private int player4Steps;  //steps of player 4
	private Point player1Point;
	private Point player2Point;
	private Point player3Point;
	private Point player4Point;
	
	public Point getPlayer1Point() {
		return player1Point;
	}

	public void setPlayer1Point(Point player1Point) {
		this.player1Point = player1Point;
	}

	public Point getPlayer2Point() {
		return player2Point;
	}

	public void setPlayer2Point(Point player2Point) {
		this.player2Point = player2Point;
	}
	
	public Point getPlayer3Point() {
		return player3Point;
	}

	public void setPlayer3Point(Point player3Point) {
		this.player3Point = player3Point;
	}
	
	public Point getPlayer4Point() {
		return player4Point;
	}

	public void setPlayer4Point(Point player4Point) {
		this.player4Point = player4Point;
	}


	
	public MainWindow(){
		//header
		f = new JFrame("Quoridor");
		//size
		f.setSize(900, 800);
		//close action
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//the layout
		//f.setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		JButton btnNewButton = new JButton("Back");  //button to access the main menu from the board.
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				MenueWindow preWindow = new MenueWindow();
				preWindow.showWindow();
				
			}
		});
		

		
		topPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		topPanel.add(btnNewButton_1);
		title = new JLabel();
		topPanel.add(title);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setSize(500,500);
		//set the background color
		bottomPanel.setBackground(BGC);
		//set it into the center
		f.setLocationRelativeTo(null);
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(topPanel, BorderLayout.NORTH);
		f.getContentPane().add(bottomPanel, BorderLayout.CENTER);	
		//display
		f.setVisible(true);
		
		pb = new PosButton[ROW][COL];
		for(int i = 0; i < ROW; i++){
			for(int j = 0; j <COL; j++){
				pb[i][j] = new PosButton(i,j,this);
				pb[i][j].addActionListener(this);
				pb[i][j].addMouseListener(this);
				this.bottomPanel.add(pb[i][j]);
			}
		}
		
		turn = 1;
		//this.setTurn();
		this.setTitle();
		f.repaint();
		//this.f.add(pb1);
	}
	
	public int getTurn(){
		return this.turn;
	}
	public PosButton[][] getPos(){
		return this.pb;
	}
	
	private void setTitle(){
		String titleTxt = "";
		if(this.turn == 1){
			titleTxt = "Player1's Turn (Green)    ";
		}else if(this.turn ==2){
			titleTxt = "Player2's Turn (Red)    ";
		}else if(this.turn ==3){
			titleTxt = "Player3's Turn (magenta)    ";
		}else if(this.turn ==4){
			titleTxt = "Player4's Turn (orange)    ";
		}
		titleTxt += "Player1's Steps is " + player1Steps;
		
		titleTxt += "    ";
		titleTxt += "Player2's Steps is " + player2Steps;
		
		titleTxt += "    ";
		titleTxt += "Player3's Steps is " + player3Steps;
		
		titleTxt += "    ";
		titleTxt += "Player4's Steps is " + player4Steps;
		this.title.setText(titleTxt);
	}
	
	public void setTurn(){
		if(this.turn == 1){
			player1Steps++;
			this.turn = 2;
		}else if(this.turn ==2){
			player2Steps++;
			this.turn = 3;
		}else if(this.turn ==3){
			player3Steps++;
			this.turn = 4;
		}else if(this.turn ==4){
			player4Steps++;
			this.turn = 1;
		}
		this.setTitle();
		this.judgeWin();
	}
	
	// judge if one of the player wins
	private void judgeWin(){
		if(player1Point.x == ROW -1){
			JOptionPane.showMessageDialog(null,"Player1 Win");
			f.setVisible(false);
			MenueWindow newWindow = new MenueWindow(); // go to menu after win
			newWindow.showWindow();
		}else if(player2Point.x == 0){
			JOptionPane.showMessageDialog(null,"Player2 Win"); // go to win after win
			f.setVisible(false);
			MenueWindow newWindow = new MenueWindow();
			newWindow.showWindow();
			
			
		}else if(player3Point.y == COL-1){
			JOptionPane.showMessageDialog(null,"Player3 Win"); // go to win after win
			f.setVisible(false);
			MenueWindow newWindow = new MenueWindow();
			newWindow.showWindow();
			
			
		}else if(player4Point.y == 0){
			JOptionPane.showMessageDialog(null,"Player4 Win"); // go to win after win
			f.setVisible(false);
			MenueWindow newWindow = new MenueWindow();
			newWindow.showWindow();
			
			
		}
	}
	
	private void reColor(){
		for(int i = 0; i < ROW; i++){
			for(int j = 0; j <COL; j++){
				pb[i][j].paintColor();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//Add action event and listener.
		Object selected = e.getSource();
		if (selected instanceof PosButton){
			PosButton select = (PosButton) selected;
			//select.setBoard(true);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// Auto generated method 
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object selected = e.getSource();
		if(selected instanceof PosButton){
			PosButton select = (PosButton) selected;
			select.setBoardMouseIn(this);
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// Recalculated all the colors of blocks.
		this.reColor();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// Mouse clicked actions.
		Object selected = e.getSource();
		if(selected instanceof PosButton){
			PosButton select = (PosButton) selected;
			select.setBoardMousePress(this);
		}
		this.reColor();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// Auto generated method.
	}
}
