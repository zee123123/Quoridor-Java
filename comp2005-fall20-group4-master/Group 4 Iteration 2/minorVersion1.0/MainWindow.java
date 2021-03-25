import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private Point player1Point;
	private Point player2Point;
	
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
	
	public MainWindow(){
		//header
		f = new JFrame("Quoridor");
		//size
		f.setSize(700, 800);
		//close action
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//the layout
		//f.setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		title = new JLabel();
		topPanel.add(title);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setSize(500,500);
		//set the background color
		bottomPanel.setBackground(BGC);
		//set it into the center
		f.setLocationRelativeTo(null);
		f.setLayout(new BorderLayout());
		f.add(topPanel, BorderLayout.NORTH);
		f.add(bottomPanel, BorderLayout.CENTER);	
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
			titleTxt = "Player1's Turn    ";
		}else if(this.turn ==2){
			titleTxt = "Player1's Turn    ";
		}
		titleTxt += "Player1's Steps is " + player1Steps;
		titleTxt += "    ";
		titleTxt += "Player2's Steps is " + player2Steps;
		this.title.setText(titleTxt);
	}
	
	public void setTurn(){
		if(this.turn == 1){
			player1Steps++;
			this.turn = 2;
		}else if(this.turn ==2){
			player2Steps++;
			this.turn = 1;
		}
		this.setTitle();
		this.judgeWin();
	}
	
	// judge if one of the player wins
	private void judgeWin(){
		if(player1Point.x == ROW -1){
			JOptionPane.showMessageDialog(null,"Player1 Win");
		}else if(player2Point.x == 0){
			JOptionPane.showMessageDialog(null,"Player2 Win");
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
			select.setBoard(true);
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
		// Recaculated all the colors of blocks.
		this.reColor();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// Mouse cliked actions.
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