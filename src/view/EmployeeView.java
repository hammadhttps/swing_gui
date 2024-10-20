package view;

import java.util.Scanner;

public class EmployeeView {

    private Scanner scanner = new Scanner(System.in);

    // Get username input
    public String getUsernameInput() {
        System.out.print("Enter username: ");
        return scanner.nextLine();
    }

    // Get password input
    public String getPasswordInput() {
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }

    // Get new password input
    public String getNewPasswordInput() {
        System.out.print("Enter new password: ");
        return scanner.nextLine();
    }

    // Show login success
    public void showLoginSuccess() {
        System.out.println("Login successful.");
    }

    // Show login failure
    public void showLoginFailure() {
        System.out.println("Invalid username or password.");
    }

    // Show password change success
    public void showPasswordChangeSuccess(String username) {
        System.out.println("Password updated successfully for user: " + username);
    }

    // Show password change failure
    public void showPasswordChangeFailure() {
        System.out.println("Invalid current password.");
    }

    // Display employee information
    public void displayEmployee(String username, String password) {
        System.out.println("Username: " + username + ", Password: " + password);
    }
}
