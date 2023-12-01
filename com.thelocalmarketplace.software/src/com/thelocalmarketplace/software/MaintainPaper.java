package com.thelocalmarketplace.software;

import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;

import com.jjjwelectronics.OverloadedDevice;

import com.jjjwelectronics.printer.ReceiptPrinterListener;
import com.jjjwelectronics.printer.ReceiptPrinterBronze;
import com.jjjwelectronics.printer.ReceiptPrinterSilver;
import com.jjjwelectronics.printer.ReceiptPrinterGold;

public class MaintainPaper {

	private final int GOLD = 2;
	private final int SILVER = 1;
	private final int BRONZE = 0;
	
	private int stationGrade;
	
	private AbstractSelfCheckoutStation station;
	
	public MaintainPaper(AbstractSelfCheckoutStation usedStation) {
		this.station = usedStation;
		
		if (usedStation instanceof SelfCheckoutStationBronze) {
			stationGrade = BRONZE;
		} else if (usedStation instanceof SelfCheckoutStationSilver) {
			stationGrade = SILVER;
		} else if (usedStation instanceof SelfCheckoutStationGold) {
			stationGrade = GOLD;
		}
	}
	
	public void addPaper(int quantity) throws OverloadedDevice {
		switch(stationGrade) {
			case BRONZE:
				station.getPrinter().addPaper(quantity);
			case SILVER:
				//same question with the maintainink class
				if (station.getPrinter().paperRemaining() < quantity) {
					station.getPrinter().addPaper(quantity);
				} else {
					throw new OverloadedDevice("Added too much paper.");
				}
			case GOLD:
				if (station.getPrinter().paperRemaining() < quantity) {
					station.getPrinter().addPaper(quantity);
				} else {
					throw new OverloadedDevice("Added too much paper.");
				}
		}
	}
	
}
