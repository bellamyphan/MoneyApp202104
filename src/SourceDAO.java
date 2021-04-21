/*
 * Author: Bellamy Phan.
 * Date Created: 2020/12/07.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class SourceDAO {

	// Hold the file.
	String filename;
	// Hold the list of SourceUnits.
	private LinkedList<SourceUnit> list;
	
	
	// Constructor.
	public SourceDAO() {
		// Initialize the attributes.
		filename = "SourceDB.txt";
		list = new LinkedList<>();
		// Backup the database.
		this.backupFile();
		// Feed data to the list.
		feedData();
	}
	
	
	// Get the list.
	public LinkedList<SourceUnit> getList() {
		return list;
	}
	
	
	// Add new SourceUnit.
	public void addSourceUnit() {
		// Hold the id and the name of the new unit.
		int id;
		String name;
		// Get the new id.
		id = list.getLast().getID() + 1;
		// Get the new name.
		Scanner input = new Scanner(System.in);
		System.out.print("Source name: ");
		name = input.nextLine();
		// Avoid empty name.
		if (name.length() == 0) {
			name = input.nextLine();
		}
		// Close the scanner after using.
		input.close();
		// Create new unit.
		SourceUnit newUnit = new SourceUnit(id, name);
		// Add new unit to the list.
		list.addLast(newUnit);
		// Output to the file
		this.outputList();
	}
	
	
	// Edit a SourceUnit
	public void editSourceUnit() {
		// Introduction.
		System.out.println("Edit a Source.");
		System.out.println(this.toString());
		// Get the ID of the source.
		System.out.print("ID of the source to edit: ");
		Scanner input = new Scanner(System.in);
		int id = input.nextInt();
		// Avoid empty id.
		if (id == 0) {
			id = input.nextInt();
		}
		// Get the index of the source in the list.
		int index = 0;
		System.out.println("Output the current source.");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getID() == id) {
				index = i;
				break;
			}
		}
		System.out.println(list.get(index));
		String newName = "";
		System.out.print("Enter the new name for this source: ");
		newName = input.nextLine();
		// Avoid empty name.
		if (newName.length() == 0) {
			newName = input.nextLine();
		}
		System.out.println();
		// Change the name of the of the current source in the list.
		list.get(index).setName(newName);
		// Save the list.
		this.outputList();
		// Close the Scanner.
		input.close();
	}
	
	
	// Feed data to the list.
	private void feedData() {
		// Open and read the file.
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot open SourceDB.txt.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		// Read each line in the file.
		String currentLine = fileInput.nextLine();
		while (currentLine.length() != 0) {
			// arrayOfString[0] = #ID
			// arrayOfString[1] = name
			String[] arrayOfString = currentLine.split("\\|");
			// Get ID by remove # character.
			int id = Integer.parseInt(arrayOfString[0].substring(1));
			// Create a SourceUnit.
			SourceUnit unit = new SourceUnit(id, arrayOfString[1]);
			// Add the current unit to the list.
			list.addLast(unit);
			// Update the currentLine.
			if (fileInput.hasNext()) {
				currentLine = fileInput.nextLine();
			} else {
				currentLine = ""; // Stopping condition.
			}
		}
		// Close the file after reading.
		fileInput.close();
	}
	
	
	// Backup the file.
	private void backupFile() {
		// Get the backupFilename.
		Time today = new Time(Main.timeZone);
		String filename = this.filename.substring(0, this.filename.length() - 4); // Ignore .txt in the original filename.
		String backupFilename = "/Users/bellamyphan/Desktop/MoneyReport/backup/" + filename + today.get_yyyymmdd_hhmm() + ".txt";
		// Create the backup file.
		File backupFile = new File(backupFilename);
		backupFile.delete();
		try {
			backupFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot create backup file for SourceDAO.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		// Open both files to read and write.
		Scanner originalFile = null;
		PrintWriter backupOutput = null;
		try {
			originalFile = new Scanner(new File(this.filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot open SourceDB.txt.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		try {
			backupOutput = new PrintWriter(backupFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot open backup file for SourceDAO.txt.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		// Backup each line.
		String currentLine = originalFile.nextLine();
		while (currentLine.length() != 0) {
			backupOutput.println(currentLine);
			// Update currentLine.
			if (originalFile.hasNext()) {
				currentLine = originalFile.nextLine();
			} else {
				currentLine = "";
			}
		}
		// Close the files after using.
		originalFile.close();
		backupOutput.close();
	}
	
	
	// Output the list.
	public void outputList() {
		// Use PrintWriter to open the file.
		PrintWriter fileOutput = null;
		try {
			fileOutput = new PrintWriter(new File(this.filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot open SourceDB.txt.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		// Write each unit to the file.
		for (int i = 0; i < list.size(); i++) {
			fileOutput.println("#" + list.get(i).getID() + "|" + list.get(i).getName());
		}
		// Close the file after using.
		fileOutput.close();
	}
	
	
	// toString.
	@Override
	public String toString() {
		String string = "";
		for (int i = 0; i < list.size(); i++) {
			// Avoid the newline character at the end of the string.
			if (i == list.size() - 1) {
				string += list.get(i);
				break;
			}
			string += list.get(i) + "\n";
		}
		return string;
	}
	
	
	
}
