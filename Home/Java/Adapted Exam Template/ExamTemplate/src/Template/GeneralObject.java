package Template;

import java.io.Serializable;
import java.util.Scanner;

public abstract class GeneralObject implements Serializable {
	
/*	This is a class that every object will extend. It contains an ID (ensuring that all objects
 * 	will have one) and contains an abstract function to get the ID (ensures all objects have this 
 * 	function and makes the delete function in the list much easier to implement). Just don't forget
 * 	to make every object extend this one...
 */
	
	protected int id;
	
	public GeneralObject()
	{
		this.id = 0;
	}
	
	public abstract int getID();
	public abstract void setID(int idVar);
	public abstract void update(Scanner scan);
	public abstract void setAddData(Scanner scan);

}
