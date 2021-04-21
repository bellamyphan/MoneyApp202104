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

public class AccountDAO {

	// Hold the file.
	private String filename;
	// Hole the list of AccountUnits.
	private LinkedList<AccountUnit> list;
	
	
	// Constructor.
	public AccountDAO() {
		// Initialize the attributes.
		filename = "AccountDB.txt";
		list = new LinkedList<>();
		// Backup the database.
		this.backupFile();
		// Feed the data to the list.
		this.feedData();
	}
	
	
	// Get the list.
	public LinkedList<AccountUnit> getList() {
		return this.list;
	}
	
	
	// Open the menu.
	public void menu() {
		// Use a Scanner to read input.
		Scanner input = new Scanner(System.in);
		System.out.println("1. Add new account.");
		System.out.println("2. Edit an account.");
		System.out.println("3. Delete an account.");
		System.out.println("0. Go back.");
		System.out.print("Your choice: ");
		int option = input.nextInt();
		System.out.println();
		switch (option) {
		case 0:
			break;
		case 1:
			this.addAccountUnit();
			break;
		case 2:
			System.out.println("Edit an account.");
			System.out.println("This function is not completed.");
			System.out.println();
			break;
		case 3:
			System.out.println("Delete an account.");
			System.out.println("This function is not completed.");
			System.out.println();
			break;
		default:
			break;
		}
		input.close();
	}
	
	
	// Add new AccountUnit.
	public void addAccountUnit() {
		// Introduction.
		System.out.println("Add new account.");
		// Get the ID for new account.
		int id = list.getLast().getID() + 1;
		System.out.println("ID: " + id);
		// Get the name for new account.
		Scanner input = new Scanner(System.in);
		System.out.print("Name: ");
		String name = "";
		while (name.length() == 0) {
			name = input.nextLine();
		}
		// Get the type of the new account.
		String type = "";
		System.out.print("Account type? Checking/Credit: ");
		while (type.compareTo("Checking") != 0 && type.compareTo("Credit") != 0) {
			type = input.nextLine();
		}
		// Get the open date.
		Time today = new Time(-6);
		int year = today.getYear();
		int month = today.getMonth();
		int day = today.getDay();
		System.out.println("Suggested open day: " + year + "/" + month + "/" + day);
		System.out.print("Change the open day? Yes/No: ");
		if (input.nextLine().compareTo("No") == 0) {
			System.out.print("Year = ");
			year = input.nextInt();
			System.out.print("Month = ");
			month = input.nextInt();
			System.out.print("Day = ");
			day = input.nextInt();
		}
		Time open = new Time(Main.timeZone, year, month, day);
		System.out.println("Open date = " + open);
		// Get the close date.
		Time close = new Time((long)0);
		// Close the Scanner.
		input.close();
		// Create new unit.
		AccountUnit newUnit = new AccountUnit(id, name, type, open, close);
		// Add new unit to the list.
		list.addLast(newUnit);
		// Output to the file
		this.outputList();
		System.out.println("Added new account to the list.");
		System.out.println();
	}
	
	
	/*
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
	*/
	
	
	// Feed data to the list.
	private void feedData() {
		// Open and read the file.
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot open AccountDB.txt.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		// Read each line in the file.
		String currentLine = fileInput.nextLine();
		while (currentLine.length() != 0) {
			// arrayOfString[0] = #ID
			// arrayOfString[1] = name
			// arrayOfString[2] = type
			// arrayOfString[3] = T openTime
			// arrayOfString[4] = T closeTime
			String[] arrayOfString = currentLine.split("\\|");
			// Get ID by remove # character.
			int id = Integer.parseInt(arrayOfString[0].substring(1));
			// Get open time by remove T character.
			long open = Long.parseLong(arrayOfString[3].substring(1));
			// Get close time by remove T character.
			long close = Long.parseLong(arrayOfString[4].substring(1));
			// Create a AccountUnit.
			AccountUnit unit = new AccountUnit(id, arrayOfString[1], arrayOfString[2], new Time(open), new Time(close));
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
		backupFile.delete(); // Delete the existed file.
		try {
			backupFile.createNewFile(); // Create a new file.
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot create backup file for AccountDAO.");
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
			System.out.println("ERROR: Cannot open AccountDB.txt.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		try {
			backupOutput = new PrintWriter(backupFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ERROR: Cannot open backup file for AccountDAO.txt.");
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
			System.out.println("ERROR: Cannot open AccountDB.txt.");
			System.out.println("The program is terminated.");
			System.out.println();
			System.exit(0);
		}
		// Write each unit to the file.
		for (int i = 0; i < list.size(); i++) {
			fileOutput.println("#" + list.get(i).getID() + "|" + list.get(i).getName() + 
					"|" + list.get(i).getType() + "|T" + list.get(i).getOpen().getTime() +
					"|T" + list.get(i).getClose().getTime());
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
