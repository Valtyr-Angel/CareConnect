package com.careconnect.careconnect.GUI;

import javax.swing.*;

public class Dørlås {
    private boolean isLocked;  // Holds the status of the door lock

    public Dørlås() {
        // Initialize the lock status to "locked"
        isLocked = true;
    }

    // Method to toggle the lock status between "locked" and "open"
    public void toggleLock() {
        isLocked = !isLocked;  // Toggle the lock status
    }

    // Method to return the current lock status as a string
    public String getLockStatus() {
        return isLocked ? "Status: Locked" : "Status: Open";
    }

    // Perform action (just as a simple example, this method is no longer used)
    public void performAction() {
        String statusMessage = isLocked ? "Døren er låst." : "Døren er åpen.";
        JOptionPane.showMessageDialog(null, statusMessage);
    }
}
