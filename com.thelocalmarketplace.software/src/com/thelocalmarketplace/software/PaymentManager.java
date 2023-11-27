package com.thelocalmarketplace.software;

import com.tdc.coin.Coin;

public class PaymentManager {
    public boolean enabled;

	public float amountDue = 0;
	public float amountPaid = 0;
	private PayWithCoin payWithCoin;


	// initialize payment manager - JW
	public PaymentManager() {
		System.out.println("Payment Manager has been created");
		payWithCoin = new PayWithCoin(this);
	}

	// takes the coin inserted and uses the PaymentManager - JW
	public void addPayment(Coin newCoin) {
		payWithCoin.insertCoin(newCoin);
		if (amountPaid >= amountDue) {
			// implement print receipt here
		}
	}

}
