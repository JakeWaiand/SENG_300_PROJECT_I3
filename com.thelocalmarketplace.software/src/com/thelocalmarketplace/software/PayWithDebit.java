/*
 * Dongwen Tian			 30181813
 *Fardin Rahman Sami             30172916
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

package com.thelocalmarketplace.software;

import com.jjjwelectronics.card.Card;
import com.jjjwelectronics.card.Card.CardInsertData;
import com.jjjwelectronics.card.Card.CardSwipeData;
import com.jjjwelectronics.card.Card.CardTapData;
import com.thelocalmarketplace.hardware.external.CardIssuer;

import java.io.IOException;
import java.util.Scanner;

public class PayWithDebit {

    private static boolean identity = false;
    private static StartSession session;
    
    public PayWithDebit(StartSession session) {
    	this.session = session;
    }
    
    public boolean getIdentity() {
    	return identity;
    }

    public static CardIssuer selectCardType() {
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

    public static void verifyCardHolder(String signature, Card card) {
        if (signature.equals(card.cardholder)) {
            identity = true;
        }
        else {
        	identity = false;
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
            
            /*
            while (!cardRead) {
                System.out.println("please swipe again");
                cardRead = bank.block(card.getNumber());
            }
            */

            //bank.unblock(card.getNumber());
            
            long holdNumber = bank.authorizeHold(card.getNumber(), amount);
            boolean postAmount = bank.releaseHold(card.getNumber(), holdNumber);

            if (postAmount) {
                boolean successful = bank.postTransaction(card.getNumber(), holdNumber, amount);

                if (successful) {
                    System.out.println("Remaining balance: " + (session.getTotalPrice() - amount));
                }
            } else {
            	identity = false;
            }
        }
    }

    public static void PayByDebitSwipe(Card card, CardIssuer bank) throws IOException {
        double amountDue = session.getTotalPrice();

        System.out.println("please enter your signature");
        Scanner signatureScanner = new Scanner(System.in);
        String userSignature = signatureScanner.nextLine();

        verifyCardHolder(userSignature, card);
        
        session.getStation().getCardReader().swipe(card);
        
        sendMessage(card.swipe(), bank, amountDue);
    }

    public static void swipePayment(Card card) throws IOException {
        CardIssuer bank;
		
			bank = selectCardType();
        PayByDebitSwipe(card, bank);
    }
    
    
    
    
    
    
    
    
	public static void sendMessage(Card card, CardIssuer bank, double amount) {
		 bank.unblock(card.number);
        long holdNumber = bank.authorizeHold(card.number, amount);
        boolean postAmount = bank.releaseHold(card.number, holdNumber);

        if (postAmount) {
            boolean successful = bank.postTransaction(card.number, holdNumber, amount);

            if (successful) {
                System.out.println("Remaining balance: " + (session.getTotalPrice() - amount));
            }
        }
		
	}
    
    
    public static void PayByDebitTap(Card card, CardIssuer bank) throws IOException {
        double amountDue = session.getTotalPrice();
        session.getStation().getCardReader().tap(card);
        sendMessage(card, bank, amountDue);
    }

    public static void tapPayment(Card card) throws IOException {
        CardIssuer bank = selectCardType();
        PayByDebitTap(card, bank);
    }
	
    
    
    
    
    
    
    public static void sendMessage(CardInsertData card, CardIssuer bank, double amount) {
            bank.unblock(card.getNumber());
            long holdNumber = bank.authorizeHold(card.getNumber(), amount);
            boolean postAmount = bank.releaseHold(card.getNumber(), holdNumber);

            if (postAmount) {
                boolean successful = bank.postTransaction(card.getNumber(), holdNumber, amount);

                if (successful) {
                    System.out.println("Remaining balance: " + (session.getTotalPrice() - amount));
                }
            }
            session.getStation().getCardReader().remove();
		
	}
    
    public static void PayByDebitInsert(Card card, CardIssuer bank) throws IOException {
    	 double amountDue = session.getTotalPrice();
    	 System.out.println("please enter your PIN");
         Scanner pinScanner = new Scanner(System.in);
         String userPin = pinScanner.next();
         session.getStation().getCardReader().insert(card, userPin);
         sendMessage((CardInsertData) session.getStation().getCardReader().insert(card, userPin),bank,amountDue);
	}


	public static void insertPayment(Card card) throws IOException {
        CardIssuer bank = selectCardType();
        PayByDebitInsert(card, bank);
    }

	
    
    
    
}
