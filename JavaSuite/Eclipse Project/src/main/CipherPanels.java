package main;
/*Creates panels specific to each Cipher type*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CipherPanels extends JPanel								//Generates Cipher specific interface panels
{
	static int s;														//Variable declarations
	private static JCheckBox crack;
	private static JPanel root, panel0, panel1, panel2, panel3;
	private static JLabel banner0, banner1, banner2;
	private static JButton encrypt, decrypt, back;
	private static JTextField encrypted, decrypted, key;
	private static JTextArea info;
	private static JScrollPane sp;
	private static JComboBox<String> xOptions;
	
	public static JPanel ShiftCipher()									//*****SHIFT CIPHER PANEL*****
	{
		s = 0;															//Sets case for button listener
		root = new JPanel();
		
		encrypt = new JButton("Encrypt " + (char)8659);					//Set element contents
		encrypt.setFont(new Font("Verdana", Font.BOLD, 40));
		decrypt = new JButton("Decrypt " + (char)8657);
		decrypt.setFont(new Font("Verdana", Font.BOLD, 40));
		crack = new JCheckBox(" Break");
		crack.setFont(new Font("Verdana", Font.BOLD, 40));
		back = new JButton("Back");
		back.setFont(new Font("Verdana", Font.BOLD, 40));
		banner0 = new JLabel ("Original Text: ");
		banner0.setFont(new Font("Verdana", Font.BOLD, 40));
		banner1 = new JLabel ("Encrypted Text: ");
		banner1.setFont(new Font("Verdana", Font.BOLD, 40));
		banner2 = new JLabel ("Key: ");
		banner2.setFont(new Font("Verdana", Font.BOLD, 40));
		info = new JTextArea("", 8, 60);
		info.setFont(new Font("Verdana", Font.PLAIN, 25));
		sp = new JScrollPane(info);
		encrypted = new JTextField();
		encrypted.setFont(new Font("Verdana", Font.BOLD, 40));
		decrypted = new JTextField();
		decrypted.setFont(new Font("Verdana", Font.BOLD, 40));
		key = new JTextField();
		key.setFont(new Font("Verdana", Font.BOLD, 40));
		
		panel0 = new JPanel();											//Add elements to panel0
		panel0.add(banner0);
		panel0.add(decrypted);
		panel0.setLayout(new GridLayout(2, 1));
		
		panel1 = new JPanel();											//Add elements to panel1
		panel1.add(banner2);
		panel1.add(key);
		panel1.add(encrypt);
		panel1.add(decrypt);
		panel1.add(crack);
		panel1.setLayout(new GridLayout(1, 5));
		
		panel2 = new JPanel();											//Add elements to panel2
		panel2.add(banner1);
		panel2.add(encrypted);
		panel2.setLayout(new GridLayout(2, 1));
		
		panel3 = new JPanel();
		panel3.add(panel0);												//Add panels to root container
		panel3.add(panel1);
		panel3.add(panel2);
		panel3.add(back);
		panel3.setLayout(new GridLayout(4, 1));
		root.add(panel3);
		root.add(sp);
		root.setLayout(new GridLayout(2, 1));
		
		ButtonListener button = new ButtonListener();					//Initializes button listener
		encrypt.addActionListener(button);
		decrypt.addActionListener(button);
		back.addActionListener(button);
		
		return root;
	}
	
	public static JPanel AffineCipher()									//*****AFFINE CIPHER PANEL*****
	{
		xOptions = new JComboBox<String>(); 
		xOptions.setFont(new Font("Verdana", Font.BOLD, 40));
		
		String[] xVals = {"X", "1", "3", "5", "7", "9", "11", "15", "17", "19", "21", "23", "25"};								
		
		for(int i = 0; i < xVals.length; i++)
		{
			xOptions.addItem(xVals[i]);
		}
		
		root = (JPanel) ShiftCipher();									//Uses same base format as shift panel and edit as needed
		panel1.removeAll();
		panel1.add(banner2);
		panel1.add(xOptions);
		panel1.add(key);
		panel1.add(encrypt);
		panel1.add(decrypt);
		panel1.add(crack);
		panel1.setLayout(new GridLayout(1, 6));
		
		s = 1;															//Sets case for button listener
		
		return root;
	}

	public static JPanel VigenereCipher()								//*****VIGENERE CIPHER PANEL*****
	{
		root = ShiftCipher();											//Uses same base format as shift panel and edit as needed
		
		s = 2;															//Sets case for button listener
		
		return root;
	}

	public static JPanel PlayfairCipher()								//*****PLAYFAIR CIPHER PANEL*****
	{
		root = (JPanel) ShiftCipher();									//Uses same format as shift panel
		
		s = 3;															//Sets case for button listener
		
		return root;
	}

	public static JPanel HillCipher()									//*****HILL CIPHER PANEL*****
	{
		root = (JPanel) ShiftCipher();									//Uses same format as shift panel
		key.setText("[n x n]");
		
		s = 4;															//Sets case for button listener
		
		return root;
	}
	
	static class ButtonListener implements ActionListener				//Action listener for active panel elements
	{
	    public void actionPerformed (ActionEvent event)					//Action performed
	    {
	    	if(event.getSource() == back)								//BACK button
	    	{
	    		MenuPanel window = Main.window; 						
	    		window.setToMenu();
	    	}
	    	
	    	if(s == 0)													//Defines Shift cipher button functions
	    	{
		    	if(event.getSource() == encrypt)						//Calls Shift cipher encryption method
		        {
		    		String plaintext = Ciphers.Shift(decrypted.getText(), key.getText(), 1, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	encrypted.setText(text[1]);
			    	info.setText(text[2]);
		    	}
		    	
		    	if(event.getSource() == decrypt)						//Calls Shift cipher decryption method
		        {
		    		String plaintext = Ciphers.Shift(encrypted.getText(), key.getText(), 0, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	decrypted.setText(text[1]);
			    	info.setText(text[2]);
		        }
	    	}
	    	
	    	if(s == 1)													//Defines Affine cipher button functions
	    	{
		    	if(event.getSource() == encrypt)						//Calls Affine cipher encryption method
		        {
		    		String xy = "(" + xOptions.getSelectedItem() + "," + key.getText() + ")";
		    		String plaintext = Ciphers.Affine(decrypted.getText(), xy, 1, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	for(int i = 0; i < 13; i++)
			    	{
			    		if(xOptions.getItemAt(i).equals(text[0]))
			    		{
			    			xOptions.setSelectedIndex(i);
			    			break;
			    		}
			    	}
			    	key.setText(text[1]);
			    	encrypted.setText(text[2]);
			    	info.setText(text[3]);
		        }
		    	
		    	if(event.getSource() == decrypt)						//Calls Affine cipher decryption method
		        {
		    		String xy = "(" + xOptions.getSelectedItem() + "," + key.getText() + ")";
		    		String plaintext = Ciphers.Affine(encrypted.getText(), xy, 0, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	for(int i = 0; i < 13; i++)
				    {
				    	if(xOptions.getItemAt(i).equals(text[0]))
				    	{
				    		xOptions.setSelectedIndex(i);
				    		break;
				    	}
				    }
			    	key.setText(text[1]);
			    	decrypted.setText(text[2]);
			    	info.setText(text[3]);
		    	}
	    	}
	    	
	    	if(s == 2)													//Defines Vigenere cipher button functions
	    	{
		    	if(event.getSource() == encrypt)						//Calls Vigenere cipher encryption method
		        {
		    		String plaintext = Ciphers.Vigenere(decrypted.getText(), key.getText(), 1, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	encrypted.setText(text[1]);
			    	info.setText(text[2]);
		        }
		    	
		    	if(event.getSource() == decrypt)						//Calls Vigenere cipher decryption method
		        {
		    		String plaintext = Ciphers.Vigenere(encrypted.getText(), key.getText(), 0, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	decrypted.setText(text[1]);
			    	info.setText(text[2]);
		    	}
	    	}

	    	if(s == 3)													//Defines Playfair cipher button functions
	    	{
		    	if(event.getSource() == encrypt)						//Calls Playfair cipher encryption method
		        {
		    		String plaintext = Ciphers.Playfair(decrypted.getText(), key.getText(), 1, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	encrypted.setText(text[1]);
			    	info.setText(text[2]);
			    }
		    	
		    	if(event.getSource() == decrypt)						//Calls Playfair cipher decryption method
		        {
		    		String plaintext = Ciphers.Playfair(encrypted.getText(), key.getText(), 0, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	decrypted.setText(text[1]);
			    	info.setText(text[2]);
		    	}
	    	}

	    	if(s == 4)													//Defines Hill cipher button functions
	    	{
		    	if(event.getSource() == encrypt)						//Calls Hill cipher encryption method
		        {
		    		String plaintext = Ciphers.Hill(decrypted.getText(), key.getText(), 1, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	encrypted.setText(text[1]);
			    	info.setText(text[2]);
		        }
		    	
		    	if(event.getSource() == decrypt)						//Calls Hill cipher decryption method
		        {
		    		String plaintext = Ciphers.Hill(encrypted.getText(), key.getText(), 0, crack.isSelected());
		    		
			    	String[] text = plaintext.split(":");
			    	key.setText(text[0]);
			    	decrypted.setText(text[1]);
			    	info.setText(text[2]);
		    	}
	    	}
	    } 
	}
} 