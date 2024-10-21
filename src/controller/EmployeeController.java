package controller;

import model.EmployeeModel;
import model.TaxTariffInfo;
import view.EmployeeView;
import model.NadraDBModel;
import view.NadraDBView;
import controller.NadraDBController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {
    String user;
    private EmployeeModel model;
    private EmployeeView view;
    private  NadraDBModel NDM;
    private  NadraDBView NDV;
    private JFrame loginFrame;
    private JFrame passwordChangeFrame;
    private JFrame Menuframe;
    NadraDBController nadraController;

    // Constructor
    public EmployeeController(EmployeeModel model, EmployeeView view,NadraDBModel nd,NadraDBView nv) {
        this.model = model;
        this.view = view;
        NDM=nd;
        NDV=nv;
        nadraController = new NadraDBController(NDM,NDV);
    }

    // Load employee data
    public void loadEmployeeData() {
        model.loadEmployeeData();
    }
    public void showMenu() {
        // Resize the icon to fit better with the text
        ImageIcon icon = new ImageIcon(new ImageIcon("C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\icons\\Network-Control-Panel-icon.png")
                .getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        // Create the JLabel with text and icon, center-align the icon
        JLabel empMenu = new JLabel("Employee Menu", icon, JLabel.CENTER);

        // Set font for a more prominent title
        empMenu.setFont(new Font("Arial", Font.BOLD, 20));
        empMenu.setForeground(Color.DARK_GRAY); // Prominent dark text color

        // Set bounds for positioning the label at the top center
        empMenu.setBounds(125, 10, 250, 80); // Adjust width and height as necessary

        // Create buttons with their respective bounds
        JButton ChangePass = createStyledButton("Change Password");
        ChangePass.setBounds(80, 120, 150, 35);

        JButton viewBill = createStyledButton("View Bills");
        viewBill.setBounds(230, 120, 150, 35);

        JButton ViewCNIC = createStyledButton("Show Customers CNIC Dates");
        ViewCNIC.setBounds(80, 170, 300, 35);

        JButton Billreports = createStyledButton("Bill Reports");
        Billreports.setBounds(80, 220, 300, 35);

        JButton taxtarriff = createStyledButton("Update Tax Tariff");
        taxtarriff.setBounds(80, 270, 300, 35);

        JButton cancel = createStyledButton("Cancel");
        cancel.setBounds(300, 350, 100, 35);

        // Create the menu frame
        Menuframe = new JFrame("Employee Control");
        Menuframe.setLayout(null);

        // Add components to the frame
        Menuframe.add(empMenu); // Add the label with icon and text at the top center
        Menuframe.add(ChangePass);
        Menuframe.add(viewBill);
        Menuframe.add(ViewCNIC);
        Menuframe.add(Billreports);
        Menuframe.add(taxtarriff);
        Menuframe.add(cancel);

        // Configure the frame
        Menuframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Menuframe.setSize(500, 500); // Increase size to fit all buttons comfortably
        Menuframe.setLocationRelativeTo(null); // Center the frame on the screen
        Menuframe.setVisible(true);


        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menuframe.dispose();
            }
        });
        ChangePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPasswordChangeFrame();
            }
        });

        ViewCNIC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nadraController.showExpiries();
            }
        });
    }


    // Create the employee login frame with enhanced style
    public void createLoginFrame() {
        loginFrame = new JFrame("Employee Login");
        loginFrame.setSize(420, 420);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setBackground(Color.white);
        loginFrame.setLayout(null);

        // Add username and password fields
        JLabel usernameLabel = createStyledLabel("Username: ");
        usernameLabel.setForeground(Color.gray);
        usernameLabel.setBounds(50, 100, 85, 25);
        JTextField usernameField = createStyledTextField();
        usernameField.setBounds(125, 100, 200, 25);

        JLabel passwordLabel = createStyledLabel("Password: ");
        passwordLabel.setForeground(Color.gray);
        passwordLabel.setBounds(50, 150, 85, 25);
        JPasswordField passwordField = createStyledPasswordField();
        passwordField.setBounds(125, 150, 200, 25);

        // Add 'Show Password' checkbox
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(125, 180, 200, 25);
        showPasswordCheckBox.setBackground(Color.white);

        // Add login and cancel buttons
        JButton cancelButton = createStyledButton("Cancel");
        JButton loginButton = createStyledButton("Login");
        loginButton.setBounds(150, 230, 100, 35);
        cancelButton.setBounds(150, 280, 100, 35);

        // Add components to the frame
        loginFrame.setLocationRelativeTo(null);
        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(showPasswordCheckBox);
        loginFrame.add(new JLabel());  // Empty label for spacing
        loginFrame.add(loginButton);
        loginFrame.add(cancelButton);

        // Add action listener to show password checkbox
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);  // Show password
                } else {
                    passwordField.setEchoChar('•');  // Hide password
                }
            }
        });

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(loginFrame, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (model.validateEmployee(username, password)) {
                    user = username;
                    view.showLoginSuccess();
                    showMenu();
                    loginFrame.dispose();  // Close login frame
                } else {
                    view.showLoginFailure();
                }
            }
        });

        // Add action listener to cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
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
        passwordChangeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passwordChangeFrame.setLayout(null);
        passwordChangeFrame.setLocationRelativeTo(null);
        passwordChangeFrame.getContentPane().setBackground(Color.white);

        // Add current password and new password fields
        JLabel currentPasswordLabel = createStyledLabel("Current Password: ");
        currentPasswordLabel.setBounds(50, 100, 250, 25);
        JPasswordField currentPasswordField = createStyledPasswordField();
        currentPasswordField.setBounds(200, 100, 150, 25);  // Adjusted field position

        JLabel newPasswordLabel = createStyledLabel("New Password: ");
        newPasswordLabel.setBounds(50, 170, 250, 25);
        JPasswordField newPasswordField = createStyledPasswordField();
        newPasswordField.setBounds(200, 170, 150, 25);  // Adjusted field position

        // Add 'Show Password' checkboxes for both fields
        JCheckBox showCurrentPasswordCheckBox = new JCheckBox("Show Current Password");
        showCurrentPasswordCheckBox.setBounds(200, 130, 180, 25);  // Adjusted checkbox position
        showCurrentPasswordCheckBox.setBackground(Color.white);

        JCheckBox showNewPasswordCheckBox = new JCheckBox("Show New Password");
        showNewPasswordCheckBox.setBounds(200, 200, 150, 25);  // Adjusted checkbox position
        showNewPasswordCheckBox.setBackground(Color.white);

        // Add change password and cancel buttons
        JButton changePasswordButton = createStyledButton("Change Password");
        changePasswordButton.setBounds(140, 250, 145, 35);
        JButton cancelButton = createStyledButton("Cancel");
        cancelButton.setBounds(165, 300, 100, 35);

        // Add components to the frame
        passwordChangeFrame.add(currentPasswordLabel);
        passwordChangeFrame.add(currentPasswordField);
        passwordChangeFrame.add(newPasswordLabel);
        passwordChangeFrame.add(newPasswordField);
        passwordChangeFrame.add(showCurrentPasswordCheckBox);
        passwordChangeFrame.add(showNewPasswordCheckBox);
        passwordChangeFrame.add(changePasswordButton);
        passwordChangeFrame.add(cancelButton);

        // Add action listeners to the 'Show Password' checkboxes
        showCurrentPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showCurrentPasswordCheckBox.isSelected()) {
                    currentPasswordField.setEchoChar((char) 0);  // Show current password
                } else {
                    currentPasswordField.setEchoChar('•');  // Hide current password
                }
            }
        });

        showNewPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showNewPasswordCheckBox.isSelected()) {
                    newPasswordField.setEchoChar((char) 0);  // Show new password
                } else {
                    newPasswordField.setEchoChar('•');  // Hide new password
                }
            }
        });

        // Add action listener to change password button
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPassword = new String(currentPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());

                if (currentPassword.isEmpty() || newPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(passwordChangeFrame, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (model.changePassword(user, currentPassword, newPassword)) {
                    view.showPasswordChangeSuccess(user);
                    passwordChangeFrame.dispose();  // Close the password change frame after successful password change
                } else {
                    view.showPasswordChangeFailure();
                }
            }
        });

        // Add action listener to cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordChangeFrame.dispose();
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
        textField.setForeground(Color.black);
        textField.setBackground(new Color(211, 211, 211));
        textField.setBorder(BorderFactory.createLineBorder(new Color(90, 98, 118), 1));
        return textField;
    }

    // Method to create a styled JPasswordField
    private JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setForeground(Color.black);
        passwordField.setBackground(new Color(211, 211, 211));
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
