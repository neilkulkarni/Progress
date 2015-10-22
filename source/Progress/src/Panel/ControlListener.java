package Panel;
import java.util.ArrayList;

/**
 * 
 * @author Neil
 *
 * An interface that helps assign constants to determine task arrangement.
 */
public interface ControlListener 
{
	public static final int ASSIGNED = 1;
	public static final int PLANNED = 2;
	public static final int DUE = 3;
	
	public void setList(int l); 	
}
