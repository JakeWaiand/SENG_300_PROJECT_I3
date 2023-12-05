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

import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;

import ca.ucalgary.seng300.simulation.InvalidStateSimulationException;

import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;

import com.jjjwelectronics.OverloadedDevice;

public class MaintainInk {

	private final int GOLD = 2;
	private final int SILVER = 1;
	private final int BRONZE = 0;
	
	private int stationGrade;
	
	private AbstractSelfCheckoutStation station;
	
	/**
	 * Constructor using a station to allow an attendant to simulate
	 * maintaining available ink for the receipt printer.
	 * @param usedStation
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
			throw new InvalidStateSimulationException("Station must be disabled.");
		}
	}
}
