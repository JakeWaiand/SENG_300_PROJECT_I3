package com.thelocalmarketplace.software.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.Mass;
import com.thelocalmarketplace.software.WeightDiscrepancy;

/*
Firdovsi Aliyev 30178471
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

public class WeightDiscrepancyTest {


    
    @Before
    public void setUp() {
        // WeightDiscrepancy WeightDiscrepancy;
        // Initialize the necessary state
        com.thelocalmarketplace.software.WeightDiscrepancy.startingWeight = new Mass(0);
        com.thelocalmarketplace.software.WeightDiscrepancy.expectedWeight = new Mass(0);
        com.thelocalmarketplace.software.WeightDiscrepancy.actualWeight = new Mass(0);
        com.thelocalmarketplace.software.WeightDiscrepancy.weightDiscrepancy = false;
        // Other initializations if needed
    }

    @Test
    public void testSetWeightDiscrepancy() {
        WeightDiscrepancy.set_weightDiscrepancy(true);
        assertTrue("Weight discrepancy should be true", WeightDiscrepancy.weightDiscrepancy);

        WeightDiscrepancy.set_weightDiscrepancy(false);
        assertFalse("Weight discrepancy should be false", WeightDiscrepancy.weightDiscrepancy);
    }


    @Test
    public void testRemoveLastItemWithNoItems() {
        WeightDiscrepancy.removeLastItem();
        assertTrue("There should be no items to remove", Add_item.pickedItems.isEmpty());
    }
    @Test
    public void testRemoveLastItemWithOneItem() {
        // Add one item to the list
        Add_item.pickedItems.add("Test Item");
        Add_item.priceList.add(100L);
        Add_item.totalPrice = 100;
        Add_item.productsWeight = new Mass(5);
        Add_item.expectedWeight = new Mass(5);

        WeightDiscrepancy.removeLastItem();

        assertTrue("Item should be removed", Add_item.pickedItems.isEmpty());
        assertEquals("Expected weight should be updated", 0, Add_item.expectedWeight.inGrams().longValue());
        assertEquals("Total price should be updated", 0, Add_item.totalPrice);
    }

    
}
