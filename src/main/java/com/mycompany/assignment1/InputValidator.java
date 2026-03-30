package com.mycompany.assignment1;

import com.mycompany.assignment1.Customer.*;
import java.util.List;

/**
 * Client IO
 * Handles validating customer data.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 20/03/2026
 */
public class InputValidator
{
    /**
     * Is Valid Name
     * Returns if string name is valid.
     * 
     * @param name String
     * @return true if it is, false if not
     * 
     * @precondition  none
     * @postcondition returns if name is valid.
     */
    public static boolean IsValidName(String name)
    {
        return (!name.trim().isBlank());
    }
    
    /**
     * Is Email Valid
     * Returns if string email is valid
     * 
     * @param email String
     * @return true if it is, false if not
     * 
     * @precondition  none
     * @postcondition returns if email is valid.
     */
    public static boolean IsValidEmail(String email)
    {
        if (email == null || email.trim().isEmpty())
            return false;
        else if (!email.contains("@") || !email.contains("."))
            return false;
        
        return true;
    }
    
    /**
     * Does Customer Already Exist
     * Checks and returns if a customer is already in a list of customers.
     * 
     * @param customers List of Customer
     * @param customer Customer
     * @return true if it is, false if not.
     * 
     * @precondition  filled out list of Customers (or not). And a valid customer object
     * @postcondition true if customer is in that list, false if not.
     */
    public static boolean DoesCustomerAlreadyExist(List<Customer> customers, Customer customer)
    {
        for (Customer cust : customers)
        {
            if (cust.IsSame(customer)) return true;
        }
        return false;
    }
}
