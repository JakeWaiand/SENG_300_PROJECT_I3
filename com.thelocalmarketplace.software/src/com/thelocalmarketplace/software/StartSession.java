package com.thelocalmarketplace.software;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.io.BufferedReader;

import com.jjjwelectronics.Mass;
import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.card.AbstractCardReader;
import com.jjjwelectronics.scale.*;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.*;
import com.tdc.banknote.Banknote;
import com.tdc.banknote.BanknoteInsertionSlot;
import com.tdc.banknote.BanknoteValidator;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinDispenserGold;
import com.tdc.coin.CoinSlot;
import com.tdc.coin.CoinStorageUnit;
import com.tdc.coin.CoinValidator;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.CoinTray;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.external.ProductDatabases;

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

public class StartSession {
	public static AbstractSelfCheckoutStation station;
	public static String level;
	public static AbstractElectronicScale scale;
	public static AbstractCardReader cardReader;
	public static IBarcodeScanner handHeldScanner;
	public static IBarcodeScanner mainScanner;
	public static boolean isActive;
	public static boolean activeSession;
	public static BanknoteInsertionSlot cashSlot;
	public static PayWithCash cashListener; 
	public static boolean paymentSuccessful = false;
	
	
	
	public static Currency cad = Currency.getInstance("CAD");
	public static Banknote banknote5 = new Banknote(cad,new BigDecimal("5")); static Banknote banknote10 = new Banknote(cad,new BigDecimal("10")); static Banknote banknote20 = new Banknote(cad,new BigDecimal("20")); static Banknote banknote50 = new Banknote(cad,new BigDecimal("50")); static Banknote banknote100 = new Banknote(cad,new BigDecimal("100"));
	public static BigDecimal[] banknoteDenominations = {new BigDecimal("5"),new BigDecimal("10"),new BigDecimal("20"),new BigDecimal("50"),new BigDecimal("100")};
	public static Banknote[] banknotes = {banknote5, banknote10, banknote20 ,banknote50, banknote100};
	
	public static Coin coin1 = new Coin(cad,new BigDecimal("0.01")); static Coin coin5 = new Coin(cad,new BigDecimal("0.05")); static Coin coin10 = new Coin(cad,new BigDecimal("0.10")); static Coin coin25 = new Coin(cad,new BigDecimal("0.25")); static Coin coin100 = new Coin(cad,new BigDecimal("1")); static Coin coin200 = new Coin(cad,new BigDecimal("2"));
	public static BigDecimal[] coinDenominations = {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1"), new BigDecimal("2")};
	public static Coin[] coins = {coin5,coin10,coin25,coin100,coin200};
	
	public static BanknoteInsertionSlot banknoteSlot = new BanknoteInsertionSlot();
	public static BanknoteValidator banknoteValidator = new BanknoteValidator(cad,banknoteDenominations);
	//BanknoteStorageUnit banknoteStorage = new BanknoteStorageUnit();
	
	public static CoinSlot coinSlot = new CoinSlot();
	public static CoinValidator coinValidator = new CoinValidator(cad,Arrays.asList(coinDenominations));
	
	
	
	
	public StartSession(AbstractSelfCheckoutStation input_station) throws OverloadedDevice {
		station = input_station;
		scale = (AbstractElectronicScale)station.baggingArea;
		cardReader = (AbstractCardReader)station.cardReader;
		handHeldScanner = station.handheldScanner;
		mainScanner = station.mainScanner;
		station.turnOn();
		if (station instanceof SelfCheckoutStationBronze) {
			level = "bronze";
		}
		else if (station instanceof SelfCheckoutStationSilver) {
			level = "silver";
		}
		else if (station instanceof SelfCheckoutStationGold){
			level = "gold";
		}
		Add_item addItem = new Add_item();
		WeightDiscrepancy WD = new WeightDiscrepancy();
		PayWithDebit pay_debit = new PayWithDebit();
		PayWithCredit pay_Credit = new PayWithCredit();
		EScaleListenerImplement scaleListener = new EScaleListenerImplement();
		BarcodeListenerImplement barcodeListener = new BarcodeListenerImplement();
		CardlistenerImplement cardListener = new CardlistenerImplement();
		scale.register(scaleListener);
		cardReader.register(cardListener);
		handHeldScanner.register(barcodeListener);
		mainScanner.register(barcodeListener);
		cashSlot.attach(cashListener);
		isActive = true;
		displaySplashScreen();
		listenForInput();
		
		
		input_station.configureBanknoteDenominations(banknoteDenominations);
		input_station.configureCoinDenominations(coinDenominations);
		input_station.configureCurrency(cad);
		
		
		
		
	}
	
        
   
    public void displaySplashScreen() throws OverloadedDevice {
        System.out.println("Press anywhere to start");
        handHeldScanner.enable();
        mainScanner.enable();
        cardReader.enable();
        scale.enable();
        cashSlot.enable();
        station.coinSlot.enable();
    }
     public void listenForInput() throws OverloadedDevice {
        System.out.println("waiting......");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            br.readLine();
            System.out.println("Session started");
            while(isActive){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e){
            System.err.println("Error reading from user");
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return isActive;
    }
    
  
 
    public void endSession() throws OverloadedDevice {
    	 handHeldScanner.disable();
         mainScanner.disable();
         cardReader.disable();
         scale.disable();
         cashSlot.disable();
         station.coinSlot.disable();
        
    }
	
	

	
	
	
}
