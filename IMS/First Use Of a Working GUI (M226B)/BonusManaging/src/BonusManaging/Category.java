package BonusManaging;

import java.util.Scanner;

public class Category extends GeneralObject {
	
	private String category;
	private double bonus;
	
	public Category(int idVar, String catVar, double bonVar)
	{
		this.id = idVar;
		this.setCategory(catVar);
		this.setBonus(bonVar);
	}
	
	public Category()
	{
		this.id = 0;
		this.setCategory("");
		this.setBonus(100000);
	}

	public String getCategory() 
	{
		return category;
	}

	public void setCategory(String category) 
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
		return "\n ID: " + this.id +  " / Category: " + this.category + " / Bonus: " + this.bonus;
	}

	public double getBonus() 
	{
		return bonus;
	}

	public void setBonus(double bonus) 
	{
		this.bonus = bonus;
	}

}
