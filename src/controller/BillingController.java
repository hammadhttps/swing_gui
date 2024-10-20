package controller;

import model.BillingInfo;
import view.BillingView;

import java.util.List;

public class BillingController {
    private List<BillingInfo> billingInfos;
    private BillingView billingView;

    public BillingController(List<BillingInfo> billingInfos, BillingView billingView) {
        this.billingInfos = billingInfos;
        this.billingView = billingView;
    }

    public void viewBill() {
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

    public void showBillReports() {
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

    public void updateBillStatus(List<BillingInfo> billingInfos) {
        String customerId = billingView.promptCustomerId();

        BillingInfo bill = null;
        for (BillingInfo billingInfo : billingInfos) {
            if (billingInfo.getCus_id().equals(customerId)) {
                bill = billingInfo;
                break;
            }
        }

        if (bill != null && bill.getBill_paid_status().equals("unpaid")) {
            bill.setBill_paid_status("paid");
            BillingInfo.saveBillingData(billingInfos, "path/to/BillingInfo.txt");  // Save updated data
            System.out.println("Bill status updated to 'paid' for Customer ID: " + customerId);
        } else if (bill != null && bill.getBill_paid_status().equals("paid")) {
            System.out.println("Bill is already marked as 'paid'.");
        } else {
            billingView.noBillFoundMessage(customerId);
        }
    }
}
