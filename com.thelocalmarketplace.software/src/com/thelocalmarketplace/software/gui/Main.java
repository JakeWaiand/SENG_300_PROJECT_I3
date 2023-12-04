package com.thelocalmarketplace.software.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();

        JLabel label = new JLabel();
        label.setText("Self CheckOut System");

        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        label.setForeground(Color.yellow);
        label.setFont(new Font("Times New Roman", Font.BOLD, 34));

        JButton goToMainScreen = new JButton("Start");
        goToMainScreen.setBounds(10, 10, 100, 30);

        // Use lambda expression for ActionListener
        goToMainScreen.addActionListener(e -> loadMainGUI());

        mainFrame.setLayout(null);
        mainFrame.add(label);
        mainFrame.add(goToMainScreen);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private static void loadMainGUI() {
        JFrame mainFrame = new MyFrame(); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
