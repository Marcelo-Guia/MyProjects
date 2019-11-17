package MobileManagement;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Menu {
	
/*	So here the only 3 things that changed are: A new bool attribute and a change to the function
 * 	executions, so that now you can enable or disable a function (won't be shown or work even if
 *  the key is pressed), the function GetMenuItemFunction has been removed and replaced with a 
 * 	more user-friendly and flexible function called GetMenuItem, that requests the MenuItem itself 
 * 	instead of the function, which allows you to access all function under the MenuItem class and 
 * 	last but not least, as a direct result of that last change I mentioned, the MenuItem class
 * 	is now public. This means there's technically less protection, but I haven't found any issues 
 * 	with it so far and it works wonders as now instead of needing to create a more complex 
 * 	function in the Menu class that accesses the functions in the MenuItem class, you simply 
 * 	only need to instead write: 
 * 
 * 	your_menu_variable.GetMenuItem('the_character_of_the_MenuItem').name_of_the_function()
 * 
 * 	As always contact me if you found improvements so we can share it with the class.
 */

	protected Collection <MenuItem> items = new HashSet<MenuItem>();
	protected String title;
	
	public interface Function 
	{
		void eval();
	}
	
	public Menu()
	{
		this.title = "Menu";
	}
	
	public Menu(String title)
	{
		this.title = title;
	}

	public void Display() 
	{
		System.out.println(this.title);
		for (Iterator<MenuItem> i = this.items.iterator(); i.hasNext();)
		{
			MenuItem item = i.next();
			if (item.isEnabled()) 
			{
				System.out.println(item);
			}
		}
		System.out.println("\n0   end\n");
		System.out.println("Please choose:");
		Scanner scan = new Scanner(System.in);
		char key = scan.next().charAt(0);
		
		if (key == '0')
		{
			return;
		}
		else
		{
			Function funct = this.GetMenuItem(key).getFunct();
			if (funct != null && this.GetMenuItem(key).isEnabled())
			{
				funct.eval();
			}
			else
			{
				System.out.println("That function does not exist or is disabled!\nTry again:");
			}
			System.out.println("");
			scan.nextLine();
			this.Display();
		}
	}
	
	public MenuItem GetMenuItem(char key)
	{
		for (Iterator<MenuItem> i = this.items.iterator(); i.hasNext();)
		{
			MenuItem item = (MenuItem) i.next();
			if (item.getKey() == key)
			{
				return item;
			}
		}
		System.out.println("No such menu item exists!");
		return null;
	}
	
	public void Add(char key, String description, Menu.Function funct)
	{
		MenuItem i = new MenuItem(key, description, funct);
		items.add(i);
	}
	
	public class MenuItem {

		protected char key;
		protected String description;
		protected Menu.Function funct;
		private boolean enabled;
		
		public MenuItem(char key, String description, Menu.Function funct)
		{
			this.key = key;
			this.description = description;
			this.funct = funct;
			this.setEnabled(true);
		}
		
		public char getKey() 
		{
			return key;
		}

		public Menu.Function getFunct() 
		{
			return funct;
		}

		@Override
		public String toString()
		{
			return this.key + "   " + this.description;
		}
		
		@Override 
		public int hashCode()
		{
			int hash = 5;
			hash = 29 * hash + this.key;
			return hash;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if (obj == null)
			{
				return false;
			}
			else if (getClass() != obj.getClass())
			{
				return false;
			}
			
			final MenuItem other = (MenuItem) obj;
			if (this.key != other.key)
			{
				return false;
			}
			
			return true;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}
}

