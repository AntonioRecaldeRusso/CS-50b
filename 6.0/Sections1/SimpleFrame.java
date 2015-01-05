// SimpleFrame.java

import java.awt.*;
import javax.swing.*;

public class SimpleFrame extends JFrame
{
	public SimpleFrame()
	{
		setSize(400, 300);
		setTitle("My Siple Frame");
		setVisible(true);
		setLocation(200, 200);
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		JLabel jl = new JLabel("Welcome to this program!", SwingConstants.CENTER);
		add(jl, BorderLayout.NORTH);
		
		String name = JOptionPane.showInputDialog(null, "What's your name?");
		JLabel jl2 = new JLabel("Glad you are here. " + name);
		add(jl2, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public static void main (String [] args)
	{
		SimpleFrame sf = new SimpleFrame();
	}
}
