package com.mycompany.assignment1.Customer;

import java.util.List;
import java.util.ArrayList;
import com.mycompany.assignment1.Magazine.Supplement;
import com.mycompany.assignment1.PaymentMethods.PaymentMethod;

/**
 * Paying Customer
 * Class that holds the data for a paying customer
 * that being a customer that pays for their own goods, and sometimes
 * for other associate customers.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public class PayingCustomer extends Customer
{
    // stores a Payment Method
    private PaymentMethod m_paymentMethod;
    private List<AssociateCustomer> m_associateCustomers;
    
    /**
     * Paying Customer Constructor with parameters
     * Given parameters, construct a paying customer.
     * 
     * @param name String
     * @param email String
     * @param payment PaymentMethod
     * @throws IllegalArgumentException if any parameters are invalid
     * 
     * @precondition  all parameters are valid
     * @postcondition creates paying customer object with those parameters
     */
    public PayingCustomer(String name, String email, PaymentMethod payment)
    {
        super(name, email);
        if (payment == null)
            throw new IllegalArgumentException("ERROR: Paying Customer: payment method cannot be null.");
        
        m_paymentMethod = payment;
        m_associateCustomers = new ArrayList<>();
    }
    
    /**
     * Paying Customer Constructor with parameters
     * Given parameters, construct a paying customer.
     * 
     * @param name String
     * @param email String
     * @param supplements List of Supplement
     * @param payment PaymentMethod
     * @throws IllegalArgumentException if any parameters are invalid
     * 
     * @precondition  all parameters are valid
     * @postcondition creates paying customer object with those parameters
     */
    public PayingCustomer(String name, String email, List<Supplement> supplements, PaymentMethod payment)
    {
        super(name, email, supplements);
        if (payment == null)
            throw new IllegalArgumentException("ERROR: Paying Customer: payment method cannot be null.");
        
        m_paymentMethod = payment;
        m_associateCustomers = new ArrayList<>();
    }
    
    /**
     * Paying Customer Copy Constructor
     * Given another customer and a payment method, create object
     * 
     * @param other Customer
     * @param payment PaymentMethod
     * @throws IllegalArgumentException if any parameters are invalid.
     * 
     * @precondition  Customer is a valid customer, as is PaymentMethod
     * @postcondition creates paying customer object with those parameters.
     */
    public PayingCustomer(Customer other, PaymentMethod payment)
    {
        super(other.GetName(), other.GetEmail());
        
        if (payment == null)
            throw new IllegalArgumentException("ERROR: Paying Customer: payment method cannot be null.");
        
        m_paymentMethod = payment;
        m_associateCustomers = new ArrayList<>();
        
        // copy over all the supplements.
        for (Supplement supp : other.GetSupplements())
        {
            super.AddSupplement(supp);
        }
    }
    
    /**
     * Close Override
     * When closing this object, ensure there are no associate customers attached.
     * 
     * @throws IllegalStateException if Paying Customer still has Associate customers.
     * 
     * @precondition  Paying Customer must NOT have any associate customers linked
     * @postcondition deletes this object, unless they have associate customers
     */
    @Override
    public void close()
    {
        // basically if there are associate customers, you
        // CANNOT DELETE THIS CUSTOMER BECAUSE IT'S GONNA LEAVE
        // A BUNCH OF ASSOCIATE CUSTOMERS STRANDED.
        
        if (!m_associateCustomers.isEmpty())
            throw new IllegalStateException("ERROR: Customer: Cannot delete paying customer if they still have associate customers.");
    }
    
    /**
     * Set Payment Method
     * Given parameter, set this payment method to the parameter.
     * 
     * @param payment PaymentMethod
     * @throws IllegalArgumentException if payment method is invalid
     * 
     * @precondition  valid paymentmethod parameter
     * @postcondition sets this objects paymentmethod to the parameter.
     */ 
    public void SetPaymentMethod(PaymentMethod payment)
    {
        if (payment == null)
            throw new IllegalArgumentException("ERROR: Paying Customer: payment method cannot be null.");
        m_paymentMethod = payment;
    }
    
    /**
     * Get Payment Method
     * Returns the payment method for this paying customer
     * 
     * @return PaymentMethod
     * 
     * @precondition  valid PayingCustomer object
     * @postcondition returns paymentmethod.
     */
    public PaymentMethod GetPaymentMethod()
    {
        return m_paymentMethod;
    }
    
    /**
     * Get Associate Customers
     * Returns the list of associate customers tied to this paying customer
     * 
     * @return List of AssociateCustomers
     * 
     * @precondition  valid PayingCustomer object
     * @postcondition returns all Associate Customers
     */
    public List<AssociateCustomer> GetAssociateCustomers()
    {
        return m_associateCustomers;
    }
    
    /**
     * Add Associate Customer
     * Adds an associate customer to this paying object.
     * 
     * @param associate AssociateCustomer
     * 
     * @precondition  valid AssociateCustomer and PayingCustomer objects
     * @postcondition adds AssociateCustomer to this PayingCustomer.
     */
    public void AddAssociateCustomer(AssociateCustomer associate)
    {
        if (!m_associateCustomers.contains(associate) && associate != null)
        {
            // add paying customer to associate
            m_associateCustomers.add(associate);
            associate.SetPayingCustomer(this);
        }
    }
    
    /**
     * Remove Associate Customer
     * Removes an associate customer from the paying customer
     * 
     * @param associate AssociateCustomer
     * 
     * @precondition  valid AssociateCustomer and PayingCustomer object
     * @postcondition removes the AssociateCustomer from this PayingCustomer, and removes this PayingCustomer from that AssociateCustomer
     */
    public void RemoveAssociateCustomer(AssociateCustomer associate)
    {
        if (m_associateCustomers.contains(associate) && associate != null)
        {
            // remove paying customer from associate
            associate.RemovePayingCustomer();
            m_associateCustomers.remove(associate);
        }
    }
    
    /**
     * Contains Associate Customer
     * Checks and returns if this Paying Customer holds onto the Associate Customer
     * 
     * @param customer AssociateCustomer
     * @return true if it does, false if not
     * 
     * @precondition  valid PayingCustomer and AssociateCustomer object
     * @postcondition returns bool if it exists or not.
     */
    public boolean ContainsAssociateCustomer(AssociateCustomer customer)
    {
        return (m_associateCustomers.contains(customer));
    }
}
