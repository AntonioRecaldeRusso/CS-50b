import java.awt.*;
import javax.swing.*;

public class BmiGui2 
{
	private JFrame frame;
	private JTextField heightField;
	private JTextField weightField;
	private JLabel bmiLabel;
	private JButton computeButton;
	
	public BmiGui2()
	{
		heightField = new JTextField(5);
		weightField = new JTextField(5);
		bmiLabel = new JLabel(" Type you height and weight ");
		computeButton = new JButton("Compute");
		
		JPanel north = new JPanel(new GridLayout(2, 2));
		north.add(new JLabel("Height: "));
		north.add(heightField);
		north.add(new JLabel("Weight: "));
		north.add(weightField);
		
		JFrame frame = new JFrame("BMI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(north, BorderLayout.NORTH);
		frame.add(bmiLabel, BorderLayout.CENTER);
		frame.add(computeButton, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
	}
}

