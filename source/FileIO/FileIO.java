package FileIO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class FileIO 
{
	/**
	 * Makes a string that represents the line separator.
	 */
	public String lineSeparator;

	/**
	 * Creates the FileIO object and initializes the lineSeparator.
	 */
	public FileIO() 
	{
		lineSeparator = System.getProperty("line.separator");
	}

	/**
	 * Interprets the file line by line.
	 * 
	 * @param filename Reads the filename.
	 * @return String represented in a line by line fashion
	 */
	public String readFile (String filename) 
	{
		BufferedReader breader = null;
		FileReader reader = null;
		String fileData = null;
		Scanner in = null;

		try 
		{	
			reader = new FileReader(filename);
			breader = new BufferedReader(reader);
			in = new Scanner(breader);

			StringBuffer changingFileData = new StringBuffer();

			while (in.hasNextLine()) 
			{
				changingFileData.append(in.nextLine());
				changingFileData.append(lineSeparator);
			}

			fileData = changingFileData.toString();

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (in != null)
			{
				in.close();
			}
		}

		return fileData;
	}

	/**
	 * Interprets the line by line data into a file.
	 * 
	 * @param filename Writes to a filename.
	 * @param data Uses the line by line data obtained from reading the file.
	 */
	public void writeFile (String filename, String data) 
	{	
		BufferedWriter bwriter = null;
		FileWriter writer = null;

		try 
		{
			writer = new FileWriter(filename);
			bwriter = new BufferedWriter(writer);

			bwriter.write(data);
			bwriter.flush();

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace(); // Change to a better reaction
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (bwriter != null) 
			{
				try 
				{
					bwriter.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Reads the data from file to an Object.
	 * 
	 * @param filename Reads the filename.
	 * @return Object representing the data in the file
	 */
	public Object readObject (String filename) 
	{
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		
		Object out = null;

		try 
		{
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			out = ois.readObject();

		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace(); // Change to a better reaction
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (ois != null) 
			{
				try 
				{
					ois.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}

		return out;
	}

	/**
	 * Writes the data from file to an Object.
	 * 
	 * @param filename Writes the filename.
	 * @param data Uses the Object data obtained from reading the file.
	 */
	public void writeObject (String filename, Object data) 
	{
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;

		try 
		{
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.flush();	
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace(); // Change to a better reaction
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (oos != null) 
			{
				try 
				{
					oos.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
