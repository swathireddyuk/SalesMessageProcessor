package com.jpmorgan.sales.operations;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class for testing different Sales
 * @author Swathi N
 *
 */
public class SalesTest {

	Sales sales = new Sales();
	
	@Test
	public void test_process_add_message_notification() {
		boolean msg = sales.processNotification("Add 20p apples");
		Assert.assertTrue(msg);
	}

	@Test
	public void test_process_message_notification_adjusting_price() {
		boolean msg = sales.processNotification("20 sales of cherries at 10p each");
		Assert.assertTrue(msg);
	}
}
