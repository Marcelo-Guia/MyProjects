package Spiel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GUIManager {

	// Create the frame
	private JFrame frame;
	
	// 
	static private GUIManager window;
	
	// Create all the lists, that will load the objects of the game
	static private List<Aktion> aktionenListe = new List<Aktion>("Aktionen.txt", "id;name;description;actiondescription", "Aktion");
	static private List<Charakter> charaktereListe = new List<Charakter>("Charaktere.txt", "id;name;health;stamina;damagemultiplier", "Charakter");
	static private List<Spieler> spielerListe = new List<Spieler>("SpielerListe.txt", "id;name", "Spieler");
	
	// Create all the panels that will be available in the game (Part of GUIManager)
	private GraphicsPanel fähigkeitenAuswahl;
	private GraphicsPanel errorScreen;
	private GraphicsPanel kampfBildschirm1;
	private GraphicsPanel kampfBildschirm2;
	private GraphicsPanel kampfBildschirm3;
	
	// Create the current player (used to store whoever's turn it is)
	static private Spieler currentPlayer;
	
	// Create a current character for the character selection (since Java is pass by value, this is necessary for the code to work)
	static private Charakter currentChar;
	
	// String used for the description of what happened in the last round
	static private String desc;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					// Here we create an error String to check whether the files are there or not. The function is further explained 
					// down below, but in short: If the files are all found the error string will return a text describing so. Otherwise
					// the string will contain 1-3 errors depending on how many of the 3 files were found. 
					String error = DatenLaden();
					
					// Initialize the GUI part of the game and set it visible
					window = new GUIManager(error);
					window.frame.setVisible(true);
					
					// If the error string came out correctly, go through the game
					if (error == "Alle Dateien vorhanden.\nDaten wurden geladen.\nCharakterauswahl wird gestartet...\nDies braucht nur ein kurzer Moment...")
					{
						// Initialize the current player
						currentPlayer = new Spieler();
						
						// Start the character select
						window.FähigkeitenAuswählen();
					}
					// Otherwise set up a button for the errorscreen, that allows the player to leave the game
					else
					{
						// Create an EasyButton with the function of exiting with an OK status for the error panel
						EasyButton exit = new EasyButton("Spiel schliessen", new Runnable()  
					    {    
					        public void run()  
					        {
					        	System.exit(0);
					        }
					    });
						
						// Add a rigid area to create some space between the text and the button and add the button itself to the error panel
						window.errorScreen.add(Box.createRigidArea(new Dimension(0,50)));
						window.errorScreen.add(exit.getButton());
						// Set some other properties of the button to make it prettier and avoid weird looking errors
						exit.getButton().setMaximumSize(new Dimension(150, 50));
						exit.getButton().setAlignmentX(Component.CENTER_ALIGNMENT);
					}
				} 
				catch (Exception e) 
				{
					//e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.toString());
					System.exit(0);
				}
			}
		});
	}

	// Create the application.
	public GUIManager(String error) 
	{
		try
		{
			// Initialize the frame and the panels
			initialize(error);
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String error) 
	{
		try
		{
			// Set up the frame for the GUI
			frame = new JFrame();
			frame.setBounds(0, 0, 1920, 1080);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			// Initialize the panels and set their layouts
			fähigkeitenAuswahl = new GraphicsPanel(1902, 1033, 2);
			errorScreen = new GraphicsPanel(1902, 1033, 1);
			kampfBildschirm1 = new GraphicsPanel(1902, 400, 3);
			kampfBildschirm3 = new GraphicsPanel(1902, 400, 3);
			kampfBildschirm2 = new GraphicsPanel(1902, 233, 4);
			fähigkeitenAuswahl.getGP().setLayout(new BoxLayout(fähigkeitenAuswahl, BoxLayout.PAGE_AXIS));
			errorScreen.getGP().setLayout(new BoxLayout(errorScreen, BoxLayout.PAGE_AXIS));
			kampfBildschirm1.getGP().setLayout(new BoxLayout(kampfBildschirm1, BoxLayout.PAGE_AXIS));
			kampfBildschirm2.getGP().setLayout(new BoxLayout(kampfBildschirm2, BoxLayout.LINE_AXIS));
			kampfBildschirm3.getGP().setLayout(new BoxLayout(kampfBildschirm3, BoxLayout.LINE_AXIS));
			
			// This is the configuration for the errorscreen
			
			JTextArea errorText = new JTextArea(error);
			// Add a little space on the top
			errorScreen.add(Box.createRigidArea(new Dimension(0,50)));
			// Set it's maximum size and align it to the center
			errorText.setMaximumSize(new Dimension(1500, 700));
			errorText.setAlignmentX(Component.CENTER_ALIGNMENT);
			// Add it to the panel
			errorScreen.add(errorText);
			// Set it's font, set the settings for the wrap, set the background and text colors and make it non-editable
			errorText.setFont(errorText.getFont().deriveFont(40f));
	        errorText.setLineWrap(true);
	        errorText.setWrapStyleWord(true);
	        errorText.setBackground(frame.getBackground());
	        errorText.setEnabled(false);
	        errorText.setDisabledTextColor(Color.DARK_GRAY);
			
	        // Add the panel to the frame
			frame.getContentPane().add(errorScreen);
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	// This function simply changes the panel of the frame passed as the 1st parameter, to the one passed as the following parameters
	public void ChangePanel(JFrame frame, GraphicsPanel ... panelsToChange)
	{
		try
		{
			// Loop through all the panels
			for (int i = 0; i < panelsToChange.length; i++)
			{
				// If it's the first panel, set the content pane
				if (i == 0)
				{
					frame.setContentPane(panelsToChange[i]);
				}
				// Otherwise just add it to the content pane
				else
				{
					frame.getContentPane().add(panelsToChange[i]);
				}
			}
			
			// Revalidate it to finish
			frame.revalidate();
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	public void FähigkeitenAuswählen()
	{
		try
		{
			// Start the character select and let it go until the character is selected
			SelectCharacter(0);
	        
	        ChangePanel(window.frame, window.fähigkeitenAuswahl);
	        
	        // The first player is notified, that it's his turn to select a character
	        JOptionPane.showMessageDialog(null, spielerListe.getObject(0).getName() + " wählt zuerst sein Character.");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	// This function checks whether the files are present or not
	public static String DataCheck(String error, String fileName)
	{
		try
		{
			// Create or find a file with the given filename
			File file = new File(fileName);
			// If the file is created, that means it's missing and was not found
			if (file.createNewFile())
			{
				// If that's the case add the error to the string and delete the new created file, as it will be empty
				error += "Es sieht so aus als ob die " + fileName + " Datei fehlt, bitte laden Sie diese neu.\n";
				file.delete();
			}
		}
		catch (IOException e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
		// Return the error
		return error;
	}
	
	public static String DatenLaden()
	{
		// The error String is created outside the try catch blocks, in order to ensure that the value is passed in the end, otherwise it gives an error
		String error = "";
		try 
		{
			// Check whether the files are present or not
			error = DataCheck(error, "Aktionen.txt");
			error = DataCheck(error, "Charaktere.txt");
			error = DataCheck(error, "SpielerListe.txt");
			
			// If the error is empty, then everything went well
			if (error == "")
			{
				// First the data is loaded using the LoadData() function in the List class
				aktionenListe.LoadData();
				charaktereListe.LoadData();
				spielerListe.LoadData();
				
				// Set the error to this specific string, so the code later knows the loading process was successful. This will
				// also be displayed in the errorscreen, so in case the code is slow for whatever reason, the user knows it's loading the game
				error = "Alle Dateien vorhanden.\nDaten wurden geladen.\nCharakterauswahl wird gestartet...\nDies braucht nur ein kurzer Moment...";
			}
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
		return error;
	}
	
	public void ShowCharacter(String characterName, int charID)
	{
		try
		{
			// Create the text for the character
			JTextArea Charactertext = new JTextArea(characterName + ":\nName: " + charaktereListe.getObject(charID).getName() + ", HP: " + charaktereListe.getObject(charID).getHP() + ", Stamina: " + charaktereListe.getObject(charID).getStamina() + ", DMG Multiplier: " + charaktereListe.getObject(charID).getDmgMultiplier());
			// Add some space above the text
			window.fähigkeitenAuswahl.add(Box.createRigidArea(new Dimension(0,100)));
			// Set it's maximum size and align it to the center
			Charactertext.setMaximumSize(new Dimension(1500, 150));
			Charactertext.setAlignmentX(Component.CENTER_ALIGNMENT);
			// Add the text to the panel
			window.fähigkeitenAuswahl.add(Charactertext);
			// Set the font, colors etc.
			Charactertext.setFont(Charactertext.getFont().deriveFont(40f));
			Charactertext.setLineWrap(true);
	        Charactertext.setWrapStyleWord(true);
	        Charactertext.setBackground(window.frame.getBackground());
	        Charactertext.setEnabled(false);
	        // If the current character chosen is this one, make the text orange to show it is selected.
	        if (currentChar == charaktereListe.getObject(charID))
	        {
	        	Charactertext.setDisabledTextColor(Color.ORANGE);
	        }
	        // Otherwise make it the normal color, so it's unselected.
	        else
	        {
	        	Charactertext.setDisabledTextColor(Color.DARK_GRAY);
	        }
	        // Create the button that selects this character, add it to the panel and align it to the center.
	        EasyButton choose = new EasyButton(characterName + " Auswählen", () -> window.SelectCharacter(charID));
	        window.fähigkeitenAuswahl.add(choose.getButton());
			choose.getButton().setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	// A function that is responsible for setting the current character, refreshing the Panel and redoing all the code from ShowCharacter.
	// Behaves similarly to a loop.
	public void SelectCharacter(int charID)
	{
		try
		{
			// Set the current character to the selected character
			currentChar = charaktereListe.getObject(charID);
			// Refresh the panel to change the text colors and redo the entire panel
			RefreshPanel(window.frame, window.fähigkeitenAuswahl);
			
			// Create all the text for the character and the button to select it
			ShowCharacter("Character 1", 0);
			ShowCharacter("Character 2", 1);
			ShowCharacter("Character 3", 2);
			
			// If player 1 has no character create a button that assigns it and restarts the process
			if (spielerListe.getObject(0).getCharacter() == null)
			{
				// Create a button to confirm the selection of the character and add it to the panel
				EasyButton auswählen = new EasyButton("Auswahl Bestätigen", new Runnable()
				{
					public void run()
					{
						// Assign the character to the player
						spielerListe.getObject(0).setCharacter(currentChar);
						
						// Player 2 is notified, that it's his turn to select a character
						JOptionPane.showMessageDialog(null, spielerListe.getObject(1).getName() + " wählt jetzt sein Character.");
						
						// Restart the process for the second player
						SelectCharacter(0);
					}
				});
				// Add a rigid area to create some space between the text and the button and add it to the panel
				window.fähigkeitenAuswahl.add(Box.createRigidArea(new Dimension(0,50)));
				window.fähigkeitenAuswahl.add(auswählen.getButton());
				// Set some other properties of the button to make it prettier and avoid weird looking errors
				auswählen.getButton().setMaximumSize(new Dimension(300, 50));
				auswählen.getButton().setAlignmentX(Component.CENTER_ALIGNMENT);
			}
			//  Otherwise do the same except for the second player and start the battle instead of restarting the process
			else
			{
				// Create a button to confirm the selection of the character and add it to the panel
				EasyButton auswählen = new EasyButton("Auswahl Bestätigen und Kampf starten", new Runnable()
				{
					public void run()
					{
						// Assign the character to the player
						spielerListe.getObject(1).setCharacter(currentChar);
						
						// If both players choose the same character, make a copy of that character for the second player.
						// This prevents both players from being assigned with the same character and therefore, later
						// when the abilities are used, only one of them will be affected. This bypasses Java's tendency
						// to make non-primitive values pass-by-reference and makes both characters independent from 
						// from one another.
						if (spielerListe.getObject(0).getCharacter() == spielerListe.getObject(1).getCharacter())
						{
							spielerListe.getObject(1).setCharacter(new Charakter(spielerListe.getObject(0).getCharacter()));
						}
						
						// Start the battle
						KampfStarten();
					}
				});
				// Add a rigid area to create some space between the text and the button and add it to the panel
				window.fähigkeitenAuswahl.add(Box.createRigidArea(new Dimension(0,50)));
				window.fähigkeitenAuswahl.add(auswählen.getButton());
				// Set some other properties of the button to make it prettier and avoid weird looking errors
				auswählen.getButton().setMaximumSize(new Dimension(300, 50));
				auswählen.getButton().setAlignmentX(Component.CENTER_ALIGNMENT);
			}
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	// Similar to change panel, but this one is used to refresh the panel, by removing all content and resetting the panel.
	public void RefreshPanel(JFrame frame, GraphicsPanel panelToChange)
	{
		try
		{
			// Remove content from the content pane
			frame.getContentPane().removeAll();
			// Reset the content pane to the panel and revalidate it
			frame.setContentPane(panelToChange);
			frame.revalidate();
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	// Start the battle by setting player 1 as the current player and changing the panel to the Battle panel
	public void KampfStarten()
	{
		try
		{
			currentPlayer = spielerListe.getObject(0);
			
			// Initialize the battlescreen
			SetBattleScreen();
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	public void SetBattleScreen()
	{
		try
		{
			// Remove all contents from the previous round, so that they aren't duplicated for this round
			window.kampfBildschirm1.removeAll();
			window.kampfBildschirm3.removeAll();
			window.kampfBildschirm2.removeAll();
			
			// If the description is null, set it to the start of the battle and mention who's first
			if (desc == null) 
			{
				desc = "Der Kampf hat gestartet!\n" + currentPlayer.getName() + " ist jetzt dran.";
			}
			
			// Set the text for the character stats, using the toString of the character class
			String char1Text = spielerListe.getObject(0).getName()+ "\n" + spielerListe.getObject(0).getCharacter();
			String char2Text = spielerListe.getObject(1).getName()+ "\n" + spielerListe.getObject(1).getCharacter();
			
			// Set the description text for when nothing was hovered over
			String actDesc = "Beschreibung:\n";
			
			// Set the different colors for selected, deselected characters and for the deselected text of the character's stats
			Color selectedChar = Color.ORANGE;
			Color deselectedChar = Color.LIGHT_GRAY;
			Color disText = Color.GRAY;
			
			// Create and add all the TextAreas to create the description of what happened in the last round, the description of
			// the action and both player's statistics.
			JTextArea descText = makeConsole(kampfBildschirm1, 0, 50, 1500, 300, desc, Color.LIGHT_GRAY, Color.DARK_GRAY);
			JTextArea char1Data = makeConsole(kampfBildschirm3, 0, 50, 450, 400, char1Text, Color.LIGHT_GRAY, Color.DARK_GRAY);
			JTextArea actDescText = makeConsole(kampfBildschirm3, 0, 50, 600, 400, actDesc, Color.DARK_GRAY, Color.WHITE);
			JTextArea char2Data = makeConsole(kampfBildschirm3, 0, 50, 450, 400, char2Text, Color.LIGHT_GRAY, Color.DARK_GRAY);
			
			// If the current player is player 1, set his background to the selected color and player 2's text and background to the deselected colors
			if (currentPlayer == spielerListe.getObject(0))
			{
				char1Data.setBackground(selectedChar);
				char2Data.setBackground(deselectedChar);
				char2Data.setDisabledTextColor(disText);
			}
			// Otherwise do the opposite
			else
			{
				char1Data.setBackground(deselectedChar);
				char1Data.setDisabledTextColor(disText);
				char2Data.setBackground(selectedChar);
			}
			
	        // Create an array that will contain all the Actions contained in the aktionenListe and add them as long as they belong to the 
			// current player's character
			Aktion[] aktionen = new Aktion[aktionenListe.getObjectList().size()];
			// Loop through the list
			for (Iterator<Aktion> i = aktionenListe.getObjectList().iterator(); i.hasNext();)
			{
				// Store the action in a temporary variable
				Aktion objectVar = i.next();
				// If the current action's owner is the current player's character or everyone, add it to the array
				if (currentPlayer.getCharacter().getName().equals(objectVar.getOwner()) || objectVar.getOwner().equals("Alle"))
				{
					aktionen[objectVar.getID()] = objectVar;
				}
			}
			
			// Create an array for the buttons (total of 5 per character)
			EasyButton[] buttons = new EasyButton[5];
			
			// Create an outside variable that will be used for the buttons
			int x = 0;
			// Loop through all the actions and create a variable for the actions
			for (int i = 0; i < aktionen.length; i++)
			{
				// If the current action isn't empty, make a button out of it and add it
				if (aktionen[i] != null)
				{
					// Make a final int out of i to prevent errors below
					final int slot = i;
					// the current button slot will be equal to a new button containing the action's information
					buttons[x] = new EasyButton(aktionen[i].getName(), new Runnable()
							{
								public void run()
								{
									// Store the other player's ID so we can make hi the current player later
									int nextPlayerID = spielerListe.getOtherPlayer(currentPlayer).getID();
									
									// A random number between 1 and 100 is created
						        	Random randy = new Random();
						        	int result = randy.nextInt(100) + 1;
						        	
						        	// If the stamina of the current player is lower than 10, he gets 10% less chance
						        	if (currentPlayer.getCharacter().getStamina() < 20)
						        	{
						        		result += 10;
						        	}
						        	// If it's below 50, he only gets a reduction of 5% chance
						        	else if (currentPlayer.getCharacter().getStamina() < 50)
						        	{
						        		result += 5;
						        	}
						        	
						        	// If the number is less than or equal to the probability of the hit, then the function is executed
						        	if (result <= aktionen[slot].getChance())
						        	{
						        		// We run the runAction function, which basically just runs all the numbers contained in the cation's 
						        		// attributes through both player's HP and Stamina
						        		spielerListe = aktionen[slot].RunAction(currentPlayer, spielerListe.getOtherPlayer(currentPlayer));
						        		
						        		// We set the description of this round, to the action description of the action and add a text saying
						        		// which player is next
						        		desc = currentPlayer.getName() + aktionen[slot].getActDesc() + "\n" + spielerListe.getObject(nextPlayerID).getName() + " ist jetzt dran.";
						        	}
						        	// If not, we set the fail description instead and we only run the self stamina reduction/increase, because technically there's
						        	// no effect for the other player if we fail
						        	else
						        	{
						        		// We set this round's description to the fail description contained in the action and mention who's next
						        		desc = currentPlayer.getName() + aktionen[slot].getFailDesc() + "\n" + spielerListe.getObject(nextPlayerID).getName() + " ist jetzt dran.";
						        		
						        		// We run the self stamina through the current payer
						        		currentPlayer = aktionen[slot].GiveStaminaSelf(currentPlayer);
						        	}
						        	
						        	// Since the current player has changed whether he succeeded or not, we can't use him with the getOtherPlayer() function 
						        	// of the list, so we will use the next player's ID, that we stored earlier.
									currentPlayer = spielerListe.getObject(nextPlayerID);
									
									// We add warnings in case the next player has low stamina, so he knows he's losing chance
									if (currentPlayer.getCharacter().getStamina() < 20)
									{
										desc += "\nACHTUNG: Ihre Ausdauer ist unter 20, Treffsicherheiten um 10% vermindert!";
									}
									else if (currentPlayer.getCharacter().getStamina() < 50)
									{
										desc += "\nACHTUNG: Ihre Ausdauer ist unter 50, Treffsicherheiten um 5% vermindert!";
									}
									
									// If the new current player's health was brought down to 0 or less with that last action
									// we go through the final pieces of code and end the game
									if (currentPlayer.getCharacter().getHP() <= 0)
									{
										// We set the set the action description again, but this time, instead of saying who's next, we
										// mention who's lost the game
										desc = spielerListe.getOtherPlayer(currentPlayer).getName() + aktionen[slot].getActDesc() + "\n" + currentPlayer.getName() + " hat verloren.";
										
										// We refresh the Battle screen again, so the text is updated
										SetBattleScreen();
										
										// We display a message saying who won
										JOptionPane.showMessageDialog(null, spielerListe.getOtherPlayer(currentPlayer).getName() + " hat gewonnen!");
										
										// After all that, we exit the game with an OK code of 0
										System.exit(0);
									}
									// If the new current player still has health left, the battle continues and we refresh the screen
									else
									{
										// Refresh the screen
										SetBattleScreen();
									}
								}
							});
					
					// Here we create a dimension, that makes the buttons a little more appealing
					Dimension d = buttons[x].getButton().getPreferredSize();
					d.width = 300;
					d.height = 50;
					
					// And we force all sizes to be set to that new dimension
					buttons[x].getButton().setMaximumSize(d);
					buttons[x].getButton().setMinimumSize(d);
					buttons[x].getButton().setPreferredSize(d);
					
					// Here we also edit the font to change it's size, as there is no direct way of changing it on a button
					Font font = buttons[x].getButton().getFont();
					buttons[x].getButton().setFont(new Font(font.getFontName(), font.getStyle(), 25));
					
					// Here we add a mouse listener to the button, so that every time we hover over a function
					// the action description text, will change to that action's description
					// This way the user is always able to know, what that action actually does
					buttons[x].getButton().addMouseListener(new java.awt.event.MouseAdapter() 
					{
					    public void mouseEntered(java.awt.event.MouseEvent evt) 
					    {
					    	// Set the text of the action description TextArea
					    	actDescText.setText("Beschreibung:\n" + aktionen[slot].getDesc());
					    }
					});
					
					// Add the current button to the part Nr. 2 of the battle screen, which will contain all buttons
					window.kampfBildschirm2.add(buttons[x].getButton());
					// Center the button's alignment, so they all stick to the middle in the end
					buttons[x].getButton().setAlignmentX(Component.CENTER_ALIGNMENT);
					
					// Increment the x variable for the next button
					x++;
				}
			}
			
			// Change the current panel to the battle screen, consisting of 3 panels
			ChangePanel(window.frame, window.kampfBildschirm1, window.kampfBildschirm3, window.kampfBildschirm2);
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
	}
	
	// A function, that creates the TextAreas as they are required in the battle screen. This and the other similar function
	// can't be fused together, because the battle screen has different requirements to the ability screen
	public JTextArea makeConsole(GraphicsPanel panel, int spaceX, int spaceY, int width, int height, String text, Color backCol, Color textCol)
	{
		JTextArea texty = new JTextArea(text);
		try
		{
			// Add a little space on the top
			panel.add(Box.createRigidArea(new Dimension(spaceX, spaceY)));
			// Set it's maximum size and align it to the center
			texty.setMaximumSize(new Dimension(width, height));
			texty.setAlignmentX(Component.CENTER_ALIGNMENT);
			// Add it to the panel
			panel.add(texty);
			// Set it's font, set the settings for the wrap, set the background and text colors and make it non-editable
			texty.setFont(texty.getFont().deriveFont(40f));
			texty.setLineWrap(true);
			texty.setWrapStyleWord(true);
			texty.setBackground(backCol);//frame.getBackground());
			texty.setEnabled(false);
			texty.setDisabledTextColor(textCol);
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString());
			System.exit(0);
		}
		return texty;
	}
}
