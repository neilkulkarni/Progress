package FileIO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Task implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date assigned, planned, due;

	/**
	 * Creates a new task.
	 * 
	 * @param name The name of the task.
	 * @param assigned The date assigned.
	 * @param planned The planned date of completion.
	 * @param due The due date.
	 */
	public Task(String name, Date assigned, Date planned, Date due)
	{
		this.name = name;
		this.assigned = assigned;
		this.planned = planned;
		this.due = due;
	}
	
	
	// GETTERS
	
	public Date getDateAssigned()
	{
		return assigned;
	}
		
	public Date getDatePlanned()
	{
		return planned;
	}
	
	public Date getDateDue()
	{
		return due;
	}
	
	
	// TO STRINGS
	
	/**
	 * Returns a string of the task and its components.
	 * 
	 * @return A string of the task and its components. 
	 */
	public String toString()
	{
		return toStringName() + "\n" + toStringAssigned() + "\n" + toStringPlanned() + "\n" + toStringDue() + "\n";
	}
	
	/**
	 * Returns a string of the task name.
	 * 
	 * @return A string of the task name. 
	 */
	public String toStringName()
	{	
		return "Name: " + name;
	}
	
	/**
	 * Returns a string of the date assigned.
	 * 
	 * @return A string of the date assigned. 
	 */
	public String toStringAssigned()
	{
		String assignedDate = "Assigned: " + extractDate(assigned);
		
		return assignedDate;
	}
	
	/**
	 * Returns a string of the date planned.
	 * 
	 * @return A string of the date planned. 
	 */
	public String toStringPlanned()
	{
		String plannedDate = "Planned: " +  extractDate(planned);
		
		return plannedDate;
	}
	
	/**
	 * Returns a string of the date due.
	 * 
	 * @return A string of the date due. 
	 */
	public String toStringDue()
	{
		String dueDate = "Due: " + extractDate(due);
		
		return dueDate;
	}
	
	
	// INTERPRETS DATE TO STRING
	private String extractDate(Date d)
	{
		String ouput = "";
		String date = d.toString();
		
		ouput = date.substring(0, date.indexOf(':')-3) + ", " + date.substring(date.lastIndexOf(' ')+1, date.length());
		
		return ouput;
	}
}
