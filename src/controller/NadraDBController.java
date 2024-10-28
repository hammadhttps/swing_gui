package controller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import model.NadraDBModel;
import view.NadraDBView;
import model.Customer;
import controller.CustomerController;

public class NadraDBController {

    private NadraDBModel model;
    private NadraDBView view;
    String customerFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\CustomerInfo.txt";

    // Constructor
    public NadraDBController(NadraDBModel model, NadraDBView view) {
        this.model = model;
        this.view = view;
    }

    // Load data
    public List<NadraDBModel> loadNadraData() {
        return NadraDBModel.loadNadraData();
    }

    public void showExpiries()
    {
        List<NadraDBModel>nadralist=loadNadraData();
        List<Customer>customers=Customer.loadCustomerData(customerFile);
        checkExpiry(nadralist,customers);
    }


    // Check expiry for each customer
    public void checkExpiry(List<NadraDBModel> nadraList, List<Customer> customers) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentDate = LocalDate.now();

        // Prepare data for table
        String[] columnNames = {"Customer ID", "CNIC", "Days Until Expiry"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Customer customer : customers) {
            String customerCNIC = customer.getCNIC();
            for (NadraDBModel ndb : nadraList) {
                if (customerCNIC.equals(ndb.getCNIC())) {
                    LocalDate expiryDate = LocalDate.parse(ndb.getExpiryDate(), formatter);
                    long daysUntilExpiry = ChronoUnit.DAYS.between(currentDate, expiryDate);
                    if (daysUntilExpiry > 0 && daysUntilExpiry <= 30) {
                        // Add row to the table model
                        model.addRow(new Object[]{customer.getCus_id(), customerCNIC, daysUntilExpiry});
                    }
                }
            }
        }

        // Create and display the JFrame with the JTable
        if (model.getRowCount() > 0) {
            JFrame frame = new JFrame("Customer CNIC Expiry in Next 30 Days");
            frame.setSize(400, 350);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Add a close button
            JButton closeButton = new JButton("Close");
            closeButton.setBackground(new Color(60, 180, 75));
            closeButton.setFocusPainted(false);
            closeButton.addActionListener(e -> frame.dispose());
            closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    closeButton.setBackground(new Color(0, 150, 136));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    closeButton.setBackground(new Color(60, 180, 75));
                }
            });
            frame.add(closeButton, BorderLayout.SOUTH);

            frame.setLocationRelativeTo(null);  // Center the frame
            frame.setVisible(true);
        } else {
            // No customers with expiring CNICs in the next 30 days
            JOptionPane.showMessageDialog(null, "No customers with CNIC expiry in the next 30 days!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateExp()
    {
        List<NadraDBModel>nadralist=loadNadraData();
        List<Customer>customers=Customer.loadCustomerData(customerFile);
        updateExpiryDate(nadralist,customers);

    }


    // Update expiry date
    public void updateExpiryDate(List<NadraDBModel> nadraList, List<Customer> customers) {
        JFrame updateFrame = new JFrame("Update CNIC Expiry Date");
        updateFrame.setSize(500, 350);
        updateFrame.setBackground(Color.white);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setLayout(null);
        updateFrame.setLocationRelativeTo(null);

        // Create labels and text fields
        JLabel cnicLabel = new JLabel("Enter CNIC: ");
        cnicLabel.setBounds(50, 50, 100, 25);
        JTextField cnicField = new JTextField();
        cnicField.setBounds(160, 50, 150, 25);

        JLabel customerIDLabel = new JLabel("Enter Customer ID: ");
        customerIDLabel.setBounds(50, 90, 120, 25);
        JTextField customerIDField = new JTextField();
        customerIDField.setBounds(160, 90, 150, 25);

        JLabel newExpiryLabel = new JLabel("New Expiry Date (dd/MM/yyyy): ");
        newExpiryLabel.setBounds(50, 130, 180, 25);
        JTextField newExpiryField = new JTextField();
        newExpiryField.setBounds(220, 130, 90, 25);

        // Create Update and Cancel buttons
        JButton updateButton = new JButton("Update Expiry");
        updateButton.setBounds(80, 180, 120, 30);
        updateButton.setBackground(new Color(60, 180, 75));
        updateButton.setForeground(Color.black);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210, 180, 100, 30);
        cancelButton.setBackground(new Color(60, 180, 75));
        cancelButton.setForeground(Color.black);

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                cancelButton.setBackground(new Color(0, 150, 136));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                cancelButton.setBackground(new Color(60, 180, 75));
            }
        });

        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                updateButton.setBackground(new Color(0, 150, 136));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                updateButton.setBackground(new Color(60, 180, 75));
            }
        });

        // Add components to frame
        updateFrame.add(cnicLabel);
        updateFrame.add(cnicField);
        updateFrame.add(customerIDLabel);
        updateFrame.add(customerIDField);
        updateFrame.add(newExpiryLabel);
        updateFrame.add(newExpiryField);
        updateFrame.add(updateButton);
        updateFrame.add(cancelButton);

        // Action listener for Cancel button
        cancelButton.addActionListener(e -> updateFrame.dispose());

        // Action listener for Update button
        updateButton.addActionListener(e -> {
            String CNIC = cnicField.getText();
            String customerID = customerIDField.getText();
            String newExpiryDate = newExpiryField.getText();

            boolean foundCustomer = false;
            boolean foundCNIC = false;

            // Search for matching customer and update expiry date
            for (Customer customer : customers) {
                if (customer.getCNIC().equals(CNIC) && customer.getCus_id().equals(customerID)) {
                    foundCustomer = true;

                    for (NadraDBModel ndb : nadraList) {
                        if (ndb.getCNIC().equals(CNIC)) {
                            foundCNIC = true;

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            try {
                                // Validate and update expiry date
                                LocalDate.parse(newExpiryDate, formatter);
                                NadraDBModel.updateExpiryDate(nadraList, CNIC, newExpiryDate);
                                JOptionPane.showMessageDialog(updateFrame, "Expiry date updated successfully for CNIC: " + CNIC, "Success", JOptionPane.INFORMATION_MESSAGE);
                                updateFrame.dispose();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(updateFrame, "Invalid date format! Please enter the date in dd/MM/yyyy format.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                        }
                    }
                    break;
                }
            }

            if (!foundCustomer) {
                JOptionPane.showMessageDialog(updateFrame, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!foundCNIC) {
                JOptionPane.showMessageDialog(updateFrame, "CNIC not found in NadraDB!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Display frame
        updateFrame.setVisible(true);
    }
}
