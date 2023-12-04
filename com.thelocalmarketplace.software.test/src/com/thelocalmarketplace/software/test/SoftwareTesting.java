 package com.thelocalmarketplace.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.Mass;
import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.BarcodedItem;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.software.StartSession;

import powerutility.PowerGrid;

/*
Kimih Yan 30160567
Kenny Zeng 30151985 
Daniel Adebisi 30179418
Kourosh Malayeri 30174987
Tahamina Chowdhury 30140920
Firdovsi Aliyev 30178471
Hasan Qasim 30164530
Yasna Naseri  30182402
Muhammad Niazi 30177775
Yasir Hussain 30195085
Almik biju 30170902 
*/

/* 
 * Software Testing for The Local Market Place Software
 * 
 * I think  
 * 
 * */


public class SoftwareTesting {
	
	StartSession session;
	ArrayList<BarcodedItem> orderItems;
	Numeral[] testCode = {Numeral.one,Numeral.two,Numeral.three,Numeral.four};
	Barcode testBarcode = new Barcode(testCode);
	AbstractSelfCheckoutStation selfCheckoutStation = new SelfCheckoutStationBronze();
	double testWeight = 5.0;

	Mass testMass = new Mass(2L);
	double marginOfError = 0.1;

	
	@Before
	public void setup() {
		session = Session.getInstance();
		orderItems = new ArrayList<BarcodedItem>();
		selfCheckoutStation.plugIn(PowerGrid.instance());
		selfCheckoutStation.turnOn();
	
	}
	
	//Testing for Session Class
	
	//Tests if the session returns true for isActive() method after activate() method is called
	@Test
	public void sessionIsActiveTest() {
		session.activate();
		assertTrue(session.isActive());
	}
	
	//Tests if the session returns false for isActive() method after deactivate() method is called
	@Test
	public void sessionIsNotActiveTest() {
		session.deactivate();
		assertFalse(session.isActive());
	}
	
	//Test to see if a new item is added to orderItems when newOrderItem is called
	@Test
	public void newOrderedItemTest() {
		BarcodedItem testBarcodedItem = new BarcodedItem(testBarcode, new Mass(2L));
		session.newOrderItem(testBarcodedItem);
		orderItems.add(testBarcodedItem);
		assertEquals(orderItems,session.getOrderItem());
	}
	
	// Exception handling needed in code for 
	// adding a null item, should result in a null pointer exception
//	@Test(expected = NullPointerException.class)
//	public void newOrderedNullItemTest() {
//		BarcodedItem testBarcodedItem = null;
//		session.newOrderItem(testBarcodedItem);
//		orderItems.add(testBarcodedItem);
//		session.getOrderItem();
//	} 
	
	//Test to see if a addTotalExcpectedWeight updates when weight is added
	@Test
	public void addTotalExpectedWeightTest() {
		session.addTotalExpectedWeight(testWeight);
		assertEquals(testWeight, session.getTotalExpectedWeight(), marginOfError);
	}
	
	//Test to see if a addAmountDue updates when amount is added
	@Test
	public void addAmountDueTest() {
		double testAmount = session.getAmountDue() + 1;
		session.addAmountDue(1);
		assertEquals(testAmount, session.getAmountDue(), marginOfError);
	}
	
	//Test to see if a addAmountDue updates when amount is subtracted
	@Test
	public void subAmountDueTest() {
		session.addAmountDue(3);
		session.subAmountDue(2);
		assertEquals(1, session.getAmountDue(), marginOfError);
	}
	
	//Test to see if setWeightDiscrepancy returns correct boolean value true
	@Test
	public void setWeightDiscrepancyTest() {
		session.setWeightDiscrepancy();
		assertTrue(session.hasWeightDiscrepancy());
	}
	
	//Test to see if setNoWeightDiscrepancy returns correct boolean value false
	@Test
	public void setNoWeightDiscrepancyTest() {
		session.setNoWeightDiscrepancy();
		assertFalse(session.hasWeightDiscrepancy());
	}
}