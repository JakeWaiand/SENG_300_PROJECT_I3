package com.thelocalmarketplace.software.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        this.setTitle("Self Checkout Station");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setPreferredSize(new Dimension(600, 100));

        JLabel titleLabel = new JLabel("Self Checkout Station");
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(Color.BLACK);

        JButton startSessionButton = new JButton("Start Session");
        JButton addItemButton = new JButton("Add Item");
        JButton completeTransactionButton = new JButton("Complete Transaction");

        // startSessionButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Simulate starting a session
        //         JOptionPane.showMessageDialog(MainFrame.this, "Session started");
        //     }
        // });

        startSessionButton.addActionListener(e -> loadMainGUI());

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate adding items
                String itemName = JOptionPane.showInputDialog(MainFrame.this, "Enter item name:");
                if (itemName != null && !itemName.isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Item added: " + itemName);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Invalid item name");
                }
            }
        });

        completeTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate completing the transaction
                int result = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to complete the transaction?",
                        "Complete Transaction", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Transaction completed");
                }
            }
        });

        centerPanel.add(startSessionButton);
        centerPanel.add(addItemButton);
        centerPanel.add(completeTransactionButton);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

    private static void loadMainGUI() {
        JFrame addFrame = new JFrame(); 
        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.setSize(800, 600);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }
}
