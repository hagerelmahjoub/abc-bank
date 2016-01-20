package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.util.AccountUtil;
import com.abc.util.BankConstantUtil;
import com.abc.util.BankUtil;

/**
 * Unit Test class for Bank Class
 * 
 * @author hager.elmahjoub
 *
 */
public class BankUtilTest {
	/**
	 * 
	 */
	static final double DOUBLE_DELTA = 1e-15;

	@Test(expected = Exception.class)
	public void testAddCustomerToBankException() throws Exception {
		{
			Bank bank = new Bank();
			BankUtil.getFirstCustomer(bank);
		}
	}

	@Test
	public void testAddCustomerToBank() throws Exception {
		{
			Bank bank = new Bank();
			BankUtil.addCustomer(bank, new Customer("hager.elmahjoub"));
			BankUtil.addCustomer(bank, new Customer("John.Doe"));
			BankUtil.addCustomer(bank, new Customer("Jane.Doe"));
			assertEquals(3, bank.getCustomers().size());
			assertEquals("hager.elmahjoub", BankUtil.getFirstCustomer(bank));
		}
	}

	/**
	 * 
	 */
	@Test
	public void testGetCustomerSummary() {
		Bank bank = new Bank();
		Customer customer = BankUtil.addCustomer(bank, new Customer("hager.elmahjoub"));
		AccountUtil.openAccount(customer, BankConstantUtil.CHECKING_ACCOUNT);
		AccountUtil.openAccount(customer, BankConstantUtil.SAVINGS_ACCOUNT);
		AccountUtil.openAccount(customer, BankConstantUtil.MAXI_SAVINGS_ACCOUNT);
		assertEquals(BankConstantUtil.CUSTOMER_SUMMARY + "hager.elmahjoub(3 accounts)",
				BankUtil.getCustomerSummary(bank));
	}

	@Test
	public void testCreateCheckingAccountAndMakeDeposit() {
		Bank bank = new Bank();
		Customer newCustomer = new Customer("Bill");
		Account checkingAccount = AccountUtil.openAccount(newCustomer, BankConstantUtil.CHECKING_ACCOUNT);
		BankUtil.addCustomer(bank, newCustomer);
		AccountUtil.depositAmount(checkingAccount, 100.0);
		assertEquals(0.1, BankUtil.getTotalInterestPaid(bank), DOUBLE_DELTA);
	}

	@Test
	public void testCreateSavingsAccountAndMakeDeposit() {
		Bank bank = new Bank();
		Customer newCustomer = new Customer("Bill");
		BankUtil.addCustomer(bank, newCustomer);
		Account savingAccount = AccountUtil.openAccount(newCustomer, BankConstantUtil.SAVINGS_ACCOUNT);
		AccountUtil.depositAmount(savingAccount, 1500.0);
		assertEquals(2.0, BankUtil.getTotalInterestPaid(bank), DOUBLE_DELTA);
	}

	@Test
	public void testCreateMaxSavingsAccountAndMakeDeposit() {
		Bank bank = new Bank();
		Customer newCustomer = new Customer("Bill");
		BankUtil.addCustomer(bank, newCustomer);
		Account maxiSavingAccount = AccountUtil.openAccount(newCustomer, BankConstantUtil.MAXI_SAVINGS_ACCOUNT);
		AccountUtil.depositAmount(maxiSavingAccount, 3000.0);
		AccountUtil.depositAmount(maxiSavingAccount, 500.0);
		AccountUtil.depositAmount(maxiSavingAccount, 100.0);
		AccountUtil.withdrawAmount(maxiSavingAccount, 1500.0);

		assertEquals(2.1, BankUtil.getTotalInterestPaid(bank), DOUBLE_DELTA);
	}

}
