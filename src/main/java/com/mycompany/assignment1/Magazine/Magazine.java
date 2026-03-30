package com.mycompany.assignment1.Magazine;

/**
 * Supplement
 * Class that holds the data for a magazine supplement.
 * It stores the name and cost for that supplement.
 * 
 * @author  Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public class Magazine
{
    // private vars
    private String m_name;
    private double m_cost;
        
    /**
     * Magazine Constructor with parameters
     * Constructs a magazine object with values given.
     * 
     * @param  name String
     * @param  cost double
     * @throws IllegalArgumentException if parameters are invalid.
     * 
     * @precondition  name must be non-null String, cost must be positive double.
     * @postcondition constructs supplement object with those values.
     */
    public Magazine(String name, double cost)
    {
        if (CheckValidName(name))
            m_name = name;
        
        if (CheckValidCost(cost))
            m_cost = cost;
    }
    
    /**
     * Magazine Copy Constructor
     * Constructs a magazine object with the values of another magazine object
     * 
     * @param other Magazine
     * @throws IllegalArgumentException if any parameters are invalid
     * 
     * @precondition  Valid Magazine other object
     * @postcondition sets all values of this Magazine to other.
     */
    public Magazine(Magazine other)
    {
        if (CheckValidName(other.m_name))
            m_name = other.m_name;
        
        if (CheckValidCost(other.m_cost))
            m_cost = other.m_cost;
    }
    
    // SETTERS //
    
    /**
     * Set Name
     * Sets the name of the magazine, in case it changes.
     * 
     * @param  name String
     * @throws IllegalArgumentException if name is null
     * 
     * @precondition  name must be non-null String.
     * @postcondition changes magazine name to parameter.
     */
    public void SetName(String name)
    {
        if (CheckValidName(name))
            m_name = name;
    }
    
    /**
     * Set Cost
     * Sets the cost of the magazine, in case it changes
     * 
     * @param  cost double
     * @throws IllegalArgumentException if cost is negative.
     * 
     * @precondition  cost must be positive
     * @postcondition changes magazine cost to parameter.
     */
    public void SetCost(double cost)
    {
        if (CheckValidCost(cost))
            m_cost = cost;
    }
    
    // GETTERS //
    /**
     * Get Name
     * Returns the name of the magazine
     * 
     * @return name String
     * 
     * @precondition  Magazine must be constructed
     * @postcondition Returns string name.
     */
    public String GetName()
    {
        return m_name;
    }
    
    /**
     * Get Cost
     * Returns the cost of the magazine
     * 
     * @return cost double
     * 
     * @precondition  Magazine must be constructed
     * @postcondition Returns double cost.
     */
    public double GetCost()
    {
        return m_cost;
    }
    
    /**
     * Is Same
     * Checks if one Magazine is the same as this one
     * 
     * @param  other Magazine
     * @return true if same, false if not
     * 
     * @precondition  Both this magazine and other have been constructed
     * @postcondition returns if they are the same magazine or not.
     */
    public boolean IsSame(Magazine other)
    {
        return other.m_name.equals(this.m_name);
    }
    
    /**
     * Check Valid Cost
     * Helper function that validates the double cost.
     * Throws exception if the cost is not valid.
     * 
     * @param  cost double
     * @return true if cost is valid
     * @throws IllegalArgumentException if cost is negative
     * 
     * @precondition  cost is a positive double
     * @postcondition returns true if the cost is positive, else throws an exception.
     */
    private static boolean CheckValidCost(double cost)
    {
        if (cost < 0.00)
            throw new IllegalArgumentException("ERROR: Supplement: Cost cannot be negative.");
        
        return true;
    }
    
    /**
     * Check Valid Name
     * Helper function that validates the String name.
     * Throws exception if the name is null.
     * 
     * @param  name String
     * @return true if name is valid.
     * @throws IllegalArgumentException if name is null
     * 
     * @precondition  name is a non-null String
     * @postcondition returns true if the name is valid, else throws exception.
     */
    private static boolean CheckValidName(String name)
    {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("ERROR: Supplement: Name cannot be null.");
        
        return true;
    }
}
