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

public class MaintainInk {

	private final int GOLD = 2;
	private final int SILVER = 1;
	private final int BRONZE = 0;
	
	private int stationGrade;
	
	private AbstractSelfCheckoutStation station;
	
	/**
	 * Constructor using a station.
	 * @param newStation
	 *    Any particular self checkout station.
	 */
	public MaintainInk(AbstractSelfCheckoutStation usedStation) {
		station = usedStation;
		
		if (usedStation instanceof SelfCheckoutStationBronze) {
			stationGrade = BRONZE;
		} else if (usedStation instanceof SelfCheckoutStationSilver) {
			stationGrade = SILVER;
		} else if (usedStation instanceof SelfCheckoutStationGold) {
			stationGrade = GOLD;
		}
	}
	
	/**
	 * Adds ink to the printer. Simulates an attendant filling the printer with ink,
	 * calls 
	 * @param quantity
	 *     amount of ink poured into the printer
	 * @throws OverloadedDevice
	 *     thrown if it is determined that the amount of ink being loaded exceeds
	 *     the ink already in the printer
	 */
	public void addInk(int quantity) throws OverloadedDevice {
		
		if (station.getPrinter().isDisabled()) {
			
			switch(stationGrade) {
				case BRONZE:
					station.getPrinter().addInk(quantity);
				case SILVER:
					//not so sure with this one. the amount of ink determined is supposedly inaccurate,
					//so idk if i'm supposed to work around that or how
					if (station.getPrinter().inkRemaining() < quantity) {
						station.getPrinter().addInk(quantity);
					} else {
						throw new OverloadedDevice("Added too much ink.");
					}
				case GOLD:
					if (station.getPrinter().inkRemaining() < quantity) {
						station.getPrinter().addInk(quantity);
					} else {
						throw new OverloadedDevice("Added too much ink.");
					}
			}
		} else {
			//throw an exception
		}
	}
}
