package com.abc;

import java.util.ArrayList;
import java.util.List;

/**
 * Bank Entity
 * 
 * @author hager.elmahjoub
 *
 */
public class Bank {

	/**
	 * Attributes
	 */
	private List<Customer> customers;

	/**
	 * Default Constructor
	 */
	public Bank() {
		customers = new ArrayList<Customer>();
	}

	/**
	 * 
	 * @param CustomertoAdd
	 */
	public void addCustomer(Customer CustomertoAdd) {
		customers.add(CustomertoAdd);
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
