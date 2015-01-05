// MailLayout.java

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

/**
 * 	This class demonstrates the use of Listener classes as well as some of java's GUI elements.
 * 	The program takes input from the user and outputs it into a file. Additionally, the 
 * 	input from the Subject textField is set as the title of the frame.
 * 
 * @author Antonio Recalde
 * @version 04/015/2013
 *
 */
public class MailLayout extends JFrame implements ActionListener, FocusListener
{
	/* these variables correspond to each element on the layout */
	private JTextField toTextField = new JTextField("");		
	private JTextField ccTextField = new JTextField("");
	private JTextField bccTextField = new JTextField("");
	private JTextField subjectTextField = new JTextField("");
	private JComboBox comboBox = new JComboBox();
	private JButton sendButton = new JButton("Send");
	private JTextArea textArea = new JTextArea();
	
	public MailLayout()
	{
		// Adds an ActionListener to a JButton "sendButton." -"this" refers to the object to which the constructor belongs
		sendButton.addActionListener(this);
		// Adds a FocusListener to the textField that belongs to this object.
		subjectTextField.addFocusListener(this);
		textArea.setLineWrap(true);									// wraps the lines around if their length is greater than the Panel or frame.
		textArea.setWrapStyleWord(true);
		
		loadComboBoxItems();										// this method load each item onto the frame.
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// exits the program when you click on the red X.
		this.setSize(new Dimension(700, 600));						
		this.setTitle("New Message");								// title is "New Message" until something is entered into the subject field.
		this.setLayout(new BorderLayout());		
		
		JPanel northWest = new JPanel(new GridLayout(5, 1));		// Gridbox(5, 1) is like a box layout. Same results either way.
		northWest.add(new JLabel("To: ", SwingConstants.RIGHT));	// SwingConstants.RIGHT makes the label justify "right"
		northWest.add(new JLabel("Cc: ", SwingConstants.RIGHT));
		northWest.add(new JLabel("Bcc ", SwingConstants.RIGHT));
		northWest.add(new JLabel("          Subject: ", SwingConstants.RIGHT));
		northWest.add(new JLabel("From: ", SwingConstants.RIGHT));
		
		JPanel northCenter = new JPanel(new GridLayout(5, 1, 0, 10));// You taught me this! ...vertical gap
		northCenter.add(toTextField);								
		northCenter.add(ccTextField);								// adds this and other components to the panel.
		northCenter.add(bccTextField);
		northCenter.add(subjectTextField);
		northCenter.add(comboBox);
		
		JPanel north = new JPanel(new BorderLayout());				// adding JPanels to JPanels! JPanels are like <div>'s in HTML.
		north.add(northWest, BorderLayout.WEST);
		north.add(northCenter, BorderLayout.CENTER);
		
		JPanel south = new JPanel(new FlowLayout());				// FlowLayout so that the button gets the PreferredSize, and places in the middle. 
		south.add(sendButton);
		
		/* Now adding the JPanels to the Frame. This is like making sandwiches. Bread, mayo, cheese, ham, tomatoes, mayo, Bread. */
		this.add(north, BorderLayout.NORTH);						
		this.add(textArea, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
		
		this.setVisible(true);										// otherwise it won't show in the screen. 
	}
	
	/**
	 * 	This method loads the comboBox items into the comboBox
	 */
	public void loadComboBoxItems()
	{
		comboBox.addItem("asdfa3423451@email.com");
		comboBox.addItem("qwer1234@gmail.com");
		comboBox.addItem("qazwsx0987@yahoo.com");	
	}
	
	/**
	 * This method uses the FocusListener in order to determine when our subject textField acquired focus.
	 * Nothing is excecuted, but this method needs to be present for the compiler to work.
	 */
	public void focusGained(FocusEvent e) {
	}
	
	/**
	 * This method executes calls a method that updates our title to whatever text was written in the subject textField
	 * at the moment it lost focus.
	 */
	public void focusLost(FocusEvent fe) {
        if ( fe.getSource() == subjectTextField)
        {
        	updateTitle();
        }
    }
	
	/**
	 * This method executes code whenever an ActionListener detects an action taking place with a component that has loaded it.
	 */
	public void actionPerformed( ActionEvent ae )
    {
        if( ae.getSource() == sendButton )
        	sendMail();
    }
	
	/**
	 * This method turns the user input into a string object, then outputs it into a file using the PrintWriter class
	 */
	public void sendMail()
	{
		try {
			// declaring our output file
			PrintWriter outfile = new PrintWriter("output.txt");
			// here we create a string object, in order to print it into the outFile. This is better and simpler than using the append function.
			String mailContent = String.format("To:\t\t %s\nCc:\t\t %s\nBcc:\t %s\nSubject: %s\nFrom:\t %s\n\n\n%s", 
					toTextField.getText(), ccTextField.getText(), bccTextField.getText(), subjectTextField.getText(), 
					comboBox.getSelectedItem().toString(), textArea.getText() );
			
			// prints file... yet, only in memory..
			outfile.print(mailContent);
			// after closing the file, the printing really takes place.
			outfile.close();
			
			// here we update the title again as a mere formality. But, also in case that send was while Subject was "onFocus"
			updateTitle();
			// this was not requested... but I felt like it was more natural. Or else you don't see anything happening.
			JOptionPane.showMessageDialog(null, "             Email sent!", "MailLayout", JOptionPane.INFORMATION_MESSAGE);
			
			/* Here I reset all the fields. The title is not affected because the SubjectTextField never gained focus */
			toTextField.setText("");
			ccTextField.setText("");
			bccTextField.setText("");
			subjectTextField.setText("");
			textArea.setText("");
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error writing output file", "MailLayout.java", JOptionPane.PLAIN_MESSAGE);
			
		}
	}
	/**
	 * This method updates the title of our JFrame
	 */
	public void updateTitle()
	{
		String subjectText = subjectTextField.getText();
		// if we delete our subject, we want to make sure we display "New Message" again.
    	if (subjectText.length() < 1 )			
    		this.setTitle("New Message");
    	else
    		this.setTitle(subjectTextField.getText());
	}
	
	public static void main (String [] args)
	{
		MailLayout email = new MailLayout();
	}
}