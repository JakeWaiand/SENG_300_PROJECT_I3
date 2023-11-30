package com.thelocalmarketplace.software;

import java.util.ArrayList;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.printer.ReceiptPrinterListener;

/*
 * Kenny Zeng 30151985
 * Tahamina Chowdhury 30140920
 * Sneh Patel 30086076
 * Jake Waiand 30179510
 * Roko Condic 30185671
 * Farouq Arafeh 30158214
 * Dongwen Tian 30181813
 * K M Chisty 30145123
 * Mohammad Soomro 30130440
 * Daniel Adebisi 30179418
 * Eyuel Kahsay 30181884
 * Fardin Rahman Sami 30172916
 * Almik Biju 30170902
 * Kourosh Malayeri 30174987
 * Hasan Qasim 30164530
 * Ariba Noman 30111428
 * Kyuyop (Andrew) Park 10046592
 * Jiaqi Wu 30172397
 * Ludovik Chojnacki [uc number]
 * Muhammad Niazi 3017777
 * Firdovsi Aliyev 30178471
 * Ratul Chakraborty [uc number]
 * 
 */

/* USES ITERATION 2 CODE WRITTEN BY GROUP 21
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



public class PrintReceipt implements ReceiptPrinterListener{
	public static ArrayList<String> itemlist = Add_item.pickedItems;
	public static ArrayList<Long> priceList = Add_item.priceList;
	public static void printReceipt() throws EmptyDevice, OverloadedDevice {
		String record = new String();
		record = "";
	
		for(int i = 0; itemlist.size() > i; i++) {
		record += (itemlist.get(i) + "                " + priceList.get(i));
		}
		for (int i = 0; record.length() > i; i++) {
			StartSession.station.printer.print(record.charAt(i));
			
		}
		
	}	
	
	/**
	 * Announces that the printer is out of paper.
	 */
	@Override
	public void thePrinterIsOutOfPaper() {
		System.out.println("The printer is out of paper");
	}

	/**
	 * Announces that the printer is out of ink.
	 */
	@Override
	public void thePrinterIsOutOfInk() {
		System.out.println("The printer is out of ink");
	}

	/**
	 * Announces that the printer is low on ink.
	 */
	@Override
	public void thePrinterHasLowInk() {
		System.out.println("The printer is low on ink");
	}

	/**
	 * Announces that the printer is low on paper.
	 */
	@Override
	public void thePrinterHasLowPaper() {
		System.out.println("The printer is low on paper");
	}

	/**
	 * Announces that paper has been added to the printer.
	 */
	@Override
	public void paperHasBeenAddedToThePrinter() {
		System.out.println("Paper has been added to the printer");
	}

	/**
	 * Announces that ink has been added to the printer.
	 */
	@Override
	public void inkHasBeenAddedToThePrinter() {
		System.out.println("Ink has been added to the printer");
	}

	@Override
	public void aDeviceHasBeenEnabled(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aDeviceHasBeenDisabled(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aDeviceHasBeenTurnedOn(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aDeviceHasBeenTurnedOff(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}
}

