import java.util.ArrayList;

public class Ledger {

	private ArrayList<Transaction> transactions;
	
	public Ledger() {
		transactions = new ArrayList<>();
	}
	

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	public void commitTransactions() {
		for(Transaction t:transactions) {
			if (t.isCurrentMonth() == true) {
				t.setCurrentMonth(false);
			}
		}
	}
	
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
