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

public class MaintainPaper {

	private final int GOLD = 2;
	private final int SILVER = 1;
	private final int BRONZE = 0;
	
	private int stationGrade;
	
	private AbstractSelfCheckoutStation station;
	
	/**
	 * Constructor using a station to allow an attendant to simulate
	 * maintaining available paper for the receipt printer.
	 * @param usedStation
	 *    Any particular self checkout station.
	 */
	public MaintainPaper(AbstractSelfCheckoutStation usedStation) {
		station = usedStation;
		
		if (usedStation instanceof SelfCheckoutStationBronze) {
			stationGrade = BRONZE;
		} else if (usedStation instanceof SelfCheckoutStationSilver) {
			stationGrade = SILVER;
		} else if (usedStation instanceof SelfCheckoutStationGold) {
			stationGrade = GOLD;
		}
	}
	
	public void addPaper(int quantity) throws OverloadedDevice {

		if (station.getPrinter().isDisabled()) {
			
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
		} else {
			throw new InvalidStateSimulationException("Station must be disabled.");
		}
	}
	
}
