// FifteenPuzzle.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class creates a JFrame object, then loads it with a JPanel set with a GridLayout
 * in order to create a grid of JButtons. In total, there are 16 buttons, one of which is set 
 * with a "black" background. If a button that is next to the dark or black button is clicked, 
 * this button acquires the dark background and the other one acquires the previous characteristics of
 * the one that was just clicked.
 * 
 * @author Antonio Recalde
 * @version 04/15/213
 *
 */
public class FifteenPuzzle extends JFrame
{
	/* This JButton array is not altogether necessary, but I found it more neat to declare all the JButtons
	 * inside a one-dimensional array rather than inside a 2d array.
	 */
	JButton [] buttons = new JButton[16];
	
	// this 2d array will keep track of the position of all the pieces.
	JButton [][] grid = new JButton[4][4];
	
	/**
	 * This constructor initializes the JButtons as well as our listener class MakeMove().
	 */
	public FifteenPuzzle()
	{
		this.setLayout(new GridLayout(4, 4));
		MakeMove moveRequest = new MakeMove();
		
		// initializes the numbered buttons
		for (int i = 0; i < 16; i++)
		{
			buttons[i] = new JButton("" + (i + 1));
			buttons[i].addActionListener(moveRequest);
		}
		// button 15 is the special button... for now.
		buttons[15].setText("");
		buttons[15].setBackground(Color.BLACK);
		// loads into the screen.
		this.load();
	}
	
	public static void main(String[] args) 
	{
		// this class extends JFrame, therefore this initializes the JFrame puzzle, and runs the objects constructor
		FifteenPuzzle puzzle = new FifteenPuzzle ( );
		puzzle.setTitle ("Fifteen Puzzle");
		puzzle.setSize( 400,  400);
		puzzle.setVisible( true);
		puzzle.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * This method shuffles the numbers in the grid before starting the game.. It then loads them into the JPanel in their
	 * respective positions.
	 */
	public void load()
	 {
		// this boolean variable will tell us which numbers the random generator found and loaded.
		boolean [] loaded	= new boolean[16];
		int randomNumber = (int) (16 * Math.random());				// initialize here, so it goes into the loop already containing a value.
		 
		for (int i = 0; i < 4; i++)									// for each column
		{
			for (int j = 0; j < 4; j++)								// each row.. 
			{
				while ( loaded[randomNumber] )						// if the number has already been found...
					randomNumber = (int) (16 * Math.random());		// ...then look for another number until loaded[randomNumber] is false
		
				loaded[randomNumber] = true;						// ...then set this randomNumber to true, because it has just been found
				grid[i][j] = buttons[randomNumber];					// now, in the grid, we'll add a pointer to its location.
				this.add(grid[i][j]);								// add it to the object.. the object is a frame.. same as frame.add();
			 }
		}
	 }
	/**
	 * This method moves the pieces if the request is a legal move
	 * The array "xy" represents the X, and Y coordinates of a button in the grid.
	 * The truth is that ***the buttons do not move***, they simply change their background and
	 * text in order to simulate movement.
	 * 
	 * @param xy 
	 */
	public void movePiece(int [] xy)
	{
		String text = grid[ xy[0] ][ xy[1] ].getText();				// get the text in the button at that location. xy[0] is the X coordinate. 
		Color bg = grid[ xy[0] ][ xy[1] ].getBackground();			
		
		int [] empty = locateEmpty();								// locates the position of the "black" or empty button.
		if ( isLegalMove(xy, empty ) )								// checks if the move is legal...
		{
			grid[ empty[0] ][ empty[1] ].setBackground(bg);			/* swap characteristics...*/
			grid[ empty[0] ][ empty[1] ].setText(text);
			
			grid[ xy[0] ][ xy[1] ].setBackground(Color.BLACK);		/* this is the new black */
			grid[ xy[0] ][ xy[1] ].setText("");
		}
		
	}
	
	/**
	 * This method returns true if a requested move is legal.
	 * I was inspired by the chess problem from a couple psets ago.
	 * The mechanism to determine if a move is legal is similar to the movement of the king, except for the diagonals.
	 * 
	 * @param xy
	 * @param empty
	 * @return
	 */
	public boolean isLegalMove(int [] xy, int [] empty  )
	{
		int deltaXX = ( Math.abs(xy[0] - empty[0]) );				// difference between "clicked button" and "empty button" along the X axis
		int deltaYY = ( Math.abs(xy[1] - empty[1]) );				// difference between "clicked button" and "empty button" along the Y axis
		
		/* 
		 * One of axes must be equal for a move to be legal. But at the same time, the other axis must not be farther than
		 * one square away. E.g. [1,1] is next to [1,0].. so it's legal.
		 */
		if ( ( deltaXX <= 1 && deltaYY <= 1 ) && ( Math.abs(deltaXX - deltaYY) == 1 ) )
			return true;
		
		else return false;
	}
	
	/*
	 * This method locates the X-Y position of the "black" or "empty" square. This information is later used to determine if the button
	 * clicked is next to the empty button when verifying if a move is legal or not.
	 */
	public int [] locateEmpty()
	{
		/* go through the grid until you find it... once you do, return */
		for (int i = 0; i < 4; i++)	
		{
			for (int j = 0; j < 4; j++)
			{
				if ( grid[i][j].getText().equals("") )
				{
					int [] isHere = {i, j};
					return isHere;
				}
			}
		}
		return null;												// this should never happen.
	}
	
	/*
	 * This is our ActionListener class
	 */
	class MakeMove implements ActionListener
	{
		public void actionPerformed (ActionEvent ae)
		{
			JButton button = (JButton) ae.getSource();				// get the source, then go through the grid comparing it to each button.
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
				{
					if (grid[i][j] == button)
					{
						int []  fromPosition = {i, j};				// once found, the i and j variables of the loop correspond to the X and Y coordinates.
						movePiece(fromPosition);
						break;
					}
				}
		}
	}	
}