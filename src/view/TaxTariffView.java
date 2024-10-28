package view;

import model.TaxTariffInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaxTariffView {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public TaxTariffView() {
        frame = new JFrame("Tax Tariff Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Meter Type", "Regular Price", "Peak Price", "Tax %", "Fixed Charges", "Update", "Delete"}, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add buttons for each row
        addTableButtons();

        // Set up the frame
        frame.setVisible(true);
    }

    // Method to add rows to the table
    public void displayAllTariffs(List<TaxTariffInfo> pricingList) {
        model.setRowCount(0); // Clear the table first
        for (TaxTariffInfo info : pricingList) {
            model.addRow(new Object[]{
                    info.getMeterType(),
                    info.getRegularUnitPrice(),
                    info.getPeakHourUnitPrice() != null ? info.getPeakHourUnitPrice() : "N/A",
                    info.getTaxPercentage(),
                    info.getFixedCharges(),
                    "Update",
                    "Delete"
            });
        }
    }

    private void addTableButtons() {
        table.getColumn("Update").setCellRenderer(new ButtonRenderer());
        ButtonEditor updateButtonEditor = new ButtonEditor(new JCheckBox(), new JButton("Update"));
        table.getColumn("Update").setCellEditor(updateButtonEditor);

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        ButtonEditor deleteButtonEditor = new ButtonEditor(new JCheckBox(), new JButton("Delete"));
        table.getColumn("Delete").setCellEditor(deleteButtonEditor);

        // Add ActionListeners for Update and Delete buttons
        ActionListener actionListener = e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;

            String meterType = model.getValueAt(row, 0).toString();

            if (e.getActionCommand().equals("Update")) {
                JTextField field1 = new JTextField(model.getValueAt(row, 1).toString());
                JTextField field2 = new JTextField(model.getValueAt(row, 2).toString());
                JTextField field3 = new JTextField(model.getValueAt(row, 3).toString());
                JTextField field4 = new JTextField(model.getValueAt(row, 4).toString());

                Object[] message = {
                        "Regular Price:", field1,
                        "Peak Price:", field2,
                        "Tax %:", field3,
                        "Fixed Charges:", field4
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Update Row", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    model.setValueAt(field1.getText(), row, 1);
                    model.setValueAt(field2.getText(), row, 2);
                    model.setValueAt(field3.getText(), row, 3);
                    model.setValueAt(field4.getText(), row, 4);
                }
                TaxTariffInfo.savePricingData(convertTableToList());

            } else if (e.getActionCommand().equals("Delete")) {
                model.removeRow(row);
                TaxTariffInfo.savePricingData(convertTableToList());
            }
        };

        updateButtonEditor.getButton().addActionListener(actionListener);
        deleteButtonEditor.getButton().addActionListener(actionListener);
    }

    // Method to convert table data to a list of TaxTariffInfo
    private List<TaxTariffInfo> convertTableToList() {
        List<TaxTariffInfo> pricingList = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String meterType = model.getValueAt(i, 0).toString();
            int regularPrice = Integer.parseInt(model.getValueAt(i, 1).toString());
            Integer peakPrice = model.getValueAt(i, 2).toString().equals("N/A") ? null : Integer.parseInt(model.getValueAt(i, 2).toString());
            int taxPercentage = Integer.parseInt(model.getValueAt(i, 3).toString());
            int fixedCharges = Integer.parseInt(model.getValueAt(i, 4).toString());

            pricingList.add(new TaxTariffInfo(meterType, regularPrice, peakPrice, taxPercentage, fixedCharges));
        }
        return pricingList;
    }

    // Custom ButtonEditor class to handle button actions in the table
    public class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, JButton button) {
            super(checkBox);
            this.button = button;
            this.button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
            this.isPushed = false;
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value != null) {
                button.setText(value.toString());
            }
            this.isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                return label; // Return the button's label if needed
            }
            isPushed = false;
            return null;
        }

        public JButton getButton() {
            return this.button;
        }
    }

    // Custom ButtonRenderer class to render the button in the table
    public class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row) {
            setText(value != null ? value.toString() : "");
            return this;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaxTariffView());
    }
}
