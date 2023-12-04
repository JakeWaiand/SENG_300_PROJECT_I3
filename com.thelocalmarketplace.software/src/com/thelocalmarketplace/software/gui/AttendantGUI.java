package com.thelocalmarketplace.software.gui;

	import java.awt.BorderLayout;
	import java.awt.Dimension;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.GridLayout;
	import java.awt.Insets;
	import java.awt.Toolkit;

	import javax.swing.JFrame;

	import com.jjjwelectronics.screen.ITouchScreen;
	import com.thelocalmarketplace.GUI.customComponents.Colors;
	import com.thelocalmarketplace.GUI.customComponents.PlainButton;
	import com.thelocalmarketplace.software.attendant.Attendant;

	public class AttendantGUI {
	    private Attendant attendant;
	    private ITouchScreen screen;
	    private JFrame attendantFrame;

	    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	    private static final int DEFAULT_WIDTH = 1500;
	    private static final int DEFAULT_HEIGHT = 800;

	    public AttendantGUI(Attendant attendant, ITouchScreen screen) {
	        this.attendant = attendant;
	        this.screen = screen;
	        initializeFrame();
	    }

	    private void initializeFrame() {
	        int width = Math.min((int) SCREEN_SIZE.getWidth(), DEFAULT_WIDTH);
	        int height = Math.min((int) SCREEN_SIZE.getHeight(), DEFAULT_HEIGHT);

	        screen.getFrame().setTitle("Attendant Screen GUI");
	        screen.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        screen.getFrame().setSize(new Dimension(width, height));
	        screen.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);

	        setupContentPane();
	        setupButtons();
	        
	        screen.setVisible(true);
	    }

	    private void setupContentPane() {
	        screen.getFrame().getContentPane().setLayout(new GridLayout(10, 10));
	        screen.getFrame().getContentPane().setBackground(Colors.color1);
	    }

	    private void setupButtons() {
	        GridBagConstraints gridConstraints = new GridBagConstraints();
	        gridConstraints.insets = new Insets(1, 1, 1, 1);
	        gridConstraints.gridx = 0;
	        gridConstraints.gridy = 0;

	        screen.getFrame().getContentPane().add(new PlainButton("test", Colors.color4), gridConstraints);
	    }
	}
}
