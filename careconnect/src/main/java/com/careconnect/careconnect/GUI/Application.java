package com.careconnect.careconnect.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame {
    private CardLayout cardLayout;      // Layoutmanager for å bytte mellom paneler
    private JPanel mainPanel;           // Hovedpanelet som holder alle "cards"
    private Journal journal;            // Instans av klassen Journal
    private DoorLock doorLock;          // Instans av klassen DoorLock
    private JLabel lockStatusLabel;     // Label for å vise låsestatus

    public Application() {
        // Initialiser instansene av Journal og DoorLock
        journal = new Journal();
        doorLock = new DoorLock();

        // Sett egenskaper for vinduet
        setTitle("CareConnect");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();  // Initialiser CardLayout
        mainPanel = new JPanel(cardLayout);  // Opprett hovedpanel med CardLayout

        // Legg til skjerm for valg av innloggingsmetode
        JPanel loginMethodPanel = createLoginMethodSelectionPanel();
        mainPanel.add(loginMethodPanel, "LoginMethod");

        // Legg til individuelle innloggingspaneler
        JPanel regularLoginPanel = createRegularLoginPanel();
        JPanel employeeCardLoginPanel = createEmployeeCardLoginPanel();
        JPanel biometricLoginPanel = createBiometricLoginPanel();

        // Legg til hovedmeny og funksjonspaneler
        JPanel mainMenu = createMainMenu();
        JPanel scannerPanel = createScannerPanel();
        JPanel journalPanel = createJournalPanel();
        JPanel doorLockPanel = createDoorLockPanel();

        mainPanel.add(regularLoginPanel, "RegularLogin");
        mainPanel.add(employeeCardLoginPanel, "EmployeeCardLogin");
        mainPanel.add(biometricLoginPanel, "BiometricLogin");
        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(scannerPanel, "Scanner");
        mainPanel.add(journalPanel, "Journal");
        mainPanel.add(doorLockPanel, "DoorLock");

        // Legg til mainPanel i JFrame
        add(mainPanel);

        // Vis skjerm for valg av innloggingsmetode først
        cardLayout.show(mainPanel, "LoginMethod");
    }

    // Metode for å lage skjerm for valg av innloggingsmetode
    private JPanel createLoginMethodSelectionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Velg en innloggingsmetode:");
        JButton regularLoginButton = new JButton("Logg inn med brukernavn og passord");
        JButton employeeCardLoginButton = new JButton("Logg inn med ansattkort");
        JButton biometricLoginButton = new JButton("Logg inn med biometrisk skanning");
        JButton exitButton = new JButton("Avslutt");

        // Legg til komponenter i panelet
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridy = 1;
        panel.add(regularLoginButton, constraints);

        constraints.gridy = 2;
        panel.add(employeeCardLoginButton, constraints);

        constraints.gridy = 3;
        panel.add(biometricLoginButton, constraints);

        constraints.gridy = 4;
        panel.add(exitButton, constraints);

        // ActionListener for knapp for vanlig innlogging
        regularLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "RegularLogin");  // Gå til skjerm for vanlig innlogging
            }
        });

        // ActionListener for knapp for ansattkort-innlogging
        employeeCardLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "EmployeeCardLogin");  // Gå til skjerm for ansattkort-innlogging
            }
        });

        // ActionListener for knapp for biometrisk innlogging
        biometricLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "BiometricLogin");  // Gå til skjerm for biometrisk innlogging
            }
        });

        // ActionListener for knapp for å avslutte
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Avslutt applikasjonen
            }
        });

        return panel;
    }

    // Metode for å lage panelet for vanlig innlogging
    private JPanel createRegularLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Brukernavn:");
        JLabel passLabel = new JLabel("Passord:");
        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);
        JButton loginButton = new JButton("Logg inn");
        JButton backButton = new JButton("Tilbake");
        JLabel messageLabel = new JLabel(""); // For å vise feilmeldinger

        // Legg til komponenter i panelet
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

        // ActionListener for innloggingsknappen
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                // Sjekk om brukernavn og passord er begge "123"
                if (username.equals("123") && password.equals("123")) {
                    cardLayout.show(mainPanel, "MainMenu"); // Vis hovedmeny ved suksess
                } else {
                    messageLabel.setText("Ugyldig brukernavn eller passord!");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

        // ActionListener for tilbakeknappen
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");  // Gå tilbake til valg av innloggingsmetode
            }
        });

        return panel;
    }

    // Metode for å lage panelet for ansattkort-innlogging
    private JPanel createEmployeeCardLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Skriv inn ansattkort-kode:");
        JTextField cardField = new JTextField(10);
        JButton loginButton = new JButton("Logg inn med ansattkort");
        JButton backButton = new JButton("Tilbake");
        JLabel messageLabel = new JLabel("");

        // Legg til komponenter i panelet
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
        panel.add(backButton, constraints);

        constraints.gridy = 3;
        panel.add(messageLabel, constraints);

        // ActionListener for innlogging med ansattkort
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeCardCode = cardField.getText();

                // Sjekk om koden er "123456"
                if (employeeCardCode.equals("123456")) {
                    cardLayout.show(mainPanel, "MainMenu"); // Vis hovedmeny ved suksess
                } else {
                    messageLabel.setText("Ugyldig ansattkort-kode!");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });

        // ActionListener for tilbakeknappen
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");  // Gå tilbake til valg av innloggingsmetode
            }
        });

        return panel;
    }

    // Metode for å lage panelet for biometrisk innlogging
    private JPanel createBiometricLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Biometrisk skanning ikke implementert ennå!", SwingConstants.CENTER); // Sentrert tekst
        JButton backButton = new JButton("Tilbake");

        panel.add(label, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        // ActionListener for tilbakeknappen
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");  // Gå tilbake til valg av innloggingsmetode
            }
        });

        return panel;
    }

    // Metode for å lage hovedmenyen
    private JPanel createMainMenu() {
        JPanel mainMenu = new JPanel(new GridLayout(4, 1));

        JButton button1 = new JButton("Scanner");
        JButton button2 = new JButton("Journal");
        JButton button3 = new JButton("Dør Lås");
        JButton logoutButton = new JButton("Logg ut");

        mainMenu.add(button1);
        mainMenu.add(button2);
        mainMenu.add(button3);
        mainMenu.add(logoutButton);

        // ActionListener for menyknappene
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Scanner");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Journal");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "DoorLock");
            }
        });

        // ActionListener for logg ut-knappen
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "LoginMethod");
            }
        });

        return mainMenu;
    }

    // Metode for å lage scanner-panelet (detaljer utelatt for enkelhetens skyld)
    private JPanel createScannerPanel() {
        JPanel scannerPanel = new JPanel();
        scannerPanel.add(new JLabel("Scanner-funksjonalitet ikke implementert ennå!"));
        return scannerPanel;
    }

    // Metode for å lage journal-panelet (detaljer utelatt for enkelhetens skyld)
    private JPanel createJournalPanel() {
        JPanel journalPanel = new JPanel();
        journalPanel.add(new JLabel("Journal-funksjonalitet ikke implementert ennå!"));
        return journalPanel;
    }

    // Metode for å lage dør-lås panelet (detaljer utelatt for enkelhetens skyld)
    private JPanel createDoorLockPanel() {
        JPanel doorLockPanel = new JPanel();
        doorLockPanel.add(new JLabel("Dør-lås funksjonalitet ikke implementert ennå!"));
        return doorLockPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application app = new Application();
            app.setVisible(true);  // Vis applikasjonsvinduet
        });
    }
}
