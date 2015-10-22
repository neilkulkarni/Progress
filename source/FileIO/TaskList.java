package FileIO;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;


public class TaskList implements Serializable, WindowListener
{
	private static final long serialVersionUID = 1L;

	private ArrayList<Task> tasks;
	private int points = 0;
	private int level = 1;
	
	/**
	 * Creates an ArrayList of tasks used to represent program data.
	 */
	public TaskList ()
	{
		tasks = new ArrayList<Task>();
	}
	
	
	// GETTERS
	
	public ArrayList<Task> getTasks()
	{
		return tasks;
	}
	
	public int getPointsTotal()
	{
		return points;
	}
	
	public int getPointsRemaining()
	{
		int pointsRemaining = points%100;
		
		return pointsRemaining;
	}
	
	public int getLevel()
	{		
		return level;
	}
	
	
	/**
	 * Sets points and level fields according to task completion.
	 * 
	 * @param t Determines points rewarded given a task.
	 */
	public void determinePointsAndLevel(Task t)
	{
		int credits = assessPoints(t);
		points += credits;
						
		JOptionPane.showMessageDialog(null, 
				"You just earned " + credits + " points!", "Congrats!", JOptionPane.PLAIN_MESSAGE);
		
		
		int levelBefore = level;
		assessLevel(points);
		int levelAfter = level;
		
		if (levelAfter > levelBefore)
		{
			JOptionPane.showMessageDialog(null, 
					"You reached Level " + level + "!", "Level Up!", JOptionPane.PLAIN_MESSAGE);
		}
		
		System.out.println(points + " / " + level);
	}
	
	
	// HELPER METHODS TO DETERMINE POINTS AND LEVEL
	
	private int assessPoints (Task t)
	{
		int credits = 0;
		int now = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int planned = t.getDatePlanned().getDate();
		
		if (now < planned)
		{
			credits = 40 + level*5;
		}
		else if (now == planned)
		{
			credits = 20 + level*5;
		}
		else
		{
			credits = 0 + level*5;
		}
		
		return credits; 
	}

	private void assessLevel (int total)
	{
		level = total/100 + 1;
	}

	
	/**
	 * Writes the data of the program to a locally stored file.
	 */
	public void windowClosing(WindowEvent arg0) 
	{
		FileIO accessor = new FileIO();
		accessor.writeObject("tasks.pro", this);	
	}
	
	// WINDOW LISTENER EXTRAS
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}
