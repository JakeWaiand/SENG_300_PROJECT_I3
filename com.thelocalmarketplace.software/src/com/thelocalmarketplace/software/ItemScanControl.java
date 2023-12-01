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
import com.thelocalmarketplace.hardware.Product;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.external.ProductDatabases;
import  com.thelocalmarketplace.hardware.PLUCodedProduct;

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
public class ItemScanControl extends ItemProcessingControl {
	private static boolean itemScanned = false;
	private static BarcodedProduct barcodedProduct;
	
	public ItemScanControl() {
		super();
		
	}
	
	public static void setProduct(BarcodedProduct input_product) {
		barcodedProduct = input_product;

	}


	@Override
	public BarcodedProduct getProduct() {
		return barcodedProduct;

	}

	public static void addItemScanning(Barcode barcode, IBarcodeScanner scanner) {
		itemScanned = true;
		BarcodedProduct theProduct = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
		setProduct(theProduct);
		addItem(station, barcodedProduct.getDescription(),  barcodedProduct.getPrice(), 
				new Mass(barcodedProduct.getExpectedWeight()));
		itemScanned = false;
	}
	

	

	
	// changes need to be made in remove item and from this point forward in the code.

	public static void removeItem(Barcode barcode) {
		if (remove_item == true) {
			WeightDiscrepancy.disableInteractions(station);
			BarcodedProduct removedProduct = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
			if (pickedItems.contains(removedProduct.getDescription())) {
				int index = pickedItems.indexOf(removedProduct.getDescription());
				totalPrice -= priceList.get(index);
				pickedItems.remove(index);
				priceList.remove(index);
				Mass weight = new Mass(removedProduct.getExpectedWeight());
				MassDifference difference = expectedWeight.difference(weight);
				expectedWeight = difference.abs(); //updates expectedWeight
				System.out.println("Item removed: " + removedProduct.getDescription());
				System.out.println("Total price: " + totalPrice);
				System.out.println("please remove the item from bagging.");

				while (WeightDiscrepancy.weightDiscrepancy == true) {
					try {
						WeightDiscrepancy.evaluate(); // evaluates whether or not the there is a weight discrepancy
					} catch (OverloadedDevice e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				WeightDiscrepancy.disableInteractions(station);
			} else {
				System.out.println("Item not found in the scanned items list.");
			}
		}
	}



	public void setRemove_item(boolean costumersChoice) {
		remove_item = costumersChoice;
	}
}


	
