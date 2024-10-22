package controller;

import model.BillingInfo;
import view.BillingView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BillingController {
    private List<BillingInfo> billingInfos;
    private BillingView billingView;

    public BillingController(List<BillingInfo> billingInfos, BillingView billingView) {
        this.billingInfos = billingInfos;
        this.billingView = billingView;

        // Adding listeners to the buttons
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

            if (bill != null && "unpaid".equalsIgnoreCase(bill.getBill_paid_status())) {
                bill.setBill_paid_status("paid");
                BillingInfo.saveBillingData(billingInfos);  // Save updated data
                JOptionPane.showMessageDialog(null, "Bill status updated to 'paid' for Customer ID: " + customerId);
            } else if (bill != null && "paid".equalsIgnoreCase(bill.getBill_paid_status())) {
                JOptionPane.showMessageDialog(null, "Bill is already marked as 'paid'.");
            } else {
                billingView.noBillFoundMessage(customerId);
            }
        }
    }
}
