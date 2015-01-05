//  NumGuess.java

/**
 *  This program plays a number guessing game with the user, in which the user 
 *  tries to guess a randomly selected value.  Upon success, the number of guesses 
 *  is displayed.
 *
 *  @author   Jan Jackson
 */

import java.util.*;

public class NumGuess
{
    //  The random number
    private int number;

    /**
     *  Checks the user's guess against the randomly selected number
     *
     *  @param   value   The user's guess
     *  @return          0 for a match, -1 if too low, 1 if too high
     */
    public int checkGuess( int value )
    {
        if( value == number ) return 0;
        else if( value < number ) return -1;
        else return 1;
    }

    /**
     *  Initially, instructions are printed and the number is randomly obtained.
     *  The method then sets up and runs the game, looping while the user tries 
     *  to guess the number.  For incorrect guesses, the user is told to "Guess 
     *  higher" or "Guess lower".
     */
    public void play()
    {
        int guesses = 0, comp = 0;
        Scanner in = new Scanner( System.in );
        number = (int) ( Math.random() * 100 + 1 );

        System.out.println( "\nThis game will give you a chance to guess the lucky number!" +
                            "\n\tPick a number between 1 and 100 and type it in... " +
                            "\n\tI'll tell you if you are high or low!" );

        //  Loops until the user matches the number, then breaks out.
        while( true ) {
            System.out.print( "\n\tYour guess is:  " );
            int input = in.nextInt();
            guesses++;
            comp = checkGuess( input );
            if( comp == 0 ) {
                System.out.println( "\n\tYOU WIN!!!  Only " + guesses + " guesses!!\n" );
                break;
            }
            else if( comp < 0 ) {
                System.out.println( "\t  Guess higher!" );
            }
            else {
                System.out.println( "\t  Guess lower!" );
            }
        }
    }

    public static void main( String [] args )
    {
        NumGuess ng = new NumGuess();
        ng.play();
    }
}