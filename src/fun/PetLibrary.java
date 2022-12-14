package fun;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.collections.ObservableList;



/**
 * Class: PetShop
 * 
 * @author Bouba. C
 * @version 15.0 
 * 
 * Written on September 20
 *
 * This is a program that reads in pets.dat 
 * and displays its information in a listView
 * It also can be used to write additional pets to the file
 */
public class PetLibrary {
	
	
	//Create a private arrayList for Pet objects
	private ArrayList<Pet> petList = new ArrayList<Pet>();

	public ArrayList<Pet> getTheList() {
		
		return petList;
	}
	
	//Variable for the file 
	String theFile;

	/**
	 * Method: ReadBinaryFile
	 * 
	 * A method to read in from a binary file using ObjectInputStream
	 */
	public void readBinaryFile() {
		
		
		
		//Create an input stream
		FileInputStream file = null;
		ObjectInputStream input = null;
		
		/**
		 * Try and catch blocks to catch exceptions
		 */
		try {
			theFile = "pets.dat";
			file = new FileInputStream(theFile);
			input = new ObjectInputStream(file);
		}
		catch(IOException ex) {
			
			System.out.println("Cannot find the file, check the file's source");
		}
		
		/**
		 * If the file is found, this block of code will read the file
		 */
		try {
			
			while(true) {
				
				Pet pets = (Pet) input.readObject();
				petList.add(pets);
			}	
		}
		
		catch(EOFException ex) {
			
			//Do nothing, end of the file
		}
		
		catch(IOException ex) {
			
			ex.printStackTrace();
			System.out.println("Unable to read from the file!");
		} 
		
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("Object read was not a Pet object");
		}
	}
	
	/**
	 * Method: writeBinaryFile()
	 * 
	 * This method allows the existing file to be overwritten
	 * if any additional pet is added
	 * 
	 */
	public void writeBinaryFile(ObservableList<Pet> lists) {
		
		//Create an output stream
		FileOutputStream file = null;
		ObjectOutputStream output = null;
		
		/**
		 * Try and catch blocks to handle exceptions
		 */
		try {
			theFile = "pets.dat";
			file = new FileOutputStream(theFile);
			output = new ObjectOutputStream(file);
		}
		catch(IOException ex) {
			
			System.out.print("File not found, check the source!");
		}
		
		//If the the file is found, write in the file
		try {
			
			for(Pet pet: lists) {
				
				output.writeObject(pet);	
			}
			
			//Close the file 
			output.close();	
		}
		catch(IOException ex) {
			
			ex.printStackTrace();
			System.out.println("Unable to write to file!");
		}
	}

}
