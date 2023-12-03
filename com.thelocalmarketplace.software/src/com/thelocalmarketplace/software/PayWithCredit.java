package com.thelocalmarketplace.software;


import java.util.Scanner;

import com.jjjwelectronics.card.Card.CardSwipeData;
import com.thelocalmarketplace.hardware.external.CardIssuer;

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

public class PayWithCredit {

    private boolean identity = false;
    private StartSession session;
    
    public PayWithCredit(StartSession session) {
    	this.session = session;
    }
    
    public CardIssuer selectCardType() {
        Scanner scanner = new Scanner(System.in);
        int cardTypeInt;

        do {
            System.out.println("Enter the number that corresponds to the type of card you have:");
            System.out.println("1 for Mastercard");
            System.out.println("2 for Visa");
            System.out.println("3 for American Express");

            // Line to ensure input is a number of type int
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            // Ensure that the cardTypeInt is a value between 1-3, the possible options
            cardTypeInt = scanner.nextInt();

            if (cardTypeInt < 1 || cardTypeInt > 3) {
                System.out.println("Invalid option. Please select one of the options.");
            }
        } while (cardTypeInt < 1 || cardTypeInt > 3);

        String selectedCard;

        // Switch statement to turn int value into a string for more eligible work
        switch (cardTypeInt) {
            case 1:
                selectedCard = "Visa";
                break;
            case 2:
                selectedCard = "Mastercard";
                break;
            case 3:
                selectedCard = "American Express";
                break;
            default:
                selectedCard = "Unknown";
                break;
        }

        CardIssuer bank = new CardIssuer(selectedCard, 5);
        return bank;
    }

    public void verifyCardHolder(String signature, CardSwipeData card) {
        if (signature.equals(card.getCardholder())) {
            identity = true;
        }
    }

    public void sendMessage(CardSwipeData card, CardIssuer bank, long amount) {
        if (identity) { // I found no way to send the signature
            boolean cardRead = false;

            while (!cardRead) {
                System.out.println("Please swipe again");
                cardRead = bank.block(card.getNumber());
            }

            bank.unblock(card.getNumber());
            long holdNumber = bank.authorizeHold(card.getNumber(), amount); // Part 4 of the use case
            boolean postAmount = bank.releaseHold(card.getNumber(), holdNumber);

            if (postAmount) {
                boolean successful = bank.postTransaction(card.getNumber(), holdNumber, amount);

                if (successful) {
                    System.out.println("Remaining balance: " + (session.getTotalPrice() - amount));
                }
            }
        }
    }

    public void payByCredit(CardSwipeData card, CardIssuer bank) {
        long amountDue = session.getTotalPrice();
        System.out.println("Please enter your signature");
        Scanner signatureScanner = new Scanner(System.in);
        String thisSignature = signatureScanner.next();

        verifyCardHolder(thisSignature, card);
        sendMessage(card, bank, amountDue);
    }

    public void paymentInProgress(CardSwipeData card) {
        payByCredit(card, selectCardType());
    }
}
