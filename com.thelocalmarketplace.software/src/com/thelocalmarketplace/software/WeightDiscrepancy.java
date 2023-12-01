package com.thelocalmarketplace.software;

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
	public static Mass expectedWeight = ItemProcessingControl.expectedWeight;
	public static Mass actualWeight;
	public static boolean weightDiscrepancy = false; // 
	private AbstractSelfCheckoutStation station = StartSession.getStation();
	public static AbstractElectronicScale scale = StartSession.getScale();
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
		station.getBanknoteInput().disable();
		station.getCardReader().disable();
		station.getCoinSlot().disable();
		station.getMainScanner().disable();
		station.getHandheldScanner().disable();
		station.getScanningArea().disable(); 
		
		
	}
	public static void enableInteractions(AbstractSelfCheckoutStation station) {
		station.getBanknoteInput().enable();
		station.getCardReader().enable();
		station.getCoinSlot().enable();
		station.getMainScanner().enable();
		station.getHandheldScanner().enable();
		station.getScanningArea().enable(); 
		
	} 
	public static void removeLastItem() {
    	if (ItemProcessingControl.pickedItems.isEmpty()) {
    		System.out.println("No items to remove.");
    		return;
    	}
/*
 * i updated this in regards to the changes i made, the person that is responsible for remove item should update
 * subtract, as this iterations hardware doesnt support it. i would suggest reading the Mass class.
 */
    // Retrieve details of the last added item
    	long lastItemPrice = ItemProcessingControl.priceList.remove(ItemProcessingControl.priceList.size() - 1);
    	Mass lastItemWeight = new Mass(ItemProcessingControl.productsWeight.inGrams().doubleValue());

    // Remove the last item from the list
    	ItemProcessingControl.pickedItems.remove(ItemProcessingControl.pickedItems.size() - 1);

    // Update the expectedWeight and total price
    	ItemProcessingControl.expectedWeight.subtract(lastItemWeight);
    	ItemProcessingControl.totalPrice -= lastItemPrice;

    	System.out.println("Last item removed. Updated expectedWeight: " + ItemProcessingControl.expectedWeight);
    	System.out.println("Updated total price: " + ItemProcessingControl.totalPrice);
	}
}
          