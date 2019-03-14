package cipherPanels;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JLabel;

public class CipherPanel extends Panel {
	//Fonts
	private Font small_font = new Font("Helvetica", Font.PLAIN, 40); 
	private Font med_font = new Font("Helvetica", Font.PLAIN, 60);
	private Font large_font = new Font("Helvetica", Font.PLAIN, 80);
	
	//Layouts
	private GridLayout grid = new GridLayout(3,1);
	private FlowLayout flow = new FlowLayout();
	
	//Inner panels
	private Panel top_panel = new Panel(flow);
	private Panel mid_panel = new Panel(flow);
	private Panel low_panel = new Panel(flow);
	
	//Panel Components
	private JLabel title;
	private Button shift_button, affine_button, vigenaire_button, playfaire_button, hill_button;
	
	public CipherPanel() {
			
		//Set up fonts:
		//title.setFont(large_font);
		
		//Set up inner_panels
		top_panel.add(new JLabel("a"));
		mid_panel.add(new JLabel("b"));
		low_panel.add(new JLabel("c"));
		
		//Set up this panel
		this.setLayout(grid);
		this.add(top_panel);
		this.add(mid_panel);
		this.add(low_panel);
		
	}
}
