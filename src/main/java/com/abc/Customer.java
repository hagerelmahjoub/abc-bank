package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer Class
 * 
 * @author hager.elmahjoub
 *
 */
public class Customer {
	private String customerName;
	private List<Account> accounts;

	/**
	 * 
	 * @param name
	 */
	public Customer(String customerName) {
		this.customerName = customerName;
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * 
	 * @param accountToAdd
	 */
	public void addAccount(Account accountToAdd) {
		this.accounts.add(accountToAdd);
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
