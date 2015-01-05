import java.awt.*;
import javax.swing.*;

public class SimleFrame2
{
	public static void main (String [] args)
	{
		JFrame frame = new JFrame();
		frame.setForeground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(new Point(10, 50));
		frame.setSize(new Dimension(900, 500));
		frame.setTitle("A Frame");
		frame.setVisible(true);
		
		JLabel label = new JLabel("This is a label");
	}
}
