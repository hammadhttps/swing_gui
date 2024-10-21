package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LESCOBillingSystem_GUI extends JFrame {

    public int value;

    public LESCOBillingSystem_GUI() {
        // Set the title of the frame
        super("Welcome to the LESCO Billing System");

        // Set size, layout, and location
        setSize(600, 550);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the frame on the screen

        // Set an icon beside the title
        ImageIcon icon = new ImageIcon("C:\\Users\\city\\Desktop\\java\\lesco GUI\\src\\company-building-icon.png"); // Add your icon path here
        JLabel titleLabel = new JLabel("Welcome to the LESCO Billing System", icon, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.darkGray);
        titleLabel.setHorizontalTextPosition(JLabel.RIGHT);
        titleLabel.setVerticalTextPosition(JLabel.CENTER);

        // Create the buttons
        JButton employeeButton = new JButton("For Employee");
        JButton customerButton = new JButton("For Customer");
        JButton cancelButton = new JButton("Cancel");

        // Customize button appearance
        customizeButton(employeeButton);
        customizeButton(customerButton);
        customizeButton(cancelButton);
        customerButton.setBackground(new Color(60, 180, 75));
        employeeButton.setBackground(new Color(60, 180, 75));


        employeeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                super.mouseEntered(e);
                employeeButton.setBackground(new Color(0, 150, 136));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                employeeButton.setBackground(new Color(60, 180, 75));
            }
        });

        customerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                customerButton.setBackground(new Color(0, 150, 136));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                customerButton.setBackground(new Color(60, 180, 75));
            }
        });

        // Set action for Cancel button to close the application
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the program
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              value=1;
              dispose();
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                value=2;
                dispose();
            }
        });



        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(employeeButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setOpaque(false); // Transparent background for the panel

        // Add the title and buttons to the frame
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Make frame visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to customize button appearance
    private void customizeButton(JButton button) {
        button.setFont(new Font("Verdana", Font.PLAIN, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180)); // Steel blue background
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
    public Integer getvalue()
    {
        return value;
    }




}
