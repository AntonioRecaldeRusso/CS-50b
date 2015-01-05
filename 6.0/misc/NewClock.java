import java.awt.*;

import javax.swing.*;

public class NewClock extends JFrame
{
	
	
	
	
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int MYWIDTH = 600;
	public static final int MYHEIGHT = 600;
	
	private Object [] gObject = new Object[1];
	private JPanel panel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			paintClock(g);
		}
	};
	private JPanel timePanel = new JPanel();
	private JTextField hoursTextField = new JTextField();
	private JTextField minutesTextField = new JTextField();
	private JLabel hoursLabel = new JLabel("Hours", SwingConstants.RIGHT);
	private JLabel minutesLabel = new JLabel("Minutes:", SwingConstants.RIGHT);
	
	public NewClock()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Clock.java");
		this.setBackground(Color.WHITE);
		
		timePanel.setLayout(new GridLayout(2, 2, 10, 10));
		timePanel.setBackground(Color.WHITE);
		timePanel.add(hoursLabel);
		timePanel.add(hoursTextField);
		timePanel.add(minutesLabel);
		timePanel.add(minutesTextField);
		timePanel.setBounds(400, 20, 190, 80);
		hoursTextField.setBackground(new Color(252, 248, 252));
		minutesTextField.setBackground(new Color(252, 248, 252));
		
		panel.setBackground(Color.WHITE);					// I don't really need to do this because, but nothing wrong with being redundant.
		panel.setPreferredSize(new Dimension(600, 600));
		panel.add(timePanel);
		panel.setLayout(null);
		
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	public void paintHoursPointer(int hours)
	{	
		double angleHours;
		double radianHours;
		int pointerLength = 100;
		final Point CENTER_POINT = new Point ( (MYWIDTH / 2) , (MYHEIGHT / 2) );
		
		if ( !(hours < 0 || hours > 23) )
			return;
		
		else 
		{
			angleHours = (double)(hours * 30);
			radianHours = Math.toRadians(angleHours);
			
			int endX   = (int)( CENTER_POINT.x + pointerLength * Math.sin(radianHours) );
			int endY   = (int)( CENTER_POINT.y + pointerLength * Math.cos(radianHours) );
			
			((Graphics) gObject[0]).drawLine(CENTER_POINT.x, CENTER_POINT.y, endX, endY );
		}
		
		
	}
	
	public void paintClock(Graphics g)
	{
		for (int i = 0; i < 360; i+= 6)
		{
			g.setColor(Color.BLACK);
			drawMinutes(300, 300, 200, i, g);
			g.setColor(Color.WHITE);
			g.fillOval(110, 110, 380, 380);
		}
		for (int j = 0; j < 360; j+= 30)
		{
			g.setColor(Color.BLACK);
			drawMinutes(300, 300, 200, j, g);
			g.setColor(Color.WHITE);
			g.fillOval(120, 120, 360, 360);
		}	
		g.drawLine(300, 300, 300, 200);
	}
	
	public static void drawMinutes(int ax, int ay, int l, int ang, Graphics object)
	{
		Graphics g = object;
		int length = l;
		int x = ax;
		int y = ay;
		int angle = ang;
		double radian = Math.toRadians(angle);
		int endX   = (int)( x + length * Math.sin(radian) );
		int endY   = (int)( y + length * Math.cos(radian) );
		
		g.drawLine(x, y, endX, endY);
	}
	public static void main (String [] args)
	{
		NewClock clockObject = new NewClock();
	}
}
