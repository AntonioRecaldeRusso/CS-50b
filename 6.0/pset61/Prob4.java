// Prob4.java
import java.awt.*;
import javax.swing.*;

/**
 * 	This class draws a type of fractal consisting of squares and a line onto a JFrame
 * 	This class makes use of another class "MyPanel" where the JPanels are created.
 * @author Antonio Recalde
 * @version 04/15/2013
 *
 */
public class Prob4
{
	public static void main (String [] args)
	{
		/*
		 * I decided to use three different arrays because this way it is easier to think of the coordernates in terms of
		 * their position.... if instead it were { 50, 50, 100 }, or even a method (50, 50, 100)  more attention would be 
		 * required to differentiate size from x from y.	
		 */
		int [] x = { 50 };
		int [] y = { 50 };
		int [] size = { 100 };
		
		
		Dimension dimension = new Dimension (300, 200);			// dimensions for our JPanel 
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Prob4.java");
		
		// creating a new JPanel Object with x, y coordenates, imageSize, and dimensions of the JPanel.
		MyPanel panel = new MyPanel(x, y, size, dimension);		
		
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();											// pack.
		frame.setVisible(true);
	}
}
