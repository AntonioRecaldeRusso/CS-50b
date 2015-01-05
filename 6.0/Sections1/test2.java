import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//  A version of the old fifteen puzzle: move squares until values
//  are in order.

public class test2  extends JFrame {
	//  create an array of references to the squares
	JButton [ ][ ] squares = new JButton [4][4];
	
	// Constructor: create 16 buttons and place them
	//  on a grid. Keep a two-dimensional array to be
	//  able to locate a given button on the grid.
	//  Add an action listener to receive button events.
	
	public test2 ( ) {
		Container container = getContentPane ( );
		container.setLayout( (new GridLayout( 4, 4)));
		
		for (int i = 0; i <4; i++) {
			for (int j = 0; j < 4; j++) {
			    JButton num = new JButton ( );
			    squares [i] [j]= num;
			    container.add( num);
			}
		}
		//  Create even handler as an implementation of
		//  ActionListener.
		
		ButaneListener pushed = new ButaneListener( ); 
				
         //  Indicate that every button event is handled by the listener object.
	   for (int i = 0; i < 4; i++ ) {
	      for (int j = 0; j < 4; j++) {
		   squares [i][j].addActionListener (pushed);
	      }
	   }
    }   //  end fifteen

    public static void main(String[] args) {
	test2 game = new test2 ( );
	game.setTitle ("can you solve it?");
	game.setSize( 400,  400);
	game.setVisible( true);
	game.scramble ( );
	game.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
	
   }
   
   //USE THIS FOR THE MODEL FOR INIT(); ONLY NOW THE TILES WILL BE LABELED WITH
   // CONSECUTIVE INTEGERS
   public void scramble ( ) {
	//  fill the squares with random numbers. keep track
	//  of numbers already to ensure they are all distinct.
		
	boolean  [ ] used = new boolean [16];
		
	//  generate a random number between 0 and 15
	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
	     	    int val = (int)(16*Math.random ( ));
			
		    while (used [val]) {
		    	val = (int) (16 * Math.random ( ));
		     }
		     //  now this value has been applied
		     used [val] = true;
			
		     //  leave one square empty and identify it
		     //  by color
		    if (val !=0) {
		         squares[i][j].setText( "" + val);
		         squares[i][j].setBackground (Color.lightGray);
		    }
		    else
		         squares[i][j].setBackground (Color.blue);
	  	}
	   }	
    }  //  end scramble
	
	
	//USE THIS METHOD WITHOUT MAKING ANY CHANGES
    public void moveSquare (int i, int j) {
	JButton empty = null;
	//  find if the empty square is adjacent to the selected one.
	//  the neighbors of (i, j) are (i+1, j), (i-1, j), (i, J+1), and (i, j-1).
	//  as long as all quantities are between 0 and 3.
		
	if (i < 3 && squares [i+1][j].getBackground( ) == Color.blue) 
		empty = squares [i+1][j];
	if (i > 0 && squares [i -1][j].getBackground( ) == Color.blue) 
		empty = squares [i -1][j];
	if (j < 3 && squares [i][j+1].getBackground( ) == Color.blue) 
		empty = squares [i][j+1];
	if (j > 0 && squares [i][j -1].getBackground( ) == Color.blue) 
		empty = squares [i][j -1];
		
	if (empty == null) return;    //  player pressed on a button that cannot be moved
		
	else {
		empty.setText (squares [i][j].getText ( ));
		empty.setBackground (Color.lightGray);
		squares [i][j].setText("");
		squares [i][j].setBackground (Color.blue);
		
	}		
    }    // end moveSquare
    
    class ButaneListener implements ActionListener
    {    
	     public void actionPerformed(ActionEvent e) 
	     {
				Object square = e.getSource( );
			    //  iterate over set of buttons to identify the
			    //  one that got pushed.
			outer: for (int i = 0; i < 4; i++ ) 
			{
			   for (int j = 0; j < 4; j++) 
			   {
				if (squares [i][j] == square) 
				{
			 	   moveSquare (i, j);
				   break outer;
//this is not a goto but an indication to break the outer loop.
				}
			  }
			}								 
		   }
	    }
}