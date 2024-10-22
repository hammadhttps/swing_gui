package view;

import model.BillingInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class BillingView {
    private JFrame frame;
    private JTextField customerIdField;
    private JButton viewBillButton, showReportsButton, updateStatusButton;

    public BillingView() {
        // Main frame setup
        frame = new JFrame("Billing System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.setLayout(new BorderLayout(10, 10));

        // Add a welcome label at the top
//        JLabel welcomeLabel = new JLabel("Welcome to the Billing System", JLabel.CENTER);
//        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        frame.add(welcomeLabel, BorderLayout.NORTH);

        // Panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));  // 3 rows, 1 column, 10px padding

        // Buttons for different functionalities
        viewBillButton = new JButton("View Bill");
        showReportsButton = new JButton("Show Bill Reports");
        updateStatusButton = new JButton("Update Bill Status");

        buttonPanel.add(viewBillButton);
        buttonPanel.add(showReportsButton);
        buttonPanel.add(updateStatusButton);

        // Adding padding around the buttons
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }

    // Prompting for Customer ID with a dialog box
    public String promptCustomerId() {
        customerIdField = new JTextField(20);
        int result = JOptionPane.showConfirmDialog(frame, customerIdField, "Enter Customer ID:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return customerIdField.getText();
        }
        return null;
    }

    // Display billing information in a JTable within a new JFrame
    public void displayBillingInfo(BillingInfo billingInfo) {
        String[] columns = {"Field", "Value"};
        Object[][] data = {
                {"Customer ID", billingInfo.getCus_id()},
                {"Billing Month", billingInfo.getBilling_month()},
                {"Current Regular Meter Reading", billingInfo.getCurr_reg_meter()},
                {"Current Peak Meter Reading", billingInfo.getCurr_reg_peak()},
                {"Reading Date", billingInfo.getReading_date()},
                {"Cost of Electricity", billingInfo.getCost_of_elec()},
                {"Sales Tax", billingInfo.getSales_tax()},
                {"Fixed Charges", billingInfo.getFixed_charges()},
                {"Total Amount", billingInfo.getTotal_amnt()},
                {"Due Date", billingInfo.getDue_date()},
                {"Bill Paid Status", billingInfo.getBill_paid_status()},
                {"Payment Date", billingInfo.getPayment_date().isEmpty() ? "N/A" : billingInfo.getPayment_date()}
        };

        JTable billingTable = new JTable(new DefaultTableModel(data, columns));
        billingTable.setRowHeight(25);
        billingTable.setFont(new Font("Arial", Font.PLAIN, 14));

        // Frame to display the billing information
        JFrame billFrame = new JFrame("Billing Information for Customer: " + billingInfo.getCus_id());
        billFrame.setSize(600, 400);
        billFrame.setLayout(new BorderLayout(10, 10));
        billFrame.add(new JScrollPane(billingTable), BorderLayout.CENTER);

        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> billFrame.dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);
        billFrame.add(bottomPanel, BorderLayout.SOUTH);

        billFrame.setLocationRelativeTo(null);  // Center the new frame on the screen
        billFrame.setVisible(true);
    }

    // Display bill reports in a message dialog
    public void displayBillReports(int paidCount, int unpaidCount) {
        JOptionPane.showMessageDialog(frame,
                "Paid Bills: " + paidCount + "\nUnpaid Bills: " + unpaidCount,
                "Bill Reports",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Show error message when no bill is found
    public void noBillFoundMessage(String customerId) {
        JOptionPane.showMessageDialog(frame,
                "No bill found for Customer ID: " + customerId,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Methods to add listeners to buttons
    public void addViewBillListener(ActionListener listenForViewBill) {
        viewBillButton.addActionListener(listenForViewBill);
    }

    public void addShowReportsListener(ActionListener listenForShowReports) {
        showReportsButton.addActionListener(listenForShowReports);
    }

    public void addUpdateStatusListener(ActionListener listenForUpdateStatus) {
        updateStatusButton.addActionListener(listenForUpdateStatus);
    }

    // Function to display the main view
    public void displayMainView() {
        frame.setVisible(true);
    }
}
