package MobileManagement;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GraphicsPanel extends JPanel{
	
	private int ID;
	private JFrame frame;
	
	public GraphicsPanel()
	{
		initialize(500, 500);
	}

	public GraphicsPanel(int widthVar, int heightVar) 
	{
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
}
