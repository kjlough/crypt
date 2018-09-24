/*Creates the applet container*/

import javax.swing.JFrame;

public class Applet {
	
	private static MenuPanel window;
	
	public static void main(String[] args) {						//Initializes container		
		window = new MenuPanel();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public static MenuPanel getWindow() {
		return window;
	}
}