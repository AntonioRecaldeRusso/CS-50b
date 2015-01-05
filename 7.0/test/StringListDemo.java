// StringListDemo

/** 
 * This demo program will use the StringList class to create a list and add
 * items, then to print it and show deletion of items.
 * Based on the sample programs from CSCI E-50b lecture by Dr. Leitner
 *
 * @author:   Jan Jackson
 * @version:  1.1  04/23/13  
 */

public class StringListDemo
{
    public static void main(String[] args)
    {
        StringList list = new StringList();
        list.addANodeToStart("One");
        list.addANodeToStart("Three");
        list.addANodeToStart("Two");
        list.addANodeToStart("Four");
        System.out.println("List has " + list.length() 
                            + " entries.");
        list.showList();

	System.out.println();
        if (list.onList("Three"))
            System.out.println("Three is on list.");
        else
            System.out.println("Three is NOT on list.");
	System.out.println();

        list.deleteHeadNode();
	System.out.println(" Deleting head node... " );
	list.showList();
	System.out.println();

        if (list.onList("One"))
            System.out.println("One is on list.");
        else
            System.out.println("One is NOT on list.");
	System.out.println();

        list.deleteHeadNode();
	list.showList();
	System.out.println();

        list.deleteHeadNode();
        System.out.println("Start of list:");
        list.showList();
        System.out.println("End of list.");
    }
}