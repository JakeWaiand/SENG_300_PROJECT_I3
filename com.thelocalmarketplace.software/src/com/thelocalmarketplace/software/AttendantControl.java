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
