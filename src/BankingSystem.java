import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingSystem extends Application{
	BorderPane mainPane = new BorderPane(); 
	TextArea taOutput = new TextArea();

	VBox accordian = new VBox();
	TitledPane addAccountPane = new TitledPane();
	GridPane addAccountGrid = new GridPane();
	TextField tfAddAccount_CustomerFirstName = new TextField();
	TextField tfAddAccount_CustomerLastName = new TextField();	
	ToggleGroup tgAddAccount_Type = new ToggleGroup();
	RadioButton addRegAcct = new RadioButton("Regular");
	RadioButton addChkAcct = new RadioButton("Checking");
	RadioButton addGoldAcct = new RadioButton("Gold");
	Button addAcctBtn = new Button("Add Account");

	TitledPane removeAccountPane = new TitledPane();
	GridPane removeAccountGrid = new GridPane();
	TextField tfRemoveAccount_AccountID = new TextField();
	Button removeAcctBtn = new Button("Remove Account");
	
	TitledPane performTransactionPane = new TitledPane();
	GridPane performTransactionGrid = new GridPane();
	TextField tfTransactionID = new TextField();
	TextField tfTransactionAmount = new TextField("");
	Button depositButton = new Button("Deposit");
	Button withdrawButton = new Button("Withdraw");
	Label lblTransactionStatus = new Label();
	Label lblRemoveAcctStatus = new Label();
	Label lblAddAcctStatus = new Label();
	VBox transactionVBox = new VBox();
	VBox removeAcctVBox = new VBox();
	VBox addAcctVBox = new VBox();
	
	TextField tfTotalBal = new TextField();
	TextField tfAvgBal = new TextField();
	TextField tfNumAccts = new TextField();
	TextField tfLargestBal = new TextField();
	TextField tfNumZeroBalAccts = new TextField();
	
	Button monthEndBtn = new Button("Monthly Process");
	
	TitledPane adminPane = new TitledPane();
	GridPane adminGrid = new GridPane();
	TitledPane bankStatsPane = new TitledPane();
	GridPane bankStatsGrid = new GridPane();
	

	Bank bank = new Bank();
	int activeAccount = 0;
	
	public void start(Stage primaryStage) {
//		primaryStage.setResizable(false);x

		// ADD ACCOUNT 
		addAcctVBox.getChildren().addAll(addAccountGrid,lblAddAcctStatus);
		addAccountPane.setContent(addAcctVBox);
		addAccountPane.setText("Add Account");
		addAccountPane.setExpanded(false);
		
		addAccountGrid.add(new Label("Customer Name:  "), 0, 0);
		addAccountGrid.add(tfAddAccount_CustomerLastName, 1, 0);
		addAccountGrid.add(tfAddAccount_CustomerFirstName, 2, 0);
		addAccountGrid.add(new Label("Account Type:  "), 0, 1);
		addAccountGrid.add(addRegAcct, 1, 1);
		addAccountGrid.add(addChkAcct, 1, 2);
		addAccountGrid.add(addGoldAcct, 1, 3);
		addAccountGrid.add(addAcctBtn, 0, 4);
		
		addRegAcct.setToggleGroup(tgAddAccount_Type);
		addChkAcct.setToggleGroup(tgAddAccount_Type);
		addGoldAcct.setToggleGroup(tgAddAccount_Type);
		
		addRegAcct.setSelected(true);
		tfAddAccount_CustomerLastName.setPromptText("Last");
		tfAddAccount_CustomerFirstName.setPromptText("First");
		addAcctBtn.setOnAction(e -> addAccount());

		//REMOVE ACCOUNT
		removeAcctVBox.getChildren().addAll(removeAccountGrid, lblRemoveAcctStatus);
		removeAccountPane.setContent(removeAcctVBox);
		removeAccountPane.setText("Remove Account");
		removeAccountPane.setExpanded(false);
		removeAccountGrid.add(new Label("Account ID: "), 0,0);
		removeAccountGrid.add(tfRemoveAccount_AccountID, 1, 0);
		removeAccountGrid.add(removeAcctBtn, 0, 2);
		
		removeAcctBtn.setOnAction(e -> removeAccount());
		
		//TRANSACTIONS
		transactionVBox.getChildren().addAll(performTransactionGrid, lblTransactionStatus);
		performTransactionPane.setContent(transactionVBox);
		performTransactionPane.setText("Perform Transactions");
		performTransactionPane.setExpanded(false);
		
		performTransactionGrid.add(new Label("Account ID: "), 0,0);
		performTransactionGrid.add(tfTransactionID, 1,0);
		performTransactionGrid.add(new Label("Amount: "), 0,1);
		performTransactionGrid.add(tfTransactionAmount, 1,1);
		performTransactionGrid.add(depositButton, 0,3);
		performTransactionGrid.add(withdrawButton, 1,3);
		depositButton.setOnAction(e -> deposit());
		withdrawButton.setOnAction(e -> withdraw());
		
		//ADMIN
		adminPane.setContent(adminGrid);
		adminPane.setText("Administration");
		adminPane.setExpanded(false);
		adminGrid.add(monthEndBtn, 0, 0);
		monthEndBtn.setOnAction(e -> monthEndProcess());
		
		//BANK STATISTICS
		bankStatsPane.setContent(bankStatsGrid);
		bankStatsPane.setText("Bank Statistics");
		bankStatsPane.setExpanded(true);
		bankStatsGrid.add(new Label("Total Balance: "), 0, 0);
		bankStatsGrid.add(tfTotalBal,1, 0);
		tfTotalBal.setEditable(false);
		
		bankStatsGrid.add(new Label("Number of Accounts: "), 0, 1);
		bankStatsGrid.add(tfNumAccts,1, 1);
		tfTotalBal.setEditable(false);
		
		bankStatsGrid.add(new Label("Average Balance: "), 0, 2);
		bankStatsGrid.add(tfAvgBal,1, 2);
		tfAvgBal.setEditable(false);
		
		bankStatsGrid.add(new Label("Largest Balance: "), 0, 3);
		bankStatsGrid.add(tfLargestBal,1, 3);
		tfLargestBal.setEditable(false);
		
		bankStatsGrid.add(new Label("Zero Balance Accts:"), 0, 4);
		bankStatsGrid.add(tfNumZeroBalAccts,1, 4);
		tfLargestBal.setEditable(false);

		accordian.getChildren().addAll(addAccountPane, removeAccountPane, performTransactionPane, adminPane, bankStatsPane);
		
		taOutput.setEditable(false);
		mainPane.setLeft(accordian);
		mainPane.setRight(taOutput);
		

		Scene scene = new Scene(mainPane, 900, 600);
		primaryStage.setTitle("Simple Banking System");
		primaryStage.setScene(scene);
		primaryStage.show();
		refreshOutputs();
		
		Button btnClear = new Button();
		btnClear.setOnAction(new EventHandler<ActionEvent>() {
			   @Override 
			   public void handle(ActionEvent e) {
			      taOutput.clear();
			   }
			 });
	}
	
	public String accountSummary() {
		String summary = "";
		for(Account a: bank.getAccounts()) {
			summary += a.toString();
			summary += '\n';
			summary += a.getLedger().toString();
		}
		return summary;
	}
	
	public void showAccountList() {
		taOutput.setText(accountSummary());
	}
	
	public void addAccount() {
		if(tfAddAccount_CustomerLastName.getText() == "" || tfAddAccount_CustomerFirstName.getText() == "") {
			lblAddAcctStatus.setText("You must enter the customer's full name.");
		} else {
			if(addRegAcct.isSelected()) {
				bank.addAccount(new RegularAccount(bank.getNextAcctID(), new Customer(bank.getNextCustID(), tfAddAccount_CustomerLastName.getText() + ", " + tfAddAccount_CustomerFirstName.getText())));
			} else if(addChkAcct.isSelected()){
				bank.addAccount(new CheckingAccount(bank.getNextAcctID(), new Customer(bank.getNextCustID(), tfAddAccount_CustomerLastName.getText() + ", " + tfAddAccount_CustomerFirstName.getText())));
			} else if(addGoldAcct.isSelected()){
				bank.addAccount(new GoldAccount(bank.getNextAcctID(), new Customer(bank.getNextCustID(), tfAddAccount_CustomerLastName.getText() + ", " + tfAddAccount_CustomerFirstName.getText())));
			}
			lblAddAcctStatus.setText("Account added.");
		}
		tfAddAccount_CustomerLastName.clear();
		tfAddAccount_CustomerFirstName.clear();
		refreshOutputs();
	}
	
	public void removeAccount() {
		if(tfRemoveAccount_AccountID.getText() == "") {
			lblRemoveAcctStatus.setText("Please enter an account ID.");
		} else {
			
		int acctToRemove = Integer.parseInt(tfRemoveAccount_AccountID.getText());
		
		if(bank.accountExists(acctToRemove)) {
			bank.removeAccount(acctToRemove);
			lblRemoveAcctStatus.setText(String.format("Account %d removed.", acctToRemove));
		} else {
			lblRemoveAcctStatus.setText(String.format("Account %d does not exist.", acctToRemove));
		}
		tfRemoveAccount_AccountID.clear();
		refreshOutputs();
	}
	}
	
	public void deposit() {
		if(tfTransactionID.getText() == "" || tfTransactionAmount.getText() == "") {
			lblTransactionStatus.setText("You cannot leave fields blank.");
		} else {
		
		int acctID = Integer.parseInt(tfTransactionID.getText());
		
		if(bank.accountExists(acctID)) {
			for(Account a:bank.getAccounts()) {
				if(a.getId() == acctID) {
					a.deposit(Double.parseDouble(tfTransactionAmount.getText()));
				}
			}
			lblTransactionStatus.setText("Deposit successful");

		} else {
			lblTransactionStatus.setText("Account does not exist");
		}

		}
		tfTransactionID.clear();
		tfTransactionAmount.clear();
		refreshOutputs();
	}
	
	public void withdraw() {
		if(tfTransactionID.getText() == "" || tfTransactionAmount.getText() == "") {
			lblTransactionStatus.setText("You cannot leave fields blank.");
		} else {

		int acctID = Integer.parseInt(tfTransactionID.getText());
		double withdrawalAmt = Double.parseDouble(tfTransactionAmount.getText());
		
		if(bank.accountExists(acctID)) {
			for(Account a:bank.getAccounts()) {
				if(a.getId() == acctID) {
					double acctBal = a.getBalance();
					if(a.canWithdraw(withdrawalAmt)) {
						a.withdraw(withdrawalAmt);
						lblTransactionStatus.setText("Withdrawal successful");
					} else {
						a.withdraw(withdrawalAmt);
						lblTransactionStatus.setText(String.format("Insufficient funds. $%.2f withdrawn.", acctBal));
					}
				}
			}

		
		} else {
			lblTransactionStatus.setText("Account does not exist");
		}

		}
		tfTransactionID.clear();
		tfTransactionAmount.clear();
		refreshOutputs();
	}
	
	
	public void monthEndProcess() {
		for(Account a: bank.getAccounts()) {
			a.monthEndProcess();
		}
		refreshOutputs();

	}
	
	public void getTotalBalance() {
		tfTotalBal.setText(String.format("$%.2f", bank.totalBalance()));
	}
	
	public void getAvgBalance() {
		tfAvgBal.setText(String.format("$%.2f", bank.avgBalance()));
	}
	public void getNumAccts() {
		tfNumAccts.setText(Integer.toString(bank.numAccounts()));
	}
	
	public void getLargestBalance() {
		tfLargestBal.setText(String.format("$%.2f", bank.largestBalance()));
	}
	
	public void getNumZeroBalAccts() {
		tfNumZeroBalAccts.setText(Integer.toString(bank.numZeroBalAccts()));
	}

	public void refreshOutputs() {
		showAccountList();
		getTotalBalance();
		getNumAccts();
		getAvgBalance();
		getLargestBalance();
		getNumZeroBalAccts();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	}


