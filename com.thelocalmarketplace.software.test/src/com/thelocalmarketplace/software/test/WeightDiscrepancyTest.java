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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.Item;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.BarcodedItem;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.software.StartSession;
import com.thelocalmarketplace.software.WeightDiscrepancy;

import powerutility.PowerGrid;



/**
 * Test class for weight discrepancy functions
 * 
 * @author Dongwen Tian
 *
 */

public class WeightDiscrepancyTest {
	private static WeightDiscrepancy weightDiscrep;
	private static AbstractSelfCheckoutStation testStation;
	private static StartSession testSession;
	
	private static Item testItem;

    
	@BeforeClass
	public static void initialSetUp() {

    	
	}
	
    @Before
    public void setUp() {
        // WeightDiscrepancy WeightDiscrepancy;
        // Initialize the necessary state
        // Other initializations if needed
    	testItem = new BarcodedItem(new Barcode(new Numeral[] {Numeral.one}), new Mass(20.0));
    	testStation = new SelfCheckoutStationBronze();
    	
    	testStation.plugIn(PowerGrid.instance());
    	testStation.turnOn();
    	
    	try {
			testSession = new StartSession(testStation);
		} catch (OverloadedDevice | EmptyDevice e) {} 
			
    	weightDiscrep = new WeightDiscrepancy(testSession);
    	
    }

    /**
     * Test what happens when the expected weight is the same as the actual weight
     */
    @Test
    public void sameWeightTest() {
    	testSession.getStation().getBaggingArea().addAnItem(testItem);
    	testSession.setExpectedWeight(new Mass(20.0));
    	try {
			weightDiscrep.evaluate();
		} catch (OverloadedDevice e) {
			fail();
		}
    	assertTrue(!weightDiscrep.isWeightDiscrepancy());
    	
    	testSession.getStation().getBaggingArea().removeAnItem(testItem);
    	
    }
    
    /**
     * Test what happens when the expected weight is greater than the actual weight
     */
    @Test
    public void greaterWeightTest() {
    	testSession.getStation().getBaggingArea().addAnItem(testItem);
    	testSession.setExpectedWeight(new Mass(30.0));
    	try {
			weightDiscrep.evaluate();
		} catch (OverloadedDevice e) {
			fail();
		}
    	assertTrue(weightDiscrep.isWeightDiscrepancy());
    	
    	testSession.getStation().getBaggingArea().removeAnItem(testItem);
    }
    
    /**
     * Test what happens when the expected weight is less than the actual weight
     */
    @Test
    public void lessWeightTest() {
    	testSession.getStation().getBaggingArea().addAnItem(testItem);
    	testSession.setExpectedWeight(new Mass(10.0));
    	try {
			weightDiscrep.evaluate();
		} catch (OverloadedDevice e) {
			fail();
		}
    	assertTrue(weightDiscrep.isWeightDiscrepancy());
    	
    	testSession.getStation().getBaggingArea().removeAnItem(testItem);
    	
    }
    
    @Test
    public void testSetWeightDiscrepancy() {
        weightDiscrep.set_weightDiscrepancy(true);
        assertTrue("Weight discrepancy should be true", weightDiscrep.isWeightDiscrepancy());

        weightDiscrep.set_weightDiscrepancy(false);
        assertFalse("Weight discrepancy should be false", weightDiscrep.isWeightDiscrepancy());
    }
    
    


}
