/** Queue.java
 *  Implementation of QUEUE as a "circular" array
 *  NOTE:  to queue up primitive types, "wrap" them in 
 *         java "wrapper" classes (Integer for int, e.g.)
 *
 *      Dr. Henry H. Leitner
 *      Last Modified March 29, 2013 
 */

public class Queue
{
    private int front;                // index of current front element
    private int rear;       	      // index of current rear element
    private int count;      	      // current # elements
    private int capacity;             // max # elements that'll fit
    private final static int CAPACITY_INCREMENT = 5;
    private Object [] entry;
    private static final int DEFAULT_SIZE = 10;
    
    public Queue ()        	     // no-arg constructor function
    {   
        count = 0;
        front = 1;
        rear = 0;
        capacity = DEFAULT_SIZE;     // set up default sized queue
        entry = new Object [capacity + 1];
    }

    public Queue (int size)  	    // 1-arg constructor method
    {   
        count = 0;
        front = 1;
        rear = 0;
        capacity = size;  
        entry = new Object [capacity + 1];
    }

    /** remove next item, return it as an Object, or return null if empty */
    public Object delete ()
    {
       Object temp;

       if (empty()) return null;     //underflow error
       else
       {
            count--;
            temp = entry [front];
            front = (front % capacity) + 1;
            return temp;
       }
    }


    public boolean empty ()
    {
        return (count == 0);
    }



    /** add an object to the rear of the queue */
    public void add (Object x)
    {
	if (count == capacity)
	{     // increase the capacity of the queue
           capacity += CAPACITY_INCREMENT;
           Object [] tempArray = new Object[capacity+1];
           int oldCount = count;
           for (int i = 1; i <= oldCount; i++) 
           {
               tempArray[i] = delete ();
           }
           front = 1;
           rear = oldCount;
           count = oldCount;
           entry = tempArray;
        }

	count++;
	rear = (rear % capacity) + 1;
	entry [rear] = x;
    }


    public int size ()
    {
         return count;
    }
}