package com.jpmorgan.sales.operations;

import org.junit.Assert;
import org.junit.Test;

import com.jpmorgan.sales.entity.Product;

/**
 * This class for testing price calculations -  add,multiply,subtract
 * @author Swathi N
 *
 */
public class PriceAdjusterTest {

	@Test
	public void test_add_price() {
		Product product = new Product("apples");
		product.setProductType("apples");
		product.setProductQuantity(10);	
		product.setTotalQuantity(100);
		product.setTotalPrice(1000.0);
		product.setAdjustmentOperator("ADD");
		PriceAdjuster priceAdjuster = new PriceAdjuster(product);
		priceAdjuster.addPrice();
		Assert.assertTrue(1000.0 == priceAdjuster.getAdjustedPrice());
	}
	
	@Test
	public void test_subtract_price() {
		Product product = new Product("apples");
		product.setProductType("apples");
		product.setProductQuantity(10);	
		product.setTotalQuantity(100);
		product.setTotalPrice(100.0);
		product.setAdjustmentOperator("SUBTRACT");
		PriceAdjuster priceAdjuster = new PriceAdjuster(product);
		priceAdjuster.subtractPrice();
		Assert.assertTrue(100.0 == priceAdjuster.getAdjustedPrice());
	}
	
	@Test
	public void test_multiply_price() {
		Product product = new Product("apples");
		product.setProductType("apples");
		product.setProductQuantity(10);	
		product.setTotalQuantity(100);
		product.setTotalPrice(100.0);
		product.setAdjustmentOperator("MULTIPLY");
		PriceAdjuster priceAdjuster = new PriceAdjuster(product);
		priceAdjuster.multiplyPrice();
		Assert.assertTrue(100.0 == priceAdjuster.getAdjustedPrice());
	}

	@Test
	public void test_get_adjustment_report(){
		Product product = new Product("apples");
		product.setProductType("apples");
		product.setProductQuantity(10);	
		product.setTotalQuantity(100);
		product.setTotalPrice(100.0);
		product.setAdjustmentOperator("MULTIPLY");
		PriceAdjuster priceAdjuster = new PriceAdjuster(product);
		String report = priceAdjuster.adjustmentReport();
		Assert.assertTrue(report.equalsIgnoreCase("Performed MULTIPLY 0.00p to 100 apples and price adjusted from 100.00p to 0.00p"));
	}
}
