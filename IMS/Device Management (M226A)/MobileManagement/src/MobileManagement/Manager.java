package MobileManagement;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class Manager {
	
	// Author: Marcelo Guia
	// This is from the M226 A exam and I reworked it so it can be used as a template for the GUI exam, as it was more refined than my old template.
	// With that said, keep in mind some of the comments will be from the exam, so yeah...
	
//	I made the entire code by myself (except a quick syntax search for the file creation)
//	and used a template I created myself containing most function I implemented here, so 
//	pretty much everything is my own code, and I do have proof that the template is in fact
//	mine if necessary.

	static private Menu menu = new Menu("Menu");
	static private Menu addMenu = new Menu("Addition options:");
	static private Menu deleteMenu = new Menu("Delete options:");
	static private Menu updateMenu = new Menu("Delete options:");
	static private List<Device> deviceList = new List<Device>("DeviceList.dat");
	static private List<Person> personList = new List<Person>("PersonList.dat");
	static private List<Ownership> ownershipList = new List<Ownership>("OwnershipList.dat");
	static private Scanner scan = new Scanner(System.in);
	
	// Remember these very useful and time saving shortcuts:
	// Comment out selected: Ctrl + Shift + / (not on numpad) , Surround with: Alt + Shift + Z
	// Show more shortcuts: Ctrl + Shift + L
	
	// ***** CAUTION: As of the 6.6.2019 I have realized that changing the structure of some of these objects 
	// will result in a serialization issue. This will cause an exception, that makes it impossible to use previously
	// saved data via the save() function. 
	// Classes affected by this so far: GeneralObject derived classes
	// If you change the structure of a class and get the invalid class exception with the serialversionUID, it means this is what happened.
	// Going to your workspace and deleting the 3 files that contain the saved data is an alternative as long as you don't mind losing the data in them.
	// So with that in mind, be careful if you change any structure in this program.

	public static void main(String[] args) 
	{
		try 
		{
//			Create Files if they don't exist.
			File file1 = new File("DeviceList.dat");
			File file2 = new File("PersonList.dat");
			File file3 = new File("OwnershipList.dat");
			
			if (file1.createNewFile())
			{
				System.out.println("It seems your DeviceList.dat file was missing, so a new one was created for you.");
				deviceList.save(deviceList);
			}
			if (file2.createNewFile())
			{
				System.out.println("It seems your PersonList.dat file was missing, so a new one was created for you.");
				personList.save(personList);
			}
			if (file3.createNewFile())
			{
				System.out.println("It seems your OwnershipList.dat file was missing, so a new one was created for you.");
				ownershipList.save(ownershipList);
			}
			
//			Starting Process
			deviceList = deviceList.load();
			personList = personList.load();
			ownershipList = ownershipList.load();
			
			System.out.println(deviceList);
			System.out.println(personList);
			System.out.println(ownershipList);
			
//			Functions
			addMenu.Add('1', "Add a Device", addDevice);
			addMenu.Add('2', "Add a Person", addPerson);
			addMenu.Add('3', "Add a relation between a Person and a Device", addOwnership);
			deleteMenu.Add('1', "Delete a Device", deleteDevice);
			deleteMenu.Add('2', "Delete a Person", deletePerson);
			deleteMenu.Add('3', "Delete a relation between a Person and a Device", deleteOwnership);
			menu.Add('1', "Add something", add);
			menu.Add('2', "Delete something", delete);
			menu.Add('3', "Update something", update);
			updateMenu.Add('1', "Update a Device", updateDevice);
			updateMenu.Add('2', "Update a Person", updatePerson);
			updateMenu.Add('3', "Update a relation between a Person and a Device", updateOwnership);
			menu.Add('4', "Show all owners of a device", showOwners);
			menu.Add('5', "Show all devices of a owner", showDevices);
			menu.Display();
			
//			Closing Process
			scan.close();
			System.out.println(deviceList);
			System.out.println(personList);
			System.out.println(ownershipList);
			deviceList.save(deviceList);
			personList.save(personList);
			ownershipList.save(ownershipList);
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
			addMenu.Display();
		}
	};
	
	private static Menu.Function delete = new Menu.Function() 
	{
		public void eval()
		{
			deleteMenu.Display();
		}
	};
	
	private static Menu.Function addDevice = new Menu.Function() 
	{
		public void eval()
		{
			Device dev = new Device();
			dev.setAddData(scan);
			deviceList.add(dev);
			System.out.println("Device added successfully!");
		}
	};
	
	private static Menu.Function addPerson = new Menu.Function() 
	{
		public void eval()
		{
			Person pers = new Person();
			pers.setAddData(scan);
			personList.add(pers);
			System.out.println("Person added successfully!");
		}
	};
	
	private static Menu.Function addOwnership = new Menu.Function() 
	{
		public void eval()
		{
			Ownership own = new Ownership();
			own.setAddData(scan);
			ownershipList.add(own);
			System.out.println("Device added successfully!");
		}
	};
	
	private static Menu.Function deleteDevice = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			deviceList.delete(id);
		}
	};
	
	private static Menu.Function deletePerson = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			personList.delete(id);
		}
	};
	
	private static Menu.Function deleteOwnership = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			ownershipList.delete(id);
		}
	};
	
	private static Menu.Function showOwners = new Menu.Function() 
	{
		public void eval()
		{
			Ownership own = new Ownership();
			own.showOwners(scan, personList, ownershipList);
		}
	};
	
	private static Menu.Function showDevices = new Menu.Function() 
	{
		public void eval()
		{
			Ownership own = new Ownership();
			own.showDevices(scan, deviceList, ownershipList);
		}
	};
	
	
	private static Menu.Function updateDevice = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			deviceList.update(scan, id);
		}
	};
	
	private static Menu.Function updatePerson = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			personList.update(scan, id);
		}
	};
	
	private static Menu.Function updateOwnership = new Menu.Function() 
	{
		public void eval()
		{
			System.out.println("Please enter the ID manually:");
			int id = Integer.parseInt(scan.next());
			ownershipList.update(scan, id);
		}
	};
	
	private static Menu.Function update = new Menu.Function() 
	{
		public void eval()
		{
			updateMenu.Display();
		}
	};

}
