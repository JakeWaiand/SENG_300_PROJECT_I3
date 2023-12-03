package com.thelocalmarketplace.software;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jjjwelectronics.scanner.Barcode;

public class AttendantControl {
	private boolean WDALram = false;
	private boolean WDdecision;
	private String attendantSearchResult;
	
	
	
	
	public void attendantSearchItem(String searchResults) { 
		//gui must pass the attendants search result 
		attendantSearchResult = searchResults; 
		Set<String> keySet = InternalDatabase.internalDataBase_Description.keySet();
			List<String> keyList = new ArrayList<String>();
			keyList.addAll(keySet);
		for(int i = 0; i < keySet.size();i++) {
			if (keyList.get(i).contains(attendantSearchResult)){
				System.out.print(keyList.get(i)); //this prints all the item descriptions that have the
				//text result in them. but you need to change print to gui writings(create a method for it and call it here)
			} // after the item has been picked, you can call one of addItemText methods in ItemProcessing control to add it.
			 //AttendantControl and ItemProcessingcontrol are both instantiated in startSession, you can use the getters to get them
			
				
		}
		
	}
	
	/**
	 * when the attendant receives a weight discrepancy message
	 * WD stands for weight discrepancy
	 */
	public void sendWDMessage() {
		WDALram = true;
		//AttendantGUI.runWDAlaram() //here gui should run the alarm somehow
		// approve? yes / no in the gui
		// then calls setDecision with the String, chosen by the attendants button,
		//either yes or no
			
			
	}
	
	public void setWDDecision(String decision) { //Gui team calls this
		if (decision == "yes")
			this.WDdecision = true;
		else
			this.WDdecision = false;
		
	}
	public boolean getWDDecision() {
		return this.WDdecision;
	}
	
	
	

}
