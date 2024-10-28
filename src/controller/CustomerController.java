package controller;
import java.util.List;
import model.Customer;
import view.CustomerView;

import java.util.List;

public class CustomerController {
    private Customer model;
    private CustomerView view;
    String customerFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\CustomerInfo.txt";

    // Constructor
    public CustomerController(Customer model, CustomerView view) {
        this.model = model;
        this.view = view;
    }

    // Load customer data from file
    public List<Customer> getCustomerData() {
        return Customer.loadCustomerData(customerFile);
    }

    // Display customer information using the view
//    public void displayCustomerInfo(String cus_id) {
//        List<Customer> customerList = getCustomerData();
//        Customer matchedCustomer = null;
//        for (Customer customer : customerList) {
//            if (customer.getCus_id().equals(cus_id)) {
//                matchedCustomer = customer;
//                break;
//            }
//        }
//        if (matchedCustomer != null) {
//            view.displayCustomerInfo(matchedCustomer);
//        } else {
//            view.displayErrorMessage("Customer not found.");
//        }
//    }

    // Update customer information
    public void updateCustomerInfo(String cus_id, String phone_no, String address) {
        List<Customer> customerList = getCustomerData();
        for (Customer customer : customerList) {
            if (customer.getCus_id().equals(cus_id)) {
                customer.setPhone_no(phone_no);
                customer.setAddress(address);
                // Logic to save the updated data to the file (not shown here)
                break;
            }
        }
    }
}
