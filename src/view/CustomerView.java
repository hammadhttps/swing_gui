package view;// CustomerView.java
import model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerView {
    String customerFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\CustomerInfo.txt";
    private List<Customer> customerList; // Holds the customer data loaded from file
    private DefaultTableModel model;
    private JTable table;

    public CustomerView() {
        this.customerList = Customer.loadCustomerData(customerFile);
        createCustomerTable();
    }

    // Method to create the customer table with Update and Delete buttons
    private void createCustomerTable() {
        JFrame frame = new JFrame("Customer Data Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        model = new DefaultTableModel(new Object[]{"Customer ID", "Name", "Phone No", "Address", "Update", "Delete"}, 0);
        table = new JTable(model);
        table.getColumn("Update").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());

        // Load customer data into table
        loadCustomerDataToTable();

        // Action listener for Update and Delete buttons
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) return;

                if (e.getActionCommand().equals("Update")) {
                    handleUpdateAction(row);
                } else if (e.getActionCommand().equals("Delete")) {
                    handleDeleteAction(row);
                }
            }
        };

        // Add Update and Delete buttons to each row
        for (int i = 0; i < table.getRowCount(); i++) {
            JButton updateButton = new JButton("Update");
            JButton deleteButton = new JButton("Delete");
            updateButton.setActionCommand("Update");
            deleteButton.setActionCommand("Delete");
            updateButton.addActionListener(buttonListener);
            deleteButton.addActionListener(buttonListener);
            table.getColumn("Update").setCellEditor(new ButtonEditor(new JCheckBox(), updateButton));
            table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), deleteButton));
        }

        // Display the table in a scroll pane
        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }

    // Load customer data into the table model
    private void loadCustomerDataToTable() {
        for (Customer customer : customerList) {
            model.addRow(new Object[]{
                    customer.getCus_id(), customer.getName(),
                    customer.getPhone_no(), customer.getAddress(), "Update", "Delete"
            });
        }
    }

    // Handle the Update button action
    private void handleUpdateAction(int row) {
        // Show input dialog to update customer information
        JTextField nameField = new JTextField(table.getValueAt(row, 1).toString());
        JTextField phoneField = new JTextField(table.getValueAt(row, 2).toString());
        JTextField addressField = new JTextField(table.getValueAt(row, 3).toString());

        Object[] message = {"Name:", nameField, "Phone No:", phoneField, "Address:", addressField};
        int option = JOptionPane.showConfirmDialog(null, message, "Update Row", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            // Update the model and table view
            model.setValueAt(nameField.getText(), row, 1);
            model.setValueAt(phoneField.getText(), row, 2);
            model.setValueAt(addressField.getText(), row, 3);

            // Update the customer list and save to file
            Customer customer = customerList.get(row);
            customer.setName(nameField.getText());
            customer.setPhone_no(phoneField.getText());
            customer.setAddress(addressField.getText());
            Customer.saveCustomerData(customerList,customerFile);
        }
    }

    // Handle the Delete button action
    private void handleDeleteAction(int row) {
        // Remove the customer from the list and table model
        customerList.remove(row);
        model.removeRow(row);
        // Save updated customer list to file
        Customer.saveCustomerData(customerList,customerFile);
    }

//    // Display a single customer's information
//    public void displayCustomerInfo(Customer customer) {
//        System.out.println("Customer ID: " + customer.getCus_id());
//        System.out.println("CNIC: " + customer.getCNIC());
//        System.out.println("Name: " + customer.getName());
//        System.out.println("Address: " + customer.getAddress());
//        System.out.println("Phone No: " + customer.getPhone_no());
//        System.out.println("Customer Type: " + customer.getCus_type());
//        System.out.println("Meter Type: " + customer.getMeter_type());
//        System.out.println("Connection Date: " + customer.getConnec_date());
//        System.out.println("Regular Hour Units: " + customer.getReg_hour_units());
//        System.out.println("Peak Hour Units: " + customer.getPeak_hour_units());
//        System.out.println();
//    }
//
//    public void displayErrorMessage(String errorMessage) {
//        System.out.println(errorMessage);
//    }
}
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox, JButton button) {
        super(checkBox);
        this.button = button;
        this.button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
