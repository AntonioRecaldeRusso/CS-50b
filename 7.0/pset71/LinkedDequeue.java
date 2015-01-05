// LinkedDequeue.java

/**
 * This class creates double ended linked list objects, and provides the ability to manage them
 * them through various methods. Namely, with you class you may add and remove elements from head 
 * or tail, check the number of elements, check if it's empty, and peek at elements in front and rear position.
 * 
 * This class does not contain a main method. It runs in conjunction with LinkedDeuqueueRun.java and Foobar.java
 * 
 * @author Antonio Recalde
 * @version 04/27/2013
 *
 */
public class LinkedDequeue
{
	private QueueNode tail;
    public QueueNode head;
    private int count;
   
    
    /**
     * This inner class models a queue node
     */
    public class QueueNode
    {
    	private Object item;
    	private QueueNode next;
    } 
    // end inner class
    
    
    /**
     * Constructor. Sets variables to default values. 
     */
    public LinkedDequeue()
    {
    	head = null;
    	tail = null;
    	count = 0;
    }
    
    /**
     * This method adds objects to the head of the linked list
     * @param input
     */
    public void headAdd(Object input)
    {
    	QueueNode temp = new QueueNode();
    	temp.item = input;
    	temp.next = null;
    	
    	if (head == null)
    		head = temp;
    		
    	else  
    	{
    		temp.next = head;
    		head = temp;
    	}
    	count++;
    }
    
    /**
     * This method adds object elements to the tail of the linked list
     * 
     * @param input
     */
    public void tailAdd(Object input)
    {
    	QueueNode temp = new QueueNode();		// QueueNode type object for temporary use.. 
    	
    	/*
    	 *  stores the input into the item variable of the QueueNode object. Hence QueueNode temp != input. 
    	 *  Rather QueueNode is more like an index. For each list element, there is a QueueNode.
    	 */
    	temp.item = input;						
    	temp.next = null;						// because this will be the tail, next must point to null.
    	
    	if (tail == null)						// it's the same as saying if isEmpty().. but faster.
    	{
    		head = temp;						// since there is nothing there, both head and tail will point the the now only object.
    		tail = temp;
    	}
    	
    	else  									// the list is not empty...
    	{
    		tail.next = temp;					// so, this is the next after the current tail object.
    		
    		/*
    		 * and now update the QueueNode tail, not the object in it. The previous tail is still out there being pointed by some 
    		 * other QueueNode, but it is not referenced by the tail pointer.
    		 */
    		tail = temp;						
    	}
    	count++;								// we just added an element, increase the count.
    }
    
    /**
     * This Method checks the current object being referenced by head.item, and returns it.
     * @return
     */
    public Object headPeek()
    {	
    	// if it's not empty, return head.item... I do not use isEmpty() because it basically does the same thing.
    	if (head != null)						
    		return head.item;
    	
    	else return null;						
  
    }
    
    /**
     * This method checks the item stored at rear position, and returns it.
     * @return
     */
    public Object tailPeek()
    {
    	if (isEmpty())							// if the list is empty, return null.
    		return null;
    	
    	else
    		return tail.item;					// else return the object referenced in the tail object.
    }
    
    /**
     * This method removes an object from the front of the list, and returns it
     * 
     * @return temp
     */
    public Object headRemove()
    {
    	if (isEmpty())							// if the list is empty, there is nothing...
    		return null;
    	
    	else
    	{
    		Object temp = head.item;			// if the list is not empty, store a reference to the current head in a temporary object.
    		head = head.next;					// now, point the QueueNode head to the next object.
    		if (head == null)					// if the head was the only item in the list, update the tail.. now it's null. The list is empty.
    			tail = null;
    		count--;							// minus one object..
    		/*
    		 *  because we referenced the old head object in temp, we can still return it. After the method is over, temp will be garbage collected
    		 *  as it only exists locally. No need to de-reference pointers. Phew!
    		 */
    		return temp;						
    	}
    }
    
    /**
     * This method removes an element from the tail of the list and returns it.
     * @return
     */
    public Object tailRemove()
    {
    	QueueNode temp = head;					
    	QueueNode temp2 = tail;
    	
    	if (isEmpty())		
    		return null;
    	
    	else if (head == tail)						
    	{
    		/*
    		 * If there is only one element in the list, reset everything to null as by removing it, the list if then empty.
    		 */
    		head = null;							
    		tail = null;
    		count--;
    		return temp2.item;					// return the removed object.
    	}
    		
    	else
    	{
    		/*
    		 * There are multiple objects in the list. So, traverse the list until the reference to the tail is found.
    		 */
    		while (temp.next != temp2)
    			temp = temp.next;
    		
    		// the reference to the tail was found in temp.next.. So, make tail point to temp. 
    		tail = temp;
    		
    		/*
    		 *  temp.next still points to the old tail.. So, disconnect it by setting to null. The old tail now floats in infinity (yet memory is finite
    		 *  in extension, though infinite in possible attributes... this puts into perspective the fact that by analogy, our conscience being a pointer 
    		 *  to something outside of us (us, as self definition) may apply, in a fractal universe where thought and extension comply to the Spinozan 
    		 *  perspective). Thanks.
    		 *  
    		 */
    		tail.next = null;
    		
    		count--;
    		return temp2.item;
    	}
    }
    
    /**
     * This method returns true if the list if empty
     * @return boolean (count == 0)
     */
    public boolean isEmpty()
    {
    	return (count == 0);
    }
    
    /**
     * This method returns the number of elements in the list
     * @return count
     */
    public int size()
    {
    	return count;
    }
    
    /**
     * This toString method prints the elements present in the list, and what they point to.
     */
    public String toString()
    {
    	QueueNode position;
    	position = head;
    	StringBuilder str = new StringBuilder();
    	
    	str.append("\n\tList Elements:  ");
    	while (position != null)
    	{
    		str.append( String.format("%s -> ", position.item) );
    		position = position.next;
    	}
    	str.append( ("null\n") );
    	
    	return str.toString();
    }
 }
    