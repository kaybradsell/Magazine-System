package com.mycompany.assignment1.PaymentMethods;

/**
 * Payment Method
 * Abstract class that holds data for a payment method.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public abstract class PaymentMethod
{
    private String m_accountName;
    
    /**
     * Payment Method Constructor
     * constructs an empty payment method with placeholder accountName
     * 
     * @precondition  none
     * @postcondition Constructs payment method with placeholder values.
     */
    public PaymentMethod()
    {
        m_accountName = "placeholder";
    }
    
    /**
     * Payment Method Constructor with parameters
     * Constructs a payment method object with given parameters
     * 
     * @param accountName String
     * @throws IllegalArgumentException if parameters are invalid
     * 
     * @precondition  accountName must be non-null String
     * @postcondition constructs object with the parameters given.
     */
    public PaymentMethod(String accountName)
    {
        if (CheckValidAccountName(accountName))
            m_accountName = accountName;
    }
    
    /**
     * Get Account Name
     * Returns the string account name for this payment method object
     * 
     * @return accountName String
     * 
     * @precondition  payment method must be constructed object
     * @postcondition returns String accountName
     */
    public String GetAccountName()
    {
        return m_accountName;
    }
    
    /**
     * Set Account Name
     * Sets the account name of this object given it is valid.
     * 
     * @param accountName 
     * @throws IllegalArgumentException if parameters are invalid
     * 
     * @precondition  constructed payment method and new name is valid
     * @postcondition sets payment method's accountname to new string
     */
    public void SetAccountName(String accountName)
    {
        if (CheckValidAccountName(accountName))
            m_accountName = accountName;
    }
    
    /**
     * Check Valid Account Name
     * Checks and returns if the accountName String is valid. Throws exception if not.
     * 
     * @param accountName String
     * @return true if accountName is valid
     * @throws IllegalArgumentException if the accountName is null
     * 
     * @precondition  accountName is a non-null String
     * @postcondition returns true if it is, throws exception if not.
     */
    private static boolean CheckValidAccountName(String accountName)
    {
        if (accountName == null || accountName.trim().isEmpty())
            throw new IllegalArgumentException("ERROR: Customer: Cannot have null name.");
        
        return true;
    }
}
