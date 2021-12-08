
public class CheckingAccount extends Account {

	public CheckingAccount(int id, Customer customer) {
		super(id, customer);
		this.accountType = "Checking";
		this.annualInterestRate = 0;
		this.transactionFee = 3.;
		this.freeTransactions = 2;
		this.maintenanceFee = 0;
	}
	
	public CheckingAccount(int id, Customer customer, double startingBalance) {
		super(id, customer, startingBalance);
		this.accountType = "Checking";
		this.annualInterestRate = 0;
		this.transactionFee = 3.;
		this.freeTransactions = 2;
		this.maintenanceFee = 0;
	}
	
	@Override
	public boolean canWithdraw(double amount) {
		if(getBalance() >= amount) {
			return true;
		} else {
			return false;
		}

	}

}
