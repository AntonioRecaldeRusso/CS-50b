// Queen.java

/**
 * This class creates Queen objects. These objects store the positions in which they were placed on the board.
 * In case they were to be removed from a certain board position, they do not reset their values. However,
 * those values get overwritten the next time they are place on the board.
 * 
 * @author Antonio Recalde
 * @version 05/09/2013
 *
 */
public class Queen
{
	int pieceRow = 0;
	int pieceColumn = 0;
	
	public Queen()	{}
	
	public Queen(int row, int column)
	{
		pieceColumn = column;
		pieceRow = row;
	}
	
	/**
	 * This method assigns coordinates that represent a a Queen's position on the board.
	 * @param row
	 * @param column
	 */
	public void placeQueen(int row, int column)
	{
		pieceRow = row;
		pieceColumn = column;
	}
	
	public String toString()
	{
		return String.format(" %d:%d ", pieceRow, pieceColumn);
	}
}
