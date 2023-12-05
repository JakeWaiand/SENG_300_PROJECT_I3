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

public class MembershipDatabase {
	/**
     * Instances of this class are not needed, so the constructor is private.
     */
    private MembershipDatabase() {}

    /**
     * Map containing registered memberships, where the key is the membership number.
     */
    public static final Map<String, Membership> REGISTERED_MEMBERS = new HashMap<>();

    /**
     * Retrieves a membership from the database based on the provided membership number.
     *
     * @param membershipNumber The membership number to look up.
     * @return The Membership object associated with the membership number, or null if not found.
     */
    public static Membership getMembership(String membershipNumber) {
        return REGISTERED_MEMBERS.get(membershipNumber);
    }

    /**
     * Adds a new membership to the database.
     *
     * @param membership The Membership object to add.
     */
    public static void addMembership(Membership membership) {
        REGISTERED_MEMBERS.put(membership.getMembershipNumber(), membership);
    }

    /**
     * Updates the points for a specific membership.
     *
     * @param membershipNumber The membership number of the member.
     * @param newPoints The new points value.
     */
    public static void updatePoints(String membershipNumber, int newPoints) {
        Membership membership = REGISTERED_MEMBERS.get(membershipNumber);
        if (membership != null) {
            membership.setPoints(newPoints);
        }
    }

}
