/*
 * Author: Bellamy Phan.
 * Date Created: 2020/12/07.
 */

import java.util.Scanner;

public class Main {
	
	// Create the timeZone for this program.
	// All the classes in this program can use timeZone, make it public.
	// Default timeZone is -6 TX.
	public static Integer timeZone = -6;

	// This is the main method.
	public static void main(String[] args) {
		
		
		// Introduction.
		System.out.println();
		System.out.println("MoneyReport_Version_1.0");
		System.out.println("This program was compile by Java.");
		System.out.println("Use Terminal.app to run this program.");
		System.out.println();
		
		
		// Get the time zone.
		Scanner input = new Scanner(System.in);
		System.out.println("Default timeZone for TX is -6");
		System.out.println("Change the timezone in the Source files if needed.");	
		System.out.println();
		
		
		// Read input from the keyboard.
		int option = 0;
		System.out.println("1. Transaction options.");
		System.out.println("2. Account options.");
		System.out.println("3. Source options.");
		System.out.println("4. Type options.");
		System.out.println("5. Location options.");
		System.out.println("6. Report options.");
		System.out.println("0. Save and Exit.");
		System.out.print("Your choice: ");
		option = input.nextInt();
		System.out.println();
		switch (option) {
		case 1:
			System.out.println("Transaction options.");
			System.out.println("This function is not completed.");
			System.out.println();
			break;
		case 2:
			AccountDAO account = new AccountDAO();
			account.menu();
			break;
		case 3:
			System.out.println("Source options.");
			System.out.println("This function is not completed.");
			System.out.println();
			break;
		case 4:
			System.out.println("Type options.");
			System.out.println("This function is not completed.");
			System.out.println();
			break;
		case 5:
			System.out.println("Location options.");
			System.out.println("This function is not completed.");
			System.out.println();
			break;
		case 6:
			System.out.println("Report options.");
			System.out.println("This function is not completed.");
			System.out.println();
			break;
		default:
			break;
		}
		
		
		// Close input.
		input.close();
		
		
		// The program terminated properly.
		System.out.println("The program terminated properly.");
		System.out.println();
	}
	
	
	
	
	
	
	
	
}
