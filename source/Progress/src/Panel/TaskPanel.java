package Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import FileIO.Task;
import FileIO.TaskList;

public class TaskPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private TaskList tl;
	private ArrayList<Task> tasks;
	
	private Font f;
	private int pos;
	private double ratioX, ratioY;
	
	private Point position;
	
	private transient BufferedImage check;
	private transient BufferedImage xmark;

	/**
	 * Creates a new task panel to display all tasks.
	 * 
	 * @param tl A TaskList containing the data of all the tasks.
	 */
	public TaskPanel(TaskList tl) 
	{
		super();
		
		this.tl = tl;
		tasks = tl.getTasks();
	
		f = new Font("Arial Unicode MS", Font.BOLD, 16);
		pos = 20;
		
		position = new Point();

		// Create Border Layout
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		setBackground(Color.WHITE);

		JButton addTask = new JButton("Add Task");
		addTask.addActionListener(this);
		add(addTask, layout.SOUTH);

		addMouseListener(new MouseHandler());
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

		ratioX = (double)width/450;
		ratioY = (double)height/800;

		Graphics2D g2 = (Graphics2D)g; // Import Graphics2D.
		AffineTransform at = g2.getTransform(); // Matrix stuff.
		g2.scale(ratioX, ratioY); // Scale.

		g.setFont(f);

		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(10, 15, 440, 15);
		
		try 
		{
			check = ImageIO.read(new File("check.png"));
			xmark = ImageIO.read(new File("x.png"));
		}
		catch (IOException e) 
		{
			System.out.println("Can't find pics");
		}

		for (int i = 0; i < tasks.size(); i++)
		{
			// DRAW CHECK MARK AND X MARK
			
			g.drawImage(check, 25, pos*(i+1) + 10 + 80*i, 30, 30, this);
			g.drawImage(xmark, 25, pos*(i+1) + 50 + 80*i, 30, 30, this);

			// DRAW TASK LINES
			
			g.drawLine(10, pos*(i+1) + 95 + 80*i, 440, pos*(i+1) + 95 + 80*i);
			
			// DRAW TASKS
			
			g.setColor(Color.BLACK);
			g.setFont(f);
			g.drawString(tasks.get(i).toStringName(),     70, pos*(i+1) + 20 + 80*(i));
			g.drawString(tasks.get(i).toStringAssigned(), 70, pos*(i+1) + 40 + 80*(i));
			g.drawString(tasks.get(i).toStringPlanned(),  70, pos*(i+1) + 60 + 80*(i));
			g.drawString(tasks.get(i).toStringDue(),      70, pos*(i+1) + 80 + 80*(i));
		}

		g2.setTransform(at); // Return matrix.
	}

	/**
	 * 
	 * @author Neil
	 *
	 * Implements mouse listener to handle mouse related commands.
	 *
	 */
	class MouseHandler implements MouseListener 
	{
		
		/**
		 * Asses the Point clicked to see if a task was completed or deleted. 
		 * Rewards points for completion.
		 */
		public void mouseClicked(MouseEvent e) 
		{
			System.out.println("am i here?");
			requestFocus();
			
			position = e.getPoint();

			if (position.getX() > 0 && position.getY() > 0)
			{
				int xPos = (int)(position.getX()/ratioX);
				int yPos = (int)(position.getY()/ratioY);

				for (int i = 0; i < tasks.size(); i++)
				{
					if (xPos >= 25 && xPos <= 55 && yPos >= pos*(i+1) + 10 + 80*i && yPos <= pos*(i+1) + 40 + 80*i)
					{
						int option = JOptionPane.showConfirmDialog(null,
								"Have you completed all aspects of this task?",
								null, JOptionPane.YES_NO_OPTION,
								JOptionPane.PLAIN_MESSAGE);

						if (option == JOptionPane.YES_OPTION)
						{
							tl.determinePointsAndLevel(tasks.get(i));
							
							tasks.remove(i);
						}
					}
					else if (xPos >= 25 && xPos <= 55 && yPos >= pos*(i+1) + 50 + 80*i && yPos <= pos*(i+1) + 80 + 80*i)
					{
						int option = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to delete this task?",
								null, JOptionPane.YES_NO_OPTION,
								JOptionPane.PLAIN_MESSAGE);

						if (option == JOptionPane.YES_OPTION)
						{
							tasks.remove(i);
						}
					}
				}
			}

			repaint();
		}

		// MOUSE LISTENER EXTRAS
		
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}

	/**
	 * Performs the action requested by the JButton of adding a new task.
	 * 
	 * @param e An ActionEvent for the JButton
	 */
	public void actionPerformed(ActionEvent e) 
	{

		SpinnerModel model = new SpinnerDateModel();
		JSpinner spinner = new JSpinner(model);
		JComponent editor = new JSpinner.DateEditor(spinner, "EEE, MMMMMMMMM dd, yyyy");
		spinner.setEditor(editor);

		String name = JOptionPane.showInputDialog("Please enter a task.");
		
		if (name == null)
		{
			return;
		}
		
		Date assigned = getDateAssigned(spinner);
		
		if (assigned == null)
		{
			return;
		}
		
		Date planned = getDatePlanned(spinner);
		
		if (planned == null)
		{
			return;
		}
		
		Date due = getDateDue(spinner);

		if (due == null)
		{
			return;
		}

		tasks.add(new Task(name, assigned, planned, due));

		repaint();
	}

	// HELPER METHODS FOR GETTING DATES AND SPINNER PANEL
	
	private Date getDateAssigned(JSpinner s) 
	{
		int option = JOptionPane.showConfirmDialog(null,
				getPanel(s),
				"Please enter the date assigned.",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (option == JOptionPane.OK_OPTION)
		{
			System.out.println(s.getValue());
			return (Date) s.getValue();
		}

		return null;
	}

	private Date getDatePlanned(JSpinner s) 
	{
		int option = JOptionPane.showConfirmDialog(null,
				getPanel(s),
				"Please enter the date you plan on completing.",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (option == JOptionPane.OK_OPTION)
		{
			System.out.println(s.getValue());
			return (Date) s.getValue();
		}

		return null;
	}

	private Date getDateDue(JSpinner s) 
	{
		int option = JOptionPane.showConfirmDialog(null,
				getPanel(s),
				"Please enter the date due.",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (option == JOptionPane.OK_OPTION)
		{
			System.out.println(s.getValue());
			return (Date) s.getValue();
		}

		return null;
	}

	private JPanel getPanel(JSpinner s) 
	{
		JPanel panel = new JPanel();
		panel.add(s);
		return panel;
	}

}
