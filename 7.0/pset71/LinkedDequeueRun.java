// LinkedDequeueRun.java

import java.io.IOException;												// for in.read()

/**
 * This class runs a main program to test the LinkedDequeue.java class' methods.
 * It creates two LinkedDequeue list objects, and tests each method subsequently.
 * This class is executed in conjunction with the "LinkedDequeue" class and the "Foobar" class
 * 
 * @author Antonio Recalde
 * @version 04/27/2013
 *
 */
public class LinkedDequeueRun

{
	public static void main (String [] args)
	{
	System.out.println("Creating list: list1\n");
	LinkedDequeue list1 = new LinkedDequeue();								// Creating list1;
	
	System.out.println("Adding 4 elements using headAdd() method: ...");
	addNewHead(list1);														// adding elements with headAdd()
	System.out.println("\nCurrent list size: " + list1.size());
	
	System.out.println("\nRemoving all Objects and testing methods: headPeek, headRemove(), size(), toString()");
	
	// The for loop below test the list with each method in many conditions: When empty, with only 1 elements, and multiple elements.
	for (int i = 0; i < 5; i++)
	{
		System.out.println("headPeek():\t" + list1.headPeek());
		System.out.println("headRemove():\t" + list1.headRemove());
		System.out.println("Size(): \t" + list1.size());
		System.out.println(list1);
	}
	
	System.out.println("\n\n------------------------------Press ENTER key to continue------------------------------\n");
	try { System.in.read(); } 												// trying to get the effect of system("pause") from C.
	catch (IOException e) {	e.printStackTrace(); }
	
	
	System.out.println("Creating new list: list2\n");
	LinkedDequeue list2 = new LinkedDequeue();								// creating second list.
	
	System.out.println("Adding 4 elements using tailAdd() method: ...");
	addNewTail(list2);														// now adding elements using the tailAdd() method.
	System.out.println("Current list size: " + list1.size());
	System.out.println(list2);
		
	System.out.println("\nRemoving all Objects and testing methods: tailPeek, tailRemove(), size(), toString()");
	// The for loop below test the list with each method in many conditions: When empty, with only 1 elements, and multiple elements.
	for (int i = 0; i < 5; i++)
	{
		System.out.println("tailPeek():\t" + list1.tailPeek());
		System.out.println("tailRemove():\t" + list1.tailRemove());
		System.out.println("Size(): \t" + list1.size());
		System.out.println(list1);
	}
	}
	
	/**
	 * This method adds elements to a list, using the tailAdd method() from LinkedDequeue.java
	 * @param list
	 */
	public static void addNewTail(LinkedDequeue list)
	{
		list.tailAdd(new Foobar("pie"));
		System.out.print(list);
		list.tailAdd(new Foobar("tuna"));
		System.out.print(list);
		list.tailAdd(new Foobar("bacon"));
		System.out.print(list);
		list.tailAdd(new Foobar("apples"));
		System.out.print(list);
	}
	
	/**
	 * This method uses the headAdd() method from LinkedDequeue.java to add elements to a list.
	 * @param list
	 */
	public static void addNewHead(LinkedDequeue list)
	{
		list.headAdd(new Foobar("pie"));
		System.out.print(list);
		list.headAdd(new Foobar("tuna"));
		System.out.print(list);
		list.headAdd(new Foobar("bacon"));
		System.out.print(list);
		list.headAdd(new Foobar("apples"));
		System.out.print(list);
	}
}
