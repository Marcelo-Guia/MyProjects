package Spiel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
	private String HEADER;
	private String classType;
	
	public List(String fileNameVar, String headVar, String classVar)
	{
		 this.setObjectList(new HashSet<T>());
		 this.setFileName(fileNameVar);
		 this.setHEADER(headVar);
		 this.setClassType(classVar);
	}
	
	public T getGraphicsPanel(int idVar)
	{
		T object = null;
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			T objectVar = i.next();
			if (((GraphicsPanel) objectVar).getID() == idVar)
			{
				object = objectVar;
				break;
			}
			else if (!i.hasNext())
			{
				System.out.println("There is no object with that ID.");
			}
		}
		return object;
	}
	
	public T getObject(int idVar)
	{
		T object = null;
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			T objectVar = i.next();
			if (((GeneralObject) objectVar).getID() == idVar)
			{
				object = objectVar;
				break;
			}
			else if (!i.hasNext())
			{
				System.out.println("There is no object with that ID.");
			}
		}
		return object;
	}
	
	public Spieler getOtherPlayer(Spieler curPlayer)
	{
		Spieler newCurPlayer = null;
		for (Iterator<Spieler> i = (Iterator<Spieler>) objectList.iterator(); i.hasNext();)
		{
			Spieler playerVar = i.next();
			if (playerVar != curPlayer)
			{
				newCurPlayer = playerVar;
				break;
			}
			else if (!i.hasNext())
			{
				System.out.println("There is no object with that ID.");
			}
		}
		return newCurPlayer;
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
			if ((getID((GeneralObject) i.next()) == removeID))
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
			outputStream.flush();
			outputStream.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.toString());
			System.exit(0);
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
			JOptionPane.showMessageDialog(null, ex.toString());
			System.exit(0);
			list = null;
		}
		return list;
	}
	
	// Unnecessary for this game
	/*public void update(Scanner scan, int updateID)
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
	}*/
	
	@Override
	public String toString()
	{
		String text = "";
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			text += i.next().toString() + "\n";
		}
		return text;
	}
	
	public void SaveData()
	{
		FileWriter fileWriter = null;
		
		try 
		{
			fileWriter = new FileWriter(fileName);
			
			//Write the CSV file header
            fileWriter.append(HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append("\n");
			
            // Create a placeholder for the current object
			T object = null;
			for (Iterator<T> i = objectList.iterator(); i.hasNext();)
			{
				// Assign the current object to the placeholder
				T objectVar = i.next();
				
				// Call the SaveData() function, that appends the Class-Specific String to the text file
				((GeneralObject) objectVar).SaveData(fileWriter);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
		finally
		{
			try 
			{
				// Flush and close to make results official
				fileWriter.flush();
				fileWriter.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString());
				System.exit(0);
			}
		}
	}
	
	// Load the data contained in the file (from a code we made last semester, but adapted to my classes and needs)
	public void LoadData()
	{
		BufferedReader fileReader = null;

	    try {

	        // Empty String as placeholder
	        String line = "";

	        //Create the file reader
	        fileReader = new BufferedReader(new FileReader(fileName));

	        //Read the CSV file header to skip it
	        fileReader.readLine();

	        //Read the file line by line starting from the second line
	        while ((line = fileReader.readLine()) != null) {
	            //Get all tokens available in line
	            String[] tokens = line.split(";");
	            if (tokens.length > 0) 
	            {
	            	// Get the class of the saved Objects by name (classType var)
	            	Class<?> classVar = Class.forName("Spiel." + this.classType);
	            	// Create an instance of that class, to make it possible to use the class specific LoadData() function
	            	T placeHolder = (T) classVar.newInstance();
	            	// Use the LoadData() function which reads all the attributes from the file and sets them as that instance's attributes
	            	((GeneralObject) placeHolder).LoadData(tokens);
	            	// Add that instance to the list so it is stored in the code
	                this.add(placeHolder);
	            }
	        }

	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
	    } 
	    finally 
	    {
	        try 
	        {
	            fileReader.close();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, e.toString());
				System.exit(0);
	        }
	    }
	}

	public String getHEADER() 
	{
		return HEADER;
	}

	public void setHEADER(String hEADER) 
	{
		HEADER = hEADER;
	}

	public String getClassType() 
	{
		return classType;
	}

	public void setClassType(String classType) 
	{
		this.classType = classType;
	}
}
