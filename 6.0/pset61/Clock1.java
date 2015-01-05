import java.awt.*;
import javax.swing.*;
import java.math.*;

public class Clock1
{
	public Clock()
	{
		DrawingPanel panel = new DrawingPanel(600, 600);
		Graphics g = panel.getGraphics();
		
		loadTextFields();
		
		for (int i = 0; i < 360; i+= 6)
		{
			g.setColor(Color.BLACK);
			drawMinutes(300, 300, 200, i, g);
			g.setColor(Color.WHITE);
			g.fillOval(110, 110, 380, 380);
		}
		for (int j = 0; j < 360; j+= 30)
		{
			g.setColor(Color.BLACK);
			drawMinutes(300, 300, 200, j, g);
			g.setColor(Color.WHITE);
			g.fillOval(120, 120, 360, 360);
		}	
	}
	public static void main (String [] args)
	{
		Clock clockObject = new Clock();
	}
	
	public static void loadTextFields()
	{
		
	}
	public static void drawMinutes(int ax, int ay, int l, int ang, Graphics object)
	{
		Graphics g = object;
		int length = l;
		int x = ax;
		int y = ay;
		int angle = ang;
		double radian = Math.toRadians(angle);
		int endX   = (int)( x + length * Math.sin(radian) );
		int endY   = (int)( y + length * Math.cos(radian) );
		
		g.drawLine(x, y, endX, endY);
	}
}