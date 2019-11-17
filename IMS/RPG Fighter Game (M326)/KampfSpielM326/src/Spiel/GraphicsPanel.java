package Spiel;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GraphicsPanel extends JPanel {
	
	private int ID;
	private JFrame frame;
	private Runnable graphicsCommand;
	
	public GraphicsPanel()
	{
		setID(0);
		initialize(500, 500);
	}

	public GraphicsPanel(int widthVar, int heightVar, int idVar) 
	{
		setID(idVar);
		initialize(widthVar, heightVar);
	}
	
	public GraphicsPanel getGP()
	{
		return this;
	}

	private void initialize(int widthVar, int heightVar) 
	{
		this.setBounds(0, 0, widthVar, heightVar);
		this.setLayout(null);
	}
	
	public void draw()
	{
		repaint();
	}
	
	public void drawGraphic(Graphics g)
	{
		//graphicsCommand.run();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawGraphic(g);
	}

	public int getID() 
	{
		return ID;
	}

	public void setID(int iD) 
	{
		ID = iD;
	}

	public Runnable getGraphicsCommand() 
	{
		return graphicsCommand;
	}

	public void setGraphicsCommand(Runnable graphicsCommand) 
	{
		this.graphicsCommand = graphicsCommand;
	}

	public JFrame getFrame() 
	{
		return frame;
	}

	public void setFrame(JFrame frame) 
	{
		this.frame = frame;
	}
}
