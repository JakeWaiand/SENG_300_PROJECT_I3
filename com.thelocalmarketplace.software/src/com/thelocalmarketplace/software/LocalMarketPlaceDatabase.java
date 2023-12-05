/*
 * Dongwen Tian			 30181813
 *Fardin Rahman Sami             30172916
 * Kenny Zeng 			 30151985
 * Tahamina Chowdhury 	         30140920
 * Sneh Patel 			 30086076
 * Jake Waiand 			 30179510
 * Roko Condic 			 30185671
 * Farouq Arafeh		 30158214
 * K M Chisty 			 30145123
 * Mohammad Soomro 		 30130440
 * Daniel Adebisi 		 30179418
 * Eyuel Kahsay 		 30181884
 * Almik Biju 			 30170902
 * Kourosh Malayeri 	         30174987
 * Hasan Qasim 			 30164530
 * Ariba Noman 			 30111428
 * Kyuyop (Andrew) Park          10046592
 * Jiaqi Wu 			 30172397
 * Ludovik Chojnacki 	         30178890
 * Muhammad Niazi 		 30177775
 * Firdovsi Aliyev 		 30178471
 * Ratul Chakraborty	         30194422
 */

package com.thelocalmarketplace.software;

import java.util.HashMap;
import java.util.Map;

import com.jjjwelectronics.Numeral;
import com.jjjwelectronics.scanner.Barcode;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.Product;




public class LocalMarketPlaceDatabase {
	
	private static LocalMarketPlaceDatabase instance = null;

	private final Map<Barcode, BarcodedProduct> BARCODED_PRODUCT_DATABASE;
	private final Map<Product, Integer> INVENTORY;

	private LocalMarketPlaceDatabase() {
		BARCODED_PRODUCT_DATABASE = new HashMap<>();
		INVENTORY = new HashMap<>();
		populateDatabase();
	}
	
	private void populateDatabase() {

		Barcode milkBarcode = new Barcode(new Numeral[] {Numeral.one, Numeral.two, Numeral.three, Numeral.four, Numeral.five});
		Barcode juiceBarcode = new Barcode(new Numeral[] {Numeral.two, Numeral.three, Numeral.four, Numeral.five, Numeral.one});
		Barcode breadBarcode = new Barcode( new Numeral[] {Numeral.three, Numeral.four, Numeral.five, Numeral.one, Numeral.two});
		Barcode eggsBarcode = new Barcode(new Numeral[] {Numeral.four, Numeral.five, Numeral.one, Numeral.two, Numeral.three});
		Barcode canOfBeansBarcode = new Barcode(new Numeral[] {Numeral.five, Numeral.one, Numeral.two, Numeral.three, Numeral.four});

		final BarcodedProduct milk = new BarcodedProduct(milkBarcode, "MooMilk 2% 4L", 5L, 4128.00);
		final BarcodedProduct juice = new BarcodedProduct(juiceBarcode, "Orange Juice Pulp Free 2.63L", 6L, 2630.00);
		final BarcodedProduct bread = new BarcodedProduct(breadBarcode, "Whole Wheat Sliced Bread", 2L, 675.00);
		final BarcodedProduct eggs = new BarcodedProduct(eggsBarcode, "Large Eggs, 12 Count", 3L, 699.00);
		final BarcodedProduct canOfBeans = new BarcodedProduct(canOfBeansBarcode, "Dark Red Kidney Beans, 540mL", 1L, 423.00);
			
		addBarcodedProductToDatabase(milk);
		addBarcodedProductToDatabase(juice);
		addBarcodedProductToDatabase(bread);
		addBarcodedProductToDatabase(eggs);
		addBarcodedProductToDatabase(canOfBeans);

		addBarcodedProductToInventory(milk, 25);
		addBarcodedProductToInventory(juice, 12);
		addBarcodedProductToInventory(bread, 35);
		addBarcodedProductToInventory(eggs, 44);
		addBarcodedProductToInventory(canOfBeans, 75);
	}

	

	public static LocalMarketPlaceDatabase getInstance() {
		if (instance == null) {
			instance = new LocalMarketPlaceDatabase();
		}
		return instance;
	}

	public void addBarcodedProductToDatabase(BarcodedProduct barcodedProduct) {
		BARCODED_PRODUCT_DATABASE.put(barcodedProduct.getBarcode(), barcodedProduct);
	}
	
	public BarcodedProduct getBarcodedProductToDatabase(Barcode barcode) {
		return BARCODED_PRODUCT_DATABASE.get(barcode);
	}

	public void addBarcodedProductToInventory(BarcodedProduct barcodedProduct, int amount) {
		INVENTORY.put(barcodedProduct, amount);
	}

	public void removeBarcodedProductFromInventory(BarcodedProduct barcodedProduct, int amountRemoved) {
		INVENTORY.put(barcodedProduct, INVENTORY.get(barcodedProduct) - amountRemoved);
	}

	public int getInventoryOfBarcodedProduct(BarcodedProduct barcodedProduct) {
		return INVENTORY.get(barcodedProduct);
	}

}
