package com.thelocalmarketplace.software.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Main {
    public void main(String[] args){

    


        // panel for the the self checkout
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0,0,700,200);
// Label for the selfCheck out panel 

        JLabel label = new JLabel();
        label.setText("Self CheckOut System");
        
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        label.setForeground(Color.yellow);
        label.setFont(new Font("Times New Roman", Font.BOLD, 34));
        
        label.setBounds(0, 0, 0, 0);

        
        // label.setBounds(0, 0, 0, 0);

        // JLabel start = new JLabel();
        // start.setText("Start Session");

        // start.setVerticalAlignment(SwingConstants.CENTER);
        // start.setHorizontalAlignment(SwingConstants.CENTER);


        JButton goToMainScreen = new JButton("Start");
        goToMainScreen.setBounds(10, 10, 100, 30); // Set the button bounds as needed

        goToMainScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMainGUI();
            }
        });

        add(goToMainScreen);
    }

    private void loadMainGUI() {
        // Code to load the main GUI directly within the panel
        // For example:
        JFrame mainFrame = new MainFrame(); // Replace MainFrame with your actual main frame class
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Other methods or variables can be added as needed
}




        MyFrame MyFrame = new MyFrame();
        MyFrame.add(label);
        // MyFrame.add(start);



    }
    
}

