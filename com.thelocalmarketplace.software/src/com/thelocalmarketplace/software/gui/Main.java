package com.thelocalmarketplace.software.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class CombinedFrame extends JFrame {

    public CombinedFrame() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Self Checkout System");

        // Create a container
        Container container = this.getContentPane();
        container.setBackground(Color.black);

        // Create a panel for Main components
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);

        // Set layout manager for the panel (you can customize this as needed)
        mainPanel.setLayout(null);

        // Add components from Main to the panel
        JLabel label = new JLabel();
        label.setText("Self CheckOut System");
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.yellow);
        label.setFont(new Font("Times New Roman", Font.BOLD, 34));
        mainPanel.add(label);

        JButton goToMainScreen = new JButton("Start");
        goToMainScreen.setBounds(10, 10, 100, 30);
        // Use lambda expression for ActionListener
        goToMainScreen.addActionListener(e -> loadMainGUI());
        mainPanel.add(goToMainScreen);

        JButton pluButton = new JButton("PLU Button");
        pluButton.setBounds(150, 10, 120, 30);
        // Use lambda expression for ActionListener
        pluButton.addActionListener(e -> handlePLUButtonClick());
        mainPanel.add(pluButton);

        JButton visualCatalogueButton = new JButton("Visual Catalogue");
        visualCatalogueButton.setBounds(300, 10, 150, 30);
        // Use lambda expression for ActionListener
        visualCatalogueButton.addActionListener(e -> handleVisualCatalogueButtonClick());
        mainPanel.add(visualCatalogueButton);

        // Create a panel for MyFrame components
        JPanel myFramePanel = new JPanel();
        myFramePanel.setBackground(Color.black);

        // Set layout manager for the panel (you can customize this as needed)
        myFramePanel.setLayout(null);

        // Add components from MyFrame to the panel
        JButton button = new JButton("Click me");
        button.setBounds(50, 50, 100, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click
                System.out.println("Button clicked!");
            }
        });
        myFramePanel.add(button);

        // Set border for the panel
        Border myFrameBorder = BorderFactory.createLineBorder(Color.yellow, 5);
        myFramePanel.setBorder(myFrameBorder);

        // Add the panels to the container
        container.add(mainPanel);
        container.add(myFramePanel);

        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
    }

    private void loadMainGUI() {
        JFrame mainFrame = new CombinedFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void handlePLUButtonClick() {
        // Add logic for PLU button click
        System.out.println("PLU Button clicked!");
    }

    private void handleVisualCatalogueButtonClick() {
        // Add logic for Visual Catalogue button click
        System.out.println("Visual Catalogue Button clicked!");
    }

    public static void main(String[] args) {
        new CombinedFrame();
    }
}
