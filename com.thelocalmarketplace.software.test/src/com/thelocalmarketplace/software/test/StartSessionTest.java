package com.thelocalmarketplace.software.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.OverloadedDevice;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.software.StartSession;

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

public class StartSessionTest {

    private SelfCheckoutStationBronze bronzeStation;
    private SelfCheckoutStationSilver silverStation;
    private SelfCheckoutStationGold goldStation;
    private StartSession startSession;

    @Before
    public void setUp() throws OverloadedDevice {
        bronzeStation = new SelfCheckoutStationBronze();
        silverStation = new SelfCheckoutStationSilver();
        goldStation = new SelfCheckoutStationGold();
    }

    @Test
    public void testStartSessionBronze() {
        try {
            startSession = new StartSession(bronzeStation);
            assertTrue(startSession.isActive());
            startSession.endSession();
            assertFalse(startSession.isActive());
        } catch (OverloadedDevice e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartSessionSilver() {
        try {
            startSession = new StartSession(silverStation);
            assertTrue(startSession.isActive());
            startSession.endSession();
            assertFalse(startSession.isActive());
        } catch (OverloadedDevice e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStartSessionGold() {
        try {
            startSession = new StartSession(goldStation);
            assertTrue(startSession.isActive());
            startSession.endSession();
            assertFalse(startSession.isActive());
        } catch (OverloadedDevice e) {
            e.printStackTrace();
        }
    }
}