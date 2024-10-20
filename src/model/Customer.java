package model;

import java.io.*;
import java.util.*;


public class Customer {
    private String cus_id;
    private String CNIC;
    private String name;
    private String phone_no;
    private String address;
    private char cus_type;
    private String meter_type;
    private String connec_date;
    private int reg_hour_units;
    private int peak_hour_units;
    private static String filename;

    // Constructor with file name
    public Customer(String filename1) {
        filename = filename1;
    }

    // Default constructor
    public Customer() {}

    // Full constructor
    public Customer(String cus_id, String CNIC, String name, String address, String phone_no, char cus_type, String meter_type, String connec_date, int reg_hour_units, int peak_hour_units) {
        this.cus_id = cus_id;
        this.CNIC = CNIC;
        this.name = name;
        this.address = address;
        this.phone_no = phone_no;
        this.cus_type = cus_type;
        this.meter_type = meter_type;
        this.connec_date = connec_date;
        this.reg_hour_units = reg_hour_units;
        this.peak_hour_units = peak_hour_units;
    }

    // Getters and Setters
    public String getCus_id() { return cus_id; }
    public void setCus_id(String cus_id) { this.cus_id = cus_id; }

    public String getCNIC() { return CNIC; }
    public void setCNIC(String CNIC) { this.CNIC = CNIC; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone_no() { return phone_no; }
    public void setPhone_no(String phone_no) { this.phone_no = phone_no; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public char getCus_type() { return cus_type; }
    public void setCus_type(char cus_type) { this.cus_type = cus_type; }

    public String getMeter_type() { return meter_type; }
    public void setMeter_type(String meter_type) { this.meter_type = meter_type; }

    public String getConnec_date() { return connec_date; }
    public void setConnec_date(String connec_date) { this.connec_date = connec_date; }

    public int getReg_hour_units() { return reg_hour_units; }
    public void setReg_hour_units(int reg_hour_units) { this.reg_hour_units = reg_hour_units; }

    public int getPeak_hour_units() { return peak_hour_units; }
    public void setPeak_hour_units(int peak_hour_units) { this.peak_hour_units = peak_hour_units; }

    // Load customer data from file
    public static List<Customer> loadCustomerData() {
        List<Customer> customerList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    Customer customer = new Customer(
                            parts[0], parts[1], parts[2], parts[3], parts[4],
                            parts[5].charAt(0), parts[6], parts[7],
                            Integer.parseInt(parts[8]), Integer.parseInt(parts[9])
                    );
                    customerList.add(customer);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerList;
    }
}
