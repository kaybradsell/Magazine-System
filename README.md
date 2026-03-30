# Weekly Magazine System Backend

This is an assignment for ICT373 Software Architectures.

## Requirements/Specifications
The class requirements for this task are as follows:
- A customer class, storing a name, email address, and list of Supplements.
- A paying customer class, a customer that pays for themselves and other customers
- An associate customer class, a customer that has their goods paid for.
- A payment method class, storing the details for the payment method, that being credit or debit.
- Supplement class, that stores the name and cost of that supplement
- Magazine class, that stores its name and cost of itself.
On top of this, the main program for this task needed to be able to construct lists of supplements and customers, fill them in with hard-coded data, print out both weekly and monthly emails, add a customer, and remove a customer.

Some assumptions were made in the creation of this program:
- Emails are only considered valid if they include an @ and a ., meaning any emails inputted not in that format will not be accepted.
- Customers can switch between PayingCustomer and AssociateCustomer, although it does not save any of their payment details, nor AssociateCustomers
- An AssociateCustomer cannot exist without a PayingCustomer, so a PayingCustomer cannot be deleted unless all of the Associate Customers attached to it have a different PayingCustomer.
- Magazines and Supplements do not share any functionality between the two.
- Credit and Debit PaymentMethods store completely different data and hence do not share any functionality aside from being PaymentMethods.
- Supplements and Magazine charge per month. So you pay per month and you get the supplement and magazine for all weeks in that month.

## Structure/Design
### Customer
For the customers, I chose to use inheritance.

As both paying and associate customers share a lot of the same data types and functionality, that being the Name, Email, Supplements they are subscribed to, and both must be used in the emails sent, having a superclass Customer would store those data, and the subclasses AssociateCustomer and PayingCustomer would store those specifics.

For the Supplements stored by a Customer, they are stored by reference, not copied. This way, any changes made to a Supplement, for example a name change or price change, it will be reflected to all references to that Supplement. This way, Customer does not own Supplements, and removing a Supplement from a Customer will not delete that Supplement entirely.

As a PayingCustomer can pay for none/multiple AssociateCustomers, PayingCustomers would store a reference to the AssociateCustomer, and an AssociateCustomer will store a reference to that PayingCustomer. However, only PayingCustomers should be able to control what AssociateCustomers to add/remove. Much like Supplements, PayingCustomers store references, not copies. On top of this, to ensure a PayingCustomer cannot be deleted and leave all of their AssociateCustomers without a PayingCustomer, they cannot delete their account/data until all AssociateCustomers have been moved.

Relationship between Associate to Paying is aggregation. Paying can have 0 to many Associates, Associate can only have 1 Paying. Associate also stores reference to Paying, but it’s only 1.

<img width="858" height="428" alt="UML of Customer" src="https://kaybradsell.github.io/data/imgs/MagazineCustomerUML.png" />

### Payment
For payment methods, the same as customers I used inheritance. I also used a factory pattern for handling which payment method to create during runtime.

For the superclass PaymentMethod, it should store the accountName, validating the accountName. The subclasses Credit and Debit would store their own data. They do not store any as of yet, but are open for extension. The PaymentMethodFactory will allow for different types of PaymentMethods, like paypal or bitcoin, by adding an enum to refer to the type, and adding a case to the switch-case for Create(). This way, instead of every time you add or change a payment method in the client program, you can call Create(type, accountName) and it will return that subclass instead.

Both Credit and Debit inherit from abstract PaymentMethod.

PaymentMethodFactory depends on PaymentType, Credit, Debit, and PaymentMethod.

<img width="858" height="428" alt="UML of Customer" src="https://kaybradsell.github.io/data/imgs/MagazinePaymentUML.png" />

### Full
Finally, Supplement and Magazines were designed as separate classes. While they both store the same data and hence same validating data helpers, they were kept separate because their purposes are different. A Supplement could contain wildly different data to a Magazine, and they shouldn’t be inheriting from a superclass because they should not be interchangeable via superclass.

<img width="858" height="2580" alt="UML of Customer" src="https://kaybradsell.github.io/data/imgs/MagazineFullUML.png" />


## Limitations
For limitations of the program:
-	The program can only read from CLI and output via CLI.
-	Adding Customer or Removing Customer immediately aborts back to main menu if not allowed or user inputs any bad inputs.

Aside from that, the program does what is needed from the specifications.
