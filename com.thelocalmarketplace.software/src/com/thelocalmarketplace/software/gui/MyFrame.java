package com.thelocalmarketplace.software.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MyFrame extends JFrame {

    MyFrame() {

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setTitle("Self Checkout System");

        // Create a container
        Container container = this.getContentPane();
        container.setBackground(Color.black);

        // Create a panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);

        // Set layout manager for the panel (you can customize this as needed)
        panel.setLayout(null);

        // Add components to the panel
        JButton button = new JButton("Click me");
        button.setBounds(50, 50, 100, 30);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click
                System.out.println("Button clicked!");
            }
        });

        // Add the button to the panel
        panel.add(button);

        // Set border for the panel
        Border border = BorderFactory.createLineBorder(Color.yellow, 5);
        panel.setBorder(border);

        // Add the panel to the container
        container.add(panel);

        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
