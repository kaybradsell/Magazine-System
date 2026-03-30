package com.mycompany.assignment1.PaymentMethods;

/**
 * Credit Payment Method
 * Class that holds the data for a credit payment method
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public class CreditPaymentMethod extends PaymentMethod
{
    /**
     * Credit Payment Method Constructor
     * Constructs a credit payment method with placeholder parameters.
     * 
     * @precondition  none
     * @postcondition constructs credit payment method
     */
    public CreditPaymentMethod()
    {
        super();
    }
    
    /**
     * Credit Payment Method Constructor with parameters
     * Constructs a credit payment method object with given parameters.
     * 
     * @param accountName String
     * @throws IllegalArgumentException if parameters are invalid.
     * 
     * @precondition  accountName must be non-null String.
     * @postcondition constructs object with the parameters given. 
     */
    public CreditPaymentMethod(String accountName)
    {
        super(accountName);
    }
}
