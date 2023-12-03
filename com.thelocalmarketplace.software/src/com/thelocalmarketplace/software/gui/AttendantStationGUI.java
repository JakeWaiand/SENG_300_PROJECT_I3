package com.thelocalmarketplace.software.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendantStationGUI extends JFrame implements ActionListener{

    JPanel[] station1Panels = new JPanel[5];
    JButton[] station1Buttons = new JButton[8];
    JPanel[] station2Panels = new JPanel[5];
    JButton[] station2Buttons = new JButton[8];
    JPanel[] station3Panels = new JPanel[5];
    JButton[] station3Buttons = new JButton[8];

    public AttendantStationGUI() {
        setTitle("Attendant Station");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main layout manager
        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setVgap(10);
        mainLayout.setHgap(30);
        setLayout(mainLayout);

        // Title label
        JLabel titleLabel = new JLabel("Attendant Station", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        add(titleLabel, BorderLayout.NORTH);

        // Station panels
        JPanel stationPanel = new JPanel(new GridLayout(1, 3));
        add(stationPanel, BorderLayout.CENTER);

        JPanel stationBox1 = createStationBoxPanel(1, station1Panels, station1Buttons);
        stationBox1.setBorder(BorderFactory.createLineBorder(Color.black));
        stationPanel.add(stationBox1);

        JPanel stationBox2 = createStationBoxPanel(2, station2Panels, station2Buttons);
        stationBox2.setBorder(BorderFactory.createLineBorder(Color.black));
        stationPanel.add(stationBox2);

        JPanel stationBox3 = createStationBoxPanel(3, station3Panels, station3Buttons);
        stationBox3.setBorder(BorderFactory.createLineBorder(Color.black));
        stationPanel.add(stationBox3);

        setVisible(true);
    }


    private JPanel createStationBoxPanel(int stationNum, JPanel[] stationPanels, JButton[] stationButtons) {
        JPanel stationBox = new JPanel();
        stationBox.setLayout(new BoxLayout(stationBox, BoxLayout.Y_AXIS));

        stationBox.add(createStationTitle(stationNum));

        stationPanels[0] = createComponent("Ink remaining: 100%", Color.GRAY);
        stationButtons[0] = new JButton("Maintain Ink");
        stationButtons[0].addActionListener(this);
        stationBox.add(stationPanels[0]);
        stationBox.add(stationButtons[0]);

        stationBox.add(Box.createVerticalStrut(10));

        stationPanels[1] = createComponent("Paper remaining: 100%", Color.GRAY);
        stationButtons[1] = new JButton("Maintain Paper");
        stationButtons[1].addActionListener(this);
        stationBox.add(stationPanels[1]);
        stationBox.add(stationButtons[1]);

        stationBox.add(Box.createVerticalStrut(10));

        stationPanels[2] = createComponent("Coins storage: 0%", Color.GRAY);
        stationButtons[2] = new JButton("Maintain Coins");
        stationButtons[2].addActionListener(this);
        stationBox.add(stationPanels[2]);
        stationBox.add(stationButtons[2]);

        stationBox.add(Box.createVerticalStrut(10));

        stationPanels[3] = createComponent("Banknote storage: 0%", Color.GRAY);
        stationButtons[3] = new JButton("Maintain Banknotes");
        stationButtons[3].addActionListener(this);
        stationBox.add(stationPanels[3]);
        stationBox.add(stationButtons[3]);


        // Buttons side by side
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        stationButtons[4] = new JButton("Add Item");
        stationButtons[5] = new JButton("Weight Discrepancy");
        stationButtons[4].addActionListener(this);
        stationButtons[5].addActionListener(this);

        buttonPanel.add(stationButtons[4]);
        buttonPanel.add(stationButtons[5]);
        stationBox.add(buttonPanel);

        stationBox.add(Box.createVerticalStrut(10));

        stationPanels[4] = createComponent("Status", Color.green);
        stationBox.add(stationPanels[4]);

        stationBox.add(Box.createVerticalStrut(10));

        // Buttons side by side
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new GridLayout(1, 2));

        stationButtons[6] = new JButton("Enable");
        stationButtons[7] = new JButton("Disable");
        stationButtons[6].addActionListener(this);
        stationButtons[7].addActionListener(this);

        buttonPanel1.add(stationButtons[6]);
        buttonPanel1.add(stationButtons[7]);
        stationBox.add(buttonPanel1);

        return stationBox;
    }

    private void doSomething() {
        station1Panels[0].getComponents();
    }

    private JPanel createComponent(String labelText, Color color) {
        JPanel componentPanel = new JPanel();
        componentPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        componentPanel.setBackground(color);
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        componentPanel.add(label);
        return componentPanel;
    }

    private JPanel createStationTitle(int stationNum) {
        JPanel stationTitle = new JPanel();
        stationTitle.setBackground(Color.black);
        JLabel label = new JLabel("STATION " + stationNum);
        label.setFont(new Font("Serif", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        stationTitle.add(label);
        return stationTitle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // station 1 buttons ==========================================================================
        if (e.getSource() == station1Buttons[0]) {
            System.out.println("Station 1 Ink Maintenance");
        }
        else if (e.getSource() == station1Buttons[1]) {
            System.out.println("Station 1 Paper Maintenance");
        }
        else if (e.getSource() == station1Buttons[2]) {
            System.out.println("Station 1 Coin Maintenance");
        }
        else if (e.getSource() == station1Buttons[3]) {
            System.out.println("Station 1 Banknote Maintenance");
        }
        else if (e.getSource() == station1Buttons[4]) {
            System.out.println("Station 1 Add Item");
        }
        else if (e.getSource() == station1Buttons[5]) {
            System.out.println("Station 1 Weight Discrepancy");
        }
        else if (e.getSource() == station1Buttons[6]) {
            station1Panels[4].setBackground(Color.green);
        }
        else if (e.getSource() == station1Buttons[7]) {
            station1Panels[4].setBackground(Color.red);
        }
        // Station 2 buttons ==========================================================================
        else if (e.getSource() == station2Buttons[0]) {
            System.out.println("Station 2 Ink Maintenance");
        }
        else if (e.getSource() == station2Buttons[1]) {
            System.out.println("Station 2 Paper Maintenance");
        }
        else if (e.getSource() == station2Buttons[2]) {
            System.out.println("Station 2 Coin Maintenance");
        }
        else if (e.getSource() == station2Buttons[3]) {
            System.out.println("Station 2 Banknote Maintenance");
        }
        else if (e.getSource() == station2Buttons[4]) {
            System.out.println("Station 2 Add Item");
        }
        else if (e.getSource() == station2Buttons[5]) {
            System.out.println("Station 2 Weight Discrepancy");
        }
        else if (e.getSource() == station2Buttons[6]) {
            station2Panels[4].setBackground(Color.green);
        }
        else if (e.getSource() == station2Buttons[7]) {
            station2Panels[4].setBackground(Color.red);
        }

        // Station 3 buttons ==========================================================================
        else if (e.getSource() == station3Buttons[0]) {
            System.out.println("Station 3 Ink Maintenance");
        }
        else if (e.getSource() == station3Buttons[1]) {
            System.out.println("Station 3 Paper Maintenance");
        }
        else if (e.getSource() == station3Buttons[2]) {
            System.out.println("Station 3 Coin Maintenance");
        }
        else if (e.getSource() == station3Buttons[3]) {
            System.out.println("Station 3 Banknote Maintenance");
        }
        else if (e.getSource() == station3Buttons[4]) {
            System.out.println("Station 3 Add Item");
        }
        else if (e.getSource() == station3Buttons[5]) {
            System.out.println("Station 3 Weight Discrepancy");
        }
        else if (e.getSource() == station3Buttons[6]) {
            station3Panels[4].setBackground(Color.green);
        }
        else if (e.getSource() == station3Buttons[7]) {
            station3Panels[4].setBackground(Color.red);
        }
    }

    public static void main(String[] args) {
        AttendantStationGUI gui = new AttendantStationGUI();
    }
}
