package com.careconnect.careconnect.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private DoorLock doorLock = new DoorLock();

    public Application() {
        // Create the main frame
        frame = new JFrame("CareConnect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Use CardLayout to switch between different screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different "cards" (screens) to the main panel
        mainPanel.add(createLoginMethodSelectionPanel(), "LoginMethod");
        mainPanel.add(createRegularLoginPanel(), "RegularLogin");
        mainPanel.add(createEmployeeCardLoginPanel(), "AnsattkortLogin");
        mainPanel.add(createBiometricLoginPanel(), "BiometricLogin");
        mainPanel.add(createMainMenuPanel(), "MainMenu");
        mainPanel.add(createJournalOptionsPanel(), "JournalOptions");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Panel for login method selection
    private JPanel createLoginMethodSelectionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Set a common button size
        Dimension buttonSize = new Dimension(240, 35);

        // Create label and buttons
        JLabel label = new JLabel("Choose a login method:");
        JButton regularLoginButton = new JButton("Login with username and password");
        JButton ansattkortLoginButton = new JButton("Login with employee card");
        JButton biometricLoginButton = new JButton("Login with biometric scan");
        JButton exitButton = new JButton("Exit");

        // Set preferred size for all buttons
        regularLoginButton.setPreferredSize(buttonSize);
        ansattkortLoginButton.setPreferredSize(buttonSize);
        biometricLoginButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridy = 1;
        panel.add(regularLoginButton, constraints);

        constraints.gridy = 2;
        panel.add(ansattkortLoginButton, constraints);

        constraints.gridy = 3;
        panel.add(biometricLoginButton, constraints);

        constraints.gridy = 4;
        panel.add(exitButton, constraints);

        // Add ActionListeners
        regularLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegularLogin");
            }
        });

        ansattkortLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "AnsattkortLogin");
            }
        });

        biometricLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "BiometricLogin");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return panel;
    }

    // Regular login panel
    private JPanel createRegularLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);
        Dimension fieldSize = new Dimension(220, 30);
        userField.setPreferredSize(fieldSize);
        passField.setPreferredSize(fieldSize);

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("back");
        Dimension buttonSize = new Dimension(220, 30);
        loginButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        JLabel messageLabel = new JLabel(""); // For error messages

        // Add components
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(userLabel, constraints);

        constraints.gridx = 1;
        panel.add(userField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passLabel, constraints);

        constraints.gridx = 1;
        panel.add(passField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        constraints.gridy = 3;
        panel.add(backButton, constraints);

        constraints.gridy = 4;
        panel.add(messageLabel, constraints);

        // ActionListeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals("123") && password.equals("123")) {
                    cardLayout.show(mainPanel, "MainMenu");
                } else {
                    messageLabel.setText("Invalid username or password!");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");
            }
        });

        return panel;
    }

    // Employee card login panel
    private JPanel createEmployeeCardLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Enter code to proceed:");
        JTextField codeField = new JTextField(10);
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");
        Dimension fieldSize = new Dimension(220, 30);
        codeField.setPreferredSize(fieldSize);
        loginButton.setPreferredSize(new Dimension(220, 30));
        backButton.setPreferredSize(new Dimension(220, 30));

        // Add components
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridy = 1;
        panel.add(codeField, constraints);

        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        constraints.gridy = 3;
        panel.add(backButton, constraints);

        // ActionListeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeField.getText();
                if (code.equals("123")) {
                    cardLayout.show(mainPanel, "MainMenu");
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid code!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");
            }
        });

        return panel;
    }

    // Biometric login panel
    private JPanel createBiometricLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Use biometric scan to login:");
        JButton scanButton = new JButton("Use Biometric Scan");
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false); // Hide progress bar initially
        JButton backButton = new JButton("Back");

        scanButton.setPreferredSize(new Dimension(220, 30));
        progressBar.setPreferredSize(new Dimension(220, 30));
        backButton.setPreferredSize(new Dimension(220, 30));

        // Add components
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridy = 1;
        panel.add(scanButton, constraints);

        constraints.gridy = 2;
        panel.add(progressBar, constraints);

        constraints.gridy = 3;
        panel.add(backButton, constraints);

        // ActionListeners
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progressBar.setVisible(true);
                new Thread(() -> {
                    try {
                        // Simulate scanning (this would be replaced by actual biometric code)
                        Thread.sleep(3000); // Simulate delay for biometric scan
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    progressBar.setVisible(false);
                    cardLayout.show(mainPanel, "MainMenu");
                }).start();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");
            }
        });

        return panel;
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Main menu buttons
        JButton journalButton = new JButton("Journal");
        JButton doorLockButton = new JButton("DoorLock");
        JButton logoutButton = new JButton("Logout");

        Dimension buttonSize = new Dimension(220, 30);
        journalButton.setPreferredSize(buttonSize);
        doorLockButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(journalButton, constraints);

        constraints.gridy = 1;
        panel.add(doorLockButton, constraints);

        constraints.gridy = 2;
        panel.add(logoutButton, constraints);

        // ActionListeners
        journalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "JournalOptions");
            }
        });

        doorLockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle the door lock status
                doorLock.toggleLock();

                // Show a pop-up with the current door lock status
                JOptionPane.showMessageDialog(frame, doorLock.getLockStatus());
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");
            }
        });

        return panel;
    }



    // Journal options panel
    private JPanel createJournalOptionsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Journal option buttons
        JButton scanBraceletButton = new JButton("Scan Bracelet");
        JButton brukpatientIDButton = new JButton("Use PatientID");
        JButton backButton = new JButton("Back");

        Dimension buttonSize = new Dimension(220, 30);
        scanBraceletButton.setPreferredSize(buttonSize);
        brukpatientIDButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);

        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(scanBraceletButton, constraints);

        constraints.gridy = 1;
        panel.add(brukpatientIDButton, constraints);

        constraints.gridy = 2;
        panel.add(backButton, constraints);

        // ActionListeners
        scanBraceletButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new JFrame for the scan process with a progress bar
                JFrame scanFrame = new JFrame("Scan Bracelet");
                scanFrame.setSize(400, 150);
                scanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel scanPanel = new JPanel(new GridBagLayout());
                GridBagConstraints scanConstraints = new GridBagConstraints();
                scanConstraints.insets = new Insets(5, 5, 5, 5);

                JLabel scanLabel = new JLabel("Scanning... Please wait.");
                JProgressBar progressBar = new JProgressBar();
                progressBar.setIndeterminate(true);
                progressBar.setPreferredSize(new Dimension(300, 30));

                scanConstraints.gridx = 0;
                scanConstraints.gridy = 0;
                scanPanel.add(scanLabel, scanConstraints);

                scanConstraints.gridy = 1;
                scanPanel.add(progressBar, scanConstraints);

                scanFrame.add(scanPanel);
                scanFrame.setLocationRelativeTo(null);  // Center the window
                scanFrame.setVisible(true);

                // Simulate scanning process and display the result after a delay
                new Thread(() -> {
                    try {
                        // Simulate the scanning delay (replace with actual scan logic)
                        Thread.sleep(3000); // Simulate scan for 3 seconds

                        // Close the scan frame
                        scanFrame.dispose();

                        // Show the scan completed message
                        JOptionPane.showMessageDialog(scanFrame, "Scan completed");

                        // Create a Journal object to display the patient data
                        Journal journal = new Journal();
                        journal.performAction();  // This reads and displays the .json content
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });

        brukpatientIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show a pop-up asking for PasientID (for example, 123)
                String patientID = JOptionPane.showInputDialog(panel, "Enter PastentID:");

                // Verify if the entered PasientID is 123
                if (patientID != null && patientID.equals("123")) {
                    // If the ID is valid, call the Journal's method to fetch the data and display it
                    Journal journal = new Journal();
                    journal.performAction();  // This will read from the JSON file and display the patient information
                } else {
                    // Show an error message if the entered ID is incorrect
                    JOptionPane.showMessageDialog(panel, "Invalid PatientID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MainMenu");
            }
        });

        return panel;
    }
    private void openDoorLockWindow() {
        // Create a new JFrame for the door lock control
        JFrame doorLockFrame = new JFrame("Door Lock Control");
        doorLockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        doorLockFrame.setSize(400, 200);

        // Panel for door lock control
        JPanel doorLockPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Create the buttons and label
        JButton changeStatusButton = new JButton("Change Doorlock Status");
        JButton backButton = new JButton("Back");
        JLabel statusLabel = new JLabel("Current Status: " + doorLock.getLockStatus());

        changeStatusButton.setPreferredSize(new Dimension(220, 30));
        backButton.setPreferredSize(new Dimension(220, 30));

        // Add components to the door lock panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        doorLockPanel.add(statusLabel, constraints);

        constraints.gridy = 1;
        doorLockPanel.add(changeStatusButton, constraints);

        constraints.gridy = 2;
        doorLockPanel.add(backButton, constraints);

        // ActionListeners
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle the door lock status
                doorLock.toggleLock();

                // Update the status label with the new status
                statusLabel.setText("Current Status: " + doorLock.getLockStatus());

                // Show a message popup with the door lock status
                JOptionPane.showMessageDialog(doorLockFrame, doorLock.getLockStatus());
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doorLockFrame.dispose();  // Close the door lock window
            }
        });

        // Add the panel to the frame and make it visible
        doorLockFrame.add(doorLockPanel);
        doorLockFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application();
            }
        });
    }
}
