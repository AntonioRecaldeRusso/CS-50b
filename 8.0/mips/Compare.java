//  Compare.java

/**
 *  This program asks the user for two integers from the keyboard,
 *  compares them, and prints the larger value.
 *  
 *  @author   Jan Jackson
 */

import java.util.*;
import java.io.*;

public class Compare
{
    public static void main( String args[] ) 
    {
	int num1 = 0, num2 = 0;
	Scanner in = new Scanner( System.in );

	/*  Request input from the user   */
	System.out.print(" Enter an integer... ");
	num1 = in.nextInt();
	
	System.out.print(" Enter an integer... ");
	num2 = in.nextInt();

	/*  Evaluate the two numbers to determine the larger, and print it  */
	System.out.print( " The larger value is... " );
	if( num1 > num2 ) {
	    System.out.println( num1 );
	}
	else {
	    System.out.println( num2 );
	}
    }
}


