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
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.external.ProductDatabases;
import com.thelocalmarketplace.WeightDiscrepancy;
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
public class Add_item {
}
	private static BarcodedProduct product;
	private static boolean itemScanned = false;
	public static ArrayList<String> pickedItems; //this can be used for print receipt
	public static ArrayList<Long> priceList; //this can be used for print receipt
	public static long totalPrice; //this can be used for print receipt
	public static Mass expectedWeight = new Mass(0);
	public static Mass productsWeight;
	private static long productsPrice;
	private static boolean ignore = false;
	private static AbstractSelfCheckoutStation station = StartSession.station;
	private String level = StartSession.level;
	public static boolean remove_item = true;
	public double weight;
	private boolean blocked;
	private boolean noBaggingRequested;
	public Mass weightLimit;
	private double expectWeight;
	private WeightDiscrepancy weightDiscrepancy;


	public static void setProduct(BarcodedProduct input_product) {
		product = input_product;

	}

	public static void setIgnore(boolean ignoring) {
		ignore = ignoring;
	}

	public static void setWeightAndPrice() {
		productsWeight = new Mass(product.getExpectedWeight());
		productsPrice = product.getPrice();
	}

	public BarcodedProduct getProduct() {
		return product;

	}

	public static void itemGotScanned(Barcode barcode, IBarcodeScanner scanner) {
		itemScanned = true;

		BarcodedProduct theProduct = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(barcode);
		setProduct(theProduct);
		addItem_Scanning(station, product);
		itemScanned = false;
	}


	public static void updateCostumer() {

		totalPrice += productsPrice;
		pickedItems.add(product.getDescription());
		priceList.add(product.getPrice());
		System.out.print("total price: ");
		System.out.println(totalPrice);
		System.out.println("these are the items scanned:");
		for (int i = 0; i <= pickedItems.size(); i++)
			System.out.println(pickedItems.get(i));
		System.out.println("please put the item in the bagging area");

	}

	public static void updateScale() {
		expectedWeight.sum(productsWeight);

	}

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


	public static void addItem_Scanning(AbstractSelfCheckoutStation theStation, BarcodedProduct thisProduct) {
		// the listener sets itemScanned to true
		if (itemScanned && !ignore) {

			if (WeightDiscrepancy.weightDiscrepancy == false) {
				WeightDiscrepancy.disableInteractions(theStation);
				//3
				setWeightAndPrice();
				//4
				updateScale();
				//5
				updateCostumer();
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

	public static void setRemove_item(boolean costumersChoice) {
		remove_item = costumersChoice;
	}



	public void BulkyItem(double expectWeight) {
		this.expectWeight = expectWeight;
		this.blocked = false;
		this.noBaggingRequested = false;
	}



	// Blocks the session when there is a bulky weight.
	public void blockStation() {
		blocked = true;
	}


	public void addItemToBaggingArea(double weight) {
		if (!blocked) {
			expectWeight += weight;
		} else {
			// Handle the exception where the customer adds an item despite the block
			System.out.println("Weight Discrepancy detected. Please remove the bulky item.");
		}
	}

	// checks if the weight of them is more than the weight limit if so it blocks the state
	public void bulkyItemCheck(IElectronicScale scale, Mass mass) throws OverloadedDevice {
		weight = mass.inGrams().doubleValue();
		weightLimit = scale.getMassLimit();
		if ( weight >= weightLimit.inGrams().doubleValue()){
			blockStation();
			addItemToBaggingArea(weight);
			System.out.println("Item too heavy for bagging area.Session Disabled.");


		} else if (expectWeight == weight){
			weightDiscrepancy.evaluate();
			System.out.println("Weight Discrepancy detected.");

		}




}