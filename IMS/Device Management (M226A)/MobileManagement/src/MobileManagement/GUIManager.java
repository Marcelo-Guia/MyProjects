package MobileManagement;


import java.awt.EventQueue;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

public class GUIManager {

	private JFrame frame;
	static private List<Device> deviceList = new List<Device>("DeviceList.dat");
	static private List<Person> personList = new List<Person>("PersonList.dat");
	static private List<Ownership> ownershipList = new List<Ownership>("OwnershipList.dat");
	static private Scanner scan = new Scanner(System.in);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					// Create Files if they don't exist.
					File file1 = new File("DeviceList.dat");
					File file2 = new File("PersonList.dat");
					File file3 = new File("OwnershipList.dat");
					
					if (file1.createNewFile())
					{
						System.out.println("It seems your DeviceList.dat file was missing, so a new one was created for you.");
						deviceList.save(deviceList);
					}
					if (file2.createNewFile())
					{
						System.out.println("It seems your PersonList.dat file was missing, so a new one was created for you.");
						personList.save(personList);
					}
					if (file3.createNewFile())
					{
						System.out.println("It seems your OwnershipList.dat file was missing, so a new one was created for you.");
						ownershipList.save(ownershipList);
					}
					
//					Starting Process
					deviceList = deviceList.load();
					personList = personList.load();
					ownershipList = ownershipList.load();
					
					System.out.println(deviceList);
					System.out.println(personList);
					System.out.println(ownershipList);
					
					// GUI
					GUIManager window = new GUIManager();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIManager() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		this.frame = new JFrame();
		this.frame.setBounds(700, 400, 500, 500);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		
		GraphicsPanel panel = new GraphicsPanel(490, 490);
		this.frame.getContentPane().add(panel);
		
		// Simple demo of the EasyButton Class: Param. are: Name, WidthOfPanel, HeightOfPanel, WidthOfButton, HeightOfButton, XOffset, YOffset, Class::Function
		// This allows for easy and precise placement with little code. Both offsets are from the middle: + to the right/bottom, - to the left/top
		// Still needs some tweaking on the y axis, but is running just fine everywhere else
		EasyButton button = new EasyButton("Demobutton", 500, 400, 100, 40, -150, 0, () -> GUIManager.runFunct(0));
		
		EasyButton add = new EasyButton("Add", 500, 500, 100, 40, 0, -150, () -> GUIManager.runFunct(1));
		EasyButton delete = new EasyButton("Delete", 500, 500, 100, 40, 0, -75, () -> GUIManager.runFunct(2));
		EasyButton update = new EasyButton("Update", 500, 500, 100, 40, 0, 0, () -> GUIManager.runFunct(3));
		EasyButton showDevices = new EasyButton("Show Devices", 500, 500, 100, 40, 0, 75, () -> GUIManager.showDevices(scan));
		EasyButton showOwners = new EasyButton("Show Owners", 500, 500, 100, 40, 0, 150, () -> GUIManager.runFunct(5));
		
		// Add the button using the getButton() function
		panel.add(button.getButton());
		
		panel.add(add.getButton());
		panel.add(delete.getButton());
		panel.add(update.getButton());
		panel.add(showDevices.getButton());
		panel.add(showOwners.getButton());
	}
	
	public static void runFunct(int num)
	{
		System.out.println("Button pressed: " + num);
	}
	
	public static void showDevices(Scanner scan)
	{
		Ownership own = new Ownership();
		own.showDevices(scan, deviceList, ownershipList);
	}

}
