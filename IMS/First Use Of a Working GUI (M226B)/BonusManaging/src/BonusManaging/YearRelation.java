package BonusManaging;

import java.util.Scanner;

public class YearRelation extends GeneralObject {
	
	private int personID;
	private int year;
	private double sales;
	private double bonusP;
	private double bonusCHF;
	
	public YearRelation(int idVar, Person personVar, int yearVar, double salesVar, List personList)
	{
		this.id = idVar;
		this.setpersonID(personVar.getID());
		this.setYear(yearVar);
		this.setSales(salesVar);
		if (((Person) personList.getObject(idVar)).getIsManager() == true)
		{
			this.setBonusP(0.3);
		}
		else
		{
			this.setBonusP(sales/ personVar.getCategory().getBonus());
		}
		this.setBonusCHF(sales * bonusP);
	}
	
	public YearRelation()
	{
		this.id = 0;
		this.setpersonID(0);
		this.setYear(0);
		this.setSales(0);
		this.setBonusP(0);
		this.setBonusCHF(0);
	}

	public int getpersonID() 
	{
		return personID;
	}

	public void setpersonID(int personID) 
	{
		this.personID = personID;
	}

	public int getYear() 
	{
		return year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}

	public double getSales() 
	{
		return sales;
	}

	public void setSales(double sales) 
	{
		this.sales = sales;
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
		return "\n ID: " + this.id + " / PersonID: " + this.personID + " / Year: " + this.year + " / Sales: " + this.sales + " / Bonus%: " + this.bonusP + " / BonusCHF: " + this.bonusCHF;
	}

	public double getBonusP() 
	{
		return bonusP;
	}

	public void setBonusP(double bonusP) 
	{
		this.bonusP = bonusP;
	}

	public double getBonusCHF() 
	{
		return bonusCHF;
	}

	public void setBonusCHF(double bonusCHF) 
	{
		this.bonusCHF = bonusCHF;
	}

}
