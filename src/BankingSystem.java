import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingSystem extends Application{
	//BorderPanes
	BorderPane mainPane = new BorderPane(); 

	// Buttons
	Button addAcctBtn = new Button("Add Account");
	Button removeAcctBtn = new Button("Remove Account");
	Button depositButton = new Button("Deposit");
	Button withdrawButton = new Button("Withdraw");
	Button monthEndBtn = new Button("Monthly Process");
	
	// RadioButtons
	RadioButton addRegAcct = new RadioButton("Regular");
	RadioButton addChkAcct = new RadioButton("Checking");
	RadioButton addGoldAcct = new RadioButton("Gold");
	
	// TitledPanes
	TitledPane addAccountPane = new TitledPane();
	TitledPane removeAccountPane = new TitledPane();
	TitledPane adminPane = new TitledPane();
	TitledPane bankStatsPane = new TitledPane();
	TitledPane performTransactionPane = new TitledPane();
	
	//ToggleGroups
	ToggleGroup tgAddAccount_Type = new ToggleGroup();

	//GridPanes
	GridPane removeAccountGrid = new GridPane();
	GridPane performTransactionGrid = new GridPane();
	GridPane addAccountGrid = new GridPane();
	GridPane adminGrid = new GridPane();
	GridPane bankStatsGrid = new GridPane();

	//Labels
	Label lblTransactionStatus = new Label();
	Label lblRemoveAcctStatus = new Label();
	Label lblAddAcctStatus = new Label();
	Label lblAddAcctCustName = new Label("Customer Name:  ");
	Label lblAddAcctType = new Label("Account Type:  ");
	Label lblRemoveAcctId = new Label("Account ID: ");
	Label lblTransactionAcctId = new Label("Account ID: ");
	Label lblTransactionAmount = new Label("Amount: ");
	Label lblTotalBalance = new Label("Total Balance: ");
	Label lblNumAccts = new Label("Number of Accounts: ");
	Label lblAvgBal = new Label("Average Balance: ");
	Label lblLargestBal = new Label("Largest Balance: ");
	Label lblNumZeroBalAccts = new Label("Zero Balance Accts:");
	
	//VBox
	VBox transactionVBox = new VBox();
	VBox removeAcctVBox = new VBox();
	VBox addAcctVBox = new VBox();
	VBox accordian = new VBox();
	
	//TextAreas
	TextArea taOutput = new TextArea();
	
	//TextFields
	TextField tfTotalBal = new TextField();
	TextField tfAvgBal = new TextField();
	TextField tfNumAccts = new TextField();
	TextField tfLargestBal = new TextField();
	TextField tfNumZeroBalAccts = new TextField();
	TextField tfTransactionID = new TextField();
	TextField tfTransactionAmount = new TextField("");
	TextField tfRemoveAccount_AccountID = new TextField();
	TextField tfAddAccount_CustomerFirstName = new TextField();
	TextField tfAddAccount_CustomerLastName = new TextField();	
	
	//Bank
	Bank bank = new Bank();
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Simple Banking System");
		
		// ADD ACCOUNT 
			addAccountPane.setText("Add Account");
			addAccountPane.setExpanded(false);
			
			//Customer name fields
			addAccountGrid.add(lblAddAcctCustName, 0, 0);
			
			addAccountGrid.add(tfAddAccount_CustomerLastName, 1, 0);
			tfAddAccount_CustomerLastName.setPromptText("Last");
			
			addAccountGrid.add(tfAddAccount_CustomerFirstName, 2, 0);
			tfAddAccount_CustomerFirstName.setPromptText("First");
			
			//Account type options
			addAccountGrid.add(lblAddAcctType, 0, 1);
			
			addAccountGrid.add(addRegAcct, 1, 1);
			addRegAcct.setToggleGroup(tgAddAccount_Type);
			addRegAcct.setSelected(true);
			
			addAccountGrid.add(addChkAcct, 1, 2);
			addChkAcct.setToggleGroup(tgAddAccount_Type);
			
			addAccountGrid.add(addGoldAcct, 1, 3);
			addGoldAcct.setToggleGroup(tgAddAccount_Type);
			
			addAccountGrid.add(addAcctBtn, 0, 4);
			addAcctBtn.setOnAction(e -> addAccount());
			
			addAcctVBox.getChildren().addAll(addAccountGrid,lblAddAcctStatus);
			addAccountPane.setContent(addAcctVBox);

		//REMOVE ACCOUNT
			removeAccountPane.setText("Remove Account");
			removeAccountPane.setExpanded(false);
			
			removeAccountGrid.add(lblRemoveAcctId, 0,0);
			removeAccountGrid.add(tfRemoveAccount_AccountID, 1, 0);
			
			removeAccountGrid.add(removeAcctBtn, 0, 2);
			removeAcctBtn.setOnAction(e -> removeAccount());
			
			removeAcctVBox.getChildren().addAll(removeAccountGrid, lblRemoveAcctStatus);
			removeAccountPane.setContent(removeAcctVBox);

		//TRANSACTIONS
			performTransactionPane.setText("Perform Transactions");
			performTransactionPane.setExpanded(false);
			
			performTransactionGrid.add(lblTransactionAcctId, 0,0);
			performTransactionGrid.add(tfTransactionID, 1,0);
			performTransactionGrid.add(lblTransactionAmount, 0,1);
			performTransactionGrid.add(tfTransactionAmount, 1,1);
			
			performTransactionGrid.add(depositButton, 0,3);
			depositButton.setOnAction(e -> deposit());
			
			performTransactionGrid.add(withdrawButton, 1,3);
			withdrawButton.setOnAction(e -> withdraw());
			
			transactionVBox.getChildren().addAll(performTransactionGrid, lblTransactionStatus);
			performTransactionPane.setContent(transactionVBox);
		
		//ADMIN
			adminPane.setText("Administration");
			adminPane.setExpanded(false);
			
			adminGrid.add(monthEndBtn, 0, 0);
			monthEndBtn.setOnAction(e -> monthEndProcess());
			
			adminPane.setContent(adminGrid);
		
		//BANK STATISTICS
			bankStatsPane.setText("Bank Statistics");
			bankStatsPane.setExpanded(true);
			
			bankStatsGrid.add(lblTotalBalance, 0, 0);
			bankStatsGrid.add(tfTotalBal,1, 0);
			tfTotalBal.setEditable(false);
			
			bankStatsGrid.add(lblNumAccts, 0, 1);
			bankStatsGrid.add(tfNumAccts,1, 1);
			tfTotalBal.setEditable(false);
			
			bankStatsGrid.add(lblAvgBal, 0, 2);
			bankStatsGrid.add(tfAvgBal,1, 2);
			tfAvgBal.setEditable(false);
			
			bankStatsGrid.add(lblLargestBal, 0, 3);
			bankStatsGrid.add(tfLargestBal,1, 3);
			tfLargestBal.setEditable(false);
			
			bankStatsGrid.add(lblNumZeroBalAccts, 0, 4);
			bankStatsGrid.add(tfNumZeroBalAccts,1, 4);
			tfLargestBal.setEditable(false);
			
			bankStatsPane.setContent(bankStatsGrid);
	
		taOutput.setEditable(false);
		accordian.getChildren().addAll(addAccountPane, removeAccountPane, performTransactionPane, adminPane, bankStatsPane);
		mainPane.setLeft(accordian);
		mainPane.setRight(taOutput);
		
		Scene scene = new Scene(mainPane, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		refreshOutputs();
	}
	
//	OUTPUT CONTROL METHODS	
	public void showAccountList() {
		taOutput.setText(bank.accountSummary());
	}
	
	public void getTotalBalance() {
		tfTotalBal.setText(String.format("$%.2f", bank.totalBalance()));
	}

	public void getNumAccts() {
		tfNumAccts.setText(Integer.toString(bank.numAccounts()));
	}

	public void getAvgBalance() {
		tfAvgBal.setText(String.format("$%.2f", bank.avgBalance()));
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
	
//	BUTTON ACTION METHODS
	public void addAccount() {
		if(tfAddAccount_CustomerLastName.getText() == "" || tfAddAccount_CustomerFirstName.getText() == "") {
			lblAddAcctStatus.setText("You must enter the customer's full name.");
		} else {
			if(addRegAcct.isSelected()) {
				bank.addAccount(new RegularAccount(bank.getNextAcctID(), new Customer(bank.getNextCustID(), tfAddAccount_CustomerLastName.getText() + ", " + tfAddAccount_CustomerFirstName.getText()), 0));
			} else if(addChkAcct.isSelected()){
				bank.addAccount(new CheckingAccount(bank.getNextAcctID(), new Customer(bank.getNextCustID(), tfAddAccount_CustomerLastName.getText() + ", " + tfAddAccount_CustomerFirstName.getText()), 0));
			} else if(addGoldAcct.isSelected()){
				bank.addAccount(new GoldAccount(bank.getNextAcctID(), new Customer(bank.getNextCustID(), tfAddAccount_CustomerLastName.getText() + ", " + tfAddAccount_CustomerFirstName.getText()), 0));
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
	
	
//	MAIN METHOD
	public static void main(String[] args) {
		launch(args);
	}

	}


