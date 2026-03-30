package com.mycompany.assignment1;

import com.mycompany.assignment1.Customer.*;
import com.mycompany.assignment1.Magazine.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Client IO
 * Handles input for manipulating customers.
 * Takes data to output to CLI, and returns int/string of user input/selection.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 20/03/2026
 */
public class CustomerInputHandler
{
    /**
     * Get Valid Name
     * Gets a valid String name input from user.
     * 
     * @param in Scanner
     * @return String
     * 
     * @precondition  none
     * @postcondition returns string name from user input.
     */
    public static String GetValidName(Scanner in)
    {
        String name;
        
        do {
            name = ClientIO.GetStringInput(in);
        } while (!InputValidator.IsValidName(name));
        
        return name;
    }

    /**
     * Get Valid Email
     * Gets a valid String email input from user.
     * 
     * @param in Scanner
     * @return String
     * 
     * @precondition  none
     * @postcondition returns string email from user input.
     */
    public static String GetValidEmail(Scanner in)
    {
        String email;
        
        do {
            System.out.println("Enter email: ");
            email = ClientIO.GetStringInput(in);
        } while (!InputValidator.IsValidEmail(email));
        
        return email;
    }
    
    /**
     * Get User Supplements
     * Prints supplements to user and returns a list of supplements chosen by user.
     * 
     * @param in Scanner
     * @param supplements List of Supplements
     * @return List of Supplements
     * 
     * @precondition  supplements is a valid list with values.
     * @postcondition returns list of supplements chosen by user.
     */
    public static List<Supplement> GetUserSupplements(Scanner in, List<Supplement> supplements)
    {
        List<Supplement> supps = new ArrayList<>();
        
        System.out.println("Input the corresponding supplement you wish to view.");
        for (int i = 0; i < supplements.size(); i++)
        {
            System.out.println(" (" + i + ") " + supplements.get(i).GetName());
        }
        System.out.println(" (" + supplements.size() + ") Stop");
        
        int input;
        do {
            input = ClientIO.GetIntInput(in);
            
            if (input < supplements.size() && input >= 0)
            {
                if (supps.contains(supplements.get(input)))
                    System.out.println("Already have that supplement.");
                else
                {
                    supps.add(supplements.get(input));
                    System.out.println("Added " + supplements.get(input).GetName() + ".");
                }
            }
            else if (input != supplements.size())
                System.out.println("Invalid Input.");
        } while (input != supplements.size());
        
        return supps;
    }
    
    /**
     * Get User Paying Customer
     * After printing a list of paying customers from customers, allow the user to choose one and return reference to it.
     * 
     * @param in Scanner
     * @param customers List of Customer
     * @return PayingCustomer
     * 
     * @precondition  List of Customers contains Paying Customers
     * @postcondition returns the customer chosen by user. Or null.
     */
    public static PayingCustomer GetUserPayingCustomer(Scanner in, List<Customer> customers)
    {
        List<PayingCustomer> payers = new ArrayList<>();
        int index = 0;
        
        System.out.println("Input corresponding number for Customer to link to.");
        for (Customer cust : customers)
        {
            if (cust instanceof PayingCustomer)
            {
                System.out.println(" (" + index++ + ") " + cust.GetName());
                payers.add((PayingCustomer)cust);
            }
        }
        
        if (payers.isEmpty())
        {
            System.out.println("No Paying Customers...");
            return null; // if no payingcustomers, return null
        }
        
        int input;
        
        do {
            input = ClientIO.GetIntInput(in);
            
            if (!(input < payers.size() && input >= 0))
                System.out.println("Invalid input.");
        } while (input >= payers.size() || input < 0);
       
        return payers.get(input);
    }
    
    /**
     * Get User Customer
     * After printing all customers, allow the user to return one Customer reference.
     * 
     * @param in Scanner
     * @param customers List of Customers
     * @return Customer
     * 
     * @precondition  valid list of Customers
     * @postcondition returns one Customer.
     */
    public static Customer GetUserCustomer(Scanner in, List<Customer> customers)
    {
        System.out.println("Input corresponding number for Customer to remove.");
        for (int i = 0; i < customers.size(); i++)
        {
            System.out.println(" (" + i + ") " + customers.get(i).GetName());
        }
        
        int input;
        
        do {
            input = ClientIO.GetIntInput(in);
            
            if (!(input < customers.size() && input >= 0))
                System.out.println("Invalid input.");
        } while (input >= customers.size() || input < 0);
       
        return customers.get(input);
    }
}
