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

import com.jjjwelectronics.IDevice;
import com.jjjwelectronics.IDeviceListener;
import com.jjjwelectronics.Mass;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.scale.ElectronicScaleListener;
import com.jjjwelectronics.scale.IElectronicScale;


public class EScaleListenerImplement implements ElectronicScaleListener {
	private StartSession session;
	private boolean PLUItemIncoming;
	public EScaleListenerImplement(StartSession session) {
		this.session = session;
		setPLUItemIncoming(false);
	}
	@Override
	public void aDeviceHasBeenEnabled(IDevice<? extends IDeviceListener> device) {
		// TODO Auto-generated method stub
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
	public void theMassOnTheScaleHasChanged(IElectronicScale scale, Mass mass) {
		// TODO Auto-generated method stub
		// something like massChanged that must be defined somewhere.
		if (scale == session.getScale()) {
			try {
				session.getWD().evaluate();
			} catch (OverloadedDevice e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (scale == session.getScanScale()) {
			if(PLUItemIncoming) {
				session.getItemControl().addItemPLU(mass);
				setPLUItemIncoming(false);
			}
			else {
				session.getItemControl().setMass(mass);
				
			}
		}	
		
		
	}

	@Override
	public void theMassOnTheScaleHasExceededItsLimit(IElectronicScale scale) {
		session.getWD().exceedWeightEvaluate();
		
		
	}

	@Override
	public void theMassOnTheScaleNoLongerExceedsItsLimit(IElectronicScale scale) {
		session.getWD().setWeightExcess(false);
	
	}
	public boolean isPLUItemIncoming() {
		return PLUItemIncoming;
	}
	public void setPLUItemIncoming(boolean pLUItemIncomming) {
		PLUItemIncoming = pLUItemIncomming;
	}

}
