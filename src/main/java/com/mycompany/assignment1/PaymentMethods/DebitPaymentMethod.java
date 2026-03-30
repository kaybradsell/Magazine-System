package com.mycompany.assignment1.PaymentMethods;

/**
 * Debit Payment Method
 * Class that holds the data for a debit payment method
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public class DebitPaymentMethod extends PaymentMethod
{
    /**
     * Debit Payment Method Constructor
     * Constructs a debit payment method with placeholder parameters.
     * 
     * @precondition  none
     * @postcondition constructs debit payment method
     */
    public DebitPaymentMethod()
    {
        super();
    }
    
    /**
     * Debit Payment Method Constructor with parameters
     * Constructs a debit payment method object with given parameters.
     * 
     * @param accountName String
     * @throws IllegalArgumentException if parameters are invalid.
     * 
     * @precondition  accountName must be non-null String.
     * @postcondition constructs object with the parameters given. 
     */
    public DebitPaymentMethod(String accountName)
    {
        super(accountName);
    }
}
