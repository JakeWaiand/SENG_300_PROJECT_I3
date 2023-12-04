package com.thelocalmarketplace.software;


import java.util.Scanner;

import com.jjjwelectronics.card.Card.CardInsertData;
import com.jjjwelectronics.card.Card.CardSwipeData;
import com.jjjwelectronics.card.Card.CardTapData;
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

Dongwen Tian 30181813
*/

public class PayWithCredit {

    private static boolean identity = false;
	private static StartSession session;
    public static CardIssuer selectCardType() {
    	
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

    public static void verifyCardHolder(String signature, CardSwipeData card) {
        if (signature.equals(card.getCardholder())) {
            identity = true;
        }
    }
    
    private static boolean verifyCardHolder(String pin, CardInsertData card) {
		if (pin.equals(card.getCVV())) {
			return true;
		}
		return false;
	}
    
    
    
    
    

    public static void sendMessage(CardSwipeData card, CardIssuer bank, double amount) {
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

    public static void PayByCreditSwipe(CardSwipeData card, CardIssuer bank) {
        double amountDue = Add_item.totalPrice;
        System.out.println("please enter your signature");
        Scanner signatureScanner = new Scanner(System.in);
        String userSignature = signatureScanner.next();

        verifyCardHolder(userSignature, card);
        sendMessage(card, bank, amountDue);
    }

    public static void swipePayment(CardSwipeData card) {
        CardIssuer bank = selectCardType();
        PayByCreditSwipe(card, bank);
    }
    
    
    
    
    
    
    
    
	public static void sendMessage(CardTapData card, CardIssuer bank, double amount) {
		 bank.unblock(card.getNumber());
        long holdNumber = bank.authorizeHold(card.getNumber(), amount);
        boolean postAmount = bank.releaseHold(card.getNumber(), holdNumber);

        if (postAmount) {
            boolean successful = bank.postTransaction(card.getNumber(), holdNumber, amount);

            if (successful) {
                System.out.println("Remaining balance: " + (Add_item.totalPrice - amount));
            }
        }
		
	}
    
    
    public static void PayByCreditTap(CardTapData card, CardIssuer bank) {
        double amountDue = Add_item.totalPrice;
        
        sendMessage(card, bank, amountDue);
    }

    public static void tapPayment(CardTapData card) {
        CardIssuer bank = selectCardType();
        PayByCreditTap(card, bank);
    }
	
    
    
    
    
    
    
    public static void sendMessage(CardInsertData card, CardIssuer bank, double amount) {

            bank.unblock(card.getNumber());
            long holdNumber = bank.authorizeHold(card.getNumber(), amount);
            boolean postAmount = bank.releaseHold(card.getNumber(), holdNumber);

            if (postAmount) {
                boolean successful = bank.postTransaction(card.getNumber(), holdNumber, amount);

                if (successful) {
                    System.out.println("Remaining balance: " + (Add_item.totalPrice - amount));
                }
            }
        
		
	}
    
    public static void PayByCreditInsert(CardInsertData card, CardIssuer bank) {
    	 double amountDue = Add_item.totalPrice;
    	 System.out.println("please enter your PIN");
         Scanner pinScanner = new Scanner(System.in);
         String userPin = pinScanner.next();
         if(verifyCardHolder(userPin, card)) {
        	 sendMessage(card, bank, amountDue);
         }
         else {
        	 System.out.print("Entered Pin is wrong");
         }

	}


	public static void insertPayment(CardInsertData card) {
        CardIssuer bank = selectCardType();
        PayByCreditInsert(card, bank);
    }
}
