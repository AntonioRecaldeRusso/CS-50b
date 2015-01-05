// Age.java

import javax.swing.JOptionPane;

/**
 *  This Class demonstrates the use of messageDialog and inputDialog methods.
 *  
 * @author Antonio Recalde
 * @version 04/15/2013
 *
 */
public class Age
{
		public static void main (String [] args)
		{
		// this variable stores the value of what I want to say to the user
		String output;				
		
		// the inputDialog returns a string... it will be stored in this variable.
		String input = JOptionPane.showInputDialog("What's your age, cowboy?");

		try 
		{	
			// the string inputed by the user must be parsed in order to be used as an int
			int age = Integer.parseInt(input);	
									
			if (age == 0)
				output = String.format("You did tell me your age properly");	// trying to be humorous
			
			else if (age < 40)
				output = String.format("You are young, you are %d", age);		
			
			else
				output = String.format("You are old enough to be President! So let's go to Hawaii get you a birth certificate!");
				
			// showMessageDialog will display my message.
			JOptionPane.showMessageDialog(null, output, "Problem 1. Pset61", JOptionPane.PLAIN_MESSAGE);
		
		} catch (Exception e) {
			// Murphy's law predicted case.
			JOptionPane.showMessageDialog(null, "You did not enter your age properly", "Age.java", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
