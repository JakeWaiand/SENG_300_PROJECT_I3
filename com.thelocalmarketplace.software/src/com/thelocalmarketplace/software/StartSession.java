package com.thelocalmarketplace.software;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
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
	private AbstractSelfCheckoutStation station;
	private AbstractElectronicScale scale;
	private AbstractCardReader cardReader;
	private IBarcodeScanner handHeldScanner;
	private IBarcodeScanner mainScanner;
	private boolean isActive;
	private boolean activeSession;
	private BanknoteInsertionSlot cashSlot;
	private PayWithCash cashListener; 
	private boolean paymentSuccessful = false;
	private ItemProcessingControl itemControl;
	private WeightDiscrepancy WD;
	private PayWithDebit pay_debit;
	private PayWithCredit pay_Credit;
	private EScaleListenerImplement scaleListener;
	private BarcodeListenerImplement barcodeListener;
	private CardlistenerImplement cardListener;
	private AbstractElectronicScale scanScale;
	private ArrayList<String> pickedItems; //this can be used for print receipt
	private ArrayList<Long> priceList; //this can be used for print receipt
	private ArrayList<Mass> weightList;
	private long totalPrice; //this can be used for print receipt
	private Mass expectedWeight = new Mass(0);
	private AttendantControl attendantControl;
	

	
	
	
	private Currency cad = Currency.getInstance("CAD");
	private Banknote banknote5 = new Banknote(cad,new BigDecimal("5")); static Banknote banknote10 = new Banknote(cad,new BigDecimal("10")); static Banknote banknote20 = new Banknote(cad,new BigDecimal("20")); static Banknote banknote50 = new Banknote(cad,new BigDecimal("50")); static Banknote banknote100 = new Banknote(cad,new BigDecimal("100"));
	private BigDecimal[] banknoteDenominations = {new BigDecimal("5"),new BigDecimal("10"),new BigDecimal("20"),new BigDecimal("50"),new BigDecimal("100")};
	private Banknote[] banknotes = {banknote5, banknote10, banknote20 ,banknote50, banknote100};
	
	private Coin coin1 = new Coin(cad,new BigDecimal("0.01")); static Coin coin5 = new Coin(cad,new BigDecimal("0.05")); static Coin coin10 = new Coin(cad,new BigDecimal("0.10")); static Coin coin25 = new Coin(cad,new BigDecimal("0.25")); static Coin coin100 = new Coin(cad,new BigDecimal("1")); static Coin coin200 = new Coin(cad,new BigDecimal("2"));
	private BigDecimal[] coinDenominations = {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1"), new BigDecimal("2")};
	private Coin[] coins = {coin5,coin10,coin25,coin100,coin200};
	
	private BanknoteInsertionSlot banknoteSlot = new BanknoteInsertionSlot();
	private BanknoteValidator banknoteValidator = new BanknoteValidator(cad,banknoteDenominations);
	//BanknoteStorageUnit banknoteStorage = new BanknoteStorageUnit();
	
	private CoinSlot coinSlot = new CoinSlot();
	private CoinValidator coinValidator = new CoinValidator(cad,Arrays.asList(coinDenominations));
	
	
	
	
	public StartSession(AbstractSelfCheckoutStation input_station) throws OverloadedDevice {
		setStation(input_station);
		setScale((AbstractElectronicScale)station.getBaggingArea());
		cardReader = (AbstractCardReader)station.getCardReader();
		setHandHeldScanner(station.getHandheldScanner());
		setMainScanner(station.getMainScanner());
		setScanScale((AbstractElectronicScale) station.getScanningArea());
		station.turnOn();
		
		setWD(new WeightDiscrepancy(this));
		setItemControl(new ItemProcessingControl(this));
		pay_debit = new PayWithDebit();
		pay_Credit = new PayWithCredit();
		setScaleListener(new EScaleListenerImplement(this));
		barcodeListener = new BarcodeListenerImplement(this);
		cardListener = new CardlistenerImplement();
		getScale().register(getScaleListener());
		cardReader.register(cardListener);
		getHandHeldScanner().register(barcodeListener);
		getMainScanner().register(barcodeListener);
		cashSlot.attach(cashListener);
		isActive = true;
		getScanScale().register(getScaleListener());
		displaySplashScreen();
		listenForInput();
		
		
		input_station.configureBanknoteDenominations(banknoteDenominations);
		input_station.configureCoinDenominations(coinDenominations);
		input_station.configureCurrency(cad);
		
		
		
		
	}
	
        
   
    public void displaySplashScreen() throws OverloadedDevice {
        System.out.println("Press anywhere to start");
        getHandHeldScanner().enable();
        getMainScanner().enable();
        cardReader.enable();
        getScale().enable();
        cashSlot.enable();
        station.getCoinSlot().enable();
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
    	 getHandHeldScanner().disable();
         getMainScanner().disable();
         cardReader.disable();
         getScale().disable();
         cashSlot.disable();
         station.getCoinSlot().disable();   //changes need to be made
         station.getScanningArea().disable();
        
    }
	



	public AbstractSelfCheckoutStation getStation() {
		return station;
	}



	public void setStation(AbstractSelfCheckoutStation station) {
		this.station = station;
	}



	public ItemProcessingControl getItemControl() {
		return itemControl;
	}



	public void setItemControl(ItemProcessingControl itemControl) {
		this.itemControl = itemControl;
	}



	public AbstractElectronicScale getScanScale() { // for the plu items
		return scanScale;
	}



	public void setScanScale(AbstractElectronicScale scanScale) {
		this.scanScale = scanScale;
	}



	public AbstractElectronicScale getScale() {
		return scale;
	}



	public void setScale(AbstractElectronicScale scale) {
		this.scale = scale;
	}



	public IBarcodeScanner getHandHeldScanner() {
		return handHeldScanner;
	}



	public void setHandHeldScanner(IBarcodeScanner handHeldScanner) {
		this.handHeldScanner = handHeldScanner;
	}



	public IBarcodeScanner getMainScanner() {
		return mainScanner;
	}



	public void setMainScanner(IBarcodeScanner mainScanner) {
		this.mainScanner = mainScanner;
	}





	public ArrayList<String> getPickedItems() {
		return pickedItems;
	}



	public void setPickedItems(ArrayList<String> pickedItems) {
		this.pickedItems = pickedItems;
	}



	public long getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}



	public ArrayList<Long> getPriceList() {
		return priceList;
	}



	public void setPriceList(ArrayList<Long> priceList) {
		this.priceList = priceList;
	}



	public Mass getExpectedWeight() {
		return expectedWeight;
	}



	public void setExpectedWeight(Mass expectedWeight) {
		this.expectedWeight = expectedWeight;
	}



	public ArrayList<Mass> getWeightList() {
		return weightList;
	}



	public void setWeightList(ArrayList<Mass> weightList) {
		this.weightList = weightList;
	}



	public WeightDiscrepancy getWD() {
		return WD;
	}



	public void setWD(WeightDiscrepancy wD) {
		WD = wD;
	}



	public EScaleListenerImplement getScaleListener() {
		return scaleListener;
	}



	public void setScaleListener(EScaleListenerImplement scaleListener) {
		this.scaleListener = scaleListener;
	}



	public AttendantControl getAttendantControl() {
		return attendantControl;
	}



	public void setAttendantControl(AttendantControl attendantControl) {
		this.attendantControl = attendantControl;
	}

	
	
	
}
