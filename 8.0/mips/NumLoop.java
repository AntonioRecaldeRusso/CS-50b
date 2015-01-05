//  NumLoop.java

/************************************************************
 *  This program illustrates a simple for loop in Java, in which
 *  the user specifies the number of values to print, and the
 *  loop cycles to print them.  Demo to compare with SPIM.
 *  
 *  @author     Jan Jackson
 *  @version    1.0  (5/7/08)
 ***********************************************************/

import java.util.*;

public class NumLoop
{
    public static void main( String [] args )
    {
	Scanner in = new Scanner( System.in );

	System.out.print( "How many numbers do you want me to print?  " );
	int qty = in.nextInt();

	for( int i = 1; i <= qty; i++ ) {
	    System.out.print( "  The number is now... " + i + "\n" );
	}
	System.out.print( "\n" );
    }
}
