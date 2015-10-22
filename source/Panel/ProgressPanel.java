package Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import FileIO.TaskList;


public class ProgressPanel extends JPanel
{
	private TaskList tl;

	private Font f1, f2, f3;
	
	/**
	 * Creates a panel to display and keep track of progress.
	 * 
	 * @param tl A TaskList that provides data for the points and levels.
	 */
	public ProgressPanel (TaskList tl)
	{
		super();

		setBackground(Color.WHITE);
		
		this.tl = tl;	

		f1 = new Font("Serif", Font.BOLD, 48);
		f2 = new Font("Serif", Font.ITALIC, 24);
		f3 = new Font("Serif", Font.ITALIC, 24);
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
		g2.scale(ratioX, ratioY); // Scale

		int pointsTotal = tl.getPointsTotal();
		int pointsRemaining = tl.getPointsRemaining();
		int level = 1;
		level = tl.getLevel();	
		
		// PROGRESS BAR
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(140, 115, 170, 620);

		g.setColor(new Color (153, 50, 204));
		g.fillRect(150, 125, 150, 600);

		int heightOfProgress = 600 - pointsRemaining*6;
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(150, 125, 150, heightOfProgress);

		// POINTS REMAINING
		
		g.setFont(f2);
		g.setColor(Color.WHITE);
		FontMetrics fm2 = g.getFontMetrics(f2);
		int sw2 = fm2.stringWidth(pointsRemaining + " / 100");
		g.drawString(pointsRemaining + " / 100", (450-sw2)/2, heightOfProgress + 117);

		// LEVEL
		
		g.setColor(Color.DARK_GRAY);
		g.setFont(f1);
		FontMetrics fm1 = g.getFontMetrics(f1);
		int sw1 = fm1.stringWidth("Level: " + level);
		g.drawString("Level: " + level, (450-sw1)/2, 75);		

		// TOTAL POINTS
		
		g.setFont(f3);
		FontMetrics fm3 = g.getFontMetrics(f3);
		int sw3 = fm3.stringWidth("Total Points: " + pointsTotal);
		g.drawString("Total Points: " + pointsTotal, (450-sw3)/2, 775);

		g2.setTransform(at); // Return matrix.
	}
}
