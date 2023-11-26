package com.thelocalmarketplace;

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
	public static Mass startingWeight = new Mass(0);
	public static Mass expectedWeight = Add_item.expectedWeight;
	public static Mass actualWeight;
	public static boolean weightDiscrepancy = false; // 
	private String level = StartSession.level;
	private static AbstractSelfCheckoutStation station = StartSession.station;
	public static AbstractElectronicScale scale = StartSession.scale;
	public static boolean weightEcxcess; 
 
	
	
		
	
	
	public static void set_weightDiscrepancy(boolean weightDis) {
		weightDiscrepancy = weightDis;
	}
	
	public static void evaluate() throws OverloadedDevice {
		
		MassDifference difference = scale.getCurrentMassOnTheScale().difference(expectedWeight);
		Mass absDifference = difference.abs();
		 if (absDifference.compareTo(scale.getSensitivityLimit()) == -1) {
			 set_weightDiscrepancy(false); 
		 }
		else if (scale.getCurrentMassOnTheScale().compareTo(expectedWeight) == 1) {
			set_weightDiscrepancy(true);
			System.out.println("unexpected item in bagging area"
					+ "please remove the item before continuing.");
		}
		else if (scale.getCurrentMassOnTheScale().compareTo(expectedWeight) == -1) {
			set_weightDiscrepancy(true);
			System.out.println("please put the item in the bagging area.");
		}
		 	
	}
	
	public static void exceedWeightEvaluate() {
		weightEcxcess = true;
		while(weightEcxcess) {
			System.out.println("the scale has exceeded its limit. please remove the last item you added.");
			
		}
	}
	public static void disableInteractions(AbstractSelfCheckoutStation station) {
		station.banknoteInput.disable();
		station.cardReader.disable();
		station.coinSlot.disable();
		station.mainScanner.disable();
		station.handheldScanner.disable();
		station.scanningArea.disable(); 
		
		
	}
	public static void enableInteractions(AbstractSelfCheckoutStation station) {
		station.banknoteInput.enable();
		station.cardReader.enable();
		station.coinSlot.enable();
		station.mainScanner.enable();
		station.handheldScanner.enable();
		station.scanningArea.enable();
	} 
	public static void removeLastItem() {
    	if (Add_item.pickedItems.isEmpty()) {
    		System.out.println("No items to remove.");
    		return;
    	}

    // Retrieve details of the last added item
    	long lastItemPrice = WeightDiscrepancy.priceList.remove(WeightDiscrepancy.priceList.size() - 1);
    	Mass lastItemWeight = new Mass(Add_item.productsWeight.inGrams().doubleValue());

    // Remove the last item from the list
    	Add_item.pickedItems.remove(Add_item.pickedItems.size() - 1);

    // Update the expectedWeight and total price
    	Add_item.expectedWeight.subtract(lastItemWeight);
    	WeightDiscrepancy.totalPrice -= lastItemPrice;

    	System.out.println("Last item removed. Updated expectedWeight: " + Add_item.expectedWeight);
    	System.out.println("Updated total price: " + WeightDiscrepancy.totalPrice);
	}
}
          