public abstract class Account {

	private int id;
	protected int freeTransactions;
	private Customer customer;
	private Ledger ledger;
	protected String accountType;
	protected double annualInterestRate, transactionFee, maintenanceFee;
	
	
	public Account(int id, Customer customer) {
		this.id = id;
		this.customer = customer;
		this.ledger = new Ledger();
	}
	
	public Account(int id, Customer customer, double startingBalance) {
		this.id = id;
		this.customer = customer;
		this.ledger = new Ledger();
		ledger.addTransaction(new Transaction(startingBalance, "Starting balance" ));
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public double getBalance() {
		double balance = 0;
		for(Transaction t: ledger.getTransactions()) {
			balance += t.getAmount();
		}
		return balance;
	}
	
	@Override
	public String toString() {
		
		return String.format("\n\t%2d\t\t%-30s\t\t\t$%.2f\t\t\t%-10s", getId(), getCustomer().getName(), getBalance(), getAccountType());

	}
	
	public abstract boolean canWithdraw(double amount);
	
	
	public void deposit(double amount) {
		ledger.addTransaction(new Transaction(amount, "Deposit"));
	}
	
	public void withdraw(double amount) {
		if(canWithdraw(amount)) {
			ledger.addTransaction(new Transaction(0-amount, "Withdrawal"));
		} else {
			amount = getBalance();
			ledger.addTransaction(new Transaction(0-amount, "Withdrawal"));
			System.out.printf("Insufficient funds! $%.2f withdrawn.\n", amount);
		}
	}

	public Ledger getLedger() {
		return ledger;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	public void accrueMonthlyInterest() {
		double monthlyInterestRate = annualInterestRate/12, monthlyInterest = getBalance() * monthlyInterestRate;
		if(annualInterestRate == 0 || getBalance() < 0) {
			System.out.printf("\nAccount #%d either does not earn interest or had a negative balance.", getId());
		} else {
			System.out.printf("\nAccount #%d earns %f%% annually. Accrued this month: $%.2f", getId(), annualInterestRate * 100, monthlyInterest);
			ledger.addTransaction(new Transaction(monthlyInterest, "Monthly Interest"));
		}
	}
	
	public int currentMonthTransactionCount() {
		int count = 0;
		for(Transaction t:ledger.getTransactions()) {
			if(t.isCurrentMonth()) {
				count +=1;
			}
		}
		return count;
	}
	
	public void applyTransactionFees() {
		if(freeTransactions == 0) {
			System.out.printf("\nAccount #%d does not charge transaction fees.", getId());
		} else if(currentMonthTransactionCount() <= freeTransactions){
			System.out.printf("\nAccount #%d gets %d free transactions per month. No fees applied.", getId(), freeTransactions);
		} else {
			System.out.printf("\nAccount #%d is charged $%.2f per transaction after the first %d transactions. $.2f fee applied.", getId(), transactionFee, freeTransactions, (currentMonthTransactionCount() - freeTransactions) * transactionFee);
			ledger.addTransaction(new Transaction((currentMonthTransactionCount() - freeTransactions) * transactionFee, "Transaction Fees"));
		}
		
	}
	
	public void applyMaintenanceFee() {
		if(maintenanceFee == 0) {
			System.out.printf("\nAccount #%d does not charge a maintenance fee.", getId());
		} else {
			System.out.printf("\nAccount #%d pays a $%.2f monthly maintenance fee", getId(), maintenanceFee);
			ledger.addTransaction(new Transaction(0-maintenanceFee, "Maintenance Fee"));
		}
	}
	
	public void monthEndProcess() {
		accrueMonthlyInterest();
		applyMaintenanceFee();
		applyTransactionFees();
		ledger.commitTransactions();
	}
	



}
