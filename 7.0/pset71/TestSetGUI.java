// TestSetGUI.java

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class provides a Gui for the purpose of testing the Bitset class.
 * 
 * @author Antonio Recalde
 * @version 04/27/2013
 *
 */
public class TestSetGUI extends JPanel implements ActionListener
{
	/*
	 *  Creating Bitset objects to store sets.
	 */
	Bitset setA = new Bitset (16);
    Bitset setB = new Bitset (8);
    
    public final int NUMBER_OF_BUTTONS = 10;				// the Gui has 10 buttons.
    
	private JPanel centerPanel = new JPanel();
	private JPanel centerTop = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel centerCenter = new JPanel();
	private JPanel centerBottom = new JPanel();
	private JPanel setDisplay = new JPanel();
	
	private JLabel result = new JLabel("*** Bitset.java ***");
	private JLabel setALabel = new JLabel("Set A : (max < 16)", SwingConstants.CENTER);
	private JLabel setBLabel = new JLabel("Set B : (max < 8 )", SwingConstants.CENTER);
	private JLabel setAValue = new JLabel("null", SwingConstants.CENTER);
	private JLabel setBValue = new JLabel("null", SwingConstants.CENTER);
	
	// This array will store the buttons we'll use. This way we can use a for loop to initialize them.
	private JButton [] buttons = new JButton[NUMBER_OF_BUTTONS];
	// This array holds the text values for each button according to their index.
	private String [] buttonsText = {"Create Set A", "Create Set B", "A \u2229 B", "A \u222A B", "A \u224F B", "B \u224F A", "| A |", "| B |", "A \u2282 B", "B \u2282 A" };

	private JTextField input = new JTextField();
	
	public TestSetGUI()
	{
		this.setBackground(Color.lightGray);
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 600));			// setPreferrredSize, so we can use the pack() function.
		
		this.add(leftPanel, BorderLayout.WEST);					// adds buttons to the left.
		leftPanel.setLayout(new GridLayout(10, 1, 5, 5));		// 10 buttons vertically.. 5 pixels of margin on each side.
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
		
		// this for loop initializes the buttons.
		for (int i = 0; i < NUMBER_OF_BUTTONS; i++)
		{
			buttons[i] = new JButton();
			buttons[i].setText(buttonsText[i]);
			buttons[i].addActionListener(this);
			leftPanel.add(buttons[i]);
		}
		
		this.add(rightPanel, BorderLayout.EAST);				// This makes the center more to the left.
		rightPanel.setPreferredSize(new Dimension(25, 500));	// the y-coordinate does not really matter as the panel will be stretched vertically.
		
		this.add(centerPanel, BorderLayout.CENTER);				// adding center panel. It will be divided into top, center, and bottom.
		centerPanel.setLayout(new BorderLayout());
		
		centerPanel.add(centerTop, BorderLayout.NORTH);		
		centerTop.setPreferredSize(new Dimension(500, 150));
		centerTop.setLayout(new GridBagLayout());				// Using GridBadLayout() thus auto-centers the JLabel "result."
		centerTop.add(result);
		
		
		centerPanel.add(centerCenter, BorderLayout.CENTER);		// centerCenter added. This will be the center of our center panel.
		
		centerCenter.setLayout(new BorderLayout());				
		centerCenter.add(setDisplay, BorderLayout.NORTH);		// adding the setDisplay panel. It will provide info about the inputs.
		setDisplay.setLayout(new GridLayout(7, 1));				// GridLayout... 7 elements, 1 column.
		setDisplay.setPreferredSize(new Dimension(100, 250));
		setDisplay.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		setDisplay.add(setALabel);								
		setDisplay.add(setAValue);								// This will display the current value of setA
		setDisplay.add(new JLabel(""));							// This is like \n\n.. provides an empty line to separate components
		setDisplay.add(setBLabel);								
		setDisplay.add(setBValue);								// This will display the current value of setB
		setDisplay.add(new JLabel(""));							// seemingly empty space for separation.
		setDisplay.add(new JLabel("Enter data below, separated by spaces:", SwingConstants.CENTER));
		
		centerCenter.add(centerBottom, BorderLayout.SOUTH);		// centerBottom added.
		centerBottom.setLayout(null);							// Null layout, so the JTextField doesn't stretch horizontally.
		centerBottom.setPreferredSize(new Dimension(0, 220));	// only the y-coordinate matters in BorderLayout.SOUTH
		centerBottom.add(input);								// JTextField for input added.
		input.setBounds(115, 25, 200, 25);						// sets the bounds of our JTextField.
	}
	
	public static void main (String [] args)
	{
		JFrame frame = new JFrame("Bitset.java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add( new TestSetGUI() );
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * This method sets up the functions of each button
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Bitset temp;
		 /*
		  * Buttons are in an array. They are also displayed from top to bottom in index order on the left of the frame.
		  */
		
		// buttons[0] = create setA
		if (ae.getSource() == buttons[0])
		{
			// if the result label says error, reset it to empty every time a new set is created
			if (result.getText() == "ERROR")					
				result.setText("");	
			
			try {
				setA.readSet(input.getText());					// if the input doesn't parse correctly, catch!
			} catch(NumberFormatException e) {
				result.setText("ERROR");						// there was an error.. say ERROR, and continue.
			}
			input.setText("");									// reset the JTextField to empty.
			setAValue.setText("" + setA);						// display the current values in setA
		}
		
		// buttons[1] = create setB
		if (ae.getSource() == buttons[1])
		{
			if (result.getText() == "ERROR")					// reset result every time we create a set
				result.setText("");								
			
			try {
				setB.readSet(input.getText());
			} catch(NumberFormatException e) {
				result.setText("ERROR");
			}
			input.setText("");
			setBValue.setText("" + setB);
		}
		
		// button[2] = A INTERSECTION B
		if (ae.getSource() == buttons[2])
		{
			temp = setA.intersect(setB);						// store the value of the operation somewhere.. or else we can't return it.
			result.setText("A \u2229 B = " + temp);				// setting the result to the value stored in temp.
		}
		
		// buttons[3] = A UNION B
		if (ae.getSource() == buttons[3])
		{
			temp = setA.union(setB);							// store in temp for further retrieval
			result.setText("A \u222A B = " + temp);				// display temp on the screen
		}
		
		// buttons[4] = A DIFFERENCE B
		if (ae.getSource() == buttons[4])
		{
			temp = setA.difference(setB);						// same as the previous button
			result.setText("A \u224F B = " + temp);				// same.
		}
		
		// buttons[5] B DIFFERENCE A 	...Oddly, the original command line program skipped this. But I gladly incorporated it here.
		if (ae.getSource() == buttons[5])
		{
			temp = setB.difference(setA);
			result.setText("A \u224F B = " + temp);
		}
		
		// buttons[6] = CARDINALITY A
		if (ae.getSource() == buttons[6])
			result.setText("| A | = " + setA.cardinality());
		
		// buttons[7] = CARDINALITY B
		if (ae.getSource() == buttons[7])
			result.setText("| B | = " + setB.cardinality());
		
		// buttons[8] = A SUBSET B
		if (ae.getSource() == buttons[8])
			result.setText("A \u2282 B = " + setA.isSubset(setB));
		
		// buttons[9] = B SUBSET A
		if (ae.getSource() == buttons[9])
			result.setText("B \u2282 A = " + setB.isSubset(setA));
	}
}
