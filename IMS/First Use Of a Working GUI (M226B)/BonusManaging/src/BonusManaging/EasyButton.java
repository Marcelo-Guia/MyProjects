package BonusManaging;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EasyButton extends JButton {
	
	private JButton button;
	
	public EasyButton(String name, int panelWidth, int panelHeight, int width, int height, int widthOffset, int heightOffset, Runnable funct)
	{
		this.button = new JButton(name);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				funct.run();
			}
		});
		button.setBounds(panelWidth/2-(width/2)+widthOffset, panelHeight/2-(height/2)+heightOffset, width, height);
	}

	public JButton getButton() 
	{
		return button;
	}

	public void setButton(JButton button) 
	{
		this.button = button;
	}

}
