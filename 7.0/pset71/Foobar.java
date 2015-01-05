// Foobar.java

/**
 * This class creates Foobar type objects for the purpose of testing the LinkedDeuque class
 * 
 * @author Antonio Recalde
 * @version 04/27/2013
 *
 */
public class Foobar 
{
	private String str;
	public Foobar()	{}
	public Foobar(String text)
	{
		str = text;
	}
	
	public String toString()
	{
		return String.format("%s", str);
	}
}
