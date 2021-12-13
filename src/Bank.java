import java.util.ArrayList;
import java.util.Iterator;

public class Bank {
	private ArrayList<Account> accounts;
	private int nextAcctID = 1, nextCustID = 1;
	
// 	CONSTRUCTOR
	public Bank() {
		accounts = new ArrayList<>();
		
		// CREATE SOME INITIAL ACCOUNTS
		accounts.add(new RegularAccount(getNextAcctID(), new Customer(nextCustID, "Fante, Matthew"), 150));
		accounts.add(new GoldAccount(getNextAcctID(), new Customer(nextCustID, "Skywalker, Luke"), 275));
		accounts.add(new CheckingAccount(getNextAcctID(), new Customer(nextCustID, "Smith, John"), 3.5));
		accounts.add(new GoldAccount(getNextAcctID(), new Customer(nextCustID, "White, Walter"), 1000));
		accounts.add(new CheckingAccount(getNextAcctID(), new Customer(nextCustID, "Morgan, Dexter"), 0));
		accounts.add(new RegularAccount(getNextAcctID(), new Customer(nextCustID, "Mouse, Mickey"), 99));
		accounts.add(new GoldAccount(getNextAcctID(), new Customer(nextCustID, "Garten, Ina"), 1));
		accounts.add(new CheckingAccount(getNextAcctID(), new Customer(nextCustID, "Wayne, Tater"), 94));
		accounts.add(new RegularAccount(getNextAcctID(), new Customer(nextCustID, "Petunia, Aunt"), 15));
		accounts.add(new GoldAccount(getNextAcctID(), new Customer(nextCustID, "Lovato, Poot"), -15));
	}
	
//	GETTERS
	public int getNextAcctID() {
		int acctID = nextAcctID;
		nextAcctID += 1;
		return acctID;
	}

	public int getNextCustID() {
		int custID = nextCustID;
		nextCustID += 1;
		return custID;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
//	BANK ACTIONS
	public void addAccount(Account a) {
		accounts.add(a);
	}
		
	public void removeAccount(int Id) {
		Iterator<Account> iter = accounts.iterator();
		while(iter.hasNext()) {
			if(iter.next().getId() == Id) {
				iter.remove();
			}
		}
	}
	
	public boolean accountExists(int id) {
		for(Account a:accounts) {
			if(a.getId() == id) {
				System.out.println("Account found!");
				return true;
			}
		}
		System.out.println("Account not found!");
		return false;
	}
	
	public String accountSummary() {
		String summary = "\t=============== ACCOUNT INFORMATION ===============\n";
		for(Account a: getAccounts()) {
			summary += a.toString();
			summary += '\n';
			summary += a.getLedger().toString();
		}
		return summary;
	}

//	BANK STATISTICS
	public double totalBalance() {
		double balance = 0;
		for(Account a: accounts) {
			balance += a.getBalance();
		}
		return balance;
	}
	
	public double avgBalance() {
		return totalBalance()/numAccounts();
	}
	
	public int numAccounts() {
		return accounts.size();
	}
	
	public int numZeroBalAccts() {
		int zeroBalAccts = 0;
		for(Account a:accounts) {
			if(a.getBalance() == 0) {
				zeroBalAccts += 1;
			}
		}
		return zeroBalAccts;
	}
	
	public double largestBalance() {
		double largestBalance = 0;
		for(Account a: accounts) {
			if(a.getBalance() > largestBalance) {
				largestBalance = a.getBalance();
			}
		}
		return largestBalance;
	}

// 	OVERRIDDEN METHODS
	@Override
	public String toString() {
		String listOfAccounts ="";
		for(Account a:accounts) {
			listOfAccounts += a.toString();
		}
		return listOfAccounts;
	}
}
