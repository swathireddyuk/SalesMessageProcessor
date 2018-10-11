package com.jpmorgan.sales.utils;

import org.junit.Assert;
import org.junit.Test;
/**
 * Test class to test the Message Parser
 * @author Swathi N
 *
 */
public class MessageParserTest {
	
	@Test
	public void test_message_parser_parse_message_type1() {
		MessageParser msg = new MessageParser("apple at 10p"); 
		Assert.assertTrue(msg.getProductType().equals("apples"));
	}
	
	@Test
	public void test_message_parser_parse_message_type2() {
		MessageParser msg = new MessageParser("20 sales of apples at 10p each"); 
		Assert.assertTrue(msg.getProductType().equals("apples"));
		Assert.assertTrue(msg.getOperatorType().equals(""));
	}
	
	@Test
	public void test_message_parser_parse_message_type3() {
		MessageParser msg = new MessageParser("Add 20p apples"); 
		Assert.assertTrue(msg.getOperatorType().equals("Add"));
		Assert.assertTrue(msg.getProductType().equals("apples"));
	}
	
	@Test
	public void test_message_parser_wrong_sales_message() {
		MessageParser msg = new MessageParser("abcdefgh"); 
		Assert.assertTrue(msg.getOperatorType().equals(""));
		Assert.assertTrue(msg.getProductType().equals(""));
	}

}
