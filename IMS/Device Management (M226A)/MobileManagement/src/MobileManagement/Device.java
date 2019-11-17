package MobileManagement;

import java.util.Scanner;

public class Device extends GeneralObject {
	
	private String model;
	private String manu;
	private String serialNum;
	private String os;
	private Boolean SIM;
	
	public Device(int idVar, String modelVar, String manuVar, String serialNumVar, String osVar, Boolean SIMVar)
	{
		this.id = idVar;
		this.setModel(modelVar);
		this.setManu(manuVar);
		this.setSerialNum(serialNumVar);
		this.setOs(osVar);
		this.setSIM(SIMVar);
	}
	
	
	public Device()
	{
		this.id = 0;
		this.setModel("");
		this.setManu("");
		this.setSerialNum("");
		this.setOs("");
		this.setSIM(false);
	}

	public String getModel() 
	{
		return model;
	}

	public void setModel(String model) 
	{
		this.model = model;
	}

	public String getManu() 
	{
		return manu;
	}

	public void setManu(String manu) 
	{
		this.manu = manu;
	}

	public String getSerialNum() 
	{
		return serialNum;
	}

	public void setSerialNum(String serialNum) 
	{
		this.serialNum = serialNum;
	}

	public String getOs() 
	{
		return os;
	}

	public void setOs(String os) 
	{
		this.os = os;
	}

	public Boolean getSIM() 
	{
		return SIM;
	}

	public void setSIM(Boolean sIM) 
	{
		SIM = sIM;
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
		return "\nID: " + this.id + " / Model: " + this.model + " / Manufacturer: " + this.manu + " / Serial Number: " + this.serialNum + " / OS: " + this.os + " / Contains a SIM: " + this.SIM;
	}

	@Override
	public void setAddData(Scanner scan) 
	{
		System.out.println("Please enter the ID manually:");
		this.id = Integer.parseInt(scan.next());
		System.out.println("Please enter the Model:");
		scan.nextLine();
		this.model = scan.nextLine();
		System.out.println("Please enter the Manufacturer:");
		this.manu = scan.nextLine();
		System.out.println("Please enter the Serial Number:");
		this.serialNum = scan.nextLine();
		System.out.println("Please enter the Device OS:");
		this.os = scan.nextLine();
		Boolean SIM = null;
		do {
			System.out.println("Please enter true if Device has a SIM or false if not:");
			String bDataScan = scan.nextLine();
			if (bDataScan.toLowerCase().equals("true")) 
			{
				SIM = true;
				this.SIM = true;
			} 
			else if (bDataScan.toLowerCase().equals("false")) 
			{
				SIM = false;
				this.SIM = false;
			} 
		} while (SIM == null);
	}

}
