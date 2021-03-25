package testRunQuoridor02;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PosButton extends JButton{
	public static final int LONG_SIDE = 40;  // the long side-> block size
	public static final int SHORT_SIDE = 30; // the short side-> grid gap size
	public static final Color BGC = new Color(0x09,0xC7,0xF7);
	
	//enum the type of girds
	public enum ButtonType{
		//squires
		Squire,
		//board
		Board,
		// grids between 2 boards 
		Center
	}
	//
	public enum ButtonPlayer{
		//may add more player after, for now just 2 players 
		Non,
		Player1,
		Player2
	}
	
	boolean isBoard;           //isBoard to judge if it is covered
	private ButtonType btype;  // the type of grids
	int x;                     //X coordinates
	int y;                     //Y coordinates
	
	private ButtonPlayer bplayer;
	public ButtonPlayer getBplayer() {
		return bplayer;
	}

	public void setBplayer(ButtonPlayer bplayer) {
		this.bplayer = bplayer;
	}
	
	
	public void setIsBoard(boolean _isBoard){
		this.isBoard = _isBoard;
	}
	
	public PosButton(int x,int y,MainWindow mw){
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.x=x;
		this.y=y;
		this.isBoard = false;
		this.setOpaque(true); // !!Without this code, the code can run on windows but cannot run on mac!!
		if( x==0 && y== MainWindow.COL/2 ){
			this.bplayer = ButtonPlayer.Player1;
			mw.setPlayer1Point(new Point(x, y));
		}else if( x== MainWindow.ROW - 1 && y== MainWindow.COL/2 ){
			this.bplayer = ButtonPlayer.Player2;
			mw.setPlayer2Point(new Point(x, y));
		}else{
			this.bplayer = ButtonPlayer.Non;
		}
		
		// paint color on grids
		paintColor();
		// Calculate the position of pawns
		int xx = LONG_SIDE * ((y+1)/2) + SHORT_SIDE * (y/2);
		int yy = LONG_SIDE * ((x+1)/2) + SHORT_SIDE * (x/2);
		this.setLocation(xx, yy);
		// set no border
		this.setBorderPainted(false);
		
		
		
	}
	
	public void paintColor(){
		if( x % 2 == 0 && y % 2 == 0 ){  // white grids
			btype = ButtonType.Squire;
			this.setSize(LONG_SIDE,LONG_SIDE);
			this.setBackground(Color.white);
			if(this.bplayer == ButtonPlayer.Player1){ // set the player 1 color to green
				this.setBackground(Color.green);
			}else if(this.bplayer == ButtonPlayer.Player2){ //set the player 2 color to red 
				this.setBackground(Color.red);
			}
		}else if( x % 2 == 1 && y % 2 == 1 ){    //column grids
			btype = ButtonType.Center;
			this.setSize(SHORT_SIDE,SHORT_SIDE);
			this.setBackground(BGC);
		}else if( x % 2 == 1 && y % 2 == 0 ){    //row grids
			btype = ButtonType.Board;
			this.setSize(LONG_SIDE,SHORT_SIDE);
			this.setBackground(BGC);
		}else if( x % 2 == 0 && y % 2 == 1 ){    // the smallest grids
			btype = ButtonType.Board;
			this.setSize(SHORT_SIDE,LONG_SIDE);
			this.setBackground(BGC);
		}
		if(this.btype == ButtonType.Board || this.btype == ButtonType.Center){
			if(this.isBoard){
				this.setBackground(Color.gray);
			}
			
		}
	}
	
	public void setBoard(boolean _isBoard){
		if(this.btype == ButtonType.Board){
			this.isBoard = _isBoard;
			if(_isBoard){
				this.setBackground(Color.gray);
			}else{
				this.setBackground(BGC);
			}
		}
	}
	//set the wall
	public void setBoardMousePress(MainWindow mw){
		PosButton[][] pb = mw.getPos();
		if(this.btype == ButtonType.Board){
			//set the wall on columns
			if( x%2 == 1){
				if( this.y < MainWindow.COL - 1){
					pb[this.x][this.y + 1].setIsBoard(true);
					pb[this.x][this.y + 2].setIsBoard(true);
					mw.setTurn();
				}
			}else if( y%2 ==1 ){//set the rows' walls
				if( this.x < MainWindow.ROW - 1){
					pb[this.x + 1][this.y].setIsBoard(true);
					pb[this.x + 2][this.y].setIsBoard(true);
					mw.setTurn();
				}
			}
		}
		//click to move
		if(this.btype == ButtonType.Squire){
			int turn = mw.getTurn();
			if(turn == 1){
				Point p1 = mw.getPlayer1Point();
				if((x+2==p1.x&&y==p1.y)||(x-2==p1.x&&y==p1.y)||(y+2==p1.y&&x==p1.x)||(y-2==p1.y&&x==p1.x)){
					if(!pb[(x+p1.x)/2][(y+p1.y)/2].isBoard && pb[x][y].bplayer==ButtonPlayer.Non){
						mw.setPlayer1Point(new Point(x,y));
						this.setBplayer(ButtonPlayer.Player1);
						pb[p1.x][p1.y].setBplayer(ButtonPlayer.Non);
						mw.setTurn();
					}
					
				}
			}else if(turn == 2){
				Point p2 = mw.getPlayer2Point();
				if((x+2==p2.x&&y==p2.y)||(x-2==p2.x&&y==p2.y)||(y+2==p2.y&&x==p2.x)||(y-2==p2.y&&x==p2.x)){
					if(!pb[(x+p2.x)/2][(y+p2.y)/2].isBoard && pb[x][y].bplayer==ButtonPlayer.Non){
						mw.setPlayer2Point(new Point(x,y));
						this.setBplayer(ButtonPlayer.Player2);
						pb[p2.x][p2.y].setBplayer(ButtonPlayer.Non);
						mw.setTurn();
					}
					
				}
			}
		}
	}
	

	//when the mouse is on the pawn
	public void setBoardMouseIn(MainWindow mw){
		PosButton[][] pb = mw.getPos();
		int turn = mw.getTurn();
		if(this.btype == ButtonType.Board){
			// change the color of column grids
			if( x%2 == 1){
				if( this.y < MainWindow.COL - 1){
					this.setBackground(Color.gray);
					pb[this.x][this.y + 1].setBackground(Color.gray);
					pb[this.x][this.y + 2].setBackground(Color.gray);
				}
			}else if( y%2 ==1 ){ //change the color of row grids
				if( this.x < MainWindow.ROW - 1){
					this.setBackground(Color.gray);
					pb[this.x + 1][this.y].setBackground(Color.gray);
					pb[this.x + 2][this.y].setBackground(Color.gray);
				}
			}
		}
		if((turn == 1 && this.bplayer == ButtonPlayer.Player1) || (turn == 2 && this.bplayer == ButtonPlayer.Player2)){
			if(x+1 < MainWindow.ROW - 1){ // if not on the bottom edge
				if(!pb[this.x + 1][this.y].isBoard && pb[this.x + 2][this.y].bplayer == ButtonPlayer.Non){
					
					pb[this.x + 2][this.y].setBackground(Color.lightGray);
				}
			}
			if(x>0){                    // if not on the top edge
				if(!pb[this.x -1][this.y].isBoard && pb[this.x - 2][this.y].bplayer == ButtonPlayer.Non){
					pb[this.x -2][this.y].setBackground(Color.lightGray);	
				}
				
			}                           // if not on the right edge
			if(y+1 < MainWindow.COL - 1){
				if(!pb[this.x][this.y + 1].isBoard && pb[this.x][this.y + 2].bplayer == ButtonPlayer.Non){
					pb[this.x][this.y + 2].setBackground(Color.lightGray);
				}
			}                           // if not on the left edge
			if(y>0){
				if(!pb[this.x][this.y - 1].isBoard && pb[this.x][this.y - 2].bplayer == ButtonPlayer.Non){
					pb[this.x][this.y - 2].setBackground(Color.lightGray);	
				}
				
			}
		}
	}
	//set the action when mouse leaves, paint the color
	public void setBoardMouseMove(PosButton pb[][]){
		this.paintColor();
		
	}

}