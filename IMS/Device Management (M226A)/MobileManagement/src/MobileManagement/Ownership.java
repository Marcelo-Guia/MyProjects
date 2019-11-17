package MobileManagement;

import java.util.Iterator;
import java.util.Scanner;

public class Ownership extends GeneralObject {
	
	private int personID;
	private int deviceID;
	static private List<Person> showOwnersList = new List<Person>("showOwnersList.dat");
	static private List<Device> showDevicesList = new List<Device>("showDevicesList.dat");
	
	public Ownership(int idVar, int personIDVar, int deviceIDVar)
	{
		this.id = idVar;
		this.setPersonID(personIDVar);
		this.setDeviceID(deviceIDVar);
	}
	
	public Ownership()
	{
		this.id = 0;
		this.setPersonID(0);
		this.setDeviceID(0);
	}
	
	public void showOwners(Scanner scan, List personList, List ownershipList)
	{
		int count = 0;
		System.out.println("Please enter the ID of the Device:");
		int id = Integer.parseInt(scan.next());
		
		for (Iterator<Ownership> i = ownershipList.getObjectList().iterator(); i.hasNext();)
		{
			GeneralObject object = (GeneralObject) i.next();
			if (((Ownership) object).getDeviceID() == id)
			{
				for (Iterator<Person> p = personList.getObjectList().iterator(); p.hasNext();)
				{
					GeneralObject object2 = (GeneralObject) p.next();
					if (((Person) object2).getID() == ((Ownership) object).getPersonID())
					{
						count++;
						showOwnersList.add((Person) object2);
					}
				}
			}
			if (!i.hasNext())
			{
				System.out.println("Done with the search. Found " + count + " result(s) (Duplicates included):");
			}
		}
		System.out.println(showOwnersList);
		showOwnersList.getObjectList().clear();
		System.out.println("Press enter to continue:");
		scan.nextLine();
		scan.nextLine();
	}
	
	public void showDevices(Scanner scan, List deviceList, List ownershipList)
	{
		int count = 0;
		System.out.println("Please enter the ID of the Person:");
		int id = Integer.parseInt(scan.next());
		
		for (Iterator<Ownership> i = ownershipList.getObjectList().iterator(); i.hasNext();)
		{
			GeneralObject object = (GeneralObject) i.next();
			if (((Ownership) object).getPersonID() == id)
			{
				for (Iterator<Device> p = deviceList.getObjectList().iterator(); p.hasNext();)
				{
					GeneralObject object2 = (GeneralObject) p.next();
					if (((Device) object2).getID() == ((Ownership) object).getDeviceID())
					{
						count++;
						showDevicesList.add((Device) object2);
					}
				}
			}
			if (!i.hasNext())
			{
				System.out.println("Done with the search. Found " + count + " result(s) (Duplicates included):");
			}
		}
		System.out.println(showDevicesList);
		showDevicesList.getObjectList().clear();
		System.out.println("Press enter to continue:");
		scan.nextLine();
		scan.nextLine();
	}

	public int getPersonID() 
	{
		return personID;
	}

	public void setPersonID(int personID) 
	{
		this.personID = personID;
	}

	public int getDeviceID() 
	{
		return deviceID;
	}

	public void setDeviceID(int deviceID) 
	{
		this.deviceID = deviceID;
	}

	@Override
	public int getID() 
	{
		return this.id;
	}

	@Override
	public void setID(int idVar) 
	{
		this.id = idVar;
	}

	@Override
	public void update(Scanner scan) 
	{
		setAddData(scan);
	}
	
	@Override
	public String toString() 
	{
		return "\nID: " + this.id + " / Person ID: " + this.personID + " / Device ID: " + this.deviceID;
	}

	@Override
	public void setAddData(Scanner scan) 
	{
		System.out.println("Please enter the ID manually:");
		this.id = Integer.parseInt(scan.next());
		System.out.println("Please enter the Person ID:");
		this.personID = Integer.parseInt(scan.next());
		System.out.println("Please enter the Device ID:");
		this.deviceID = Integer.parseInt(scan.next());
	}

}
