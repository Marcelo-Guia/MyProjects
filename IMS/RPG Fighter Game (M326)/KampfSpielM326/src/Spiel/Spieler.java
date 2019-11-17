package Spiel;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Spieler extends GeneralObject{
	
	private String name;
	private Charakter character;
	
	public Spieler()
	{
		this.id = 0;
		this.name = "";
		this.character = null;
	}
	
	public Spieler(int idVar, String nameVar, Charakter charVar)
	{
		this.id = idVar;
		this.name = nameVar;
		this.character = charVar;
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

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Charakter getCharacter() 
	{
		return character;
	}

	public void setCharacter(Charakter character) 
	{
		this.character = character;
	}
	
	@Override
	public void LoadData(String[] tokens) 
	{
		try 
		{
			this.id = Integer.parseInt(tokens[0]);
			this.name = tokens[1];
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}

	@Override
	public void SaveData(FileWriter fileWriter) 
	{
		try 
		{
			// Append the String separating attributes with ";" and add a new line
			fileWriter.append(this.id + ";" + this.name);
			fileWriter.append("\n");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	@Override
	public String toString()
	{
		return this.id + " / " + this.name;
	}

}
