package com.thelocalmarketplace.software.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PluFrame extends JFrame {
    private VirtualKeyboardPanel keyboardPanel;
    private JTextField pluTextField;

    public PluFrame() {
        setTitle("PLU Entry");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pluTextField = new JTextField(20);
        keyboardPanel = new VirtualKeyboardPanel();

        // Set up the PLU input field to show the keyboard when clicked
        pluTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                keyboardPanel.setTargetTextField(pluTextField);
                keyboardPanel.setVisible(true);
            }
        });

        // Layout the components
        add(pluTextField, BorderLayout.NORTH);
        add(keyboardPanel, BorderLayout.SOUTH);
        keyboardPanel.setVisible(false); // Initially hide the keyboard

        setVisible(true);
    }

    // Rest of the PluFrame class...
}



