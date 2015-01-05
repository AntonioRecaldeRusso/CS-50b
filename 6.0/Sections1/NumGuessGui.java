//  NumGuessGui.java

/****
 *  This program plays a number guessing game.  The program will select a 
 *  random number, and ask the user to guess it.  Upon success, the total 
 *  number of guesses will be displayed.  A graphical user interface has 
 *  been added for user convenience.
 *
 *  @author   Jan Jackson
 *  @version  1.0   Last modified 3/14/08
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class NumGuessGui extends JFrame implements ActionListener
{
    /*  The comments using this style would not normally be part of my class - they
     *  are added to explain some of the new material and to help you...  
     */

    public static final int MYWIDTH = 475;
    public static final int MYHEIGHT = 230;
    
    private int number;
    private int guesses = 0;
    
    private JLabel instructions;
    private JTextArea feedback;
    private JTextField input;
    private JButton enter;

    /****
     *  Constructor for the class, it sets up the user interface and performs
     *  initialization.
     */
    public NumGuessGui()
    {
        /*  Get the random number for this game  */
        number = (int) ( Math.random() * 100 + 1 );
        
        setTitle( "Number Guesser" );
        setSize( MYWIDTH, MYHEIGHT );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        // setLocation( 200, 200 );
        
        /*  I've decided to locate this GUI in the center of the user's 
         *  screen.  The Toolkit object has a static method that gets 
         *  the default toolkit, which contains information about the 
         *  user's system.  This allows me to ask for the user's screen 
         *  dimension as a Dimension object, and I can then find the 
         *  width and height and calculate the placement.  
         */
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int midWidth = (int) ( dim.getWidth() - MYWIDTH ) / 2;
        int midHeight = (int) ( dim.getHeight() - MYHEIGHT ) / 2;
        setLocation( midWidth, midHeight );
        
        /*  For this version, I've decided to use a JPanel, since it will allow me to set a 
         *  border to space widgets in from the edges of the JFrame.
         */
        JPanel jp = new JPanel();
        setBackground( new Color( 25, 55, 25 ) );
        jp.setBorder( BorderFactory.createEmptyBorder( 0, 10, 10, 10) );

        /*  The BorderLayout constructor that takes two ints adds space vertically and
         *  horizontally between elements.
         */
        jp.setLayout( new BorderLayout( 10, 10 ) );

        /*  I'm using a JLabel with HTML text to have multiple lines of information.   */
        instructions = new JLabel( "<html><b>This game will give you a chance to guess the lucky number!" +
                                      "<br>&nbsp;&nbsp;&nbsp;Pick a number between 1 and 100 and type it in... " +
                                      "<br>&nbsp;&nbsp;&nbsp;I'll tell you if you are high or low!" +
				   "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;What's your guess?</b></html>");

        instructions.setFont( new Font( "SansSerif", Font.BOLD, 14 ) );

        /*  Finally, we can now add this object to the JPanel.  */
        jp.add( instructions, BorderLayout.NORTH );

        /*  The JTextField (a single line of text) will take the user's guesses.  I want
         *  the text that's typed to not be all the way to the left, so I've centered it.
         *  I've also added a border to make it stand out for the user.  Finally, to have 
         *  the number processed if the user hits ENTER when finished typing, I've added
         *  an ActionListener to the JTextField.
         */
        input = new JTextField( "", 15 );
        input.setHorizontalAlignment( JTextField.CENTER ); 
        input.setBorder( BorderFactory.createBevelBorder( BevelBorder.LOWERED ) );
        input.setFont( new Font( "SansSerif", Font.BOLD, 14 ) );

        input.addActionListener( this );

        jp.add( input, BorderLayout.CENTER );

        enter = new JButton( "    ENTER    " );
        enter.addActionListener( this );
        jp.add( enter, BorderLayout.EAST );

        feedback = new JTextArea( 3, 25 );
        feedback.setEditable( false );
        feedback.setBorder( BorderFactory.createLineBorder( Color.GRAY, 2 ) );
        feedback.setFont( new Font( "SansSerif", Font.BOLD, 14 ) );
        jp.add( feedback, BorderLayout.SOUTH );

        /*  Having used the JPanel for all the widgets, we now add it to the ContentPane.  By default,
         *  it is put in the center of the BorderLayout.
         */
        getContentPane().add( jp );
        setVisible( true );
    }

    /****
     *  Checks the user's guess against the selected number.
     *
     *  @param   value   The user's guess
     *  @return          0 for a match, -1 if the guess is lower, 1 if higher.
     */
    public int checkGuess( int value )
    {
        if( value == number ) return 0;
        else if( value < number ) return -1;
        else return 1;
    }

    /****
     *  Tests the source of the event, and calls a method to process it if appropriate.
     *
     *  @param  ae   The event generated by the button click
     */
    public void actionPerformed( ActionEvent ae )
    {
        if( ae.getSource() == input || ae.getSource() == enter )
            processGuess();
    }

    /**
     *  This method maintains a counter to track the number of guesses; it also checks the 
     *  outcome of the guess and informs the user to guess higher or lower, or if he/she 
     *  has correctly guessed the number.
     */
    public void processGuess()
    {
        guesses++;
        int comp;  
        
        /*  I'm doing several things at once in the line below.  Broken down, it would be:
         *     String text = input.getText();  //  get text from the JTextField
         *     int value = Integer.parseInt( text );  //  turn it into an int
         *     comp = checkGuess( value );  //  call the method to evaluate it
         *
         *  This version now deals with incorrect input.  We use a try/catch structure to 
         *  do so - we TRY to extract a number and process it.  If we can't extract a number,
         *  a NumberFormatException is thrown which we can catch, and then display an
         *  appropriate error message.  In any case, the input field is cleared.
         */
        try{ 
            comp = checkGuess( Integer.parseInt( input.getText() ) );
            /*  Now we need to decide what to print back to the user in the feedback area...  */
            if( comp == 0 ) {
                feedback.setText( "\n\tYOU WIN!!!  Only " + guesses + " guesses!!\n" );
            }
            else if( comp < 0 ) {
                feedback.setText( "\t  Guess higher!" );
                /*  If the guess wasn't the number, I want to clear the user's input area... */
            }
            else {
                feedback.setText( "\t  Guess lower!" );
            }
        }
        catch( NumberFormatException nfe ) {
            feedback.setText( "   Incorrect input!  Please enter a number!" );
        }
        input.setText( "" );
        /*  This method below will put the focus back into the input area if the
         *  user shifted it to the button when entering a guess.
         */
        input.requestFocusInWindow();
    }
    
    public static void main( String [] args )
    {
        NumGuessGui ng = new NumGuessGui();
    }
}