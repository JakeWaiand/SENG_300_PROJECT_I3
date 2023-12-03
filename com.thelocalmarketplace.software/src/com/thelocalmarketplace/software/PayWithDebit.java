package com.thelocalmarketplace.software;

import com.jjjwelectronics.card.Card;
import com.jjjwelectronics.card.Card.CardSwipeData;
import com.thelocalmarketplace.hardware.external.CardIssuer;

import java.util.Scanner;

public class PayWithDebit {

    private boolean identity = false;
    private StartSession session;
    
    public PayWithDebit(StartSession session) {
    	this.session = session;
    }
    
    public boolean getIdentity() {
    	return identity;
    }

    public CardIssuer selectCardType() {
        Scanner scanner = new Scanner(System.in);
        int cardTypeInt;

        do {
            System.out.println("Enter the number that corresponds to type of card you have:");
            System.out.println("1 for Mastercard");
            System.out.println("2 for Visa");
            System.out.println("3 for American Express");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            cardTypeInt = scanner.nextInt();

            if (cardTypeInt < 1 || cardTypeInt > 3) {
                System.out.println("Invalid option. Please select one of the options.");
            }
        } while (cardTypeInt < 1 || cardTypeInt > 3);

        String selectedCard;

        switch (cardTypeInt) {
            case 1:
                selectedCard = "Mastercard";
                break;
            case 2:
                selectedCard = "Visa";
                break;
            case 3:
                selectedCard = "American Express";
                break;
            default:
                selectedCard = "Unknown";
        }

        CardIssuer bank = new CardIssuer(selectedCard, 5);
        return bank;
    }

    public void verifyCardHolder(String signature, CardSwipeData card) {
        if (signature.equals(card.getCardholder())) {
            identity = true;
        }
    }

    public void sendMessage(CardSwipeData card, CardIssuer bank, double amount) {
        if (identity) {
            boolean cardRead = false;

            while (!cardRead) {
                System.out.println("please swipe again");
                cardRead = bank.block(card.getNumber());
            }

            bank.unblock(card.getNumber());
            long holdNumber = bank.authorizeHold(card.getNumber(), amount);
            boolean postAmount = bank.releaseHold(card.getNumber(), holdNumber);

            if (postAmount) {
                boolean successful = bank.postTransaction(card.getNumber(), holdNumber, amount);

                if (successful) {
                    System.out.println("Remaining balance: " + (session.getTotalPrice() - amount));
                }
            }
        }
    }

    public void payByDebit(CardSwipeData card, CardIssuer bank) {
        double amountDue = session.getTotalPrice();
        System.out.println("please enter your signature");
        Scanner signatureScanner = new Scanner(System.in);
        String userSignature = signatureScanner.nextLine();

        verifyCardHolder(userSignature, card);
        sendMessage(card, bank, amountDue);
    }

    public void payment_in_process(CardSwipeData card) {
        CardIssuer bank = selectCardType();
        payByDebit(card, bank);
    }
}
