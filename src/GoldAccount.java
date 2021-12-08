
public class GoldAccount extends Account {
	
	public GoldAccount(int id, Customer customer, double startingBalance) {
		super(id, customer, startingBalance);
		this.accountType = "GOLD";
		this.annualInterestRate = .05;
		this.transactionFee = 0;
		this.freeTransactions = 0;
		this.maintenanceFee = 0;
	}
	
	@Override
	public boolean canWithdraw(double amount) {
		return true;
	}

}
