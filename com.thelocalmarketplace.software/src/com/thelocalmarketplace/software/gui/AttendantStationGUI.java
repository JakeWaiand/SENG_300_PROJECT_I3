package com.thelocalmarketplace.software.gui;

import javax.swing.*;

import com.thelocalmarketplace.software.AttendantControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendantStationGUI extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JButton weightDiscrepancyButton;

    private AttendantControl attendantControl;

    // Constructor to initialize the GUI
    public AttendantStationGUI(AttendantControl attendantControl) {
        this.attendantControl = attendantControl;

        setTitle("Attendant Station");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(); // Initialize GUI components
        addListeners(); // Add event listeners

        setVisible(true);
    }

    // Initialize GUI components
    private void initComponents() {
        searchField = new JTextField();
        // Set preferred size for the search field
        searchField.setPreferredSize(new Dimension(300, 40));

        searchButton = new JButton("Search");
        resultArea = new JTextArea();
        weightDiscrepancyButton = new JButton("Weight Discrepancy");

        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Add components to the main frame
        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(weightDiscrepancyButton, BorderLayout.SOUTH);
    }

    // Add event listeners to buttons
    private void addListeners() {
        // Search Button Listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get search result from the text field and call attendantSearchItem method
                String searchResult = searchField.getText();
                attendantControl.attendantSearchItem(searchResult);
            }
        });

        // Weight Discrepancy Button Listener
        weightDiscrepancyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call sendWDMessage method when the button is clicked
                attendantControl.sendWDMessage();
                showDecisionDialog();
            }
        });

        // Additional feature: Add Item by Text Button Listener
        JButton addItemByTextButton = new JButton("Add Item by Text");
        addItemByTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a dialog to input item text
                String itemText = JOptionPane.showInputDialog(AttendantStationGUI.this, "Enter item text:");
                if (itemText != null) {
                    attendantControl.addItemByText(itemText);
                }
            }
        });

        // Additional feature: Enable Session Button Listener
        JButton enableSessionButton = new JButton("Enable Session");
        enableSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method for enabling session (replace with actual functionality)
                enableSession();
            }
        });

        // Additional feature: Disable Session Button Listener
        JButton disableSessionButton = new JButton("Disable Session");
        disableSessionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method for disabling session (replace with actual functionality)
                disableSession();
            }
        });

        // Add additional buttons to the main frame
        JPanel additionalButtonsPanel = new JPanel();
        additionalButtonsPanel.add(addItemByTextButton);
        additionalButtonsPanel.add(enableSessionButton);
        additionalButtonsPanel.add(disableSessionButton);

        // Add additional buttons panel to the main frame
        add(additionalButtonsPanel, BorderLayout.WEST);
    }

    // Show a dialog for the attendant to make a decision on weight discrepancy
    private void showDecisionDialog() {
        String[] options = {"Yes", "No"};
        int choice = JOptionPane.showOptionDialog(this,
                "Weight Discrepancy Detected. Approve?",
                "Weight Discrepancy",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        // Determine the decision based on the user's choice
        String decision = (choice == JOptionPane.YES_OPTION) ? "yes" : "no";
        attendantControl.setWDDecision(decision);
    }

    // Placeholder method for enabling session (replace with actual functionality)
    private void enableSession() {
        JOptionPane.showMessageDialog(this, "Session Enabled!");
    }

    // Placeholder method for disabling session (replace with actual functionality)
    private void disableSession() {
        JOptionPane.showMessageDialog(this, "Session Disabled!");
    }

    // Update the result area with the given text
    public void updateResultArea(String result) {
        resultArea.setText(result);
    }

    // Show visual alarm
    public void runWDAlarm() {
        // Implement the visual alarm (e.g., change background color or display a popup)
        JOptionPane.showMessageDialog(this, "Weight Discrepancy Alarm!");
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AttendantControl attendantControl = new AttendantControl();
                AttendantStationGUI attendantStationGUI = new AttendantStationGUI(attendantControl);
                attendantControl.setAttendantStationGUI(attendantStationGUI); // Set the GUI reference
            }
        });
    }
}
