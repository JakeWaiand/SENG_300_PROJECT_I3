package com.thelocalmarketplace.software;

import java.util.List;

import com.tdc.CashOverloadException;
import com.tdc.banknote.Banknote;
import com.tdc.coin.Coin;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;

import ca.ucalgary.seng300.simulation.InvalidStateSimulationException;
import ca.ucalgary.seng300.simulation.SimulationException;

import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;

public class MaintainBanknotes {

	private final int GOLD = 2;
	private final int SILVER = 1;
	private final int BRONZE = 0;
	
	private int stationGrade;
	
	private AbstractSelfCheckoutStation station;
	
	/**
	 * Constructor using a station to allow an attendant to simulate
	 * maintaining the amount of banknotes in the banknote storage unit
	 * @param usedStation
	 *     Any particular self checkout station
	 */
	public MaintainBanknotes(AbstractSelfCheckoutStation usedStation) {
		station = usedStation;
	}
	
	/**
	 * Simulates manually adding banknotes to the storage unit
	 * @param banknotes
	 *     At least one banknote
	 * @throws CashOverloadException
	 *     If more banknotes are added than the storage unit can contain
	 */
	public void addBanknotes(Banknote... banknotes) throws CashOverloadException {
		if (station.getBanknoteStorage().isDisabled()) {
			station.getBanknoteStorage().load(banknotes);
		} else {
			throw new InvalidStateSimulationException("Station must be disabled.");
		}
	}
	
	/**
	 * Simulates manually removing banknotes to the storage unit
	 * @param quantity
	 *     The number of banknotes to be removed
	 * @return
	 *     The list of removed banknotes
	 * @throws CashOverloadException
	 *     If too many banknotes are somehow reloaded
	 */
	public List<Banknote> removeBanknotes(int quantity) throws CashOverloadException {
		List<Banknote> removedBanknotes;
		
		if ((station.getBanknoteStorage().isDisabled()) && (quantity < station.getBanknoteStorage().getBanknoteCount())) {
			
			removedBanknotes = station.getBanknoteStorage().unload();
			
			int returnQuantity = removedBanknotes.size() - quantity;
			
			for (int i = 0; i < returnQuantity; i++) {
				station.getBanknoteStorage().load(removedBanknotes.get(removedBanknotes.size() - 1));
			}
		} else if (quantity > station.getBanknoteStorage().getBanknoteCount()) {
			throw new InvalidStateSimulationException("Cannot take more coins out than are in the storage unit.");
		} else {
			throw new InvalidStateSimulationException("Station must be disabled.");
		}
		return removedBanknotes;
	}
}
