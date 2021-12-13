import java.util.Date;

public class Transaction {
	private Date date;
	private double amount;
	private boolean currentMonth;
	private String memo;
	
//	CONSTRUCTOR
	public Transaction(double amount, String memo) {
		this.date = new Date();
		this.amount = amount;
		this.currentMonth = true;
		this.memo = memo;
	}
	
//	GETTERS
	public Date getDate() {
		return date;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public boolean isCurrentMonth() {
		return currentMonth;
	}
	
	public String getMemo() {
		return memo;
	}
	
// 	SETTERS
	public void setDate(Date date) {
		this.date = date;
	}
		
	public void setAmount(double amount) {
		this.amount = amount;
	}	
	
	public void setCurrentMonth(boolean currentMonth) {
		this.currentMonth = currentMonth;
	}
	
//	OVERRIDDEN METHODS
	@Override
	public String toString() {
		return "\t- " + date + "\t\t" + String.format("$%.2f",amount) + "\t\t" + memo;
	}
}
