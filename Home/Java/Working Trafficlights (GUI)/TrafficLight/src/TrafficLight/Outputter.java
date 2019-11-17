package TrafficLight;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Outputter {

	private JFrame frame;
	private JTextField txtUnedited;
	private String edit;
	private int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Outputter window = new Outputter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Outputter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		edit = "edit 1";
		count = 0;
		
		txtUnedited = new JTextField();
		txtUnedited.setEditable(false);
		txtUnedited.setText("Unedited");
		txtUnedited.setBounds(95, 13, 231, 59);
		panel.add(txtUnedited);
		txtUnedited.setColumns(10);
		
		JTextArea txtrUnedited = new JTextArea();
		txtrUnedited.setEditable(false);
		txtrUnedited.setText("Unedited");
		txtrUnedited.setBounds(95, 85, 231, 69);
		panel.add(txtrUnedited);
		
		JTextPane txtpnUnedited = new JTextPane();
		txtpnUnedited.setEditable(false);
		txtpnUnedited.setText("Unedited");
		txtpnUnedited.setBounds(95, 167, 231, 62);
		panel.add(txtpnUnedited);
		
		JButton btnChangeText = new JButton("Change Text");
		btnChangeText.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				count++;
				txtUnedited.setText(edit);
				txtrUnedited.setText(edit);
				txtpnUnedited.setText(edit);
				edit = "Edit " + count;
			}
		});
		btnChangeText.setBounds(170, 50, 150, 40);
		panel.add(btnChangeText);
	}
}
