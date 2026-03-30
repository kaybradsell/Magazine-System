package com.mycompany.assignment1.PaymentMethods;

/**
 * Payment Method Factory
 * Factory that generates Payment Method object given type
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 18/03/2026
 */
public class PaymentMethodFactory
{  
    /**
     * Payment Type Enum
     * For picking which type of payment type
     */
    public enum PaymentType
    {
        CREDIT,
        DEBIT,
    };
    
    /**
     * Create
     * Given PaymentType and parameters, create Payment Type and return it
     * 
     * @param type PaymentType
     * @param accountName String
     * @return PaymentMethod
     * @throws IllegalArgumentException if any parameters are invalid.
     * 
     * @precondition  valid type and accountName
     * @postcondition returns PaymentMethod subclass for that type
     */
    public static PaymentMethod Create(PaymentType type, String accountName)
    {
        switch (type)
        {
            case CREDIT:
                return new CreditPaymentMethod(accountName);
            case DEBIT:
                return new DebitPaymentMethod(accountName);
            default:
                throw new IllegalArgumentException("PaymentMethod: Invalid payment method type.");
        }
    }
}
