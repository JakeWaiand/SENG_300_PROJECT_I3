package com.thelocalmarketplace.software.gui;

import javax.swing.*;

import com.thelocalmarketplace.software.ItemProcessingControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOwnBagsWindow {

    public static void open() {
        JFrame frame = new JFrame("Add Own Bags");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(0x7293A0));

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel headingLabel = new JLabel("Add Own Bags");
        headingLabel.setFont(new Font("Serif", Font.BOLD, 33));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(headingLabel, gbc);

        JButton addButton = new JButton("Add Bags");
        addButton.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(addButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the AddOwnBagsWindow
            AddWindow.open(); // Open the AddWindow again
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(backButton, gbc);

        // ActionListener for Add Bags button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for the number of bags to add
                String input = JOptionPane.showInputDialog("Enter the number of bags:");
                try {
                    int numberOfBags = Integer.parseInt(input);
                    // Call the method in ItemProcessingControl to add own bags
                    // You need a reference to ItemProcessingControl for this
                    // For demonstration purposes, assuming you have a reference named itemProcessingControl
                    ItemProcessingControl.addOwnBags(numberOfBags);
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
