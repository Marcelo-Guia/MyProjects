package TrafficLight;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

	private JFrame frame;
	static private TrafficLight TFTop = new TrafficLight(710, 20, "vertical");
	static private TrafficLight TFLeft = new TrafficLight(210, 350, "horizontal");
	static private TrafficLight TFRight = new TrafficLight(1210, 350, "horizontal");
	static private TrafficLight TFBottom = new TrafficLight(710, 600, "vertical");
	static private List<TrafficLight> tFList = new List<TrafficLight>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		tFList.add(TFTop);
		tFList.add(TFLeft);
		tFList.add(TFRight);
		tFList.add(TFBottom);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
					TFTop.getFrame().setVisible(true);
					TFLeft.getFrame().setVisible(true);
					TFRight.getFrame().setVisible(true);
					TFBottom.getFrame().setVisible(true);
					Controller window = new Controller();
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
	public Controller() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(710, 350, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 490, 490);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		JButton btnTurnOff = new JButton("Turn Off");
		btnTurnOff.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				tFList.turnOff("vertical");
				tFList.turnOff("horizontal");
			}
		});
		btnTurnOff.setBounds(170, 50, 150, 40);
		panel.add(btnTurnOff);
		
		JButton btnTurnOnVertGreen = new JButton("Turn on (Vertical: Green)");
		btnTurnOnVertGreen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				tFList.turnOnGreen("vertical");
				tFList.turnOnRed("horizontal");
			}
		});
		btnTurnOnVertGreen.setBounds(135, 100, 210, 40);
		panel.add(btnTurnOnVertGreen);
		
		JButton btnTurnOnVertRed = new JButton("Turn on (vertical: red)");
		btnTurnOnVertRed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				tFList.turnOnRed("vertical");
				tFList.turnOnGreen("horizontal");
			}
		});
		btnTurnOnVertRed.setBounds(135, 150, 210, 40);
		panel.add(btnTurnOnVertRed);
	}

}
