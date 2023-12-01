package com.thelocalmarketplace.software;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.jjjwelectronics.Mass;
import com.jjjwelectronics.Mass.MassDifference;
import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.scale.*;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.*;
import com.tdc.coin.CoinDispenserGold;
import com.tdc.coin.CoinSlot;
import com.tdc.coin.CoinStorageUnit;
import com.tdc.coin.CoinValidator;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.CoinTray;
import com.thelocalmarketplace.hardware.PLUCodedProduct;
import com.thelocalmarketplace.hardware.Product;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.external.ProductDatabases;



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
public abstract class ItemProcessingControl {
    protected static ArrayList<String> pickedItems; //this can be used for print receipt
	protected static ArrayList<Long> priceList; //this can be used for print receipt
	protected static long totalPrice; //this can be used for print receipt
	protected static Mass expectedWeight = new Mass(0);
	protected static Mass productsWeight;
	protected static long productsPrice;
	protected static boolean ignore = false;
	protected static AbstractSelfCheckoutStation station = StartSession.getStation();
	protected static boolean remove_item = false;
	protected double weight;
	protected boolean blocked;
	protected boolean noBaggingRequested;
	protected Mass weightLimit;
    protected double expectWeight;
    protected WeightDiscrepancy weightDiscrepancy;

  
    	
 

    /**
     * if the user chooses ignore scan, it sets the ignore variable to true
     * UI TEAM NEEDS TO WORK WITH THIS.
     * @param ignoring
     */
	public void setIgnore(boolean ignoring) {
		ignore = ignoring;
	}

    /**
     * sets the products weight and price.
     * 
     * @param weight is the weight of the product
     * @param price is the final price of the item
     */
    public static void setWeightAndPrice(Mass weight, long price) {
    	productsWeight = weight;
		productsPrice = price;
    }

    /**
     *
     * @return the product
     */
    public abstract Product getProduct();
    /**
     * updates the costumer, at this point it just prints
     *
     * UI TEAM NEEDS TO WORK WITH THIS.
     * 
     * @param description is the description of the item
     * @param price is the final price of the item
     */
    public static void updateCostumer(String description, long price) { // needs to work with gui.

		totalPrice += productsPrice;
		pickedItems.add(description);
		priceList.add(price);
		System.out.print("total price: ");
		System.out.println(totalPrice);
		System.out.println("these are the items scanned:");
		for (int i = 0; i <= pickedItems.size(); i++)
			System.out.println(pickedItems.get(i));
		System.out.println("please put the item in the bagging area");

	}
    
    /**
     * updates the scale about the changed expected weight
     */

    public static void updateScale() {
        expectedWeight.sum(productsWeight);

    }
    /**
     * 
     * @param theStation the self checkout station(hardware)
     * @param description is the description of the item
     * @param price is the final price of the item
     * @param weight is the weight of the product
     */
    public static  void addItem(AbstractSelfCheckoutStation theStation, String description, long price,
    		Mass weight) {
		// the listener sets itemScanned to true
		if (!ignore) {

			if (WeightDiscrepancy.weightDiscrepancy == false) {
				WeightDiscrepancy.disableInteractions(theStation);
				//3
				setWeightAndPrice(weight, price);
				//4
				updateScale();
				//5
				updateCostumer(description, price);
				//6
				try {
					WeightDiscrepancy.evaluate(); // evaluates whether or not the there is a weight discrepancy
				} catch (OverloadedDevice e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				while (WeightDiscrepancy.weightDiscrepancy == true) {
					try {
						WeightDiscrepancy.evaluate(); // evaluates whether or not the there is a weight discrepancy
					} catch (OverloadedDevice e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				WeightDiscrepancy.disableInteractions(theStation);

			}


		}
	}

    
    // changes need to be made in remove item and from this point forward in the code.

   


}
   
