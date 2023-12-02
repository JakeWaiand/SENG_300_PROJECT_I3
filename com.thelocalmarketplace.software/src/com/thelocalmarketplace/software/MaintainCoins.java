package com.thelocalmarketplace.software;

import com.tdc.CashOverloadException;
import com.tdc.coin.Coin;

import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;

public class MaintainCoins {
	
	private final int GOLD = 2;
	private final int SILVER = 1;
	private final int BRONZE = 0;
	
	private int stationGrade; //idk if i even need this, coin storage seems to be the same regardless of station type
	
	private AbstractSelfCheckoutStation station;
	
	//general constructor
	public MaintainCoins(AbstractSelfCheckoutStation usedStation) {
		station = usedStation;
		
		if (usedStation instanceof SelfCheckoutStationBronze) {
			stationGrade = BRONZE;
		} else if (usedStation instanceof SelfCheckoutStationSilver) {
			stationGrade = SILVER;
		} else if (usedStation instanceof SelfCheckoutStationGold) {
			stationGrade = GOLD;
		}
	}
	
	//add coins
	//the ... means you can use more than one of that object
	public void addCoins(Coin... coins) throws CashOverloadException {
		if (station.getCoinStorage().isDisabled()) {
			station.getCoinStorage().load(coins);
		} else {
			//throw an exception
		}
	}
	
	//remove coins
	public void removeCoins() {
		if (station.getCoinStorage().isDisabled()) {
			//how do i do this without dumping the whole thing, can't use emit in coindispenser because it needs to be enabled
			//also needs to somehow determine how many it needs (which i've never seen specified anywhere. 
			//i want someone to bash my brains in with a sledgehammer
		} else {
			//throw an exception
		}
	}
}
