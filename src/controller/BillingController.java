package controller;

import model.BillingInfo;
import view.BillingView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDate;

public class BillingController {
    private List<BillingInfo> billingInfos;
    private BillingView billingView;

    public BillingController(List<BillingInfo> billingInfos, BillingView billingView, String filename) {
        this.billingInfos = billingInfos;
        this.billingView = billingView;

        // Set the filename to avoid null issues
        BillingInfo.filename = filename;

        billingView.addViewBillListener(new ViewBillListener());
        billingView.addShowReportsListener(new ShowReportsListener());
        billingView.addUpdateStatusListener(new UpdateBillStatusListener());
    }


    // ActionListener for viewing a bill
    class ViewBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String customerId = billingView.promptCustomerId();

            BillingInfo bill = null;
            for (BillingInfo billingInfo : billingInfos) {
                if (billingInfo.getCus_id().equals(customerId)) {
                    bill = billingInfo;
                    break;
                }
            }

            if (bill != null) {
                billingView.displayBillingInfo(bill);
            } else {
                billingView.noBillFoundMessage(customerId);
            }
        }
    }

    // ActionListener for showing bill reports
    class ShowReportsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int paidCount = 0;
            int unpaidCount = 0;

            for (BillingInfo billingInfo : billingInfos) {
                if ("paid".equalsIgnoreCase(billingInfo.getBill_paid_status())) {
                    paidCount++;
                } else {
                    unpaidCount++;
                }
            }

            billingView.displayBillReports(paidCount, unpaidCount);
        }
    }

    // ActionListener for updating bill status
    // In the BillingController class

    // ActionListener for updating bill status
      // Import required for getting the current date

    // Import DateTimeFormatter for custom date format

    class UpdateBillStatusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String customerId = billingView.promptCustomerId();

            BillingInfo bill = null;
            for (BillingInfo billingInfo : billingInfos) {
                if (billingInfo.getCus_id().equals(customerId)) {
                    bill = billingInfo;
                    break;
                }
            }

            if (bill != null) {
                if ("unpaid".equalsIgnoreCase(bill.getBill_paid_status())) {
                    // Update the bill status and set the payment date
                    bill.setBill_paid_status("paid");

                    // Format the current date to "dd/MM/yy" format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                    String formattedDate = LocalDate.now().format(formatter);
                    bill.setPayment_date(formattedDate);  // Set formatted date as payment date

                    // Save updated data to file
                    BillingInfo.saveBillingData(billingInfos);

                    JOptionPane.showMessageDialog(null, "Bill status updated to 'paid' for Customer ID: " + customerId);
                } else {
                    JOptionPane.showMessageDialog(null, "Bill is already marked as 'paid'.");
                }
            } else {
                billingView.noBillFoundMessage(customerId);
            }
        }
    }



}
