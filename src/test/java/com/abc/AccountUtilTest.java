package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.util.AccountUtil;
import com.abc.util.BankConstantUtil;

/**
 * Test class for AccountUtil class
 * 
 * @author hager.elmahjoub
 *
 */
public class AccountUtilTest {

	private Customer newCustomer = new Customer("Oscar");

	/**
	 * 
	 */
	@Test // Test customer statement generation
	public void testGetStatement() {

		Customer newCustomer = new Customer("Henry");
		Account checkingAccount = AccountUtil.openAccount(newCustomer, BankConstantUtil.CHECKING_ACCOUNT);
		Account savingsAccount = AccountUtil.openAccount(newCustomer, BankConstantUtil.SAVINGS_ACCOUNT);
		AccountUtil.depositAmount(checkingAccount, 100.0);
		AccountUtil.depositAmount(savingsAccount, 4000.0);
		AccountUtil.withdrawAmount(savingsAccount, 200.0);

		String actual = AccountUtil.getStatement(newCustomer);
		String expected = BankConstantUtil.STATEMENT_FOR + "Henry\n" + BankConstantUtil.CHECKING_ACCOUNT_NAME
				+ BankConstantUtil.DEPOSIT + "$100.00\n" + BankConstantUtil.TOTAL + "$100.00\n\n" +

		BankConstantUtil.SAVINGS_ACCOUNT_NAME + BankConstantUtil.DEPOSIT + "$4,000.00\n" + BankConstantUtil.WITHDRAWAL
				+ "$200.00\n" + BankConstantUtil.TOTAL + "$3,800.00\n\n" +

		BankConstantUtil.TOTAL_IN_ALL_ACCOUNTS + "$3,900.00";
		assertEquals(expected, actual);
	}

	/**
	 * 
	 */
	@Test
	public void testCreateOpenOneAccount() {
		AccountUtil.openAccount(newCustomer, BankConstantUtil.SAVINGS_ACCOUNT);
		assertEquals(1, newCustomer.getNumberOfAccounts());
	}

	/**
	 * 
	 */
	@Test
	public void testCreateOpenTwoAccount() {
		AccountUtil.openAccount(newCustomer, BankConstantUtil.SAVINGS_ACCOUNT);
		AccountUtil.openAccount(newCustomer, BankConstantUtil.CHECKING_ACCOUNT);
		assertEquals(2, newCustomer.getNumberOfAccounts());
	}

	/**
	 * 
	 */
	@Test
	public void testCreateOpenThreeAccount() {
		AccountUtil.openAccount(newCustomer, BankConstantUtil.SAVINGS_ACCOUNT);
		AccountUtil.openAccount(newCustomer, BankConstantUtil.CHECKING_ACCOUNT);
		AccountUtil.openAccount(newCustomer, BankConstantUtil.MAXI_SAVINGS_ACCOUNT);
		assertEquals(3, newCustomer.getNumberOfAccounts());
	}

	/**
	 * Test for AccountUtil.transferAmount
	 */
	@Test
	public void testTransferAmount() {
		Account savingAccount = AccountUtil.openAccount(newCustomer, BankConstantUtil.SAVINGS_ACCOUNT);

		Account checkingAccount = AccountUtil.openAccount(newCustomer, BankConstantUtil.CHECKING_ACCOUNT);
		AccountUtil.depositAmount(checkingAccount, 5000.0);
		assertEquals(2, newCustomer.getNumberOfAccounts());
		AccountUtil.transferAmount(checkingAccount, savingAccount, 1000.0);
		assertEquals(4000.0, checkingAccount.getBalance(), BankUtilTest.DOUBLE_DELTA);
		assertEquals(1000.0, savingAccount.getBalance(), BankUtilTest.DOUBLE_DELTA);

	}
}
