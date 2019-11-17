package MobileManagement;

import java.util.Scanner;

public class Person extends GeneralObject {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	public Person(int idVar, String firstVar, String lastVar, String phoneVar)
	{
		this.id = idVar;
		this.setFirstName(firstVar);
		this.setLastName(lastVar);
		this.setPhoneNumber(phoneVar);
	}
	
	public Person()
	{
		this.id = 0;
		this.setFirstName("");
		this.setLastName("");
		this.setPhoneNumber("");
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
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
		return "\nID: " + this.id + " / First Name: " + this.firstName + " / Last Name: " + this.lastName + " / Phone Number: " + this.phoneNumber;
	}

	@Override
	public void setAddData(Scanner scan) 
	{
		System.out.println("Please enter the ID manually:");
		this.id = Integer.parseInt(scan.next());
		System.out.println("Please enter the First Name:");
		scan.nextLine();
		this.firstName = scan.nextLine();
		System.out.println("Please enter the Last Name:");
		this.lastName = scan.nextLine();
		System.out.println("Please enter the Phone Number:");
		this.phoneNumber = scan.nextLine();
	}

}
