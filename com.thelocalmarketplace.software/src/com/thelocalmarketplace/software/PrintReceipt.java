package com.thelocalmarketplace.software;

import java.util.ArrayList;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.printer.ReceiptPrinterListener;


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



public class PrintReceipt implements ReceiptPrinterListener{
	private ArrayList<String> itemlist;
	private ArrayList<Long> priceList;
	private StartSession session;
	public PrintReceipt(StartSession session) throws EmptyDevice, OverloadedDevice {
		this.session = session;
		itemlist = session.getPickedItems();
		priceList = session.getPriceList();
		
		String record = new String();
		record = "";
	
		for(int i = 0; itemlist.size() > i; i++) {
		record += (itemlist.get(i) + "                " + priceList.get(i));
		}
		for (int i = 0; record.length() > i; i++) {
			session.getStation().getPrinter().print(record.charAt(i));
			
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

