package view;

import java.util.Scanner;

public class NadraDBView {

    private Scanner scanner = new Scanner(System.in);

    // Method to prompt for CNIC
    public String getCNICInput() {
        System.out.print("Enter CNIC: ");
        return scanner.nextLine();
    }

    // Method to prompt for customer ID
    public String getCustomerIDInput() {
        System.out.print("Enter Customer ID: ");
        return scanner.nextLine();
    }

    // Method to prompt for new expiry date
    public String getNewExpiryDate() {
        System.out.print("Enter new expiry date (dd/MM/yyyy): ");
        return scanner.nextLine();
    }

    // Display message to show expiry warning
    public void displayExpiryWarning(String customerID, long daysUntilExpiry) {
        System.out.println("Customer ID: " + customerID + " has a CNIC expiring in " + daysUntilExpiry + " days.");
    }

    // Display successful expiry date update
    public void displayUpdateSuccess(String CNIC) {
        System.out.println("Expiry date updated successfully for CNIC: " + CNIC);
    }

    // Display invalid date format message
    public void displayInvalidDateFormat() {
        System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
    }

    // Display message for missing customer or CNIC
    public void displayMissingCustomer() {
        System.out.println("Customer number or CNIC not found.");
    }

    public void displayCNICNotFound() {
        System.out.println("CNIC not found in Nadra database.");
    }
}
