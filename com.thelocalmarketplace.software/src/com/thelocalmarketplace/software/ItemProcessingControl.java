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
import com.jjjwelectronics.scanner.*;
import com.tdc.coin.CoinDispenserGold;
import com.tdc.coin.CoinSlot;
import com.tdc.coin.CoinStorageUnit;
import com.tdc.coin.CoinValidator;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.CoinTray;
import com.thelocalmarketplace.hardware.PLUCodedProduct;
import com.thelocalmarketplace.hardware.PriceLookUpCode;
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
public class ItemProcessingControl {
	private Mass productsWeight;
	private long productsPrice;
	private boolean ignore = false;
	private AbstractSelfCheckoutStation station;
	private boolean remove_item = false;
    private StartSession session;
	private boolean itemScanned;
	private PriceLookUpCode PLUcode;

  
    	
 
    public ItemProcessingControl(StartSession session) {
    	this.session = session;
    	station = session.getStation();
    	itemScanned = false;
    }
    
  
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
    public void setWeightAndPrice(Mass weight, long price) {
    	productsWeight = weight;
		productsPrice = price;
    }

    
    /**
     * updates the costumer, at this point it just prints
     *
     * UI TEAM NEEDS TO WORK WITH THIS.
     * 
     * @param description is the description of the item
     * @param price is the final price of the item
     */
    public void updateCostumer(String description, long price, Mass weight) { // needs to work with gui.

		session.setTotalPrice(session.getTotalPrice() + productsPrice);
		session.getPickedItems().add(description);
		session.getPriceList().add(price);
		session.getWeightList().add(weight);
		
		System.out.print("total price: ");
		System.out.println(session.getTotalPrice());
		System.out.println("these are the items scanned:");
		for (int i = 0; i <= session.getPickedItems().size(); i++)
			System.out.println(session.getPickedItems().get(i));
		System.out.println("please put the item in the bagging area");

	}
    
    /**
     * updates the scale about the changed expected weight
     */

    public void updateScale() {
        session.setExpectedWeight(session.getExpectedWeight().sum(productsWeight));

    }
    /** 
     * 
     * @param theStation the self checkout station(hardware)
     * @param description is the description of the item
     * @param price is the final price of the item
     * @param weight is the weight of the product
     */
    public void addItem(AbstractSelfCheckoutStation theStation, String description, long price,
    		Mass weight) {
		// the listener sets itemScanned to true
		if (!ignore) {

			if (session.getWD().isWeightDiscrepancy() == false) {
				session.getWD().disableInteractions(theStation);
				//3
				setWeightAndPrice(weight, price);
				//4
				updateScale();
				//5
				updateCostumer(description, price, weight);
				//6
				try {
					session.getWD().evaluate(); // evaluates whether or not the there is a weight discrepancy
				} catch (OverloadedDevice e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				while (session.getWD().isWeightDiscrepancy() == true) {
					try {
						session.getWD().evaluate(); // evaluates whether or not the there is a weight discrepancy
					} catch (OverloadedDevice e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				session.getWD().disableInteractions(theStation);

			}


		}
	}
    public void addItemScanning(Barcode barcode, IBarcodeScanner scanner) {
		itemScanned  = true;
		BarcodedProduct barcodedProduct = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
		addItem(session.getStation(), barcodedProduct.getDescription(),  barcodedProduct.getPrice(), 
				new Mass(barcodedProduct.getExpectedWeight()));
		itemScanned = false;
	}
   
    public void addItemPLU(Mass massOnScale) {
		PLUCodedProduct PLUproduct = ProductDatabases.PLU_PRODUCT_DATABASE.get(PLUcode);
		long PLUpricePerUnit = PLUproduct.getPrice();
		long totalPrice =  PLUpricePerUnit * massOnScale.inGrams().longValue();
		addItem(station, PLUproduct.getDescription(), totalPrice, 
				massOnScale);
		
	}
	
	public void setPLUCode(PriceLookUpCode code) {
		PLUcode = code;
		session.getHandHeldScanner().disable();  // just to make sure no item is being scanned at the same time
		session.getMainScanner().disable();    // even though weightdiscrepancy also does this
		
	}

    // i need these three variables from gui.
   public void addItemVisualCatalogue(long price, String description, Mass weight) { 
	   addItem(session.getStation(), description, price, weight);
	   
   }
   public void addItemTextSearch(String itemName) {
	   //to be implemented
	   
	   
   }
   
   public void removeItem(Barcode barcode) {
		if (remove_item == true) {
			session.getWD().disableInteractions(session.getStation());
			BarcodedProduct removedProduct = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
			if (session.getPickedItems().contains(removedProduct.getDescription())) {
				int index = session.getPickedItems().indexOf(removedProduct.getDescription());
				session.setTotalPrice(session.getTotalPrice() - session.getPriceList().get(index));
				session.getPickedItems().remove(index);
				session.getPriceList().remove(index);
				Mass weight = new Mass(removedProduct.getExpectedWeight());
				MassDifference difference = session.getExpectedWeight().difference(weight);
				session.setExpectedWeight(difference.abs()); //updates expectedWeight
				System.out.println("Item removed: " + removedProduct.getDescription());
				System.out.println("Total price: " + session.getTotalPrice());
				System.out.println("please remove the item from bagging.");

				while (session.getWD().isWeightDiscrepancy() == true) {
					try {
						session.getWD().evaluate(); // evaluates whether or not the there is a weight discrepancy
					} catch (OverloadedDevice e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				session.getWD().disableInteractions(session.getStation());
			} else {
				System.out.println("Item not found in the scanned items list.");
			}
		}
	}

   


}
   
