package com.madonahsyombua.bank;

import java.util.ArrayList;

public class Account {

	private String name;
	private String uuid;
	private User holder;
	private ArrayList<Transaction> transaction;

	public Account(String name, User holder, Bank theBank) {
		/**set the account name and holder**/
		this.name = name;
		this.holder = holder;
		/** get account uuid**/
		this.uuid = theBank.getNewAccontUUID();

		/**initialize transaction**/
		this.transaction = new ArrayList<Transaction>();

	}

	/** get the account ID**/
	public String getUUID() {

		return this.uuid;

	}

	public String getSummaryLine() {
		// TODO Auto-generated method stub
		/**get the accounts balance**/
		double balance = this.getBalance();
		/**format the summary line depending on whether the balance is negative**/

		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
		}

	}

	public double getBalance() {
		// TODO Auto-generated method stub
		double balance = 0;
		for (Transaction t : this.transaction) {
			balance += t.getAmount();
		}
		return balance;
	}

	
	public void printTransHistory() {

		System.out.printf("\nTranscation History for accounts %s\n", this.uuid);
		for (int t = this.transaction.size() - 1; t >= 0; t--) {
			System.out.printf(this.transaction.get(t).getSummaryLine());

		}
		System.out.println(); // TODO Auto-generated method stub

	}

	public void addTransaction(double amount, String memo) {
		// TODO Auto-generated method stub
		/** create new transaction object **/

		Transaction newTrans = new Transaction(amount, memo, this);
		this.transaction.add(newTrans);
	}

}
