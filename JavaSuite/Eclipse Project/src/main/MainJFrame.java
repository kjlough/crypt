package main;

import javax.swing.JFrame;

import cipherPanels.CipherPanel;

public class MainJFrame extends JFrame {
	private CipherPanel genericPanel = new CipherPanel();
	
	// Constructor
	public MainJFrame() {
		this.add(genericPanel);
		this.setSize(2000, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
