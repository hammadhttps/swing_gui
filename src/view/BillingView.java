package view;

import model.BillingInfo;

import java.util.List;
import java.util.Scanner;

public class BillingView {

    public void displayBillingInfo(BillingInfo billingInfo) {
        System.out.println("Customer ID: " + billingInfo.getCus_id());
        System.out.println("Billing Month: " + billingInfo.getBilling_month());
        System.out.println("Current Regular Meter Reading: " + billingInfo.getCurr_reg_meter());
        System.out.println("Current Peak Meter Reading: " + billingInfo.getCurr_reg_peak());
        System.out.println("Reading Date: " + billingInfo.getReading_date());
        System.out.println("Cost of Electricity: " + billingInfo.getCost_of_elec());
        System.out.println("Sales Tax: " + billingInfo.getSales_tax());
        System.out.println("Fixed Charges: " + billingInfo.getFixed_charges());
        System.out.println("Total Amount: " + billingInfo.getTotal_amnt());
        System.out.println("Due Date: " + billingInfo.getDue_date());
        System.out.println("Bill Paid Status: " + billingInfo.getBill_paid_status());
        System.out.println("Payment Date: " + (billingInfo.getPayment_date().isEmpty() ? "N/A" : billingInfo.getPayment_date()));
        System.out.println();
    }

    public void displayBillReports(int paidCount, int unpaidCount) {
        System.out.println("The number of Paid Bills: " + paidCount);
        System.out.println("The number of Unpaid Bills: " + unpaidCount);
    }

    public String promptCustomerId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer ID: ");
        return scanner.nextLine();
    }

    public void noBillFoundMessage(String customerId) {
        System.out.println("No bill found for Customer ID: " + customerId);
    }
}
