
public class RegularAccount extends Account {

	public RegularAccount(int id, Customer customer) {
		super(id, customer);
		this.accountType = "Regular";
		this.annualInterestRate = .06;
		this.transactionFee = 0;
		this.freeTransactions = 0;
		this.maintenanceFee = 10.;
	}
	
	public RegularAccount(int id, Customer customer, double startingBalance) {
		super(id, customer, startingBalance);
		this.accountType = "Regular";
		this.annualInterestRate = .06;
		this.transactionFee = 0;
		this.freeTransactions = 0;
		this.maintenanceFee = 10.;
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
