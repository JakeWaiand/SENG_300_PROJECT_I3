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

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tdc.CashOverloadException;
import com.tdc.coin.Coin;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.software.MaintainCoins;

import ca.ucalgary.seng300.simulation.InvalidStateSimulationException;
import powerutility.PowerGrid;

public class MaintainCoinsTest {

	private static AbstractSelfCheckoutStation testStation;
	private static MaintainCoins testMaintainCoins;
	private static Coin testCoin;
	private static Coin testCoin2;
	
	@BeforeClass
	public static void initialSetUp() {
		
		Coin.DEFAULT_CURRENCY = Currency.getInstance("CAD");
		AbstractSelfCheckoutStation.configureCoinStorageUnitCapacity(5);
		
		testStation = new SelfCheckoutStationGold();
		testStation.plugIn(PowerGrid.instance());
		testStation.turnOn();
		testStation.getCoinStorage().disable();
		
		testCoin = new Coin(new BigDecimal(1.00));
		testCoin2 = new Coin(new BigDecimal(1.00));
		
		testMaintainCoins = new MaintainCoins(testStation);		
		
	}
	
	@After
	public void removeCoins() {
		testStation.getCoinStorage().disable();
		testStation.getCoinStorage().unload();
	}
	
	/**
	 * Test whether coins are added to the coin storage unit.
	 */
	@Test
	public void addCoinTest() {
		
		try {
			testMaintainCoins.addCoins(testCoin, testCoin2);
		} catch (CashOverloadException e) {
			fail();
		}
		
		assertTrue(testStation.getCoinStorage().getCoinCount() == 2);
		
	}
	
	/**
	 * Test whether coins are added when enabled.
	 */
	@Test
	public void addCoinEnabledTest() {
		
		testStation.getCoinStorage().enable();
		try {
			testMaintainCoins.addCoins(testCoin, testCoin2);
		} catch (CashOverloadException e) {
			fail();
		} catch (InvalidStateSimulationException e2) {
			assertTrue(true);
		}
		
	}
	
	/**
	 * Test whether coins are removed.
	 */
	@Test
	public void removeCoinTest() {
		
		try {
			testMaintainCoins.addCoins(testCoin, testCoin2);
			testMaintainCoins.removeCoins(1);
		} catch (CashOverloadException e) {
			fail();
		}
		
		assertTrue(testStation.getCoinStorage().getCoinCount() == 1);
		
	}
	
	/**
	 * Test whether coins are removed when the number of coins to be removed
	 * is greater than the number of coins in the storage.
	 */
	@Test
	public void removeCoinGreaterTest() {

		try {
			testMaintainCoins.addCoins(testCoin, testCoin2);
			testMaintainCoins.removeCoins(3);
		} catch (CashOverloadException e) {
			fail();
		} catch (InvalidStateSimulationException e2) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test whether coin are removed when the storage unit is enabled.
	 */
	@Test
	public void removeCoinEnabledTest() {
		
		testStation.getCoinStorage().enable();
		try {
			testMaintainCoins.addCoins(testCoin, testCoin2);
			testMaintainCoins.removeCoins(1);
		} catch (CashOverloadException e) {
			fail();
		} catch (InvalidStateSimulationException e2) {
			assertTrue(true);
		}
	}
	
}
