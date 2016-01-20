package com.abc.util;

import static java.lang.Math.abs;

import java.util.Calendar;
import java.util.List;

import com.abc.Account;
import com.abc.Customer;
import com.abc.Transaction;

/**
 * Account Class utilities
 * 
 * @author hager.elmajhoub
 *
 */
public class AccountUtil {

	/**
	 * Returns the amount of the interest for the specific account
	 * 
	 * @param account
	 * @return the interest
	 */
	private static double getInterestEarnedForAccount(Account account) {
		double balance = account.getBalance();
		switch (account.getAccountType()) {

		case BankConstantUtil.SAVINGS_ACCOUNT:
			if (balance <= 1000)
				return balance * 0.001;
			else
				return 1 + (balance - 1000) * 0.002;

		case BankConstantUtil.MAXI_SAVINGS_ACCOUNT:
			double rate = getMaxiSavingAccountInterestRate(account);
			return balance * rate;
		default: // CHECKING ACCOUNT CASE
			return balance * 0.001;
		}
	}

	/**
	 * Returns the rate the an account with MAXI SAVING type
	 * 
	 * @param account
	 * @return
	 */
	private static double getMaxiSavingAccountInterestRate(Account account) {
		double rate = 0.001;
		boolean isWidthdraw = false;
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		for (Transaction transaction : account.getTransactions()) {
			if (transaction.getAmount() < 0) {
				calendar2.setTime(transaction.getTransactionDate());
				long diffInMilis = Math.abs(calendar1.getTimeInMillis() - calendar2.getTimeInMillis());
				long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
				if (diffInDays < 10) {
					isWidthdraw = true;
					break;
				}
			}

		}
		if (!isWidthdraw)
			rate = 0.005;
		return rate;
	}

	/**
	 * Returns the total of the interest earned for the accounts
	 * 
	 * @param accounts
	 * @return total interest
	 */
	protected static double getTotalInterestEarnedForAllAccounts(List<Account> accounts) {
		double totalOfAllAccounts = 0;
		for (Account account : accounts)
			totalOfAllAccounts += AccountUtil.getInterestEarnedForAccount(account);
		return totalOfAllAccounts;
	}

	/**
	 * Create an account for the customer
	 * 
	 * @param customer
	 * @param accountTypeToOpen
	 * @return the created account
	 */
	public static Account openAccount(Customer customer, int accountTypeToOpen) {
		Account newAccountToOpen = new Account(accountTypeToOpen);
		customer.addAccount(newAccountToOpen);
		return newAccountToOpen;
	}

	/**
	 * Deposits an amount for the account
	 * 
	 * @param account
	 * @param amountToDeposit
	 */
	public static void depositAmount(Account account, double amountToDeposit) {
		if (amountToDeposit <= 0) {
			throw new IllegalArgumentException(BankConstantUtil.AMOUNT_GREATER_THAN_ZERO_ERROR_MESSAGE);
		} else {
			account.addTransaction(new Transaction(amountToDeposit));
		}
	}

	/**
	 * withdraw an amount from the specific account
	 * 
	 * @param account
	 * @param amountToWithdraw
	 */
	public static void withdrawAmount(Account account, double amountToWithdraw) {
		if (amountToWithdraw <= 0) {
			throw new IllegalArgumentException(BankConstantUtil.AMOUNT_GREATER_THAN_ZERO_ERROR_MESSAGE);
		} else {
			account.addTransaction(new Transaction(-amountToWithdraw));
		}
	}

	/**
	 * Transfers an amount from the accountToWidhraw to accountToDeposit
	 * 
	 * @param accountToWidhraw
	 * @param accountToDeposit
	 * @param amount
	 */
	public static void transferAmount(Account accountToWidhraw, Account accountToDeposit, double amount) {
		withdrawAmount(accountToWidhraw, amount);
		depositAmount(accountToDeposit, amount);
	}

	/**
	 * Returns the statement for a customer
	 * 
	 * @param customer
	 * @return the statement
	 */
	public static String getStatement(Customer customer) {
		StringBuffer statementBuffer = new StringBuffer();
		statementBuffer.append(BankConstantUtil.STATEMENT_FOR).append(customer.getCustomerName());
		double total = 0.0;
		for (Account account : customer.getAccounts()) {
			statementBuffer.append(statementForAccount(account));
			total += account.getBalance();
		}
		statementBuffer.append(BankConstantUtil.NEW_LINE).append(BankConstantUtil.TOTAL_IN_ALL_ACCOUNTS)
				.append(formatToDollars(total));
		return statementBuffer.toString();
	}

	/**
	 * Returns a statement for an account
	 * 
	 * @param account
	 * @return the statement for an account
	 */
	private static String statementForAccount(Account account) {
		StringBuffer statementToReturn = new StringBuffer();
		statementToReturn.append(BankConstantUtil.NEW_LINE);
		// Translate to pretty account type
		switch (account.getAccountType()) {
		case BankConstantUtil.CHECKING_ACCOUNT:
			statementToReturn.append(BankConstantUtil.CHECKING_ACCOUNT_NAME);
			break;
		case BankConstantUtil.SAVINGS_ACCOUNT:
			statementToReturn.append(BankConstantUtil.SAVINGS_ACCOUNT_NAME);
			break;
		case BankConstantUtil.MAXI_SAVINGS_ACCOUNT:
			statementToReturn.append(BankConstantUtil.MAXI_SAVINGS_ACCOUNT_NAME);
			break;
		}

		// Now total up all the transactions
		double total = 0.0;
		for (Transaction transaction : account.getTransactions()) {
			statementToReturn
					.append(transaction.getAmount() < 0 ? BankConstantUtil.WITHDRAWAL : BankConstantUtil.DEPOSIT)
					.append(formatToDollars(transaction.getAmount())).append(BankConstantUtil.NEW_LINE);
			total += transaction.getAmount();
		}
		statementToReturn.append(BankConstantUtil.TOTAL).append(formatToDollars(total));
		statementToReturn.append(BankConstantUtil.NEW_LINE);
		return statementToReturn.toString();
	}

	/**
	 * Formats the amount to a dollar String
	 * 
	 * @param amount
	 * @return the formatted amount
	 */
	private static String formatToDollars(double amount) {
		return String.format(BankConstantUtil.DOLLAR_FORMAT, abs(amount));
	}
}
