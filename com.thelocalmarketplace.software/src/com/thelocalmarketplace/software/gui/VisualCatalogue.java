package com.thelocalmarketplace.software.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import com.thelocalmarketplace.software.InternalDatabase;

public class VisualCatalogue extends JPanel {

    private Map<String, Integer> itemsQuantity;

    public VisualCatalogue() {
        super(new BorderLayout());
        setBackground(new Color(0x7293A0));
        itemsQuantity = new HashMap<>();

        JLabel titleLabel = new JLabel("Self Checkout Station", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0x7293A0));
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        itemsPanel.setBackground(new Color(0x7293A0));

        // Use the internal database to get items information
        for (String description : InternalDatabase.internalDataBase_Description.keySet()) {
            JPanel itemPanel = createItemPanel(description);
            itemsPanel.add(itemPanel);
        }

        this.add(itemsPanel, BorderLayout.CENTER);

        JButton addItemButton = new JButton("Add item");
        addItemButton.addActionListener(this::addItemAction);
        this.add(addItemButton, BorderLayout.SOUTH);
    }

    private JPanel createItemPanel(String itemName) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(itemName));

        JLabel quantityLabel = new JLabel("0", SwingConstants.CENTER);
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemsQuantity.put(itemName, 0);

        JButton minusButton = new JButton("-");
        minusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        minusButton.addActionListener(e -> updateQuantity(itemName, -1, quantityLabel));

        JButton plusButton = new JButton("+");
        plusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        plusButton.addActionListener(e -> updateQuantity(itemName, 1, quantityLabel));

        panel.add(Box.createVerticalGlue());
        panel.add(quantityLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(minusButton);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(plusButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private void updateQuantity(String itemName, int delta, JLabel quantityLabel) {
        int currentQuantity = itemsQuantity.get(itemName);
        int newQuantity = Math.max(0, currentQuantity + delta);
        itemsQuantity.put(itemName, newQuantity);
        quantityLabel.setText(String.valueOf(newQuantity));
    }

    private void addItemAction(ActionEvent e) {
        // Replace "session" with the appropriate variable or parameter
        SwingUtilities.getWindowAncestor(this).dispose();
        // AddWindow.open(session);
    }

    public static void open() {
        JFrame frame = new JFrame("Visual Catalogue");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        VisualCatalogue visualCatalogue = new VisualCatalogue();
        frame.add(visualCatalogue);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}