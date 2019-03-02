package main;
/*Creates the Cipher selection panel*/

import java.awt.*;    // KEVIN!
import javax.swing.*; // TODO: YOU ARE USING SWING ??? Look up JavaFX in your free time -> it is what is beginning to replace Swing for application building.
import java.awt.event.*;                                                   // (according my CS professor, which is why we are learning JavaFX instead of Swing at UA)

public class MenuPanel extends JFrame
{
	private final int WIDTH  = 2000, HEIGHT = 1000;
	private JLabel banner, title;												//Variable declarations
	private JPanel panel, menuPanel;
	private JButton select;
	private JComboBox<String> options; 
	String[] ciphers = {"Shift", "Affine", "Vigenere", "Playfair", "Hill"};	

	public MenuPanel()									//Generate panels
	{		
		title = new JLabel("Crypt Tools");
		title.setFont(new Font("Verdana", Font.BOLD, 100));
		title.setHorizontalAlignment(JLabel.CENTER);
		banner = new JLabel ("Select Encryption Type: ");
		banner.setFont(new Font("Verdana", Font.BOLD, 40));
		select = new JButton ("Select");
		select.setFont(new Font("Verdana", Font.BOLD, 40));
		options = new JComboBox<String>();
		options.setFont(new Font("Verdana", Font.BOLD, 40));
		
		
		for(int i = 0; i < ciphers.length; i++)
		{
			options.addItem(ciphers[i] + " Cipher");
		}
		
		menuPanel = new JPanel();											//Add elements to panel
		menuPanel.add(banner);
		menuPanel.add(options);
		menuPanel.add(select);
		menuPanel.setLayout(new GridLayout(3, 1));
		
		panel = menuPanel; 
		
		ButtonListener button = new ButtonListener();					//Initialize button listener
		select.addActionListener(button);
		
		add(panel, BorderLayout.CENTER); 
		add(title, BorderLayout.NORTH);
		
		setSize(WIDTH, HEIGHT);
	}
	
	/* 
	 * Reset the main Applet window back to the menu selection screen by re-storing the menu panel into
	 * the panel being viewed.
	 */
	public void setToMenu() {
		remove(panel);
		panel = menuPanel;
		add(panel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
	
	class ButtonListener implements ActionListener						//Action listener for active panel elements
	{
	    public void actionPerformed (ActionEvent event)
	    {
	    	if(event.getSource() == select)
	        {
	    		String s = (String) options.getSelectedItem();
	    		title.setText("Crypt Tools: " + s); 
				
	    		remove(panel);
	    		
	    		switch (s)												//Dropdown selection options
	    		{
	    			case "Shift Cipher":		
	    				panel = CipherPanels.ShiftCipher();					//Generate Shift cipher interface
	    				break;
	    			case "Affine Cipher":								
	    				panel = CipherPanels.AffineCipher();				//Generate Affine cipher interface
		    			break;	
	    			case "Vigenere Cipher":								
	    				panel = CipherPanels.VigenereCipher();				//Generate Vigenere cipher interface
		    			break;
	    			case "Playfair Cipher":								
	    				panel = CipherPanels.PlayfairCipher();				//Generate Playfair cipher interface
		    			break;	
	    			case "Hill Cipher":								
	    				panel = CipherPanels.HillCipher();					//Generate Hill cipher interface
		    			break;	
	    		}
	    		
	    		add(panel, BorderLayout.CENTER);
	    		revalidate();
	    		repaint();
	        }
	    } 
	}    
} 