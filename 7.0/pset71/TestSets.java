/**
*  TestSets.java
*  
*  A few more options added to the original.
*  Cardinality(), isSubset()
*  
*  Don't forget to run TestSetGUI. Both this and TestSetGUI use the Bitset class to process sets.
* 
*
*  @version: Last Modified April 27, 2013
*  @author:  Henry Leitner, Antonio Recalde
*/

import java.util.*;

public class TestSets
{
  static void menu()
  {
     System.out.println ("\n");
     System.out.print ("Type 1 to CREATE SET A\n");
     System.out.print ("Type 2 to CREATE SET B\n");
     System.out.print ("Type 3 to CREATE INTERSECTION (A * B)\n");
     System.out.print ("Type 4 to CREATE UNION (A + B)\n");
     System.out.print ("Type 5 to CREATE DIFFERENCE (A - B)\n");				// Difference B - A not required. But added on GUI.
     System.out.print ("Type 6 to DISPLAY CARDINALITY OF SET A \n");			// New.
     System.out.print ("Type 7 to DISPLAY CARDINALITY OF SET B \n");			// New.
     System.out.print ("Type 8 to TEST IF SET A is SUBSET OF SET B \n");		// New.
     System.out.print ("Type 9 to TEST IF SET B is SUBSET OF SET A \n");		// New.
     System.out.print ("Type any OTHER # to EXIT PROGRAM \n\n");
     System.out.print ("Command: ");
  }
  
  public static void main (String [] args) 
  {
     Bitset setA = new Bitset (16);
     Bitset setB = new Bitset (8);
     int command;
    
     Scanner keyboard = new Scanner (System.in);
     do 
     {
         menu();
         
         switch (command = keyboard.nextInt ()) 
         {
            case 1:
              System.out.println ("Type some small integers, each < 16" 
                                 + ", and type DONE when all done!");
              setA.readSet(keyboard);
              System.out.print ("     SET A = " + setA);
              break;

            case 2:
              System.out.println ("Type some small integers, each < 8"
                                 + ", and type DONE when all done!");
              setB.readSet(keyboard);
              System.out.print ("     SET B = " + setB);
              break;

           case 3:
              System.out.print ("     Intersection (A * B) = ");
              System.out.print (setA.intersect(setB));
              break;
           
	       case 4:
              System.out.print ("     Union (A + B) = ");
              System.out.print (setA.union(setB));
              break;

           case 5:
              System.out.print ("     Difference (A - B) = ");
              System.out.print (setA.difference(setB));
              break;
              
           case 6:
        	   System.out.print ("    Cardinality Set A = ");
        	   System.out.print ( setA.cardinality() );
        	   break;
        	   
           case 7:
        	   System.out.print ("    Cardinality Set B = ");
        	   System.out.print ( setB.cardinality() );
        	   break;
        
           case 8:
        	   System.out.print ("    A subSet B = ");
        	   System.out.print ( setA.isSubset(setB));
        	   break;
        	   
           case 9:
        	   System.out.print ("    B subSet A = ");
        	   System.out.print ( setB.isSubset(setA));
        	   break;

           default:  System.exit(0);
        
         }
       } while (command > 0 && command < 10);
  }
}
