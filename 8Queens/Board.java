// Board.java

import java.util.Arrays;

/**
 * This class creates a board object for the 8-Queens game.
 * The objective of this game is to find a combination of queen positions in which 8 queens must be
 * placed on the board, without any of them attacking each other. This means, that the queens must never share
 * the same Row, Column, or Diagonal.
 * 
 * This class has its own main method. It may run by itself, or it may be executed via NQueens.java
 * 
 * @author Antonio Recalde
 * @version 05/09/2013
 *
 */
public class Board 
{
	/**
	 * This array will keep track of which squares are under attack. Thus, each queen will check whether the square is safe 
	 * or not, instead of calculating its safety based on the positions of the other queens.
	 * It represents an 8x8 board.
	 */
	public static int [] attacked = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 		0, 0, 0, 0, 0, 0, 0, 0, 0		// avoids ArrayOutOfBounds exception.
	};
	
	// squaresIndex keeps track of the current square the program must analyze.
	public static int squaresIndex;
	
	/**
	 *  This array keeps track of the squares where the program already tried placing queens, with no success of finding a solution.
	 *  Based on the results in this array, we determine our next square destination when backtracking.
	 */
	public static int [] tried = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 		0, 0, 0, 0, 0, 0, 0, 0, 0		// avoids arrayOutOfBoundsException.
	};
	
	// There are 92 possible solutions in this game. We save the results of each solution in this array.
	public static Solution [] s = new Solution[92];
	
	public static int thisRow;											// this is the current row we are trying to place queens in.
	public static int thisColumn;										// stores the current column we will check.
	public static int queenIndex = 0;									// use for accessing arrays. Stores number of queens on the board.
	public static int position;											// temporary variable. Marks piece location for method to work with it.
	 
	//this Queen array stores the values of the queens we have currently placed over the board.  
	public static Queen [] q = new Queen[9];
	
	public static long elapsedTime = 0;									// ...tells how long it took to find all solutions.
	
	
	public Board()
	{
		squaresIndex = 0;
		thisRow = 0;
		thisColumn = 0;
		
		for (int i = 0; i < 9; i++)										// declaring each queen object.
			q[i] = new Queen();
	}
	
	public static void solve()
	{ 	
		long startTime = System.currentTimeMillis();					// used for measuring time.
		int solution_counter = 0;										// keeps track of how many solutions have been found so far.

		while (solution_counter < 92)
		{

 		/*
 		 *  The lines below are commented because they are only meant to be used when running Board.java in the command prompt.
 		 *   When running the program using NQueens.java, these lines become unnecessary.
 		 */
/* 		 
		System.out.println("run: " + run);
		for (int i = 0; i < 64; i++)
		{
		if (i % 8 == 0)
			System.out.println();
		System.out.printf("%2d", attacked[i]);
		}
		System.out.printf("\n %d -- %d \n\n", squaresIndex, attacked[49]);
		
		for (int j = 0; j < 64; j++)
		{
		if (j % 8 == 0)
			System.out.println();
		System.out.printf("%2d", tried[j]);
		}
		System.out.println();
*/		
			
			/*
			 * The following if statement allows the execution of its subsequent code granted a square is safe and untested.
			 * It works in the following way:
			 * 
			 * 1) If the current square we are analyzing (represented by "squaresIndex") has a value other than 1, it means it's being attacked. So, skip.
			 * 2) If we already tried a square with no success, tried[squareIndex] will not equal 0... so, skip.
			 * 3) If squaresIndex > 63, it's beyond the range of a chess board. This may happen after a Queen was just placed in the 63th square.
			 * 4) If the queen count is more than 7, it means we already have all the queens (0 - 7) we need. 
			 */
			if ( (attacked[squaresIndex] == 0) && (tried[squaresIndex] == 0) && (squaresIndex) < 64 && (queenIndex < 8) )
			{
				// Here, we set the location of the current queen on the board. The locations are saved in the Queen object.
				q[queenIndex].placeQueen(thisRow, thisColumn);
				markAttacked();												// marks the squares under attack.
				tried[squaresIndex] = 1;									// the square where the queen was place was tried.. So, update array.
				thisRow++;													// move on to the next row.
				squaresIndex = thisRow * 8;									// the new board position we must analyze is now the first column of this new row.
				thisColumn = 0;												
				queenIndex++;												// update number of queens on the board.
				
				
				// the following if statement is true whenever we have found a solution. queenIndex == 8 only when all queens have been placed
				if (queenIndex == 8)
				{
					// stores all current values in our Queens [] array via the constructor.
					s[solution_counter] = new Solution(q);
					solution_counter++;
				}
			}
			
			// This condition happens when the square we were looking at was not the correct one.
			else
			{
				thisColumn++;												// update so we can analyze the next column.
				squaresIndex++;			
				
				
				//If we went past the last column, it means that we placed no Queens on this Row.. Thus, we need to go back and remove the last one.
				if (thisColumn > 7)			
					backtrack();
			}
		}
		long stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
	}
	
	/**
	 * This method removes the previous Queen, unmarks all the squares the removed Queens were attacking, and updates the
	 * new starting point of the search.
	 */
	private static void backtrack() 
	{
		// updates number of Queens on the board.
		queenIndex--;
		
		/*
		 *  updates the new starting point of the search. It is the next square after the unsuccessful position. Thus, if for example,
		 *  the removed queen was in the squareIndex #23, the program must continue the analysis in squareIndex #24.
		 */
		squaresIndex = ( (q[queenIndex].pieceRow * 8) + (q[queenIndex].pieceColumn) + 1 );
		thisRow = q[queenIndex].pieceRow;
		thisColumn = q[queenIndex].pieceColumn + 1;
		
		// unmarks all squares that were being attacked by the Queen we just removed.
		unmarkAttacked();
		
		// updates the tried[] array.
		updateTried(squaresIndex);
		
		/*
		 *  recurse if after backtracking, the next square leads to a new row. This happens when the backtracked Queen was placed at the
		 *  rightmost column. Thus, if the last column in that row is not safe, it means that the columns were exhausted without a successful find.
		 *  Consequently, we can have to remove 2 Queens at once.
		 */
		if (thisColumn > 7)
			backtrack();
	}

	/**
	 * This method marks all attacked squares by giving them non-zero values. Each square displays the number of pieces attacking it.
	 * E.g. If 3 Queens attack a square, its value will be 3.
	 */
	private static void markAttacked() 
	{
		/* divide and conquer */
		markRow();
		markColumn();
		markDiagonal();
	}

	/**
	 * This method increments the value of the attacked squares in the same row as the Queen's current position.
	 */
	private static void markRow()
	{
		// this for loop will traverse the row and increment the value of each square in the attacked[] array.
		for (int i = 0; i < 8; i++)
		{	
			// ...but, if the loop is over the current position of the Queen, ignore it. The Queen does not attack the square she sits in.
			if (i == thisColumn)
				continue;
			attacked[(8 * thisRow) + i]++;
		}
	}
	
	/**
	 * This method increments the value of all attacked squares in the same column as the current Queen's position.
	 */
	private static void markColumn() 
	{
		for (int i = 0; i < 8; i++)
		{
			// if traversing over the Queen's current position, ignore it.
			if (i == thisRow)
				continue;
			
			attacked[(8 * i) + thisColumn]++;
		}
	}
	
	/**
	 * This method increments the value of all diagonals under influence of the currently placed Queen, inside the attacked[] array.
	 */
	private static void markDiagonal()
	{
		position = ((thisRow * 8) + thisColumn);							// This is where the Queen sits. Using position instead of squareIndex
		
		/*
		 * Dividing the board in 4 quadrants, all in relation to the Queen's current position.
		 */
		int quadrant1 = position;											// NorthWest
		int quadrant2 = position;											// NorthEast
		int quadrant3 = position;											// SouthWest
		int quadrant4 = position;											// SouthEast
		
		/*
		 * In the following while() condition statements, as long as the stopping conditions have not been found, each quadrant coordinate will
		 * keep traversing the board. Each diagonal position differs in index from its neighbors by an absolute value of 7 or 9, depending on which
		 * direction they are at... Thus, for position 0, it's bottom-right diagonal will be 9... But 0 has no other diagonals.
		 * This is the advantage of using a one-dimensional array for this problem... The next square can be accessed via one single mathematical 
		 * operation.  In my opinion... of course.
		 */
		
		// This executes when the Queen is not in the top-most row, and neither is she at the left-most column.
		while ((quadrant1 >= 9) && ((quadrant1 % 8) != 0))
		{
			quadrant1 -= 9;													
			attacked[quadrant1]++;											
		}
		// This executes when the Queen is not in the top-most row and neither in the left-most column.
		while ((quadrant2 > 7) && ((quadrant2 % 8) != 7))
		{
			quadrant2 -= 7;
			attacked[quadrant2]++;
		}
		// Executes only when bounds of the board have not been exceeded, and not in the left-most column.
		while ((quadrant3 <= 63) && ((quadrant3 % 8) != 0))
		{
			quadrant3 += 7;
			attacked[quadrant3]++;
		}
		// Executes only when bounds of the board have not been exceeded, and not in the right-most column.
		while ((quadrant4 <= 63) && ((quadrant4 % 8) != 7))
		{
			quadrant4 += 9;
			attacked[quadrant4]++;
		}
	}
	
	/**
	 * This method unmarks the values corresponding to a removed Queen, in the attacked[] array.
	 */
	private static void unmarkAttacked() 
	{
		unmarkRow();
		unmarkColumn();
		unmarkDiagonal();
	}
	
	/**
	 * This method unmarks rows.
	 */
	private static void unmarkRow()
	{
		for (int i = 0; i < 8; i++)
		{	
			if (i == q[queenIndex].pieceColumn)
				continue;
			attacked[(8 * thisRow) + i]--;
		}
	}
	
	/**
	 * This method unmarks columns
	 */
	private static void unmarkColumn()
	{
		for (int i = 0; i < 8; i++)
		{
			if (i == q[queenIndex].pieceRow)
				continue;
			
			attacked[(8 * i) + q[queenIndex].pieceColumn]--;
		}
	}
	
	/**
	 * This method unmarks Diagonals. It works in the same way as the markDiagonals() method. The difference is that instead of
	 * incrementing the positions in the attacked[] array, it deducts from them... erasing whatever the may have previously added.
	 */
	private static void unmarkDiagonal()
	{
		position = ((thisRow * 8) + q[queenIndex].pieceColumn);
		int quadrant1 = position;
		int quadrant2 = position;
		int quadrant3 = position;
		int quadrant4 = position;
		
		while ((quadrant1 >= 9) && ((quadrant1 % 8) != 0))
		{
			quadrant1 -= 9;
			attacked[quadrant1]--;
		}
		while ((quadrant2 > 7) && ((quadrant2 % 8) != 7))
		{
			quadrant2 -= 7;
			attacked[quadrant2]--;
		}
		while ((quadrant3 <= 63) && ((quadrant3 % 8) != 0))
		{
			quadrant3 += 7;
			attacked[quadrant3]--;
		}
		while ((quadrant4 <= 63) && ((quadrant4 % 8) != 7))
		{
			quadrant4 += 9;
			attacked[quadrant4]--;
		}	
	}
	
	/**
	 * This method updates the tried[] array. Thus, when backtracking, all the tries that came after the queen that was removed
	 * are erased. E.g. Suppose the password of your luggage is 1234, and you try to find it by brute force.. If you tried 1134, you don't want
	 * mark the last two digits as tried based on the logic of this program... or else you may not retry them in the same position.
	 * @param index
	 */
	public static void updateTried(int index)
	{
		for (int i = index; i < 64; i++)
			tried[i] = 0;
	}
	
	/**
	 * Main method for running Board.java in the command prompt.
	 * @param args
	 */
	public static void main(String [] args)
	{
		Board b = new Board();
		solve();
	}
}
