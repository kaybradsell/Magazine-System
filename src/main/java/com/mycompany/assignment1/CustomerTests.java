package com.mycompany.assignment1;

import com.mycompany.assignment1.Customer.*;
import com.mycompany.assignment1.PaymentMethods.*;
import com.mycompany.assignment1.Magazine.*;
import com.mycompany.assignment1.PaymentMethods.PaymentMethodFactory.*;

public class CustomerTests
{
   
    public static void ExecuteTests()
    {
        TestAssociateCustomerConstructors();
        TestPayingCustomerConstructors();
        TestAddingSupplements();
        TestingInteractionWithAssociateAndPayingCustomers();
        TestGeneratingEmails();
        
        System.out.println("If you're seeing this at the end: YAYAYAYAY YOU PASSED :D");
    }
    
    public static void TestAssociateCustomerConstructors()
    {
        // first test, construct using basic constructors and assert the names and emails stick
        PrintTest(1);
        System.out.println("TEST: Checking basic constructor with valid details.");
        AssociateCustomer ass1 = new AssociateCustomer("Jane", "Jane@google.com");
        
        assert (ass1.GetName().equals("Jane"));
        assert (ass1.GetEmail().equals("Jane@google.com"));
        assert (ass1.GetSupplements().isEmpty());
        System.out.println("[PASS]\n");
        
        
        // next, test with invalid name (empty)
        try
        {
            System.out.println("TEST: Checking if invalid name causes Exception.");
            AssociateCustomer ass2 = new AssociateCustomer(" ", "Jane@google.com");
        }
        catch(Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with invalid email (empty)
        try
        {
            System.out.println("TEST: Checking if invalid email causes Exception.");
            AssociateCustomer ass2 = new AssociateCustomer("Jane", " ");
        }
        catch(Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with invalid email (no @)
        try
        {
            System.out.println("TEST: Checking if invalid email causes Exception: 2.");
            AssociateCustomer ass2 = new AssociateCustomer("Jane", "Janegoogle.com");
        }
        catch (Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with invalid email (no .)
        try
        {
            System.out.println("TEST: Checking if invalid email causes Exception: 3.");
            AssociateCustomer ass2 = new AssociateCustomer("Jane", "Jane@googlecom");
        }
        catch (Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with both invalid
        try
        {
            System.out.println("TEST: Checking if both invalid values causes Exception");
            AssociateCustomer ass2 = new AssociateCustomer(" ", " ");
        }
        catch (Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        System.out.println("TEST: Checking copy constructor if it makes deep copy.");
        AssociateCustomer ass2 = new AssociateCustomer(ass1);
        ass1.SetName("Mary");
        ass1.SetEmail("Mary@hotmail.com");
        
        assert(!ass2.IsSame(ass1));
        assert(!ass1.GetName().equals(ass2.GetName()));
        assert(!ass1.GetEmail().equals(ass2.GetEmail()));
        
        System.out.println("[PASS]\n");
        
        
        System.out.println("TEST: Checking copy constructor with PaymentCustomer.");
        PayingCustomer pay1 = new PayingCustomer("John", "John@aol.com", PaymentMethodFactory.Create(PaymentType.CREDIT, "John's Credit Account"));
        AssociateCustomer ass3 = new AssociateCustomer(pay1);
        pay1.SetName("Jonathan");
        pay1.SetEmail("Jonathan@aol.com");
        
        assert(!ass3.IsSame(pay1));
        assert(!pay1.GetName().equals(ass3.GetName()));
        assert(!pay1.GetEmail().equals(ass3.GetEmail()));
        
        System.out.println("[PASS]\n");
        
        
        // check that two customers cannot have the same details (email)
        System.out.println("TEST: Checking that two customers with same details match.");
        AssociateCustomer ass4 = new AssociateCustomer("John", "John@john.john");
        AssociateCustomer ass5 = new AssociateCustomer("Jahn", "John@john.john");
        
        assert(ass4.IsSame(ass5));
        
        System.out.println("[PASS]\n");
    }
    
    public static void TestPayingCustomerConstructors()
    {
        // first test, construct using basic constructors and assert the names and emails stick
        PrintTest(2);
        System.out.println("TEST: Checking basic constructor with valid details.");
        PayingCustomer ass1 = new PayingCustomer("Jane", "Jane@google.com", PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        
        assert (ass1.GetName().equals("Jane"));
        assert (ass1.GetEmail().equals("Jane@google.com"));
        assert (ass1.GetSupplements().isEmpty());
        assert (ass1.GetAssociateCustomers().isEmpty());
        assert (ass1.GetPaymentMethod().GetAccountName().equals("Jane's Credit Account"));
        
        System.out.println("[PASS]\n");
        
        
        // next, test with invalid name (empty)
        try
        {
            System.out.println("TEST: Checking if invalid name causes Exception.");
            PayingCustomer ass2 = new PayingCustomer(" ", "Jane@google.com", PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        }
        catch(Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with invalid email (empty)
        try
        {
            System.out.println("TEST: Checking if invalid email causes Exception.");
            PayingCustomer ass2 = new PayingCustomer("Jane", " ", PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        }
        catch(Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with invalid email (no @)
        try
        {
            System.out.println("TEST: Checking if invalid email causes Exception: 2.");
            PayingCustomer ass2 = new PayingCustomer("Jane", "Janegoogle.com", PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        }
        catch (Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with invalid email (no .)
        try
        {
            System.out.println("TEST: Checking if invalid email causes Exception: 3.");
           PayingCustomer ass2 = new PayingCustomer("Jane", "Jane@googlecom", PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        }
        catch (Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        // test with both invalid
        try
        {
            System.out.println("TEST: Checking if both invalid values causes Exception");
            PayingCustomer ass2 = new PayingCustomer(" ", " ", PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        }
        catch (Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        System.out.println("[PASS]\n");
        
        
        System.out.println("TEST: Checking copy constructor if it makes deep copy.");
        PayingCustomer ass2 = new PayingCustomer(ass1, PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        ass1.SetName("Mary");
        ass1.SetEmail("Mary@hotmail.com");
        ass1.SetPaymentMethod(PaymentMethodFactory.Create(PaymentType.DEBIT, "Mary's cool Debit Account"));
        
        assert(!ass2.IsSame(ass1));
        assert(!ass1.GetName().equals(ass2.GetName()));
        assert(!ass1.GetEmail().equals(ass2.GetEmail()));
        assert(!ass1.GetPaymentMethod().GetAccountName().equals(ass2.GetPaymentMethod().GetAccountName()));
        
        System.out.println("[PASS]\n");
        
        
        System.out.println("TEST: Checking copy constructor with AssociateCustomer.");
        AssociateCustomer ass3 = new AssociateCustomer("Chicken", "Chicken@coop.com");
        PayingCustomer pay1 = new PayingCustomer(ass3, PaymentMethodFactory.Create(PaymentType.CREDIT, "Jane's Credit Account"));
        ass3.SetName("Mary");
        ass3.SetEmail("Mary@hotmail.com");
        
        assert(!ass3.IsSame(pay1));
        assert(!pay1.GetName().equals(ass3.GetName()));
        assert(!pay1.GetEmail().equals(ass3.GetEmail()));
        
        System.out.println("[PASS]\n");
    }
    
    public static void TestAddingSupplements()
    {
        PrintTest(3);
        
        // we'll test with 2 supplements.
        Supplement supp1 = new Supplement("Kuro no Kiseki I", 10.00);
        Supplement supp2 = new Supplement("Kuro no Kiseki II: CRIMSON SiN", 20.00);
        
        
        System.out.println("TEST: Adding a supplement to a customer.");
        
        AssociateCustomer customer = new AssociateCustomer("Jane", "Jane@google.com");
        customer.AddSupplement(supp2);
        
        assert(!customer.GetSupplements().isEmpty());
        assert(supp2.IsSame(customer.GetSupplements().get(0)));
        
        System.out.println("[PASS]\n");
        
        
        // testing if changing cost of supplement affects the supplement held by customer
        System.out.println("TEST: Checking if changing supplement details reflects in customer's List.");
        
        supp2.SetCost(25.00); // oh no! Inflation!
        
        assert(customer.GetSupplements().get(0).GetCost() == supp2.GetCost());

        System.out.println("[PASS]\n");
        
        
        // testing if removing supplement from customer deletes supplement
        System.out.println("TEST: Checking if removing supplement from customer deletes supplement.");

        customer.RemoveSupplement(supp2);
        
        assert(customer.GetSupplements().isEmpty());
        assert(supp2.GetName().equals("Kuro no Kiseki II: CRIMSON SiN"));
        assert(supp2.GetCost() == 25.00);
        
        System.out.println("[PASS]\n");
        
        
        // checking that supplements have deep copy constructor
        System.out.println("TEST: Checking supplement copy constructor makes deep copy.");

        Supplement supp3 = new Supplement(supp2);
        supp3.SetName("Kai no Kiseki");
        supp3.SetCost(30.00);
        
        assert(!supp3.GetName().equals(supp2.GetName()));
        assert(supp3.GetCost() != supp2.GetCost());
        
        System.out.println("[PASS]\n");
        
        
        // checking that copying customers also copies over supplements
        System.out.println("TEST: Checking that copying customers copies over supplements.");

        customer.AddSupplement(supp1);
        customer.AddSupplement(supp2);
        
        AssociateCustomer customer2 = new AssociateCustomer(customer);
        
        assert(customer.GetSupplements().size() == customer2.GetSupplements().size());
        assert(customer.GetSupplements().get(0).IsSame(customer2.GetSupplements().get(0)));
        assert(customer.GetSupplements().get(1).IsSame(customer2.GetSupplements().get(1)));
        
        System.out.println("[PASS]\n");
        
        
        // checking that removing supplement from one copy doesn't affect other
        System.out.println("TEST: Checking that removing supplement from one copy doesn't affect the other.");
        
        customer.RemoveSupplement(supp1);
        
        assert(customer.GetSupplements().size() != customer2.GetSupplements().size());
        
        System.out.println("[PASS]\n");
        
        
        // checking you can't add two of a supplement to a customer
        System.out.println("TEST: Checking that you can't add two of a supplement to a customer.");

        // customer has supp2 and that's it
        customer.AddSupplement(supp2);
        customer.AddSupplement(supp2);
        
        assert(customer.GetSupplements().size() == 1);
        
        System.out.println("[PASS]\n");
    }
    
    public static void TestingInteractionWithAssociateAndPayingCustomers()
    {
        PrintTest(4);
        
        PayingCustomer pay = new PayingCustomer("Van", "VanArkride@ArkrideSolutions.orb", PaymentMethodFactory.Create(PaymentType.CREDIT, "Van's super cool credit account that he has 0 debt in."));
        AssociateCustomer ass = new AssociateCustomer("Agnes", "AgnesClaudel@ArkrideSolutions.orb");
        AssociateCustomer ass2 = new AssociateCustomer("Ferida", "FeridaAlFayed@Kruga.jaeger");
        
        // check if paying customer can link to associate customer
        System.out.println("TEST: Checking if a paying customer can link to an associate customer.");
        
        pay.AddAssociateCustomer(ass);
        
        assert(pay.ContainsAssociateCustomer(ass));
        assert(ass.GetPayingCustomer().IsSame(pay));
        
        System.out.println("[PASS]\n");
        
        // check that having an associate customer add a paying customer throws exception
        System.out.println("TEST: Checking that having an associate customer add a paying customer throws exception.");
        
        try
        {
            ass2.SetPayingCustomer(pay);
        }
        catch (Exception e)
        {
            System.out.println(">>> CAUGHT EXCEPTION: " + e.getMessage());
        }
        
        System.out.println("[PASS]\n");
        
        // check that you can add a second associate customer
        System.out.println("TEST: Checking that you can add a second associate customer.");
        
        pay.AddAssociateCustomer(ass2);
        
        assert (pay.GetAssociateCustomers().size() == 2);
        assert (pay.ContainsAssociateCustomer(ass));
        assert (pay.ContainsAssociateCustomer(ass2));
        
        System.out.println("[PASS]\n");
        
        // check that you can change paying customer for an associate customer
        System.out.println("TEST: Checking that you can swap paying customers for an associate customer.");
        
        PayingCustomer pay2 = new PayingCustomer("Risette", "RisetteTwinings@Marduk.co", PaymentMethodFactory.Create(PaymentType.DEBIT, "Risette's debit account because she's hip with the times"));
        pay2.AddAssociateCustomer(ass2);
        
        assert (pay.GetAssociateCustomers().size() == 1);
        assert (!pay.ContainsAssociateCustomer(ass2));
        assert (pay2.GetAssociateCustomers().size() == 1);
        assert (pay2.ContainsAssociateCustomer(ass2));
        
        System.out.println("[PASS]\n");
        
        // check that you can safely delete an associate customer
        System.out.println("TEST: Checking that you can safely delete an associate customer.");
        
        ass2.close();
        
        assert (pay2.GetAssociateCustomers().isEmpty());
        assert (!pay2.ContainsAssociateCustomer(ass2));
        
        System.out.println("[PASS]\n");
        
        // check that you canNOT safely delete a paying customer
        System.out.println("TEST: Checking that you canNOT safely delete a paying customer if they have associate customers.");
        
        try
        {
            pay.close();
        }
        catch (Exception e)
        {
            System.out.println(">>> EXCEPTION CAUGHT: " + e.getMessage());
        }
        
        System.out.println("[PASS]\n");
        
        
        // check that you can safely delete a paying customer IF THEY DON'T HAVE ASSOCIATE CUSTOMERS
        System.out.println("TEST: Checking that you can safely delete a paying customer if they DO NOT have associate customers.");
        
        // pay2 doesn't have ass2, so safe to close
        pay2.close();
        
        // no checks cuz otherwise it'll just throw an exception.
        
        System.out.println("[PASS]\n");
        
        
        // check that you can't have duplicate associate customers
        System.out.println("TEST: Checking that you can't have duplicate associate customers.");
        
        pay.AddAssociateCustomer(ass);
        
        assert (pay.GetAssociateCustomers().size() == 1);
        
        System.out.println("[PASS]\n");
    }
    
    public static void TestGeneratingEmails()
    {
        PrintTest(5);
        System.out.println("The next few tests are visual based. Please confirm their contents.");
        
        PayingCustomer pay = new PayingCustomer("Van", "VanArkride@ArkrideSolutions.orb", PaymentMethodFactory.Create(PaymentType.CREDIT, "Van's super cool credit account that he has 0 debt in."));
        PayingCustomer pay2 = new PayingCustomer("Risette", "RisetteTwinings@Marduk.co", PaymentMethodFactory.Create(PaymentType.DEBIT, "Risette's Debit Account because she's hip with the time."));
        AssociateCustomer ass = new AssociateCustomer("Agnes", "AgnesClaudel@ArkrideSolutions.orb");
        AssociateCustomer ass2 = new AssociateCustomer("Ferida", "FeridaAlFayed@Kruga.jaegercorps");
        AssociateCustomer ass3 = new AssociateCustomer("Aaron", "AaronWei@Heiyue.co");
        
        Magazine mag = new Magazine("The Legend of Heroes: Trails through Daybreak", 50.00);
        Supplement supp1 = new Supplement("Kuro no Kiseki", 10.00);
        Supplement supp2 = new Supplement("Kuro no Kiseki II: CRIMSON SiN", 20.00);
        Supplement supp3 = new Supplement("Kai no Kiseki", 25.00);
        
        pay.AddSupplement(supp1);
        pay.AddSupplement(supp2);
        pay.AddSupplement(supp3);
        
        ass.AddSupplement(supp2);
        
        ass2.AddSupplement(supp2);
        ass2.AddSupplement(supp3);
        
        ass3.AddSupplement(supp1);
        ass3.AddSupplement(supp2);
        
        pay.AddAssociateCustomer(ass);
        pay.AddAssociateCustomer(ass2);
        pay.AddAssociateCustomer(ass3);
        
        System.out.println("TEST: Testing weekly email for all customers.");

        // this should really have been an array >:(
        System.out.println("\n-----------------\nSending to " + pay.GetEmail());
        System.out.println(CustomerView.GenerateWeeklyEmailString(pay, mag));
        System.out.println("\nSending to " + pay2.GetEmail());
        System.out.println(CustomerView.GenerateWeeklyEmailString(pay2, mag));
        System.out.println("\nSending to " + ass.GetEmail());
        System.out.println(CustomerView.GenerateWeeklyEmailString(ass, mag));
        System.out.println("\nSending to " + ass2.GetEmail());
        System.out.println(CustomerView.GenerateWeeklyEmailString(ass2, mag));
        System.out.println("\nSending to " + ass3.GetEmail());
        System.out.println(CustomerView.GenerateWeeklyEmailString(ass3, mag));
        
        
        // 
        System.out.println("TEST: Testing monthly email for paying customers.");
        
        System.out.println("\n-----------------\nSending to " + pay.GetEmail());
        System.out.println(CustomerView.GenerateMonthlyEmailString(pay, mag));
        System.out.println("\nSending to " + pay2.GetEmail());
        System.out.println(CustomerView.GenerateMonthlyEmailString(pay2, mag));
        
        // 
        System.out.println("TEST: .");

        
        System.out.println("[PASS]\n");
    }
    
    public static void PrintTest(int num)
    {
        System.out.println("\n------------------------------------------------------------------------\n       Test " + num + " \n------------------------------------------------------------------------");
    }
}

