package com.thelocalmarketplace.software.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.card.Card;
import com.jjjwelectronics.card.Card.CardSwipeData;
import com.thelocalmarketplace.hardware.external.CardIssuer;
import com.thelocalmarketplace.software.PayWithDebit;

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

public class PayWithDebitTest {

    private PayWithDebit paymentProcessor;
    private CardIssuer bank;
    private Card visaCard;
    private Card mastercard;
    private Card americanExpressCard;
    private InputStream originalSystemIn;

    @Before
    public void setUp() {
        paymentProcessor = new PayWithDebit();
        bank = new CardIssuer("LocalBank", 5);
        visaCard = new Card("Visa", "1234 5678 9012 3456", "Card Holder", "123");
        mastercard = new Card("Mastercard", "2345 6789 0123 4567", "Card Holder", "456");
        bank.block(mastercard.getNumber());
        americanExpressCard = new Card("American Express", "3456 7890 1234 5678", "Card Owner", "789");
        americanExpressCard.setExpirationYear(2023);
        originalSystemIn = System.in;
    }

    @Test
    public void testSuccessfulDebitTransaction() {
        CardSwipeData cardSwipeData = visaCard.getCardSwipeData();
        System.setIn(new ByteArrayInputStream("Card Holder".getBytes()));
        paymentProcessor.PayByDebit(cardSwipeData, bank);
        assertTrue("Payment should be successful", paymentProcessor.identity);
    }

    @Test
    public void testInvalidCardholderSignature() {
        CardSwipeData cardSwipeData = visaCard.getCardSwipeData();
        System.setIn(new ByteArrayInputStream("Invalid Signature".getBytes()));
        paymentProcessor.PayByDebit(cardSwipeData, bank);
        assertFalse("Payment should fail with an invalid signature", paymentProcessor.identity);
    }

    @Test
    public void testBlockedCardTransaction() {
        CardSwipeData cardSwipeData = mastercard.getCardSwipeData();
        System.setIn(new ByteArrayInputStream("Card Holder".getBytes()));
        paymentProcessor.PayByDebit(cardSwipeData, bank);
        assertFalse("Payment should fail with a blocked card", paymentProcessor.identity);
    }

    @Test
    public void testExpiredCardTransaction() {
        CardSwipeData cardSwipeData = americanExpressCard.getCardSwipeData();
        System.setIn(new ByteArrayInputStream("Card Owner".getBytes()));
        paymentProcessor.PayByDebit(cardSwipeData, bank);
        assertFalse("Payment should fail with an expired card", paymentProcessor.identity);
    }
}