import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

class NQueens extends JFrame implements ActionListener
{
	JPanel board64 = new JPanel();
	JPanel menu = new JPanel();
	JPanel buttonsPanel = new JPanel();
	JPanel messagePanel = new JPanel();
	JButton [][] squares = new JButton[8][8];
	JButton search = new JButton("Start!");
	JButton previous = new JButton("Previous Solution");
	JButton next = new JButton("Next Solution");
	JLabel message = new JLabel("Welcome to NQueens.java! Click the \"Start!\" button to begin searching for solutions.");
	
	int solutionIndex;
	
	Board b = new Board();
	
	public NQueens()
	{
		solutionIndex = 0;
		this.setLayout(new BorderLayout());
		
		board64.setLayout(new GridLayout(8,8));
		board64.setPreferredSize(new Dimension(600,600));
		addSquares();
		this.add(board64, BorderLayout.CENTER);
		
		menu.setLayout( new BorderLayout() );
		menu.setPreferredSize(new Dimension(600, 100));
		menu.add(buttonsPanel, BorderLayout.NORTH);
		menu.add(messagePanel, BorderLayout.CENTER);
		
		buttonsPanel.setLayout(new FlowLayout());
		messagePanel.setLayout(new GridBagLayout());
		
		addMenuButtons();
		messagePanel.add(message);
		this.add(menu, BorderLayout.NORTH);
	}
	
	/**
	 * This method adds the JButton squares to the board.
	 */
	private void addSquares()
	{
		for (int row = 0; row < 8; row++)
		{
			for (int column = 0; column < 8; column++)
			{
			squares[row][column] = new JButton();
			
			if ( (row + column) % 2 == 0)
				squares[row][column].setBackground(Color.WHITE);
			
			else
				squares[row][column].setBackground(Color.LIGHT_GRAY);
			
			squares[row][column].setFont(new Font("Serif", Font.PLAIN, 41));
			board64.add(squares[row][column]);
			}
		}
	}
	
	/**
	 * This method adds the buttons to the top menu.
	 */
	private void addMenuButtons()
	{
		buttonsPanel.add(search);
		search.addActionListener(this);
		buttonsPanel.add(previous);
		previous.addActionListener(this);
		previous.setEnabled(false);
		buttonsPanel.add(next);
		next.addActionListener(this);
		next.setEnabled(false);
	}
	
	/**
	 * This method finds all the possible solutions to the 8 Queens game.
	 */
	private void searchSolutions()
	{
		solutionIndex = 0;									// this variable keeps track of which solution are seeing on the board GUI
		b.solve();
		loadSolution(solutionIndex);						// loads a particular solution onto the screen.
	}
	
	/**
	 * This method loads a particular solution onto the board.
	 * @param number
	 */
	private void loadSolution(int number)
	{
		int row, column;
		clearBoard();
		for (int i = 0; i < 8; i++)
		{
			row = b.s[number].coordinates[i].pieceRow;
			column = b.s[number].coordinates[i].pieceColumn;
			squares[row][column].setText("\u265B");
		}
	}
	
	/**
	 * This method clears the board before loading a new solution
	 */
	private void clearBoard()
	{
		for (int row = 0; row < 8; row++)
		{
			for (int column = 0; column < 8; column++)
			{
				squares[row][column].setText("");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == search)
		{
			searchSolutions();
			search.setEnabled(false);
			next.setEnabled(true);
			message.setText( "All 92 solutions found in  " + b.elapsedTime + "  milliseconds!\n   Solution #" + (solutionIndex + 1) );
		}
		
		if (ae.getSource() == previous)
		{
			solutionIndex--;
			next.setEnabled(true);
			loadSolution(solutionIndex);
			if (solutionIndex == 0)
				previous.setEnabled(false);
			message.setText( "Solution #" + (solutionIndex + 1) );
		}
		
		if (ae.getSource() == next)
		{
			solutionIndex++;
			previous.setEnabled(true);
			loadSolution(solutionIndex);
			if (solutionIndex == 91)
				next.setEnabled(false);
			message.setText( "Solution #" + (solutionIndex + 1) );
		}
		
	}
	
	public static void main(String [] args)
	{
		NQueens game = new NQueens();
		game.pack();
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setResizable(false);
		game.setVisible(true);
	}
}