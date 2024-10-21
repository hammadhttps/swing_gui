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

import static java.lang.Thread.*;

public class main {
    public static void main(String[] args) throws InterruptedException {

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


        LESCOBillingSystem_GUI obj = new LESCOBillingSystem_GUI();
        sleep(2500);
        if (obj.getvalue() == 1) {
            employeeController.createLoginFrame();

        } else if (obj.getvalue() == 2) {

        }


    }
}
