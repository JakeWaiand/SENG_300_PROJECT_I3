package com.thelocalmarketplace.software.gui;

import javax.swing.*;

import com.jjjwelectronics.Mass;
import com.thelocalmarketplace.software.StartSession;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SessionFrame extends JFrame {
    StartSession session;

    private JLabel totalPriceLabel;
    private DefaultListModel<String> pickedItemsModel;
    private JList<String> pickedItemsList;

    public SessionFrame(StartSession session) {
        this.session = session;
        setTitle("Self Checkout Station - Session");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        addTopPanel();
        addCenterPanel();

        // Optionally, uncomment this to add the catalogue panel directly
        // addCataloguePanel();

        initializeCustomerUpdateComponents();

        setVisible(true);
    }

    public void initializeCustomerUpdateComponents() {
        totalPriceLabel = new JLabel("Total Price: $0");
        pickedItemsModel = new DefaultListModel<>();
        pickedItemsList = new JList<>(pickedItemsModel);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(totalPriceLabel);
        bottomPanel.add(new JScrollPane(pickedItemsList));

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // public void updateCustomer(String description, long price, Mass weight) {
    //     session.setTotalPrice(session.getTotalPrice() + price);
    //     session.getPickedItems().add(description);
    //     session.getPriceList().add(price);
    //     session.getWeightList().add(weight);
        
    //    totalPriceLabel.setText("Total Price: $" + session.getTotalPrice());
    //     pickedItemsModel.addElement(description);
        
    //     JOptionPane.showMessageDialog(this, "Please put the item in the bagging area");
    // }




    private void addTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0x7293A0));
        topPanel.setPreferredSize(new Dimension(800, 100));

        JLabel titleLabel = new JLabel("Session Panel");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        add(topPanel, BorderLayout.NORTH);
    }

    private void addCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(new Color(0x7293A0));

        JButton enterPLUButton = new JButton("Enter PLU");
        enterPLUButton.addActionListener(e -> new PluFrame());

        JButton viewCatalogueButton = new JButton("View Catalogue");
        viewCatalogueButton.addActionListener(e -> addCataloguePanel());

        centerPanel.add(enterPLUButton);
        centerPanel.add(viewCatalogueButton);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void addCataloguePanel() {
        List<CatalogueItem> items = loadCatalogueItems();
        CataloguePanel cataloguePanel = new CataloguePanel(items);
        getContentPane().removeAll();
        add(cataloguePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private List<CatalogueItem> loadCatalogueItems() {
        List<CatalogueItem> items = new ArrayList<>();
        // Here, add some sample CatalogueItem objects.
        // Example: items.add(new CatalogueItem("Item 1", new ImageIcon("path/to/image1.jpg"), "Description 1", 10.99));
        // Repeat for more items
        return items;
    }


    public void setTotalPriceLabel(JLabel price){
		this.totalPriceLabel = price;
	}
	public JLabel getTotalPriceLabel() {
		return totalPriceLabel;
	}

    public void setPickedItemsModel(DefaultListModel<String> items){
        this.pickedItemsModel = items;
    }
    public DefaultListModel<String> getPickedItemsModel(){
        return pickedItemsModel;
    }











}
