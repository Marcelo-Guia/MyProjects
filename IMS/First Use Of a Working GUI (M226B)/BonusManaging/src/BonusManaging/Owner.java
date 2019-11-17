package BonusManaging;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Owner {

	private JFrame frame;
	static private List<Category> categoryList = new List<Category>("CategoryList.dat");
	static private List<Person> personList = new List<Person>("PersonList.dat");
	static private List<YearRelation> yearRelList = new List<YearRelation>("YearRelation.dat");
	static private JTextField txtToDisplay;
	static private int totalSales;
	static private int totalBonuses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					// Initialization with 2 normal employees, 2 manager employees and 2 years, also 2 Categories;
					categoryList.add(new Category(0, "SalesDep0", 100000));
					categoryList.add(new Category(1, "SalesDep1", 80000));
					personList.add(new Person(0, "John0", "Howard0", categoryList.getObject(0), "Basel0", false));
					personList.add(new Person(1, "John1", "Howard1", categoryList.getObject(1), "Basel1", false));
					personList.add(new Person(2, "John2", "Howard2", categoryList.getObject(0), "Basel2", true));
					personList.add(new Person(3, "John3", "Howard3", categoryList.getObject(1), "Basel3", true));
					yearRelList.add(new YearRelation(0, personList.getObject(0), 1, 20000, yearRelList));
					yearRelList.add(new YearRelation(1, personList.getObject(0), 1, 28000, yearRelList));
					yearRelList.add(new YearRelation(2, personList.getObject(1), 1, 18000, yearRelList));
					yearRelList.add(new YearRelation(3, personList.getObject(1), 1, 28000, yearRelList));
					yearRelList.add(new YearRelation(4, personList.getObject(2), 2, 40000, yearRelList));
					yearRelList.add(new YearRelation(5, personList.getObject(2), 2, 30000, yearRelList));
					yearRelList.add(new YearRelation(6, personList.getObject(3), 2, 80000, yearRelList));
					yearRelList.add(new YearRelation(7, personList.getObject(3), 2, 40000, yearRelList));
					
					// GUI
					Owner window = new Owner();
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
	public Owner() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		totalSales = 0;
		
		this.frame = new JFrame();
		this.frame.setBounds(550, 170, 800, 800);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		
		GraphicsPanel panel = new GraphicsPanel(800, 800);
		this.frame.getContentPane().add(panel);
		
		txtToDisplay = new JTextField();
		txtToDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		txtToDisplay.setEditable(false);
		txtToDisplay.setText("Hello, please choose an option!");
		txtToDisplay.setBounds(50, 440, 700, 300);
		panel.add(txtToDisplay);
		txtToDisplay.setColumns(10);
		
		/*// Simple demo of the EasyButton Class: Param. are: Name, WidthOfPanel, HeightOfPanel, WidthOfButton, HeightOfButton, XOffset, YOffset, Class::Function
		// This allows for easy and precise placement with little code. Both offsets are from the middle: + to the right/bottom, - to the left/top
		// Still needs some tweaking on the y axis, but is running just fine everywhere else
		EasyButton button = new EasyButton("Demobutton", 800, 400, 100, 40, -150, 0, () -> Owner.runFunct(0));*/
		
		EasyButton showFullList = new EasyButton("Show Full List of Bonuses", 800, 800, 200, 40, 0, -300, () -> Owner.showFullList());
		EasyButton showSmallList = new EasyButton("Show fewer details", 800, 800, 200, 40, 0, -225, () -> Owner.showSmallList());
		EasyButton showTotalSales = new EasyButton("Show total sales", 800, 800, 200, 40, 0, -150, () -> Owner.showTotalSales());
		EasyButton showTotalEmployees = new EasyButton("Show total employees", 800, 800, 200, 40, 0, -75, () -> Owner.showTotalEmployees());
		EasyButton showTotalBonuses = new EasyButton("Show total bonuses", 800, 800, 200, 40, 0, 0, () -> Owner.showTotalBonuses());
		
		/*// Add the button using the getButton() function
		panel.add(button.getButton());*/
		
		panel.add(showFullList.getButton());
		panel.add(showSmallList.getButton());
		panel.add(showTotalSales.getButton());
		panel.add(showTotalEmployees.getButton());
		panel.add(showTotalBonuses.getButton());
	}
	
	/*public static void runFunct(int num)
	{
		System.out.println("Button pressed: " + num);
	}*/
	
	public static void showFullList()
	{
		txtToDisplay.setText(personList.toString() + yearRelList.toString());
	}
	
	public static void showSmallList()
	{
		System.out.println(personList.toString() + yearRelList.toString());
	}
	
	public static void showTotalSales()
	{
		txtToDisplay.setText("Total sales of year 1: " + yearRelList.displayTotalSales(1, totalSales) + " / Total sales of year 2: " + yearRelList.displayTotalSales(2, totalSales));
		totalSales = 0;
	}
	
	public static void showTotalEmployees()
	{
		txtToDisplay.setText("Current amount of employees: " + personList.getObjectList().size());
	}
	
	public static void showTotalBonuses()
	{
		txtToDisplay.setText("Total bonuses of year 1: " + yearRelList.displayTotalBonuses(1, totalBonuses) + " / Total bonuses of year 2: " + yearRelList.displayTotalBonuses(2, totalBonuses));
		totalBonuses = 0;
	}

}
