package TrafficLight;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TrafficLight extends JPanel{
	
	private int ID;
	private boolean red;
	private boolean green;
	private boolean yellow;
	private boolean turnedOn;
	private String stateOfLights;
	private String connectedWith;
	private JFrame frame;
	private Timer timer;
	private long timeState = 7 * 1000;
	private long timeChange = 2 * 1000;
	private long timeDiff = timeState - timeChange;
	
	public TrafficLight()
	{
		initialize(710, 350);
		this.setConnectedWith("");
		turnOff();
	}
	
	public TrafficLight(String connection)
	{
		initialize(710, 350);
		this.setConnectedWith(connection);
		turnOff();
	}

	/**
	 * Create the application.
	 */
	public TrafficLight(int xVar, int yVar, String connection) 
	{
		initialize(xVar, yVar);
		this.setConnectedWith(connection);
		turnOff();
	}
	
	public TrafficLight getTF()
	{
		return this;
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int xVar, int yVar) 
	{
		frame = new JFrame();
		frame.setBounds(xVar, yVar, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		this.setBounds(0, 0, 490, 490);
		this.setLayout(null);
		frame.getContentPane().add(this);
	}
	
	public void draw()
	{
		repaint();
	}
	
	public void drawTrafficLight(Graphics g, int posX, int posY, int size)
	{
		if (this.red)
		{
			g.setColor(Color.RED);
		}
		else 
		{
			g.setColor(Color.BLACK);
		}
		g.fillOval(posX - (size/2), posY, size, size);
		
		if (this.yellow)
		{
			g.setColor(Color.YELLOW);
		}
		else 
		{
			g.setColor(Color.BLACK);
		}
		g.fillOval(posX - (size/2), posY + (size + (size/2)), size, size);
		
		if (this.green)
		{
			g.setColor(Color.GREEN);
		}
		else 
		{
			g.setColor(Color.BLACK);
		}
		g.fillOval(posX - (size/2), posY + (2 * (size + (size/2))), size, size);
	}
	
	public void turnOff()
	{
		if (turnedOn) 
		{
			this.stateOfLights = "turnedoff";
			updateLights();
			this.turnedOn = false;
			draw();
			timer.cancel();
		}
	}
	
	public void turnOn(String state)
	{
		if (!turnedOn) 
		{
			timer = new Timer();
			this.turnedOn = true;
			stateOfLights = state;
			updateLights();
			draw();
			changeLightsTimer2();
			timer.scheduleAtFixedRate(new TimerTask() {
				  @Override
				  public void run() 
				  {
					  changeLights();
					  draw();
					  changeLightsTimer2();
				  }
			}, timeState, timeState);
		}
	}
	
	public void changeLightsTimer2()
	{
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() 
			  {
				  changeLights();
				  draw();
			  }
		}, timeDiff);
	}
	
	public void changeLights()
	{
		switch (stateOfLights)
		{
		case "red":
			stateOfLights = "redtogreen";
			break;
		case "green": 
			stateOfLights = "greentored";
			break;
		case "redtogreen": 
			stateOfLights = "green";
			break;
		case "greentored": 
		case "turnedoff":
			stateOfLights = "red";
			break;
		}
		updateLights();
	}
	
	public void updateLights()
	{
		if (turnedOn) 
		{
			switch (stateOfLights) 
			{
			case "red":
				this.red = true;
				this.green = false;
				this.yellow = false;
				break;
			case "green":
				this.red = false;
				this.green = true;
				this.yellow = false;
				break;
			case "redtogreen":
				this.red = true;
				this.green = false;
				this.yellow = true;
				break;
			case "greentored":
				this.red = false;
				this.green = false;
				this.yellow = true;
				break;
			case "turnedoff":
				this.red = false;
				this.green = false;
				this.yellow = false;
				break;
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawTrafficLight(g, 250, 60, 50);
	}

	public String getConnectedWith() 
	{
		return connectedWith;
	}

	public void setConnectedWith(String connectedWith) 
	{
		this.connectedWith = connectedWith;
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
