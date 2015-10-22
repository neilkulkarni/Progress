package Window;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;

import FileIO.FileIO;
import FileIO.TaskList;
import Panel.CalendarPanel;
import Panel.ProgressPanel;
import Panel.TaskPanel;


public class Frame extends JFrame
{	
	/**
	 * Creates a frame to hold all panels.
	 */
	public Frame()
	{
		super("Progress");

		setBounds(100, 100, 450, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FileIO accessor = new FileIO();
		TaskList tl = (TaskList)accessor.readObject("tasks.pro");
		
		if (tl == null)
		{
			tl = new TaskList();
		}
		
		addWindowListener(tl);
		
		TaskPanel taskPanel = new TaskPanel(tl);
		CalendarPanel calendarPanel = new CalendarPanel(tl.getTasks());
		ProgressPanel progressPanel = new ProgressPanel(tl);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Tasks", taskPanel);
		tabbedPane.addTab("Calendar", calendarPanel);
		tabbedPane.addTab("Progress", progressPanel);
		
		add(tabbedPane);
	}
}
