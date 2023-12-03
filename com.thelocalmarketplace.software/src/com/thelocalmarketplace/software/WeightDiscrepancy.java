package com.thelocalmarketplace.software;

import java.math.BigInteger;

import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.Mass.MassDifference;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.scale.AbstractElectronicScale;
import com.jjjwelectronics.scale.ElectronicScaleListener;
import com.jjjwelectronics.scale.IElectronicScale;
import com.thelocalmarketplace.hardware.*;

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

public class WeightDiscrepancy {
    private Mass startingWeight = new Mass(0);
    private Mass actualWeight;
    private boolean weightDiscrepancy = false;
    private AbstractSelfCheckoutStation station;
    private AbstractElectronicScale scale;
    private boolean weightExcess;
    private StartSession session;
    private Mass purchasedBagsWeight;

    public WeightDiscrepancy(StartSession session) {
        this.session = session;
    }

    public void evaluate() throws OverloadedDevice {
        MassDifference difference = scale.getCurrentMassOnTheScale().difference(session.getExpectedWeight());
        Mass absDifference = difference.abs();
        if (absDifference.compareTo(scale.getSensitivityLimit()) == -1) {
            setWeightDiscrepancy(false);
        } else if (scale.getCurrentMassOnTheScale().compareTo(session.getExpectedWeight()) == 1) {
            setWeightDiscrepancy(true);
            System.out.println("Unexpected item in bagging area. Please remove the item before continuing.");
        } else if (scale.getCurrentMassOnTheScale().compareTo(session.getExpectedWeight()) == -1) {
            setWeightDiscrepancy(true);
            System.out.println("Please put the item in the bagging area.");
        }
    }

    public void exceedWeightEvaluate() {
        setWeightExcess(true);
        while (isWeightExcess()) {
            System.out.println("The scale has exceeded its limit. Please remove the last item you added.");
        }
    }

    public void disableInteractions(AbstractSelfCheckoutStation station) {
        // Disable interactions with various station components
        station.getBanknoteInput().disable();
        station.getCardReader().disable();
        station.getCoinSlot().disable();
        station.getMainScanner().disable();
        station.getHandheldScanner().disable();
        station.getScanningArea().disable();
    }

    public void enableInteractions(AbstractSelfCheckoutStation station) {
        // Enable interactions with various station components
        station.getBanknoteInput().enable();
        station.getCardReader().enable();
        station.getCoinSlot().enable();
        station.getMainScanner().enable();
        station.getHandheldScanner().enable();
        station.getScanningArea().enable();
    }

    public void removeLastItem() {
        if (session.getPickedItems().isEmpty()) {
            System.out.println("No items to remove.");
            return;
        }

        // Retrieve details of the last added item
        long lastItemPrice = session.getPriceList().remove(session.getPriceList().size() - 1);
        Mass lastItemWeight = new Mass(session.getWeightList().get(session.getWeightList().size() - 1).inGrams().doubleValue());

        // Remove the last item from the list
        session.getPickedItems().remove(session.getPickedItems().size() - 1);

        // Update the expectedWeight and total price
        session.setExpectedWeight(session.getExpectedWeight().difference(lastItemWeight).abs());
        session.setTotalPrice(session.getTotalPrice() - lastItemPrice);

        System.out.println("Last item removed. Updated expectedWeight: " + session.getExpectedWeight());
        System.out.println("Updated total price: " + session.getTotalPrice());
    }

    public boolean isWeightDiscrepancy() {
        return weightDiscrepancy;
    }

    public void setWeightDiscrepancy(boolean weightDiscrepancy) {
        this.weightDiscrepancy = weightDiscrepancy;
    }

    public boolean isWeightExcess() {
        return weightExcess;
    }

    public void setWeightExcess(boolean weightExcess) {
        this.weightExcess = weightExcess;
    }

    // This method was previously outside the class, so it needs to be properly placed inside the class
    private void bagsTooHeavy() {
        weightExcess = true;
        
        // there is no class called WeightListener
        /*
        for (WeightListener l : listeners) {
            l.notifyDiscrepancy();
        }
        */
    }

    /**
     * Runs when a customer has signaled their desire to add their own bags to the bagging area.
     *
     * Method used to set adding bags to true.
     * Method used to add a bag to the bagging area.
     *
     * @param numberOfBags The number of reusable bags to be added.
     */
    public void addBags(int numberOfBags) {
        Mass bagWeight = new Mass(BigInteger.valueOf(5_000_000)); // ideal mass from ReusableBag class
        purchasedBagsWeight = new Mass(bagWeight.inMicrograms().multiply(BigInteger.valueOf(numberOfBags)));
        
        // there is no "update" method in purchasedBagWeight
        //this.update(purchasedBagsWeight); // update the scale
    }
}
