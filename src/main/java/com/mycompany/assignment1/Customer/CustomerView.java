package com.mycompany.assignment1.Customer;

import com.mycompany.assignment1.Magazine.*;
import java.util.Map;
import java.util.HashMap;

/**
 * Customer View
 * Class that stores functions to generate emails and other outputs
 * for customer classes.
 * 
 * @author Asch
 * @version 1.0 19/03/2026
 */
public class CustomerView
{
    /**
     * Generate Weekly Email String
     * Given customer and magazine, generate the email to send to the
     * customer, outlining magazine, and any supplements they are subscribed to.
     * 
     * @param customer Customer
     * @param magazine Magazine
     * @return String email
     * 
     * @precondition  Customer and Magazine are both valid objects
     * @postcondition returns a string of that email.
     */
    public static String GenerateWeeklyEmailString(Customer customer, Magazine magazine)
    {
        String text;
        
        text = "Dear " + customer.GetName() + ",\n";
        text += "The magazine you are subscribed to, " + magazine.GetName() + ", has updated for the week!\n";
        
        if (!customer.GetSupplements().isEmpty())
        {
            text += "On top of this, the supplements you are subscribed to: \n";
            for (Supplement supp : customer.GetSupplements())
                text += " - " + supp.GetName() + "\n";
            text += "have also been updated for your viewing pleasure.\n";
        }
        
        text += "Thank you for subscribing to our service.\n";
        text += "\nKind Regards,\nMagazine Service.";
        
        return text;
    }
    
    /**
     * Generate Monthly Email String
     * Given a PayingCustomer and a Magazine, output an email for their
     * monthly invoice. This includes the magazine, all associate customers
     * they are paying for's magazine, their supplements, and the associate
     * customers supplements. It will output the base costs, the amount, and the
     * total cost, as well as outline which customers they are paying for.
     * Displayed in $.
     * 
     * @param customer Customer
     * @param magazine Magazine
     * @return String email
     * 
     * @precondition  PayingCustomer and Magazine are both valid objects
     * @postcondition returns the output email to send.
     */
    public static String GenerateMonthlyEmailString(PayingCustomer customer, Magazine magazine)
    {
        // double check this is PayingCustomer
        if (!(customer instanceof PayingCustomer))
            throw new IllegalArgumentException("ERROR: Customer: Cannot use AssociateCustomer for PayingCustomer parameter.");
        
        // calculate all costs
        int amountOfMagazines = 1 + customer.GetAssociateCustomers().size();
        double magazineCost = amountOfMagazines * magazine.GetCost();
        double totalCost = magazineCost;
        Map<Supplement, Integer> supplements = new HashMap<>();
        
        for (AssociateCustomer cust : customer.GetAssociateCustomers())
        {
            for (Supplement supp : cust.GetSupplements())
            {
                if (!supplements.containsKey(supp))
                    supplements.put(supp, 1);
                else
                    supplements.put(supp, supplements.get(supp) + 1);
            }
        }
        
        for (Supplement supp : customer.GetSupplements())
        {
            if (!supplements.containsKey(supp))
                supplements.put(supp, 1);
            else
                supplements.put(supp, supplements.get(supp) + 1);
        }
        
        String text = "Dear " + customer.GetName() + ",\n";
        text += "As you are a paying customer, here is the invoice for this month's subscription.";
        text += "\n- Magazines (" + magazine.GetCost() + ") x" + amountOfMagazines + ": $" + magazineCost + ".\n";
        
        for (Map.Entry map : supplements.entrySet())
        {
            Supplement supp = (Supplement)map.getKey();
            int amount = (int)map.getValue();
            text += "- " + supp.GetName() + " (" + supp.GetCost() + ") x" + amount + ": $" + supp.GetCost() * amount + ".\n";
            totalCost += supp.GetCost() * amount;
        }
        
        text += "Total Cost: $" + totalCost + " will be charged to " + customer.GetPaymentMethod().GetAccountName() + ".\n";
        
        if (!customer.GetAssociateCustomers().isEmpty())
        {
            text += "\nPlease note that you are paying for the following customers: \n";
            
            for (AssociateCustomer cust : customer.GetAssociateCustomers())
            {
                text += "- " + cust.GetName() + "\n";
            }
        }
        
        text += "\n Thank you for your patronage.\n";
        text += "\nKind Regards,\nMagazine Service.";
        
        return text;
    }
}
