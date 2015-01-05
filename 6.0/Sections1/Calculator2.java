import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class Calculator2 extends JFrame
{
	private JButton [] buttons = new JButton[18];
	private JTextField display = new JTextField("");
	private Font displayFont = new Font("Arial", Font.PLAIN, 36);
	private double result = 0;
	private  String [] buttonsText = { "C", "\u221A", "/", "7", "8", "9", "4", "5", "6", "1", "2", "3", "*", "-", "+", "=" };
	
	public Calculator2()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(350, 450));
		this.setLayout(null);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		int x = 0;
		int y = 0;
		for (int i = 0; i < 17; i++)
		{
			buttons[i] = new JButton("" + i);
			buttons[i].setBounds(x, y, 50, 50);
			x += 50;
			y += 50;
			panel.add(buttons[i]);
		}
		this.add(panel);
		
		/*
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout( new GridLayout(5, 4) );
		
		for (int i = 0; i < 17; i++)
		{
			buttons[i] = new JButton("" + i);
			buttonsPanel.add(buttons[i]);
		}
		this.add(buttonsPanel);
		
		Point zeroLocation = buttons[16].getLocation();
		Dimension dim = buttons[1].getSize();
		JButton zeroButton = new JButton();
		zeroButton.setSize(dim.width * 2, dim.height);
		zeroButton.setBounds(zeroLocation.x, zeroLocation.y, dim.width, dim.height);
		this.add(zeroButton);
		
		
		
		
		
		
	//	this.add(displayPanel, BorderLayout.NORTH);
		
		buttonsPanel.setSize(new Dimension(300, 400));
		buttonsPanel.setLocation(0, 0);
		
		
		this.add(buttonsPanel);
		this.add(zeroButton);				*/
		this.setVisible(true);
		
	}

	public static void main (String [] args)
	{
		Calculator2 cal = new Calculator2();
	}
}