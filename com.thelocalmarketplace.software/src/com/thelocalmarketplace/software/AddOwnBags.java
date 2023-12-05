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
import com.thelocalmarketplace.hardware.*;
import com.tdc.*;
import com.thelocalmarketplace.software.StartSession;
import com.jjjwelectronics.scale.*;
import com.jjjwelectronics.*;

public class AddOwnBags extends Item {

    private Mass maxBagMass;

    private long price;

    public AddOwnBags(Mass mass) {
        super(mass);
        price = 0;
        ;
    }

    public long getPrice() {
        return price;
    }

    public void setMaxBagMass(Mass max) {
        maxBagMass = max;
    }

    public void addOwnBag() {
        if (maxBagMass.compareTo(this.getMass()) >= 0) {
            StartSession.getShoppingCart().add(this);
            StartSession.updateExpectedMass(StartSession.getExpectedMass().sum(this.getMass()));
        }
    }
}
