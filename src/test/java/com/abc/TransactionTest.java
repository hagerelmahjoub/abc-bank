package com.abc;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for Transaction
 * 
 * @author hager.elmahjoub
 *
 */
public class TransactionTest {
	
	/**
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void transaction() throws InterruptedException {

		Transaction transaction1 = new Transaction(5);
		System.out.println("Waiting for 3s...");
		Thread.sleep(3000);
		Transaction transaction2 = new Transaction(5);
		System.out.println(transaction1.getTransactionDate());

		System.out.println(transaction2.getTransactionDate());
		Assert.assertTrue(transaction1.getTransactionDate().before((transaction2.getTransactionDate())));

	}
}
