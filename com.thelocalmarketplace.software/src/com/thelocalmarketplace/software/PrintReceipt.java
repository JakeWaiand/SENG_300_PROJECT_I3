package com.thelocalmarketplace.software;

import java.time.LocalDate;
import java.util.ArrayList;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.OverloadedDevice;

/*
 * Dongwen Tian 30181813
 * Kenny Zeng 30151985
 * Tahamina Chowdhury 30140920 
 * Sneh Patel 30086076
 * Jake Waiand 30179510
 * Roko Condic 30185671
 * Farouq Arafeh 30158214 
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
 * Ludovik Chojnacki [number]
 * Muhammad Niazi 3017777
 * Firdovsi Aliyev 30178471
 * Ratul Chakraborty [number]
 */

/*
=======
USES ITERATION 2 CODE FROM GROUP 21

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


Dongwen Tian 30181813
=======

*/

public class PrintReceipt {

	private ArrayList<String> itemList;
	private ArrayList<Long> priceList;
	private long totalPrice;
	private StartSession session;
	
	/**
	 * Constructor using a session that can be used to simulate
	 * printing a receipt
	 * @param session
	 *     Any particular session at a station
	 */
	public PrintReceipt(StartSession session) {
		this.session = session;
		itemList = session.getPickedItems();
		priceList = session.getPriceList();
		totalPrice = session.getTotalPrice();
	}	
	
	/**
	 * Prints the contents of the receipt, cuts it and removes it
	 * from the printer.
	 * @return
	 *     The receipt, simulated as a string.
	 * @throws EmptyDevice
	 *     If the printer is either low on ink or paper.
	 * @throws OverloadedDevice
	 *     If a line exceeds the width of the paper.
	 */
	public String printReceipt() throws EmptyDevice, OverloadedDevice {
		String receipt = makeReceipt();
		
		try {
			for (char c : receipt.toCharArray()) {
				session.getStation().getPrinter().print(c);
			}
		} catch (EmptyDevice e) {
			return e.getMessage();
		} catch (OverloadedDevice e) {
			return e.getMessage();
		}
		
		session.getStation().getPrinter().cutPaper();
		return session.getStation().getPrinter().removeReceipt();
	}
	
	/**
	 * Creates the contents of the receipt
	 * @return
	 *     The contents of the receipt
	 */
	private String makeReceipt() {
		/* This is roughly how the receipt should look:
		 * RECEIPT DATE: [yyyy-MM-dd]
		 * 
		 * SALE:
		 * -[list of products] - [individual price]
		 * 
		 * TOTAL: [total amount paid]
		 */
		String receipt = "";
		
		LocalDate currentDate = LocalDate.now();
		receipt += "RECEIPT DATE: " + currentDate + "\n\nSALE:\n"; 
		
		int i = 0;
		for (String s : itemList) {
			receipt += "-" + s + " - " + priceList.get(i) + "\n";
			i++;
		}
		
		receipt += "\nTOTAL: " + totalPrice;
		
		return receipt;
	}
}

