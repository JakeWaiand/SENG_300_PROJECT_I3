package com.thelocalmarketplace.software;

import java.util.ArrayList;

import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;

import com.jjjwelectronics.printer.ReceiptPrinterListener;
import com.jjjwelectronics.printer.ReceiptPrinterBronze;
import com.jjjwelectronics.printer.ReceiptPrinterSilver;
import com.jjjwelectronics.printer.ReceiptPrinterGold;

public class MaintainInk {

	private int GOLD = 2;
	private int SILVER = 1;
	private int BRONZE = 0;
	
	private int stationGrade;
	
	private SelfCheckoutStationBronze stationB;
	private SelfCheckoutStationSilver stationS;
	private SelfCheckoutStationGold stationG;
	
	public MaintainInk(SelfCheckoutStationBronze station) {
		this.stationB = station;
		this.stationGrade = BRONZE;
	}
	
	public MaintainInk(SelfCheckoutStationSilver station) {
		this.stationS = station;
		this.stationGrade = SILVER;
	}
	
	public MaintainInk(SelfCheckoutStationGold station) {
		this.stationG = station;
		this.stationGrade = GOLD;
	}
	
}
