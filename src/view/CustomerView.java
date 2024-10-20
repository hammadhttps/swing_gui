package view;
import model.Customer;
import java.util.Scanner;

public class CustomerView {
    public void displayCustomerInfo(Customer customer) {
        System.out.println("Customer ID: " + customer.getCus_id());
        System.out.println("CNIC: " + customer.getCNIC());
        System.out.println("Name: " + customer.getName());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Phone No: " + customer.getPhone_no());
        System.out.println("Customer Type: " + customer.getCus_type());
        System.out.println("Meter Type: " + customer.getMeter_type());
        System.out.println("Connection Date: " + customer.getConnec_date());
        System.out.println("Regular Hour Units: " + customer.getReg_hour_units());
        System.out.println("Peak Hour Units: " + customer.getPeak_hour_units());
        System.out.println();
    }

    public void displayErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
