import java.util.ArrayList;

public class Ledger {

	private ArrayList<Transaction> transactions;
	
//	CONSTRUCTOR
	public Ledger() {
		transactions = new ArrayList<>();
	}
	
//	GETTERS
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
//	LEDGER ACTIONS
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	//used as part of end of month process
	public void commitTransactions() {
		for(Transaction t:transactions) {
			if (t.isCurrentMonth() == true) {
				t.setCurrentMonth(false);
			}
		}
	}
	
//	OVERRIDDEN METHODS
	@Override
	public String toString() {
		String ledgerString = "";
		for(Transaction t:transactions) {
			ledgerString += t.toString();
			ledgerString += "\n";
		}
		return ledgerString;
	}
}
