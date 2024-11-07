package com.careconnect.careconnect.GUI;

import javax.swing.*;

public class DoorLock {
    private boolean isLocked;  // Holds the status of the door lock

    public DoorLock() {
        // Initialize the lock status to "locked"
        isLocked = true;
    }

    // Method to toggle the lock status between "locked" and "open"
    public void toggleLock() {
        isLocked = !isLocked;  // Toggle the lock status
    }

    // Method to return the current lock status as a string
    public String getLockStatus() {
        return isLocked ? "Status: Door is now Locked" : "Status: Door in now Open";
    }

    }
