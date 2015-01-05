// Calculator.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * 	This class creates a JFrame object, then loads it with a JTextArea, and JButtons in order to reproduce the
 * 	functions of a calculator.
 * 
 * @author Antonio Recalde
 * @version 04/15/2013
 *
 */
public class Calculator extends JFrame implements ActionListener
{
	
	private Font displayFont = new Font("Arial", Font.PLAIN, 36);	// font for the JTextField.
	private int buttonNumber = 0;									// this is a counter.
	private int posX = 0;											// posX represents the X coordinate of a given button.
	private int posY = 0;											// posY represents the Y coordinate of a given button.
	private int buttonLength = 70;									// this is how long the sides of the buttons will be
	
	// squaredButtons are squared in shape. In total this calculator has 14 such buttons.
	private Dimension squaredButtons = new Dimension(buttonLength, buttonLength);
	// button15 is longer north to south. "+ button"
	private Dimension button15 = new Dimension(buttonLength, buttonLength * 2);
	// button16 is the wider button.. "0 button"
	private Dimension button16 = new Dimension(buttonLength * 2, buttonLength);
	
	private JButton plusMinus = new JButton("+/-");					// this button will toggle the negative sign.			
	private JButton [] buttons = new JButton[18];					// this array will hold the calculator's buttons
	private String buttonCharacters = "C\u221A/*789-456+123=0.";	// I will use this string to set the text of the buttons
	private JTextField display = new JTextField();					
	
	private JPanel displayPanel = new JPanel();						
	private JPanel displayPanelWest = new JPanel();
	private JPanel buttonsPanel = new JPanel();
	
	// getting Screen size to position the JFrame in the center of the screen.
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int MYWIDTH = 287;							/* these are the height and width of my frame. I set them manually so that... */
    public static final int MYHEIGHT = 478;							/* .... later I can spawn the JFrame in the middle of the screen */
    
    private double result = 0;										// this variable will hold the results of our calculations
    
    
    /*
     *  this means that a NewValue will be entered as input in the calculator. When the screen is blank, the next number we enter will
     *  be the first index of a new number value. But, if we are already entering numbers while not using any arithmetic operators, the
     *  string that represents the number should keep growing
     *  E.g. after using the + operator, we want the program to know that the new integer we enter is the start of a new number.. 
     *  or else, 2+3+2 would be 2+23+232
     */
    private boolean newNumber = true;								
    private boolean noError = true;									// means there is no runtime errors. Eg. if you do 1 + a =, the screen will say error.
    
    private String currentOperation = "=";							// what we are calculating right now.			
    private String resultStr;
    JLabel operator = new JLabel(" ");								// displays what operation is going to take place
    DecimalFormat df = new DecimalFormat("#.##########");			// this decimal format ensures that no results have more than 10 decimal number. 
    
	
    
    
    public static void main (String [] args)
	{
		Calculator calculatorObject = new Calculator();
	}
	
	public Calculator()
	{
		this.setTitle("Calculator.java");							
		this.setSize(new Dimension(MYWIDTH, MYHEIGHT));
		this.setResizable(false);									
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* locating the JFrame in the center of the screen */
        int midWidth = (int) ( dim.getWidth() - MYWIDTH ) / 2;
        int midHeight = (int) ( dim.getHeight() - MYHEIGHT ) / 2;
        setLocation( midWidth, midHeight );
        
        plusMinus.addActionListener(this);
		
        operator.setFont(displayFont);
        // to the left of the display textField, there will be a JLabel and a JButon one on top of the other
        displayPanelWest.setLayout( new GridLayout(2, 1) );			
        displayPanelWest.setBackground(Color.YELLOW);
        displayPanelWest.add(operator);								// for displaying the current operator we are using
        displayPanelWest.add(plusMinus);							// toggle negative symbol and value of the current value in the JTextField
        displayPanel.setBackground(Color.YELLOW);
        // using BorderLayout so that the JTextField in the center occupies all the space not taken by other components
		displayPanel.setLayout( new BorderLayout() );				
		displayPanel.add(display, BorderLayout.CENTER);
		displayPanel.add(displayPanelWest, BorderLayout.WEST);
		
		/*
		 * setting preferredSize for JPanel containing our display TextField. The height will be 100, but because it is set to be in the center, 
		 * but the width will be dynamically adjusted depending on the width of the JPanel
		 */
		display.setPreferredSize( new Dimension(0,100) );
		display.setBackground(Color.YELLOW);
		display.setFont(displayFont);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setBorder(null);									// means no border.
		
		
		// setting the buttonsPanel to null.. In my opinion using a null Layout is much better for creating a grid with buttons of various sizes.
		buttonsPanel.setLayout(null);
		// the bounds are from 0, 0 of the JPanel, spanning 4 buttons wide, and 5 buttons high.
		buttonsPanel.setBounds(0, 0, buttonLength * 4, buttonLength * 5);
		
		loadButtons();												// this method loads the button into the JPanel
		
		this.add(displayPanel, BorderLayout.NORTH);
		this.add(buttonsPanel, BorderLayout.CENTER);
		this.setVisible(true);	
	}
	
	/*
	 * This method loads the buttons of the calculator into the JPanel
	 */
	private void loadButtons() 
	{
		for (int i = 0; i < 5; i++)									// for each column...
		{
			// This is like the hitting the return button. After finishing loading each row, return to the 0 position of the X axis.
			posX = 0;												
			for (int j = 0; j < 4; j++)								// each row...
			{
				if (i == 4 && j > 1)								// the last row only has 2 buttons... Hence break after j > 1.
					break;
				
				// place a specific button, at a specified position.
				placeButtons(buttons[buttonNumber], buttonNumber, posX);
				// after a button has been loaded, the next position should be a button length to the right.
				posX += squaredButtons.width;						
				this.buttonsPanel.add(buttons[buttonNumber]);
				buttonNumber++;
				
			}
			// after finishing each row, let's go to the next line. In a way, this is no different than drawing a grid in the command prompt.
			posY += squaredButtons.height;
		}
	}
	
	/*
	 * This method positions a JButton into the buttonsPanel
	 */
	private void placeButtons(JButton button, int buttonNumber, int posX)
	{	// the buttonCharacters String is already ordered in such a way that its CharAt(0) corresponds to buttons[0]... and so on.
		buttons[buttonNumber] = new JButton("" + buttonCharacters.charAt(buttonNumber));
		buttons[buttonNumber].addActionListener(this);										
		buttons[buttonNumber].setLocation(posX, posY);
		
		if (buttonNumber == 15)			{ buttons[buttonNumber].setSize(button15);		}	// ...these buttons have special dimensions. 
		else if (buttonNumber == 16)	{ buttons[buttonNumber].setSize(button16);
										  this.posX += squaredButtons.width;					} // instead of using pointers.. Java is great!!
		else							{ buttons[buttonNumber].setSize(squaredButtons);}	
	}
	
	/*
	 * This method updates the current mathematical operator to the screen
	 */
	private void updateOperatorLabel(String cmd)
	{
		operator.setText("" + cmd);
	}
	
	/*
	 * this method makes the arithmetics calculations requested based on the current operator being used.
	 */
	private void process(double num, String cmd)
	  {
		if (cmd.equals("\u221A"))									
			sqrt(num);												
		
		else if (currentOperation.equals("+"))
			result += num;
		else if (currentOperation.equals("-"))
			result -= num;
		else if (currentOperation.equals("*"))
			result *= num;
		else if (currentOperation.equals("/"))
			result /= num;
		else if (currentOperation.equals("="))						
			result = num;
		
		resultStr = df.format(result);								// Decimal Format #,##########
		display.setText(resultStr);									
	   }
	/*
	 * This method toggles the sign of the value currently in the display JText.
	 */
	private void toggleSign()
	{
		Double tempResult;			
		tempResult = Double.parseDouble(display.getText());
		tempResult *= -1;
		resultStr = df.format(tempResult);
		display.setText(resultStr);
		return;
	}
	
	/*
	 *  This method calculates the square root of a number.
	 */
	private void sqrt(double num)
	{
		result = Math.sqrt(num);
		resultStr = df.format(result);								// Decimal Format #,##########
		display.setText(resultStr);
		operator.setText(" ");
		return;
	}
	
	/*
	 * 	This method produces the effects of the Clear button in a calculator. It resets every value as they were at the moment
	 * 	the program started. Additionally, this button allows to reset the screen when there is a runtime error.
	 */
	private void clear()
	{
		result = 0;
		newNumber = true;
		currentOperation = "=";
		display.setText("");
		display.setBackground(Color.YELLOW);
		updateOperatorLabel(" ");
		noError = true;

	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String cmd = ae.getActionCommand();
		
		/*
		 *  The first thing to check is if the ClearButton was clicked. This allows us to reset the calculator in case there has been an error,
		 *  but it also resets all other values associated with the calculator, as they were at the start of the program.
		 */
		if (cmd.equals("C"))									
		{
			clear();
			return;
		}
		// If there has been a runtime error, all the functions of the calculator are disabled, except the clear button.
		if (noError)
		{
			try {
				if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals("."))
				{
					// newNumber signals whether the calculator is taking a new value at this point.. 
					if (newNumber)											
					{
						// in that case set the display text to whatever was just entered.. discard the rest.
						display.setText(cmd);
						// since something is now being entered, the calculator is not taking a new number anymore... 
						newNumber = false;
					}
					else
					{
						// but, if the program is not expecting a new value, append the last input to what is already in the display field.
						display.setText(display.getText() + cmd);
						newNumber = false;			
					}
					
				}
				// else, if we didn't enter a number or '.'   ... we probably entered an operator.. e.g. +, -. =, etc.
				else
				{
					/* if we entered an operator when the program is expenting the start of a new number value... then, we only
					 * care about whether a square root operation, or a toggle sign operation were requested. Both these buttons must work even
					 * if there are currently no numbers to be analized. That is, because sqrt(9) should behave the same way as 9(sqrt). The order shouldn't matter.
					 */
					if (newNumber)
					{
						if (cmd.equals("\u221A"))
							sqrt(result);
						
						else if (cmd.equals("+/-"))
							toggleSign();
						
						// if it is not a sqrt or +/-, then let's set the current expected operation to the value just entered.
						else
						{ 
							currentOperation = cmd;
							updateOperatorLabel(cmd);						// displays on the screen that the operator changed, or not.
						}		
					}
					// if it is not a newNumber, and we entered an operator, we want to perform the operation...
					else
					{
						if (cmd.equals("+/-"))
							toggleSign();									// you should be able to change the sign at this point too...
						
						
						// else, calculate the operation... 
						else
						{
						double number = Double.parseDouble(display.getText());
						updateOperatorLabel(cmd);
						process(number, cmd);								// calculating... This method is the real calculator
						currentOperation = cmd;
						newNumber = true;
						}
					}
				}
			} catch (NumberFormatException e) {
				display.setText("Error  ");									// if there is an error, let it be known! Only the Clear button resets everything
				display.setBackground(Color.RED);
				noError = false;
				return;
			}
		}
	}
}