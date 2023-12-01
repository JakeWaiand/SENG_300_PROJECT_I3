package com.thelocalmarketplace.software;

import com.jjjwelectronics.Mass;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.PLUCodedProduct;
import com.thelocalmarketplace.hardware.PriceLookUpCode;
import com.thelocalmarketplace.hardware.Product;
import com.thelocalmarketplace.hardware.external.ProductDatabases;

public class ItemPLUControl extends ItemProcessingControl  {
	private static PLUCodedProduct PLUproduct;
	private static Mass massOnScale;
	private static long pricePerUnit;
	private static long totalPrice;
	private static PriceLookUpCode PLUcode;
	// note for the gui team: when an item is being added via PLU,
	// you need to get the entered code and call setPLUcode(code).
	
	public ItemPLUControl() {
		super();
		
	}
	
	public static void setProduct(PLUCodedProduct input_product) {
		PLUproduct = input_product;
		pricePerUnit = PLUproduct.getPrice();
		totalPrice =  pricePerUnit * massOnScale.inGrams().longValue();
	
	}


	@Override
	public PLUCodedProduct getProduct() {
		return PLUproduct;

	}

	public static void setMass(Mass mass) {
		massOnScale = mass;
		
	}
	public static void addItemPLU() {
		PLUCodedProduct thisProduct = ProductDatabases.PLU_PRODUCT_DATABASE.get(PLUcode);
		addItem(station, PLUproduct.getDescription(),  totalPrice, 
				massOnScale);
		
	}
	
	public static void setPLUCode(PriceLookUpCode code) {
		PLUcode = code;
		StartSession.getHandHeldScanner().disable();  // just to make sure no item is being scanned at the same time
		StartSession.getMainScanner().disable();    // even though weightdiscrepancy also does this
		
	}
	
	
	
	
}	
	
	
	
	
	
