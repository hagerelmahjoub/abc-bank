package com.abc.util;

import com.abc.Bank;
import com.abc.Customer;

/**
 * Utility Class for all related bank operation
 * 
 * @author hager.elmahjoub
 *
 */
public class BankUtil {

	/**
	 * adds a customer to myBank
	 * 
	 * @param myBank
	 * @param customerToAdd
	 * @return the created customer
	 */
	public static Customer addCustomer(Bank myBank, Customer customerToAdd) {
		myBank.addCustomer(customerToAdd);
		return customerToAdd;
	}

	/**
	 * Returns a report of all the customers of the bank
	 * 
	 * @return
	 */
	public static String getCustomerSummary(Bank myBank) {
		StringBuffer summaryBuffer = new StringBuffer(BankConstantUtil.CUSTOMER_SUMMARY);
		for (Customer customer : myBank.getCustomers()) {
			String formatAccount = getWordingAccount(customer.getNumberOfAccounts());
			summaryBuffer.append(customer.getCustomerName()).append(BankConstantUtil.OPENING_PARENTHESIS)
					.append(formatAccount).append(BankConstantUtil.CLOSING_PARENTHESIS);

		}
		return summaryBuffer.toString();
	}

	/**
	 * 
	 * @param accountNumber
	 * @return
	 */
	private static String getWordingAccount(int accountNumber) {
		return new StringBuffer().append(accountNumber)
				.append((accountNumber == 1 ? BankConstantUtil.ACCOUNT : BankConstantUtil.ACCOUNTS)).toString();
	}

	/**
	 * Computes the total interest to be paid by the bank
	 * 
	 * @param myBank
	 * @return the total interest
	 */
	public static double getTotalInterestPaid(Bank myBank) {
		double totalInterestPaid = 0;
		for (Customer customer : myBank.getCustomers())
			totalInterestPaid += AccountUtil.getTotalInterestEarnedForAllAccounts(customer.getAccounts());
		return totalInterestPaid;
	}

	/**
	 * Retrieves the name of first customer of the bank. If no customer found,
	 * then throws an exception
	 * 
	 * @param myBank
	 * @return the name the customer
	 * @throws Exception
	 */
	public static String getFirstCustomer(Bank myBank) throws Exception {
		try {
			return myBank.getCustomers().get(0).getCustomerName();
		} catch (Exception exception) {
			throw exception;
		}
	}

}
