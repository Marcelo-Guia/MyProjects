package TrafficLight;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class List<T> {
	
	private Collection<T> objectList;
	
	public List()
	{
		 this.objectList = new HashSet<T>();
	}
	
	public int getID(TrafficLight objectVar)
	{
		return objectVar.getID();
	}
	
	public Collection<T> getObjectList()
	{
		return objectList;
	}
	
	public void add(T objectVar)
	{
		objectList.add(objectVar);
	}
	
	public void delete(int removeID)
	{
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			if (getID((TrafficLight) i.next()) == removeID)
			{
				i.remove();
				break;
			}
			else if (!i.hasNext())
			{
				System.out.println("There is no Trafficlight with that ID.");
			}
		}
	}
	
	public void turnOff(String connection)
	{
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			TrafficLight tF = (TrafficLight) i.next();
			if (tF.getConnectedWith().equals(connection)) 
			{
				tF.turnOff();
			}
		}
	}
	
	public void turnOnGreen(String connection)
	{
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			TrafficLight tF = (TrafficLight) i.next();
			if (tF.getConnectedWith().equals(connection)) 
			{
				tF.turnOn("green");
			}
		}
	}
	
	public void turnOnRed(String connection)
	{
		for (Iterator<T> i = objectList.iterator(); i.hasNext();)
		{
			TrafficLight tF = (TrafficLight) i.next();
			if (tF.getConnectedWith().equals(connection)) 
			{
				tF.turnOn("red");
			}
		}
	}
	
	@Override
	public String toString()
	{
		return objectList.toString();
	}

}
