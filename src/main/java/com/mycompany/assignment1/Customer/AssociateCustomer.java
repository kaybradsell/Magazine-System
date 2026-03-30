package com.mycompany.assignment1.Customer;

import com.mycompany.assignment1.Magazine.Supplement;
import java.util.List;

/**
 * Associate Customer
 * Class that holds the data for an associate customer,
 * that being a customer that is not paying for their supplements
 * and magazines, but another customer is.
 * 
 * @author  Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public class AssociateCustomer extends Customer
{
    // for now, customer stores nothing.
    // or it points back to their paying customer?
    private PayingCustomer m_payingCustomer;
    
    /**
     * Associate Customer Constructor
     * Constructs associate customer object with given details
     * 
     * @param name String
     * @param email String
     * @throws IllegalArgumentException if either parameter is invalid
     * 
     * @precondition  name and email are valid Strings
     * @postcondition creates Associate Customer object
     */
    public AssociateCustomer(String name, String email)
    {
        super(name, email);
        m_payingCustomer = null;
    }
    
    /**
     * Associate Customer Constructor
     * Constructs associate customer object with given details
     * 
     * @param name String
     * @param email String
     * @param supplements List of Supplement
     * @throws IllegalArgumentException if either parameter is invalid
     * 
     * @precondition  name and email are valid Strings
     * @postcondition creates Associate Customer object
     */
    public AssociateCustomer(String name, String email, List<Supplement> supplements)
    {
        super(name, email, supplements);
        m_payingCustomer = null;
    }
    
    /**
     * Associate Customer Copy Constructor
     * Constructs associate customer object from details of another Customer
     * 
     * @param other Customer
     * @throws IllegalArgumentException if any parameters are invalid
     * 
     * @precondition  Customer other is a valid customer
     * @postcondition copies all customer data to this customer.
     */
    public AssociateCustomer(Customer other)
    {
        super(other.GetName(), other.GetEmail(), other.GetSupplements());
        m_payingCustomer = null;
    }
    
    /**
     * Close Override
     * When closing this object, remove the payingCustomer reference
     * 
     * @precondition  Associate Customer is a constructed object
     * @postcondition deletes this associate customer and removes any reference of it.
     */
    @Override
    public void close()
    {
        RemovePayingCustomer();
    }
    
    /**
     * Get Paying Customer
     * If associate customer has a paying customer, return that customer
     * 
     * @return PayingCustomer, else null
     * 
     * @precondition  Associate customer is a valid object
     * @postcondition returns the Paying Customer associated with this object. Null if not
     */
    public PayingCustomer GetPayingCustomer()
    {
        if (m_payingCustomer != null)
            return m_payingCustomer;
        return null;
    }
    
    /**
     * Set Paying Customer
     * Sets the paying customer reference of this object.
     * 
     * @param payer PayingCustomer
     * @throws IllegalArgumentException if try to set to null, or this associate customer is not referenced by the paying customer we're linking it to.
     * 
     * @precondition  PayingCustomer already has reference to this associate customer
     * @postcondition sets the PayingCustomer reference of this object to the parameter.
     */
    public void SetPayingCustomer(PayingCustomer payer)
    {
        // check that paying customer already links to this one
        if (payer == null)
            throw new IllegalArgumentException("ERROR: AssociateCustomer: Trying to link to null Paying Customer.");
        if (!payer.ContainsAssociateCustomer(this))
            throw new IllegalArgumentException("ERROR: AssociateCustomer: Linking to Paying Customer that does not link to this one.");
        
        if (m_payingCustomer != null)
            m_payingCustomer.RemoveAssociateCustomer(this);
        
        m_payingCustomer = payer;
    }
    
    /**
     * Remove Paying Customer
     * Removes the paying customer reference from this customer
     * 
     * @precondition  valid constructed associate customer object
     * @postcondition removes the paying customer reference from this associate customer.
     */
    public void RemovePayingCustomer()
    {
        
        if (m_payingCustomer != null)
        {
            PayingCustomer temp = m_payingCustomer;
            m_payingCustomer = null;
            temp.RemoveAssociateCustomer(this);
        }
    }
    
}
