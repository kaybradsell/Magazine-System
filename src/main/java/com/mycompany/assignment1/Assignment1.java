package com.mycompany.assignment1;

import com.mycompany.assignment1.Customer.*;
import com.mycompany.assignment1.Magazine.*;

import java.util.List;
import java.util.Scanner;

/**
 * Assignment
 * Main class for client.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 20/03/2026
 */
public class Assignment1 {

    public static void main(String[] args)
    {
        // Uncomment if you want to see the tests:
        //CustomerTests.ExecuteTests();
        
        ClientIO.DisplayStudentDetails();
        
        // hard-code (as requested from assignment) details:
        Magazine mag = new Magazine("Eiyuu Densetsu", 30.00);
        List<Supplement> supps = DataModifier.FillSupplementData();
        List<Customer> custs = DataModifier.FillCustomerData(supps);
        
        // menu :D
        int option;
        Scanner scanner = new Scanner(System.in);
        
        do {
            ClientIO.DisplayMenu();
            option = ClientIO.GetIntInput(scanner);
            
            switch (option)
            {
                case 1: // print weekly
                    System.out.println("Printing the next four weeks of emails...");
                    for (int i = 0; i < 4; i++)
                    {
                        System.out.println("-------- WEEK " + (i+1) + " --------");
                        for (Customer cust : custs)
                        {
                            System.out.println("\n\n!!! Sending to " + cust.GetEmail() + " !!!\n");
                            ClientIO.PrintToCLI(CustomerView.GenerateWeeklyEmailString(cust, mag));
                        }
                    }
                    break;
                    
                case 2: // print monthly
                    System.out.println("Printing the end-of-month emails...");
                    for (Customer cust : custs)
                    {
                        if (cust instanceof PayingCustomer)
                        {
                            PayingCustomer c = (PayingCustomer) cust;
                            System.out.println("\n\n!!! Sending to " + cust.GetEmail() + " !!!\n");
                            ClientIO.PrintToCLI(CustomerView.GenerateMonthlyEmailString(c, mag));
                        }
                    }
                    break;
                    
                case 3: // add new customer
                    System.out.println("\nAdding a new customer. Please input details.");
                    DataModifier.CreateCustomer(scanner, custs, supps);
                    break;
                    
                case 4: // remove customer
                    //DataModifier.RemoveCustomer(scanner, custs);
                    DataModifier.RemoveCustomer(scanner, custs);
                    break;
                case 5:
                    System.out.println("Exiting Program...");
                    break;
                default:
                    System.out.println("Invalid input...");
                    break;
            }
        }
        while (option != 5);
        
    }

}
