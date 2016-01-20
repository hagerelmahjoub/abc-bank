package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author hager.elmahjoub
 *
 */
public class Transaction {
	
	private final double amount;
	private Date transactionDate;

	/**
	 * 
	 * @param amount
	 */
	public Transaction(double amount) {
		this.amount = amount;
		this.transactionDate =Calendar.getInstance().getTime();
	}

	/**
	 * 
	 * @return
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * 
	 * @return
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

}
