// Solution.java

/**
 * This class creates Solution objects. Each Solution object stores the positions of all Queens on the board whenever
 * a successful 8 Queens game solution has been found.
 * @author admin
 *
 */
public class Solution
{
	Queen [] coordinates = new Queen[8];
	
	public Solution()	{}
	
	/**
	 * This constructor creates a new Solution object, and saves all coordinates of all Queens in the array it is passed.
	 * @param q
	 */
	public Solution(Queen [] q)
	{
		for (int i = 0; i < 8; i++)
		{
			// places Queens, with their respective positions inside the array.
			coordinates[i] = new Queen(q[i].pieceRow, q[i].pieceColumn);
		}
	}
}
