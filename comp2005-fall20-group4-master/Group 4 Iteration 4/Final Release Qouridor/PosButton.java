import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PosButton extends JButton{
    public static final int LONG_SIDE = 45;  // the long side-> block size
    public static final int SHORT_SIDE = 35; // the short side-> grid gap size
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
        Non,
        Player1,
        Player2,
        Player3,
        Player4
    }

    boolean isBoard = false;           //isBoard to judge if it is covered
    private ButtonType btype;  // the type of grids
    int x;                     //X coordinates
    int y;                     //Y coordinates
    public int wallNumber = 20;
    int limit = wallNumber / 4;
    static int count1 =0;
    static int count2 = 0;
    static int count3= 0;
    static int count4 = 0;

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

    public boolean getIsBoard(){
        return isBoard;
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
        if( x==0 && y== MainWindow.COL/2 ){    // player1
            this.bplayer = ButtonPlayer.Player1;
            mw.setPlayer1Point(new Point(x, y));
        }else if( x== MainWindow.ROW - 1 && y== MainWindow.COL/2 ){   //player 2
            this.bplayer = ButtonPlayer.Player2;
            mw.setPlayer2Point(new Point(x, y));
        }else if( x== MainWindow.ROW/2 && y== 0 ){   //player 3
            this.bplayer = ButtonPlayer.Player3;
            mw.setPlayer3Point(new Point(x, y));
        }else if( x== MainWindow.ROW/2 && y== MainWindow.COL -1 ){   //player4
            this.bplayer = ButtonPlayer.Player4;
            mw.setPlayer4Point(new Point(x, y));
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
        this.setBorderPainted(false); //change it to false when done.**********************************************************************************************************

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
            }else if(this.bplayer == ButtonPlayer.Player3){ //set the player 3 color to cyan 
                this.setBackground(Color.magenta);  
            }else if(this.bplayer == ButtonPlayer.Player4){ //set the player 4 color to orange 
                this.setBackground(Color.orange);   
            }

        }else if( x % 2 == 1 && y % 2 == 1 ){    //the smallest grids in the crossroad
            btype = ButtonType.Center;
            this.setSize(SHORT_SIDE,SHORT_SIDE);
            this.setBackground(BGC);
        }else if( x % 2 == 1 && y % 2 == 0 ){    //row blue grids
            btype = ButtonType.Board;
            this.setSize(LONG_SIDE,SHORT_SIDE);
            this.setBackground(BGC);
        }else if( x % 2 == 0 && y % 2 == 1 ){    // column blue grids
            btype = ButtonType.Board;
            this.setSize(SHORT_SIDE,LONG_SIDE);
            this.setBackground(BGC);
        }
        if(this.btype == ButtonType.Board || this.btype == ButtonType.Center){
            if(this.getIsBoard() == true){
                this.setBackground(Color.gray);
            }

        }
    }

    public void setBoard(boolean _isBoard){
        if(this.btype == ButtonType.Board){
            this.isBoard = _isBoard;
        }
    }

    public void setBoardMousePress(MainWindow mw){
        int turn = mw.getTurn();
        PosButton[][] pb = mw.getPos();
        //wall number for each player

        if(this.btype == ButtonType.Board){
            if (turn ==1){
                if( count1 <= limit){ 
                    //System.out.print(count1);
                    if( x%2 == 1){ 
                        if( this.y < MainWindow.COL - 1){
                            if (pb[this.x][this.y+2].getIsBoard() == false
                            && pb[this.x][this.y+1].getIsBoard() == false
                            && pb[this.x][this.y].getIsBoard() == false
                            
                            ){

                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x][this.y + 1].setIsBoard(true);
                                pb[this.x][this.y + 2].setIsBoard(true);
                                count1++;
                                mw.setTurn();

                            }
                        //    else if(pb[this.x][this.y+2].getIsBoard() == true){
                          //      mw.setTurn();
                          //  }
                        } // avaible for y end 
                    } // x is odd end 
                    else if( y%2 ==1 ){//set the rows' walls (along y-axis)
                        if( this.x < MainWindow.ROW - 1){
                            if (pb[this.x][this.y].getIsBoard() == false
                            &&pb[this.x+1][this.y].getIsBoard() == false
                            && pb[this.x+2][this.y].getIsBoard() == false){
                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x + 1][this.y].setIsBoard(true);
                                pb[this.x + 2][this.y].setIsBoard(true);
                                count1++;
                                mw.setTurn();
                            }
                            //else if(pb[this.x+2][this.y].getIsBoard() == true){
                              //  mw.setTurn();
                            //}
                        }

                    }

                } // count limit check end 

               else if (count1 > 5){
                   ; 
                }
            } // turn 1 end 


            else if (turn ==2 ){
                if( count2 <= limit){ 
                    if( x%2 == 1){ 
                        if( this.y < MainWindow.COL - 1){
                            if (pb[this.x][this.y].getIsBoard() == false 
                            &&pb[this.x][this.y+1].getIsBoard() == false 
                            &&pb[this.x][this.y+2].getIsBoard() == false ){
                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x][this.y + 1].setIsBoard(true);
                                pb[this.x][this.y + 2].setIsBoard(true);
                                count2++;
                                mw.setTurn();
                            }
                           // else if(pb[this.x][this.y+2].getIsBoard() == true){
                            //    mw.setTurn();
                            //}
                        } // avaible for y end 
                    } // x is odd end 
                    else if( y%2 ==1 ){//set the rows' walls (along y-axis)
                        if( this.x < MainWindow.ROW - 1){
                            if (pb[this.x][this.y].getIsBoard() == false
                            &&pb[this.x+1][this.y].getIsBoard() == false
                            &&pb[this.x+2][this.y].getIsBoard() == false){
                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x + 1][this.y].setIsBoard(true);
                                pb[this.x + 2][this.y].setIsBoard(true);
                                count2++;
                                mw.setTurn();
                            }
                            //else if(pb[this.x+2][this.y].getIsBoard() == true){
                              //  mw.setTurn();
                            //}
                        }

                    }

                } // count limit check end 

                //else if (count2 > limit){
                //    mw.setTurn(); 
                //}
            } // turn 2 end 

            else if (turn ==3 ){
                if( count3 <= limit){ 
                    if( x%2 == 1){ 
                        if( this.y < MainWindow.COL - 1){
                            if (pb[this.x][this.y].getIsBoard() == false
                            &&pb[this.x][this.y+1].getIsBoard() == false
                            &&pb[this.x][this.y+2].getIsBoard() == false ){
                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x][this.y + 1].setIsBoard(true);
                                pb[this.x][this.y + 2].setIsBoard(true);
                                count3++;
                                mw.setTurn();
                            }
                            //else if(pb[this.x][this.y+2].getIsBoard() == true){
                                //mw.setTurn();
                           /// }
                        } // avaible for y end 
                    } // x is odd end 
                    else if( y%2 ==1 ){//set the rows' walls (along y-axis)
                        if( this.x < MainWindow.ROW - 1){
                            if (pb[this.x][this.y].getIsBoard() == false
                            &&pb[this.x+1][this.y].getIsBoard() == false
                            &&pb[this.x+2][this.y].getIsBoard() == false){
                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x + 1][this.y].setIsBoard(true);
                                pb[this.x + 2][this.y].setIsBoard(true);
                                count3++;
                                mw.setTurn();
                            }
                           // else if(pb[this.x+2][this.y].getIsBoard() == true){
                               // mw.setTurn();
                           // }
                        }

                    }

                } // count limit check end 

                //else if (count3 > limit){
                 //   mw.setTurn(); 
               // }
            } // turn 3 end 
            else if (turn ==4){
                if( count4 <= limit){ 
                    if( x%2 == 1){ 
                        if( this.y < MainWindow.COL - 1){
                            if (pb[this.x][this.y].getIsBoard() == false 
                            &&pb[this.x][this.y+1].getIsBoard() == false 
                            &&pb[this.x][this.y+2].getIsBoard() == false ){
                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x][this.y + 1].setIsBoard(true);
                                pb[this.x][this.y + 2].setIsBoard(true);
                                count4++;
                                mw.setTurn();
                            }
                           // else if(pb[this.x][this.y+2].getIsBoard() == true){
                           //     mw.setTurn();
                           // }
                        } // avaible for y end 
                    } // x is odd end 
                    else if( y%2 ==1 ){
                        //set the rows' walls (along y-axis)
                        if( this.x < MainWindow.ROW - 1){
                            if (pb[this.x][this.y].getIsBoard() == false
                            &&pb[this.x+1][this.y].getIsBoard() == false
                            &&pb[this.x+2][this.y].getIsBoard() == false){
                                pb[this.x][this.y].setIsBoard(true);
                                pb[this.x + 1][this.y].setIsBoard(true);
                                pb[this.x + 2][this.y].setIsBoard(true);
                                count4++;
                                mw.setTurn();
                            }
                           // else if(pb[this.x+2][this.y].getIsBoard() == true){
                             //   mw.setTurn();
                            //}
                        }

                    }

                } // count limit check end 

                //else if (count4 > limit){
                //    mw.setTurn(); 
                //}
            } // turn 4 end 

        } // button Type 



        if(this.btype == ButtonType.Squire){
            if(turn == 1){ 
                Point p1 = mw.getPlayer1Point();
                // if the squares are on its left, right, top , bottom
                if((x+2==p1.x&&y==p1.y)||(x-2==p1.x&&y==p1.y)||(y+2==p1.y&&x==p1.x)||(y-2==p1.y&&x==p1.x)) {
                    // if there is not a wall between squares (blocks) and the square trying to move is not a player (just regalur white square)
                    if(!pb[(x+p1.x)/2][(y+p1.y)/2].isBoard && pb[x][y].bplayer==ButtonPlayer.Non){

                        mw.setPlayer1Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player1);
                        pb[p1.x][p1.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();    
                    }    
                }
                // if the blocks right side is other player and right's right is player 1
                else if((x-4==p1.x&&y==p1.y) && (pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player2 
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player3
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p1.x)/2-1][(y+p1.y)/2].isBoard && !pb[(x+p1.x)/2+1][(y+p1.y)/2-1].isBoard ){
                     mw.setPlayer1Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player1);
                        pb[p1.x][p1.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                 // if the blocks down side is other player and down's down is player 1
                else if((x+4==p1.x&&y==p1.y) && (pb[this.x+2][this.y].bplayer == ButtonPlayer.Player2 
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player3
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p1.x)/2-1][(y+p1.y)/2].isBoard && !pb[(x+p1.x)/2+1][(y+p1.y)/2-1].isBoard ){
                     mw.setPlayer1Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player1);
                        pb[p1.x][p1.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                // if the blocks left side is other player and left's left is player 1
                else if((x==p1.x&&y-4==p1.y) && (pb[this.x][this.y-2].bplayer == ButtonPlayer.Player2 
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player3
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p1.x)/2][(y+p1.y)/2-1].isBoard && !pb[(x+p1.x)/2][(y+p1.y)/2+1].isBoard ){
                     mw.setPlayer1Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player1);
                        pb[p1.x][p1.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                // if the blocks right side is other player and right's right is player 1
                else if((x==p1.x&&y+4==p1.y) && (pb[this.x][this.y+2].bplayer == ButtonPlayer.Player2 
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player3
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p1.x)/2][(y+p1.y)/2-1].isBoard && !pb[(x+p1.x)/2][(y+p1.y)/2+1].isBoard ){
                     mw.setPlayer1Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player1);
                        pb[p1.x][p1.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
               /*
                else if((x+4==p1.x&&y==p1.y)||(x-4==p1.x&&y==p1.y)||(y+4==p1.y&&x==p1.x)||(y-4==p1.y&&x==p1.x)) {
                    //if there are some walls in the pathway
                    if(!pb[(x+p1.x)/2+1][(y+p1.y)/2+1].isBoard && !pb[(x+p1.x)/2+1][(y+p1.y)/2-1].isBoard 
                    &&!pb[(x+p1.x)/2-1][(y+p1.y)/2+1].isBoard && !pb[(x+p1.x)/2-1][(y+p1.y)/2-1].isBoard
                    && pb[(x+p1.x)/2][(y+p1.y)/2].bplayer!=ButtonPlayer.Non){
                        mw.setPlayer1Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player1);
                        pb[p1.x][p1.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                    }

                } 
                */
            }

            else if(turn == 2){
                Point p2 = mw.getPlayer2Point();

                // if the squares are on its left, right, top , bottom
                if((x+2==p2.x&&y==p2.y)||(x-2==p2.x&&y==p2.y)||(y+2==p2.y&&x==p2.x)||(y-2==p2.y&&x==p2.x)){
                    // if there is not a wall between squares (blocks) and the square trying to move is not a player (just regalur white square)
                    if(!pb[(x+p2.x)/2][(y+p2.y)/2].isBoard && pb[x][y].bplayer==ButtonPlayer.Non){

                        mw.setPlayer2Point(new Point(x,y)); 

                        this.setBplayer(ButtonPlayer.Player2);
                        pb[p2.x][p2.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                        // if there is a player below
                        //if(!pb[this.x + 1][this.y].isBoard && pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player1||pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player2){
                        // mw.setPlayer1Point(new Point(x+4,y));
                        //this.setBplayer(ButtonPlayer.Player1);
                        //pb[p2.x][p2.y].setBplayer(ButtonPlayer.Non);
                        // mw.setTurn();
                        //}
                    }
                }
                else if((x-4==p2.x&&y==p2.y) && (pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player1
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player3
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p2.x)/2-1][(y+p2.y)/2].isBoard && !pb[(x+p2.x)/2+1][(y+p2.y)/2-1].isBoard ){
                     mw.setPlayer2Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player2);
                        pb[p2.x][p2.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                 // if the blocks down side is other player and down's down is player 1
                else if((x+4==p2.x&&y==p2.y) && (pb[this.x+2][this.y].bplayer == ButtonPlayer.Player1
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player3
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p2.x)/2-1][(y+p2.y)/2].isBoard && !pb[(x+p2.x)/2+1][(y+p2.y)/2-1].isBoard ){
                     mw.setPlayer2Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player2);
                        pb[p2.x][p2.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                // if the blocks left side is other player and left's left is player 1
                else if((x==p2.x&&y-4==p2.y) && (pb[this.x][this.y-2].bplayer == ButtonPlayer.Player1
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player3
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p2.x)/2][(y+p2.y)/2-1].isBoard && !pb[(x+p2.x)/2][(y+p2.y)/2+1].isBoard ){
                     mw.setPlayer2Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player2);
                        pb[p2.x][p2.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                
                // if the blocks right side is other player and right's right is player 1
                else if((x==p2.x&&y+4==p2.y) && (pb[this.x][this.y+2].bplayer == ButtonPlayer.Player1
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player3
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p2.x)/2][(y+p2.y)/2-1].isBoard && !pb[(x+p2.x)/2][(y+p2.y)/2+1].isBoard ){
                     mw.setPlayer2Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player2);
                        pb[p2.x][p2.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }

            }
                 // if the blocks down side is other player and down's down is player 1
                
            else if(turn == 3){
                Point p3 = mw.getPlayer3Point();
                if((x+2==p3.x&&y==p3.y)||(x-2==p3.x&&y==p3.y)||(y+2==p3.y&&x==p3.x)||(y-2==p3.y&&x==p3.x)){

                    if(!pb[(x+p3.x)/2][(y+p3.y)/2].isBoard && pb[x][y].bplayer==ButtonPlayer.Non){

                        mw.setPlayer3Point(new Point(x,y)); 

                        this.setBplayer(ButtonPlayer.Player3);
                        pb[p3.x][p3.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                    }

                }
                else if((x-4==p3.x&&y==p3.y) && (pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player2
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player1
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p3.x)/2-1][(y+p3.y)/2].isBoard && !pb[(x+p3.x)/2+1][(y+p3.y)/2-1].isBoard ){
                     mw.setPlayer3Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player3);
                        pb[p3.x][p3.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                 // if the blocks down side is other player and down's down is player 1
                else if((x+4==p3.x&&y==p3.y) && (pb[this.x+2][this.y].bplayer == ButtonPlayer.Player2
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player1
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p3.x)/2-1][(y+p3.y)/2].isBoard && !pb[(x+p3.x)/2+1][(y+p3.y)/2-1].isBoard ){
                     mw.setPlayer3Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player3);
                        pb[p3.x][p3.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                // if the blocks left side is other player and left's left is player 1
                else if((x==p3.x&&y-4==p3.y) && (pb[this.x][this.y-2].bplayer == ButtonPlayer.Player2
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player1
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p3.x)/2][(y+p3.y)/2-1].isBoard && !pb[(x+p3.x)/2][(y+p3.y)/2+1].isBoard ){
                     mw.setPlayer3Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player3);
                        pb[p3.x][p3.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                // if the blocks right side is other player and right's right is player 1
                else if((x==p3.x&&y+4==p3.y) && (pb[this.x][this.y+2].bplayer == ButtonPlayer.Player2
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player1
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player4
                )){// if the there are no walls between them
                    if (!pb[(x+p3.x)/2][(y+p3.y)/2-1].isBoard && !pb[(x+p3.x)/2][(y+p3.y)/2+1].isBoard ){
                     mw.setPlayer3Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player3);
                        pb[p3.x][p3.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }

            }

            else if(turn == 4){
                Point p4 = mw.getPlayer4Point();
                if((x+2==p4.x&&y==p4.y)||(x-2==p4.x&&y==p4.y)||(y+2==p4.y&&x==p4.x)||(y-2==p4.y&&x==p4.x)){
                    if(!pb[(x+p4.x)/2][(y+p4.y)/2].isBoard && pb[x][y].bplayer==ButtonPlayer.Non){

                        mw.setPlayer4Point(new Point(x,y)); 

                        this.setBplayer(ButtonPlayer.Player4);
                        pb[p4.x][p4.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                    }

                }
                else if((x-4==p4.x&&y==p4.y) && (pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player2
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player1
                || pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player3
                )){// if the there are no walls between them
                    if (!pb[(x+p4.x)/2-1][(y+p4.y)/2].isBoard && !pb[(x+p4.x)/2+1][(y+p4.y)/2-1].isBoard ){
                     mw.setPlayer4Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player4);
                        pb[p4.x][p4.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                 // if the blocks down side is other player and down's down is player 1
                else if((x+4==p4.x&&y==p4.y) && (pb[this.x+2][this.y].bplayer == ButtonPlayer.Player2
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player1
                || pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player3
                )){// if the there are no walls between them
                    if (!pb[(x+p4.x)/2-1][(y+p4.y)/2].isBoard && !pb[(x+p4.x)/2+1][(y+p4.y)/2-1].isBoard ){
                     mw.setPlayer4Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player4);
                        pb[p4.x][p4.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                // if the blocks left side is other player and left's left is player 1
                else if((x==p4.x&&y-4==p4.y) && (pb[this.x][this.y-2].bplayer == ButtonPlayer.Player2
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player1
                || pb[this.x][this.y-2].bplayer == ButtonPlayer.Player3
                )){// if the there are no walls between them
                    if (!pb[(x+p4.x)/2][(y+p4.y)/2-1].isBoard && !pb[(x+p4.x)/2][(y+p4.y)/2+1].isBoard ){
                     mw.setPlayer4Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player4);
                        pb[p4.x][p4.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
                
                // if the blocks right side is other player and right's right is player 1
                else if((x==p4.x&&y+4==p4.y) && (pb[this.x][this.y+2].bplayer == ButtonPlayer.Player2
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player1
                || pb[this.x][this.y+2].bplayer == ButtonPlayer.Player3
                )){// if the there are no walls between them
                    if (!pb[(x+p4.x)/2][(y+p4.y)/2-1].isBoard && !pb[(x+p4.x)/2][(y+p4.y)/2+1].isBoard ){
                     mw.setPlayer4Point(new Point(x,y));
                        this.setBplayer(ButtonPlayer.Player4);
                        pb[p4.x][p4.y].setBplayer(ButtonPlayer.Non);
                        mw.setTurn();
                }
                }
 

                }

            }



        }




 
    //when the mouse is in objects
    public void setBoardMouseIn(MainWindow mw){
        PosButton[][] pb = mw.getPos();
        int turn = mw.getTurn();
        if(this.btype == ButtonType.Board){
            // change the color of column grids for setting the wall
            if( x%2 == 1){
                if( this.y < MainWindow.COL - 1){
                    if (pb[this.x][this.y].getIsBoard() == false 
                    && pb[this.x][this.y+1].getIsBoard() == false
                    && pb[this.x][this.y+2].getIsBoard() == false){
                        this.setBackground(Color.gray);
                        pb[this.x][this.y + 1].setBackground(Color.gray);
                        pb[this.x][this.y + 2].setBackground(Color.gray);
                    }
                }
            }else if( y%2 ==1 ){ //change the color of row grids for setting the wall
                if( this.x < MainWindow.ROW - 1){
                    if (pb[this.x][this.y].getIsBoard() == false
                    && pb[this.x+1][this.y].getIsBoard() == false
                    && pb[this.x+2][this.y].getIsBoard() == false){
                        this.setBackground(Color.gray);
                        pb[this.x + 1][this.y].setBackground(Color.gray);
                        pb[this.x + 2][this.y].setBackground(Color.gray);
                    }
                }
            }
        }

        if((turn == 1 && this.bplayer == ButtonPlayer.Player1) || (turn == 2 && this.bplayer == ButtonPlayer.Player2)||
        (turn == 3 && this.bplayer == ButtonPlayer.Player3)|| (turn == 4 && this.bplayer == ButtonPlayer.Player4)){
            if(x+1 < MainWindow.ROW - 1){ 
                // if there is a player on bottom
                if(!pb[this.x + 1][this.y].isBoard && pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player1
                ||pb[this.x + 2][this.y].bplayer == ButtonPlayer.Player2){

                    pb[this.x + 4][this.y].setBackground(Color.lightGray);
                }   
                // if not on the bottom edge
                if(!pb[this.x + 1][this.y].isBoard && pb[this.x + 2][this.y].bplayer == ButtonPlayer.Non){

                    pb[this.x + 2][this.y].setBackground(Color.lightGray);
                }

            }
            if(x>0){  
                // if there is a player on top
                if(!pb[this.x - 1][this.y].isBoard && pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player1||pb[this.x - 2][this.y].bplayer == ButtonPlayer.Player2){

                    pb[this.x - 4][this.y].setBackground(Color.lightGray);
                }   

                // if not on the top edge
                if(!pb[this.x -1][this.y].isBoard && pb[this.x - 2][this.y].bplayer == ButtonPlayer.Non){
                    pb[this.x -2][this.y].setBackground(Color.lightGray);   
                }

            }                           
            if(y+1 < MainWindow.COL - 1){
                // if there is a player on right
                if(!pb[this.x][this.y+1].isBoard && pb[this.x][this.y+2 ].bplayer == ButtonPlayer.Player1||pb[this.x][this.y+2 ].bplayer == ButtonPlayer.Player2){

                    pb[this.x][this.y+4].setBackground(Color.lightGray);
                }   

                // if not on the right edge
                if(!pb[this.x][this.y + 1].isBoard && pb[this.x][this.y + 2].bplayer == ButtonPlayer.Non){
                    pb[this.x][this.y + 2].setBackground(Color.lightGray);
                }
            }                          
            if(y>0){
                // if there is a player on the left
                if(!pb[this.x][this.y-1].isBoard && pb[this.x][this.y-2].bplayer == ButtonPlayer.Player1||pb[this.x][this.y-2].bplayer == ButtonPlayer.Player2){

                    pb[this.x][this.y-4].setBackground(Color.lightGray);
                }   

                // if not on the left edge
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
