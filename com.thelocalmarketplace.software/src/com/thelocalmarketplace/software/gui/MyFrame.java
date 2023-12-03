package com.thelocalmarketplace.software.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class MyFrame extends JFrame{

    MyFrame(){

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setResizable(false);

        this.setSize(700,700);
        this.setTitle("Self Checkout System");
        this.getContentPane().setBackground(Color.black);
        // this.setLayout(null);
        Border Border = BorderFactory.createLineBorder(Color.yellow,5);
        this.getRootPane().setBorder(Border);
        

    }
    
}
