package main;

public class Main {
	static MenuPanel window;
	
	public static void init() {
		window = new MenuPanel();
		window.setVisible(true);
	}

	public static MenuPanel getWindow() {
		// TODO Auto-generated method stub
		return window;
	}
	
	public static void main(String args[]) {
	    init();
	}
	
	
}
