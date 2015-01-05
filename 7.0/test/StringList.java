// StringLinkedList.java
/** Unit 7
  * @author: Jan Jackson  (Modified from lecture example StringLinkedList.java - Dr. H. Leitner)
  * @version  1.0
  */

public class StringList
{
    /**
     *  A private inner class to create Node objects, each of which 
     *  contains a String and a link to the next item.
     */
    private class Node
    {
	/*  Instance variables of the Node class..  since the class is private,
	 *  these will not be accessible outside it.
	 */
	String data;
	Node next;

	/**
	 *  0-Param constructor that sets default values
	 */
	Node()
	{
	    data = "";
	    next = null;
	}

	/**
	 *  This constructor sets up the data but leaves the next link null
	 *
	 *  @param  addData   The String for the Node to hold
	 */
	Node( String addData )
	{
	    data = addData;
	    next = null;
	}

	/**
	 *  This constructor sets up both the data and next link fields.
	 *
	 *  @param  n         The next Node in the list
	 *  @param  addData   The String for the Node to hold	
	 */
	Node( String addData, Node n )
	{
	    data = addData;
	    next = n;
	}
    }

    private Node head;

    /**
     *  The 0-param constructor will create the list object with the head set to null.
     */
    public StringList()
    {
        head = null;
    }

    /**
     *  Returns the number of nodes in the list.
     *
     *  @return     the number of Nodes in the list
     */
    public int length()
    {
        int count = 0;
        Node position = head;
        while (position != null)
        {
            count++;
            position = position.next;
        }
        return count;
    }

    /**
     *  Adds a node at the start of the list. The added node has addData
     *  as its data. The added node will be the first node in the list.
     *
     *  @param   addData   The data to be added (in a Node) to the front of the list
     */
    public void addANodeToStart(String addData)
    {
        head = new Node(addData, head);
    }

    /**
     *  This method will remove the first Node from the front of the list,
     *  reassigning the head pointer to the next Node in the sequence.
     */
    public String deleteHeadNode()
    {
	String str = head.data;
        if (head != null)
        {
            head = head.next;
        }
        else
        {
            System.out.println("Deleting from an empty list.");
        }
	return str;
    }

    /**
     *  This method will look for a String within the list and return
     *  whether or not it was found to be there.
     *
     *  @return       true if the item is found, false if not.
     */
    public boolean onList(String target)
    {
        return (find(target) != null);
    }

    /**
     *  Finds the first node containing the target data, and returns a
     *  reference to that node. If target is not in the list, null is returned
     *
     *  @return     a reference to the Node containing the target String
     */
    private Node find (String target)
    {
        Node position;
        position = head;
        String dataAtPosition;
        while (position != null)
        {
            dataAtPosition = position.data;
            if (dataAtPosition.equals(target))
                return position;
            position = position.next;
        }
        return null; 
    }

    /**
     *  This method will print out the list, one item per line
     */
    public void showList()
    {
        Node position;
        position = head;
        while (position != null)
        {
            System.out.println(position.data);
            position = position.next;
        }
    }

    /*
    public  void mystery() throws LinkedListException
    {
        Node p = this.head;
        if (p == null) throw new LinkedListException(
                 "You cannot delete from an empty list!");
        if (p.getLink() == null) 
        {
            this.head = null;
            return;
        }
        while (p.getLink().getLink() != null)
        {
            p = p.getLink();
        }
        p.setLink(null);
     }
    */
}
