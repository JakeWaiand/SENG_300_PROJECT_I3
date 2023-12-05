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

import com.jjjwelectronics.*;
import com.jjjwelectronics.IDeviceListener;
import com.jjjwelectronics.scanner.Barcode;
import com.jjjwelectronics.scanner.BarcodeScannerListener;
import com.jjjwelectronics.scanner.IBarcodeScanner;
import com.thelocalmarketplace.hardware.BarcodedProduct;
import com.thelocalmarketplace.hardware.external.ProductDatabases;


public class BarcodeListenerImplement implements BarcodeScannerListener {
	private StartSession session;
	
	public BarcodeListenerImplement(StartSession session) {
		this.session = session;
		
	}

	@Override
	public void aDeviceHasBeenEnabled(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method
		System.out.println("a device has been enabled.");
		
	}

	@Override
	public void aDeviceHasBeenDisabled(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
		System.out.println("a device has been disabled");
		
	}

	@Override
	public void aDeviceHasBeenTurnedOn(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
		System.out.println("a device has been turned on");
	}

	@Override
	public void aDeviceHasBeenTurnedOff(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
		System.out.println("a device has been turned off");
		
	}

	@Override
	public void aBarcodeHasBeenScanned(IBarcodeScanner barcodeScanner, Barcode barcode) {
		// TODO Auto-generated method stub
		
		session.getItemControl().addItemScanning(barcode, barcodeScanner);
		
		
	}	

}
