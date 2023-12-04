package com.thelocalmarketplace.software.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class CataloguePanel extends JPanel {
    private JList<CatalogueItem> itemList;

    public CataloguePanel(List<CatalogueItem> items) {
        setLayout(new BorderLayout());
        itemList = new JList<>(new Vector<>(items));
        itemList.setCellRenderer(new CatalogueCellRenderer());
        add(new JScrollPane(itemList), BorderLayout.CENTER);
    }

    private class CatalogueCellRenderer extends JPanel implements ListCellRenderer<CatalogueItem> {
        @Override
        public Component getListCellRendererComponent(JList<? extends CatalogueItem> list, CatalogueItem item, int index, boolean isSelected, boolean cellHasFocus) {
            // Customize this method to display the item properties
            return this;
        }
    }
}
