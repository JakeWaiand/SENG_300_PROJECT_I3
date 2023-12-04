package com.thelocalmarketplace.software.gui;

import javax.swing.*;

public class CatalogueItem {
    private String name;
    private ImageIcon image;
    private String description;
    private double price;

    public CatalogueItem(String name, ImageIcon image, String description, double price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters
}
