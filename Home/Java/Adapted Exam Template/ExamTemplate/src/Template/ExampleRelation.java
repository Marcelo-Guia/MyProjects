package Template;

import java.util.Scanner;

public class ExampleRelation extends GeneralObject {
	
	private int example1ID;
	private int example2ID;
	
	public ExampleRelation(int idVar, int example1IDVar, int example2IDVar)
	{
		this.id = idVar;
		this.setExample1ID(example1IDVar);
		this.setExample2ID(example2IDVar);
	}

	public int getExample1ID() 
	{
		return example1ID;
	}

	public void setExample1ID(int personID) 
	{
		this.example1ID = personID;
	}

	public int getExample2ID() 
	{
		return example2ID;
	}

	public void setExample2ID(int deviceID) 
	{
		this.example2ID = deviceID;
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
		return "ID: " + this.id + " / Person ID: " + this.example1ID + " / Device ID: " + this.example2ID;
	}

	@Override
	public void setAddData(Scanner scan) {
		// TODO Auto-generated method stub
		
	}

}
