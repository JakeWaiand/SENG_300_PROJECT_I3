package com.thelocalmarketplace.software.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PayWindow {

    public static void open() {
        JFrame frame = new JFrame("Pay");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(0x7293A0));

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel headingLabel = new JLabel("Payment Options");
        headingLabel.setFont(new Font("Serif", Font.BOLD, 33));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(headingLabel, gbc);

        JButton creditCardButton = new JButton("Pay by Credit Card");
        creditCardButton.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(creditCardButton, gbc);

        JButton cashButton = new JButton("Pay by Cash");
        cashButton.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(cashButton, gbc);

        JButton debitCardButton = new JButton("Pay by Debit Card");
        debitCardButton.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(debitCardButton, gbc);

        JButton coinsButton = new JButton("Pay with Coins");
        coinsButton.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(coinsButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the PayWindow
            AddWindow.open(); // Open the AddWindow again
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        frame.add(backButton, gbc);

        // ActionListener for Credit Card button
        creditCardButton.addActionListener(e -> itemProcessingControl.processPayment(PaymentMethod.CREDIT_CARD));

        // ActionListener for Cash button
        cashButton.addActionListener(e -> itemProcessingControl.processPayment(PaymentMethod.CASH));

        // ActionListener for Debit Card button
        debitCardButton.addActionListener(e -> itemProcessingControl.processPayment(PaymentMethod.DEBIT_CARD));
        
        // ActionListener for Coins button
        coinsButton.addActionListener(e -> itemProcessingControl.processPayment(PaymentMethod.COINS));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
