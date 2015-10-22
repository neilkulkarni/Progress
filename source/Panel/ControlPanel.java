package Panel;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ControlPanel extends JPanel 
{
	private ArrayList<ControlListener> listeners;

	private JRadioButton assigned, planned, due;
	private ButtonGroup b;
	
	/**
	 * Creates a new control panel to display all tasks.
	 */
	public ControlPanel ()
	{
		super();
		
		listeners = new ArrayList<ControlListener>();
		
		setLayout(new GridLayout(1,3));
	
		assigned = new JRadioButton("Assigned Date");
		planned = new JRadioButton("Planned Date");
		due = new JRadioButton("Due Date");
		
		assigned.addActionListener(new RadioButtonHandler());
		planned.addActionListener(new RadioButtonHandler());
		due.addActionListener(new RadioButtonHandler());
		
		b = new ButtonGroup();
		b.add(assigned);
		b.add(planned);
		b.add(due);	
		
		add(assigned);
		add(planned);
		add(due);
	}
	
	/**
	 * Adds the control listeners to an ArrayList
	 * 
	 * @param l A ControlListener, such as the CalendarPanel.
	 */
	public void addControlListener(ControlListener l) 
	{
		listeners.add(l);
	}
	
	/**
	 * 
	 * @author Neil
	 * 
	 * Implements ActionListener in order to handle the requests of each Radio Button.
	 *
	 */
	class RadioButtonHandler implements ActionListener 
	{
		/**
		 * Sets the arrangement based on which Radio Button was selected.
		 */
		public void actionPerformed(ActionEvent e) 
		{
			Object source = e.getSource();
			
			if (source == assigned)
			{
				System.out.println("hello");
				
				for (ControlListener l : listeners)
				{
					l.setList(1);
				}
			}
			else if (source == planned)
			{
				System.out.println("hello");
				for (ControlListener l : listeners)
				{
					l.setList(2);
				}
			}
			else if (source == due)
			{
				System.out.println("hello");
				for (ControlListener l : listeners)
				{
					l.setList(3);
				}
			}
		}
	}
}
