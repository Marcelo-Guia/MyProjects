package BonusManaging;

import java.util.Scanner;

public class Person extends GeneralObject {
	
	private String firstName;
	private String lastName;
	private Category category;
	private String place;
	private Boolean isManager;
	
	public Person(int idVar, String firstVar, String lastVar, Category catVar, String placeVar, Boolean isManager)
	{
		this.id = idVar;
		this.setFirstName(firstVar);
		this.setLastName(lastVar);
		this.setCategory(catVar);
		this.setPlace(placeVar);
		this.setIsManager(isManager);
	}
	
	public Person()
	{
		this.id = 0;
		this.setFirstName("");
		this.setLastName("");
		this.setCategory(new Category());
		this.setPlace("");
		this.setIsManager(false);
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

	public Category getCategory() 
	{
		return category;
	}

	public void setCategory(Category category) 
	{
		this.category = category;
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
		
	}
	
	@Override
	public String toString() 
	{
		return "\n ID: " + this.id + " / First Name: " + this.firstName + " / Last Name: " + this.lastName + " / Category: " + this.category + " / Sales: " + this.place;
	}

	public String getPlace() 
	{
		return place;
	}

	public void setPlace(String place) 
	{
		this.place = place;
	}

	public Boolean getIsManager() 
	{
		return isManager;
	}

	public void setIsManager(Boolean isManager) 
	{
		this.isManager = isManager;
	}

}
