package Template;

import java.util.Iterator;
import java.util.Scanner;


public class Main {
	
/*	**********VERY IMPORTANT DO NOT SKIP THIS!!!**********
 * 	This is a template class that contains a lot of general, tedious and time consuming features
 * 	and displays a few shortcuts at the top, in order to get into a faster workflow more easily.
 * 	It also comes with a few other classes which contain even more of those features and those 
 * 	at the moment include: Datetime and Period formatters for easy and fast handling of time, a
 * 	generic list that contains the essential list features and an improved version of Stadelmann's
 * 	menu, which contains the ability to turn functions on or off and replaces the GetMenuItemFunction()
 * 	function with a more user-friendly GetMenuItem() function, which allows users to directly 
 * 	access all features within the MenuItem class at the cost of it now being public.
 * 
 * 	This template is intended to be there for no other purpose than speeding up the work by 
 * 	cutting time that's spent writing code down and having the features ready at hand. This 
 * 	template was created by Marcelo Guia from the I2a (as of May 5th 2019) and will be shared 
 * 	with the class as it's updated as well (hopefully). I do not promote any sort of cheating 
 * 	during the exams and hereby confirm that these templates are given out BEFORE the exams and 
 * 	never during the exams. Therefore according to Mr. Stadelmann this does not count as cheating
 * 	and can be used during the exams.
 * 
 * 	****NOTE!****
 * 	For this to work with no issues, all your objects being used in the list have to contain an 
 * 	id, otherwise the list won't work properly won't work.
 * 
 * 	Have a nice day and if you manage to somehow improve this template, please contact me so we 
 * 	can update it for the whole class. :)
 */
	
	static private Menu menu = new Menu("Menu");
	static private List<ExampleObject> exampleList = new List<ExampleObject>("PersonList.dat");
	static private Scanner scan = new Scanner(System.in);
	
	// Remember these very useful and time saving shortcuts:
	// Comment out selected: Ctrl + Shift + / (not on numpad) , Surround with: Alt + Shift + Z
	// Show more shortcuts: Ctrl + Shift + L
	
//	TODO: 
//	1. Add try catch to all functions
//	2. Finish update function in ExampleRelations
//	3. Add show list functionality to the template

	public static void main(String[] args) 
	{
		try 
		{
			menu.Add('1', "Add an ExampleObject", add);
			menu.Add('2', "Delete an ExampleObject", delete);
			menu.Add('3', "Update an ExampleObject", update);
			menu.Add('4', "Save", save);
			menu.Add('5', "Load data from the file", load);
			menu.Display();
			
			System.out.println(exampleList);
			scan.close();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	
	private static Menu.Function add = new Menu.Function() 
	{
		public void eval()
		{
			ExampleObject object = new ExampleObject();
			object.setAddData(scan);
			exampleList.add(object);
			System.out.println("ExampleObject added successfully!");
		}
	};
	
	private static Menu.Function delete = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			exampleList.delete(id);
			System.out.println("ExampleObject with the ID " + id + " successfully deleted!");
		}
	};
	
	private static Menu.Function update = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			exampleList.update(scan, id);
		}
	};
	
	private static Menu.Function save = new Menu.Function() 
	{
		public void eval()
		{
			exampleList.save(exampleList);
			System.out.println("Successfully saved!");
		}
	};
	
	private static Menu.Function load = new Menu.Function() 
	{
		public void eval()
		{
			exampleList.load();
			System.out.println("Successfully loaded all data!");
		}
	};

}
