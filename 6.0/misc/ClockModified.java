// Clock.java


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
  
/**
 * 	This class creates a clock and tells the time based on user input. 
 * 	I was not able to paint dynamically. Rather, this program prints the clock at the specified time once.
 * 
 * @author Antonio Recalde
 *
 */
public class ClockModified extends JPanel implements Runnable {
 
	private Object [] graphics = new Object[1];					// stores the graphic object so it can be accessed by all methods without passing it.
	private final Point CENTER_POINT = 	new Point(300, 300);	// center of  the JFrame
	
	private JTextField hoursTextField = new JTextField();
	private static int h;
	private static int min;
	
	public ClockModified()	
	{
		this.setBackground(Color.WHITE);
		this.setLayout(null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    graphics[0] = g;
    drawClock(g);
    drawMinutesPointer(min);
    drawHoursPointer(h);   
    }
	
	/*
	 * This method draws the circular shape of the clock.. 
	 */
    private void drawClock(Graphics g)
    {
    	int length = 200; 										// this is 
    	double angle;
    	double radian;
    	int x;
    	int y;
    	int endX;
    	int endY;
    	
    	for (int minute = 0; minute < 60; minute++)				// each line represents a minute.
    	{
	    	angle = minute * 6;									// every minute is separated by 6 degrees.. hence 60 minutes equals 360 degrees.
	    	radian= Math.toRadians(angle);						// getting the radian value of each angle
	    	
	    	// This X coordinate will mark the end of the minutes line.. It is found by getting the X coordinate at the end of the arc.
	    	endX = (int)( CENTER_POINT.x + length * Math.sin(radian) );
	    	// this will be the Y coordinate for the minutes line... there will be 60 such lines in the clock.
			endY = (int)( CENTER_POINT.y + length * Math.cos(radian) );
			
			// every 5 lines, the lines should be longer.. hence we pretend the radius is shorter.
			if (minute % 5 == 0)								
			{
				x = (int)( CENTER_POINT.x + (length - 10) * Math.sin(radian) );
				y = (int)( CENTER_POINT.y + (length - 10) * Math.cos(radian) );
			}
			// shorter lines.
			else
			{
		    	x = (int)( CENTER_POINT.x + (length - 5) * Math.sin(radian) );
				y = (int)( CENTER_POINT.y + (length - 5) * Math.cos(radian) );
			}
			g.drawLine(x, y, endX, endY);
    	}
    }
    
    /*
     * 	This method draws the pointer that tells the hour.
     */
    private void drawHoursPointer(int hour)
    {
    	final int length = 100;										//  length of the line
    	double angle;					
    	double radian;
    	int endX;													// X coordinate of where the line will end
    	int endY;													// Y coordinate....
    	int h = hour % 12;
    	
    	
    	// there are 12 hours. 360° / 12 = 30. Hence I multiply by 30. I add +6 because I want 0 or 12 hours to be at the 90° position.. hence I need to skip 3 quadrants.
    	angle = (hour + 6) * 30;										
    	radian = Math.toRadians(angle);
    	
    	// I use negative radians to go clockwise. X, Y are in quadrant II. Thus, degrees increase counterclockwise.
    	endX = (int)( CENTER_POINT.x + (length) * Math.sin(radian * -1) );
    	endY = (int)( CENTER_POINT.x + (length) * Math.cos(radian * -1) );
		// draw line
		((Graphics)graphics[0]).drawLine(CENTER_POINT.x, CENTER_POINT.y, endX, endY);
		// to be able to tell the time when both pointers overlap.
		((Graphics)graphics[0]).drawString("" + h, endX, endY);
    }
    
    /*
     * This method draws the minutes pointer.
     */
    private void drawMinutesPointer(int minute)
    {
    	final int length = 150;										// a little longer than the hours pointer
    	double angle;
    	double radian;
    	int endX;
    	int endY;
    	int mins = minute % 60;
    	
    	angle = (minute + 30) * 6;
    	radian = Math.toRadians(angle);
    	
    	// finding where the line should end.. 
    	endX = (int)( CENTER_POINT.x + (length) * Math.sin(radian * -1) );
		endY = (int)( CENTER_POINT.y + (length) * Math.cos(radian * -1) );
    	((Graphics)graphics[0]).drawLine(CENTER_POINT.x, CENTER_POINT.y, endX, endY);
    	((Graphics)graphics[0]).drawString("" + mins, endX, endY);
    		
    	
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
 
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ClockModified());
        frame.setSize(600,600);
        frame.setVisible(true);
 
    }
  
    public static void main(String[] args) {
    	String hoursStr = JOptionPane.showInputDialog("Enter Hours:");
    	String minutesStr = JOptionPane.showInputDialog("Enter Minutes");
    	
    	h = Integer.parseInt(hoursStr);
    	min = Integer.parseInt(minutesStr);
    	   	
    	/*
    	 *  Originally, I just created a new JFrame object... but as I couldn't run paintComponent more than once, I tried different ways,
    	 *  one such was was by implementing runnable. I decided to simply leave it as even though it still didn't work as I hoped.
    	 */
        EventQueue.invokeLater(new ClockModified());
    }
}
