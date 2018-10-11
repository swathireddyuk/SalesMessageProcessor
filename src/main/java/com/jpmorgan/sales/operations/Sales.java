package com.jpmorgan.sales.operations;

import com.jpmorgan.sales.entity.Product;
import com.jpmorgan.sales.report.SalesReport;
import com.jpmorgan.sales.utils.MessageParser;

/**
 * This class for processing sales notifications
 * @author Swathi N
 *
 */
public class Sales {

	public SalesReport salesReport;

	private PriceAdjuster priceAdjuster;

	private Product product;

	public Sales() {
		salesReport = new SalesReport();
	}

	// Process message notifications
	public boolean processNotification(String saleNotice) {

		MessageParser messageParser;
		messageParser = new MessageParser(saleNotice);

		String productType = messageParser.getProductType();

		// Check if product type is empty return false and do nothing.
		if (productType.isEmpty()) {
			return false;
		}

		// Returns an existing product else returns a new Product object
		this.product = salesReport.getProduct(productType);

		// Prepare the product details for adjustment
		this.priceAdjuster = new PriceAdjuster(product);

		// Set the product details from the parsed message
		this.product.setProductQuantity(messageParser.getProductQuantity());
		this.product.setTotalQuantity(messageParser.getProductQuantity());
		this.product.setProductPrice(messageParser.getProductPrice());
		this.product.setAdjustmentOperator(messageParser.getOperatorType());

		// Set the total value of the product.
		setProductTotalPrice();
		salesReport.setNormalReports(saleNotice);
		salesReport.updateProduct(product);

		return true;
	}

	// Set or append product price
	private void setProductTotalPrice() {
		double adjustedPrice;
		double productValue;

		if (!product.getAdjustmentOperator().isEmpty()) {
			adjustedPrice = priceAdjuster.getAdjustedPrice();
			salesReport.setAdjustmentReports(priceAdjuster.adjustmentReport());
			product.setTotalPrice(adjustedPrice);
		} else {
			productValue = product.calculatePrice(product.getProductQuantity(), product.getProductPrice());
			product.appendTotalPrice(productValue);
		}
	}

}
