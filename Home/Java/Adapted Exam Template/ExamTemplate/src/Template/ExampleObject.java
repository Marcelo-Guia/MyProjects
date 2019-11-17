package Template;

import java.util.Scanner;

public class ExampleObject extends GeneralObject {
	
/*	An example object to help you create objects faster and it also already contains the needed
 * 	logic for getting the ID and for converting it into a string, so literally all that's left
 * 	for you is customize it as you see fit.
 */
	
	private String stringData;
	private int intData;
	private boolean boolData;
	
	public ExampleObject()
	{
		this.stringData = "";
		this.intData = 0;
		this.boolData = false;
	}
	
	@Override
	public void setAddData(Scanner scan)
	{
		System.out.println("Please enter the ID manually:");
		this.id = Integer.parseInt(scan.next());
		System.out.println("Please enter the StringData:");
		scan.nextLine();
		this.stringData = scan.nextLine();
		System.out.println("Please enter the IntData:");
		this.intData = Integer.parseInt(scan.next());
		scan.nextLine();
		Boolean bData = null;
		do {
			System.out.println("Please enter true or false:");
			String bDataScan = scan.nextLine();
			if (bDataScan.toLowerCase().equals("true")) 
			{
				bData = true;
				this.boolData = true;
			} 
			else if (bDataScan.toLowerCase().equals("false")) 
			{
				bData = false;
				this.boolData = false;
			} 
		} while (bData == null);
	}
	
	public ExampleObject(int iId, String sData, int iData, boolean bData)
	{
		this.id = iId;
		this.stringData = sData;
		this.intData = iData;
		this.boolData = bData;
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public void update(Scanner scan)
	{
		scan.nextLine();
		int idVar = 0;
		Boolean changeID = null;
		do {
			System.out.println("Please enter true to change the ID or false to leave it as is:");
			String changeIDScan = scan.nextLine();
			if (changeIDScan.toLowerCase().equals("true")) 
			{
				changeID = true;
				System.out.println("Please enter the ID manually:");
				idVar = Integer.parseInt(scan.next());
			} 
			else if (changeIDScan.toLowerCase().equals("false")) 
			{
				changeID = false;
			} 
		} while (changeID == null);
		scan.nextLine();
		System.out.println("Please enter the StringData:");
		String sData = scan.nextLine();
		System.out.println("Please enter the IntData:");
		int iData = Integer.parseInt(scan.next());
		scan.nextLine();
		Boolean bData = null;
		do {
			System.out.println("Please enter true or false:");
			String bDataScan = scan.nextLine();
			if (bDataScan.toLowerCase().equals("true")) 
			{
				bData = true;
			} else if (bDataScan.toLowerCase().equals("false")) 
			{
				bData = false;
			} 
		} while (bData == null);
		if (changeID) 
		{
			this.setID(idVar);
		}
		this.setStringData(sData);
		this.setIntData(iData);
		this.setBoolData(bData);
	}
	
	public void setID(int idVar)
	{
		this.id = idVar;
	}
	
	public void setStringData(String sData)
	{
		this.stringData = sData;
	}
	
	public void setIntData(int iData)
	{
		this.intData = iData;
	}
	
	public void setBoolData(boolean bData)
	{
		this.boolData = bData;
	}
	
	@Override
	public String toString()
	{
		return "ID: " + this.id + " / StringData: " + this.stringData + " / IntData: " + this.intData + " / BoolData: " + this.boolData;
	}

}
