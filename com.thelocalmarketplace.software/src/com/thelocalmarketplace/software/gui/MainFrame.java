package com.thelocalmarketplace.software.gui;

import javax.swing.*;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.OverloadedDevice;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.software.StartSession;
import java.awt.*;

public class MainFrame extends JFrame {

    private StartSession session;

    public MainFrame(StartSession session) {
        this.session = session;
        setTitle("Self Checkout Station");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        addTopPanel();
        addCenterPanel();

        setVisible(true);
    }

    private void addTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0x7293A0));
        topPanel.setPreferredSize(new Dimension(800, 100));

        JLabel titleLabel = new JLabel("Self Checkout Station");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        add(topPanel, BorderLayout.NORTH);
    }

    private void addCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.setBackground(new Color(0x7293A0));

        JButton startSessionButton = new JButton("Start Session");
        startSessionButton.addActionListener(e -> openSessionFrame());

        centerPanel.add(startSessionButton);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void openSessionFrame() {
        new SessionFrame(session);
        this.dispose();
    }

    public static void main(String[] args) throws OverloadedDevice, EmptyDevice {
   
        SwingUtilities.invokeLater(() -> {
            SelfCheckoutStationBronze station = new SelfCheckoutStationBronze();
            StartSession session = null;  // Initialize session to null
            try {
                session = new StartSession(station);
                new MainFrame(session); // Move inside try block to ensure session is initialized
            } catch (OverloadedDevice | EmptyDevice e) {
                e.printStackTrace();
                // Handle the error appropriately (e.g., show an error message)
            }
        });
    }
    
}


