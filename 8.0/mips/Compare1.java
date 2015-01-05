//  Compare1.java

import java.io.*;     // needed for IOException

/**
 *  This class sets two values, then compares them to see which is larger.
 *  The larger number is printed to the user, along with a message.
 *  Compare this with compare1.asm for assembly language
 *
 *  @author    Jan Jackson
 *  @version   1.0
 **/
public class Compare1
{
    public static void main( String [] args )
    {
	/*  Begin by setting our two values...  */
	int num1 = 4;
	int num2 = 8;
	
	/*  This variable will be used to store the larger value  */
	int bigger = -1;
	
	/*  Test the two; if the first is larger, store it...  */
	if( num1 > num2 ) {
	    bigger = num1;
	}
	/*  else if the second is larger, store it   */
	else
	    {
		bigger = num2;
	    }
	
	/*  Now print the information to the user   */
	System.out.print( "The larger value is... " + bigger + "\n");
    }
}

