package model;

import java.io.*;
import java.util.*;

public class BillingInfo {
    private String cus_id;
    private int billing_month;
    private int curr_reg_meter;
    private int curr_reg_peak;
    private String reading_date;
    private int cost_of_elec;
    private int sales_tax;
    private int fixed_charges;
    private int total_amnt;
    private String due_date;
    private String bill_paid_status;
    private String payment_date;

    public BillingInfo() {}

    public BillingInfo(String cus_id, int billing_m, int curr_reg_meter, int curr_reg_peak, String reading_date,
                       int cost_of_elec, int sales_tax, int fixed_charges, int total_amnt,
                       String due_date, String bill_paid_status, String payment_date) {
        this.cus_id = cus_id;
        this.billing_month = billing_m;
        this.curr_reg_meter = curr_reg_meter;
        this.curr_reg_peak = curr_reg_peak;
        this.reading_date = reading_date;
        this.cost_of_elec = cost_of_elec;
        this.sales_tax = sales_tax;
        this.fixed_charges = fixed_charges;
        this.total_amnt = total_amnt;
        this.due_date = due_date;
        this.bill_paid_status = bill_paid_status;
        this.payment_date = payment_date;
    }

    // Getters
    public String getCus_id() {
        return cus_id;
    }

    public int getBilling_month() {
        return billing_month;
    }

    public int getCurr_reg_meter() {
        return curr_reg_meter;
    }

    public int getCurr_reg_peak() {
        return curr_reg_peak;
    }

    public String getReading_date() {
        return reading_date;
    }

    public int getCost_of_elec() {
        return cost_of_elec;
    }

    public int getSales_tax() {
        return sales_tax;
    }

    public int getFixed_charges() {
        return fixed_charges;
    }

    public int getTotal_amnt() {
        return total_amnt;
    }

    public String getDue_date() {
        return due_date;
    }

    public String getBill_paid_status() {
        return bill_paid_status;
    }

    public String getPayment_date() {
        return payment_date;
    }

    // Setters
    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public void setBilling_month(int billing_month) {
        this.billing_month = billing_month;
    }

    public void setCurr_reg_meter(int curr_reg_meter) {
        this.curr_reg_meter = curr_reg_meter;
    }

    public void setCurr_reg_peak(int curr_reg_peak) {
        this.curr_reg_peak = curr_reg_peak;
    }

    public void setReading_date(String reading_date) {
        this.reading_date = reading_date;
    }

    public void setCost_of_elec(int cost_of_elec) {
        this.cost_of_elec = cost_of_elec;
    }

    public void setSales_tax(int sales_tax) {
        this.sales_tax = sales_tax;
    }

    public void setFixed_charges(int fixed_charges) {
        this.fixed_charges = fixed_charges;
    }

    public void setTotal_amnt(int total_amnt) {
        this.total_amnt = total_amnt;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public void setBill_paid_status(String bill_paid_status) {
        this.bill_paid_status = bill_paid_status;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    // Methods for loading and saving billing data remain the same
    public static List<BillingInfo> loadBillingData(String filename) {
        List<BillingInfo> billingList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 12) {
                    BillingInfo billingInfo = new BillingInfo(
                            parts[0],
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]),
                            parts[4],
                            Integer.parseInt(parts[5]),
                            Integer.parseInt(parts[6]),
                            Integer.parseInt(parts[7]),
                            Integer.parseInt(parts[8]),
                            parts[9],
                            parts[10],
                            parts[11].trim().equals("0") ? "" : parts[11].trim()
                    );
                    billingList.add(billingInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return billingList;
    }

    public static void saveBillingData(List<BillingInfo> billingInfos, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (BillingInfo billingInfo : billingInfos) {
                writer.write(billingInfo.getCus_id() + "," + billingInfo.getBilling_month() + "," + billingInfo.getCurr_reg_meter() + "," +
                        billingInfo.getCurr_reg_peak() + "," + billingInfo.getReading_date() + "," + billingInfo.getCost_of_elec() + "," +
                        billingInfo.getSales_tax() + "," + billingInfo.getFixed_charges() + "," + billingInfo.getTotal_amnt() + "," +
                        billingInfo.getDue_date() + "," + billingInfo.getBill_paid_status() + "," +
                        (billingInfo.getPayment_date().equals("") ? "0" : billingInfo.getPayment_date()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
