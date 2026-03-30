package com.mycompany.assignment1;

import java.util.Scanner;

/**
 * Client IO
 * Handles all basic input and output for client program.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 20/03/2026
 */
public class ClientIO
{
    /**
     * Display Student Details
     * Prints to CLI student's (Kay Bradsell's) details
     * 
     * @precondition  none
     * @postcondition prints to CLI
     */
    public static void DisplayStudentDetails()
    {
        System.out.println("---------------------------------------\nStudent Name: Kay Bradsell\nStudent Number: 35110401\nEnrolement Type: External\n---------------------------------------\n");
    }
    
    /**
     * Display Menu
     * Displays menu for client program
     * 
     * @precondition  none
     * @postcondition prints to CLI
     */
    public static void DisplayMenu()
    {
        System.out.println("\n-------------------------------------------------\nInput value to corresponding action:\n (1) Print out all four weeks of magazines\n (2) Print out end of month emails\n (3) Add a new customer to service\n (4) Remove customer from service\n (5) Exit.");
    }
    
    /**
     * Print to Command Line Interface
     * Prints string to CLI.
     * 
     * @param string String
     * 
     * @precondition  String has data
     * @postcondition prints string to CLI.
     */
    public static void PrintToCLI(String string)
    {
        System.out.println(string);
    }
    
    /**
     * Get Int Input
     * From user, get an integer value. Loops until valid input.
     * 
     * @param in Scanner
     * @return int
     * 
     * @precondition  none
     * @postcondition returns int after validating.
     */
    public static int GetIntInput(Scanner in)
    {
        System.out.print(">>> ");
        
        while (!in.hasNextInt())
        {
            System.out.print("Invalid input.\n>>> ");
            in.next();
        }
        
        int value = in.nextInt();
        in.nextLine();
        return value;
    }
    
    /**
     * Get String Input
     * From user, get a string value. Loops until valid input.
     * 
     * @param in Scanner
     * @return String
     * 
     * @precondition  none
     * @postcondition returns String after validating.
     */
    public static String GetStringInput(Scanner in)
    {
        System.out.print(">>> ");
        String input = in.nextLine();
        
        while (input.isBlank())
        {
            System.out.print("Invalid input.\n>>> ");
            input = in.nextLine();
        }
        
        return input;
    }
    
    /**
     * Get Boolean Input
     * From user, get a boolean value (1/2). Loops until valid input.
     * 
     * @param in Scanner
     * @return true if 1, false if 2
     * 
     * @precondition  none
     * @postcondition returns boolean after validating.
     */
    public static boolean GetBooleanInput(Scanner in)
    {
        // its a 1 or 2 option.
        int input;
        do {
            System.out.print(">>> ");
            input = GetIntInput(in);
        } while (input != 1 && input != 2);
        return (input == 1);
    }
}