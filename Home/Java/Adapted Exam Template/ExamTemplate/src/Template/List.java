package Template;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class List<T> implements Serializable/*, GenericListInterface*/ {
	
/*	This is a generic list class that can take any object and put it in a Collection HashSet.
 * 	It requires an interface to work tho, as deleting objects without it would either be an 
 * 	unnecessary challenge or a tedious task that defeats the purpose of this template. Keep in
 * 	mind that this is technically the same as we did in class, except it uses my methods of achieving it. 
 * 	If you don't know much about generics (it's that <T> behind the list and the T being used as an object 
 * 	type all over this code), I'd recommend you to research it a little and learn what it truly is, 
 * 	because if something comes up that requires you to even slightly change important features in 
 * 	this class, you're probably gonna run into some confusing errors and you'll just end up wasting 
 * 	time (Trust me I, wasted enough with this already, just some friendly advise from me to you). 
 * 	Also remember that this writes the entire object into the binary file, so instead of trying to 
 * 	make it work with a Collection just use a List object instead.
 * 
 * 	I may update this to accept TreeSet in the future...
 * 
 * 	This is pretty much all I have for now. Again if you find a way to improve this further, contact 
 * 	me and we'll update it for the class.
 */
	
	//Properties of the List
	private Collection<T> objectList;
	private String fileName;
	
	public List(String fileNameVar)
	{
		 this.setObjectList(new HashSet<T>());
		 this.setFileName(fileNameVar);
	}
	
	public int getID(GeneralObject objectVar)
	{
		return objectVar.getID();
	}

	public String getFileName() 
	{
		return fileName;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public Collection<T> getObjectList() 
	{
		return objectList;
	}

	public void setObjectList(Collection<T> objectList) 
	{
		this.objectList = objectList;
	}
	
	public void add(T objectVar)
	{
		objectList.add(objectVar);
	}
	
	public void delete(int removeID)
	{
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			if (getID((GeneralObject) i.next()) == removeID)
			{
				i.remove();
				break;
			}
			else if (!i.hasNext())
			{
				System.out.println("There is no object with that ID.");
			}
		}
	}
	
	protected void save(List<T> objectVar)
	{
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			outputStream.writeObject(objectVar);
			outputStream.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	protected List<T> load()
	{
		List list;
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
			list = (List<T>) inputStream.readObject();
			inputStream.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			list = null;
		}
		return list;
	}
	
	public void update(Scanner scan, int updateID)
	{
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			GeneralObject object = (GeneralObject) i.next();
			if (getID(object) == updateID)
			{
				object.update(scan);
			}
			else if (!i.hasNext())
			{
				System.out.println("There is no object with that ID.");
			}
		}
	}
	
	@Override
	public String toString()
	{
		return objectList.toString();
	}
	
}
