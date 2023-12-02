package com.thelocalmarketplace.software;

import com.tdc.CashOverloadException;
import com.tdc.banknote.Banknote;

import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;

public class MaintainBanknotes {

	private final int GOLD = 2;
	private final int SILVER = 1;
	private final int BRONZE = 0;
	
	private int stationGrade;
	
	private AbstractSelfCheckoutStation station;
	
	public MaintainBanknotes(AbstractSelfCheckoutStation usedStation) {
		station = usedStation;
	}
	
	public void addBanknotes(Banknote... banknotes) throws CashOverloadException {
		if (station.getBanknoteStorage().isDisabled()) {
			station.getBanknoteStorage().load(banknotes);
		} else {
			//throw an exception
		}
	}
	
	public void removeBanknotes() {
		if (station.getBanknoteStorage().isDisabled()) {
			
		} else {
			//throw an exception
		}
	}
}
