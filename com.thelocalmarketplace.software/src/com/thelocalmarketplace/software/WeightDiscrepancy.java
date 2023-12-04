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

Dongwen Tian 30181813
*/

public class WeightDiscrepancy { 
	private Mass startingWeight = new Mass(0);
	private Mass actualWeight;
	private boolean weightDiscrepancy = false; // 
	private AbstractSelfCheckoutStation station;
	//private AbstractElectronicScale scale;
	private boolean weightEcxcess;
	private StartSession session;
	// all prints must go into gui.
	
		
	public WeightDiscrepancy(StartSession session) {
		this.session = session;
	}
	
	public void set_weightDiscrepancy(boolean weightDis) {
		setWeightDiscrepancy(weightDis);
	}
	
	public void evaluate() throws OverloadedDevice {
		
		MassDifference difference = ((AbstractElectronicScale) session.getStation().getBaggingArea()).getCurrentMassOnTheScale().difference(session.getExpectedWeight());
		Mass absDifference = difference.abs();
		 if (absDifference.compareTo(session.getStation().getBaggingArea().getSensitivityLimit()) == -1) {
			 set_weightDiscrepancy(false);
			 enableInteractions(session.getStation());
		 }
		else if (((AbstractElectronicScale) session.getStation().getBaggingArea()).getCurrentMassOnTheScale().compareTo(session.getExpectedWeight()) == 1) {
			disableInteractions(session.getStation());
			set_weightDiscrepancy(true);
			System.out.println("unexpected item in bagging area"
					+ "please remove the item before continuing.");
		}
		else if (((AbstractElectronicScale) session.getStation().getBaggingArea()).getCurrentMassOnTheScale().compareTo(session.getExpectedWeight()) == -1) {
			disableInteractions(session.getStation());
			set_weightDiscrepancy(true);
			System.out.println("please put the item in the bagging area.");
			
			//session.getAttendantControl().sendWDMessage(); 
			
			/*
	    	if (session.getAttendantControl().getWDDecision() == true) // if the attandant approves the 
	    		removeLastItemWeight();								// WD then the last items weight is not calculated. 
	    	else
	    		System.out.println("please wait for assistance"); // gui
	    	*/
		}
		 	
	}
	
	
	public void exceedWeightEvaluate() {
		setWeightExcess(true);
		while(isWeightEcxcess()) {
			System.out.println("the scale has exceeded its limit. please remove the last item you added.");
			
		}
	}
	public void disableInteractions(AbstractSelfCheckoutStation station) {
		station.getBanknoteInput().disable();
		station.getCardReader().disable();
		station.getCoinSlot().disable();
		station.getMainScanner().disable();
		station.getHandheldScanner().disable();
		station.getScanningArea().disable(); 
		
		
	}
	public void enableInteractions(AbstractSelfCheckoutStation station) {
		station.getBanknoteInput().enable();
		station.getCardReader().enable();
		station.getCoinSlot().enable();
		station.getMainScanner().enable();
		station.getHandheldScanner().enable();
		station.getScanningArea().enable(); 
		
	} 
	
	/*
	public void removeLastItem() {
    if (session.getPickedItems().isEmpty()) {
    		System.out.println("No items to remove.");
    		return;
    	}
    	

 * i updated this in regards to the changes i made, the person that is responsible for remove item should update
 * subtract, as this iterations hardware doesnt support it. i would suggest reading the Mass class.
 
    // Retrieve details of the last added item
    	
    	long lastItemPrice = session.getPriceList().remove(session.getPriceList().size() - 1);
    	Mass lastItemWeight = new Mass(session.getWeightList().get(-1).inGrams().doubleValue());

    // Remove the last item from the list
    	session.getPickedItems().remove(session.getPickedItems().size() - 1);

    // Update the expectedWeight and total price
    	MassDifference dif = session.getExpectedWeight().difference(lastItemWeight);
    	session.setExpectedWeight(dif.abs());
    	session.setTotalPrice(session.getTotalPrice() - lastItemPrice);

    	System.out.println("Last item removed. Updated expectedWeight: " + session.getExpectedWeight());
    	System.out.println("Updated total price: " + session.getTotalPrice());
	}
*/
	
	/*
	public void removeLastItemWeight() {
		Mass lastItemWeight = new Mass(session.getWeightList().get(-1).inGrams().doubleValue());
    	MassDifference dif = session.getExpectedWeight().difference(lastItemWeight);
    	session.setExpectedWeight(dif.abs());
	}
	*/

	public boolean isWeightDiscrepancy() {
		return weightDiscrepancy;
	}

	public void setWeightDiscrepancy(boolean weightDiscrepancy) {
		this.weightDiscrepancy = weightDiscrepancy;
	}

	public boolean isWeightEcxcess() {
		return weightEcxcess;
	}

	public void setWeightExcess(boolean weightEcxcess) {
		this.weightEcxcess = weightEcxcess;
	}
}
          