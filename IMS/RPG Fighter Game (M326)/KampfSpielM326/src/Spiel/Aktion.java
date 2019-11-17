package Spiel;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

public class Aktion extends GeneralObject{
	
	private String name;
	private String desc;
	private String actDesc;
	private String failDesc;
	private double dmg;
	private double healing;
	private double staminaSelf;
	private double stamina;
	private String owner;
	private int chance;
	private Runnable aktion;
	
	// This is currently commented out, because I decided to leave out status effects. They would add unnecessary complexity to this project and 
	// I needed to shave off some of the work time. They would for the most part only result in more writing, instead of coding.
	//private Collection<StatusEffekt> effektList;
	
	// This is a late addition that wasn't planned initially. By having the target variable, instead of having to pass the parameters, I can just use the 
	// setZiel() method to directly input the effects of an action in it's constructor. If I tried to do it any other way, the runnable would already be 
	// saved as a variable and I didn't find a simple way in which I could make it possible to pass parameters to that runnable... I could've just made it 
	// it always take Player 2's stats, but I wanted it to be adapted to the point of being possible to use with multiple opponents. With this system that's
	// a possibility. Also, I'm saving the files externally, so this means hard coding it, wouldn't really be a good way to do it.
	private Spieler ziel;
	
	// Same story as above, except here I realised, that I couldn't possibly pass the text from the GUI to the method, without having to keep it in the code,
	// so I added this string in order to pass it via an attribute of the Aktion class itself. This string will be used to edit the console text in the GUI.
	private String consoleText;
	
	public Aktion()
	{
		setID(0);
		setName("");
		setDesc("");
		setActDesc("");
		setFailDesc("");
		setDmg(0);
		setHealing(0);
		setOwner("");
		setChance(0);
		setAktion(null);
		//setEffektList(new HashSet<StatusEffekt>());
		setZiel(null);
	}
	
	// I recently removed the runnable parameter, because initializing an action with it and referencing that same action within it, will cause errors and 
	// since pretty much all actions will require that, it's best to force the programmer to set it later, so that those errors are avoided straight away.
	public Aktion(int idVar, String nameVar, String descVar, String actDesc, String failDesc, double dmgVar, double healingVar, String ownerVar, int chanceVar/*, Runnable aktVar , Collection<StatusEffekt> statVar*/)
	{
		setID(idVar);
		setName(nameVar);
		setDesc(descVar);
		setActDesc(actDesc);
		setFailDesc(failDesc);
		setDmg(dmgVar);
		setHealing(healingVar);
		setOwner(ownerVar);
		setChance(chanceVar);
		setAktion(null);
		//setEffektList(statVar);
		
		// The target is not supposed to be defined in the moment the character is created, but rather later on after the character select
		setZiel(null);
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

	public String getDesc() 
	{
		return desc;
	}

	public void setDesc(String desc) 
	{
		this.desc = desc;
	}

	public String getActDesc() 
	{
		return actDesc;
	}

	public void setActDesc(String actDesc) 
	{
		this.actDesc = actDesc;
	}

	public Runnable getAktion() 
	{
		return aktion;
	}

	public void setAktion(Runnable aktion) 
	{
		this.aktion = aktion;
	}

	/*public Collection<StatusEffekt> getEffektList() 
	{
		return effektList;
	}

	public void setEffektList(Collection<StatusEffekt> effektList) 
	{
		this.effektList = effektList;
	}*/

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public Spieler getZiel() 
	{
		return ziel;
	}

	public void setZiel(Spieler ziel) 
	{
		this.ziel = ziel;
	}

	public String getConsoleText() 
	{
		return consoleText;
	}

	public void setConsoleText(String consoleText) 
	{
		this.consoleText = consoleText;
	}

	@Override
	public void LoadData(String[] tokens) 
	{
		try 
		{
			this.id = Integer.parseInt(tokens[0]);
			this.name = tokens[1];
			this.desc = tokens[2];
			this.actDesc = tokens[3];
			this.failDesc = tokens[4];
			this.dmg = Double.parseDouble(tokens[5]);
			this.healing = Double.parseDouble(tokens[6]);
			this.staminaSelf = Double.parseDouble(tokens[7]);
			this.stamina = Double.parseDouble(tokens[8]);
			this.owner = tokens[9];
			this.chance = Integer.parseInt(tokens[10]);
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
			fileWriter.append(this.id + ";" + this.name + ";" + this.desc + ";" + this.actDesc + ";" + this.failDesc + ";" + this.dmg + ";" + this.healing + ";" + this.staminaSelf + ";" + this.stamina + ";" + this.owner + ";" + this.chance);
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
		return this.id + " / " + this.name + " / " + this.desc + " / " + this.actDesc + " / " + this.failDesc + " / " + this.dmg + " / " + this.healing + " / " + this.staminaSelf + " / " + this.stamina + " / " + this.owner + " / " + this.chance;
	}

	public String getFailDesc() 
	{
		return failDesc;
	}

	public void setFailDesc(String failDesc) 
	{
		this.failDesc = failDesc;
	}

	public double getHealing() 
	{
		return healing;
	}

	public void setHealing(double healing) 
	{
		this.healing = healing;
	}

	public double getDmg() 
	{
		return dmg;
	}

	public void setDmg(double dmg) 
	{
		this.dmg = dmg;
	}

	public String getOwner() 
	{
		return owner;
	}

	public void setOwner(String owner) 
	{
		this.owner = owner;
	}
	
	public int getChance() 
	{
		return chance;
	}

	public void setChance(int chance) 
	{
		this.chance = chance;
	}
	
	public Spieler RunDMG(Spieler player, Spieler target)
	{
		target.getCharacter().setHP(target.getCharacter().getHP() - (this.dmg * player.getCharacter().getDmgMultiplier()));
		return target;
	}
	
	public Spieler RunHealing(Spieler player)
	{
		player.getCharacter().setHP(player.getCharacter().getHP() + (this.healing * player.getCharacter().getDmgMultiplier()));
		return player;
	}
	
	public Spieler GiveStaminaSelf(Spieler player)
	{
		player.getCharacter().setStamina(player.getCharacter().getStamina() + this.staminaSelf);
		return player;
	}
	
	public Spieler TakeStamina(Spieler target)
	{
		target.getCharacter().setStamina(target.getCharacter().getStamina() - this.stamina);
		return target;
	}

	public double getStamina() 
	{
		return stamina;
	}

	public void setStamina(double stamina) 
	{
		this.stamina = stamina;
	}
	
	public double getStaminaSelf() 
	{
		return staminaSelf;
	}

	public void setStaminaSelf(double stamina) 
	{
		this.staminaSelf = stamina;
	}
	
	public List<Spieler> RunAction(Spieler player, Spieler target)
	{
		List<Spieler> players = new List<Spieler>("SpielerListe.txt", "id;name", "Spieler");
		
		RunDMG(player, target);
		RunHealing(player);
		GiveStaminaSelf(player);
		TakeStamina(target);
		
		players.add(player);
		players.add(target);
		
		return players;
	}

}
