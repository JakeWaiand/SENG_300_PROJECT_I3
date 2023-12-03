package com.thelocalmarketplace.software.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.card.Card;
import com.jjjwelectronics.card.Card.CardSwipeData;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.external.CardIssuer;
import com.thelocalmarketplace.software.PayWithDebit;
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

Dongwen Tian 30181813
*/

public class PayWithDebitTest {

    private PayWithDebit paymentProcessor;
    private CardIssuer bank;
    private Card visaCard;
    private Card mastercard;
    private Card americanExpressCard;
    private InputStream originalSystemIn;
    
    private CardSwipeData cardSwipeData;
    
    private StartSession testSession;
    private AbstractSelfCheckoutStation testStation;

    @Before
    public void setUp() {
    	testStation = new SelfCheckoutStationGold();
    	try {
			testSession = new StartSession(testStation);
		} catch (OverloadedDevice | EmptyDevice e) {
			fail();
		}
    	
        paymentProcessor = new PayWithDebit(testSession);
        bank = new CardIssuer("LocalBank", 5);
        visaCard = new Card("Visa", "1234 5678 9012 3456", "Card Holder", "123", null, true, false);
        mastercard = new Card("Mastercard", "2345 6789 0123 4567", "Card Holder", "456", null, true, false);
        
        //bank.block(mastercard.getNumber());
        americanExpressCard = new Card("American Express", "3456 7890 1234 5678", "Card Owner", "789", null, true, false);
        
        //americanExpressCard.setExpirationYear(2023);
        originalSystemIn = System.in;
    }

    @Test
    public void testSuccessfulDebitTransaction() {
        //CardSwipeData cardSwipeData;
    	
		try {
			cardSwipeData = visaCard.swipe();
			//System.setIn(new ByteArrayInputStream("Card Holder".getBytes()));
	        paymentProcessor.payByDebit(cardSwipeData, bank);
			System.setIn(new ByteArrayInputStream("Card Holder".getBytes()));
		} catch (IOException e) {
			fail();
		}
        assertTrue("Payment should be successful", paymentProcessor.getIdentity());
    }

    @Test
    public void testInvalidCardholderSignature() {
    	//CardSwipeData cardSwipeData;
    	
    	try {
    		cardSwipeData = visaCard.swipe();
            System.setIn(new ByteArrayInputStream("Invalid Signature".getBytes()));
            paymentProcessor.payByDebit(cardSwipeData, bank);
    	} catch (IOException e) {
    		fail();
    	}
        
        assertFalse("Payment should fail with an invalid signature", paymentProcessor.getIdentity());
    }

    @Test
    public void testBlockedCardTransaction() {
    	//CardSwipeData cardSwipeData;
    	
    	try {
    		cardSwipeData = mastercard.swipe();
            System.setIn(new ByteArrayInputStream("Card Holder".getBytes()));
            paymentProcessor.payByDebit(cardSwipeData, bank);
    	} catch (IOException e) {
    		fail();
    	}
        
        assertFalse("Payment should fail with a blocked card", paymentProcessor.getIdentity());
    }

    @Test
    public void testExpiredCardTransaction() {
    	//CardSwipeData cardSwipeData;
    	
    	try {
	        CardSwipeData cardSwipeData = americanExpressCard.swipe();
	        System.setIn(new ByteArrayInputStream("Card Owner".getBytes()));
	        paymentProcessor.payByDebit(cardSwipeData, bank);
    	} catch (IOException e) {
    		fail();
    	}
        assertFalse("Payment should fail with an expired card", paymentProcessor.getIdentity());
    }
}