
INFO C210 Course Project: 
<h1>Simple Banking System</h1>

<h2>1. Introduction</h2>
You  are  asked  to  implement  a  simple  Bank  Management  System  for  an  unrealistic 
bank so that all information on customers and transactions are stored and accessed by 
bank  operators.  The  system  supports  interactive  inquiries  from  bank  staff  through  a 
friendly user interface.  You are required to create a Java application that implements 
the system. 
You can work with your group or individually to complete this project. 
Your project will be graded based on the correctness and efficiency of your 
implementation along with the documentation and readability of your code.  
  
##2. Specifications / System Overview  
You will write a Java application to simulate a simple (unrealistic) bank system. This bank supports three different kinds of accounts: Checking, Gold, and Regular. Information common to all account types include account number, balance, and customer information (customer ID, customer name, etc) of the customer who owns 
the account.
The Checking account is interest free and charges transaction fees. The first two monthly transactions are free. It charges a $3 fee for every extra transaction (deposit, withdrawal). The Gold account gives a fixed interest at 5% while the Regular account gives fixed interest at 6%. Regular accounts are charged $10 monthly as a maintenance fee. 
 
Whenever a withdrawal from a Regular or a Checking account is attempted and the given value is higher than the account's current balance, only the money currently available on the account is withdrawn. Unlike all other accounts, a Gold account holder can withdraw indefinitely. There is no transaction fee for customers with Gold or Regular accounts. 
 
The system is host-centered program currently supporting only one kind of user – a bank operator who accesses the system to perform regular system administrative work. The following tasks are usually performed by bank operators and must be implemented in your system: 
 
* Create a Checking account 
* Create a Gold account 
* Create a Regular account 
* Deposit a specified amount of money to a given account 
* Withdraw money from a given account 
* Display account information 
* Remove an account 
* Apply  end  of  month  account  updates.  This  function  should  be  used  once 
every end of month and will apply interest to the Regular and Gold accounts 
and deduct transaction fees from Checking accounts whenever applicable 
* Display  Bank  statistics:  this  feature  will  display  a  simple  report  for  bank 
administrators  that  include  things  like  the  total  sum  of  all  accounts  in  the 
bank,  number  of  zero-balance  accounts,  average  balance  of  accounts,  the 
account with largest balance, etc. 
* Exit: quits the system. 


##3. Design and Implementation  
You need to design and implement a working solution for this problem. The design will include documentation for each of the classes that are part of the application and the methods in each class. You need to document your design and implementation in a readme file. After completing your design, you will implement it. The number of classes you decide to use is up to you; however, your design must follow the good object oriented design practices and system restrictions. 
##4. Submission 
1. A UML diagram for the complete system. 
2. A working system that can be run from the command line. 
3. A Graphical User Interface (GUI) for the bank operator.  
4. A zip archive of all your Java files (only the *.java files, no .class files). 
5. A readme file that explains how your system works. 
