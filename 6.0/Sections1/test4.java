import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;




public class test4 extends JFrame implements ActionListener, FocusListener
{
	private JTextField toTextField = new JTextField("");
	private JTextField ccTextField = new JTextField("");
	private JTextField bccTextField = new JTextField("");
	private JTextField subjectTextField = new JTextField("");
	private JComboBox comboBox = new JComboBox();
	
	private JButton sendButton = new JButton("Send");
	private JFrame frame = new JFrame();
	private JTextArea textArea = new JTextArea();
	
	public test4()
	{
		sendButton.addActionListener(this);
		textArea.addFocusListener(this);
			
		comboBox.addItem("email1@email.com");
		comboBox.addItem("email2@email.com");
		comboBox.addItem("email3@email.com");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(700, 600));
		frame.setTitle("New Message");
		frame.setLayout(new BorderLayout());
		
		JPanel northWest = new JPanel(new GridLayout(5, 1));
		northWest.add(new JLabel("To: ", SwingConstants.RIGHT));
		northWest.add(new JLabel("Cc: ", SwingConstants.RIGHT));
		northWest.add(new JLabel("Bcc ", SwingConstants.RIGHT));
		northWest.add(new JLabel("          Subject: ", SwingConstants.RIGHT));
		northWest.add(new JLabel("From: ", SwingConstants.RIGHT));
		
		JPanel northCenter = new JPanel(new GridLayout(5, 1, 0, 10));
		northCenter.add(toTextField);
		northCenter.add(ccTextField);
		northCenter.add(bccTextField);
		northCenter.add(subjectTextField);
		northCenter.add(comboBox);
		
		JPanel north = new JPanel(new BorderLayout());
		north.add(northWest, BorderLayout.WEST);
		north.add(northCenter, BorderLayout.CENTER);
		
		JPanel south = new JPanel(new FlowLayout());
		south.add(sendButton);
		
		frame.add(north, BorderLayout.NORTH);
		frame.add(textArea, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent fe) {
        if ( fe.getSource() == subjectTextField)
        	frame.setTitle(subjectTextField.getText());
    }
	
	public void actionPerformed( ActionEvent ae )
    {
  //      if (ae.getSource() == sendButton)
        	
    }
	
	public static void main (String [] args)
	{
		test4 email = new test4();
	}
}