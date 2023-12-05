package com.thelocalmarketplace.software.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ScanItemWindow {

    public static void open() {
        JFrame frame = new JFrame("Scan Item");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(0x7293A0));

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel headingLabel = new JLabel("Scan Item");
        headingLabel.setFont(new Font("Serif", Font.BOLD, 33));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(headingLabel, gbc);

        JButton scanButton = new JButton("Scan");
        scanButton.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(scanButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the ScanItemWindow
            AddWindow.open(); // Open the AddWindow again
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(backButton, gbc);

        // ActionListener for Scan button
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your logic here for scanning items
                JOptionPane.showMessageDialog(frame, "Item scanned successfully!");
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Run GUI construction in the Event-Dispatching Thread for thread safety
            ScanItemWindow.open();
        });
    }
}
