package Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import FileIO.Task;


public class CalendarPanel extends JPanel implements ControlListener
{
	private ArrayList<Task> tasks;
	
	private Font f;
	private int pos;
	
	private ControlPanel controlPanel;
	private int list;
	
	/**
	 * Creates a new calendar panel to display all tasks in a certain arrangement.
	 * 
	 * @param tasks An ArrayList<Task> of tasks.
	 */
	public CalendarPanel(ArrayList<Task> tasks) 
	{
		super();
		
		this.tasks = tasks;
		
		f = new Font("Arial Unicode MS", Font.BOLD, 16);
		pos = 20;

		// Create Border Layout
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		setBackground(Color.WHITE);

		controlPanel = new ControlPanel();
		controlPanel.addControlListener(this);
		add(controlPanel, layout.SOUTH);
	}

	/**
	 * Paints the window with changes to the window.
	 * 
	 * @param g A graphics object.
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); // Call JPanel's paintComponent method

		int width = getWidth();
		int height = getHeight();

		double ratioX = (double)width/450;
		double ratioY = (double)height/800;

		Graphics2D g2 = (Graphics2D)g; // Import Graphics2D.
		AffineTransform at = g2.getTransform(); // Matrix stuff.
		g2.scale(ratioX, ratioY); // Scale.
		
		if (list == ControlListener.ASSIGNED)
		{
			tasks = arrangeByAssigned(tasks);
		}
		else if (list == ControlListener.PLANNED)
		{
			tasks = arrangeByPlanned(tasks);
		}
		else if (list == ControlListener.DUE) 
		{
			tasks = arrangeByDue(tasks);
		}
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(f);
		g.drawLine(10, 15, 440, 15);

		for (int i = 0; i < tasks.size(); i++)
		{
			g.setColor(Color.BLACK);
			g.drawString(tasks.get(i).toStringName(),     25, pos*(i+1) + 20 + 80*(i));
			g.drawString(tasks.get(i).toStringAssigned(), 25, pos*(i+1) + 40 + 80*(i));
			g.drawString(tasks.get(i).toStringPlanned(),  25, pos*(i+1) + 60 + 80*(i));
			g.drawString(tasks.get(i).toStringDue(),      25, pos*(i+1) + 80 + 80*(i));
			
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(10, pos*(i+1) + 95 + 80*i, 440, pos*(i+1) + 95 + 80*i);
		}

		g2.setTransform(at); // Return matrix.
	}

	// METHOD FROM CONTROL LISTENER
	
	public void setList(int l)
	{
		list = l;
			
		repaint();
	}

	// HELPER METHODS FOR ARRANGEMENT
	
	private ArrayList<Task> arrangeByAssigned(ArrayList<Task> taskList)
	{
		for (int i = 0; i < taskList.size()-1; i++)
		{
			if (taskList.get(i).getDateAssigned().after(taskList.get(i+1).getDateAssigned()))
			{
				Task temp = taskList.get(i);
				taskList.set(i, taskList.get(i+1));
				taskList.set(i+1, temp);
			}
		}

		return taskList;
	}


	private ArrayList<Task> arrangeByPlanned(ArrayList<Task> taskList)
	{
		for (int i = 0; i < taskList.size()-1; i++)
		{
			if (taskList.get(i).getDatePlanned().after(taskList.get(i+1).getDatePlanned()))
			{
				Task temp = taskList.get(i);
				taskList.set(i, taskList.get(i+1));
				taskList.set(i+1, temp);
			}
		}

		return taskList;
	}

	private ArrayList<Task> arrangeByDue(ArrayList<Task> taskList)
	{
		for (int i = 0; i < taskList.size()-1; i++)
		{
			if (taskList.get(i).getDateDue().after(taskList.get(i+1).getDateDue()))
			{
				Task temp = taskList.get(i);
				taskList.set(i, taskList.get(i+1));
				taskList.set(i+1, temp);
			}
		}

		return taskList;
	}
}
