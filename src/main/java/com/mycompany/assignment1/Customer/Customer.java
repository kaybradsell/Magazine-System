package com.mycompany.assignment1.Customer;

import java.util.List;
import java.util.ArrayList;
import com.mycompany.assignment1.Magazine.Supplement;

/**
 * Customer
 * Class that holds data for customers, such as their name, email, and supplements
 * they wish to receive.
 * Customer is abstract and should not be initialised. Please use the subclasses.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public abstract class Customer implements AutoCloseable
{
    // private vars
    private String m_name;
    private String m_email;
    private List<Supplement> m_supplements;
    
    /**
     * Customer Constructor with parameters sans Supplements
     * Constructs a customer object with values given, except for supplements.
     * 
     * @param name String
     * @param email String
     * @throws IllegalArgumentException if name or email are invalid.
     * 
     * @precondition  name must be non-null, email must be non-null and contain @ and . .
     * @postcondition constructs customer object with those values
     */
    public Customer(String name, String email)
    {
        if (CheckValidName(name))
            m_name = name;
        if (CheckValidEmail(email))
            m_email = email;
        m_supplements = new ArrayList<>();
    }
    
    /**
     * Customer Constructor with parameters
     * Constructs a customer object with values given, except for supplements.
     * 
     * @param name String
     * @param email String
     * @param supplements List of Supplement
     * @throws IllegalArgumentException if name or email are invalid.
     * 
     * @precondition  name must be non-null, email must be non-null and contain @ and . .
     * @postcondition constructs customer object with those values
     */
    public Customer(String name, String email, List<Supplement> supplements)
    {
        if (CheckValidName(name))
            m_name = name;
        if (CheckValidEmail(email))
            m_email = email;
        
        m_supplements = new ArrayList<>();
        for (Supplement supp : supplements)
        {
            AddSupplement(supp);
        }
    }
    
    @Override
    public void close() {}
    
    // SETTERS //
    /**
     * Set Name
     * Sets the name of the customer, in case it changes
     * 
     * @param name String
     * @throws IllegalArgumentException if name is null
     * 
     * @precondition  name must be non-null String
     * @postcondition changes customer name to parameter.
     */
    public void SetName(String name)
    {
        if (CheckValidName(name))
            m_name = name;
    }
    
    /**
     * Set Email
     * Sets the email of the customer, in case it changes
     * 
     * @param email String
     * @throws IllegalArgumentException if email is null or does not contain @ or .
     * 
     * @precondition  email must be non-null String and contain @ and .
     * @postcondition changes customer email to parameter.
     */
    public void SetEmail(String email)
    {
        if (CheckValidEmail(email))
            m_email = email;
    }
    
    /**
     * Set Supplements
     * Changes the supplements the customer has in one batch.
     * 
     * @param supplements List of Supplements
     * 
     * @precondition  List contains valid Supplements.
     * @postcondition customer supplements now stores new supplements.
     */
    public void SetSupplements(List<Supplement> supplements)
    {
        m_supplements = new ArrayList<>(supplements);
    }
    
    // GETTERS //
    
    /**
     * Get Name
     * Returns the name value for this customer
     * 
     * @return name String
     * 
     * @precondition  customer has name.
     * @postcondition returns the String name of customer
     */
    public String GetName()
    {
        return m_name;
    }
    
    /**
     * Get Email
     * Returns the email for this customer
     * 
     * @return email String
     * 
     * @precondition  customer has been constructed
     * @postcondition returns the String email of customer
     */
    public String GetEmail()
    {
        return m_email;
    }
    
    /**
     * Get Supplements
     * Returns the list of every supplement this customer wants.
     * 
     * @return List of Supplements
     *
     * @precondition  Customer has been constructed
     * @postcondition returns List of Supplements
     */
    public List<Supplement> GetSupplements()
    {
        return m_supplements;
    }
    
    /**
     * Is Same
     * Checks if one customer is the same customer object as another.
     *
     * @param  other Customer
     * @return true if same, false if not
     * 
     * @precondition  Both this customer and other customer have been constructed
     * @postcondition returns if they are the same customer or not.
     */
    public boolean IsSame(Customer other)
    {
        if (other == null)
            return false;
        return other.m_email.toLowerCase().equals(this.m_email.toLowerCase());
    }
    
    /**
     * Add Supplement
     * Given a new supplement, add it to the customers list of supplements
     * 
     * @param supplement Supplement
     * 
     * @precondition  Supplement is a valid supplement
     * @postcondition supplement is added to customer, unless it already is in customer.
     */
    public void AddSupplement(Supplement supplement)
    {
        // check if supplement already in.
        if (!m_supplements.contains(supplement))
            m_supplements.add(supplement);
    }
    
    /**
     * Remove Supplement
     * Given a supplement, remove it from the customers list of supplements
     * 
     * @param supplement Supplement
     * 
     * @precondition  Supplement is a valid supplement
     * @postcondition supplement is removed from customer if it existed.
     */
    public void RemoveSupplement(Supplement supplement)
    {
        if (m_supplements.contains(supplement))
            m_supplements.remove(supplement);
    }
    
    /**
     * Check Valid Name
     * Checks and returns if the name String is valid. Throws exception if not.
     * 
     * @param name String
     * @return true if name is valid
     * @throws IllegalArgumentException if the name is null
     * 
     * @precondition  name is a non-null String
     * @postcondition returns true if it is, throws exception if not.
     */
    private static boolean CheckValidName(String name)
    {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("ERROR: Customer: Cannot have null name.");
        
        return true;
    }
    
    /**
     * Check Valid Email
     * Checks and returns if the email String is valid. Throws exception if not.
     * Email is considered valid if it is non-null and contains @ and .
     * 
     * @param email String
     * @return true if it is valid
     * @throws IllegalArgumentException if not.
     * 
     * @precondition  email is a non-null String containing @ and .
     * @postcondition returns true if it is, throws exception if not.
     */
    private static boolean CheckValidEmail(String email)
    {
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("ERROR: Customer: Email cannot be empty.");
        if (!email.contains("@") || !email.contains("."))
            throw new IllegalArgumentException("ERROR: Customer: Email is not valid. Missing @ and/or . .");
        
        return true;
    }
}
