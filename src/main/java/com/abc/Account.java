package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * Account Class
 * 
 * @author hager.elmajhoub
 *
 */
public class Account {

	private int accountType;
	private List<Transaction> transactions;

	/**
	 * 
	 * @param accountType
	 */
	public Account(int accountType) {
		this.accountType = accountType;
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * 
	 * @return
	 */
	public int getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction transactionToAdd) {
		this.transactions.add(transactionToAdd);
	}

	public double getBalance() {
		double balance = 0.0;
		for (Transaction transaction : transactions) {
			balance += transaction.getAmount();
		}
		return balance;
	}

}
