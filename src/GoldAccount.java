public class GoldAccount extends Account {
	
	public GoldAccount(int id, Customer customer, double startingBalance) {
		super(id, customer, startingBalance);
		this.accountType = "Gold";
		this.annualInterestRate = .05;
		this.transactionFee = 0;
		this.freeTransactions = 0;
		this.maintenanceFee = 0;
	}
	
	@Override
	//gold accounts can always withdraw, regardless of balance
	public boolean canWithdraw(double amount) {
		return true;
	}
}
