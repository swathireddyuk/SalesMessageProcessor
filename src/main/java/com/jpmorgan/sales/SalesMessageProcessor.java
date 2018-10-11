package com.jpmorgan.sales;

import java.io.BufferedReader;
import java.io.FileReader;

import com.jpmorgan.sales.operations.Sales;

/**
 * This class is for handling the sales notification messages to be processed
 * @author Swathi N
 *
 */
public class SalesMessageProcessor {
	public static void main(String[] args) {
		// Initiate the sale object
		Sales sale = new Sales();

		// Read inputs from test resources file
		try {
			String salesMsg;
			@SuppressWarnings("resource")
			BufferedReader inputFile = new BufferedReader(new FileReader("src/test/resources/salesnotifications.txt"));
			while (null != (salesMsg = inputFile.readLine())) {
				System.out.println("sales message :: "+ salesMsg);
				// process message for each sales message notification
				sale.processNotification(salesMsg);
				// report generates after every 10th iteration and stops on 50th iteration
				sale.salesReport.report();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
