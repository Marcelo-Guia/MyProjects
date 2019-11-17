package Spiel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Charakter extends GeneralObject{
	
	private String name;
	private double HP;
	private double stamina;
	private double dmgMultiplier;
	private Collection<Aktion> fähigkeiten;

	public Charakter()
	{
		setID(0);
		setName("");
		setHP(0);
		setStamina(0);
		setDmgMultiplier(0);
		//setFähigkeiten(new HashSet<Aktion>());
	}
	
	public Charakter(int idVar, String nameVar, double hpVar, double staminaVar, double dmgMulVar /*,Collection<Aktion> fähigVar*/)
	{
		setID(idVar);
		setName(nameVar);
		setHP(hpVar);
		setStamina(staminaVar);
		setDmgMultiplier(dmgMulVar);
		//setFähigkeiten(fähigVar);
	}
	
	// This is a constructor designed to make a perfect copy of a character. This was done because if we simply assign
	// the same character to both players, using an ability on one character will actually use it on both, because 
	// the characters work with pass by reference. By using this constructor in case both players are using the same
	// character, we avoid that issue and it works fine.
	public Charakter(Charakter copy)
	{
		setID(copy.getID());
		setName(copy.getName());
		setHP(copy.getHP());
		setStamina(copy.getStamina());
		setDmgMultiplier(copy.getDmgMultiplier());
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

	public double getHP() 
	{
		return HP;
	}

	public void setHP(double hP) 
	{
		HP = hP;
	}

	public double getStamina() 
	{
		return stamina;
	}

	public void setStamina(double stamina) 
	{
		this.stamina = stamina;
	}

	public double getDmgMultiplier() 
	{
		return dmgMultiplier;
	}

	public void setDmgMultiplier(double dmgMultiplier) 
	{
		this.dmgMultiplier = dmgMultiplier;
	}
	
	public Aktion getFähigkeit(String nameVar)
	{
		Aktion object = null;
		for (Iterator<Aktion> i = this.fähigkeiten.iterator(); i.hasNext();)
		{
			Aktion objectVar = i.next();
			if (((Aktion) objectVar).getName() == nameVar)
			{
				object = objectVar;
				break;
			}
			else if (!i.hasNext())
			{
				JOptionPane.showMessageDialog(null, "Keine Fähigkeit '" + nameVar + "' beim Charakter '" + this.getName() + "' gefunden!");
				System.exit(0);
			}
		}
		return object;
	}

	public Collection<Aktion> getFähigkeiten() 
	{
		return fähigkeiten;
	}

	public void setFähigkeiten(Collection<Aktion> fähigkeiten) 
	{
		this.fähigkeiten = fähigkeiten;
	}
	
	@Override
	public String toString()
	{
		return "Name: " + this.name + "\nHP: " + this.HP + "\nAusdauer: " + this.stamina + "\nMultiplikator: " + this.dmgMultiplier + "x";
	}

	@Override
	public void LoadData(String[] tokens) 
	{
		try 
		{
			this.id = Integer.parseInt(tokens[0]);
			this.name = tokens[1];
			this.HP = Double.parseDouble(tokens[2]);
			this.stamina = Double.parseDouble(tokens[3]);
			this.dmgMultiplier = Double.parseDouble(tokens[4]);
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
			fileWriter.append(this.id + ";" + this.name + ";" + this.HP + ";" + this.stamina + ";" + this.dmgMultiplier);
			fileWriter.append("\n");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
}
