package com.thelocalmarketplace.software;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jjjwelectronics.scanner.Barcode;
import com.thelocalmarketplace.software.gui.AttendantStationGUI;

public class AttendantControl {
    private boolean WDALram = false;
    private boolean WDdecision;
    private String attendantSearchResult;
    private AttendantStationGUI attendantStationGUI;  // Add reference to the GUI

    // Constructor
    public AttendantControl() {
        // Initialize the GUI reference
        this.attendantStationGUI = null;
    }

    // Setter method for GUI reference
    public void setAttendantStationGUI(AttendantStationGUI attendantStationGUI) {
        this.attendantStationGUI = attendantStationGUI;
    }

    public void attendantSearchItem(String searchResults) {
        attendantSearchResult = searchResults;
        Set<String> keySet = InternalDatabase.internalDataBase_Description.keySet();
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        // Use StringBuilder to concatenate matched item descriptions
        StringBuilder matchedDescriptions = new StringBuilder();

        for (int i = 0; i < keySet.size(); i++) {
            if (keyList.get(i).contains(attendantSearchResult)) {
                // Print or update GUI with item descriptions
                System.out.print(keyList.get(i));

                // Append the matched description to the StringBuilder
                matchedDescriptions.append(keyList.get(i)).append("\n");
            }
        }

        // Update the GUI with the concatenated descriptions
        attendantStationGUI.updateResultArea(matchedDescriptions.toString());
    }

    public void sendWDMessage() {
        WDALram = true;
        // Show visual alarm in GUI
        attendantStationGUI.runWDAlarm();
        // Show a dialog for the attendant to make a decision
        showDecisionDialog();
    }

    public void setWDDecision(String decision) {
        if (decision.equals("yes")) {
            this.WDdecision = true;
        } else {
            this.WDdecision = false;
        }
    }

    public boolean getWDDecision() {
        return this.WDdecision;
    }

    // New method to show decision dialog
    private void showDecisionDialog() {
        // Your existing implementation of showDecisionDialog
        // ...

        // Here, you can interact with the GUI as needed
        // Example: attendantStationGUI.showDecisionDialog();
    }

    // Additional methods for your features
    public void addItemByText(String itemText) {
        // Implementation for adding an item by text
    }

    public void enableSession() {
        // Implementation for enabling session
    }

    public void disableSession() {
        // Implementation for disabling session
    }
}
