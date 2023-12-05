/*
 * Dongwen Tian			 30181813
 * Fardin Rahman Sami            30172916
 * Kenny Zeng 			 30151985
 * Tahamina Chowdhury 	         30140920
 * Sneh Patel 			 30086076
 * Jake Waiand 			 30179510
 * Roko Condic 			 30185671
 * Farouq Arafeh		 30158214
 * K M Chisty 			 30145123
 * Mohammad Soomro 		 30130440
 * Daniel Adebisi 		 30179418
 * Eyuel Kahsay 		 30181884
 * Almik Biju 			 30170902
 * Kourosh Malayeri 	         30174987
 * Hasan Qasim 			 30164530
 * Ariba Noman 			 30111428
 * Kyuyop (Andrew) Park          10046592
 * Jiaqi Wu 			 30172397
 * Ludovik Chojnacki 	         30178890
 * Muhammad Niazi 		 30177775
 * Firdovsi Aliyev 		 30178471
 * Ratul Chakraborty	         30194422
 */

package com.thelocalmarketplace.software.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.OverloadedDevice;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.software.StartSession;

import powerutility.PowerGrid;



/**
 * Test class for startSession
 * 
 * @author Dongwen Tian
 *
 */

public class StartSessionTest {
	
	private AbstractSelfCheckoutStation testStation;
	private StartSession testSession;
	
	@BeforeClass
	public static void initialSetUp() {
		
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	/**
	 * Test whether the session is ended
	 */
	@Test
	public void endTest() {
		testStation = new SelfCheckoutStationGold();
		testStation.plugIn(PowerGrid.instance());
		testStation.turnOn();
		
		try {
			testSession = new StartSession(testStation);
		} catch (Exception e) {
			fail();
		}
		
		try {
			testSession.endSession();
		} catch (OverloadedDevice e) {
			fail();
		}
		assertTrue(!testSession.isActive());
	}
	
	/**
	 * Test whether a session is started when called using a gold station
	 */
	@Test
	public void goldTest() {
		testStation = new SelfCheckoutStationGold();
		testStation.plugIn(PowerGrid.instance());
		testStation.turnOn();
		
		try {
			testSession = new StartSession(testStation);
		} catch (Exception e) {
			fail();
		}
		assertTrue(testSession.isActive());
	}
	
	/**
	 * Test whether a session is started when called using a bronze station
	 */
	@Test
	public void bronzeTest() {
		testStation = new SelfCheckoutStationBronze();
		testStation.plugIn(PowerGrid.instance());
		testStation.turnOn();
		
		try {
			testSession = new StartSession(testStation);
		} catch (Exception e) {
			fail();
		}
		assertTrue(testSession.isActive());
	}
	
	/**
	 * Test whether a session is started when called using a silver station
	 */
	@Test
	public void silverTest() {
		testStation = new SelfCheckoutStationSilver();
		testStation.plugIn(PowerGrid.instance());
		testStation.turnOn();
		
		try {
			testSession = new StartSession(testStation);
		} catch (Exception e) {
			fail();
		}
		assertTrue(testSession.isActive());
	}

    
}
