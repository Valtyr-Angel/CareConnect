package com.careconnect.careconnect.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Applikasjon extends JFrame {
    private CardLayout cardLayout;  // Layout manager for switching between panels
    private JPanel mainPanel;       // Main panel to hold different cards
    private Journal journal;        // Instance of Journal class
    private Dørlås dorlås;          // Instance of Dørlås class
    private JLabel lockStatusLabel; // Label to display lock status

    public Applikasjon() {
        // Initialize the Journal and Dørlås instances
        journal = new Journal();
        dorlås = new Dørlås();

        // Set window properties
        setTitle("CareConnect");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();  // Initialize CardLayout
        mainPanel = new JPanel(cardLayout);  // Create main panel with CardLayout

        // Add the login method selection screen
        JPanel loginMethodPanel = createLoginMethodSelectionPanel();
        mainPanel.add(loginMethodPanel, "LoginMethod");

        // Add individual login panels
        JPanel regularLoginPanel = createRegularLoginPanel();
        JPanel ansattkortLoginPanel = createAnsattkortLoginPanel();
        JPanel biometricLoginPanel = createBiometricLoginPanel();

        // Add main menu and feature panels (same as your original structure)
        JPanel mainMenu = createMainMenu();
        JPanel scannerPanel = createScannerPanel();
        JPanel journalPanel = createJournalPanel();
        JPanel dorlåsPanel = createDorlåsPanel();

        mainPanel.add(regularLoginPanel, "RegularLogin");
        mainPanel.add(ansattkortLoginPanel, "AnsattkortLogin");
        mainPanel.add(biometricLoginPanel, "BiometricLogin");
        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(scannerPanel, "Scanner");
        mainPanel.add(journalPanel, "Journal");
        mainPanel.add(dorlåsPanel, "Dørlås");

        // Add mainPanel to the JFrame
        add(mainPanel);

        // Show the login method selection screen first
        cardLayout.show(mainPanel, "LoginMethod");
    }

    // Method to create the login method selection screen
    private JPanel createLoginMethodSelectionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Choose a login method:");
        JButton regularLoginButton = new JButton("Login med brukernavn og passord");
        JButton ansattkortLoginButton = new JButton("Login med bruk av ansattkort");
        JButton biometricLoginButton = new JButton("Login med biometric scan");
        JButton exitButton = new JButton("Exit");

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

        // Action listener for Regular Login button
        regularLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegularLogin");  // Go to regular login screen
            }
        });

        // Action listener for Ansattkort Login button
        ansattkortLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "AnsattkortLogin");  // Go to ansattkort login screen
            }
        });

        // Action listener for Biometric Login button
        biometricLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "BiometricLogin");  // Go to biometric login screen
            }
        });

        // Action listener for Exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application
            }
        });

        return panel;
    }

    // Method to create the Regular Login panel
    private JPanel createRegularLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        JButton tilbakeButton = new JButton("Tilbake");
        JLabel messageLabel = new JLabel(""); // For displaying error messages

        // Add components to the panel
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
        panel.add(tilbakeButton, constraints);

        constraints.gridy = 4;
        panel.add(messageLabel, constraints);

        // Action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                // Check if username and password are both "123"
                if (username.equals("123") && password.equals("123")) {
                    cardLayout.show(mainPanel, "MainMenu"); // Show main menu on success
                } else {
                    messageLabel.setText("Invalid username or password!");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

        // Action listener for Tilbake button
        tilbakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");  // Go back to login method selection
            }
        });

        return panel;
    }

    // Method to create the Ansattkort Login panel
    private JPanel createAnsattkortLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Enter Employee Card Code:");
        JTextField cardField = new JTextField(10);
        JButton loginButton = new JButton("Login with Employee Card");
        JButton tilbakeButton = new JButton("Tilbake");
        JLabel messageLabel = new JLabel("");

        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridx = 1;
        panel.add(cardField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        constraints.gridy = 2;
        panel.add(tilbakeButton, constraints);

        constraints.gridy = 3;
        panel.add(messageLabel, constraints);

        // Action listener for Ansattkort login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeCardCode = cardField.getText();

                // Check if the card code is "123"
                if (employeeCardCode.equals("123")) {
                    cardLayout.show(mainPanel, "MainMenu"); // Show main menu on success
                } else {
                    messageLabel.setText("Invalid employee card code!");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

        // Action listener for Tilbake button
        tilbakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");  // Go back to login method selection
            }
        });

        return panel;
    }

    // Method to create the Biometric Login panel
    private JPanel createBiometricLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Biometric Scan not implemented yet!", SwingConstants.CENTER); // Centered text
        JButton tilbakeButton = new JButton("Tilbake");

        panel.add(label, BorderLayout.CENTER);
        panel.add(tilbakeButton, BorderLayout.SOUTH);

        // Action listener for Tilbake button
        tilbakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");  // Go back to login method selection
            }
        });

        return panel;
    }

    // Method to create the main menu panel (same as your original code)
    private JPanel createMainMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new FlowLayout());  // Simple layout for buttons

        JButton button1 = new JButton("Scanner");
        JButton button2 = new JButton("Journal");
        JButton button3 = new JButton("Dørlås");
        JButton logoutButton = new JButton("Logout");

        // Add buttons to the main menu
        mainMenu.add(button1);
        mainMenu.add(button2);
        mainMenu.add(button3);
        mainMenu.add(logoutButton);  // Add logout button at the bottom

        // Action listeners for buttons to switch to the respective panels
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Scanner");  // Show Scanner panel
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Journal");  // Show Journal panel
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Dørlås");  // Show Dørlås panel
                updateLockStatus(); // Update lock status when the panel is shown
            }
        });

        // Action listener for logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");  // Go back to login method selection
            }
        });

        return mainMenu;
    }

    // Method to show the Scanner panel
    private void showScanner() {
        cardLayout.show(mainPanel, "Scanner");
    }

    // Method to show the Journal panel
    private void showJournal() {
        cardLayout.show(mainPanel, "Journal");
    }

    // Method to show the Dørlås panel
    private void showDorlås() {
        cardLayout.show(mainPanel, "Dørlås");
        updateLockStatus(); // Update lock status when the panel is shown
    }

    // Method to create the Scanner panel
    private JPanel createScannerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("This is the Scanner panel", SwingConstants.CENTER); // Center text
        panel.add(label, BorderLayout.CENTER);

        // Create Back button
        JButton backButton = new JButton("Tilbake");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MainMenu");  // Go back to main menu
            }
        });
        panel.add(backButton, BorderLayout.NORTH);  // Add back button at the top
        return panel;
    }

    // Method to create the Journal panel
    private JPanel createJournalPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create Back button and place it at the top
        JButton backButton = new JButton("Tilbake");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MainMenu");  // Go back to main menu
            }
        });
        panel.add(backButton, BorderLayout.NORTH);  // Back button at the top

        // Create a button to show the journal and place it in the center
        JButton showJournalButton = new JButton("Show Journal");
        showJournalButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        showJournalButton.setBackground(new Color(173, 216, 230)); // Light blue color
        panel.add(showJournalButton, BorderLayout.CENTER);  // Add button in the center

        // Action listener for "Show Journal" button
        showJournalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                journal.performAction();  // Call the performAction method from Journal class
            }
        });

        // Create a label for status and place it at the bottom
        JLabel statusLabel = new JLabel("This is the Journal panel", SwingConstants.CENTER); // Center text
        panel.add(statusLabel, BorderLayout.SOUTH);  // Status label at the bottom

        return panel;
    }

    // Method to create the Dørlås panel
    private JPanel createDorlåsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Create Back button and place it at the top
        JButton backButton = new JButton("Tilbake");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "MainMenu");  // Go back to main menu
            }
        });
        panel.add(backButton, BorderLayout.NORTH);  // Back button at the top

        // Create a button to toggle the door lock and place it in the center
        JButton toggleLockButton = new JButton("Toggle Dør Lås");
        toggleLockButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        toggleLockButton.setBackground(new Color(173, 216, 230)); // Light blue color
        panel.add(toggleLockButton, BorderLayout.CENTER);  // Add button in the center

        // Create a label to show lock status and place it at the bottom
        lockStatusLabel = new JLabel("", SwingConstants.CENTER);  // Centered text
        panel.add(lockStatusLabel, BorderLayout.SOUTH);  // Add label to the bottom

        // Action listener for toggle button
        toggleLockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dorlås.toggleLock();  // Call the toggleLock method from Dørlås class
                updateLockStatus();   // Update the status label
            }
        });

        return panel;
    }

    // Method to update the lock status label
    private void updateLockStatus() {
        lockStatusLabel.setText(dorlås.getLockStatus()); // Get the lock status from Dørlås class
    }

    public static void main(String[] args) {
        // Create and display the window
        Applikasjon window = new Applikasjon();
        window.setVisible(true);
    }
}
