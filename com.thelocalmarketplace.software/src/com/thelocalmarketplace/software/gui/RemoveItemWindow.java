package com.thelocalmarketplace.software.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class RemoveItemWindow {

    private static List<Product> productList = new ArrayList<>();  // Using 'Product' as the actual item type

    public static void open() {
        JFrame frame = new JFrame("Remove Product");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(0x7293A0));

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel headingLabel = new JLabel("Remove Product");
        headingLabel.setFont(new Font("Serif", Font.BOLD, 33));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(headingLabel, gbc);

        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(removeButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the RemoveItemWindow
            AddWindow.open(); // Open the AddWindow again
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(backButton, gbc);

        // ActionListener for Remove button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Identify the product (you may need a reference to the product or its ID)
                // For demonstration purposes, assuming the list is not empty
                if (!productList.isEmpty()) {
                    Product selectedProduct = productList.get(0);  // Replace with your actual logic to get the selected product

                    // Implement the logic to remove the product
                    boolean removalSuccessful = removeProduct(selectedProduct);

                    // Provide appropriate feedback to the user
                    if (removalSuccessful) {
                        JOptionPane.showMessageDialog(frame, "Product removed successfully: " + selectedProduct.getName());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to remove the product!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "No products to remove!");
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Placeholder method for product removal logic
    private static boolean removeProduct(Product productToRemove) {
        // Implement your actual product removal logic here
        // Example: 
        // if (productList.remove(productToRemove)) {
        //     return true;
        // } else {
        //     return false;
        // }
        
        // For demonstration purposes, always return true
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Run GUI construction in the Event-Dispatching Thread for thread safety
            RemoveItemWindow.open();
        });
    }
}
