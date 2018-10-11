package com.jpmorgan.sales.operations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jpmorgan.sales.entity.Product;

/**
 * This class is for handling the price calculations if anything to be adjusted
 * @author Swathi N
 *
 */
public class PriceAdjuster implements PriceCaluculation{

	private double adjustedPrice;

	private Product product;

	public PriceAdjuster(Product product) {
		this.product = product;
		this.adjustedPrice = 0.0;
	}

	//@returns adjustedPrice
	public double getAdjustedPrice() {
		String adjustmentMethod = String.format("%sPrice", product.getAdjustmentOperator().toLowerCase());
		try {
			Method method = this.getClass().getMethod(adjustmentMethod, null);
			method.invoke(this, null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return adjustedPrice;
	}
	
	// @returns adjustmentReport
	public String adjustmentReport() {
		String adjustmentReport = String.format("Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
				this.product.getAdjustmentOperator(), this.product.getProductPrice(), this.product.getTotalQuantity(),
				this.product.getProductType(), this.product.getTotalPrice(), this.adjustedPrice);
		return adjustmentReport;
	}

	@Override
	//Adds product totalprice with the requested price value
	public void addPrice() {
		this.adjustedPrice = this.product.getTotalPrice()
				+ (this.product.getTotalQuantity() * this.product.getProductPrice());
		
	}

	@Override
	//Subtracts product totalprice with the requested price value
	public void subtractPrice() {
		this.adjustedPrice = this.product.getTotalPrice()
				- (this.product.getTotalQuantity() * this.product.getProductPrice());		
	}

	@Override
	//Multiplies product totalprice with the requested price value
	public void multiplyPrice() {
		this.adjustedPrice = this.product.getTotalPrice()
				+ (this.product.getTotalPrice() * this.product.getProductPrice())
				+ (this.product.getTotalQuantity() * this.product.getProductPrice());		
	}

}
