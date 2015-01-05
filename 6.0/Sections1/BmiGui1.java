import java.awt.*;
import javax.swing.*;

public class BmiGui1 
{
	public static void main (String [] args)
	{
		JTextField height = new JTextField(5);
		JTextField weight = new JTextField(5);
		JLabel bmiLabel = new JLabel("Type your height and weight");
		JButton button = new JButton("Compute");
		
		JPanel north = new JPanel(new GridLayout(2, 2));
		north.add(new JLabel("Height: "));
		north.add(height);
		north.add(new JLabel("Weight: "));
		north.add(weight);
		
		JFrame frame = new JFrame("BMI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(north, BorderLayout.NORTH);
		frame.add(bmiLabel, BorderLayout.CENTER);
		frame.add(button, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
