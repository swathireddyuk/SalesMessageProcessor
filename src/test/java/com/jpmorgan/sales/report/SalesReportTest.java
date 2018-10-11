package com.jpmorgan.sales.report;

import org.junit.Assert;
import org.junit.Test;

import com.jpmorgan.sales.entity.Product;

/**
 * This class to test Sales Reports
 * @author Swathi N
 *
 */
public class SalesReportTest {

	SalesReport report = new SalesReport();	
	
	@Test
	public void test_sales_report_With_10_normal_reports() {
		for(int i=0; i <=9; i++){
			report.setNormalReports(String.valueOf(i));
		}		
		report.report();		
		Assert.assertTrue(report.getAdjustmentReports().size() == 0);
		Assert.assertTrue(report.getNormalReports().size() == 10);
	}
	
	@Test
	public void test_sales_report_With_50_normal_reports() {
		for(int i=0; i <=49; i++){
			report.setNormalReports(String.valueOf(i));
		}		
		report.report();		
		Assert.assertTrue(report.getAdjustmentReports().size() == 0);
		Assert.assertTrue(report.getNormalReports().size() == 50);
	}
	
	@Test
	public void test_sales_report_With_1_normal_reports_do_not_log() {
		report.setNormalReports("1");	
		report.report();		
		Assert.assertTrue(report.getAdjustmentReports().size() == 0);
		Assert.assertTrue(report.getNormalReports().size() == 1);
	}
	
	@Test
	public void test_sales_report_format_reports() {
		Product product = new Product("apples");
		product.setProductType("apples");
		product.setProductQuantity(10);	
		product.setTotalQuantity(100);
		product.setTotalPrice(1000.0);
		report.formatReports("apples", product);	
		Assert.assertTrue(report.getTotalSalesValue() == 1000.0);
	}
}
