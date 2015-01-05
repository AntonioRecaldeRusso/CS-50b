/** File PostOffice.java -- simulation of customers and workers
 * 
 *  at a post office
 *  unit 7 lecture notes.
 * Illustrates the use of the Queue class
 * Computer Science CSCI E-50b
 *
 * Last modified: March 21, 2013
 *
 * class Queue implements a "queue" of Objects.
 * It also provides the following "member functions":
 *  Object  delete ( )
 *  boolean empty ( )
 *  void    add (Object x)
 *  int     size ()
 */
     
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class PostOffice
{
    static final int  MIN_PARCELS_PER_CUSTOMER = 1;
    static final int  MAX_PARCELS_PER_CUSTOMER = 7;
    static final int  TIME_TO_GET_OFF_AND_ON_LINE = 4;


    static class Employee                  
    {
       int busy ;                  
       int idle ;                 
       JLabel label;			
    }
      
    static Employee clerks[];  		
                             			
    static JLabel theTime;			
    static JLabel queueLabel;				
//    static MyJFrame jf;					 
    static JTextArea results;			 
    static JSlider speed;				 

    static Queue q;   			 
 
    static double parriv;        	
                 				     
    static int simulationTime = 0,  		 
            delay = 0,
            lineTolerance, 
  	        served = 0,    		 
  	          lost = 0,      		 
               waitSum = 0,   			    
             lengthSum = 0, 		 
             nClerks,       		              			 
            maxClockTicks; 		 
    static String input;		 	 


 static void initializeEverything() 
 { 
     input = JOptionPane.showInputDialog ("# of clerks working at the post office? ");
     nClerks = Integer.parseInt(input);
     input = JOptionPane.showInputDialog ("What is probability of a customer arriving "
                                           + "during any time period? ");
     parriv = Double.valueOf(input).doubleValue(); 

     input = JOptionPane.showInputDialog ("How many time periods do you want to "
                                           + "run the simulation? ");
     maxClockTicks = Integer.parseInt (input);
     input =JOptionPane.showInputDialog ("And what is the longest waiting line a "
                                         + "customer will tolerate? ");
     lineTolerance =  Integer.parseInt(input);
    
     q = new Queue ();  			       
     clerks = new Employee[nClerks];    		  
     for (int i = 0; i < nClerks; i++)   clerks[i] = new PostOffice.Employee();    
                                        	
     ImageIcon m;	
     JFrame jf = new JFrame ("Simulation");
     jf.setSize (650,650);
     jf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
   
     JLabel jl = new JLabel ("Welcome to the Post Office Simulation!");
     jl.setHorizontalAlignment (SwingConstants.CENTER);
     jl.setForeground(Color.RED.darker());
     jl.setFont(new Font ("TimesRoman", Font.BOLD, 24));
    
     JPanel topOfDisplay = new JPanel();
     topOfDisplay.setLayout (new BorderLayout());
     topOfDisplay.add (jl, BorderLayout.NORTH);
     theTime = new JLabel("Simulation Time: " + simulationTime);
     theTime.setHorizontalAlignment (SwingConstants.CENTER);
     theTime.setFont (new Font ("TimesRoman", Font.BOLD, 18));
     topOfDisplay.add (theTime, BorderLayout.SOUTH);
     jf.add (topOfDisplay, BorderLayout.NORTH);
	
     JPanel bottomOfDisplay = new JPanel();
     bottomOfDisplay.setLayout(new BorderLayout());
	
     queueLabel = new JLabel ("Queue: ");
     queueLabel.setOpaque (true);
     queueLabel.setFont (new Font("TimesRoman", Font.BOLD, 18));
     queueLabel.setForeground (Color.GREEN.darker().darker());
     bottomOfDisplay.add (queueLabel, BorderLayout.SOUTH);
 
     JPanel clerkPanel = new JPanel();
     clerkPanel.setBackground(Color.YELLOW);
     clerkPanel.setOpaque(true);
     clerkPanel.setLayout(new GridLayout(nClerks, 1, 5, 5));
    
     speed = new JSlider( SwingConstants.HORIZONTAL, 0, 200, 10 );
     speed.setMajorTickSpacing( 10 );
     speed.setPaintTicks( true );
     speed.addChangeListener (
       new ChangeListener() 
       {
            public void stateChanged( ChangeEvent e )
            {
               delay = speed.getValue()* 10 ;
            }
        }
                             );
     bottomOfDisplay.add (speed,SwingConstants.NORTH);
     JLabel delayLabel = new JLabel("Delay");
     bottomOfDisplay.add (delayLabel, BorderLayout.WEST);
     jf.add (bottomOfDisplay,BorderLayout.SOUTH);

    
     for (int i=0; i < nClerks; i++ ) 
     { 
         clerks[i].label=new JLabel("Clerk " + i);
         clerks[i].label.setBackground(Color.PINK); 
         clerks[i].label.setFont(new Font("TimesRoman", Font.BOLD, 18));
         clerks[i].label.setForeground(Color.BLACK);
         clerks[i].label.setOpaque(true);
         m = new ImageIcon("clerk"+(i+1)+".gif");
         clerks[i].label.setIcon(m);
         clerks[i].label.setIconTextGap(0);
        
         clerkPanel.add(clerks[i].label);
     }
     jf.add(clerkPanel, BorderLayout.WEST);
     results = new JTextArea(30, 14);
     results.setBackground(Color.YELLOW); 
     results.setLineWrap(true);
     results.setWrapStyleWord(true);
     results.setFont(new Font("TimesRoman", Font.BOLD, 14));
     results.setForeground(Color.BLUE.darker());
     jf.add (new JScrollPane (results), BorderLayout.EAST);
     jf.setVisible(true);
}                                    
  

 static int removeCustomerFromFrontOfQueue (Queue q)
 {   
      Object arrivalTime;
      if ((arrivalTime = q.delete()) == null)
      {
          System.out.println ("How is this possible? Queue Underflow!!!"); 
          return -1;
      }
      else return ((Integer)arrivalTime).intValue();
 }                         
 

  static void displayQueue (Queue q)
  {
   
    String s= "Queue:  ";
    for (int i = 0; i < q.size(); i++)
    {
        Object nextInLine = q.delete(); 
        s = s + ((Integer) nextInLine).intValue() + " ";
        q.add (nextInLine);                         
    }
    queueLabel.setText(s);
    try {Thread.sleep(25); } 
    catch (InterruptedException t) { }   
  }
   

  static void displayClerk (int i)
  {   
      clerks[i].label.setText(" Clerk #" + (i+1) + " is busy for another " 
                                + clerks[i].busy + " time periods" );    
  }


  static void customersArriveAndLeave()
  {      
      if (Math.random() <= parriv)       	   // did a customer arrive?
      {	 
          if (q.size() < lineTolerance)  		  
          {      					 
              q.add (simulationTime ); // place customer on line
              displayQueue (q);
          }
          else                      	 
          {
              results.append ("Queue too long!  Customer leaving in anger!\n");
              lost++;
        
          }
      }

      for (int i = 0; i < nClerks; i++)
      {     	 
          if (clerks[i].busy > 0)       		 
          {  
              clerks[i].busy-- ;         	  
              displayClerk (i);
          }
          else if (q.empty()) clerks[i].idle++;   
          else
          {                   
              waitSum += (simulationTime - removeCustomerFromFrontOfQueue(q));
              displayQueue(q); 
              served++;                       
            					  
              int need = (int) (Math.random() * 
                                (MAX_PARCELS_PER_CUSTOMER- MIN_PARCELS_PER_CUSTOMER+1));   
              clerks[i].busy = TIME_TO_GET_OFF_AND_ON_LINE + need / 2;
              displayClerk (i);
          }  
      }
  }        



 static void reportResults ()
 {    
     int idle = 0;
 
     for (int i = 0; i < nClerks; i++)  idle += clerks[i].idle;
     displayQueue (q);

     results.append ("\nTIME RAN OUT at simulation time = " + 
                        simulationTime + "\n\n");
     results.append ("We served " + served + " customer[s], and ");
     results.append ("we lost "  + lost + " customer[s].\n");
     results.append ("The clerks were idle for an average of " 
                        + (double)idle/nClerks + " time period[s],\n");
     results.append (" and there were " + q.size()
                        + " customers left on line!\n");
     results.append ("The average wait by a customer was  "
                        + (double) waitSum / served + " time units, and\n");
     results.append ("The average queue length was "
                        + (double) lengthSum / simulationTime + " customers!\n");
 } 


  public static void main (String [] args)         // the "main program" 
  {
     initializeEverything();           
     do
      {                       			
	   simulationTime++;    			       
           theTime.setText("Simulation Time: " + simulationTime);
        											
           try { Thread.sleep (delay); }           // pause
           catch  (InterruptedException e) { };

           customersArriveAndLeave();   	   // where the action happens!	 
        
           lengthSum += q.size();  			 
      } while (simulationTime < maxClockTicks);
      reportResults();
  } 
  
}
