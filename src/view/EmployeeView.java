package view;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class EmployeeView {

    private Scanner scanner = new Scanner(System.in);

    // Get username input
    public String getUsernameInput() {
        return JOptionPane.showInputDialog(null, "Enter username:");
    }

    // Get password input
    public String getPasswordInput() {
        return JOptionPane.showInputDialog(null, "Enter password:");
    }

    // Get new password input
    public String getNewPasswordInput() {
        return JOptionPane.showInputDialog(null, "Enter new password:");
    }

    // Show login success
    public void showLoginSuccess() {
        JOptionPane.showMessageDialog(null, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show login failure
    public void showLoginFailure() {
        JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
    }

    // Show password change success
    public void showPasswordChangeSuccess(String username) {
        JOptionPane.showMessageDialog(null, "Password updated successfully for user: " + username, "Password Changed", JOptionPane.INFORMATION_MESSAGE);
    }

    // Show password change failure
    public void showPasswordChangeFailure() {
        JOptionPane.showMessageDialog(null, "Invalid current password.", "Password Change Failed", JOptionPane.ERROR_MESSAGE);
    }

    // Display employee information
    public void displayEmployee(String username, String password) {
        JOptionPane.showMessageDialog(null, "Username: " + username + ", Password: " + password, "Employee Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
