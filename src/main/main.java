package main;

import controller.BillingController;
import model.BillingInfo;
import view.BillingView;
import controller.CustomerController;
import model.Customer;
import view.CustomerView;
import model.EmployeeModel;
import view.EmployeeView;
import controller.EmployeeController;
import model.NadraDBModel;
import view.NadraDBView;
import controller.NadraDBController;
import model.TaxTariffInfo;
import view.TaxTariffView;
import controller.TaxTariffController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        // File paths
        String employeeFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\EmployeesData.txt";
        String customerFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\CustomerInfo.txt";
        String billingFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\BillingInfo.txt";
        String nadraDBFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\NadraDB.txt";
        String tariffTaxFile = "C:\\Users\\city\\Desktop\\java\\LESCO_MVC\\src\\resources\\TariffTaxInfo.txt";

        // Initialize Controllers and Models
        EmployeeModel employeeModel = new EmployeeModel(employeeFile);
        EmployeeView employeeView = new EmployeeView();
        EmployeeController employeeController = new EmployeeController(employeeModel, employeeView);
        employeeController.loadEmployeeData();

        List<BillingInfo> billingInfos = BillingInfo.loadBillingData(billingFile);
        BillingView billingView = new BillingView();
        BillingController billingController = new BillingController(billingInfos, billingView);

        Customer customerModel = new Customer(customerFile);
        CustomerView customerView = new CustomerView();
        CustomerController customerController = new CustomerController(customerModel, customerView);

        NadraDBModel.setFilename(nadraDBFile);
        NadraDBModel nadraModel = new NadraDBModel();
        NadraDBView nadraView = new NadraDBView();
        NadraDBController nadraController = new NadraDBController(nadraModel, nadraView);
        List<NadraDBModel> nadraList = nadraController.loadNadraData();
        List<Customer> customers = Customer.loadCustomerData();

        TaxTariffInfo taxTariffModel = new TaxTariffInfo(tariffTaxFile);
        TaxTariffView taxTariffView = new TaxTariffView();
        TaxTariffController taxTariffController = new TaxTariffController(taxTariffModel, taxTariffView);

        Scanner sc = new Scanner(System.in);
        int option = 0;

        while (option != -1) {
            System.out.println("Enter 1 for Employee, 2 for Customer, or -1 to Exit: ");
            option = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (option) {
                case 1: // Employee actions
                    if (employeeController.handleLogin()) {
                        System.out.println("Login Successful!");

                        boolean employeeActive = true;
                        while (employeeActive) {
                            System.out.println("Enter 1 to change password, 2 to view a customer bill, 3 to check expiry dates, 4 to show bill reports, -1 to go back: ");
                            int empAction = sc.nextInt();
                            sc.nextLine();  // Consume newline

                            if (empAction == 1) {
                                employeeController.handleChangePassword();
                            } else if (empAction == 2) {
                                System.out.println("Enter customer ID to view bill: ");
                                String cusId = sc.nextLine();
                                billingController.viewBill();
                            } else if (empAction == 3) {
                                nadraController.checkExpiry(nadraList, customers);
                            } else if (empAction == 4) {
                                billingController.showBillReports();
                            } else if (empAction == -1) {
                                employeeActive = false;
                            }
                        }
                    } else {
                        System.out.println("Login Failed!");
                    }
                    break;

                case 2: // Customer actions
                    System.out.println("Enter customer ID: ");
                    String customerId = sc.nextLine();
                    customerController.displayCustomerInfo(customerId);

                    boolean customerActive = true;
                    while (customerActive) {
                        System.out.println("Enter 1 to update NADRA info, 2 to view a bill, -1 to go back: ");
                        int custAction = sc.nextInt();
                        sc.nextLine();  // Consume newline

                        if (custAction == 1) {
                            System.out.println("Enter new NADRA ID: ");
                            String newId = sc.nextLine();
                            System.out.println("Enter new address: ");
                            String newAddress = sc.nextLine();
                            System.out.println("Enter new phone number: ");
                            String newPhoneNo = sc.nextLine();
                            customerController.updateCustomerInfo(newId, newPhoneNo, newAddress);
                        } else if (custAction == 2) {
                            billingController.viewBill();
                        } else if (custAction == -1) {
                            customerActive = false;
                        }
                    }
                    break;

                case -1: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option! Please enter 1, 2, or -1.");
                    break;
            }
        }

        sc.close();
    }
}
