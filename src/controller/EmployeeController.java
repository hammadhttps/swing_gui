package controller;

import model.EmployeeModel;
import view.EmployeeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {
    String user;
    private EmployeeModel model;
    private EmployeeView view;
    private JFrame loginFrame;
    private JFrame passwordChangeFrame;

    // Constructor
    public EmployeeController(EmployeeModel model, EmployeeView view) {
        this.model = model;
        this.view = view;
    }

    // Load employee data
    public void loadEmployeeData() {
        model.loadEmployeeData();
    }

    // Create the employee login frame with enhanced style
    public void createLoginFrame() {
        loginFrame = new JFrame("Employee Login");
        loginFrame.setSize(420, 420);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setBackground(Color.white);// Dark background
        loginFrame.setLayout(null);


        // Add stylish username and password fields
        JLabel usernameLabel = createStyledLabel("Username: ");
        usernameLabel.setForeground(Color.gray);
        usernameLabel.setBounds(50,100,85,25);
        JTextField usernameField = createStyledTextField();
        usernameField.setBounds(125, 100, 200, 25);

        JLabel passwordLabel = createStyledLabel("Password: ");
        passwordLabel.setForeground(Color.gray);
        passwordLabel.setBounds(50,150,85,25);
        JPasswordField passwordField = createStyledPasswordField();
        passwordField.setBounds(125, 150, 200, 25);

        // Add stylish login button
        JButton loginButton = createStyledButton("Login");
        loginButton.setBounds(150, 200, 100, 35);

        // Add components to the frame
        loginFrame.setLocationRelativeTo(null);
        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel());  // Empty label for spacing
        loginFrame.add(loginButton);

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                user = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (model.validateEmployee(username, password)) {
                    view.showLoginSuccess();
                    createPasswordChangeFrame();  // Open password change frame on successful login
                    loginFrame.dispose();  // Close login frame
                } else {
                    view.showLoginFailure();
                }
            }
        });

        // Set login frame icon and visibility
        loginFrame.setIconImage(new ImageIcon("C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\icons\\Preppy-icon.png").getImage());
        loginFrame.setVisible(true);
    }

    // Create the password change frame with enhanced style
    public void createPasswordChangeFrame() {
        passwordChangeFrame = new JFrame("Password Change");
        passwordChangeFrame.setSize(420, 420);
       // passwordChangeFrame.setLocation(400,400);
        passwordChangeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passwordChangeFrame.setLayout(null);
        passwordChangeFrame.setLocationRelativeTo(null);
        passwordChangeFrame.getContentPane().setBackground(Color.white); // Dark background

        // Add stylish fields for current password and new password
        JLabel currentPasswordLabel = createStyledLabel("Current Password: ");
        currentPasswordLabel.setBounds(50,100,250,25);
        JPasswordField currentPasswordField = createStyledPasswordField();
        currentPasswordField.setBounds(185,100,125,25);
        JLabel newPasswordLabel = createStyledLabel("New Password: ");
        newPasswordLabel.setBounds(60,150,200,25);
        JPasswordField newPasswordField = createStyledPasswordField();
        newPasswordField.setBounds(180,150,130,25);


        // Add stylish change password button
        JButton changePasswordButton = createStyledButton("Change Password");
        changePasswordButton.setBounds(150,250,145,35);
        // Add components to the frame
        passwordChangeFrame.add(currentPasswordLabel);
        passwordChangeFrame.add(currentPasswordField);
        passwordChangeFrame.add(newPasswordLabel);
        passwordChangeFrame.add(newPasswordField);
        passwordChangeFrame.add(new JLabel());  // Empty label for spacing
        passwordChangeFrame.add(changePasswordButton);

        // Add action listener to change password button
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPassword = new String(currentPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());

                if (model.changePassword(user, currentPassword, newPassword))
                {
                    view.showPasswordChangeSuccess(user);
                    passwordChangeFrame.dispose();  // Close the password change frame after successful password change
                } else {
                    view.showPasswordChangeFailure();
                }
            }
        });

        passwordChangeFrame.setVisible(true);
    }

    // Method to create a styled JLabel
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.gray); // Text color
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    // Method to create a styled JTextField
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.gray);
        textField.setBackground(new Color(55, 60, 70));
        textField.setBorder(BorderFactory.createLineBorder(new Color(90, 98, 118), 1));
        return textField;
    }

    // Method to create a styled JPasswordField
    private JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(new Color(55, 60, 70));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(90, 98, 118), 1));
        return passwordField;
    }

    // Method to create a styled JButton
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(60, 180, 75)); // Green background
        button.setFocusPainted(false);  // Remove default button focus
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 150, 136)); // Hover color
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 180, 75)); // Original color
            }
        });
        return button;
    }
}
