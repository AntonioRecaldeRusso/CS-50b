// MyPanel.java

import java.awt.*;
import javax.swing.*;

/**
 * 	This class creates JPanels to be used by both Prob4 and Prob5. 
 * 
 * @author Antonio Recalde
 * @version 04/15/2013
 *
 */
public class MyPanel extends JPanel
{
	/* 
	 * I'm using arrays to hold the values of size and the coordinates. This allows me to draw many times
	 * whilst calling the paintComponent() method only once, and also calling the constructor a single time.
	 */
	static int [] size;
	static int [] alphaX;
	static int [] alphaY;
	
	public MyPanel()	{}							// necessary to compile
	public MyPanel(int [] x, int [] y, int [] imageSize, Dimension dim)
	{	
		/* because these are arrays, all data is passed in one run	*/	
		alphaX = x;									
		alphaY = y;
		size = imageSize;
		super.setPreferredSize(dim);
	}
	
	public void paintComponent(Graphics g)
	{
		this.setBackground(Color.CYAN);
		super.paintComponent(g);
		
		// draw as many times as x coordinates in the array.. Naturally, if there are three values in the array, there are three figures to draw.
		for (int i = 0; i < alphaX.length; i++)
		{
			final int SQUARES = 5;						// the image is made of 5 rectangle squares
			int squareLength = 0;						// Initializing this variable. It will hold the value of the length or our rectangle
			int segment = size[i] / SQUARES;			// distance between each vertical line...
			g.setColor(Color.RED);
			
			// this for loop controls the number of times we keep drawing rectangles. It's always 5 for this figure.
			for (int j = 0; j < SQUARES; j++)
			{
				squareLength+= segment;					// each square is +segment longer then the previous.
				g.drawRect(alphaX[i], alphaY[i], squareLength, squareLength);
				
			}
			g.setColor(Color.BLACK);
			// after drawing the 5 squares, draw this line once, for each figure.
			g.drawLine(alphaX[i], alphaY[i], alphaX[i] + squareLength, alphaY[i] + squareLength);
		}
	}
}
