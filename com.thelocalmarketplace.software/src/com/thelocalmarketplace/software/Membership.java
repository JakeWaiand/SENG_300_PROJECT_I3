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

/**
 * Represents a membership in the self-checkout system.
 */

public class Membership {
	private String membershipNumber; // Unique identifier for the membership
    private int points; // Accumulated points for the member

    /**
     * Constructs a new Membership with the given membership number.
     *
     * @param membershipNumber The unique membership number.
     */
    public Membership(String membershipNumber) {
        this.membershipNumber = membershipNumber;
        this.points = 0; // Initialize points to zero
    }

    /**
     * Gets the membership number.
     *
     * @return The membership number.
     */
    public String getMembershipNumber() {
        return membershipNumber;
    }

    /**
     * Gets the accumulated points for the member.
     *
     * @return The accumulated points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the points for the member.
     *
     * @param points The new points value.
     * @throws IllegalArgumentException If points is negative.
     */
    public void setPoints(int points) {
        if (points >= 0) {
            this.points = points;
        } else {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipNumber='" + membershipNumber + '\'' +
                ", points=" + points +
                '}';
    }

}
