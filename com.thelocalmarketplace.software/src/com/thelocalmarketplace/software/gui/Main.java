package com.thelocalmarketplace.software.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Main {
    public static void main(String[] args){

    


        
        JLabel label = new JLabel();
        label.setText("Self CheckOut System");
        
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        label.setForeground(Color.yellow);
        label.setFont(new Font("Times New Roman", Font.BOLD, 34));
        
        // label.setBounds(0, 0, 0, 0);

        // JLabel start = new JLabel();
        // start.setText("Start Session");

        // start.setVerticalAlignment(SwingConstants.CENTER);
        // start.setHorizontalAlignment(SwingConstants.CENTER);


        JButton goToAddItem = new JButton("Start Session");
        goToAddItem.addMouseListener(new MouseAdapter() {
            public void mouseClick(MouseEvent e){
                
            }
        });




        MyFrame MyFrame = new MyFrame();
        MyFrame.add(label);
        // MyFrame.add(start);



    }
    
}

