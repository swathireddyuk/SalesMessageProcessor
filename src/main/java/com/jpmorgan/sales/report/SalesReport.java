package com.jpmorgan.sales.report;

import java.util.ArrayList;
import java.util.HashMap;

import com.jpmorgan.sales.entity.Product;

/**
 * This class for handling sales reports
 * @author Swathi N
 *
 */
public class SalesReport {

	private HashMap<String, Product> lineItems = new HashMap<>();

	private double totalSalesValue;

	private ArrayList<String> normalReports;

	private ArrayList<String> adjustmentReports;

	// Constructor
	public SalesReport() {
		this.normalReports = new ArrayList<>();
		this.adjustmentReports = new ArrayList<>();
		this.totalSalesValue = 0.0;
	}

	// Get the product from line item based on their type e.g, apple
	public Product getProduct(String type) {
		return lineItems.getOrDefault(type, new Product(type));
	}

	// Update the line item product with new details.
	public void updateProduct(Product product) {
		lineItems.put(product.getProductType(), product);
	}

	// Get all the reports that have been processed so far.
	public ArrayList<String> getNormalReports() {
		return normalReports;
	}

	// Set a sales notice message
	public void setNormalReports(String normalReport) {
		this.normalReports.add(normalReport);
	}

	// Get all the adjustment report as an array list
	public ArrayList<String> getAdjustmentReports() {
		return adjustmentReports;
	}

	// Set an adjustment report string to the adjustmentReports array
	public void setAdjustmentReports(String adjustmentReport) {
		this.adjustmentReports.add(adjustmentReport);
	}

	// return the total sales value
	public double getTotalSalesValue() {
		return totalSalesValue;
	}

	// Append any given value to the totalSalesValue field
	public void appendTotalSalesValue(double productTotalPrice) {
		totalSalesValue += productTotalPrice;
	}

	// Set total sales value with the given value
	public void setTotalSalesValue(double productTotalPrice) {
		totalSalesValue = productTotalPrice;
	}

	//generate report
	public void report() {

		// Report after 10th iteration and not at the beginning.
		if (0 == (normalReports.size() % 10) && 0 != normalReports.size()) {
			setTotalSalesValue(0.0);
			// System.out.println(normalReports);
			System.out.println("10 sales appended to log");
			System.out.println("*************** Log Report *****************");
			System.out.println("|SalesMessageProcessor.Product           |Quantity   |Value      |");
			lineItems.forEach((k, v) -> formatReports(k, v));
			System.out.println("-------------------------------------------");
			System.out.println(String.format("|%-30s|%-11.2f|", "Total Sales", getTotalSalesValue()));
			System.out.println("-------------------------------------------");
			System.out.println("End\n\n");
			try {
				// Add 2 second pause
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Report and stop execution after 50th message
		if ( 0 == (normalReports.size() % 50) && 0 != normalReports.size()) {
			System.out.println(
					"Application reached 50 messages and cannot process further. The following are the adjustment records made;\n");

			// Display all the adjustment reports so far recorded.
			getAdjustmentReports().forEach(System.out::println);
			System.exit(1);
		}
	}

	// to format the report
	public void formatReports(String type, Product product) {
		String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(), product.getTotalQuantity(),
				product.getTotalPrice());
		appendTotalSalesValue(product.getTotalPrice());
		System.out.println(lineItem);
	}

}
