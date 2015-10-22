package Window;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class Progress 
{
	
	/**
	 * Main method of the program. Begins execution and creation of tasks through user/computer dialogue.
	 * 
	 * @param args
	 * 
	 * 5/15/15
	 */
	public static void main(String[] args) 
	{
		try 
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} 
		catch (Exception ex) 
		{
			System.out.println("Uh oh!");
		}

		Frame f = new Frame();
		f.setResizable(true);
		f.setVisible(true);
	}
}
