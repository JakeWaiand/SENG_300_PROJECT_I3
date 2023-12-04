package com.thelocalmarketplace.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.OverloadedDevice;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.software.StartSession;
import com.thelocalmarketplace.software.WeightDiscrepancy;

/*
Firdovsi Aliyev 30178471
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

public class WeightDiscrepancyTest {
	private WeightDiscrepancy weightDiscrep;
	private AbstractSelfCheckoutStation testStation;
	private StartSession testSession;

    
    @Before
    public void setUp() {
        // WeightDiscrepancy WeightDiscrepancy;
        // Initialize the necessary state
        // Other initializations if needed
    	testStation = new SelfCheckoutStationBronze();
    	try {
			testSession = new StartSession(testStation);
		} catch (OverloadedDevice | EmptyDevice e) {} 
			
    	weightDiscrep = new WeightDiscrepancy(testSession);
    }

    @Test
    public void testSetWeightDiscrepancy() {
        weightDiscrep.set_weightDiscrepancy(true);
        assertTrue("Weight discrepancy should be true", weightDiscrep.isWeightDiscrepancy());

        weightDiscrep.set_weightDiscrepancy(false);
        assertFalse("Weight discrepancy should be false", weightDiscrep.isWeightDiscrepancy());
    }
    
    


}
