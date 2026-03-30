package com.mycompany.assignment1;

import com.mycompany.assignment1.Customer.*;
import com.mycompany.assignment1.Magazine.*;
import com.mycompany.assignment1.PaymentMethods.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Client IO
 * Handles modifying the data in list of Customers or
 * filling lists with hard coded data.
 * 
 * @author Kay Bradsell (35110401)
 * @version 1.0 20/03/2026
 */
public class DataModifier
{
    /**
     * Fill Supplements
     * Returns a list of hard coded supplements.
     * 
     * @return List of Supplement
     * 
     * @precondition  none
     * @postcondition returns list of predefined supplements.
     */
    public static List<Supplement> FillSupplementData()
    {
        List<Supplement> supps = new ArrayList<>();
        supps.add(new Supplement("Sora no Kiseki", 10.00));
        supps.add(new Supplement("Ao no Kiseki", 12.00));
        supps.add(new Supplement("Sen no Kiseki", 15.00));
        supps.add(new Supplement("Kuro no Kiseki", 18.50));
        
        return supps;
    }
    
    /**
     * Fill Customer Data
     * Returns a list of hard coded customers
     * 
     * @param supps List of Supplement
     * @return List of Customer
     * 
     * @precondition  Valid list of supplements (must be from FillSupplementData)
     * @postcondition returns list of predefined Customers.
     */
    public static List<Customer> FillCustomerData(List<Supplement> supps)
    {
        List<Customer> custs = new ArrayList<>();
        PayingCustomer rean = new PayingCustomer("Rean Schwarzer", "ReanSchwarzer@ymir.com", PaymentMethodFactory.Create(PaymentMethodFactory.PaymentType.CREDIT, "Rean's cool bank account"));
        PayingCustomer musse = new PayingCustomer("Musse Egret", "MusseEgret@lamarre.com", PaymentMethodFactory.Create(PaymentMethodFactory.PaymentType.DEBIT, "Musse's debit account"));
        AssociateCustomer juna = new AssociateCustomer("Juna Crawford", "JunaCrawford@crossbell.com");
        AssociateCustomer kurt = new AssociateCustomer("Kurt Vander", "KurtVander@heimdallr.com");
        AssociateCustomer altina = new AssociateCustomer("Altina Orion", "AltinaOrion@gnome.com");
        AssociateCustomer ash = new AssociateCustomer("Ash Carbide", "AshCarbide@hamel.com");
        
        // rean pays for everyone's excepts for musse.
        rean.AddAssociateCustomer(juna);
        rean.AddAssociateCustomer(kurt);
        rean.AddAssociateCustomer(altina);
        rean.AddAssociateCustomer(ash);
        
        // setup everyones supplements
        rean.AddSupplement(supps.get(2));
        rean.AddSupplement(supps.get(3));
        
        juna.AddSupplement(supps.get(1));
        juna.AddSupplement(supps.get(2));
        
        kurt.AddSupplement(supps.get(2));
        
        altina.AddSupplement(supps.get(2));
        altina.AddSupplement(supps.get(3));
        
        ash.AddSupplement(supps.get(0));
        ash.AddSupplement(supps.get(2));
        
        custs.add(rean);
        custs.add(musse);
        custs.add(juna);
        custs.add(kurt);
        custs.add(altina);
        custs.add(ash);
        
        return custs;
    }
    
    /**
     * Create Customer
     * Takes user input through a series of questions and adds a customer to list, else aborts.
     * 
     * @param in Scanner
     * @param customers List of Customer
     * @param supplements List of Supplement
     * 
     * @precondition  both customers and supplements are valid and filled.
     * @postcondition adds a customer entry to the list, else aborts.
     */
    public static void CreateCustomer(Scanner in, List<Customer> customers, List<Supplement> supplements)
    {
        System.out.println("Enter name: ");
        String name = CustomerInputHandler.GetValidName(in);
        String email = CustomerInputHandler.GetValidEmail(in);        
        
        List<Supplement> supps = CustomerInputHandler.GetUserSupplements(in, supplements);
        System.out.println("Input Customer type:\n (1) Paying\n (2) Associate");
        if (ClientIO.GetBooleanInput(in)) // paying
        {
            System.out.println("Input Payment Method type:\n (1) Credit\n (2) Debit");
            PaymentMethodFactory.PaymentType type = (ClientIO.GetBooleanInput(in)) ? PaymentMethodFactory.PaymentType.CREDIT : PaymentMethodFactory.PaymentType.DEBIT;

            System.out.println("Enter account name: ");
            String accountName = CustomerInputHandler.GetValidName(in);
            
            PayingCustomer p = CreatePayingCustomer(name, email, supps, type, accountName);
            if (p == null)
            {
                System.out.println("Aborting...");
                return;
            }
            
            if (InputValidator.DoesCustomerAlreadyExist(customers, p))
            {
                System.out.println("Customer already exists. Aborting...");
                return;
            }
            customers.add(p);
        }
        else // associate
        {
            PayingCustomer payer = CustomerInputHandler.GetUserPayingCustomer(in, customers);
            AssociateCustomer c = CreateAssociateCustomer(name, email, supps);
            if (c == null || payer == null)
            {
                System.out.println("Aborting...");
                return;
            }
            payer.AddAssociateCustomer(c);
            
            if (InputValidator.DoesCustomerAlreadyExist(customers, c))
            {
                System.out.println("Customer already exists. Aborting...");
                return;
            }
            customers.add(c);
        }
    }
    
    /**
     * Remove Customer
     * Takes user input on a customer to remove, and removes them from the list. Unless it aborts.
     * 
     * @param in Scanner
     * @param customers List of Customer
     * 
     * @precondition  valid list of customers that is filled.
     * @postcondition deletes the user chosen customer. Unless it aborts.
     */
    public static void RemoveCustomer(Scanner in, List<Customer> customers)
    {
        if (customers.isEmpty())
        {
            System.out.println("No customers to delete.");
            return;
        }
        
        Customer custToRemove = CustomerInputHandler.GetUserCustomer(in, customers);
        
        try {
            custToRemove.close();
            customers.remove(custToRemove);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
    
    /**
     * Create Paying Customer
     * Creates a PayingCustomer object and returns it given parameters.
     * 
     * @param name String
     * @param email String
     * @param supps List of Supplement
     * @param type PaymentType
     * @param accountName String
     * @return PayingCustomer
     * 
     * @precondition  all parameters are valid and non-null.
     * @postcondition creates and returns a PayingCustomer object. Unless it fails, then it returns null.
     */
    private static PayingCustomer CreatePayingCustomer(String name, String email, List<Supplement> supps, PaymentMethodFactory.PaymentType type, String accountName)
    {
        try {
            return new PayingCustomer(name, email, supps, PaymentMethodFactory.Create(type, accountName));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Create Associate Customer
     * Creates an AssociateCustomer object and returns it given parameters.
     * 
     * @param name String
     * @param email String
     * @param supps List of Supplement
     * @return AssociateCustomer
     * 
     * @precondition  all parameters are valid.
     * @postcondition creates and returns a PayingCustomer object. Unless it fails, then it returns null.
     */
    private static AssociateCustomer CreateAssociateCustomer(String name, String email, List<Supplement> supps)
    {
        try {
            return new AssociateCustomer(name, email, supps);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
